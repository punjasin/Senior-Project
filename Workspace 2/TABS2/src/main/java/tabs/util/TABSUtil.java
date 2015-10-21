package tabs.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tabs.entity.BidData;
import tabs.entity.Bidding;
import tabs.entity.User;
import tabs.service.bidding.BidDataService;
import tabs.service.bidding.BiddingService;
import tabs.service.user.UserService;

@Component
public class TABSUtil {

	@Autowired
	BiddingService biddingService;
	
	@Autowired
	BidDataService bidDataService;
	
	@Autowired
	UserService userService;

	
	@Scheduled(fixedRate = 5000)
	public void manageBiddingStatus() {
		Collection<Bidding> biddingList = biddingService.getBiddingList();		
		for(Bidding persistedBidding : biddingList){			
			if(Timestamp.valueOf(LocalDateTime.now()).after(persistedBidding.getUpdateStartTime()) && Timestamp.valueOf(LocalDateTime.now()).before(persistedBidding.getUpdateEndTime())){
				biddingService.setBiddingStatus(persistedBidding.getId(),"In bidding round");							
			}
			if(Timestamp.valueOf(LocalDateTime.now()).after(persistedBidding.getUpdateEndTime())){
				biddingService.setBiddingStatus(persistedBidding.getId(),"End");
				Collection<BidData> bidDataList = bidDataService.getBidDataListByBiddingId(persistedBidding.getId());
				ArrayList<BidData> list = new ArrayList<BidData>(bidDataList);
				Collections.sort(list,Collections.reverseOrder(new BidDataComparater()));
				int seat = persistedBidding.getSeat_quota();
				for(BidData b : list){
					if(seat>0){
						b.setStatus("Win");
						bidDataService.saveBidData(b);
					}else{
						b.setStatus("Lose");
						User u = userService.getUserByStudentId(b.getStudent_id());
						u.setToken(b.getToken()+u.getToken());
						userService.update(u);
						b.setToken(0);
						bidDataService.saveBidData(b);
					}
					seat--;						
				}				
			}
		}		
	}
}

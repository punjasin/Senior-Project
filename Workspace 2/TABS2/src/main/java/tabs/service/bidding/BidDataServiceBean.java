package tabs.service.bidding;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabs.entity.BidData;
import tabs.entity.User;
import tabs.repository.BidDataRepository;
import tabs.repository.UserRepository;

@Transactional
@Service
public class BidDataServiceBean implements BidDataService {

	@Autowired
	BidDataRepository bidDataRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Collection<BidData> getBidDataListByBiddingId(Long bId) {
		Collection<BidData> bidDataList = bidDataRepo
				.getBidDataListByBiddingId(bId);
		return bidDataList;
	}

	public BidData saveBidData(BidData bidData) {
		User u = userRepo.getUserByStudentID(bidData.getStudent_id());
		if (bidData.getId() == null) {
			userRepo.updateToken(bidData.getStudent_id(), u.getToken()
					- bidData.getToken());

			
			BidData savedBidData = bidDataRepo.save(bidData);
			return savedBidData;
		}
		BidData persistedBidData = bidDataRepo.findOne(bidData.getId());
		if (persistedBidData != null
				&& persistedBidData.getStudent_id() == u.getStudent_id()) {
			userRepo.updateToken(
					persistedBidData.getStudent_id(),
					(u.getToken() + persistedBidData.getToken())
							- bidData.getToken());
			return bidDataRepo.save(bidData);
		}
		return null;
	}

	@Override
	public BidData getUserBidData(Long biddingId, int student_id) {
		BidData persistedBidData = bidDataRepo.getUserBidData(biddingId, student_id);
		if(persistedBidData==null){
			return null;
		}
		return persistedBidData;
	}
	
	public Collection<BidData> getBidDataListByStudentId(int student_id){
		Collection<BidData> userBidDataList = bidDataRepo.getUserBidDataList(student_id);				
		return userBidDataList;
	}

	@Override
	public void cancelBid(Long biddingId, int student_id) {
		User u = userRepo.getUserByStudentID(student_id);
		BidData persistedBidData = bidDataRepo.getUserBidData(biddingId, student_id);
		if(persistedBidData==null){
			
		}else{
			userRepo.updateToken(u.getStudent_id(), u.getToken()+persistedBidData.getToken());
			bidDataRepo.deleteBidData(student_id, biddingId);
		}							
	}
	
	public Collection<BidData> getWinBidDataList(Long bidding_id){		
		return bidDataRepo.getWinBidDataList(bidding_id);
	}

}

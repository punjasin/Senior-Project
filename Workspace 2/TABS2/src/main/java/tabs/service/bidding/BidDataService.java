package tabs.service.bidding;

import java.util.Collection;

import tabs.entity.BidData;

public interface BidDataService {
	
	Collection<BidData> getBidDataListByBiddingId(Long bId);
	BidData saveBidData(BidData bidData);
	BidData getUserBidData(Long biddingId,int student_id);
	
}

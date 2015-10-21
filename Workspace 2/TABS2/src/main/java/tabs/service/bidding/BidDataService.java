package tabs.service.bidding;

import java.util.Collection;

import tabs.entity.BidData;

public interface BidDataService {
	
	Collection<BidData> getBidDataListByBiddingId(Long bId);
	Collection<BidData> getBidDataListByStudentId(int student_id);
	BidData saveBidData(BidData bidData);
	BidData getUserBidData(Long biddingId,int student_id);
	void cancelBid(Long biddingId,int student_id);
	Collection<BidData> getWinBidDataList(Long bidding_id);
	
}

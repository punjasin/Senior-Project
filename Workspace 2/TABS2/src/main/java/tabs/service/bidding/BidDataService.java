package tabs.service.bidding;

import java.util.Collection;

import tabs.entity.BidData;

public interface BidDataService {
	
	Collection<BidData> getBidDataListByBiddingId(Long bId);
	
	
}

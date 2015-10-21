package tabs.service.bidding;

import java.util.Collection;

import tabs.entity.Bidding;

public interface BiddingService {
	
	public Bidding getBidding(Long id);
	public Collection<Bidding> getBiddingList();
	public Bidding create(Bidding bidding);
	public Bidding update(Bidding bidding);
	public void setBiddingStatus(Long bidding_id, String status);
	public void deleteBidding(Long id, Long activity_id);
}

package tabs.service.bidding;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabs.entity.Bidding;
import tabs.repository.BiddingRepository;
import tabs.service.activity.ActivityService;

@Transactional
@Service
public class BiddingServiceBean implements BiddingService {

	@Autowired
	private BiddingRepository biddingRepo;

	@Autowired
	ActivityService actService;
	

	@Override
	public Bidding getBidding(Long id) {
		if (id == null || id == 0) {
			return null;
		}
		return biddingRepo.findOne(id);
	}
	

	@Override
	public Collection<Bidding> getBiddingList() {
		Collection<Bidding> biddingList = biddingRepo.findAll();
		if (biddingList.isEmpty() == true) {
			return null;
		} else {
			return biddingRepo.findAll();
		}
	}

	@Override
	public Bidding create(Bidding bidding) {
		if (bidding.getId() != null) {
			return null;
		}
		if (bidding.getActivity_id() == null || bidding.getTitle() == null
				|| bidding.getDescription() == null
				|| bidding.getbStart_time() == null
				|| bidding.getbEnd_time() == null) {
			return null;
		}
		Bidding createdBidding = biddingRepo.save(bidding);
		actService.setStatus(bidding.getActivity_id(), true);
		return createdBidding;
	}

	@Override
	public Bidding update(Bidding bidding) {
		Bidding persistedBidding = biddingRepo.getOne(bidding.getId());
		actService.setStatus(persistedBidding.getActivity_id(), false);
		if (persistedBidding.getId() == null) {
			return null;
		}else{
			Bidding updated = biddingRepo.save(bidding);
			actService.setStatus(bidding.getActivity_id(), true);
			return updated;
		}		
	}

	@Override
	public void deleteBidding(Long id, Long activity_id) {
		actService.setStatus(activity_id, false);
		biddingRepo.delete(id);
	}

	@Override
	public void setBiddingStatus(Long id, String status) {
		if(biddingRepo.getOne(id)!=null){
			biddingRepo.changeBiddingStatus(id, status);
		}
	}
}

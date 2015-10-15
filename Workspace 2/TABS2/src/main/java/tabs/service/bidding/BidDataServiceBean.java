package tabs.service.bidding;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabs.entity.BidData;
import tabs.repository.BidDataRepository;

@Transactional
@Service
public class BidDataServiceBean implements BidDataService{
	
	@Autowired
	BidDataRepository bidDataRepo;

	@Override
	public Collection<BidData> getBidDataListByBiddingId(Long bId) {
		Collection<BidData> bidDataList = bidDataRepo.getBidDataListByBiddingId(bId);
		return bidDataList;
	}

}

package com.test.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.model.Bidding;
import com.test.repository.BiddingRepository;

@Service
public class BiddingServiceBean implements BiddingService{
	
	@Autowired
	private BiddingRepository biddingRepository;

	@Override
	public Collection<Bidding> findAll() {
		Collection<Bidding> bidding = biddingRepository.findAll();
		return bidding;
	}

	@Override
	public Bidding findOne(Long id) {
		Bidding bidding = biddingRepository.findOne(id);
		return bidding;
	}

	@Override
	public Bidding create(Bidding bid) {
		if (bid.getId() != null) {
			// Cannot create Student with specified ID value
			return null;
		}
		Bidding savedBidding = biddingRepository.save(bid);
		return savedBidding;
	}

	@Override
	public Bidding update(Bidding bid) {
		Bidding biddingPersisted = findOne(bid.getId());
		if(biddingPersisted == null){
			//Cannot update Student that has not been persisted
			return null;
		}
		Bidding updatedBidding = biddingRepository.save(bid);
		return updatedBidding;
	}

	@Override
	public void delete(Long id) {
		biddingRepository.delete(id);
		
	}

}

package com.test.service;

import java.util.Collection;

import com.test.model.Bidding;

public interface BiddingService {

	Collection<Bidding> findAll();
	
	Bidding findOne(Long id);
	
	Bidding create(Bidding bid);
	
	Bidding update(Bidding bid);
	
	void delete(Long id);
}

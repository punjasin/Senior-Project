package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Bidding;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long>{

}

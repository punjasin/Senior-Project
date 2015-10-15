package tabs.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tabs.entity.BidData;

@Transactional
@Repository
public interface BidDataRepository extends JpaRepository<BidData, Long>{

	
	@Query("select bd from BidData bd where bd.bidding_id = :bId")
	Collection<BidData> getBidDataListByBiddingId(@Param("bId")Long bId);
	
	
}

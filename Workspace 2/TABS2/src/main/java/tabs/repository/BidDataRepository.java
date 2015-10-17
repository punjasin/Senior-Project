package tabs.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tabs.entity.BidData;

@Transactional
@Repository
public interface BidDataRepository extends JpaRepository<BidData, Long>{

	
	@Query("select bd from BidData bd where bd.bidding_id = :bId")
	Collection<BidData> getBidDataListByBiddingId(@Param("bId")Long bId);
	
	@Query("select bd from BidData bd where bd.bidding_id = :bidding_id AND bd.student_id = :sId")
	BidData getUserBidData(@Param("bidding_id")Long bidding_id,@Param("sId")int sId);
	
	@Modifying
	@Query("update BidData bd set bd.token = :token where bd.bidding_id = :bId AND bd.student_id = :sId")
	void updateBidData(@Param("bId")Long bId,@Param("sId")int sId,@Param("token")int token);

	
}

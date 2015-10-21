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
	
	@Query("select bd from BidData bd where bd.bidding_id = :bidding_id AND bd.student_id = :student_id")
	BidData getUserBidData(@Param("bidding_id")Long bidding_id,@Param("student_id")int student_id);
	
	@Modifying
	@Query("update BidData bd set bd.token = :token where bd.bidding_id = :bidding_id AND bd.student_id = :student_id")
	void updateBidData(@Param("bidding_id")Long bidding_id,@Param("student_id")int student_id,@Param("token")int token);
	
	@Query("select bd from BidData bd where bd.student_id = :student_id")
	Collection<BidData> getUserBidDataList(@Param("student_id")int student_id);
	
	@Modifying
	@Query("delete from BidData bd where bd.bidding_id= :bidding_id AND bd.student_id = :student_id")
	void deleteBidData(@Param("student_id")int student_id, @Param("bidding_id")Long bidding_id);
	
	@Query("select bd from BidData bd where bd.bidding_id = :bidding_id AND bd.status = 'Win' ")
	Collection<BidData> getWinBidDataList(@Param("bidding_id")Long bidding_id);
}

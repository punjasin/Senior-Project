package tabs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tabs.entity.Bidding;

@Transactional
@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {

	/*@Modifying
	@Query("update Bidding b set b.title = :title, b.activity_id = :activity_id, b.description = :description, b.bStart_time = :bStart_time, b.bEnd_time = :bEnd_time, b.seat_quota = :seat_quota where b.id = :id")
	public void updateBidding(@Param("title") String title,
			@Param("activity_id") Long activity_id,
			@Param("description") String description,
			@Param("bStart_time")Timestamp bStart_time,
			@Param("bEnd_time")Timestamp bEnd_time,
			@Param("seat_quota")int seat_quota,
			@Param("id") Long bId);*/

	@Modifying
	@Query("update Bidding b set b.status = :status where b.id = :id")
	public void changeBiddingStatus(@Param("id") Long bId, @Param("status") String status);
	
	
}

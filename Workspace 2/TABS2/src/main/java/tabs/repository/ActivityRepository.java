package tabs.repository;

import java.sql.Timestamp;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tabs.entity.Activity;

@Transactional
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Modifying(clearAutomatically = true)
	@Query("update Activity a set a.activity_name = :name, a.description = :description, a.place = :place, a.start_time = :start_time, a.end_time = :end_time, a.seat_quota = :seat_quota where a.id = :id ")
	void editActivity(@Param("name") String activity_name,
			@Param("description") String description,
			@Param("place") String place,
			@Param("start_time") Timestamp start_time,
			@Param("end_time") Timestamp end_time,
			@Param("seat_quota") int seat_quota, 
			@Param("id") Long id);

	@Query("select a from Activity a where a.isSelected = false")
	Collection<Activity> getAvailableActivity(); 

	@Modifying(clearAutomatically = true)
	@Query("update Activity a set a.isSelected = :status where a.id = :id")
	void setStatus(@Param("status")boolean status, @Param("id")Long id);
}

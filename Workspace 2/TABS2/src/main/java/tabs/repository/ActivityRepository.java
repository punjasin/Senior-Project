package tabs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tabs.entity.Activity;

@Transactional
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	
}

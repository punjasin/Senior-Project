package tabs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tabs.entity.Bidding;


@Transactional
@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long>{

	
}

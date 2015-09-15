package tabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tabs.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
}

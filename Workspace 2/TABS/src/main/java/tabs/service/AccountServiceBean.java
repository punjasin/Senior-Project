package tabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tabs.domain.Account;
import tabs.repository.AccountRepository;

@Service
public class AccountServiceBean implements AccountService{
	
	@Autowired
	AccountRepository accountRepositoty;

	@Override	
	public Account findByUser(String username) {
		
		return null;
	}

}

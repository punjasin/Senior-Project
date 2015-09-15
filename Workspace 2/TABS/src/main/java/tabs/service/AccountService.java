package tabs.service;

import tabs.domain.Account;

public interface AccountService {
		
	Account findByUser(String username);
	
}

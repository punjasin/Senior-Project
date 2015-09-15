package tabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabs.domain.Account;
import tabs.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accService;
	
	@RequestMapping(value = "/acc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getUserByUsername(@RequestParam(value="username")String username){
		
		Account acc = accService.findByUser(String.format(username));
		
		return new ResponseEntity<Account>(acc,HttpStatus.OK);
	}
}

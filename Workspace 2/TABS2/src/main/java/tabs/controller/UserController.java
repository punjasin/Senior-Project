package tabs.controller;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabs.entity.User;
import tabs.service.user.UserService;

@Transactional
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getProfile", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByEmail(@RequestParam(value="email")String email){
		User user = userService.getUserByEmail(email); 
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllUsers",  method = RequestMethod.GET)
	public ResponseEntity<Collection<User>> getAllUsers(){
		Collection<User> users = userService.getAllUsers();
		return new ResponseEntity<Collection<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/registerAcc", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user){		
		User createdUser = userService.create(user);
		if(userService.getERROR_CODE()==1){
			return new ResponseEntity<User>(createdUser,HttpStatus.INTERNAL_SERVER_ERROR);
		}if(userService.getERROR_CODE()==2){
			return new ResponseEntity<User>(createdUser,HttpStatus.NOT_ACCEPTABLE);
		}else{
			return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
		}		
	}
	
	@RequestMapping(value="/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<User> update(@RequestBody User user){		
		User updatedUser = userService.update(user);
		if(userService.getERROR_CODE()==1){
			return new ResponseEntity<User>(updatedUser,HttpStatus.INTERNAL_SERVER_ERROR);
		}else{
			return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
		}		
	}
	
	@RequestMapping(value="/changePasswordRest", method = RequestMethod.POST)
	public ResponseEntity<User> changePassword(@RequestBody User user){		
		User updatedUser = userService.changePassword(user);
		if(userService.getERROR_CODE()==1){
			return new ResponseEntity<User>(updatedUser,HttpStatus.INTERNAL_SERVER_ERROR);
		}else{
			return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
		}		
	}
	
}

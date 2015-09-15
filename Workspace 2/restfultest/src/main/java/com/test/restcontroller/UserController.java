package com.test.restcontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService studentService;
	
	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllStudent(){
		
		Collection<User> allStudent = studentService.findAll();
		
		return new ResponseEntity<Collection<User>>(allStudent,HttpStatus.OK);
	}
}

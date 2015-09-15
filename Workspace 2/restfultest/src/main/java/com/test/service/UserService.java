package com.test.service;

import java.util.Collection;
import com.test.model.User;

public interface UserService {

	Collection<User> findAll();
	
	User findOne(Long id);
	
	User create(User stadium);
	
	User update(User stadium);
	
	void delete(Long id);
}

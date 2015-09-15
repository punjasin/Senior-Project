package demo.service;

import demo.entity.User;

public interface UserService {

	public User create(User user);
	public User update(User user);	
	public User getUser(Long id);
	public User getUserByEmailAndPassword(String email, String password);
}

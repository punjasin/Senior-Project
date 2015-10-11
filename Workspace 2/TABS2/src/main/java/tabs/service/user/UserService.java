package tabs.service.user;

import java.util.Collection;

import tabs.entity.User;

public interface UserService {
	public int getERROR_CODE();
	public User create(User user);
	public User update(User user);
	public User changePassword(User user);
	public User getUserById(Long id);
	public User getUserByEmail(String email);
	public Collection<User> getAllUsers();
}

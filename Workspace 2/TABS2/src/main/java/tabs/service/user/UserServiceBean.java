package tabs.service.user;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabs.entity.Role;
import tabs.entity.User;
import tabs.repository.UserRepository;

@Transactional
@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepo;
	private int ERROR_CODE;

	public int getERROR_CODE() {
		return ERROR_CODE;
	}

	public void setERROR_CODE(int eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}

	@Override
	public User create(User user) {
		if (user == null) {
			System.out.println("user=null");
			return null;
		}
		if (user.getId() != null || user.getEmail() == null
				|| user.getPassword() == null || user.getStudent_id() == 0
				|| user.getFirstName() == null || user.getLastName() == null
				|| user.getMajor() == null || user.getFaculty() == null) {
			System.out.println("ERROR : some data is empty");
			return null;
		}
		if (user.getEmail() != null) {
			Collection<String> emailList = userRepo.getEmailList();
			for (Iterator<String> iterator = emailList.iterator(); iterator
					.hasNext();) {
				if (iterator.next().equals(user.getEmail())) {
					setERROR_CODE(1);
					return null;
				}
			}
		}
		if (user.getStudent_id() != 0) {
			Collection<Integer> studentIdList = userRepo.getStudentIdList();
			for (Iterator<Integer> iterator = studentIdList.iterator(); iterator
					.hasNext();) {
				if ((int) iterator.next() == user.getStudent_id()) {
					setERROR_CODE(2);
					return null;
				}
			}
		}
		user.setToken(1000);
		user.setRole(Role.USER);
		User savedUser = userRepo.save(user);
		setERROR_CODE(0);
		return savedUser;
	}

	@Override
	public User update(User user) {
		if (user == null) {
			System.out.println("user = null");
			return null;
		}
		if (user.getId() == null) {
			System.out.println("id = null");
			return null;
		}
		User userPersisted = userRepo.findOne(user.getId());

		if (userPersisted == null) {
			System.out.println("persisted not found or miss some data");
			return null;
		}
		if (user.getEmail() == null || user.getFirstName() == null
				|| user.getLastName() == null || user.getFaculty() == null
				|| user.getMajor() == null) {
			System.out.println("miss some data");
			return null;
		}
		if (user.getEmail() != null) {
			List<Long> idList = userRepo.getIDList();
			Collection<String> emailList = userRepo.getEmailList();
			int i = 0;
			for (Iterator<String> iterator = emailList.iterator(); iterator
					.hasNext();) {
				if (user.getEmail().equals(iterator.next())
						&& user.getId() != idList.get(i)) {
					setERROR_CODE(1);
					System.out.println("Duplicate Email");
					i = 0;
					return null;
				}
				i++;
			}
		}
		System.out.println("OK");
		userRepo.updateProfile(user.getEmail(), user.getFirstName(),
				user.getLastName(), user.getMajor(), user.getFaculty(),
				user.getId());
		setERROR_CODE(0);
		return user;
	}

	@Override
	public User changePassword(User user) {
		if (user == null) {
			setERROR_CODE(1);
			System.out.println("user = null");
			return null;
		}
		if (user.getId() == null) {
			setERROR_CODE(1);
			System.out.println("id = null");
			return null;
		}
		User userPersisted = userRepo.findOne(user.getId());
		if (userPersisted == null) {
			setERROR_CODE(1);
			System.out.println("persisted not found or miss some data");
			return null;
		}
		if (user.getPassword() == null) {
			setERROR_CODE(1);
			return null;
		}
		if (userPersisted.getId() == user.getId()) {
			userRepo.changePassword(user.getId(), user.getPassword());
		}
		setERROR_CODE(0);
		return user;
	}

	@Override
	public User getUserById(Long id) {
		if (id == null) {
			return null;
		} else {
			User user = userRepo.findOne(id);
			return user;
		}
	}

	public User getUserByEmail(String email) {
		User user = userRepo.getUserByEmail(email);
		return user;
	}

	@Override
	public Collection<User> getAllUsers() {
		Collection<User> user = userRepo.findAll();
		return user;
	}
}

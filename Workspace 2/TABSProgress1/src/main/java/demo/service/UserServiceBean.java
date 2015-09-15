package demo.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.repository.UserRepository;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User create(User user) {
		if (user == null){
			return null;
		}
		if (user.getId() != null || user.getEmail() == null
				|| user.getPassword() == null || user.getFirstName() == null
				|| user.getLastName() == null || user.getMajor() == null
				|| user.getFaculty() == null || user == null || user.getEmail().contains("@") != true) {
			return null;
		} else {
			Collection<String> emailList = userRepo.getEmailList();
			Collection<Integer> studentIdList = userRepo.getStudentIdList();

			for (Iterator<String> iterator = emailList.iterator(); iterator
					.hasNext();) {
				if (user.getEmail() == iterator.next()) {
					return null;
				}
			}
			for (Iterator<Integer> iterator = studentIdList.iterator(); iterator
					.hasNext();) {
				if (user.getStudent_id() == (int) iterator.next()) {
					return null;
				}
			}
			user.setToken(1000);
			user.setRole("student");
			User savedUser = userRepo.save(user);
			return savedUser;
		}
	}

	@Override
	public User update(User user) {
		if (user == null){
			return null;
		}
		if (user.getId() == null){
			return null;
		}
		User userPersisted = userRepo.findOne(user.getId());
		if (userPersisted == null || user.getEmail() == null
				|| user.getPassword() == null || user.getFirstName() == null
				|| user.getLastName() == null) {
			return null;
		} else {
			Collection<String> emailList = userRepo.getEmailList();
			for (Iterator<String> iterator = emailList.iterator(); iterator
					.hasNext();) {
				if (user.getEmail() == iterator.next()
						&& userPersisted.getId() != user.getId()) {
					return null;
				}
			}
			User savedUser = userRepo.save(user);
			return savedUser;
		}
	}

	@Override
	public User getUser(Long id) {
		if(id == null){
			return null;
		}else{
			User user = userRepo.findOne(id);
			return user;
		}		
	}

	public User getUserByEmailAndPassword(String email, String password) {
		User user = userRepo.getUserByEmailAndPassword(email, password);
		return user;
	}
}

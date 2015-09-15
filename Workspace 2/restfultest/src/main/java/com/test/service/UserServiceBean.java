package com.test.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.model.User;
import com.test.repository.UserRepository;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository studentRepository;	

	@Override
	public Collection<User> findAll() {
		Collection<User> students = studentRepository.findAll();
		return students;
	}

	@Override
	public User findOne(Long id) {
		User student = studentRepository.findOne(id);
		return student;
	}

	@Override
	public User create(User student) {
		if (student.getId() != null) {
			// Cannot create Student with specified ID value
			return null;
		}
		User savedStudent = studentRepository.save(student);
		return savedStudent;
	}

	@Override
	public User update(User student) {
		User stadiumPersisted = findOne(student.getId());
		if(stadiumPersisted == null){
			//Cannot update Student that has not been persisted
			return null;
		}
		User updatedStudent = studentRepository.save(student);
		return updatedStudent;
	}

	@Override
	public void delete(Long id) {
		studentRepository.delete(id);
	}
}

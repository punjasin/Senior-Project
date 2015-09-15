package demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where u.email = :email and u.password = :password")
	User getUserByEmailAndPassword(@Param("email")String email,@Param("password") String password);
	
	@Query("select email from User")
	Collection<String> getEmailList();
	
	@Query("select student_id from User")
	Collection<Integer> getStudentIdList();
	
}

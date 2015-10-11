package tabs.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tabs.entity.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u where u.email = :email")
	User getUserByEmail(@Param("email")String email);
	
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.email = :email, u.firstName = :firstName, u.lastName = :lastName, u.major = :major, u.faculty = :faculty where u.id = :id ")
	void updateProfile(@Param("email")String email,@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("major") String major,@Param("faculty") String faculty,@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.password = :password where u.id = :id")
	void changePassword(@Param("id")Long id, @Param("password")String password);
	
	@Query("select email from User")
	Collection<String> getEmailList();
	
	@Query("select student_id from User")
	Collection<Integer> getStudentIdList();
	
	@Query("select id from User")
	List<Long> getIDList();
}

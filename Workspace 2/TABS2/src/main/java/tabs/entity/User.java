package tabs.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Transactional
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password, firstName, lastName, major, faculty;
	private int student_id, token;	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User(){
		
	}
	public User(String email, String password,int student_id, String firstName, String lastName, String major, String faculty, Role role){
		this.email = email;
		this.password = password;
		this.student_id = student_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.major = major;
		this.faculty = faculty;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}

}

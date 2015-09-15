package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.FixMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import demo.Application;
import demo.entity.User;
import demo.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceUnitTestApplication {
	
	@Autowired
	private UserService us;	
	
	
	//UserService Test
	//Data for testing
	User user1 = new User("messi@gmail.com","101010",542115001,"Messi","Lionel","SE","CAMT","student");
	User user2 = new User("ronaldo@gmail.com","777777",542115002,"Ronaldo","Cristiano","SE","CAMT","student");
	User user3 = new User("suarez@gmail.com","999999",542115003,"Suarez","Luis","SE","CAMT","student");
	User user4 = new User("neymar@gmail.com","111111",542115004,"Neymar JR.","Da Silva","SE","CAMT","student");
	
	@Test
	//UTC-01 create
	public void test1UserCreate()throws Exception{
			
		//Test case no.1 - correct information format
		us.create(user1);			
		System.out.println("Test create get id : "+us.getUser((long)1).getId());
		assertEquals("messi@gmail.com",us.getUser((long)1).getEmail());
		assertEquals("101010",us.getUser((long)1).getPassword());
		assertEquals(542115001,us.getUser((long)1).getStudent_id());
		assertEquals("Messi",us.getUser((long)1).getFirstName());
		assertEquals("Lionel",us.getUser((long)1).getLastName());
		assertEquals("Messi",us.getUser((long)1).getFirstName());
		assertEquals("SE",us.getUser((long)1).getMajor());
		assertEquals("CAMT",us.getUser((long)1).getFaculty());
		assertEquals("student",us.getUser((long)1).getRole());
		
		//Test case no.2 - duplicate student id
		user4.setStudent_id(542115001);		
		assertEquals(null,us.create(user4));
		
		user4.setStudent_id(542115004);
		//Test case no.3 - duplicate email
		user4.setEmail("messi@gmail.com");
		assertEquals(null,us.create(user4));
		
		//test case no.4 - test with null
		assertEquals(null,us.create(null));
		
		//test case no.5 - test with specified id
		user3.setId((long)2);
		assertEquals(null,us.create(user3));
		
		//test case no.6 - test with some data is empty
		user2.setMajor(null);
		assertEquals(null,us.create(user2));
	}
	
	@Test
	public void test2UserUpdate()throws Exception{
		//test case no.1 - correct information format
		user1.setId((long)1);
		user1.setLastName("Buadee");		
		us.update(user1);
		
		assertEquals("Buadee", us.getUser((long)1).getLastName());
		
		//test case no.2 - test with some data empty
		us.create(user2);
		user2.setId((long)2);
		user2.setFirstName(null);
		user2.setLastName(null);		
		assertEquals(null, us.update(user2));
		
		//test case no.3 - test with null object
		assertEquals(null, us.update(null));
				
		//test case no.4 - test without id		
		user1.setId(null);
		assertEquals(null, us.update(user1));
		
	}
	
	
	@Test
	public void test3GetUserByEmailAndPassword()throws Exception{		
		//test case no.1 - test with correct data
		assertEquals("Messi",us.getUserByEmailAndPassword("messi@gmail.com", "101010").getFirstName());
		
		//test case no.2 - test with unmatched data
		assertEquals(null, us.getUserByEmailAndPassword("messi@gmail.com", "777777"));
		
		//test case no.3 - test with null
		assertEquals(null, us.getUserByEmailAndPassword(null,null));
	}
	
	@Test
	public void test4GetUser()throws Exception{
		
		//test case no.1 - test with valid id
		System.out.println("test get user id : "+us.getUser((long)1).getId());
		assertEquals("messi@gmail.com",us.getUser((long)1).getEmail());
		assertEquals("101010",us.getUser((long)1).getPassword());
		assertEquals(542115001,us.getUser((long)1).getStudent_id());
		assertEquals("Messi",us.getUser((long)1).getFirstName());
		assertEquals("Buadee",us.getUser((long)1).getLastName());
		assertEquals("SE",us.getUser((long)1).getMajor());
		assertEquals("CAMT",us.getUser((long)1).getFaculty());
		assertEquals("student",us.getUser((long)1).getRole());		
		
		//test case no.2 - test with invalid id
		assertEquals(null,us.getUser((long)6));
		
		//test case no.3 - test with null
		assertEquals(null,us.getUser(null));
	}

}

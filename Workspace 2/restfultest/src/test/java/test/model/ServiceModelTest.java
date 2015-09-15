//package test.model;
//
//import org.junit.Assert;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import org.junit.runners.MethodSorters;
//
//import com.test.Application;
//import com.test.service.StudentServiceBean;
//import com.test.model.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//
//public class ServiceModelTest {
//	
////	//Service Test
////	@Autowired
////	private StudentServiceBean ssb = new StudentServiceBean();	
////	
////	// Test create new Stadium
////	@Test
////	public void test1CreateStadium() throws Exception{				
////		String name = "Anfield";
////		String photo = "http://assets.lfcimages.com/images/international/thumb-no-image-2.jpg";		
////		Student s4 = new Student();
////		s4.setName(name);
////		s4.setPhoto(photo);				
////		ssb.create(s4);
////		
////		Assert.assertEquals(name,ssb.findOne((long)4).getName());	
////		Assert.assertEquals(photo,ssb.findOne((long)4).getPhoto());
////	}
////	
////	// Update created stadium name
////	@Test
////	public void test2UpdateStadium() throws Exception{
////		int id = 4;
////		String name = "Stamford";	
////		Student s4 = new Student();
////		s4.setStadiumID((long)id);
////		s4.setName(name);
////		
////		ssb.update(s4);		
////		Assert.assertEquals("Stamford",ssb.findOne((long)4).getName());
////	}
////	
////	// Test get all stadium
////	@Test
////	public void test3GetAllStadium() throws Exception{
////		ssb.findAll().size();
////		Assert.assertEquals(4, ssb.findAll().size());
////	}
////	
////	// Test delete the stadium with id=2
////	@Test
////	public void test4DeleteStadium() throws Exception{
////		ssb.delete((long) 2);
////		Assert.assertEquals(3, ssb.findAll().size());
////	}
//}

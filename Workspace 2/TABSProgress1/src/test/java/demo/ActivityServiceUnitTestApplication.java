package demo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import demo.entity.Activity;
import demo.service.ActivityService;
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ActivityServiceUnitTestApplication {
	
	@Autowired
	ActivityService as;	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//ActivityService Test
	//Data for testing
	Activity activity1 = new Activity("E3","Electronic Entertainment Expo","LA",Timestamp.valueOf("2015-06-16 08:00:00"),Timestamp.valueOf("2015-06-18 20:00:00"),9999);
	Activity activity2 = new Activity("Google I/O","Google Event","San Francisco",Timestamp.valueOf("2015-05-15 08:00:00"),Timestamp.valueOf("2015-05-15 20:00:00"),9999);
	Activity activity3 = new Activity("WWDC","Apple Event","San Francisco",Timestamp.valueOf("2015-06-08 08:00:00"),Timestamp.valueOf("2015-06-16 20:00:00"),9999);
	Activity activity4 = new Activity("Microsoft Ignite","Microsoft Event","Chicago",Timestamp.valueOf("2015-05-04 08:00:00"),Timestamp.valueOf("2015-05-04 20:00:00"),9999);
	
	@Test
	//UTC-01 create
	public void test5CreateActivity()throws Exception{
			
		//Test case no.1 - correct information format
		as.create(activity1);
		System.out.println("Test create get id : "+as.getActivity((long)1));
		assertEquals("E3", as.getActivity((long)1).getActivity_name());
		assertEquals("Electronic Entertainment Expo", as.getActivity((long)1).getDescription());
		assertEquals("LA", as.getActivity((long)1).getPlace());
		assertEquals("2015-06-16 08:00:00", sdf.format(as.getActivity((long)1).getStart_date()).toString());
		assertEquals("2015-06-18 20:00:00", sdf.format(as.getActivity((long)1).getEnd_date()).toString());
		assertEquals(9999, as.getActivity((long)1).getSeat_quota());
		
		//test case no.2 - test with some data is empty
		activity3.setPlace(null);
		activity3.setSeat_quota(0);
		assertEquals(null, as.create(activity3));
		
		//Test case no.3 - duplicate activity name, start and end date
		activity1.setDescription("E55555");
		activity1.setPlace("Thailand");
		activity1.setSeat_quota(500);
		assertEquals(null, as.create(activity1));		
		
		//test case no.4 - test with specified id
		activity2.setId((long)5);
		assertEquals(null, as.create(activity2));
		
		//test case no.5 - test with null
		assertEquals(null,as.create(null));
	}
	
	@Test
	public void test6UpdateActivity()throws Exception{
		
		//test case no.1 - correct information format
		activity1.setId((long)1);
		activity1.setPlace("Chian Mai, Thailand");
		as.update(activity1);
		assertEquals("Chian Mai, Thailand", as.getActivity((long)1).getPlace());
		
		
		//test case no.2 - test with some data empty
		as.create(activity3);
		activity3.setPlace(null);
		activity3.setSeat_quota(0);
		assertEquals(null, as.update(activity3));
		as.delete((long)2);
		
		//test case no.3 - test with null object
		assertEquals(null,as.update(null));
		
		//test case no.4 - test without id		
		activity1.setId(null);
		assertEquals(null, as.update(activity1));
	}
	
	
	@Test
	public void test7GetActivity()throws Exception{		
		//test case no.1 - test with valid id
		
		assertEquals("E3", as.getActivity((long)1).getActivity_name());
		assertEquals("Electronic Entertainment Expo", as.getActivity((long)1).getDescription());
		assertEquals("Chian Mai, Thailand", as.getActivity((long)1).getPlace());
		assertEquals("2015-06-16 08:00:00", sdf.format(as.getActivity((long)1).getStart_date()).toString());
		assertEquals("2015-06-18 20:00:00", sdf.format(as.getActivity((long)1).getEnd_date()).toString());
		assertEquals(9999, as.getActivity((long)1).getSeat_quota());
		
		//test case no.2 - test with invalid id
		assertEquals(null, as.getActivity((long)5));
		
		//test case no.3 - test with null
		assertEquals(null, as.getActivity(null));
	}
	
	@Test
	public void test8ActivityList()throws Exception{
		
		//test case no.1 - test with UTC-05
		as.create(activity2);
		as.create(activity3);
		as.create(activity4);
		assertEquals(4, as.getActivityList().size());
		
		
		//test case no.2 - test empty database
		as.delete((long)1);
		as.delete((long)3);
		as.delete((long)4);
		as.delete((long)5);
		
		assertEquals(null, as.getActivityList());
		
	}

}

package tabs.controller;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tabs.entity.Activity;
import tabs.service.activity.ActivityService;

@Transactional
@RestController
public class ActivityController {

	@Autowired
	ActivityService activityService;
	
	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public ResponseEntity<Collection<Activity>> getActivities(){
		Collection<Activity> activityList = activityService.getActivityList();
		return new ResponseEntity<Collection<Activity>>(activityList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getActivity", method = RequestMethod.GET)
	public ResponseEntity<Activity> getActivity(@RequestParam(value="id")Long id){
		Activity activity = activityService.getActivity(id);
		return new ResponseEntity<Activity>(activity,HttpStatus.OK);
	}
		
	@RequestMapping(value = "/createActivityRest", method = RequestMethod.POST)
	public ResponseEntity<Activity> create(@RequestBody Activity activity) {		
		Activity createdActivity = activityService.create(activity);
		if(activityService.getERROR_CODE()==2){
			return new ResponseEntity<Activity>(createdActivity, HttpStatus.INTERNAL_SERVER_ERROR);
		}if(activityService.getERROR_CODE()==1){
			return new ResponseEntity<Activity>(createdActivity, HttpStatus.NOT_ACCEPTABLE);
		}else{
			return new ResponseEntity<Activity>(createdActivity, HttpStatus.CREATED);
		}		
	}	
	
	@RequestMapping(value = "/updateActivityRest", method = RequestMethod.POST)
	public ResponseEntity<Activity> update(@RequestBody Activity activity) {		
		Activity updatedActivity = activityService.update(activity);
		if(activityService.getERROR_CODE()==2){
			return new ResponseEntity<Activity>(updatedActivity, HttpStatus.INTERNAL_SERVER_ERROR);
		}if(activityService.getERROR_CODE()==1){
			return new ResponseEntity<Activity>(updatedActivity, HttpStatus.NOT_ACCEPTABLE);
		}else{
			return new ResponseEntity<Activity>(updatedActivity, HttpStatus.CREATED);
		}		
	}
		
	@RequestMapping(value = "/deleteAct" , method = RequestMethod.DELETE)
	public void deleteActivity(@RequestParam(value="id")Long id){	
		activityService.delete(id); 
	}
	
	@RequestMapping(value = "/getAvailableActivities", method = RequestMethod.GET)
	public ResponseEntity<Collection<Activity>> getAvailableActivity(){
		Collection<Activity> AvailableActivityList = activityService.getAvailableActivity();
		return new ResponseEntity<Collection<Activity>>(AvailableActivityList, HttpStatus.OK);
	}

}

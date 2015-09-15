package demo.service;

import java.util.Collection;
import demo.entity.Activity;

public interface ActivityService {
	
	public Activity create(Activity activity);
	public Activity update(Activity activity);
	public Activity getActivity(Long id);
	public Collection<Activity> getActivityList();
	public void delete(Long id);
}

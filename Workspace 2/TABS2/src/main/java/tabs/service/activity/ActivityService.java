package tabs.service.activity;

import java.util.Collection;
import tabs.entity.Activity;

public interface ActivityService {
	public int getERROR_CODE();
	public Activity create(Activity activity);
	public Activity update(Activity activity);
	public Activity getActivity(Long id);
	public Collection<Activity> getActivityList();
	public void delete(Long id);
	public void setStatus(Long id, boolean status);
	public Collection<Activity> getAvailableActivity();
}

package tabs.service.activity;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tabs.entity.Activity;
import tabs.repository.ActivityRepository;

@Transactional
@Service
public class ActivityServiceBean implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;
	private int ERROR_CODE;
		
	public int getERROR_CODE() {
		return ERROR_CODE;
	}

	public void setERROR_CODE(int eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}

	@Override
	public Activity create(Activity activity) {
		if (activity == null) {
			return null;
		}
		if (activity.getId() != null || activity.getActivity_name() == null
				|| activity.getDescription() == null
				|| activity.getPlace() == null
				|| activity.getStart_time() == null
				|| activity.getEnd_time() == null
				|| activity.getSeat_quota() == 0) {
			setERROR_CODE(1);
			return null;
		}
		if (activity != null) {
			Collection<Activity> activityList = activityRepo.findAll();
			for (Activity persistedActivity : activityList) {
				if (persistedActivity.getActivity_name().equals(activity
						.getActivity_name())
						&& persistedActivity.getStart_time().equals(activity
								.getStart_time())
						&& persistedActivity.getEnd_time().equals(activity
								.getEnd_time())) {
					setERROR_CODE(2);
					return null;
				}
			}
		}				
		Activity savedActivity = activityRepo.save(activity);
		setERROR_CODE(0);
		return savedActivity;
		
	}

	@Override
	public Activity update(Activity activity) {
		if (activity == null) {
			return null;
		}
		if (activity.getId() == null || activity.getActivity_name() == null
				|| activity.getDescription() == null
				|| activity.getPlace() == null
				|| activity.getStart_time() == null
				|| activity.getEnd_time() == null
				|| (Integer) activity.getSeat_quota() == null) {
			return null;
		}
		Activity activityPersisted = activityRepo.findOne(activity.getId());
		if (activityPersisted == null) {
			return null;
		} else {
			Activity savedActivity = activityRepo.save(activity);
			return savedActivity;
		}
	}

	@Override
	public Activity getActivity(Long id) {
		if (id == null) {
			return null;
		}
		Activity activity = activityRepo.findOne(id);
		return activity;
	}

	@Override
	public Collection<Activity> getActivityList() {
		Collection<Activity> activityList = activityRepo.findAll();
		if (activityList.isEmpty()) {
			return null;
		}
		return activityList;
	}

	public void delete(Long id) {
		Activity deleteActivity = activityRepo.getOne(id);
		activityRepo.delete(deleteActivity);
	}
}

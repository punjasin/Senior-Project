package demo.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Activity;
import demo.repository.ActivityRepository;

@Service
public class ActivityServiceBean implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public Activity create(Activity activity) {
		if (activity == null) {
			return null;
		}
		if (activity.getId() != null || activity.getActivity_name() == null
				|| activity.getDescription() == null
				|| activity.getPlace() == null
				|| activity.getStart_date() == null
				|| activity.getEnd_date() == null
				|| (Integer) activity.getSeat_quota() == null) {
			return null;
		} else {
			Collection<Activity> activityList = activityRepo.findAll();
			for (Activity persistedActivity : activityList) {
				if (persistedActivity.getActivity_name() == activity
						.getActivity_name()
						&& persistedActivity.getStart_date() == activity
								.getStart_date()
						&& persistedActivity.getEnd_date() == activity
								.getEnd_date()) {
					return null;
				}
			}
			Activity savedActivity = activityRepo.save(activity);
			return savedActivity;
		}
	}

	@Override
	public Activity update(Activity activity) {
		if (activity == null) {
			return null;
		}
		if (activity.getId() == null || activity.getActivity_name() == null
				|| activity.getDescription() == null
				|| activity.getPlace() == null
				|| activity.getStart_date() == null
				|| activity.getEnd_date() == null
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

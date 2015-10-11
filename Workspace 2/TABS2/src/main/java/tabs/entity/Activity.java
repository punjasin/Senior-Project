package tabs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Transactional
@Entity
public class Activity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String activity_name, description, place;
	private Timestamp start_time, end_time;
	private int seat_quota;
	
	public Activity(){
		
	}
	
	public Activity(String name, String description, String place, Timestamp start_time, Timestamp end_time, int seat_quota){
		this.activity_name = name;
		this.description = description;
		this.place = place;
		
		this.seat_quota = seat_quota;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public int getSeat_quota() {
		return seat_quota;
	}
	public void setSeat_quota(int seat_quota) {
		this.seat_quota = seat_quota;
	}

	@SuppressWarnings("deprecation")
	public String getEnd_time() {
		return end_time.toLocaleString();
	}

	public void setEnd_time(String end_time) {
		this.end_time = Timestamp.valueOf(end_time);
	}

	@SuppressWarnings("deprecation")
	public String getStart_time() {
		return start_time.toLocaleString();
	}

	public void setStart_time(String start_time) {
		this.start_time = Timestamp.valueOf(start_time);
	}
	
}

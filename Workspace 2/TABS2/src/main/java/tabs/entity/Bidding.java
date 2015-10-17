package tabs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Transactional
@Entity
public class Bidding {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long activity_id;	
	private String title, description;	
	private Timestamp bStart_time, bEnd_time;	
	private int seat_quota;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(Long activity_id) {
		this.activity_id = activity_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@SuppressWarnings("deprecation")
	public String getbStart_time() {
		return bStart_time.toLocaleString();
	}
	public void setbStart_time(String bStart_time) {
		this.bStart_time = Timestamp.valueOf(bStart_time);
	}
	@SuppressWarnings("deprecation")
	public String getbEnd_time() {
		return bEnd_time.toLocaleString();
	}
	public void setbEnd_time(String bEnd_time) {
		this.bEnd_time = Timestamp.valueOf(bEnd_time);
	}
	public int getSeat_quota() {
		return seat_quota;
	}
	public void setSeat_qouta(int seat_quota) {
		this.seat_quota = seat_quota;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getUpdateStartTime(){
		return bStart_time;
	}
	
	public Timestamp getUpdateEndTime(){
		return bEnd_time;
	}
		
}

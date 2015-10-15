package tabs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Transactional
@Entity
public class BidData {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long bidding_id;
	private int student_id, token;
	private String firstName, lastName, ip_address, browser, os, session_id;
	private Timestamp placeBidtime;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBidding_id() {
		return bidding_id;
	}
	public void setBidding_id(Long bidding_id) {
		this.bidding_id = bidding_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIpAddress() {
		return ip_address;
	}
	public void setIpAddress(String ipAddress) {
		this.ip_address = ipAddress;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getSid() {
		return session_id;
	}
	public void setSid(String sid) {
		this.session_id = sid;
	}
	@SuppressWarnings("deprecation")
	public String getPlaceBidtime() {
		return placeBidtime.toLocaleString();
	}
	public void setPlaceBidtime(String placeBidtime) {
		this.placeBidtime = Timestamp.valueOf(placeBidtime);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

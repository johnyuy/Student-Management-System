package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Leave_Table")
public class Leave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int leaveId;
	private LocalDateTime dateStart;
	private int duration;
	private String status;
	
	@ManyToOne
	private Staff staff;

	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(int leaveId, LocalDateTime dateStart, int duration, String status, Staff staff) {
		super();
		this.leaveId = leaveId;
		this.dateStart = dateStart;
		this.duration = duration;
		this.status = status;
		this.staff = staff;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", dateStart=" + dateStart + ", duration=" + duration + ", status="
				+ status + ", staff=" + staff + "]";
	}
	
	
 }

package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scheduleId;
	private LocalDateTime timeStart;
	private int durationHours;
	private int staffid;
	@OneToOne
	private String subjectName;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schedule(LocalDateTime timeStart, int durationHours, int staffid, String location, String subjectName) {
		super();
		this.timeStart = timeStart;
		this.durationHours = durationHours;
		this.staffid = staffid;
		this.subjectName = subjectName;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public int getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}


	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Schedule [timeStart=" + timeStart + ", durationHours=" + durationHours + ", staffid=" + staffid
				+", subjectName=" + subjectName + "]";
	}


	
	
}

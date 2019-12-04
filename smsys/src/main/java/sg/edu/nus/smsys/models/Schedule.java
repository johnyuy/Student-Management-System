package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scheduleId;
	private LocalDateTime timeStart;
	private int durationHours;
	private int lecturerId;
	@ManyToOne
	private Subject subject;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(int scheduleId, LocalDateTime timeStart, int durationHours, int lecturerId, Subject subject) {
		super();
		this.scheduleId = scheduleId;
		this.timeStart = timeStart;
		this.durationHours = durationHours;
		this.lecturerId = lecturerId;
		this.subject = subject;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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
	public int getLecturerId() {
		return lecturerId;
	}
	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", timeStart=" + timeStart + ", durationHours=" + durationHours
				+ ", lecturerId=" + lecturerId + ", subject=" + subject + "]";
	}
	
	

	
	
}

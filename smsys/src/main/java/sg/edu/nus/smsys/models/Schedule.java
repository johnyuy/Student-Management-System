package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="schedule_table")
@SequenceGenerator(name="schedule_id_seq", initialValue = 10000000)
public class Schedule {
	
	@Id
	@NotNull
	@Min(10000000)
	@Max(19999999)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "schedule_id_seq")
	private int scheduleId;
	private LocalDateTime timeStart;
	@Min(1)
	private int durationHours;
	@ManyToOne
	private Lecturer lecturer;
	@ManyToOne
	private Subject subject;
	@ManyToOne
	private CourseClass clas;
	
	//CONSTRUCTORS
	public Schedule() {
		super();
	}
	public Schedule(int scheduleId, LocalDateTime timeStart, int durationHours, Lecturer lecturer, Subject subject) {
		super();
		this.scheduleId = scheduleId;
		this.timeStart = timeStart;
		this.durationHours = durationHours;
		this.lecturer = lecturer;
		this.subject = subject;
	}
	
	//GETTERS AND SETTERS
	public int getScheduleId() {
		return scheduleId;
	}
	public LocalDateTime getTimeStart() {
		return timeStart;
	}
	public int getDurationHours() {
		return durationHours;
	}
	public Lecturer getLecturer() {
		return lecturer;
	}
	public Subject getSubject() {
		return subject;
	}
	public CourseClass getClas() {
		return clas;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}
	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public void setClas(CourseClass clas) {
		this.clas = clas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + scheduleId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (scheduleId != other.scheduleId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", timeStart=" + timeStart + ", durationHours=" + durationHours
				+ ", lecturer=" + lecturer + ", subject=" + subject + ", clas=" + clas + "]";
	}	
}

package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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
	
	@Min(1)
	@Max(2)
	private int period;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
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
	
	
	public Schedule(@NotNull @Min(10000000) @Max(19999999) int scheduleId, @Min(1) @Max(2) int period, LocalDate date,
			Lecturer lecturer, Subject subject, CourseClass clas) {
		super();
		this.scheduleId = scheduleId;
		this.period = period;
		this.date = date;
		this.lecturer = lecturer;
		this.subject = subject;
		this.clas = clas;
	}
	//GETTERS AND SETTERS


	public int getScheduleId() {
		return scheduleId;
	}


	public int getPeriod() {
		return period;
	}


	public LocalDate getDate() {
		return date;
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


	public void setPeriod(int period) {
		this.period = period;
	}


	public void setDate(LocalDate date) {
		this.date = date;
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
		return "Schedule [scheduleId=" + scheduleId + ", period=" + period + ", date=" + date + ", lecturer=" + lecturer
				+ ", subject=" + subject + ", clas=" + clas + "]";
	}
	
}

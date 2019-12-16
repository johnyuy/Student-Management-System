package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="schedule_table")
@SequenceGenerator(name="schedule_id_seq", initialValue = 10000000)
public class Schedule {
	
	@Id
	@NotNull
	@Max(19999999)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "schedule_id_seq")
	private int scheduleId;
	
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
	
	
	public Schedule( LocalDate date,
			Lecturer lecturer, Subject subject, CourseClass clas) {
		super();
		this.date = date;
		this.lecturer = lecturer;
		this.subject = subject;
		this.clas = clas;
	}
	//GETTERS AND SETTERS


	public int getScheduleId() {
		return scheduleId;
	}

	public String getDay() {
		return this.date.getDayOfWeek().name();
	}

	public String getDateFormat() {
		
		String dateformat = this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));	
		return dateformat;
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
}

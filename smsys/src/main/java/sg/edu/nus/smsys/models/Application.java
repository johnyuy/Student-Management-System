package sg.edu.nus.smsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Applications_Table")
@SequenceGenerator(name="application_id_seq", initialValue = 1000000)
public class Application {
	@Id
	@NotNull
	@Max(9999999)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "application_id_seq")
	private int applicationId;
	@NotNull
	@ManyToOne
	private Course course;
	@ManyToOne
	private Student student;
	
	private String status;
	//status = pending
	//status = accepted (successful)
	//status = rejected (unsuccessful)
	//status = withdrawn
	//status = expired (system triggered)
	
	
	//CONSTRUCTORS
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Application(Course course, Student student) {
		super();
		this.course = course;
		this.status = "pending";
		this.student = student;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public Course getCourse() {
		return course;
	}
	public Student getStudent() {
		return student;
	}
	public String getStatus() {
		return status;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicationId;
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
		Application other = (Application) obj;
		if (applicationId != other.applicationId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", course=" + course + ", student=" + student
				+ ", status=" + status + "]";
	}
}

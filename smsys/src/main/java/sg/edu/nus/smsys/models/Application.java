package sg.edu.nus.smsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Course_Applications_Table")
@SequenceGenerator(name="application_id_seq", initialValue = 10000)
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "application_id_seq")
	private int applicationId;
	private int courseId;
	private String status;
	
	@ManyToOne
	private Student student;
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Application(int applicationId, int courseId, String status, Student student) {
		super();
		this.applicationId = applicationId;
		this.courseId = courseId;
		this.status = status;
		this.student = student;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}

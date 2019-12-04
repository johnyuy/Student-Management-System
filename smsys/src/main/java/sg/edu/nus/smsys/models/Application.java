package sg.edu.nus.smsys.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course_Applications_Table")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int applicationId;
	private int courseId;
	private String status;
	//haha
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Application(int applicationId, int courseId, String status) {
		super();
		this.applicationId = applicationId;
		this.courseId = courseId;
		this.status = status;
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
	
	
}

package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Student extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studentId;
	private String status;
	private float gpa;
	
	@OneToMany(mappedBy = "student")
	private List<Application> appliedCourses;
	public Student() {
		super();
	}
	public Student(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, int studentId, String status, float gpa, List<Application> appliedCourses) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		this.studentId = studentId;
		this.status = status;
		this.gpa = gpa;
		this.appliedCourses = appliedCourses;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", status=" + status + ", gpa=" + gpa + ", appliedCourses="
				+ appliedCourses + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public List<Application> getAppliedCourses() {
		return appliedCourses;
	}
	public void setAppliedCourses(List<Application> appliedCourses) {
		this.appliedCourses = appliedCourses;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentId != other.studentId)
			return false;
		return true;
	}
}

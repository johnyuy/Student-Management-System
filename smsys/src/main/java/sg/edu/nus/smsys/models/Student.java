package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_table")
@SequenceGenerator(name="student_id_seq", initialValue = 10001)
public class Student extends Person{
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "student_id_seq")
//	@Min(10001)
//	@Max(19999)
	private int studentId;
	private String status;
	@Min(0)
	private float gpa;
	
	@OneToMany(mappedBy = "student")
	private List<Application> appliedCourses;
	
	@OneToMany(mappedBy = "student")
	private List<Grade> gradeList;
	
	private final int accessLevel = 3;
	
	//CONSTRUCTORS
	public Student() {
		super();
	}
	
	public Student(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status, float gpa, List<Application> appliedCourses) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		this.status = status;
		this.gpa = gpa;
		this.appliedCourses = appliedCourses;
	}
	
	//GETTERS & SETTERS
	public int getStudentId() {
		return studentId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public float getGpa() {
		return gpa;
	}
	
	public List<Application> getAppliedCourses() {
		return appliedCourses;
	}
	
	public List<Grade> getGradeList() {
		return gradeList;
	}
	
	public int getAccessLevel() {
		return accessLevel;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	
	public void setAppliedCourses(List<Application> appliedCourses) {
		this.appliedCourses = appliedCourses;
	}
	
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", status=" + status + ", gpa=" + gpa + ", appliedCourses="
				+ appliedCourses + ", gradeList=" + gradeList + ", accessLevel=" + accessLevel + "]";
	}
	
}

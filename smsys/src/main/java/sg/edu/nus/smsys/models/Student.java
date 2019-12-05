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

@Entity
@Table(name = "student_table")
@SequenceGenerator(name="student_id_seq", initialValue = 1000, allocationSize = 8000)
public class Student extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "student_id_seq")
	private int studentId;
	private String status;
	private float gpa;
	
	@OneToMany(mappedBy = "student")
	private List<Application> appliedCourses;
	
	@OneToMany(mappedBy = "student")
	private List<Grade> gradeList;
	
	public Student() {
		super();
	}
	public Student(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status, float gpa, List<Application> appliedCourses) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
//		this.studentId = this.studentId;
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
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", status=" + status + ", gpa=" + gpa + ", appliedCourses="
				+ appliedCourses + ", gradeList=" + gradeList + "]";
	}
}

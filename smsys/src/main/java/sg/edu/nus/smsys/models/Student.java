package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_table")
@SequenceGenerator(name = "student_id_seq", initialValue = 10001)
public class Student extends Person {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	private int studentId;
	private String status;
	@OneToMany(mappedBy = "student")
	private List<Application> appliedCourses;

	@OneToMany(mappedBy = "student")
	private List<Grade> gradeList;

	@ManyToMany(mappedBy = "studentList")
	private List<CourseClass> courseClassList;

	private final int accessLevel = 3;

	// CONSTRUCTORS
	public Student() {
		super();
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

	public List<CourseClass> getCourseClassList() {
		return courseClassList;
	}

	public void setCourseClassList(List<CourseClass> courseClassList) {
		this.courseClassList = courseClassList;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public Student(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status,
			List<Application> appliedCourses, List<Grade> gradeList, List<CourseClass> courseClassList) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		this.status = status;
		this.appliedCourses = appliedCourses;
		this.gradeList = gradeList;
		this.courseClassList = courseClassList;
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", status=" + status + ", accessLevel=" + accessLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentId != other.studentId)
			return false;
		return true;
	}

}

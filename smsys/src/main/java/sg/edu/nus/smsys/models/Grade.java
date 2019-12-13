package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="grades_table")
@SequenceGenerator(name="grade_id_seq", initialValue = 10000100)
public class Grade {
	@Id
	@NotNull
//	@Min(1000010000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "grade_id_seq")
	private long gradeId;
	@ManyToOne
	private Student student;
	@ManyToOne
	private CourseClass clas;
	@ManyToOne
	private Subject subject;

	private String grade;
	
	//CONSTRUCTORS
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grade(Student student, CourseClass clas, Subject subject, String grade) {
		super();
		this.student = student;
		this.clas = clas;
		this.subject = subject;
		this.grade = grade;
	}

	public long getGradeId() {
		return gradeId;
	}

	public Student getStudent() {
		return student;
	}

	public CourseClass getClas() {
		return clas;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getGrade() {
		return grade;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setClas(CourseClass clas) {
		this.clas = clas;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gradeId ^ (gradeId >>> 32));
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
		Grade other = (Grade) obj;
		if (gradeId != other.gradeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", student=" + student + ", clas=" + clas + ", subject=" + subject
				+ ", grade=" + grade + "]";
	}
	
}

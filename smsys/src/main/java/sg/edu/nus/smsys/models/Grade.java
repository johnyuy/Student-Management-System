package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gradeId;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private CourseClass clas;
	
	@ManyToOne
	private Subject subject;

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grade(int gradeId, Student student, CourseClass clas, Subject subject) {
		super();
		this.gradeId = gradeId;
		this.student = student;
		this.clas = clas;
		this.subject = subject;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseClass getClas() {
		return clas;
	}

	public void setClas(CourseClass clas) {
		this.clas = clas;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", student=" + student + ", clas=" + clas + ", subject=" + subject + "]";
	}
	
	
	
}

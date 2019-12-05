package sg.edu.nus.smsys.models;

import java.util.UUID;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="grades_table")

public class Grade {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private UUID gradeId;
	
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

	public Grade(UUID gradeId, Student student, CourseClass clas, Subject subject) {
		super();
		this.gradeId = gradeId;
		this.student = student;
		this.clas = clas;
		this.subject = subject;
	}

	public UUID getGradeId() {
		return gradeId;
	}

	public void setGradeId(UUID gradeId) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
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
		if (gradeId == null) {
			if (other.gradeId != null)
				return false;
		} else if (!gradeId.equals(other.gradeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", student=" + student + ", clas=" + clas + ", subject=" + subject + "]";
	}
}

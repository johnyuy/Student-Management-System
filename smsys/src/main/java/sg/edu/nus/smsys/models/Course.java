package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course_table")
@SequenceGenerator(name="course_id_seq", initialValue = 2000)
public class Course {
	
	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "course_id_seq")
	private int courseId;
	private String courseName;
	private String courseDesc;
	private int classSize;
	private int durationSemesters;
	@ManyToMany
	private List<Subject> courseSubjectList;
	
	public Course() {
		super();
	}
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public int getDurationSemesters() {
		return durationSemesters;
	}

	public void setDurationSemesters(int durationSemesters) {
		this.durationSemesters = durationSemesters;
	}

	public List<Subject> getCourseSubjectList() {
		return courseSubjectList;
	}

	public void setCourseSubjectList(List<Subject> courseSubjectList) {
		this.courseSubjectList = courseSubjectList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId != other.courseId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDesc=" + courseDesc
				+ ", classSize=" + classSize + ", durationSemesters=" + durationSemesters + ", courseSubjectList="
				+ courseSubjectList + "]";
	}

}

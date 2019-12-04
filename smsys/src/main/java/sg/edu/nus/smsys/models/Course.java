package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int courseId;
	private String courseName;
	private String courseDesc;
	private int classSize;
	private int durationSemesters;
	
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
	
	@OneToMany(mappedBy = "course")
	private List<Subject> courseSubjectList;


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

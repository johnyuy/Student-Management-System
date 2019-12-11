package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course_table")
@SequenceGenerator(name="course_id_seq", initialValue = 2000, allocationSize = 10)
public class Course {
	
	@Id
	@NotNull
	@Min(2000)
	@Max(9999)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "course_id_seq")
	private int courseId;
	@NotNull
	private String courseName;
	@NotNull
	private String courseDesc;
	@NotNull
	@Min(10)
	private int classSize;
	@Min(1)
	private int durationSemesters;
	private boolean status = false;
	//Course status can only be Open (True) or Closed (False)
	@ManyToMany
	private List<Subject> courseSubjectList;
	@OneToMany(mappedBy="course")
	private List<CourseClass> classesList;
	
	//CONSTRUCTORS
	public Course() {
		super();
	}
	
	public Course(String courseName, String courseDesc, int classSize, int durationSemesters,
			List<Subject> courseSubjectList, List<CourseClass> classesList, boolean status) {
		super();
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.classSize = classSize;
		this.durationSemesters = durationSemesters;
		this.courseSubjectList = courseSubjectList;
		this.classesList = classesList;
		this.status = status;
	}
	
	//GETTERS & SETTERS
	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public int getClassSize() {
		return classSize;
	}

	public int getDurationSemesters() {
		return durationSemesters;
	}

	public List<Subject> getCourseSubjectList() {
		return courseSubjectList;
	}

	public List<CourseClass> getClassesList() {
		return classesList;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public void setDurationSemesters(int durationSemesters) {
		this.durationSemesters = durationSemesters;
	}

	public void setCourseSubjectList(List<Subject> courseSubjectList) {
		this.courseSubjectList = courseSubjectList;
	}

	public void setClassesList(List<CourseClass> classesList) {
		this.classesList = classesList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
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
		Course other = (Course) obj;
		if (courseId != other.courseId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDesc=" + courseDesc
				+ ", classSize=" + classSize + ", durationSemesters=" + durationSemesters + ", courseSubjectList="
				+ courseSubjectList + ", classesList=" + classesList + "]";
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}

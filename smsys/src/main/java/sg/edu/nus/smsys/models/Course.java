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
	@Max(9999)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "course_id_seq")
	private int courseId;
	@NotNull
	private String courseName;
	@NotNull
	private String courseDesc;
	@NotNull
	private String courseStatus;
	//Status can either be Open or Closed
	@Min(10)
	private int classSize;
	@Min(1)
	private int durationSemesters;
	@ManyToMany
	private List<Subject> courseSubjectList;
	@OneToMany(mappedBy="course")
	private List<CourseClass> classesList;
	
	//CONSTRUCTORS
	public Course() {
		super();
	}

	public Course(@NotNull String courseName, @NotNull String courseDesc,
			@NotNull String courseStatus, @Min(10) int classSize, @Min(1) int durationSemesters,
			List<Subject> courseSubjectList, List<CourseClass> classesList) {
		super();
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.courseStatus = courseStatus;
		this.classSize = classSize;
		this.durationSemesters = durationSemesters;
		this.courseSubjectList = courseSubjectList;
		this.classesList = classesList;
	}
	//GETTERS & SETTERS

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

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
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

	public List<CourseClass> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<CourseClass> classesList) {
		this.classesList = classesList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
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
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDesc=" + courseDesc
				+ ", courseStatus=" + courseStatus + ", classSize=" + classSize + ", durationSemesters="
				+ durationSemesters + ", courseSubjectList=" + courseSubjectList + ", classesList=" + classesList + "]";
	}

}

package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class CourseClass {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int classId;
	private String course;
	private String level;
	
	@ManyToMany
	private List<Semester> semesterList;
	
	@ManyToMany
	private List<Student> student;
	
	@ManyToMany
	private List<Lecturer> lecturerList;
	
	@OneToMany(mappedBy="clas")
	private List<Grade> gradeList;
	
//	@OneToOne(mappedBy ="class")
//	private List<Schedule> classScheduleList;
	
	public CourseClass() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}


	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}


	public List<Grade> getGradeList() {
		return gradeList;
	}


	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}


	public CourseClass(int classId, String course, String level, List<Semester> semesterList, List<Student> student,
		List<Lecturer> lecturerList, List<Grade> gradeList) {
	super();
	this.classId = classId;
	this.course = course;
	this.level = level;
	this.semesterList = semesterList;
	this.student = student;
	this.lecturerList = lecturerList;
	this.gradeList = gradeList;
}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Semester> getSemesterList() {
		return semesterList;
	}

	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Lecturer> getFacultyList() {
		return lecturerList;
	}

	public void setFacultyList(List<Lecturer> facultyList) {
		this.lecturerList = facultyList;
	}

//	public List<Schedule> getClassScheduleList() {
//		return classScheduleList;
//	}
//
//	public void setClassScheduleList(List<Schedule> classScheduleList) {
//		this.classScheduleList = classScheduleList;
//	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", course=" + course + ", level=" + level + ", semesterList="
				+ semesterList + ", student=" + student + ", lecturerList=" + lecturerList + ", gradeList=" + gradeList
				+ "]";
	}
	

}


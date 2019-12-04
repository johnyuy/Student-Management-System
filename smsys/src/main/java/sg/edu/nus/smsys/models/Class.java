package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Class {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int classId;
	private String course;
	private String level;
	
	@ManyToMany(mappedBy ="class")
	private List<Semesters> semesterList;
	
	@ManyToMany(mappedBy ="class")
	private List<Student> student;
	
	@OneToMany(mappedBy ="class")
	private List<Lecturer> facultyList;
	
	@OneToOne(mappedBy ="class")
	private List<Schedule> classScheduleList;
	
	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Class(int classId, String course, String level, List<Semesters> semesterList, List<Student> student,
			List<Lecturer> facultyList, List<Schedule> classScheduleList) {
		super();
		this.classId = classId;
		this.course = course;
		this.level = level;
		this.semesterList = semesterList;
		this.student = student;
		this.facultyList = facultyList;
		this.classScheduleList = classScheduleList;
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

	public List<Semesters> getSemesterList() {
		return semesterList;
	}

	public void setSemesterList(List<Semesters> semesterList) {
		this.semesterList = semesterList;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Lecturer> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(List<Lecturer> facultyList) {
		this.facultyList = facultyList;
	}

	public List<Schedule> getClassScheduleList() {
		return classScheduleList;
	}

	public void setClassScheduleList(List<Schedule> classScheduleList) {
		this.classScheduleList = classScheduleList;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", course=" + course + ", level=" + level + "]";
	}
	

}


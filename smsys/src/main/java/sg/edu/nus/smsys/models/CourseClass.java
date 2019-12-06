package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CourseClass {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int classId;
	private String level;
	@ManyToOne
	private Course course;
	
	@ManyToMany
	private List<Semester> semesterList;
	
	@ManyToMany
	private List<Student> student;
	
	@ManyToMany
	private List<Lecturer> lecturerList;
	
	@OneToMany(mappedBy="clas")
	private List<Grade> gradeList;
	
	@OneToMany(mappedBy ="clas")
	private List<Schedule> scheduleList;
	
	//CONSTRUCTORS
	public CourseClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CourseClass(int classId, Course course, String level, List<Semester> semesterList, List<Student> student,
			List<Lecturer> lecturerList, List<Grade> gradeList, List<Schedule> scheduleList) {
		super();
		this.classId = classId;
		this.course = course;
		this.level = level;
		this.semesterList = semesterList;
		this.student = student;
		this.lecturerList = lecturerList;
		this.gradeList = gradeList;
		this.scheduleList = scheduleList;
	}

	public int getClassId() {
		return classId;
	}

	public Course getCourse() {
		return course;
	}

	public String getLevel() {
		return level;
	}

	public List<Semester> getSemesterList() {
		return semesterList;
	}

	public List<Student> getStudent() {
		return student;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	@Override
	public String toString() {
		return "CourseClass [classId=" + classId + ", level=" + level + ", course=" + course + ", semesterList="
				+ semesterList + ", student=" + student + ", lecturerList=" + lecturerList + ", gradeList=" + gradeList
				+ ", scheduleList=" + scheduleList + "]";
	}

}


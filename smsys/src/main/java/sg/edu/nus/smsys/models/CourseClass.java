package sg.edu.nus.smsys.models;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name="class_id_seq", initialValue = 1000)
public class CourseClass {
	
	@Id
	@NotNull
	@Max(9999)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "class_id_seq")
	private int classId;
	@NotNull
	@Min(0)
	private int level;
	@ManyToOne
	private Course course;
	@ManyToMany
	private List<Semester> semesterList;
	@ManyToMany
	private List<Student> studentList;
	
	@ManyToMany
	private List<Lecturer> lecturerList;
	
	@OneToMany(mappedBy="clas")
	private List<Grade> gradeList;
	
	@OneToMany(mappedBy ="clas")
	private List<Schedule> scheduleList;
	
	private HashMap< Semester, List<Schedule>> timetable;

	
	public HashMap<Semester, List<Schedule>> getTimetable() {
		return timetable;
	}

	public void setTimetable(HashMap<Semester, List<Schedule>> timetable) {
		this.timetable = timetable;
	}
	
	//CONSTRUCTORS
	public CourseClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CourseClass(Course course, int level, List<Semester> semesterList, List<Student> studentList,
			List<Lecturer> lecturerList, List<Grade> gradeList, List<Schedule> scheduleList) {
		super();
		this.course = course;
		this.level = level;
		this.semesterList = semesterList;
		this.studentList = studentList;
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

	public int getLevel() {
		return level;
	}

	public List<Semester> getSemesterList() {
		return semesterList;
	}

	public List<Student> getStudentList() {
		return studentList;
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

	public void setLevel(int level) {
		this.level = level;
	}

	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classId;
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
		CourseClass other = (CourseClass) obj;
		if (classId != other.classId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseClass [classId=" + classId + ", level=" + level + ", course=" + course + ", semesterList="
				+ semesterList + ", studentList=" + studentList + ", lecturerList=" + lecturerList + ", gradeList=" + gradeList
				+ ", scheduleList=" + scheduleList + "]";
	}

}


package sg.edu.nus.smsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Student;

public interface CourseClassService {
	@Autowired
	public boolean canViewClass(int id);
	@Autowired
	public List<CourseClass> getClassesByUser();
	@Autowired
	public CourseClass findByClassId(int id);
	@Autowired
	public void addStudentToClass(Student s, int classId);
	@Autowired
	public void addLecturerToClass(Lecturer l, int classId);
	@Autowired
	public void removeStudentFromClass(Student s, int classId);
	@Autowired
	public void removeLecturerFromClass(Lecturer l, int classId);
	@Autowired
	public List<Lecturer> getAvailableLecturers(CourseClass cc);
}

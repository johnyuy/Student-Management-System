package sg.edu.nus.smsys.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Semester;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.SemesterRepository;
import sg.edu.nus.smsys.repository.StudentRepository;

@Service
public class ApplicationServiceImplement implements ApplicationService {

	@Autowired
	private CourseRepository crepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private SemesterRepository semrepo;

	public String displayNextSemCode(LocalDate today) {
		today = LocalDate.now();
		List<Semester> semList = new ArrayList<Semester>();
		semList = semrepo.findAll();
		int nextSemId = 0;
		for (Semester sem : semList) {
			if (today.isBefore(LocalDate.parse(sem.getEndDate()))
					&& today.isAfter(LocalDate.parse(sem.getStartDate()))) {
				nextSemId = sem.getSemId() + 1;
			}
		}
		Semester nextSem = semrepo.findBySemId(nextSemId);
		String Semcode = nextSem.getSemCode();
		return Semcode;
	}

	public boolean checkEligibility(Student student) {
		// check if student can apply based on enrolled course
		List<CourseClass> courseList = student.getCourseClassList();

		if (courseList == null || courseList.size()==0) {
			return true;
		} else {
			System.out.println(courseList.size());
			CourseClass lastCourse = courseList.get(courseList.size() - 1);
			//lastcourseclass
			if (lastCourse.getLevel() < lastCourse.getCourse().getDurationSemesters()) {
				return false;
			} else
				return true;
		}

	}

	public List<Course> displayAvailableCourses() {
		List<Course> courseList = new ArrayList<Course>();
		courseList = crepo.findAll();
		List<Course> openedCoursesList = new ArrayList<Course>();
		for (Course course : courseList) {
			if (course.getStatus() == true) {
				openedCoursesList.add(course);
			}
		}
		return openedCoursesList;
	}

	public List<Course> displayEligibleCourses(Student student) {
		//get list of courses taken
		List<CourseClass> stuCourseList = student.getCourseClassList();
		//Get all courses which are currently "open"
		List<Course> openedCoursesList = displayAvailableCourses();
		
		//find courses "open" AND not taken by student
		List<Course> eligibleCourses = new ArrayList<Course>();
		
		if (openedCoursesList.size() > 0) {
			for (Course openedCourse : openedCoursesList) {
				for (CourseClass courseClass : stuCourseList) {
					//compare the two list and add only courses not found in student's list
					int openedCourseId = openedCourse.getCourseId();
					int completedCourseId = courseClass.getCourse().getCourseId();
					if (openedCourseId != completedCourseId) {
						eligibleCourses.add(openedCourse);
					}
				}
			}
		}

		return eligibleCourses;
	}

//	public List<Application> submitApplication(Course course){
//		
//	}

}

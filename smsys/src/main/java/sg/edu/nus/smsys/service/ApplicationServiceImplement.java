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
import sg.edu.nus.smsys.repository.ApplicationRepository;
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
	@Autowired
	private ApplicationRepository arepo;

	public String displayNextSemCode(LocalDate today) {
		today = LocalDate.now();
		List<Semester> semList = new ArrayList<Semester>();
		semList = semrepo.findAll();
		int nextSemId = 0;
		for (Semester sem : semList) {
			if (today.isBefore(sem.getEndDate())
					&& today.isAfter(sem.getStartDate())) {
				nextSemId = sem.getSemId() + 1;
			}
		}
		Semester nextSem = semrepo.findBySemId(nextSemId);
		String Semcode = nextSem.getSemCode();
		return Semcode;
	}

	public boolean checkEligibility(Student student) {
		boolean output = false;
		// check if student can apply based on enrolled course
		List<CourseClass> classList = student.getCourseClassList();
		System.out.println("My Course List: " + classList);
		if (classList != null) {
			if (classList.size() == 0) {
				System.out.println("YOU ARE ELIGIBLE!");
				return true;
			} else {
				//check the level of current class
				CourseClass lastCourse = classList.get(classList.size() - 1);
				if (lastCourse.getLevel() < lastCourse.getCourse().getDurationSemesters()) {
					System.out.println("YOU ARE NOT ELIGIBLE!!");
					return false;
				} else
					return true;
			}
		}
		return output;
	}

	public List<Course> displayAvailableCourses() {
		List<Course> courseList = new ArrayList<Course>();
		courseList = crepo.findAll();
		List<Course> openedCoursesList = new ArrayList<Course>();
		for (Course course : courseList) {
			if (course.getCourseStatus().equals("Open")) {
				openedCoursesList.add(course);
			}
		}
		return openedCoursesList;
	}

	public List<Course> displayEligibleCourses(Student student) {
		// get list of courses taken
		List<CourseClass> stuCourseList = student.getCourseClassList();
		System.out.println("Student Course List: " + stuCourseList.size());
		// Get all courses which are currently "open"
		List<Course> openedCoursesList = displayAvailableCourses();
		System.out.println("Opened Course List: " + openedCoursesList.size());
		// find courses "open" AND not taken by student
		List<Course> eligibleCourses = new ArrayList<Course>();

		if (openedCoursesList.size() > 0) {
			// if studentlist of courses zie>0
			if (stuCourseList.size() > 0) {
				for (Course openedCourse : openedCoursesList) {

					for (CourseClass courseClass : stuCourseList) {
						// compare the two list and add only courses not found in student's list
						int openedCourseId = openedCourse.getCourseId();
						int completedCourseId = courseClass.getCourse().getCourseId();
						System.out.println("Comparing the Ids: " + openedCourseId + " and " + completedCourseId);
						if (openedCourseId != completedCourseId) {
							eligibleCourses.add(openedCourse);
						}
					}
				}
			} else {
				// if student have never taken ANY courses before
				eligibleCourses = openedCoursesList;
			}

		}

		return eligibleCourses;
	}

	public void saveApplication(Course course, Student student) {
		Application application = new Application(course, student);
		arepo.save(application);
	}

	public List<Application> displayAllCourseApplication() {
		List<Application> appList = new ArrayList<>();
		appList = arepo.findAll();
		return appList;
	}

	public List<Application> displayMyApplication(Student student) {
		System.out.println("Entered displayMyApplication.");
		List<Application> appList = new ArrayList<>();
		List<Application> myApp = new ArrayList<>();
		appList = arepo.findAll();
		System.out.println("appList size is " + appList.size());
		for (Application app : appList) {
			System.out.println("Entered for loop");
			if (app.getStudent().equals(student)) {
				myApp.add(app);
			}
		}
		System.out.println("myApp size is " + myApp.size());
		return myApp;
	}
	
	public List<Application> getAcceptedApplications(Course course){
		List<Application> applist = arepo.findByStatus("accepted");
		List<Application> output = new ArrayList<Application>();
		for(Application app: applist) {
			if(app.getCourse().equals(course)) {
				output.add(app);
			}
		}
		return applist;
	}
}

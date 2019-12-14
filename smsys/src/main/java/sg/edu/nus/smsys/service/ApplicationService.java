package sg.edu.nus.smsys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Student;

public interface ApplicationService {

	@Autowired
	public String displayNextSemCode(LocalDate today);
	@Autowired
	public boolean checkEligibility(Student student);
	@Autowired
	public List<Course> displayEligibleCourses(Student student);
	@Autowired
	public List<Course> displayAvailableCourses();
	@Autowired
	public List<Application> displayAllCourseApplication();
	@Autowired
	public void saveApplication(Course course, Student student);
	@Autowired
	public List<Application> displayMyApplication(Student student);
	@Autowired
	public List<Application> getAcceptedApplications();
}
	

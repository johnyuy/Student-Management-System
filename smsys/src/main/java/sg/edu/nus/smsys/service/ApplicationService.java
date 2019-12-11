package sg.edu.nus.smsys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
}
	

package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.SemesterRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.service.ApplicationService;

@Controller
@RequestMapping("/student")
public class ApplicationController {
	@Autowired
	private ApplicationService as;
	@Autowired
	private SemesterRepository semrepo;
	@Autowired
	private StudentRepository sturepo;
	
	
	@GetMapping("/applycourse")
	public String applyCourse(Model model, Student student) {

		List<Course> eligibleCourse = new ArrayList<Course>();
		model.addAttribute("courselist", eligibleCourse);
		
		 
		
		student = sturepo.findByStudentId(10001);
		
		LocalDate today = LocalDate.now();
		String semCode = as.displayNextSemCode(today);
		model.addAttribute("semester", semCode);
		
		boolean eligible = as.checkEligibility(student);
		System.out.println("eligible student? " + eligible);
		model.addAttribute("eligibility", eligible);
		
		if(eligible) {
			eligibleCourse = as.displayEligibleCourses(student);
			List<Course> availableCourses = as.displayAvailableCourses();
			System.out.println("Available Courses: " + availableCourses.size());
			model.addAttribute("courselist", eligibleCourse);
			System.out.println("There are eligible courses!!");
			System.out.println("Eligible Courses: " + eligibleCourse.size());
			for(Course c : eligibleCourse) {
				System.out.println(c.getCourseName());
				System.out.println("My courses!!");
			}
			
				
		}

		
		return "applycourse";
		
	}
	
	
	
	
}



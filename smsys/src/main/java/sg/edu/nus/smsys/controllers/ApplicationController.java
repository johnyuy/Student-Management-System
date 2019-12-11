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
import sg.edu.nus.smsys.models.Semester;
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
//		LocalDate today = LocalDate.now();
//		String semCode = as.displayNextSemCode(today);
//		Semester semester = semrepo.findBySemCode(semCode);
//		model.addAttribute("semester", semester);
		List<Course> eligibleCourse = new ArrayList<Course>();
		model.addAttribute("courselist", eligibleCourse);
		
		 
		
		student = sturepo.findByStudentId(10001);
		
		LocalDate today = LocalDate.now();
		String semCode = as.displayNextSemCode(today);
		model.addAttribute("semester", semCode);
		
//		List<Course> allCourses = as.displayAvailableCourses();
//		model.addAttribute("courses", allCourses);
		
//		List<Course> eligibleCourse = as.displayEligibleCourses(student);
//		model.addAttribute("courselist", eligibleCourse);
		
		boolean eligible = as.checkEligibility(student);
		System.out.println("eligible student? " + eligible);
		
		
		
		if(eligible) {
			eligibleCourse = as.displayEligibleCourses(student);
			model.addAttribute("courselist", eligibleCourse);
		}
//		else {
//			String notEligible = "Sorry, you are not eligible to apply for any courses at the moment.";
//			model.addAttribute("Courselist", notEligible);
//		}
		
		return "applycourse";
		
	}
	
	
	
	
}



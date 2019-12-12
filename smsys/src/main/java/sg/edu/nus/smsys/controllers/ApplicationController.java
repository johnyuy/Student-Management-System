package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Semester;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.ApplicationRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
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
	@Autowired
	private CourseRepository crepo;
	@Autowired
	private ApplicationRepository apprepo;
	
	
	@GetMapping("/applycourse")
	public String applyCourse(Model model, Student student) {

		List<Course> eligibleCourse = new ArrayList<Course>();
		model.addAttribute("courselist", eligibleCourse);
		
		 
		
		student = sturepo.findByStudentId(10001);
		model.addAttribute("studentid", student.getStudentId());
		
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
		}
		return "applycourse";
	}
	
	@PostMapping("/applycourse")
	public String applyCourse(@Valid @ModelAttribute Application app, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream()
					.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
			return "applycourse";
		}
		//System.out.println(l.getDateStart().toString());

		apprepo.save(app);
		String studentId = String.valueOf(app.getStudent().getStudentId());
		// return "redirect:/leave/leavelist?id="+staffId;
		return "redirect:/student/leavelist?id=" + studentId;
	}
	
	@GetMapping("/home")
	public String displayHome(Model model, Student student) {
		student = sturepo.findByStudentId(10001);
		model.addAttribute("studentid", student.getStudentId());
		
		return "studenthome";
	}
	
	@GetMapping("/home/appliedcourse")
	public String displayAppliedCourse(Model model, Student student) {
		student = sturepo.findByStudentId(10001);
		model.addAttribute("studentid", student.getStudentId());
		
		List<Application> myApp = as.displayStudentApplication(student);
		model.addAttribute("application", myApp);
		
		return "appliedcourse";
	}
//	
//	
//
//	@GetMapping("student/delete/{id}")
//	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
//		Leave leave = lrepo.findById(id).get();
//		lrepo.delete(leave);
//		String staffId = String.valueOf(leave.getSubmittedByStaffID().getStaffId());
//		return "redirect:/leave/leavelist?id=" + staffId;
//	}
//
//	@GetMapping("/view/{id}")
//	public String viewLeaveDetails(Model model, @ModelAttribute Leave leave, @PathVariable("id") Integer id) {
//		leave = lrepo.findById(id).get();
//
//		model.addAttribute("leave", leave);
//
//		return "leavedetails";
//	}

	
}



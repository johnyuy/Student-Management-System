package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.ApplicationRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.SemesterRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.ApplicationService;
import sg.edu.nus.smsys.service.UserService;

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
	@Autowired
	private UserService us;
	@Autowired
	private SmsUserDetailsService suds;

	@GetMapping("/applycourse")
	public String applyCourse(Model model) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		List<Course> eligibleCourse = new ArrayList<Course>();
		model.addAttribute("courselist", eligibleCourse);

		if (suds.getAuthUserAccessLevel() == 3) {
			User user = us.getUserByUsername(suds.getAuthUsername());
			Student student = us.getStudentByUser(user);

			model.addAttribute("student", student);

			LocalDate today = LocalDate.now();
			String semCode = as.displayNextSemCode(today);
			model.addAttribute("semester", semCode);

			boolean eligible = as.checkEligibility(student);
			model.addAttribute("eligibility", eligible);

			if (eligible) {
				eligibleCourse = as.displayEligibleCourses(student);
				List<Course> availableCourses = as.displayAvailableCourses();
				model.addAttribute("courselist", eligibleCourse);
			}
			return "applycourse";
		}
		return "NotFound";
	}

	@PostMapping("/submitapplication")
	public String applyCourse(@RequestParam int selectedcourse) {
		User user = us.getUserByUsername(suds.getAuthUsername());
		Student student = us.getStudentByUser(user);
		Course course = crepo.findByCourseId(selectedcourse);
		as.saveApplication(course, student);

		return "redirect:/student/appliedcourse";
	}

	@GetMapping("/home")
	public String displayHome(Model model, Student student) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		User user = us.getUserByUsername(suds.getAuthUsername());
		student = us.getStudentByUser(user);
		model.addAttribute("studentid", student.getStudentId());

		return "studenthome";
	}

	@GetMapping("/appliedcourse")
	public String displayAppliedCourse(Model model, Student student) {
		// for Student to see his applications
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		User user = us.getUserByUsername(suds.getAuthUsername());
		student = us.getStudentByUser(user);
		model.addAttribute("studentid", student.getStudentId());
		List<Application> applications = new ArrayList<Application>();
		applications.addAll(apprepo.findByStudent(student));
		model.addAttribute("myapplicationlist", applications);
		return "appliedcourse";
	}

	@GetMapping("/home/applications")
	// for Admin to see all the applications
	public String displayCourseApplications(Model model, @RequestParam(defaultValue = "") String id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());

		List<Application> applicationList = new ArrayList<Application>();

		List<Application> pending = new ArrayList<Application>();

		List<Application> accepted = new ArrayList<Application>();

		List<Application> others = new ArrayList<Application>();

		applicationList = as.displayAllCourseApplication();

		for (Application a : applicationList) {
			if (a.getStatus().equals("pending")) {
				pending.add(a);
			} else if (a.getStatus().equals("accepted")) {
				accepted.add(a);
			} else {
				others.add(a);
			}
		}
		model.addAttribute("pending", pending);
		model.addAttribute("others", others);
		model.addAttribute("accepted", accepted);
		model.addAttribute("applicationlist", applicationList);
		return "appliedcourse";
	}

	@PostMapping("/app/{id}")
	public String replyApplication(@ModelAttribute Application a, @PathVariable("id") int id) {
		
		Student student = a.getStudent();
		List<Application> alist = new ArrayList<Application>();
		alist.addAll(apprepo.findByStudent(student));
		for (Application app : alist) {
			if (app.getApplicationId() == id) {
				alist.remove(app);
				break;
			}
		}
		for (Application app : alist) {
			if (app.getStatus().equals("pending")) {
				app.setStatus("expired");
			}
		}
		apprepo.saveAll(alist);
		a.setApplicationId(id);
		apprepo.save(a);
		return "redirect:/student/home/applications";
	}

	@GetMapping("/app/{id}")
	public String viewApplicationDetails(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());

		Application app = as.getApplicationById(id);
		model.addAttribute("app", app);

		return "applicationdetails";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Application app = as.getApplicationById(id);
		as.deleteApplication(app);
		
		return "redirect:/student/appliedcourse";
	}
	

}

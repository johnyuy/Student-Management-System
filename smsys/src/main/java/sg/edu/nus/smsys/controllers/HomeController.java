package sg.edu.nus.smsys.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.models.UserSession;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private UserRepository urepo;
	@Autowired
	private LecturerRepository lrepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private CourseAdminRepository crepo;
	
	@Autowired 
	private UserService us;
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogoutPage(SessionStatus status) {
		status.setComplete();
		return "login";
	}
	
	
	@PostMapping("/authenticate")
	public String getAuthentication(@Valid @ModelAttribute User user, BindingResult bindingresult) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if(bindingresult.hasErrors()) {
			return "login";
		}
		if (us.verifyUserAndPassword(user.getUsername(), user.getPassword()) == true){
			int accesslevel = urepo.findByUsername(user.getUsername()).getAccessRights();
			 
			if ( accesslevel == 1) {
				return "redirect:/home/admin";
			} 
			if (accesslevel == 2) {
					return "redirect:/home/lecturer";
				} 
			if (accesslevel == 3) {
					return "redirect:/home/student";
				}
		}
		
		return "redirect:/home/login";
		
	}
	
	@GetMapping("/lecturer")
	public String getLecturerPage(Model model) {
		//model.addAttribute("user", new UserSession());
		return "lecturerhome";
	}
	
	@GetMapping("/admin")
	public String getAdminPage(Model model) {
		//model.addAttribute("user", new UserSession());
		return "courseadminhome";
	}
	
	@GetMapping("/student")
	public String getStudentPage(Model model) {
		//model.addAttribute("user", new UserSession());
		return "studenthome";
	}
	
	
}
package sg.edu.nus.smsys.controllers;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import sg.edu.nus.smsys.SmsysApplication;
import sg.edu.nus.smsys.UserSession;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

@Controller
//@RequestMapping("/home")
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
		System.out.println(status);
		status.setComplete();
		return "redirect:/home/login";
	}
	
	
	@PostMapping("/authenticate")
	public String getAuthentication(@Valid @ModelAttribute User user, BindingResult bindingresult, HttpSession httpSession) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if(bindingresult.hasErrors()) {
			return "login";
		}
		if (us.verifyUserAndPassword(user.getUsername(), user.getPassword()) == true){
			//LOGIN AUTHENTHICATION IS SUCCESSFUL
			Optional<User> u = urepo.findByUsername(user.getUsername());
			int accesslevel = u.get().getAccessRights();
			
			UUID sessionId = UUID.randomUUID();
			UserSession session = new UserSession(urepo.findByUsername(user.getUsername()).get(), sessionId);
			httpSession.setAttribute("session", session);
			System.out.println(session.getSessionId());
			
			//System.out.println("NEW SESSION IN TOWN!   " + UserSession.sessions.get(0).getSessionId());
			if ( accesslevel == 1) {
				return "redirect:/admin";
			} 
			if (accesslevel == 2) {
					return "redirect:/lecturer";
				} 
			if (accesslevel == 3) {
					return "redirect:/student";
				}
		}
		
		return "redirect:/home/login";
		
	}

}
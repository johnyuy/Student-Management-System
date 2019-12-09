package sg.edu.nus.smsys.controllers;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;


@Controller
@RequestMapping("/home/courseadmin/useraccount")
public class UserAccountController {
	
	@Autowired
	private UserRepository urepo;
	@Autowired
	private LecturerRepository lrepo;
	@Autowired 
	private CourseAdminRepository crepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired 
	private UserService us;
	
	@GetMapping("/add")
	public String showCreateAccount(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "createuseraccountform";
	}	
	
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute User user ) throws GeneralSecurityException
	{
		us.registerNewAccount(Integer.parseInt(user.getUsername()), user.getPassword());
		return "redirect:/home/login";
	}
	
	
	@GetMapping("/list")
	public String listAll(Model user)
	{
		List<User> userAccountList = new ArrayList<User>();
		userAccountList = urepo.findAll();
		user.addAttribute("user", userAccountList);
		return "createuseraccountform";
		
	}
	
//	@GetMapping("/delete")
//	public String showDeleteAccount(Model model) {
//	    User user = new User();
//	    model.addAttribute("user", user);
//	    return "deleteuseraccount";
//	}	
//	
//	@GetMapping("/delete")
//	public String showDeleteAccount(Model model) {
//	    User user = new User();
//	    model.addAttribute("user", user);
//	    return "deleteuseraccount";
//	}	
	
	
	
	
	
}

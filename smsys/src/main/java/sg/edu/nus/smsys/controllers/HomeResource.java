package sg.edu.nus.smsys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Controller
@RequestMapping("")
public class HomeResource {
	@Autowired
	SmsUserDetailsService suds;
	@Autowired
	CourseRepository courserepo;
	
	@GetMapping("/welcome")
	public String landing(Model model) {
		List<Course> courselist = courserepo.findAll();
		model.addAttribute("courselist",courselist);
		return "welcome.html";
	}
	
	@GetMapping("")
	public String home(){
		int accessLevel = suds.getAuthUserAccessLevel();
		if(accessLevel==1)
			return "adminhome";
		if(accessLevel==2)
			return "lecturerhome";
		if(accessLevel==3)
			return "studenthome";
		
		return "redirect:/welcome";
	}
}

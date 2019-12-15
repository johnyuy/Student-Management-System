package sg.edu.nus.smsys.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("")
@SessionAttributes("name")
public class HomeController {
	@Autowired
	SmsUserDetailsService suds;
	@Autowired
	CourseRepository courserepo;
	@Autowired
	UserService us;
	
	@GetMapping("/welcome")
	public String landing(Model model) {
		List<Course> courselist = courserepo.findAll();
		model.addAttribute("courselist",courselist);
		return "welcome.html";
	}
	
	@GetMapping("")
	public String home(HttpSession session){
		String name = "";
		int accessLevel = suds.getAuthUserAccessLevel();
		if(accessLevel==1) {
			name = us.getCourseAdminByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			System.out.println(name);
			session.setAttribute("name", name);
			return "adminhome.html";
		}
		if(accessLevel==2) {
			name = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			session.setAttribute("name", name);
			return "lecturerhome";
		}
		if(accessLevel==3) {
			name = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			session.setAttribute("name", name);
			return "studenthome";
		}
		return "redirect:/welcome";
	}
}

package sg.edu.nus.smsys.controllers;

import java.nio.file.attribute.UserPrincipal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SmsUserDetailsService suds;
	
	@Autowired
	private UserService us;
	
	@GetMapping("")
	public String admin(Model model) {
		return "adminhome";
	}

}
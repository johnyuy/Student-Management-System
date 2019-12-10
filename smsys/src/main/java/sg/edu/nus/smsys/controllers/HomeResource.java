package sg.edu.nus.smsys.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@GetMapping("/")
	public String home() {
		return("<h1>Main Home Page</h1>");
	}
	
	@GetMapping("/student")
	public String getStudentHome(Model m) {
		return("studenthome");
	}
	
	@GetMapping("/admin")
	public String getAdminHome(Model m) {
		return("courseadminhome");
	}
	
	@GetMapping("/lecturer")
	public String getLecturerHome(Model m) {
		return("lecturerhome");
	}
}

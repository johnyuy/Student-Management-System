package sg.edu.nus.smsys.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@GetMapping("/")
	public String home() {
		return("<h1>Welcome</h1>");
	}
	
	@GetMapping("/student2")
	public String student() {
		return("<h1>Welcome student</h1>");
	}
	
	@GetMapping("/admin2")
	public String admin() {
		return("<h1>Welcome admin</h1>");
	}
	
	@GetMapping("/lecturer2")
	public String lecturer() {
		return("<h1>Welcome lecturer</h1>");
	}
}

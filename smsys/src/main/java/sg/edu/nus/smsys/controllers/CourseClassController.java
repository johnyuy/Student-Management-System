package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.SemesterRepository;

@Controller
@RequestMapping("/classes")
public class CourseClassController {
	@Autowired
	private CourseClassRepository ccRepo;
	@Autowired
	private CourseRepository couRepo;
	@Autowired
	private SemesterRepository semRepo;
	
	@GetMapping("/list")
	public String viewCourseClasses(Model model, @RequestParam(defaultValue = "") String courseId) {
		
		List<CourseClass> classlist = new ArrayList<CourseClass>();
		classlist = ccRepo.findAll();
		if(courseId.equals("")) {
			classlist = ccRepo.findAll();
		}
		else {
			Course course = couRepo.findByCourseId(Integer.parseInt(courseId));
			classlist = ccRepo.findByCourse(course);
		}
		model.addAttribute("classes", classlist);
		return("classlist");
	}
	
	//can be opened from the course details page
	@GetMapping("/add")
	public String showAddForm(Model model, @RequestParam(defaultValue="")String courseId) {
		CourseClass courseclass = new CourseClass();
		model.addAttribute(courseclass);
	
		List<Course> courseList = couRepo.findAll();
		model.addAttribute("courseList",courseList);

		List<Semester> semesterList = semRepo.findAll();
		model.addAttribute("semesterList",semesterList);
		
		
		
		return("courseclassform");
	}
	
	
	@PostMapping("/insert")
	public String insertCourseClass(@ModelAttribute CourseClass courseClass)
	{
		courseClass.setLevel(0);
		
		ccRepo.save(courseClass);
		
		return "redirect:/classes/list";
	}
}

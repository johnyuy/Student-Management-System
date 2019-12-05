package sg.edu.nus.smsys.controllers;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.repository.CourseRepository;

@Controller
@RequestMapping("/course")
public class CourseController
	{
		@Autowired
		private CourseRepository cRepo;
		
		@GetMapping("/list")
		public String listAll(Model course)
		{
			ArrayList<Course> cList = new ArrayList<Course>();
			cList.add((Course) cRepo.findAll());
			course.addAttribute("courses",cList);
			return "courses";
			
		}
		
		@GetMapping("/add")
		public String showAddForm(Model model)
		{
			Course course = new Course();
			model.addAttribute("course",course);
			return "courseform";
		}
		
		@PostMapping("/insert")
		public String insertCourse(@ModelAttribute Course course)
		{
			cRepo.save(course);
			return "redirect:/course/list";
		}
	}
package sg.edu.nus.smsys.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.CourseRepository;

@Controller
@RequestMapping("/courses")
public class CourseController
	{
		@Autowired
		private CourseRepository cRepo;
		@Autowired
		CourseClassRepository classrepo;
		
		@GetMapping("/list")
		public String listAll(Model course)
		{
			List<Course> cList = new ArrayList<Course>();
			cList = cRepo.findAll();
			course.addAttribute("courses",cList);
			return "courselist";
			
		}
		
		@GetMapping("/add")
		public String showAddForm(Model model)
		{
			Course course = new Course();
			model.addAttribute("course",course);
			return "courseform";
		}
		
		@PostMapping("/add")
		public String insertCourse(@ModelAttribute Course course)
		{
			cRepo.save(course);
			return "redirect:/courses/list";
		}
		
		@GetMapping("/details")
		public String viewCourseDetails(Model model, @RequestParam() String courseId)
		{
			Course course = cRepo.findByCourseId(Integer.parseInt(courseId));
			model.addAttribute("course",course);
			return "coursedetails";
		}
		
		@GetMapping("/edit/{courseId}")
		public String editCourseForm(Model model, @PathVariable("courseId") int id) {

			Course course = cRepo.findByCourseId(id);
			model.addAttribute("course",course);
			return "courseform";
		}

		@PostMapping("/edit/{courseId}")
		public String editCourse(@Valid @ModelAttribute Course c, BindingResult bindingResult,
				@RequestParam() String courseStatus) {
			if (bindingResult.hasErrors()) {
				return "courseform";
			}
			c.setCourseStatus(courseStatus);
			cRepo.save(c);
			return "redirect:/courses/list";
		}
	}
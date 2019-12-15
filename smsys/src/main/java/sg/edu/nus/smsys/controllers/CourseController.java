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
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Controller
@RequestMapping("/courses")
public class CourseController
	{
		@Autowired
		private CourseRepository cRepo;
		@Autowired
		CourseClassRepository classrepo;
		@Autowired
		private SmsUserDetailsService suds;
		
		@GetMapping("/list")
		public String listAll(Model model)
		{
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			List<Course> cList = new ArrayList<Course>();
			cList = cRepo.findAll();
			model.addAttribute("courses",cList);
			return "courselist";
			
		}
		
		@GetMapping("/add")
		public String showAddForm(Model model)
		{
			model.addAttribute("access", suds.getAuthUserAccessLevel());
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
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Course course = cRepo.findByCourseId(Integer.parseInt(courseId));
			List<Subject> subjectlist = course.getCourseSubjectList();
			model.addAttribute("course",course);
			model.addAttribute("subjectlist", subjectlist);
			return "coursedetails";
		}
		
		@GetMapping("/edit/{courseId}")
		public String editCourseForm(Model model, @PathVariable("courseId") int id) {
			int access = suds.getAuthUserAccessLevel();
			if (access==1) {
				model.addAttribute("access", suds.getAuthUserAccessLevel());
				Course course = cRepo.findByCourseId(id);
				model.addAttribute("course",course);
				return "courseform";
			}
			return "redirect:/";
		}

		@PostMapping("/edit/{courseId}")
		public String editCourse(@Valid @ModelAttribute Course c, BindingResult bindingResult,
				@RequestParam() String courseStatus) {
			int access = suds.getAuthUserAccessLevel();
			if(access==1) {
			if (bindingResult.hasErrors()) {
				return "courseform";
			}
			c.setCourseStatus(courseStatus);
			cRepo.save(c);
			return "redirect:/courses/list";
			}
			return "redirect:/";
		}
	}
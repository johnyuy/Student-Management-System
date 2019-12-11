package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.SmsysApplication;
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
	private static final Logger log = LoggerFactory.getLogger(CourseClassController.class);
	
	@GetMapping("/list")
	public String viewCourseClasses(Model model, @RequestParam(defaultValue = "") String courseId) {
		//get all classes from db
		List<CourseClass> classlist = ccRepo.findAll();
		String title = "All Courses";
		if(courseId.equals("")) {
			classlist = ccRepo.findAll();
			model.addAttribute("title", title);
		}
		else {
			Course course = couRepo.findByCourseId(Integer.parseInt(courseId));
			classlist = ccRepo.findByCourse(course);
			model.addAttribute("title", course.getCourseName());
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
	
	
	@PostMapping("/add")
	public String insertCourseClass(@ModelAttribute CourseClass courseClass)
	{
		log.info("Inserting Course Class...");
		log.info("	Course name = " + courseClass.getCourse().getCourseName());
		log.info("	Number of semesters =" + courseClass.getCourse().getDurationSemesters());
		courseClass.setLevel(0);
		//get the course's duration(sems)
		int duration = courseClass.getCourse().getDurationSemesters();
		//get first sem and the subsequent sems as a list
		int startid = courseClass.getSemesterList().get(0).getSemId();
		List<Semester> semList = new ArrayList<Semester>();
		for(int i = 0; i < duration; i++) {
			semList.add(semRepo.findBySemId(startid+i));
		}
		courseClass.setSemesterList(semList);
		ccRepo.save(courseClass);
		System.out.println(courseClass.getSemesterList().size());
		return "redirect:/classes/list";
	}
	
	@GetMapping("/details/{id}")
	public String viewCourseClass(Model model, @PathVariable("id") int id){
		//CourseClass cc = ccRepo.fin
		
		return("courseclassdetails");
	}
}

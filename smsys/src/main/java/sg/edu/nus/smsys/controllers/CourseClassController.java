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
import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.SemesterRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.CourseClassService;
import sg.edu.nus.smsys.service.SemesterService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("/classes")
public class CourseClassController {
	@Autowired
	private CourseClassRepository ccRepo;
	@Autowired
	private CourseRepository couRepo;
	@Autowired
	private SemesterRepository semRepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	private CourseClassService ccService;
	@Autowired
	private SemesterService semService;
	private static final Logger log = LoggerFactory.getLogger(CourseClassController.class);

	//show list of classes
	@GetMapping("")
	public String viewCourseClasses(Model model) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		System.out.println("level = " + suds.getAuthUserAccessLevel());
		List<CourseClass> classlist = ccService.getClassesByUser();
		model.addAttribute("classes", classlist);
		return ("classlist");
	}

	// can be opened from the course details page
	@GetMapping("/add")
	public String showAddForm(Model model, @RequestParam(defaultValue = "") String courseId) {
		CourseClass courseclass = new CourseClass();
		model.addAttribute(courseclass);

		List<Course> courseList = couRepo.findAll();
		model.addAttribute("courseList", courseList);

		List<Semester> semesterList = semRepo.findAll();
		model.addAttribute("semesterList", semesterList);

		return ("courseclassform");
	}

	@PostMapping("/add")
	public String insertCourseClass(@ModelAttribute CourseClass courseClass) {
		log.info("Inserting Course Class...");
		log.info("	Course name = " + courseClass.getCourse().getCourseName());
		log.info("	Number of semesters =" + courseClass.getCourse().getDurationSemesters());
		courseClass.setLevel(0);
		// get the course's duration(sems)
		int duration = courseClass.getCourse().getDurationSemesters();
		// get first sem and the subsequent sems as a list
		int startid = courseClass.getSemesterList().get(0).getSemId();
		List<Semester> semList = new ArrayList<Semester>();
		for (int i = 0; i < duration; i++) {
			semList.add(semRepo.findBySemId(startid + i));
		}
		courseClass.setSemesterList(semList);
		ccRepo.save(courseClass);
		System.out.println(courseClass.getSemesterList().size());
		return "redirect:/classes";
	}

	@GetMapping("/{id}")
	public String viewCourseClass(Model model, @PathVariable("id") int id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		CourseClass cc = new CourseClass();
		String str = "";
		
		if(ccService.canViewClass(id)) {
			cc = ccRepo.findByClassId(id);
			str = semService.semestersToString(cc.getSemesterList());
			model.addAttribute("class", cc);
			model.addAttribute("semlist", str);
			return ("courseclassdetails");
		}
		return "NotFound";
		
	}

	@GetMapping("/{id}/students")
	public String viewCourseClassStudents(Model model, @PathVariable("id") int id) {
		if(ccService.canViewClass(id))
		{
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			return("courseclassstudents");
		}
		return null;
	}
}

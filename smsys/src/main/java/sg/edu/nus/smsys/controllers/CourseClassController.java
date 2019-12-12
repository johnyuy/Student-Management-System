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
	private UserService us;
	@Autowired
	private SemesterService semService;
	private static final Logger log = LoggerFactory.getLogger(CourseClassController.class);

	@GetMapping("/list")
	public String viewCourseClasses(Model model) {
		List<CourseClass> classlist = getClassesByUser();
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
		return "redirect:/classes/list";
	}

	@GetMapping("/details/{id}")
	public String viewCourseClass(Model model, @PathVariable("id") int id) {
		
		CourseClass cc = new CourseClass();
		String str = "";
		
		if(canViewClass(id)) {
			cc = ccRepo.findByClassId(id);
			str = semService.semestersToString(cc.getSemesterList());
			model.addAttribute("class", cc);
			model.addAttribute("semlist", str);
			
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			return ("courseclassdetails");
		}
		return "NotFound";
		
	}

	@GetMapping("/students/{id}")
	public String viewCourseClassStudents(Model model, @PathVariable("id") int id) {
		if(canViewClass(id))
		{
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			return("courseclassstudents");
		}
		return null;
	}

	public boolean canViewClass(int id) {
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		boolean output = false;
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			cclist = student.getCourseClassList();
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
		} else if (accesslevel == 1) {
			return true;
		}
		if (!cclist.isEmpty()) {
			for (CourseClass cc : cclist) {
				if(cc.getClassId()==id)
					return true;
			}
		}
		return output;
	}
	
	public List<CourseClass> getClassesByUser(){
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			cclist = student.getCourseClassList();
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
		} else if (accesslevel == 1) {
			cclist = ccRepo.findAll();;
		}
		return cclist;
	}
	
	
}

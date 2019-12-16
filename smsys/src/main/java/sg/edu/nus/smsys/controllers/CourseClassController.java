package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.SemesterRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.CourseClassService;
import sg.edu.nus.smsys.service.SemesterService;
import sg.edu.nus.smsys.service.StudentServiceImpl;

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
	@Autowired
	private StudentServiceImpl stuService;
	@Autowired
	private StudentRepository stuRepo;
	@Autowired
	private LecturerRepository lectRepo;
	@Autowired
	private CourseClassRepository ccrepo;
	
	
	private static final Logger log = LoggerFactory.getLogger(CourseClassController.class);

	//view classes by user
	@GetMapping("")
	public String viewCourseClasses(Model model) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		System.out.println("level = " + suds.getAuthUserAccessLevel());
		List<CourseClass> classlist = ccService.getClassesByUser();
		model.addAttribute("classes", classlist);
		
		return ("classlist");
	}
	
	@GetMapping("/course/{id}")
	public String viewCourseClassesbyCourseId(Model model, @PathVariable("id") int id) {
		List<CourseClass> classlist = new ArrayList<CourseClass>();
		Course c = couRepo.findByCourseId(id);
		classlist.addAll(ccrepo.findByCourse(c));
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		model.addAttribute("classes", classlist);
		return ("classlist");
	}
	

	// can be opened from the course details page
	@GetMapping("/add")
	public String showAddForm(Model model, @RequestParam(defaultValue = "") String courseId) {
		
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		
		CourseClass courseclass = new CourseClass();
		model.addAttribute(courseclass);

		List<Course> courseList = couRepo.findAll();
		model.addAttribute("courseList", courseList);

		List<Semester> semesterList = semRepo.findAll();
		model.addAttribute("semesterList", semesterList);
		
		if(suds.getAuthUserAccessLevel()==3)
			return "redirect:/";
		else
			return("courseclassform");

	
	}

	@PostMapping("/add")
	public String insertCourseClass(@Valid @ModelAttribute CourseClass courseClass,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream()
					.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
			return "redirect:/classes/add";
		}
		
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
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		CourseClass cc = new CourseClass();
		String str = "";
		
		if(ccService.canViewClass(id)) {
			cc = ccRepo.findByClassId(id);
			str = semService.semestersToString(cc.getSemesterList());
			model.addAttribute("class", cc);
			model.addAttribute("semlist", str);
			
			if(accesslevel==1) {
				//available lecturers
				List<Lecturer> ll = ccService.getAvailableLecturers(cc);
				model.addAttribute("addable", ll);
			}
			
			return ("courseclassdetails");
			
		}
		return "redirect:/";
		
	}

	@GetMapping("/{id}/students")
	public String viewCourseClassStudents(Model model, @PathVariable("id") int id) {
		//id is course class id
		
		if(ccService.canViewClass(id))
		{
			CourseClass cc = ccService.findByClassId(id);
			if(cc!=null) {
				
				model.addAttribute("classId", cc.getClassId());
				List<Student> studentlist = cc.getStudentList();
				model.addAttribute("studentlist", studentlist);
				int accesslevel = suds.getAuthUserAccessLevel();
				//studentlist.get(0).get
				model.addAttribute("access", accesslevel);
				
				if(accesslevel==1)
				{
					List<Student> addableStudents = new ArrayList<Student>();
					//get list of students who are accepted for this course
					List<Student> acceptedStudents = stuService.getStudentsByApplicationStatus(cc.getCourse(), "accepted");
					if(acceptedStudents!=null) {
						for(Student s : acceptedStudents) {
							addableStudents.add(s);
						}
					} else {System.out.println("no outstanding accepted applications for this course");}
					//enrolled students without a class
					List<Student> availableStudents = new ArrayList<Student>();
					availableStudents = stuService.getEnrolledBalanceStudents(cc.getCourse());
					if(availableStudents!=null) {
						for(Student s : availableStudents) {
							addableStudents.add(s);
						}
					} else {System.out.println("no enrolled students w/o class for this course");}
					System.out.println("size of addable students list " + addableStudents.size());
					
					
					model.addAttribute("addable", addableStudents);
				}

				return("courseclassstudents");
			}
		}
		return "NotFound";
	}
	
	@GetMapping("/{id}/students/add")
	public String addCourseClassStudents(@PathVariable("id") int id, @RequestParam String code) {
		
		if(suds.getAuthUserAccessLevel()==1)
		{
			System.out.println(code);
			Student s = stuRepo.findByStudentId(Integer.parseInt(code));
			ccService.addStudentToClass(s, id);
			
			String redirect = "redirect:/classes/" + id + "/students";
			return redirect;
		}
		return "NotFound";
	}
	
	@GetMapping("/{id}/students/remove")
	public String removeCourseClassStudents(@PathVariable("id") int id, @RequestParam String code) {
		
		if(suds.getAuthUserAccessLevel()==1)
		{
			Student s = stuRepo.findByStudentId(Integer.parseInt(code));
			//ccService.addStudentToClass(s, id);
			ccService.removeStudentFromClass(s, id);
			
			String redirect = "redirect:/classes/" + id + "/students";
			return redirect;
		}
		return "NotFound";
	}
	
	@GetMapping("/{id}/lecturer/add")
	public String addCourseClassLecturer(@PathVariable("id") int id, @RequestParam String code) {
		
		if(suds.getAuthUserAccessLevel()==1)
		{
			Lecturer l = lectRepo.findByStaffId(Integer.parseInt(code));
			ccService.addLecturerToClass(l, id);
			
			String redirect = "redirect:/classes/" + id;
			return redirect;
		}
		return "NotFound";
	}
	@GetMapping("/{id}/lecturer/remove")
	public String removeCourseClassLecturer(@PathVariable("id") int id, @RequestParam String code) {
		
		if(suds.getAuthUserAccessLevel()==1)
		{
			System.out.println(code);
			Lecturer l = lectRepo.findByStaffId(Integer.parseInt(code));
			//ccService.addStudentToClass(s, id);
			ccService.removeLecturerFromClass(l, id);
			
			String redirect = "redirect:/classes/" + id;
			return redirect;
		}
		return "NotFound";
	}
}

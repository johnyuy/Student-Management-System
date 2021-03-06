package sg.edu.nus.smsys.controllers;

import java.security.GeneralSecurityException;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Grade;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.*;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.StudentServiceImpl;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentServiceImpl ss;
	@Autowired
	private GradeRepository grepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private CourseClassRepository ccrepo;
	@Autowired
	private SmsUserDetailsService suds;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ApplicationRepository apprepo;
	

	
	
	@Autowired
	private UserService us;
	

	@GetMapping("/list")
	public String findStudents(Model model, @RequestParam(defaultValue = "") String name) {
		if (suds.getAuthUserAccessLevel() == 1 || suds.getAuthUserAccessLevel() == 2) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			ArrayList<Student> slist = new ArrayList<Student>();
			slist.addAll(srepo.findByStudentFullNameLike(name));
			if (slist.size() == 1) {
				// 1 student found
				Student student = slist.get(0);
				return "redirect:/students/details/" + student.getStudentId();
			} else {
				model.addAttribute("students", slist);
			}
			return "studentlist";
		}
		return "redirect:/";
	}

	@GetMapping("/details/{id}")
	public String viewStudent(Model model, @PathVariable("id") int id) {
		if (ss.canViewStudent(id)) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			// find student
			Student student = srepo.findByStudentId(id);

			// find student course
			ArrayList<CourseClass> cc = new ArrayList<CourseClass>();
			cc.addAll(ccrepo.findByStudentListContaining(student));
			student.setCourseClassList(cc);
			cc.stream().forEach(c -> System.out.println(c.getCourse().getCourseName()));

			for (CourseClass c : cc) {
				c.setGradeList(grepo.findByStudentAndClas(student, c));
			}
			// find student GPA
			ArrayList<Float> gpa = new ArrayList<Float>();
			for (CourseClass c : cc) {
				float f = ss.CalulateGPA(student, c);
				gpa.add(f);
			}
			// find student grades
			model.addAttribute("gpa", gpa);
			model.addAttribute("student", student);
			return "studentdetails";
		}
		return "redirect:/";
	}

	@GetMapping("/add")
	public String addStudentForm(Model model) {
		if (suds.getAuthUserAccessLevel() == 1) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Student student = new Student();
			model.addAttribute("student", student);
			return "studentform";
		}
		return "redirect:/";
	}

	@PostMapping("/add")
	public String addStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult) throws GeneralSecurityException {
		if (suds.getAuthUserAccessLevel() == 1) {

			{
				if (bindingResult.hasErrors()) {
					bindingResult.getFieldErrors().stream()
							.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
					return "redirect:/students/add";
				}
				Student student = new Student();
				student = s;
				srepo.save(student);
				System.out.println("1");
				System.out.println(student.getStudentId());
				
				ArrayList<Student> sl = new ArrayList<Student>();
				sl.addAll(srepo.findAll());
				
				Student newstudent = sl.get(sl.size()-1);
				
				System.out.println(newstudent.getFirstName());
				System.out.println(newstudent.getStudentId());
				
				us.registerNewAccount(newstudent.getStudentId(), "123");
				
				System.out.println("New Account:");
				System.out.println(newstudent.getStudentId());
				System.out.println("123");

				return "redirect:/students/list";
			}
		}
		return "Not Found";
	}

	@GetMapping("/edit/{id}")
	public String editStudentForm(Model model, @PathVariable("id") int id) {
		if (ss.canViewStudent(id)) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Student student = srepo.findById(id).get();
			model.addAttribute("student", student);
			return "studentform";
		}
		return "/list";

	}

	@PostMapping("/edit/{id}")
	public String editStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (ss.canViewStudent(id)) {
			if (bindingResult.hasErrors()) {
				return "studentform";
			}
			s.setStudentId(id);
			srepo.save(s);
			
			if (suds.getAuthUserAccessLevel() == 1) {
				return "redirect:/students/list";
			}
			else {
				return "redirect:/students/details/" + s.getStudentId();
			}
		}
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(Model model, @PathVariable("id") int id) {
		if (suds.getAuthUserAccessLevel() == 1) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Student student = srepo.findById(id).get();
			
			List<Application> applist= apprepo.findByStudent(student);
			apprepo.deleteAll(applist);
			
			List<Grade> glist=grepo.findByStudent(student);
			grepo.deleteAll(glist);
			
			User u=userrepo.findByUsername("S"+student.getStudentId()).get();
			userrepo.delete(u);
			
			
			srepo.delete(student);
			
			return "redirect:/students/list";
		}
		return "redirect:/";
	}
}

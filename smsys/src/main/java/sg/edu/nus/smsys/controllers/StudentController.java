package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.smsys.UserSession;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Student;

import sg.edu.nus.smsys.repository.*;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.StudentServiceImpl;

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
		return "Not Found";
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
		return "Not Found";
	}

	@GetMapping("/add")
	public String addStudentForm(Model model) {
		if (suds.getAuthUserAccessLevel() == 1) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Student student = new Student();
			model.addAttribute("student", student);
			return "studentform";
		}
		return "Not Found";
	}

	@PostMapping("/add")
	public String addStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult) {
		if (suds.getAuthUserAccessLevel() == 1) {

			{
				if (bindingResult.hasErrors()) {
					bindingResult.getFieldErrors().stream()
							.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
					return "studentform";
				}
				Student student = new Student();
				student = s;
				srepo.save(student);
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
		return "Not Found";

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
			return "redirect:/students/list";
		}
		return "Not Found";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(Model model, @PathVariable("id") int id) {
		if (suds.getAuthUserAccessLevel() == 1) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			Student student = srepo.findById(id).get();
			srepo.delete(student);
			return "redirect:/students/list";
		}
		return "Not Found";
	}
}

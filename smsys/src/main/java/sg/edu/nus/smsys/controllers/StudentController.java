package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Student;

import sg.edu.nus.smsys.models.UserSession;

import sg.edu.nus.smsys.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository srepo;
	
	

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		
//	}

	@GetMapping("/list")
	public String findStudents(Model model, @RequestParam(defaultValue = "") String name) {
		ArrayList<Student> slist = new ArrayList<Student>();
		slist.addAll(srepo.findByStudentFullNameLike(name));

		if (slist.size() == 1) {
			// 1 student found
			Student student = slist.get(0);
			return "redirect:/students/details?id=" + student.getStudentId();
		} else {
			model.addAttribute("students", slist);
		}
		return "studentlist";
	}

	@GetMapping("/details/{id}")
	public String viewStudent(Model model, @PathVariable("id") int id) {
		Student student = srepo.findByStudentId(id);
		model.addAttribute("student", student);
		return "studentdetails";
	}

	@GetMapping("/add")
	public String addStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "studentform";
	}

	@PostMapping("/add")
	public String addStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult) {
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

	@GetMapping("/edit/{id}")
	public String editStudentForm(Model model, @PathVariable("id") int id) {
		Student student = srepo.findById(id).get();
		model.addAttribute("student", student);
		return "studentform";
	}

	@PostMapping("/edit/{id}")
	public String editStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "studentform";
		}
		s.setStudentId(id);
		srepo.save(s);
		return "redirect:/students/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(Model model, @PathVariable("id") int id) {
		Student student = srepo.findById(id).get();
		srepo.delete(student);
		return "redirect:/students/list";
	}
	
	@GetMapping("/user")
	public String userTest(@SessionAttribute("banana") UserSession banana){
		System.out.println(banana.getName());
		banana.setName("not mikey banana");
		System.out.println(banana.getName());	
		return "johannlogin";
		
	}
	
	

}

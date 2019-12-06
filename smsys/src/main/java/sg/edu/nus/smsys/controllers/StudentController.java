package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentRepository srepo;


	@GetMapping("/list")
	public String findStudent(Model model, @RequestParam(defaultValue = "") String name) {
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
	
	
	@GetMapping("/details")
	public String viewStudent(Model model, @RequestParam("id") int id) {
		ArrayList<Student> slist = new ArrayList<Student>();
		Student student = srepo.findByStudentId(id);
		model.addAttribute("student", student);
		return "studentdetails";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model)
	{
		Student student = new Student();
		model.addAttribute("student", student);
		return "studentform";
	}
	
	@PostMapping("/insert")
	public String insertCourse(@ModelAttribute Student s)
	{
		Student student = new Student();
		student.setFirstName(s.getFirstName());
		student.setMiddleName(s.getMiddleName());
		student.setLastName(s.getLastName());
		student.setBirthDate(null);
		student.setAddress(s.getAddress());
		student.setMobile(s.getMobile());
		student.setEmail(s.getEmail());
		student.setStatus(s.getStatus());
		srepo.save(student);
		return "redirect:/students/list";
	}

}

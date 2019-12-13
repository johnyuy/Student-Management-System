package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Grade;

import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.GradeRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;

@Controller
@RequestMapping("/grade")
public class GradeController {
	

	@Autowired
	GradeRepository grepo;
	@Autowired
	SubjectRepository subrepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	CourseClassRepository crepo;
	

	@GetMapping("/list")
	public String listAll(Model grade)
	{
		List<Grade> glist = new ArrayList<Grade>();
		glist = grepo.findAll();
		grade.addAttribute("grade",glist);
		return "grade";
				
	}
	
	@GetMapping("/add")
	public String addGrade(Model model) {
		Grade grade=new Grade();
		model.addAttribute("grade",grade);
		
		ArrayList<Student> stulist = new ArrayList<Student>();
		stulist.addAll(srepo.findAll());
		model.addAttribute("student",stulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclass",clist);
		
		ArrayList<Subject> sublist = new ArrayList<Subject>();
		sublist.addAll(subrepo.findAll());
		model.addAttribute("subject",sublist);

		return "gradeform";
	}
	
	@PostMapping("/add")
	public String insertGrade(@ModelAttribute Grade grade)
	{
		grepo.save(grade);
		return "redirect:/grade/list";
	}

}

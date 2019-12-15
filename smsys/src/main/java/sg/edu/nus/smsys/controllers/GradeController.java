package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Grade;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.GradeRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.CourseClassService;

@Controller
@RequestMapping("/grades")
public class GradeController {
	

	@Autowired
	GradeRepository grepo;
	@Autowired
	SubjectRepository subrepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	CourseClassRepository crepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	private CourseClassService ccService;
	
	@GetMapping("/list")
	public String listAll(Model grade)
	{
		List<Grade> glist = new ArrayList<Grade>();
		glist = grepo.findAll();
		grade.addAttribute("grade",glist);
		return "grade";
				
	}
	
	@GetMapping("/{id}")
	public String viewGradesByClass(Model model, @PathVariable("id") int id) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		CourseClass cc = new CourseClass();
		if(accesslevel==3)
			return("redirect:/");;
		
		if(ccService.canViewClass(id)) {
			cc = crepo.findByClassId(id);
			model.addAttribute("class", cc);
			List<Subject> subjectlist = cc.getCourse().getCourseSubjectList();
			if(subjectlist==null)
				return("redirect:/");
			model.addAttribute("subjectlist", subjectlist);
			return ("classgrades");
		}
		return("redirect:/");
	}
	
	@GetMapping("/{id}/{subject}")
	public String viewGradesByClassSubject(Model model, @PathVariable("id") int id, @PathVariable("subject") int subject) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		CourseClass cc = new CourseClass();
		if(accesslevel==3)
			return("redirect:/");;
		
		if(ccService.canViewClass(id)) {
			cc = crepo.findByClassId(id);
			model.addAttribute("class", cc);
			Subject s = subrepo.findBySubjectId(subject);
			model.addAttribute("subject", s);
			List<Student> studentlist = cc.getStudentList();
			if(studentlist.size()==0)
				return ("redirect:/grades/"+id);
			else
				model.addAttribute("studentlist", studentlist);
			
			List<Grade> grades = new ArrayList<Grade>();
			Map<Student, Grade> sgmap = new HashMap<Student, Grade>();
			for(Student stu : studentlist) {
				Grade grade = new Grade(stu, cc, s, " ");
				grades = grepo.findByStudentAndSubjectAndClas(stu, s, cc);
				System.out.println("student " + stu.getFirstName() + " got " + grades.size() + " results for " + s.getSubjectName());
				if(!grades.isEmpty()) {
					grade = grades.get(0);
				} else {
					grepo.save(grade);
				}
				sgmap.put(stu, grade);
			}
			model.addAttribute("map", sgmap);
			
			//attr = "class","subject","studentlist","sgmap"
			return ("classsubjectgrades");
		}
		return("redirect:/");
	}
	
	
	@GetMapping("/{id}/{subject}/{student}")
	public String viewStudentGrade(Model model, @PathVariable("id") int id, @PathVariable("subject") int subject, @PathVariable("student") int student) {
		
		
		return null;
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

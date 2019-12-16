package sg.edu.nus.smsys.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Grade;
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

//	@GetMapping("/list")
//	public String listAll(Model grade) {
//		List<Grade> glist = new ArrayList<Grade>();
//		glist = grepo.findAll();
//		grade.addAttribute("grade", glist);
//		return "grade";
//
//	}

	@GetMapping("/{id}")
	public String viewGradesByClass(Model model, @PathVariable("id") int id) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		CourseClass cc = new CourseClass();
		if (accesslevel == 3)
			return ("redirect:/");
		

		if (ccService.canViewClass(id)) {
			cc = crepo.findByClassId(id);
			model.addAttribute("class", cc);
			List<Subject> subjectlist = cc.getCourse().getCourseSubjectList();
			if (subjectlist == null)
				return ("redirect:/");
			model.addAttribute("subjectlist", subjectlist);
			return ("classgrades");
		}
		return ("redirect:/");
	}

	@GetMapping("/{id}/{subject}")
	public String viewGradesByClassSubject(Model model, @PathVariable("id") int id,
			@PathVariable("subject") int subject) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		CourseClass cc = new CourseClass();
		if (accesslevel == 3)
			return ("redirect:/");
		
		if(!ccService.canViewClass(id))
			return ("redirect:/");
		
		
		cc = crepo.findByClassId(id);
		model.addAttribute("class", cc);
		Subject s = subrepo.findBySubjectId(subject);
		model.addAttribute("subject", s);
		List<Student> studentlist = cc.getStudentList();
		if (studentlist.size() == 0)
			return ("redirect:/grades/" + id);
		else
			model.addAttribute("studentlist", studentlist);

		Map<Student, Grade> sgmap = new HashMap<Student, Grade>();

		for (Student stu : studentlist) {
			
			Grade gradefound = grepo.findByStudentAndSubjectAndClas(stu, s, cc);
			if(gradefound==null) {
				gradefound=new Grade(stu, cc, s, " ");
				grepo.save(gradefound);
			}
			sgmap.put(stu, gradefound);
			
		}
		
		
		model.addAttribute("map", sgmap);
		return ("classsubjectgrades");
		}
	

	@GetMapping("/{id}/{subject}/{student}")
	public String viewStudentGrade(Model model, @PathVariable("id") int id, @PathVariable("subject") int subject,
			@PathVariable("student") int student) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		if (accesslevel == 3)
			return ("redirect:/");
		
		CourseClass cc = crepo.findByClassId(id);
		Subject sub = subrepo.findBySubjectId(subject);
		Student stu = srepo.findByStudentId(student);
		Grade g = grepo.findByStudentAndSubjectAndClas(stu, sub, cc);
		model.addAttribute("student", stu);
		model.addAttribute("subject", sub);
		model.addAttribute("grade", g.getGrade());
		model.addAttribute("classid", id);
		return ("classsubjectstudentgrade");
	}
	
	@PostMapping("/changegrade")
	public String changeGrade(@RequestParam String newgrade,@RequestParam String stuid,@RequestParam String classId, @RequestParam String subid) {
		
		CourseClass cc = crepo.findByClassId(Integer.parseInt(classId));
		Student student = srepo.findByStudentId(Integer.parseInt(stuid));
		Subject subject = subrepo.findBySubjectId(Integer.parseInt(subid));
		Grade g =  grepo.findByStudentAndSubjectAndClas(student, subject, cc);
		if(g!=null) {
			g.setGrade(newgrade);
			grepo.save(g);
		} else { System.out.println("ERROR in retrieving grade!");}
		return ("redirect:/grades/" + classId + "/" + subid);
	}


}

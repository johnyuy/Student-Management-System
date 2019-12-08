package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;

@Controller
@RequestMapping("/subjects")
@SessionAttributes("usersession")
public class SubjectController {
	
	@Autowired
	private SubjectRepository subrepo;
	
	@GetMapping("/list")
	public String findSubject(Model model, @RequestParam(defaultValue = "") String name) {
		ArrayList<Subject> sublist = new ArrayList<Subject>();
		sublist.addAll(subrepo.findBySubjectNameContaining(name));
		System.out.println(sublist);
		if (sublist.size() == 1) {
			// 1 subject found
			Subject subject = sublist.get(0);
			return "redirect:/subjects/details?id=" + subject.getSubjectId();
		} else {
			model.addAttribute("subjects", sublist);
		}
		return "subjectlist";
	}

}

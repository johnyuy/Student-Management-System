package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Subject;
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
	
	@GetMapping("/details/{id}")
	public String viewSubject(Model model, @PathVariable("id") int id) {
		Subject subject = subrepo.findById(id).get();
		model.addAttribute("subject", subject);
		return "subjectdetails";
	}
	
	@GetMapping("/add")
	public String addSubjectForm(Model model) {
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		return "subjectform";
	}

	@PostMapping("/add")
	public String addSubject(@Valid @ModelAttribute Subject s, BindingResult bindingResult) {
		{
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().stream()
						.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
				return "subjectform";
			}
			Subject subject = new Subject();
			subject = s;
			subrepo.save(subject);
			return "redirect:/subjects/list";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String editSubjectForm(Model model, @PathVariable("id") int id) {
		Subject subject = subrepo.findById(id).get();
		model.addAttribute("subject", subject);
		return "subjectform";
	}

	@PostMapping("/edit/{id}")
	public String editSubject(@Valid @ModelAttribute Subject s, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "subjectform";
		}
		s.setSubjectId(id);
		subrepo.save(s);
		return "redirect:/subjects/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteSubject(Model model, @PathVariable("id") int id) {
		Subject subject = subrepo.findById(id).get();
		subrepo.delete(subject);
		return "redirect:/subjects/list";
	}

}

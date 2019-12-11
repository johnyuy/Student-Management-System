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

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

	@Autowired
	private LecturerRepository lrepo;

	@Autowired
	private SubjectRepository srepo;
	
	@GetMapping("/list")
	public String findLecturers(Model model, @RequestParam(defaultValue = "") String name) {
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());

		if (llist.size() == 1) {
			// 1 lecturer found
			Lecturer lecturer = llist.get(0);
			return "redirect:/lecturers/details?id=" + lecturer.getStaffId();
		} else {
			model.addAttribute("lecturers", llist);
		}
		return "lecturerlist";
	}

	@GetMapping("/details/{staffId}")
	public String viewLecturer(Model model, @PathVariable("staffId") int id) {
		Lecturer lecturer = lrepo.findByStaffId(id);
		
		ArrayList<Subject> subj = new ArrayList<Subject>();
		subj.addAll(srepo.findByLecturerListContaining(lecturer));
		lecturer.setSubjectList(subj);
		subj.stream().forEach(s -> System.out.println(s.getSubjectName()));
		return "lecturerdetails";
	}

	@GetMapping("/add")
	public String addLectuerForm(Model model) {
		Lecturer lecturer = new Lecturer();
		model.addAttribute("staff", lecturer);
		return "lecturerform";
	}

	@PostMapping("/add")
	public String addLecturer(@Valid @ModelAttribute Lecturer lecturer, BindingResult bindingResult) {
		{
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().stream()
						.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
				return "lecturerform";
			}
			Lecturer l = new Lecturer();
			l = lecturer;
			lrepo.save(lecturer);
			return "redirect:/lecturers/list";
		}
	}

	@GetMapping("/edit/{id}")
	public String editLecturerForm(Model model, @PathVariable("id") int id) {
		Lecturer lecturer = lrepo.findById(id).get();
		model.addAttribute("staff", lecturer);
		return "lecturerform";
	}

	@PostMapping("/edit/{id}")
	public String editLecturer(@Valid @ModelAttribute Lecturer l, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "lecturerform";
		}
		l.setStaffId(id);
		lrepo.save(l);
		return "redirect:/lecturers/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteLecturer(Model model, @PathVariable("id") int id) {
		Lecturer lecturer = lrepo.findById(id).get();
		lrepo.delete(lecturer);
		return "redirect:/lecturers/list";
	}
}
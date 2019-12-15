package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

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
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

	@Autowired
	private LecturerRepository lrepo;

	@Autowired
	private SubjectRepository srepo;

	@Autowired
	private ScheduleRepository screpo;

	@Autowired
	private SmsUserDetailsService suds;

	@GetMapping("/list")
	public String listLecturers(Model model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		model.addAttribute("lecturerlist", null);

		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturers", llist);

		return "lecturerlist";
	}

//	@GetMapping("/details/{staffId}")
//	public String viewLecturer(Model model, @PathVariable("staffId") int id) {
//		model.addAttribute("access", suds.getAuthUserAccessLevel());
//		Lecturer lecturer = lrepo.findByStaffId(id);
//		ArrayList<Subject> s = new ArrayList<Subject>();
//		s.addAll(srepo.findByLecturerListContaining(lecturer));
//		System.out.println(s.isEmpty());
//		lecturer.setSubjectList(s);
//		s.stream().forEach(ss -> System.out.println(ss.getSubjectName()));
//		model.addAttribute("lecturer",lecturer);
//		return "lecturerdetails";
//	}
//	
	@GetMapping("/details/{staffId}")
	public String viewCourseClass(Model model, @PathVariable("staffId") int id) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		Lecturer l = new Lecturer();
		System.out.println("sl");
		String str = "";
		l = lrepo.findByStaffId(id);
		List<Subject> ls = l.getSubjectList();

		model.addAttribute("lecturer", l);
		List<Subject> unremovable = new ArrayList<Subject>();

		if (accesslevel == 1) {
			// available lecturers
			List<Subject> sl = getAvailableSubjects(l);
			model.addAttribute("addable", sl);

			List<Schedule> schlist = new ArrayList<Schedule>();

			schlist.addAll(screpo.findByLecturer(l));
			for (Schedule s : schlist) {
				for (Subject su : sl) {
					if (s.getSubject().equals(su) && !unremovable.contains(su)) {
						unremovable.add(su);
						ls.remove(su);
					}
				}
			}
			System.out.println("unr");

			System.out.println(unremovable.size());
		}
		model.addAttribute("unremovable", unremovable);
		return ("lecturerdetails");
	}

	public List<Subject> getAvailableSubjects(Lecturer l) {
		List<Subject> output = new ArrayList<Subject>();
		List<Subject> ls = l.getSubjectList();
		List<Subject> subjlist = srepo.findAll();
		if (ls != null) {
			for (Subject s : subjlist) {
				if (!ls.contains(s)) {
					output.add(s);
					continue;
				}
			}
		} else {
			output.addAll(subjlist);
		}
		return output;
	}

	@GetMapping("/add")
	public String addLectuerForm(Model model) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
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
			lrepo.save(l);
			return "redirect:/lecturers/list";
		}
	}

	@GetMapping("/edit/{staffId}")
	public String editLecturerForm(Model model, @PathVariable("staffId") int id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Lecturer lecturer = lrepo.findByStaffId(id);
		model.addAttribute("lecturer", lecturer);
		return "lecturerform";
	}

	@PostMapping("/edit/{id}")
	public String editLecturer(@Valid @ModelAttribute Lecturer lecturer, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "lecturerform";
		}
		System.out.println("Hi");
		lecturer.setStaffId(id);
		lrepo.save(lecturer);
		return "redirect:/lecturers/list";
	}

	@GetMapping("/delete/{staffId}")
	public String deleteLecturer(Model model, @PathVariable("staffId") int id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Lecturer lecturer = lrepo.findByStaffId(id);
		lrepo.delete(lecturer);
		return "redirect:/lecturers/list";
	}

}
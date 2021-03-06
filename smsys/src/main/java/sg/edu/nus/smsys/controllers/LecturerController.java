package sg.edu.nus.smsys.controllers;

import java.security.GeneralSecurityException;
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

import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

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
	
	@Autowired
	private UserService us;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ScheduleRepository schrepo;
	
	@Autowired
	private LeaveRepository leaverepo;
	
	
	@GetMapping("/list")
	public String listLecturers(Model model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		model.addAttribute("lecturerlist", null);

		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturers", llist);

		return "lecturerlist";
	}

	@GetMapping("/details/{staffId}")
	public String viewCourseClass(Model model, @PathVariable("staffId") int id) {
		int accesslevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", accesslevel);
		Lecturer l = new Lecturer();
		l = lrepo.findByStaffId(id);
		List<Subject> ls = l.getSubjectList();
		model.addAttribute("rawlist", ls);

		

		model.addAttribute("lecturer", l);
		List<Subject> unremovable = new ArrayList<Subject>();

		if (accesslevel == 1) {
			// available lecturers
			List<Subject> sl = getAvailableSubjects(l);
			List<Subject> con = new ArrayList<Subject>();

			model.addAttribute("addable", sl);

			List<Schedule> schlist = new ArrayList<Schedule>();
			schlist.addAll(screpo.findByLecturer(l));
			System.out.println("SchList Size: ");
			System.out.println(schlist.size());
			
			for (Schedule s : schlist) {
				if(! con.contains(s.getSubject())) {
					con.add(s.getSubject());
				}
			}
			System.out.println("Con Size: ");
			System.out.println(con.size());
			
			for (Subject s : con) {
				if(ls.contains(s)) {
					unremovable.add(s);
					l.getSubjectList().remove(s);
				}
			}
			
			System.out.println("unr");
			System.out.println(unremovable.size());
		}
		model.addAttribute("lecturer", l);
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
	
	
	@GetMapping("/{id}/subject/add")
	public String addCourseClassLecturer(@PathVariable("id") int id, @RequestParam String code) {
		
		if(suds.getAuthUserAccessLevel()==1)
		{
			Subject s = srepo.findBySubjectId(Integer.parseInt(code));
			Lecturer l = lrepo.findByStaffId(id);
			List<Subject> ls = l.getSubjectList();
			ls.add(s);
			l.setSubjectList(ls);
			lrepo.save(l);			
			String redirect = "redirect:/lecturers/details/" + id;
			return redirect;
		}
		return "NotFound";
	}
	
	@GetMapping("/{id}/subject/remove")
	public String removeCourseClassLecturer(@PathVariable("id") int id, @RequestParam String code) {	
		if(suds.getAuthUserAccessLevel()==1)
		{
			Subject s = srepo.findBySubjectId(Integer.parseInt(code));
			Lecturer l = lrepo.findByStaffId(id);
			List<Subject> ls = l.getSubjectList();
			ls.remove(s);
			l.setSubjectList(ls);
			lrepo.save(l);	
			String redirect = "redirect:/lecturers/details/" + id;
			return redirect;
		}
		return "NotFound";
	}
	
	
	

	@GetMapping("/add")
	public String addLectuerForm(Model model) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
		return "lecturerform";
	}

	@PostMapping("/add")
	public String addLecturer(@Valid @ModelAttribute Lecturer lecturer, BindingResult bindingResult) throws GeneralSecurityException {
		{
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().stream()
						.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
				return "redirect:/lecturers/add";
			}
			Lecturer l = new Lecturer();
			l = lecturer;
			lrepo.save(l);
			
			Lecturer l1 = new Lecturer();
			List<Lecturer>lectList = new ArrayList<Lecturer>();
			lectList.addAll(lrepo.findAll());
			l1 = lectList.get(lectList.size()-1);
			us.registerNewAccount(l1.getStaffId(), "pass");
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
		Lecturer l = lrepo.findByStaffId(id);
		l.setTitle(lecturer.getTitle());
		l.setFirstName(lecturer.getFirstName());
		l.setMiddleName(lecturer.getMiddleName());
		l.setLastName(lecturer.getLastName());
		l.setBirthDate(lecturer.getBirthDate());
		l.setMobile(lecturer.getMobile());
		l.setEmail(lecturer.getEmail());
		l.setGender(lecturer.getGender());
		l.setAddress(lecturer.getAddress());
		lrepo.save(l);
		if (suds.getAuthUserAccessLevel() == 1) {
			return "redirect:/lecturers/list";
		}
		else {
			return "redirect:/lecturers/details/" + l.getStaffId();
		}
	}

	@GetMapping("/delete/{staffId}")
	public String deleteLecturer(Model model, @PathVariable("staffId") int id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Lecturer lecturer = lrepo.findByStaffId(id);
		int staffid = lecturer.getStaffId();
		
		List<Schedule> schlist = schrepo.findByLecturer(lecturer);
		schrepo.deleteAll(schlist);
		List<Leave> llist = leaverepo.findBysubmittedByStaffID(lecturer);
		leaverepo.deleteAll(llist);
		
		//remove user account
		User u = us.getUserByUsername("L"+staffid);
		if(u!=null) {
			
			urepo.delete(u);
			
		}
		lrepo.delete(lecturer);
		return "redirect:/lecturers/list";
	}

}
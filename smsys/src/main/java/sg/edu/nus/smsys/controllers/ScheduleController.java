package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
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
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleRepository srepo;
	
	@Autowired
	LecturerRepository lrepo;
	
	@Autowired
	SubjectRepository surepo;
	
	@Autowired
	CourseClassRepository crepo;
	
	@GetMapping("/add")
	public String createSchedule(Model model) {
		
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturer",llist);
		
		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subject",sulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclass",clist);
		
		Schedule schedule=new Schedule();
		model.addAttribute("schedule",schedule);
		
		
		return "scheduleform";
	}
	
	@PostMapping("/add")
	public String showSchedule(@ModelAttribute Schedule schedule) {
		srepo.save(schedule);
		return "redirect:/schedule/list";
	}
	
	@GetMapping("/list")
	public String displaySchedulelist(Model model,@RequestParam(defaultValue = "") String id) {
		List<Schedule> slist=new ArrayList<Schedule>();
		model.addAttribute("schedule",slist);
		
		if(id.equals("")) {
			slist.addAll(srepo.findAll());
			model.addAttribute("schedule",slist);
			return "schedulelist";
		}
		else {
			slist.addAll(srepo.findByClassIdorLecturerId(Integer.parseInt(id)));
			model.addAttribute("schedule",slist);
			//int a=Integer.parseInt(id);
			return "redirect:/schedule//details/{id}";
		}
		/*model.addAttribute("schedule",slist);
		
		if(id.equals("")) {
			return "schedulelist";
		}
		else {
			int a=Integer.parseInt(id);
			return "redirect:/schedule/list?id="+a;
		}*/
		
	}
	
	/*
	@GetMapping("/list")
	public String displaySchedulelist(Model model) {
		List<Schedule> slist=new ArrayList<Schedule>();
		slist.addAll(srepo.findAll());		
		model.addAttribute("schedule",slist);
		return "schedulelist";	
	}
	*/
	
	
	@GetMapping("/details/{id}")
	public String viewSchedule(Model model, @PathVariable("id") int id) {
		List<Schedule> slist=new ArrayList<Schedule>();
		slist.addAll(srepo.findByClassIdorLecturerId(id));
		model.addAttribute("schedule",slist);
		
		return "scheduledetails";	
		
		
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Integer id) {
		
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturer",llist);
		
		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subject",sulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclass",clist);
		
		Schedule schedule=srepo.findById(id).get();
		model.addAttribute("schedule", schedule);
		return "scheduleform";
	}
	
	@PostMapping("/edit/{id}")
	public String editSchedule(@ModelAttribute Schedule schedule, @PathVariable("id") Integer id) {
		schedule.setScheduleId(id);
		srepo.save(schedule);
		return "redirect:/schedule/list" ;
	}
    
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		Schedule schedule=srepo.findById(id).get();
		srepo.delete(schedule);
		return "redirect:/schedule/list" ;
	}
	
	
	
	/*public void test() {
		LocalDate d1  = LocalDate.now();
		int dayofweek  = d1.getDayOfWeek().getValue();
		
		
	}*/
}

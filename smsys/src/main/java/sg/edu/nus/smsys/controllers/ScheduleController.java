package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
		model.addAttribute("lecturerlist",llist);
		
		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subjectlist",sulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclasslist",clist);
		
		Schedule schedule=new Schedule();
		model.addAttribute("schedule",schedule);
		
		
		return "scheduleform";
	}
	
	@PostMapping("/add")
	public String showSchedule(@ModelAttribute Schedule schedule) {
		srepo.save(schedule);
		return "redirect:/schedule/list";
	}
	
	@GetMapping("/edit/{id}")
	public String RecreateSchedule(Model model,@PathVariable("id") Integer id) {
		Schedule schedule=srepo.findById(id).get();
		srepo.delete(schedule);
		
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturerlist",llist);
		
		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subjectlist",sulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclasslist",clist);
		
		Schedule newschedule=new Schedule();
		model.addAttribute("schedule",newschedule);
		
		
		return "scheduleform";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		Schedule schedule=srepo.findById(id).get();
		srepo.delete(schedule);
		return "redirect:/schedule/list" ;
	}
	
	
	
	@GetMapping("/list")
	public String displaySchedulelist(Model model) {
		
		List<Schedule> slist=new ArrayList<Schedule>();
		
			slist.addAll(srepo.findAll());
			//Collections.sort(slist);
			
			model.addAttribute("schedule",slist);
			return "schedulelist";
		
		}
	
	
}

package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Staff;
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
		Schedule schedule=new Schedule();
		model.addAttribute("schedule",schedule);
		
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturer",llist);
		
		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subject",sulist);
		
		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclass",clist);
		
		return "scheduleform";
	}

	
	

}

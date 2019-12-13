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
	public String displaySchedulelist(Model model,@RequestParam(defaultValue = "") String classid) {
		
		List<Schedule> slist=new ArrayList<Schedule>();
		
//		slist.addAll(srepo.findByClassIdorLecturerId(Integer.parseInt(classid)));
//		
//		model.addAttribute("schedule",slist);
		
		
		if(classid.equals("")) {
			slist.addAll(srepo.findAll());
			//Collections.sort(slist);
			
			model.addAttribute("schedule",slist);
			return "schedulelist";
		}
		else {
//			model.addAttribute("schedule",slist);
			int classID=Integer.parseInt(classid);
			System.out.println("classID in the list="+classID);
			
			List<Schedule> sslist1=new ArrayList<Schedule>();
			List<Schedule> sslist2=new ArrayList<Schedule>();
			
			CourseClass courseclass=new CourseClass();
			courseclass=crepo.findByClassId(classID);
			
			sslist1.addAll(srepo.findByClas(courseclass));
			sslist2.addAll(srepo.findAll());
			
			int b;
			int c;
			b=sslist1.size();
			c=sslist2.size();
			
			System.out.println("list size by id="+b);
			System.out.println("all list size ="+c);

			return "redirect:/schedule/details?id="+classID;
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
	
	
	@GetMapping("/details/{classid}")
	public String viewSchedule(Model model, @PathVariable("classid") String id) {
		System.out.println("begin");
		
		List<Schedule> slist=new ArrayList<Schedule>();
		//System.out.println("slist created?" + slist);
		//System.out.println(srepo.findByClassId(Integer.parseInt(id)));
		
		System.out.println("next");
		//CourseClass course=new CourseClass();
		CourseClass cc=new CourseClass();
		//cc.addAll(crepo.findByClassId(Integer.parseInt(id)));
		cc=crepo.findByClassId(Integer.parseInt(id));
		
		//slist.addAll(srepo.findByClas(cc));
		slist = srepo.findByClas(cc);
		
		
		System.out.println("then");
		//System.out.println("s list :\n" + slist);
		model.addAttribute("schedule",slist);
		System.out.println("finally");
		
		return "scheduledetails";		
		
	}
	
	
	/*
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") int id) {
		Schedule schedule=srepo.findById(id).get();
		model.addAttribute("schedule", schedule);
		System.out.println(schedule.toString());
//		System.out.println(schedule.getScheduleId());
//		System.out.println(schedule.getPeriod());
//		System.out.println(schedule.getDate());
//		System.out.println(schedule.getClas().getClassId());
//		System.out.println(schedule.getLecturer().getStaffId());
//		System.out.println(schedule.getSubject().getSubjectId());
//		System.out.println(schedule.getClas());
//		System.out.println(schedule.getLecturer());
//		System.out.println(schedule.getSubject());
		
		
		List<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		//System.out.println("number of lect = " + llist.size());
		model.addAttribute("lecturerlist",llist);
		//System.out.println("lecturer="+llist);
		
		List<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subjectlist",sulist);
		//System.out.println("subject="+sulist);

		List<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclasslist",clist);
		//System.out.println("courseclass="+clist);

			
		return "scheduleform";
	}
	
	@PostMapping("/edit/{id}")
	public String editSchedule(@ModelAttribute Schedule schedule, @PathVariable("id") int id) {
		schedule.setScheduleId(id);
		srepo.save(schedule);
		return "redirect:/schedule/list" ;
	}
	*/
    
	
	
}

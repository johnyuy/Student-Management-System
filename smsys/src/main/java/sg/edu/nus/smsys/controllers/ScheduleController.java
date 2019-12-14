package sg.edu.nus.smsys.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
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

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Semester;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

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

	@Autowired
	private SmsUserDetailsService suds;

	@GetMapping("/courseclassschedule/{id}")
	public String showCourseClassSchedule(Model model, @PathVariable("id") int id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		CourseClass cc = crepo.findByClassId(id);
		int courseduration = cc.getCourse().getDurationSemesters();
		System.out.println(courseduration);
		ArrayList<Semester> semlist = new ArrayList<Semester>();
		ArrayList<Schedule> schlist = new ArrayList<Schedule>();
		List<Schedule> schfoundbycc = srepo.findByClas(cc);
		System.out.println(schfoundbycc.size());
		HashMap<Semester, ArrayList<Schedule>> timetables = new HashMap<Semester, ArrayList<Schedule>>();
		semlist.addAll(cc.getSemesterList());
		// need to increment the semester to get them all but nvm
		for (Semester s : semlist) {
			timetables.put(s, getHashMapOfSchdule(s, cc));
		}
		model.addAttribute("courseclass", cc);
		model.addAttribute("timetables", timetables);
		return "courseclassschedule";
	}

	public ArrayList<Schedule> getHashMapOfSchdule(Semester s, CourseClass clas) {
		List<Schedule> schfoundbycc = srepo.findByClas(clas);
		ArrayList<Schedule> schlist = new ArrayList<Schedule>();
		for (LocalDate date = s.getStartDate(); date.isBefore(s.getEndDate()); date = date.plusDays(1)) {
			int flag = 0;
			for (Schedule foundsch : schfoundbycc) {
				if (foundsch.getDate().equals(date)) {
					schlist.add(foundsch);
					flag = 1;
				}
			}
			if (flag == 0) {
				Lecturer noclass = new Lecturer("No Class");
				Schedule freeday = new Schedule(date, noclass, new Subject(), new CourseClass());
				// check if key existing
				schlist.add(freeday);
			}
		}
		return schlist;
	}

	@GetMapping("/add")
	public String createSchedule(Model model) {

		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(lrepo.findAll());
		model.addAttribute("lecturerlist", llist);

		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(surepo.findAll());
		model.addAttribute("subjectlist", sulist);

		ArrayList<CourseClass> clist = new ArrayList<CourseClass>();
		clist.addAll(crepo.findAll());
		model.addAttribute("courseclasslist", clist);

		Schedule schedule = new Schedule();
		model.addAttribute("schedule", schedule);

		return "scheduleform";
	}

	@PostMapping("/add")
	public String showSchedule(@ModelAttribute Schedule schedule) {
		srepo.save(schedule);
		return "redirect:/schedule/list";
	}

	@GetMapping("/edit/{id}")
	public String editSchedule(Model model, @PathVariable("id") String id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Integer classid;
		Schedule schedule = new Schedule();
		if (id.contains("-")) {
			String[] parts = id.split("X");
			classid = Integer.parseInt(parts[1]);
			LocalDate date = LocalDate.parse(parts[0]);
			schedule.setDate(date);
		} else {
			schedule = srepo.findById(Integer.parseInt(id)).get();
			classid = schedule.getClas().getClassId();
		}
		CourseClass cc = crepo.findByClassId(classid);
		
		ArrayList<Lecturer> llist = new ArrayList<Lecturer>();
		llist.addAll(cc.getLecturerList());
		model.addAttribute("lecturerlist", llist);

		ArrayList<Subject> sulist = new ArrayList<Subject>();
		sulist.addAll(cc.getCourse().getCourseSubjectList());
		model.addAttribute("subjectlist", sulist);
		
		model.addAttribute("courseclass", cc);

		model.addAttribute("schedule", schedule);
		return "scheduleform";
	}

	@PostMapping("/edit/{id}")
	public String insertSchdule(@Valid @ModelAttribute Schedule s, BindingResult bindingResult,
			@PathVariable("id") String id) {	
		Integer classid;
		if (id.contains("-")) {
			String[] parts = id.split("X");
			classid = Integer.parseInt(parts[1]);
			s.setClas(crepo.findByClassId(classid));
			srepo.save(s);
		} else {
			s.setScheduleId(Integer.parseInt(id));
			classid = s.getClas().getClassId();
			srepo.save(s);
		}
		return "redirect:/schedule/courseclassschedule/" + classid;
	}

	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") String id) {
		Integer classid;
		Schedule schedule = new Schedule();
		if (id.contains("-")) {
			String[] parts = id.split("X");
			classid = Integer.parseInt(parts[1]);
			return "redirect:/schedule/courseclassschedule/" + classid;
		} else {
			schedule = srepo.findById(Integer.parseInt(id)).get();
			srepo.delete(schedule);
			return "redirect:/schedule/courseclassschedule/" + schedule.getClas().getClassId();
		}
	}

	@GetMapping("/list")
	public String displaySchedulelist(Model model) {

		List<Schedule> slist = new ArrayList<Schedule>();

		slist.addAll(srepo.findAll());
		// Collections.sort(slist);

		model.addAttribute("schedule", slist);
		return "schedulelist";

	}

//	
//	@GetMapping("/courseclassschedule/{id}")
//	public String showSchedule(Model model, @PathVariable("id") int id) {
//		CourseClass cc = crepo.findByClassId(id);
//		List<Semester> semlist = new ArrayList<Semester>();
//		List<Schedule> schlist = new ArrayList<Schedule>();
//	    int numberOfDays = 0;
//	    Calendar c = Calendar.getInstance();    
//		semlist.addAll(cc.getSemesterList());
//		for(Semester s: semlist) {
//			LocalDate sd = s.getStartDate();
//			LocalDate ed = s.getEndDate();
////			for(Date date = java.sql.Date.valueOf(sd); date.before(java.sql.Date.valueOf(ed)); date = ((LocalDate) date).plusDays(1)) {
//////				Schedule schdule = new Schedule(date,null,null,null);
////				schlist.add(schdule);
////       	     	c.setTime(date);
////
////		        if ((Calendar.SATURDAY != c.get(Calendar.DAY_OF_WEEK)) &&(Calendar.SUNDAY != c.get(Calendar.DAY_OF_WEEK))) {	            
////		        	
////		                 numberOfDays++;
////		             }
////		             c.add(Calendar.DATE,1);
////				
//				
//				
//			}
//			model.addAttribute("courseclass",cc);
//
//			model.addAttribute("schedule",schlist);
//		}
//		return "courseclassschdule";	
//	}

}

package sg.edu.nus.smsys.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseAdmin;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Semester;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;
import sg.edu.nus.smsys.repository.ApplicationRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("")
@SessionAttributes({ "name", "classid", "date", "access","staffid", "studentid" })
public class HomeResource {
	@Autowired
	SmsUserDetailsService suds;
	@Autowired
	CourseRepository courserepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	LeaveRepository lrepo;
	@Autowired
	ApplicationRepository arepo;
	@Autowired
	UserService us;
	@Autowired
	ScheduleRepository schrepo;

	@GetMapping("/welcome")
	public String landing(Model model) {
		List<Course> courselist = courserepo.findAll();
		model.addAttribute("courselist", courselist);
		return "welcome.html";
	}

	@GetMapping("")
	public String home(HttpSession session, Model model) {
		String name = "";
		Student student = new Student();
		CourseAdmin ca = new CourseAdmin();
		Lecturer lecturer = new Lecturer();
		CourseClass clas = new CourseClass();
		LocalDate now = LocalDate.now();
		String date = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		int accessLevel = suds.getAuthUserAccessLevel();
		session.setAttribute("date", date);
		session.setAttribute("access", suds.getAuthUserAccessLevel());

		if (accessLevel == 1) {
			name = us.getCourseAdminByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			ca = us.getCourseAdminByUser(us.getUserByUsername(suds.getAuthUsername()));

			System.out.println(name);

			// Pending Leave Count
			List<Leave> ll = new ArrayList<Leave>();
			ll.addAll(lrepo.findAll());
			int leavecount = 0;
			for (Leave l : ll) {
				if (l.getStatus().equals("Pending"))
					leavecount++;
			}
			System.out.println(ll.size());
			System.out.println(leavecount);
			// Pending Application Count
			List<Application> al = new ArrayList<Application>();
			al.addAll(arepo.findAll());
			int appcount = 0;
			for (Application a : al) {
				if (a.getStatus().equals("pending"))
					appcount++;
			}
			System.out.println(al.size());
			System.out.println(appcount);
			session.setAttribute("name", name);
			model.addAttribute("leavecount", leavecount);
			session.setAttribute("staffid", ca.getStaffId());
			model.addAttribute("appcount", appcount);
			return "adminhome";
		}
		if (accessLevel == 2) {
			name = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			int staffid = lecturer.getStaffId();
			List<Schedule> schlist = new ArrayList<Schedule>();
			schlist.addAll(schrepo.findByLecturer(lecturer));
			Schedule today = new Schedule();
			Schedule next = new Schedule();

			if (!schlist.isEmpty()) {
				// TODAY
				for (Schedule sch : schlist) {
					if (sch.getDate().equals(now)) {
						today = sch;
						System.out.println("here");
						break;
					} else {
						CourseClass noclass = new CourseClass();
						Subject subject = new Subject("No Class");
						today = new Schedule(now, new Lecturer(), subject, noclass);
					}
				}
				next = getNextLecturerSchedule(lecturer);

				model.addAttribute("today", today);
				model.addAttribute("next", next);
				session.setAttribute("name", name);
				session.setAttribute("staffid", staffid);
				return "lecturerhome";
			}
		}
		if (accessLevel == 3) {
			name = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			Schedule today = new Schedule();
			Schedule next = new Schedule();
			int studentid = student.getStudentId();

			if (!student.getCourseClassList().isEmpty()) {
				clas = student.getCourseClassList().get(student.getCourseClassList().size() - 1);
				System.out.println("Class Id is:");
				System.out.println(clas.getClassId());
			} else {
				clas.setClassId(0);
				System.out.println(clas.getClassId());
			}

			CourseClass cclass = new CourseClass();
			if (student.getCourseClassList().size() > 0) {
				cclass = student.getCourseClassList().get(student.getCourseClassList().size() - 1);
				session.setAttribute("classid", cclass.getClassId());
				Semester sem = cclass.getSemesterList().get(cclass.getSemesterList().size() - 1);
				LocalDate ed = sem.getEndDate();
				List<Schedule> schlist = new ArrayList<Schedule>();
				schlist.addAll(schrepo.findByClas(cclass));

				if (!schlist.isEmpty()) {
					// TODAY
					for (Schedule sch : schlist) {
						if (sch.getDate().equals(now)) {
							today = sch;
							System.out.println("here");
							break;
						} else {
							Lecturer noclass = new Lecturer("No Class");
							Subject subject = new Subject("No Class");
							today = new Schedule(now, noclass, subject, new CourseClass());
						}
					}
					next = getNextSchedule(cclass);
				}
			} else {
				Lecturer noclass = new Lecturer("Not Enrolled");
				Subject subject = new Subject("Not Enrolled");
				today = new Schedule(now, noclass, subject, new CourseClass());
				next = new Schedule(now, noclass, subject, new CourseClass());
				session.setAttribute("classid", 0);

			}
			model.addAttribute("today", today);
			model.addAttribute("next", next);
			session.setAttribute("name", name);
			session.setAttribute("studentid", studentid);
			return "studenthome";
		}
		return "redirect:/welcome";

	}

	public Schedule getNextSchedule(CourseClass cc) {
		Semester sem = cc.getSemesterList().get(cc.getSemesterList().size() - 1);
		Schedule schedule = new Schedule();
		List<Schedule> schlist = new ArrayList<Schedule>();
		schlist.addAll(schrepo.findByClas(cc));
		LocalDate ed = sem.getEndDate();
		LocalDate now = LocalDate.now();

		for (LocalDate d = now.plusDays(1); d.isBefore(ed); d = d.plusDays(1)) {
			for (Schedule sch : schlist) {
				if (sch.getDate().equals(d)) {
					schedule = sch;
					return schedule;
				} else {
					Lecturer noclass = new Lecturer("No Class/ Timetable not Updated");
					Subject subject = new Subject("No Class/ Timetable not Updated");
					schedule = new Schedule(d, noclass, subject, new CourseClass());
				}
			}
		}
		System.out.println(schedule.getDateFormat());
		return schedule;
	}

	public Schedule getNextLecturerSchedule(Lecturer lecturer) {
		Schedule schedule = new Schedule();
		LocalDate now = LocalDate.now();
		LocalDate hundreddays = LocalDate.now().plusDays(100);
		List<Schedule> schlist = new ArrayList<Schedule>();
		schlist.addAll(schrepo.findByLecturer(lecturer));

		for (LocalDate d = now.plusDays(1); d.isBefore(hundreddays); d = d.plusDays(1)) {
			for (Schedule sch : schlist) {
				if (sch.getDate().equals(d)) {
					schedule = sch;
					return schedule;
				} else {
					Lecturer noclass = new Lecturer("No Class/ Timetable not Updated");
					Subject subject = new Subject("No Class/ Timetable not Updated");
					schedule = new Schedule(d, noclass, subject, new CourseClass());
				}
			}
		}
		System.out.println(schedule.getDateFormat());
		return schedule;
	}
}

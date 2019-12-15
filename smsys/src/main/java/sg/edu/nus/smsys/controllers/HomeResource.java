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
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.ApplicationRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("")
@SessionAttributes({"name","classid","date",""})
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
	
	@GetMapping("/welcome")
	public String landing(Model model) {
		List<Course> courselist = courserepo.findAll();
		model.addAttribute("courselist",courselist);
		return "welcome.html";
	}
	
	@GetMapping("")
	public String home(HttpSession session, Model model){
		String name = "";
		Student student = new Student();
		CourseClass clas = new CourseClass();
		String date = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		int accessLevel = suds.getAuthUserAccessLevel();
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		if(accessLevel==1) {
			name = us.getCourseAdminByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			System.out.println(name);
			session.setAttribute("name", name);
			
			//Pending Leave Count 
			List<Leave> ll = new ArrayList<Leave>();
			ll.addAll(lrepo.findAll());
			int leavecount = 0;
			for (Leave l : ll) {
				if(l.getStatus().equals("pending"))
					leavecount++;
			}
			System.out.println(ll.size());
			System.out.println(leavecount);	
			//Pending Application Count 
			List<Application> al = new ArrayList<Application>();
			al.addAll(arepo.findAll());
			int appcount = 0;
			for (Application a : al) {
				if(a.getStatus().equals("pending"))
					appcount++;
			}
			System.out.println(al.size());
			System.out.println(appcount);	
			model.addAttribute("leavecount", leavecount);
			model.addAttribute("appcount", appcount);
			return "adminhome";
		}
		if(accessLevel==2) {
			name = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			session.setAttribute("name", name);
			return "lecturerhome";
		}
		if(accessLevel==3) {
			name = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername())).getFirstName();
			student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			
			if (! student.getCourseClassList().isEmpty()) {
				clas = student.getCourseClassList().get(student.getCourseClassList().size()-1);	
				System.out.println("Class Id is:");
				System.out.println(clas.getClassId());
			}else {
				clas.setClassId(0);
				System.out.println(clas.getClassId());
			}

			session.setAttribute("name", name);
			session.setAttribute("classid", clas.getClassId());
			session.setAttribute("date", date);

			return "studenthome";
		}
		return "redirect:/welcome";
	}
}

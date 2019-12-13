package sg.edu.nus.smsys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Service
public class CourseClassServiceImplement implements CourseClassService{
	@Autowired
	private CourseClassRepository ccRepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	private UserService us;
	
	public boolean canViewClass(int id) {
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		boolean output = false;
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			cclist = student.getCourseClassList();
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
		} else if (accesslevel == 1) {
			return true;
		}
		if (!cclist.isEmpty()) {
			for (CourseClass cc : cclist) {
				if(cc.getClassId()==id)
					return true;
			}
		}
		return output;
	}
	
	
	public List<CourseClass> getClassesByUser(){
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			cclist = student.getCourseClassList();
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
		} else if (accesslevel == 1) {
			cclist = ccRepo.findAll();;
		}
		return cclist;
	}
}

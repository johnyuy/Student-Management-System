package sg.edu.nus.smsys.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.GradeRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Service
public class StudentServiceImpl {
	
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private GradeRepository grepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	private UserService us;
	@Autowired
	private ApplicationService as;

	
	public float CalulateGPA(Student student, CourseClass cc) {
		ArrayList<Grade> gradelist = new ArrayList<Grade>();
		gradelist.addAll(grepo.findByStudentAndClas(student,cc));
		float gpa= sumOfGpa(gradelist)/sumOfCredits(gradelist);
		return gpa;	
	}
	
	public float sumOfGpa(ArrayList<Grade> gradelist) {
		float sumofgrades = (float) gradelist.stream().mapToDouble(g -> g.getGradeToGPA()*g.getSubject().getUnits()).sum();
		return sumofgrades;
	}
	
	public float sumOfCredits(ArrayList<Grade> subjectlist) {
		float sumofcredits = (float) subjectlist.stream().mapToDouble(g -> g.getSubject().getUnits()).sum();		
				return sumofcredits;
	}
	
	public List<Student> getStudentByUser() {
		List<Student> slist = new ArrayList<Student>();
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			cclist = student.getCourseClassList();
			for (CourseClass cc : cclist) {
				slist.addAll(cc.getStudentList());
			}
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
			for (CourseClass cc : cclist) {
				slist.addAll(cc.getStudentList());
			}
		} else if (accesslevel == 1) {
			slist.addAll(srepo.findAll());
		}
		return slist;
	}
	
	public boolean canViewStudent(int id) {
		List<CourseClass> cclist = new ArrayList<CourseClass>();
		List<Student> slist = new ArrayList<Student>();
		boolean output = false;
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// get student
			Student student = us.getStudentByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of enrolled classes
			if(student.getStudentId()== id) {
				return true;
			}

		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			cclist = lecturer.getClassList();
			for (CourseClass cc : cclist) {
				slist.addAll(cc.getStudentList());
			}
		} else if (accesslevel == 1) {
			return true;
		}
		if (!slist.isEmpty()) {
			for (Student s : slist) {
				if(s.getStudentId()==id)
					return true;
			}
		}
		return output;
	}

	public List<Student> getAcceptedStudents(Course course){
		List<Student> studentlist = new ArrayList<Student>();
		List<Application> applist = as.getAcceptedApplications(course);
		if(applist!=null) {
			if(applist.size()>0) {
				for(Application app: applist) {
					studentlist.add(app.getStudent());
				}
			}
		}
		
		return studentlist;
	}
}

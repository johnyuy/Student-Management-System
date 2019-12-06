package sg.edu.nus.smsys.cmdlr;

import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.*;
import sg.edu.nus.smsys.service.UserService;

@Component
@Order(2)
public class CmdRunner2 implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CmdRunner2.class);
	@Autowired
	UserService us;
	@Autowired
	GradeRepository grepo;
	@Autowired
	CourseRepository courepo;
	@Autowired
	SemesterRepository semrepo;
	@Autowired
	CourseClassRepository ccrepo;
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner2");    
        //Create user accounts
  		log.info("Adding user account for lecturer..");
  		
		try {
			us.registerNewAccount(50001, "password");
			us.registerNewAccount(50002, "admin");
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//Create grades
		log.info("Adding some sample grades..");
		grepo.save(new Grade(null, null, null, "A+"));
		grepo.save(new Grade(null, null, null, "B"));
        log.info("End of CmdRunner2"); 
        
        //Create Courses
        log.info("Adding some courses..");
        courepo.save(new Course("Graduate Diploma in Systems Analysis", "The Graduate Diploma in Systems Analysis programme (GDipSA) is designed for non-IT graduates intending to craft a new career path in the IT industry.", 100, 2, null, null));
        courepo.save(new Course("Master of Technology in Enterprise Business Analytics", "The NUS Master of Technology in Enterprise Business Analytics programme (MTech EBAC) is specifically designed to meet the industry demand for data scientists who can help organisations achieve improved business outcomes through data insights.", 50, 2, null, null));
        
        //Create Semesters
        log.info("Adding some semesters..");
        semrepo.save(new Semester(LocalDate.of(2019, 8, 1), LocalDate.of(2019, 12, 31), null));
        semrepo.save(new Semester(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 5, 31), null));
        semrepo.save(new Semester(LocalDate.of(2020, 8, 1), LocalDate.of(2020, 12, 31), null));
        semrepo.save(new Semester(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 5, 31), null));
        semrepo.save(new Semester(LocalDate.of(2021, 8, 1), LocalDate.of(2021, 12, 31), null));
        semrepo.save(new Semester(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 5, 31), null));
        
        //Create classes
        log.info("Adding some classes..");
        List<Semester> semlist = new ArrayList<Semester>();
        Semester sem = semrepo.findBySemCode("19/20/1");
        semlist.add(sem);
        sem = semrepo.findBySemCode("19/20/2");
        semlist.add(sem);
        CourseClass newclass = new CourseClass(courepo.findByCourseId(2000), 1, semlist, null, null, null, null);
        ccrepo.save(newclass);
    }
}

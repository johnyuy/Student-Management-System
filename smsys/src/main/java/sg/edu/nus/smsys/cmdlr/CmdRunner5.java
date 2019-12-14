package sg.edu.nus.smsys.cmdlr;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.repository.ApplicationRepository;
import sg.edu.nus.smsys.repository.CourseRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.service.UserService;

@Component
@Order(5)
public class CmdRunner5 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner5.class);
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private StudentRepository stuRepo;
	@Autowired
	private ApplicationRepository appRepo;
	@Override
    public void run(String[] args) {
		log.info("...");    
        log.info("In CmdRunner5");    
        log.info("Creating applications");
        Application app1 = new Application(courseRepo.findByCourseId(2000), stuRepo.findByStudentId(10025));
        app1.setStatus("accepted");
        
        Application app2 = new Application(courseRepo.findByCourseId(2000), stuRepo.findByStudentId(10026));
        app2.setStatus("enrolled");
        
        Application app3 = new Application(courseRepo.findByCourseId(2000), stuRepo.findByStudentId(10027));
        app3.setStatus("pending");
        
        Application app4 = new Application(courseRepo.findByCourseId(2001), stuRepo.findByStudentId(10027));
        app4.setStatus("accepted");
        
        appRepo.save(app1);
        appRepo.save(app2);
        appRepo.save(app3);
        appRepo.save(app4);
        
        for(int i=10001; i<=100010; i++) {
        	Application app2000 = new Application(courseRepo.findByCourseId(2000), stuRepo.findByStudentId(i)); app2000.setStatus("enrolled");
        	appRepo.save(app2000);
        }
        for(int i=10011; i<=10022; i++) {
        	Application app2001 = new Application(courseRepo.findByCourseId(2001), stuRepo.findByStudentId(i)); app2001.setStatus("enrolled");
        	appRepo.save(app2001);
        }
        log.info("End of CmdRunner4"); 
    }
}

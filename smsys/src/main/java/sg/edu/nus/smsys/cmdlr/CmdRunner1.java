package sg.edu.nus.smsys.cmdlr;

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

@Component
@Order(1)
public class CmdRunner1 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner1.class);
	@Autowired
	StudentRepository srepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	CourseAdminRepository carepo;
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner1:"); 

  		//Insert Course Admin
		log.info("Adding Course Admin..");
		CourseAdmin C1 = new CourseAdmin("Megan", "Susie", "Wang", "female", LocalDate.of(1982, 3, 23), "Mrs", "Thomson", "82334576", "meganSW@hotmail.com", "Available", 21, 21, null, null, null);
		carepo.save(C1);
  		
  		log.info("End of CmdRunner1:"); 
  		
    }
	
	
}

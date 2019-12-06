package sg.edu.nus.smsys.cmdlr;

import java.security.GeneralSecurityException;

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
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner2");    
        //Create user accounts
  		log.info("Adding user account for lecturer..");
  		try {
			us.registerNewAccount(50001, "password");
			us.registerNewAccount(50002, "admin");
		} catch (GeneralSecurityException e) {

		}
        
        log.info("End of CmdRunner2"); 
    }
}

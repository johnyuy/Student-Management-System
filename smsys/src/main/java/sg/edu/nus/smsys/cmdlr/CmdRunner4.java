package sg.edu.nus.smsys.cmdlr;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.service.UserService;

@Component
@Order(4)
public class CmdRunner4 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner4.class);
	@Autowired
	UserService us;
	@Override
    public void run(String[] args) {
		
        log.info("In CmdRunner4");    
        log.info("Creating user acccounts");
		// Create user account
		try {
			//admin
			us.registerNewAccount(50001, "admin");
			//lecturer
			us.registerNewAccount(50002, "pass");
			us.registerNewAccount(50003, "pass");
			us.registerNewAccount(50004, "pass");
			//students in gdipsa
			us.registerNewAccount(10001, "123");
			us.registerNewAccount(10002, "123");
			us.registerNewAccount(10003, "123");
			us.registerNewAccount(10004, "123");
			us.registerNewAccount(10005, "123");
			//students in masters prog
			us.registerNewAccount(10011, "123");
			us.registerNewAccount(10012, "123");
			us.registerNewAccount(10013, "123");
			us.registerNewAccount(10014, "123");
			us.registerNewAccount(10015, "123");
			//students without accounts
			us.registerNewAccount(10023, "123");
			us.registerNewAccount(10024, "123");
			us.registerNewAccount(10025, "123");
			
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        log.info("End of CmdRunner4"); 
    }
}

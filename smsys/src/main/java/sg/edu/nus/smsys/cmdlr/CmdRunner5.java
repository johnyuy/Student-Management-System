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
@Order(5)
public class CmdRunner5 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner5.class);
	
	@Override
    public void run(String[] args) {
		log.info("...");    
        log.info("In CmdRunner5");    
        log.info("Creating applications");
        

        
        log.info("End of CmdRunner4"); 
    }
}

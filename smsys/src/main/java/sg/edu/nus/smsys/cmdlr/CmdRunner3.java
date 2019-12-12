package sg.edu.nus.smsys.cmdlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.service.UserService;

@Component
@Order(3)
public class CmdRunner3 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner3.class);
	@Autowired
	UserService us;
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner3");    
        log.info("Testing log in...");
        log.info("End of CmdRunner3"); 
    }
}

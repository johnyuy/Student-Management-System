package sg.edu.nus.smsys.cmdlr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CmdRunner4 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner4.class);
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner4");    
        
        
        log.info("End of CmdRunner4"); 
    }
}

package sg.edu.nus.smsys;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import sg.edu.nus.smsys.repository.*;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SmsysApplication {
	private static final Logger log = LoggerFactory.getLogger(SmsysApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(SmsysApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			log.info("END OF INITIALIZATION");
		};
	}
	
	@Scheduled(fixedRate = 1000)
	public void printTime() {
		log.info("Scheculer Time Check = " + LocalDateTime.now().toString());
	    try {
	        TimeUnit.SECONDS.sleep(10);
	    } catch (InterruptedException ex) {
	        log.info("Ran into an error : "+  ex);
	        log.info("APPLICATION ENDED");
	        //throw new IllegalStateException(ex);
	    } finally
	    {
	    	
	    }
	}
}
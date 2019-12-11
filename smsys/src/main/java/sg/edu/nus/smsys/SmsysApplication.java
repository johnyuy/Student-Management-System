package sg.edu.nus.smsys;

import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.*;
import sg.edu.nus.smsys.service.UserService;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SmsysApplication {
	private static final Logger log = LoggerFactory.getLogger(SmsysApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(SmsysApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(LecturerRepository lrepo, StudentRepository Srepo, CourseAdminRepository Crepo,
			CourseRepository Csrepo, UserService us) {

		return (args) -> {
			log.info("END OF INITIALIZATION");
		};
	}
	
	@Scheduled(fixedRate = 1000)
	public void printTime() {
		log.info("Fixed Delay Task :: Execution Time - " + LocalDateTime.now().toString());
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
package sg.edu.nus.smsys;

import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.*;
import sg.edu.nus.smsys.service.UserService;

@SpringBootApplication
public class SmsysApplication {
	private static final Logger log = LoggerFactory.getLogger(SmsysApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SmsysApplication.class, args);
		
	}

@Bean
public CommandLineRunner demo(LecturerRepository lrepo, StudentRepository Srepo, CourseAdminRepository Crepo, CourseRepository Csrepo, UserService us) {

	return (args) -> {
		log.info("END OF PROGRAM");
	};
}
	

}
;
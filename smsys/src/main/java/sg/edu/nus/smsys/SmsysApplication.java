package sg.edu.nus.smsys;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.*;

@SpringBootApplication
public class SmsysApplication {
	private static final Logger log = LoggerFactory.getLogger(SmsysApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SmsysApplication.class, args);
		Lecturer l1 = new Lecturer("Apple", "Beetroot", "Carrot", "male", LocalDate.of(2000, 1, 1),
				"Mr", "Tampines", "123", "gg@g.com", "a-status", 24,
				23, null, null, null, null);
		System.out.println(l1);
	}

	@Bean
	public CommandLineRunner demo(LecturerRepository lrepo) {
		return (args) -> {
			log.info("saving.......");
			Lecturer l1 = new Lecturer("Apple", "Beetroot", "Carrot", "male", LocalDate.of(1992, 1, 1),
					"Mr", "Tampines", "123", "gg@g.com", "a-status", 24,
					23, null, null, null, null);
			lrepo.save(l1);
			log.info("Code ends here");
		};
	}
}
;
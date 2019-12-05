package sg.edu.nus.smsys;

import java.time.LocalDate;
import java.util.ArrayList;
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
	}

	@Bean
	public CommandLineRunner demo(LecturerRepository lrepo, StudentRepository Srepo) {
		return (args) -> {
			log.info("Entering data..");
			Lecturer l1 = new Lecturer("Joel", "Nathan", "Thomas", "male", LocalDate.of(1992, 1, 1),
					"Mr", "Serangoon", "96125552", "gg@g.com", "available", 24,
					24, null, null, null, null);
			lrepo.save(l1);
			List<Student> initialStudents = new ArrayList<Student>();
			initialStudents.add(new Student("John", "Yue", "Yu", "male", LocalDate.of(1992, 11, 16), "Mr", "Tampines", "98076988", "yu.john92@gmail.com", "enrolled", 3.5f, null));
			initialStudents.add(new Student("Natalie", "Si Min", "Hong", "female", LocalDate.of(1989, 05, 2), "Ms", "Bukit Panjang", "90019910", "natalie.hong@gmail.com", "Enrolled", 4.2f , null));
			initialStudents.add(new Student("Johann", "Cheok Arn", "Fong", "male", LocalDate.of(1992, 11, 7), "Mr", "Pasir Ris", "98981123", "treedays@gmail.com", "Enrolled", 4.5f , null));
			initialStudents.add(new Student("Drake", "Lin Htet", "Ye", "male", LocalDate.of(1996, 4, 8), "Mr", "Keppel Bay", "84524324", "drakelin21@gmail.com", "Enrolled", 4.1f , null));
			initialStudents.add(new Student("Ettiyan", "", "Premalatha", "female", LocalDate.of(1990, 1, 10), "Ms", "Clementi", "87654321", "e.premalatha@gmail.com", "Enrolled", 4.2f , null));
			initialStudents.add(new Student("Frank", "Minhao", "Liu", "male", LocalDate.of(1996, 3, 16), "Mr", "Clementi", "83443543", "frankLiu@qq.com", "Enrolled", 4.9f , null));
			initialStudents.add(new Student("Gail", "Yazhi", "Jiang", "female", LocalDate.of(1994, 12, 1), "Ms", "Jurong", "98076988", "gailJiang@qq.com", "Enrolled", 5.0f , null));
			for(Student s : initialStudents) {
				Srepo.save(s);
			}
			for(Student s : initialStudents) {
				Srepo.save(s);
			}
			log.info("Code ends here");
			
		};
	}
	
}
;
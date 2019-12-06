package sg.edu.nus.smsys.cmdlr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.*;

@Component
@Order(1)
public class CmdRunner1 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner1.class);
	@Autowired
	StudentRepository srepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	CourseAdminRepository carepo;
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner1:"); 
      	//Insert Lecturer
  		log.info("Adding Lecturer..");
  		Lecturer l1 = new Lecturer("Daniel", "Edward", "Foo", "Male", LocalDate.of(1973, 2, 14), "Dr", "Holland Village", "93336566", "danielEF@hotmail.com", "Available", 21, 21, null, null, null, null, null);
  		lrepo.save(l1);
        //Insert Students
  		log.info("Adding Students..");
  		List<Student> initialStudents = new ArrayList<Student>();
  		initialStudents.add(new Student("John", "Yue", "Yu", "male", LocalDate.of(1992, 11, 16), "Mr", "Tampines", "98076988", "yu.john92@gmail.com", "enrolled", 3.5f, null));
  		initialStudents.add(new Student("Natalie", "Si Min", "Hong", "female", LocalDate.of(1989, 05, 2), "Ms", "Bukit Panjang", "90019910", "natalie.hong@gmail.com", "Enrolled", 4.2f , null));
  		initialStudents.add(new Student("Johann", "Cheok Arn", "Fong", "male", LocalDate.of(1992, 11, 7), "Mr", "Pasir Ris", "98981123", "treedays@gmail.com", "Enrolled", 4.5f , null));
  		initialStudents.add(new Student("Drake", "Lin Htet", "Ye", "male", LocalDate.of(1996, 4, 8), "Mr", "Keppel Bay", "84524324", "drakelin21@gmail.com", "Enrolled", 4.1f , null));
  		initialStudents.add(new Student("Ettiyan", "", "Premalatha", "female", LocalDate.of(1990, 1, 10), "Ms", "Clementi", "87654321", "e.premalatha@gmail.com", "Enrolled", 4.2f , null));
  		initialStudents.add(new Student("Frank", "Minhao", "Liu", "male", LocalDate.of(1996, 3, 16), "Mr", "Clementi", "83443543", "frankLiu@qq.com", "Enrolled", 4.9f , null));
  		initialStudents.add(new Student("Gail", "Yazhi", "Jiang", "female", LocalDate.of(1994, 12, 1), "Ms", "Jurong", "98076988", "gailJiang@qq.com", "Enrolled", 5.0f , null));
  		for(Student s : initialStudents) {
  			srepo.save(s);
  		}
  		//Insert Course Admin
		log.info("Adding Course Admin..");
		CourseAdmin C1 = new CourseAdmin("Megan", "Susie", "Wang", "Female", LocalDate.of(1982, 3, 23), "Mrs", "Thomson", "82334576", "meganSW@hotmail.com", "Available", 21, 21, null, null, null);
		carepo.save(C1);
  		
  		log.info("End of CmdRunner1:"); 
  		
    }
	
	
}

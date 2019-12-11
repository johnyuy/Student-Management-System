package sg.edu.nus.smsys.cmdlr;

import java.security.GeneralSecurityException;
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
import sg.edu.nus.smsys.service.UserService;

@Component
@Order(2)
public class CmdRunner2 implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CmdRunner2.class);
	@Autowired
	UserService us;
	@Autowired
	GradeRepository grepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	CourseRepository courepo;
	@Autowired
	SemesterRepository semrepo;
	@Autowired
	CourseClassRepository ccrepo;
	@Autowired
	SubjectRepository subrepo;

	@Override
	public void run(String[] args) {

		// Insert Lecturer
		log.info("Adding Lecturer..");
		Lecturer l1 = new Lecturer("Daniel", "Edward", "Foo", "male", LocalDate.of(1972, 02, 4), "Dr",
				"Holland Village", "93336566", "danielEF@hotmail.com", "Available", 21, 21, null, null, null, null,
				null, null);
		Lecturer l2 = new Lecturer("Danny", "Kim", "DimSum", "female", LocalDate.of(1972, 01, 21), "Dr",
				"Haw Par Villa", "91336506", "dimsumDA@hotmail.com", "Available", 21, 21, null, null, null, null,
				null, null);
		Lecturer l3 = new Lecturer("Boon", "Kom", "Tum", "male", LocalDate.of(1962, 11, 11), "Dr",
				"Chinatown", "91346645", "chinaboi@hotmail.com", "Available", 21, 21, null, null, null, null,
				null, null);
		lrepo.save(l1);
		lrepo.save(l2);
		lrepo.save(l3);
		// Insert Students
		log.info("Adding Students..");
		List<Student> initialStudents = new ArrayList<Student>();
		initialStudents.add(new Student("John ", "Yue", "Yu", "male", LocalDate.of(1992, 11, 16), "Mr", "Tampines", "98076988", "yu.john92@gmail.com", "enrolled", 3.5f, null, null, null));
		initialStudents.add(new Student("Natalie", "Si Min", "Hong", "female", LocalDate.of(1989, 05, 02), "Ms",
				"Bukit Panjang", "90019910", "natalie.hong@gmail.com", "Enrolled", 4.2f, null, null, null));
		initialStudents.add(new Student("Johann", "Cheok Arn", "Fong", "male", LocalDate.of(1992, 11, 07), "Mr",
				"Pasir Ris", "98981123", "treedays@gmail.com", "Enrolled", 4.5f, null, null,null));
		initialStudents.add(new Student("Drake", "Lin Htet", "Ye", "male", LocalDate.of(1996, 04, 8), "Mr",
				"Keppel Bay", "84524324", "drakelin21@gmail.com", "Enrolled", 4.1f, null,null,null));
		initialStudents.add(new Student("Ettiyan", "", "Premalatha", "female", LocalDate.of(1990, 01, 04), "Ms",
				"Clementi", "87654321", "e.premalatha@gmail.com", "Enrolled", 4.2f, null,null,null));
		initialStudents.add(new Student("Frank", "Minhao", "Liu", "male", LocalDate.of(1996, 3, 16), "Mr", "Clementi",
				"83443543", "frankLiu@qq.com", "Enrolled", 4.9f, null,null,null));
		initialStudents.add(new Student("Gail", "Yazhi", "Jiang", "female", LocalDate.of(1994, 12, 1), "Ms", "Jurong",
				"98076988", "gailJiang@qq.com", "Enrolled", 5.0f, null,null,null));
		for (Student s : initialStudents) {
			srepo.save(s);
		}

		log.info("In CmdRunner2");
		// Create user accounts
		log.info("Adding user account for lecturer..");

		try {
			us.registerNewAccount(50001, "password");
			us.registerNewAccount(50002, "admin");
			us.registerNewAccount(50003, "l2");
			us.registerNewAccount(50004, "l3");
			
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create Subjects
		log.info("Adding some subjects..");
		ArrayList<Subject> subjectlist = new ArrayList<Subject>();
		Subject subject1 = new Subject("Foundations",
				"This unit lays the foundation for programming and developing of system of records. This course will cover programming concepts using the C# Language as the vehicle. You will learn about Object Oriented Programming and acquire the technical knowledge necessary for developing a User Interface for Business Systems using Visual Studio.Net. The curriculum also covers the implementation of client/server development on .NET data objects with Visual Studio as the client side programming tool, ADO.NET as the enabling middleware, and RDBMS as the database server. It will also include the development web application using ASP.NET MVC.",
				5);
		Subject subject2 = new Subject("Design",
				"This course will cover the Application Development Life Cycle using object oriented development technique with UML. You will learn how to understand users needs using user experience design techniques and user requirement gathering tools. You will also acquire skills to analyse and design IT solutions that address business problems. You will also acquire basic IT project management skills based on Agile practices.",
				5);
		Subject subject3 = new Subject("Web Applications",
				"The course imparts the techniques and engineering skills needed for the end-to-end design, architecture, implementation, persistence and testing an enterprise web application. In this module, you will learn how to develop system of engagement using Java and JS components. You will also learn how to apply these newly acquired proficiencies by developing full stack web application project using Java Enterprise Edition (Java EE) for the Server Side and Java Script for the Client Side Libraries. The course covers a wide range of design concepts, development abilities, and integration skills, from analysing the requirements to implementing a complete solution.",
				5);
		Subject subject4 = new Subject("Mobility Applications",
				"In this module, you will learn how to develop system of insights using Python with machine learning as well as native mobile apps using the Android Development Platform. You will attain skills in publishing server-side functionality as REST API with Python, adopt popular machine learning models such as k-nearest neighbors, random forest, logistic regression, k-means, naïve Bayes and artificial neural network. You will also build and evaluate performance of machine learning models using Python. You will develop and package Android application using Android Studio and integrate Android application with server-side functionality for full stack development",
				5);
		Subject subject5 = new Subject("Web Applications",
				"This module includes the capstone project that links up all the concepts taught in the NUS-ISS Certificate in Digital Solutions Development – Foundations, Design, Web Applications and Mobility Applications. It also includes an internship programme where you will develop and propose IT solutions for your assigned internship company.",
				5);

		subjectlist.add(subject1);
		subjectlist.add(subject2);
		subjectlist.add(subject3);
		subjectlist.add(subject4);
		subjectlist.add(subject5);
		subrepo.saveAll(subjectlist);

		// Create grades
		log.info("Adding some sample grades..");
		grepo.save(new Grade(null, null, null, "A+"));
		grepo.save(new Grade(null, null, null, "B"));

		// Create Courses
		log.info("Adding some courses..");
		Course course1 = new Course("Graduate Diploma in Systems Analysis",
				"The Graduate Diploma in Systems Analysis programme (GDipSA) is designed for non-IT graduates intending to craft a new career path in the IT industry.",
				"Open", 100, 2, subjectlist, null);
		Course course2 = new Course("Master of Technology in Enterprise Business Analytics",
				"The NUS Master of Technology in Enterprise Business Analytics programme (MTech EBAC) is specifically designed to meet the industry demand for data scientists who can help organisations achieve improved business outcomes through data insights.",
				"Open", 50, 2, null, null);
		courepo.save(course1);
		courepo.save(course2);

		// Create Semesters
		log.info("Adding some semesters..");
		semrepo.save(new Semester(LocalDate.of(2019, 8, 1).toString(), LocalDate.of(2019, 12, 31).toString(), null));
		semrepo.save(new Semester(LocalDate.of(2020, 1, 1).toString(), LocalDate.of(2020, 5, 31).toString(), null));
		semrepo.save(new Semester(LocalDate.of(2020, 8, 1).toString(), LocalDate.of(2020, 12, 31).toString(), null));
		semrepo.save(new Semester(LocalDate.of(2021, 1, 1).toString(), LocalDate.of(2021, 5, 31).toString(), null));
		semrepo.save(new Semester(LocalDate.of(2021, 8, 1).toString(), LocalDate.of(2021, 12, 31).toString(), null));
		semrepo.save(new Semester(LocalDate.of(2022, 1, 1).toString(), LocalDate.of(2022, 5, 31).toString(), null));

		// Create classes
		log.info("Adding some classes..");
		List<Semester> semlist = new ArrayList<Semester>();
		Semester sem = semrepo.findBySemCode("19/20/1");
		semlist.add(sem);
		sem = semrepo.findBySemCode("19/20/2");
		semlist.add(sem);
		ccrepo.save(new CourseClass(courepo.findByCourseId(2000), 0, semlist, srepo.findAll(), null, null, null));
		ccrepo.save(new CourseClass(courepo.findByCourseId(2001), 0, semlist, null, null, null, null));
		ccrepo.save(new CourseClass(courepo.findByCourseId(2001), 0, semlist, null, null, null, null));
		

		
		log.info("End of CmdRunner2");

	}
}

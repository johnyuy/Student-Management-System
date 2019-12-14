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
		
		ArrayList<Subject> subjectlist2 = new ArrayList<Subject>();
		
		Subject subject6 = new Subject("Analytics Project Management and Delivery", "Students will be equipped with practice-oriented data analytics skills and knowledge in managing analytics project.", 10);
		Subject subject7 = new Subject("Core Analytics Techniques", "Students will learn the foundation skills to understand, design and solve analytics problems in the industry involving structured and unstructured data.", 10);
		Subject subject8 = new Subject("Customer Analytics", "Students will be equipped with the skills to manage the customer data and build analytics solutions for customer relationship management.", 10);
		Subject subject9 = new Subject("Big Data Processing", "Students will learn various aspects of data engineering while building resilient distributed datasets. Participants will learn to apply key practices, identify multiple data sources appraised against their business value, design the right storage, and implement proper access model(s).", 10);
		Subject subject10 = new Subject("Practical Language Processing", "Students will be taught advanced skills in practical language processing. This includes fundamental text processing, text analytics, deep learning techniques and their application in sentiment mining and chatbots development.", 10);
		Subject subject11 = new Subject("Advanced Predictive Modelling Techniques", "Students will be taught the advanced concepts of predictive modeling and Time Series Forecasting and their application in few special areas like Health Care & Service Industry in addition to other domains like Public Services, IT Services, Finance, Airlines, Logistics, Transport, Hotel & Tourism Industries.", 10);

		subjectlist2.add(subject6);
		subjectlist2.add(subject7);
		subjectlist2.add(subject8);
		subjectlist2.add(subject9);
		subjectlist2.add(subject10);
		subjectlist2.add(subject11);
		subrepo.saveAll(subjectlist2);



		// Insert Lecturer
		log.info("Adding Lecturer..");
		Lecturer l1 = new Lecturer("Daniel", "Edward", "Foo", "male", LocalDate.of(1972, 02, 4), "Dr",
				"Holland Village", "93336566", "danielEF@hotmail.com", "Available", 21, 21, null, null, null, subjectlist,
				null, null);
		Lecturer l2 = new Lecturer("Danny", "Kim", "DimSum", "female", LocalDate.of(1972, 01, 21), "Dr",
				"Haw Par Villa", "91336506", "dimsumDA@hotmail.com", "Available", 21, 21, null, null, null, subjectlist,
				null, null);
		Lecturer l3 = new Lecturer("Boon", "Kom", "Tum", "male", LocalDate.of(1962, 11, 11), "Dr",
				"Chinatown", "91346645", "chinaboi@hotmail.com", "Available", 21, 21, null, null, null, subjectlist,
				null, null);
		lrepo.save(l1);
		lrepo.save(l2);
		lrepo.save(l3);
		// Insert Students
		log.info("Adding Students..");
		
		List<Student> initialStudents = new ArrayList<Student>();
		
		initialStudents.add(new Student("John ", "Yue", "Yu", "male", LocalDate.of(1992, 11, 16), "Mr", "Tampines", "98076988", "yu.john92@gmail.com", "enrolled",  null, null, null));
		initialStudents.add(new Student("Natalie", "Si Min", "Hong", "female", LocalDate.of(1989, 05, 02), "Ms",
				"Bukit Panjang", "90019910", "natalie.hong@gmail.com", "Enrolled",  null, null, null));
		initialStudents.add(new Student("Johann", "Cheok Arn", "Fong", "male", LocalDate.of(1992, 11, 07), "Mr",
				"Pasir Ris", "98981123", "treedays@gmail.com", "Enrolled",  null, null,null));
		initialStudents.add(new Student("Drake", "Lin Htet", "Ye", "male", LocalDate.of(1996, 04, 8), "Mr",
				"Keppel Bay", "84524324", "drakelin21@gmail.com", "Enrolled", null,null,null));
		initialStudents.add(new Student("Ettiyan", "", "Premalatha", "female", LocalDate.of(1990, 01, 04), "Ms",
				"Clementi", "87654321", "e.premalatha@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Frank", "Minhao", "Liu", "male", LocalDate.of(1996, 3, 16), "Mr", "Clementi",
				"83443543", "frankLiu@qq.com", "Enrolled", null,null,null));
		initialStudents.add(new Student("Gail", "Yazhi", "Jiang", "female", LocalDate.of(1994, 12, 1), "Ms", "Jurong",
				"98076988", "gailJiang@qq.com", "Enrolled",  null,null,null));
		
		initialStudents.add(new Student("Jennifer", "", "Aniston", "female", LocalDate.of(1993, 2, 1), "Ms", "Tampines",
				"89884276", "jenniferan@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Courteney", "Monica", "Geller", "female", LocalDate.of(1995, 1, 1), "Ms", "West Coast",
				"95687438", "ccmonica@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Lisa", "Phoeba", "Buffay", "female", LocalDate.of(1991, 12, 11), "Ms", "East Coast",
				"99882341", "lisa1234@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Matt", "Joey", "Tribbiani", "male", LocalDate.of(1992, 9, 4), "Mr", "Queens Town",
				"80780983", "mattjoey@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Perry", "Chandler", "Bing", "male", LocalDate.of(1990, 8, 15), "Mr", "Keppel Bay",
				"86981432", "perrypenny@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("David", "Ross", "Geller", "male", LocalDate.of(1994, 7, 12), "Mr", "Novena",
				"98885631", "davidross@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Anita", "Carol", "Willick", "female", LocalDate.of(1996, 9, 21), "Ms", "Paya Lebar",
				"98124254", "anitawillick@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Maggie", "Janice", "Hosenstein", "female", LocalDate.of(1997, 6, 12), "Ms", "Seletar",
				"80766345", "maggiejanice@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Jessica", "Susan", "Bunch", "male", LocalDate.of(1994, 2, 18), "Mr", "Changi",
				"98071238", "jessicabunch@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("James", "Michael", "Tyler", "male", LocalDate.of(1995, 11, 11), "Mr", "Clementi",
				"81296076", "jamestyler@gmail.com", "Enrolled",  null,null,null));
		
		initialStudents.add(new Student("Phoebe", "Waller", "Bridge", "female", LocalDate.of(1991, 10, 21), "Ms", "Mandai",
				"95223438", "phoebewaller@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Sian", "Clifford", "Bridge", "female", LocalDate.of(1990, 12, 13), "Ms", "Ang Mo Kio",
				"99105437", "sian9012@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Olivia", "", "Colman", "female", LocalDate.of(1992, 9, 15), "Ms", "Bishan",
				"94385687", "oliviacolman@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Bill", "", "Paterson", "male", LocalDate.of(1994, 7, 4), "Mr", "Toa Payoh",
				"85386758", "bill9474@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Brett", "Martin", "Gelman", "male", LocalDate.of(1993, 2, 20), "Mr", "Orchard",
				"83800981", "brettgelman@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Hugh", "Harry", "Skinner", "male", LocalDate.of(1988, 3, 28), "Mr", "Outram",
				"83854390", "hughhs@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Hugh", "Ben", "Dennis", "male", LocalDate.of(1989, 8, 16), "Mr", "Clarke Quay",
				"97481244", "hugnben89@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Jamie", "", "Demetriou", "female", LocalDate.of(1994, 7, 30), "Ms", "Bukit Merah",
				"85344009", "jamie9407@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Andrew", "Shaw", "Scott", "male", LocalDate.of(1990, 11, 19), "Mr", "Kallang",
				"95633541", "andrewshaw@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Fiona", "Ray", "Fearon", "female", LocalDate.of(1995, 10, 22), "Ms", "Sengkang",
				"85740091", "fionaray@gmail.com", "Enrolled",  null,null,null));
		
		initialStudents.add(new Student("Anne", "", "Hathaway", "female", LocalDate.of(1991, 4, 3), "Ms", "Hougang",
				"85091332", "anne910403y@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Tina", "Sarah", "Fey", "female", LocalDate.of(1992, 1, 2), "Ms", "Bedok",
				"95740987", "tinafay@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Dey", "Joshua", "Patel", "male", LocalDate.of(1989, 6, 5), "Mr", "Toa Payoh",
				"89176919", "deypatel@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("John", "Dennis", "Slattery", "male", LocalDate.of(1990, 9, 17), "Mr", "Jurong West",
				"95916687", "johnsla@gmail.com", "Enrolled",  null,null,null));
		initialStudents.add(new Student("Julia", "Maddy", "Garner", "female", LocalDate.of(1995, 8, 29), "Ms", "Bukit Timah",
				"81294062", "juliagarner@gmail.com", "Enrolled",  null,null,null));
		
		
		
		
		for (Student s : initialStudents) {
			srepo.save(s);
		}

		log.info("In CmdRunner2");
		// Create user account
		try {
			//admin
			us.registerNewAccount(50001, "admin");
			//lecturer
			us.registerNewAccount(50002, "pass");
			us.registerNewAccount(50003, "pass");
			us.registerNewAccount(50004, "pass");
			//students in gdipsa
			us.registerNewAccount(10001, "123");
			us.registerNewAccount(10002, "123");
			us.registerNewAccount(10003, "123");
			us.registerNewAccount(10004, "123");
			us.registerNewAccount(10005, "123");
			//students in masters prog
			us.registerNewAccount(10011, "123");
			us.registerNewAccount(10012, "123");
			us.registerNewAccount(10013, "123");
			us.registerNewAccount(10014, "123");
			us.registerNewAccount(10015, "123");
			//students without accounts
			us.registerNewAccount(10023, "123");
			us.registerNewAccount(10024, "123");
			us.registerNewAccount(10025, "123");

			

			
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create Courses
		log.info("Adding some courses..");
		
		Course course1 = new Course("Graduate Diploma in Systems Analysis", "The Graduate Diploma in Systems Analysis programme (GDipSA) is designed for non-IT graduates intending to craft a new career path in the IT industry.","Open", 100, 2, subjectlist, null);
		Course course2 = new Course("Master of Technology in Enterprise Business Analytics", "The NUS Master of Technology in Enterprise Business Analytics programme (MTech EBAC) is specifically designed to meet the industry demand for data scientists who can help organisations achieve improved business outcomes through data insights.", "Open", 50, 2, subjectlist2, null);

		courepo.save(course1);
		courepo.save(course2);

		// Create Semesters
		log.info("Adding some semesters..");
		semrepo.save(new Semester(LocalDate.of(2019, 8, 1), LocalDate.of(2019, 12, 31), null));
		semrepo.save(new Semester(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 5, 31), null));
		semrepo.save(new Semester(LocalDate.of(2020, 8, 1), LocalDate.of(2020, 12, 31), null));
		semrepo.save(new Semester(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 5, 31), null));
		semrepo.save(new Semester(LocalDate.of(2021, 8, 1), LocalDate.of(2021, 12, 31), null));
		semrepo.save(new Semester(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 5, 31), null));
		semrepo.save(new Semester(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 12, 31), null));
		semrepo.save(new Semester(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 5, 31), null));

		// Create classes
		log.info("Adding some classes..");
		List<Semester> semlist1 = new ArrayList<Semester>();
		Semester sem = semrepo.findBySemCode("19/20-S2");
		semlist1.add(sem);
		sem = semrepo.findBySemCode("20/21-S1");
		semlist1.add(sem);
		List<Semester> semlist2 = new ArrayList<Semester>();
		sem = semrepo.findBySemCode("20/21-S1");
		semlist2.add(sem);
		sem = semrepo.findBySemCode("20/21-S2");
		semlist2.add(sem);
		List<Semester> semlist3 = new ArrayList<Semester>();
		sem = semrepo.findBySemCode("20/21-S2");
		semlist3.add(sem);
		sem = semrepo.findBySemCode("21/22-S1");

		
		List<Student> studentlist1 = new ArrayList<Student>();
		for(int i = 10001; i<=10010; i ++){
			studentlist1.add(srepo.findByStudentId(i));
		}
		List<Student> studentlist2 = new ArrayList<Student>();
		for(int i = 10011; i<=10022; i ++){
			studentlist2.add(srepo.findByStudentId(i));
		}
		
		
		//Create course classes
		ccrepo.save(new CourseClass(courepo.findByCourseId(2000), 0, semlist1, studentlist1, null, null, null));
		//ccrepo.save(new CourseClass(courepo.findByCourseId(2001), 0, semlist2, srepo.findByStudentFullNameLike("Johann"), null, null, null));
		ccrepo.save(new CourseClass(courepo.findByCourseId(2001), 0, semlist1, studentlist2, null, null, null));
		

		
		// Create grades
		log.info("Adding some sample grades..");
		
		// Student 10001 grades
		grepo.save(new Grade(srepo.findByStudentId(10001), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10001), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10001), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10001), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "A+"));
		grepo.save(new Grade(srepo.findByStudentId(10001), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "A+"));
		
		// Student 10002 grades
		grepo.save(new Grade(srepo.findByStudentId(10002), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10002), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10002), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10002), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10002), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "A"));
		
		// Student 10003 grades
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "B"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "B"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "B"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1001), subrepo.findBySubjectId(6), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10003), ccrepo.findByClassId(1001), subrepo.findBySubjectId(7), "B"));

		
		// Student 10004  grades
		grepo.save(new Grade(srepo.findByStudentId(10004), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "C"));
		grepo.save(new Grade(srepo.findByStudentId(10004), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10004), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10004), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10004), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "C"));
		
		// Student 10005  grades
		grepo.save(new Grade(srepo.findByStudentId(10005), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10005), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10005), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10005), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "D"));
		grepo.save(new Grade(srepo.findByStudentId(10005), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "D"));

		// Student 10006  grades
		grepo.save(new Grade(srepo.findByStudentId(10006), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "D"));
		grepo.save(new Grade(srepo.findByStudentId(10006), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10006), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10006), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "C"));
		grepo.save(new Grade(srepo.findByStudentId(10006), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "D"));

		// Student 10007  grades
		grepo.save(new Grade(srepo.findByStudentId(10007), ccrepo.findByClassId(1000), subrepo.findBySubjectId(1), "D"));
		grepo.save(new Grade(srepo.findByStudentId(10007), ccrepo.findByClassId(1000), subrepo.findBySubjectId(2), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10007), ccrepo.findByClassId(1000), subrepo.findBySubjectId(3), "A"));
		grepo.save(new Grade(srepo.findByStudentId(10007), ccrepo.findByClassId(1000), subrepo.findBySubjectId(4), "C"));
		grepo.save(new Grade(srepo.findByStudentId(10007), ccrepo.findByClassId(1000), subrepo.findBySubjectId(5), "D"));

		
		log.info("End of CmdRunner2");

	}
}

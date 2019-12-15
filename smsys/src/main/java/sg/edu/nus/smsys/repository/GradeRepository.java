package sg.edu.nus.smsys.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Grade;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	
	List<Grade> findByStudent(Student student);

	List<Grade> findByStudentAndClas(Student student, CourseClass cc);	
	
	List<Grade> findByStudentAndSubject(Student student, Subject subject);
	
	List<Grade> findByStudentAndSubjectAndClas(Student student, Subject subject, CourseClass clas);

}

package sg.edu.nus.smsys.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

	List<Subject> findBySubjectNameContaining(String name);
	
	Subject findBySubjectId(int id);

	List<Subject> findByLecturerListContaining(Lecturer lecturer);


}

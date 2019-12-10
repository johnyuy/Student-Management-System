package sg.edu.nus.smsys.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

	List<Subject> findBySubjectNameContaining(String name);
	
	Subject findBySubjectId(int id);

}

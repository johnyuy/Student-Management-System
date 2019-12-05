package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}

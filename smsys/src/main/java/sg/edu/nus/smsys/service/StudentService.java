package sg.edu.nus.smsys.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.smsys.models.Student;

public interface StudentService {

	List<Student> findByFirstNameContaining(String firstName);
	List<Student> findByMiddleNameContaining(String name);
	List<Student> findByLastNameContaining(String name);
	List<Student> findByEmailContaining(String name);
	Student findByStudentId(int id);

	
	@Query("select s from Student s where s.firstName like %?1% or s.middleName "
			+ "like %?1% or s.lastName like %?1% or s.email like %?1%  ")
	List<Student> findByStudentFullNameLike(String name);
	
}

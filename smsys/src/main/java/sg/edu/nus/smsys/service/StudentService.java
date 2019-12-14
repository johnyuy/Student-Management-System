package sg.edu.nus.smsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.Student;

public interface StudentService {
	@Autowired
	public boolean canViewClass(int id);
	@Autowired
	List<Student> findByFirstNameContaining(String firstName);
	@Autowired
	List<Student> findByMiddleNameContaining(String name);
	@Autowired
	List<Student> findByLastNameContaining(String name);
	@Autowired
	List<Student> findByEmailContaining(String name);
	@Autowired
	Student findByStudentId(int id);
	@Autowired
	public List<Student> getStudentsByApplicationStatus(Course course, String status);
	@Autowired
	@Query("select s from Student s where s.firstName like %?1% or s.middleName "
			+ "like %?1% or s.lastName like %?1% or s.email like %?1%  ")
	List<Student> findByStudentFullNameLike(String name);
	
}

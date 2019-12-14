package sg.edu.nus.smsys.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Application;
import sg.edu.nus.smsys.models.Student;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	public List<Application> findByStatus(String status);
	public Application findByApplicationId(int applicationId);
	public List<Application> findByStudent(Student student);
}

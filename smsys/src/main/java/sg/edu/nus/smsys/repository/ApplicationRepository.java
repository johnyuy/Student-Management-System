package sg.edu.nus.smsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	public List<Application> findByStatus(String status);
}

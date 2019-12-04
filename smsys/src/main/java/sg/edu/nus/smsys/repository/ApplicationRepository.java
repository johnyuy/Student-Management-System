package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}

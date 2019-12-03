package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Leave;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {

}

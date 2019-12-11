package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {


	
}

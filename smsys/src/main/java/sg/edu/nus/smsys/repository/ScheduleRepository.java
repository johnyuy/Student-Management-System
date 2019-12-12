package sg.edu.nus.smsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.models.Staff;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

	//List<Schedule> findByScheduleId(int id);
	Schedule findByScheduleId(int id);
	
	
	@Query("select s from Schedule s where s.clas.classId=id or s.lecturer.staffId=id ")
	List<Schedule> findByClassIdorLecturerId(int id);
	
	
}

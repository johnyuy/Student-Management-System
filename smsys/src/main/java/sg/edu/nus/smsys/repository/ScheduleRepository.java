package sg.edu.nus.smsys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

	//List<Schedule> findByScheduleId(int id);
	Schedule findByScheduleId(int id);
	
//	or s.lecturer.staffId=id
//	orLecturerId
	
//	@Query("select s from Schedule s where s.clas.classId=id ")
	
	List<Schedule> findByClas(CourseClass clas);

	Schedule findByDate(LocalDate date);

	Schedule findByDateAndClas(LocalDate date, CourseClass clas);

	List<Schedule> findByLecturer(Lecturer lecturer);
	
	//List<Schedule> findByCourseClass(CourseClass Courseclass );

}

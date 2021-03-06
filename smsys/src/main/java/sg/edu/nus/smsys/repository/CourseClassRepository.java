package sg.edu.nus.smsys.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseClass;
import sg.edu.nus.smsys.models.Student;


public interface CourseClassRepository extends JpaRepository<CourseClass, Integer> {

	List<CourseClass> findByCourse(Course course);
	List<CourseClass> findByStudentListContaining(Student student);
	CourseClass findByClassId(int id);

}

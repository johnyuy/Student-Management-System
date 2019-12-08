package sg.edu.nus.smsys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Course;
import sg.edu.nus.smsys.models.CourseClass;


public interface CourseClassRepository extends JpaRepository<CourseClass, Integer> {

	List<CourseClass> findByCourse(Course course);
 
}

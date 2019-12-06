package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	Course findByCourseId(int id);
}

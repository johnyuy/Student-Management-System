package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.CourseClass;


public interface CourseClassRepository extends JpaRepository<CourseClass, Integer> {
 
}

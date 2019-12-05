package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}

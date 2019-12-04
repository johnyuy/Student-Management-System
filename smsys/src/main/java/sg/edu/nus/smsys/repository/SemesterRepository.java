package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer>{

}

package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Lecturer;


public interface LecturerRepository extends JpaRepository<Lecturer,Integer>{

	Lecturer findByStaffId(int staffId);

}

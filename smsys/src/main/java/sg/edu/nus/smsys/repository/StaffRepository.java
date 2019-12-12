package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Staff;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
	Staff findByStaffId(int staffId);
	
}

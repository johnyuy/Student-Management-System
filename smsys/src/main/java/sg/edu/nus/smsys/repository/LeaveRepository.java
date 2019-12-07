package sg.edu.nus.smsys.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Staff;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {


	List<Leave> findBysubmittedByStaffID(Staff staff);

}

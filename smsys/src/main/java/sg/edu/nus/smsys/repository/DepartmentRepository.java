package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}

package sg.edu.nus.smsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}

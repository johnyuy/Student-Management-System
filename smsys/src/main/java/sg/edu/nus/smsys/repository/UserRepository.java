package sg.edu.nus.smsys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.smsys.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
	
}

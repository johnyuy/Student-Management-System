package sg.edu.nus.smsys.service;

import java.security.GeneralSecurityException;
import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;


public interface UserService {
	
	@Autowired
	public String passwordEncoder(String password, byte[] salt);
	@Autowired
	public void registerNewAccount(Integer id, String password) throws GeneralSecurityException;
	@Autowired
	public boolean verifyUserAndPassword(String username, String password);
	@Autowired
	public int getUserAccessLevel(String username);
	@Autowired
	public Student getStudentByUser(User user);
	@Autowired
	public User getUserByUsername(String username);
}

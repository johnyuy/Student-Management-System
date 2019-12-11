package sg.edu.nus.smsys.service;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;


public interface UserService {
	
	@Autowired
	public String passwordEncoder(String password, byte[] salt);
	@Autowired
	public void registerNewAccount(Integer id, String password) throws GeneralSecurityException;
	@Autowired
	public boolean verifyUserAndPassword(String username, String password);
}

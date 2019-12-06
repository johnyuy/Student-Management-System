package sg.edu.nus.smsys.service;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
	
	@Autowired
//	byte[] PasswordEncoder(String password) throws GeneralSecurityException;
	public String PasswordEncoder(String password, byte[] salt);
	@Autowired
	public void registerNewAccount(Integer id, String password) throws GeneralSecurityException;
	@Autowired
	public boolean verifyPassword(String username, String password);

}

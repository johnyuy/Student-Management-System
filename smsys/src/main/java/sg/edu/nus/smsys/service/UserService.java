package sg.edu.nus.smsys.service;

import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
	
	@Autowired
	byte[] PasswordEncoder(String password) throws GeneralSecurityException;
	@Autowired
	public void registerNewAccount(Integer id, String password) throws GeneralSecurityException;
	
}

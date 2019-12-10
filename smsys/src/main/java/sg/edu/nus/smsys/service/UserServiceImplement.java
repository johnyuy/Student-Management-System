package sg.edu.nus.smsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.smsys.cmdlr.CmdRunner2;
import sg.edu.nus.smsys.models.CourseAdmin;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImplement.class);

	@Autowired
	private UserRepository urepo;
	@Autowired
	private LecturerRepository lrepo;
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private CourseAdminRepository crepo;

	@Transactional
	public void registerNewAccount(Integer id, String password) throws GeneralSecurityException {
		int userType = id / 10000;
		byte[] salt = getSalt();
		String pw = PasswordEncoder(password, salt);
		if (userType == 5) {
	
			//Course Admin
			if (UsernameExist("A" + id) == false && CourseAdminIdExist(id) == true) {
				
				CourseAdmin ca = crepo.findByStaffId(id);
				String username = "A" + id;
				User user = new User(username, ca.getAccessLevel(), pw, salt, "ROLE_ADMIN", true);
				urepo.save(user);
				log.info("New user account for " + ca.getFirstName() + " " + ca.getLastName()
						+ " has been successfully created.");} 
			//lecturer
			else if (UsernameExist("L" + id) == false && LecturerIdExist(id) == true) {
				Lecturer lect = lrepo.findByStaffId(id);
				String username = "L" + id;
				User user = new User(username, lect.getAccessLevel(), pw, salt, "ROLE_LECTURER", true);
				urepo.save(user);
				log.info("New user account for " + lect.getFirstName() + " " + lect.getLastName()
						+ " has been successfully created.");
			}
		//student
		} else if (userType == 1 && UsernameExist("S" + id) == false && StudentIdExist(id) == true) {
			Student s = srepo.findByStudentId(id);
			String username = "S" + id;
			User user = new User(username, s.getAccessLevel(), pw, salt, "ROLE_STUDENT", true);
			urepo.save(user);
			log.info("New user account for " + s.getFirstName() + " " + s.getLastName()
					+ " has been successfully created.");
		} else {
			log.info("Error creating user account.");
			log.info("There is an existing account with that username or person does not exist in the database.");
		}
	}

	private boolean CourseAdminIdExist(int staffId) {

		if (crepo.findByStaffId(staffId) != null) {
			return true;
		}
		return false;
	}

	private boolean LecturerIdExist(int staffId) {

		if (lrepo.findByStaffId(staffId) != null) {
			return true;
		}
		return false;
	}

	private boolean UsernameExist(String username) {
		Optional<User> user =  urepo.findByUsername(username);
		if(user.isPresent()==false)
			return false;
		else
			return true;
	}

	private boolean StudentIdExist(int studentId) {

		if (srepo.findByStudentId(studentId) != null) {
			return true;
		}
		return false;
	}

	public String PasswordEncoder(String password, byte[] salt) {
		String encodedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encodedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encodedPassword;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public boolean verifyUserAndPassword(String username, String password) {
		Optional<User> u = urepo.findByUsername(username);
		if (u.isPresent()) {
			User user = u.get();
			byte[] salt = user.getSalt();
			
			String testpw = PasswordEncoder(password, salt);
			log.info("Username found, checking credentials...");
			
			if (testpw.equals(user.getPassword())) {
				log.info("Authenthication successful!");
				return true;
			} else {
				log.info("Wrong password!");
			}
		}
		log.info("Authenthication failed!");
		return false;
	}
	
}

package sg.edu.nus.smsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.smsys.models.CourseAdmin;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.models.Student;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.*;

@Service
public class UserServiceImplement implements UserService {
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
		String pw = PasswordEncoder(password).toString();
		if (userType == 5) {
			// Check if id exists in user database

			if (UsernameExist("A" + id) == false && CourseAdminIdExist(id) == true) {

				CourseAdmin ca = crepo.findByStaffId(id);
				String username = "A" + id;
				User user = new User(username, ca.getAccessLevel(), pw);
				urepo.save(user);
				System.out.println("New user account for " + ca.getFirstName() + " " + ca.getLastName()
						+ " has been successfully created.");
			} else if (UsernameExist("L" + id) == false && LecturerIdExist(id) == true) {
				Lecturer lect = lrepo.findByStaffId(id);
				String username = "L" + id;
				User user = new User(username, lect.getAccessLevel(), pw);
				urepo.save(user);
				System.out.println("New user account for " + lect.getFirstName() + " " + lect.getLastName()
						+ " has been successfully created.");
			}
		} else if (userType == 1 && UsernameExist("S" + id) == false && StudentIdExist(id) == true) {
			Student s = srepo.findByStudentId(id);
			String username = "S" + id;
			User user = new User(username, s.getAccessLevel(), pw);
			urepo.save(user);
			System.out.println("New user account for " + s.getFirstName() + " " + s.getLastName()
					+ " has been successfully created.");
		} else {
			System.out.println("Error creating user account.");
			System.out.println(
					"There is an existing account with that username or person does not exist in the database.");
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

		if (urepo.findByUsername(username) != null) {
			return true;
		}
		return false;
	}

	private boolean StudentIdExist(int studentId) {

		if (srepo.findByStudentId(studentId) != null) {
			return true;
		}
		return false;
	}

	public byte[] PasswordEncoder(String password) throws GeneralSecurityException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		return hashedPassword;
	}

}

package sg.edu.nus.smsys.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

@Service
public class SmsUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository uRepo;
	@Autowired
	UserService uService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = uRepo.findByUsername(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
		return user.map(SmsUserDetails::new).get();
	}

	public int getAuthUserAccessLevel() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			return uService.getUserAccessLevel(username);
		}
		if (principal instanceof String) {
			String s = principal.toString();
			return uService.getUserAccessLevel(s);
		}
		return 4;
	}
	
	public String getAuthUsername() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof String) {
			return principal.toString();
		}
		return null;
	}
	
	public void deAuth() {
		SecurityContextHolder.clearContext();
	}
}

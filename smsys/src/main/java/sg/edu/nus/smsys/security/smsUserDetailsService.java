package sg.edu.nus.smsys.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.UserRepository;


@Service
public class smsUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<User> user= uRepo.findByUsername(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: "+ userName));
		return user.map(smsUserDetails::new).get();
	}
	
	
}

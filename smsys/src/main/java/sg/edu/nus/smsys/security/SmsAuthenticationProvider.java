package sg.edu.nus.smsys.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.UserRepository;
import sg.edu.nus.smsys.service.UserService;

@Component
public class SmsAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	UserService us;
	@Autowired
	UserRepository uRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(us.verifyUserAndPassword(name, password)) {
        	User user = uRepo.findByUsername(name).get();
        	password = us.passwordEncoder(password, uRepo.findByUsername(name).get().getSalt());
        	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoles()));
        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
        	return token;
        } else
        {
        	throw new BadCredentialsException("Authentication failed for " + name);
        	
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

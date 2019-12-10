package sg.edu.nus.smsys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@ComponentScan("sg.edu.nus.smsys.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private smsUserDetailsService userservice;
	
	@Autowired smsAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userservice);
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http.authorizeRequests()
		 	.antMatchers("/admin2").hasRole("ADMIN")
		 	.antMatchers("/lecturer2").hasAnyRole("ADMIN", "LECTURER")
		 	.antMatchers("/student2").hasAnyRole("ADMIN", "STUDENT")
		 	.antMatchers("/").permitAll()
		 	.anyRequest()
		 	.authenticated()
		 	.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}
	
}

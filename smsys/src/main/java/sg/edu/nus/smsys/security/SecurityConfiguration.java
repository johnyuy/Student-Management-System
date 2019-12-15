package sg.edu.nus.smsys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
@ComponentScan("sg.edu.nus.smsys.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SmsUserDetailsService userservice;
	
	@Autowired SmsAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userservice);
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http.httpBasic().and().authorizeRequests()
		 	.antMatchers("/admin/**").hasRole("ADMIN")
		 	.antMatchers("/lecturer/**").hasAnyRole("ADMIN", "LECTURER")
		 	.antMatchers("/leave/**").hasAnyRole("ADMIN", "LECTURER")
		 	.antMatchers("/students/**").hasAnyRole("ADMIN", "LECTURER","STUDENT")
		 	.antMatchers("/student/**").hasAnyRole("ADMIN","LECTURER", "STUDENT")
		 	.antMatchers("/classes/**").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
		 	.antMatchers("/subjects/**").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
		 	.antMatchers("/schedule/**").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
		 	.antMatchers("/grades/**").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
		 	.antMatchers("/**").permitAll()
		 	.anyRequest()
		 	.authenticated()
		 	.and().formLogin()
		 	.successHandler(smsAuthenticationSuccessHandler())
		 	.and().authorizeRequests()
		 	.antMatchers(HttpMethod.POST, "/**").permitAll()
		 	.antMatchers(HttpMethod.GET, "/**").permitAll()
		 	.antMatchers(HttpMethod.PUT, "/**").permitAll()
		 	.and().csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}
	
	@Bean
	public AuthenticationSuccessHandler smsAuthenticationSuccessHandler() {
		return new SmsUrlAuthenticationSuccessHandler();
	}
	
	
}

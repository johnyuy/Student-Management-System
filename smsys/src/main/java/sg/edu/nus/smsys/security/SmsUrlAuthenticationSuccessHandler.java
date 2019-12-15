package sg.edu.nus.smsys.security;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class SmsUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private static final Logger log = LoggerFactory.getLogger(SmsUrlAuthenticationSuccessHandler.class);
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		handle(request, response, authentication);
        clearAuthenticationAttributes(request);
	}
	
	
	protected void handle(HttpServletRequest request, 
		      HttpServletResponse response, Authentication authentication)
		      throws IOException {
		  
		        String targetUrl = determineTargetUrl(authentication);
		 
		        if (response.isCommitted()) {
		            log.debug(
		              "Response has already been committed. Unable to redirect to "
		              + targetUrl);
		            return;
		        }
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
		    }
	
	protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities
         = authentication.getAuthorities();
        
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_LECTURER")) {
            	isUser=true;
            	break;
            }
        }
 
        if (isUser) {
            return "/";
        } else {
        	throw new IllegalStateException();
        }
    }
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}

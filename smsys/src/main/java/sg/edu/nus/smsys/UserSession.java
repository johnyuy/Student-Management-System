package sg.edu.nus.smsys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;





import sg.edu.nus.smsys.models.User;


public class UserSession {
	
	public static List<UserSession> sessions = new ArrayList<UserSession>();
	
	private User user;
	private UUID sessionId;
	
	
	public UserSession(User user, UUID sessionId) {
		super();
		this.user = user;
		this.sessionId = sessionId;
	}
	
	public User getUser() {
		return user;
	}
	public UUID getSessionId() {
		return sessionId;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
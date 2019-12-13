package sg.edu.nus.smsys.models;

import java.util.UUID;



public class UserSession {
	
	private User user;
	private UUID sessionId;
	
	public UserSession() {
		super();
		
	}
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
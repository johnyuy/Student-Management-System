package sg.edu.nus.smsys.models;

import java.util.UUID;

public class UserSession {
	private User user;
	private UUID sessionId;
	public UserSession(User user) {
		super();
		this.sessionId = UUID.randomUUID();
		this.user = user;
		
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
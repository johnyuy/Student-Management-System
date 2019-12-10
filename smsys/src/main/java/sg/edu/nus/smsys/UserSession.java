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
	private HashMap<String, Object> map;
	
	
	public UserSession(User user) {
		super();
		this.sessionId = UUID.randomUUID();
		//this.user = user;
		//this.map = new HashMap<String, Object>();

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
	public void clear() {
		this.map.clear();
	}
	
	
	
}
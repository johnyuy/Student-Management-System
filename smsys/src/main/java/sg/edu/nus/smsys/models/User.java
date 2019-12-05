package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import sg.edu.nus.smsys.repository.UserRepository;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int passid;
	
	private String username;
	private int accessRights;
	
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, int accessRights, String password) {
		super();
		this.username = username;
		this.accessRights = accessRights;
		this.password = password;
	}
	public int getPassid() {
		return passid;
	}
	public void setPassid(int passid) {
		this.passid = passid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAccessRights() {
		return accessRights;
	}
	public void setAccessRights(int accessRights) {
		this.accessRights = accessRights;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
		
}


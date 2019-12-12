package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int passid;
	
	@NotEmpty
	private String username;
	private int accessLevel;
	private String roles;
	private boolean active;
	@NotNull
	@Length(min=4)
	private String password;
	private byte[] salt;
	public String getRoles() {
		return roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, int accessRights, String password, byte[] salt, String roles, boolean active) {
		super();
		this.username = username;
		this.accessLevel = accessRights;
		this.password = password;
		this.salt = salt;
		this.roles = roles;
		this.active = active;
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
		return accessLevel;
	}
	public void setAccessRights(int accessRights) {
		this.accessLevel = accessRights;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	
		
}


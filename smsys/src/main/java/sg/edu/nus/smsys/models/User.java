package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import sg.edu.nus.smsys.repository.UserRepository;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int passid;
	
	@NotEmpty
	private String username;
	
	private int accessLevel;
	
	@NotNull
	@Length(min=4)
	private String password;
	
	private byte[] salt;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, int accessRights, String password, byte[] salt) {
		super();
		this.username = username;
		this.accessLevel = accessRights;
		this.password = password;
		this.salt = salt;
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


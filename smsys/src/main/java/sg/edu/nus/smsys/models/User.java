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
	
	private String id;
	private int accessRights;
	
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String id, int accessRights, String password) {
		super();
		this.id = id;
		this.accessRights = accessRights;
		this.password = password;
	}
	public int getPassid() {
		return passid;
	}
	public void setPassid(int passid) {
		this.passid = passid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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


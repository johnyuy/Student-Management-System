package sg.edu.nus.smsys.models;

import java.util.Arrays;

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

	public String getUsername() {
		return username;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public String getPassword() {
		return password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setPassid(int passid) {
		this.passid = passid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [passid=" + passid + ", username=" + username + ", accessLevel=" + accessLevel + ", roles=" + roles
				+ ", active=" + active + ", password=" + password + ", salt=" + Arrays.toString(salt) + "]";
	}

	
	
	
		
}


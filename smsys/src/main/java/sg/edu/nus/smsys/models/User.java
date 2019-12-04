package sg.edu.nus.smsys.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="user")
public class User {
	@Id
	@NotEmpty
	private int id;
	@NotEmpty
	private int accessRights;
	@NotEmpty
	@Length(min=4, max=20)
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(@NotEmpty int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public @NotEmpty int getId() {
		return id;
	}
	public void setId(@NotEmpty int id) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessRights;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (accessRights != other.accessRights)
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
	
}


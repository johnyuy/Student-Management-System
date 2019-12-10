package sg.edu.nus.smsys.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@MappedSuperclass
public abstract class Person {
	
	@NotEmpty(message ="This field cannot be left empty")
	private String firstName, lastName, title, address;

	private String middleName;
	
	@NotEmpty(message ="This field cannot be left empty")
	@Pattern(regexp="^[0-9]+$", message ="Mobile numbers should on be in digits")
	private String mobile;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message ="Please enter valid date")
	private LocalDate birthDate;
	
	@NotEmpty(message ="This field cannot be left empty")
	@Email(message ="Must be a well-formed email address")
	private String email;
	
	@NotEmpty(message ="This field cannot be left empty")
	@Pattern(regexp="male|female")
	private String gender;
	
	private final int accessLevel = 3;
	
	//Constructors
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.title = title;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	
	//GETTERS & SETTERS
	public String getFirstName() {
		return firstName;
	}
	

	public String getMiddleName() {
		return middleName;
	}
	

	public String getLastName() {
		return lastName;
	}
	

	public String getGender() {
		return gender;
	}
	

	public LocalDate getBirthDate() {
		return birthDate;
	}
	

	public String getTitle() {
		return title;
	}
	

	public String getAddress() {
		return address;
	}
	

	public String getMobile() {
		return mobile;
	}
	

	public String getEmail() {
		return email;
	}
	

	public int getAccessLevel() {
		return accessLevel;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAge() {	
		return Period.between(this.birthDate, LocalDate.now()).getYears();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		Person other = (Person) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender="
				+ gender + ", birthDate=" + birthDate + ", title=" + title + ", address=" + address + ", mobile="
				+ mobile + ", email=" + email + ", accessLevel=" + accessLevel + "]";
	}

}

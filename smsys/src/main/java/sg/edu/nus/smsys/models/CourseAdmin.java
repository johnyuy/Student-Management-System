package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CADM")
public class CourseAdmin extends Staff {

	public CourseAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseAdmin(String firstName, String middleName, String lastName, String gender, LocalDateTime birthDate,
			String title, String address, String mobile, String email) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		// TODO Auto-generated constructor stub
	}

}

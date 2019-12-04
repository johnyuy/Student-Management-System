package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CADM")
public class CourseAdmin extends Staff {

	public CourseAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseAdmin(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		// TODO Auto-generated constructor stub
	}
	//Main Constructor
	public CourseAdmin(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status, int annualLeaveBalance,
			int annualLeaveEntitled, List<Leave> annualLeaveList, Staff manager) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email, status, annualLeaveBalance,
				annualLeaveEntitled, annualLeaveList, manager);
		// TODO Auto-generated constructor stub
	}
	

}

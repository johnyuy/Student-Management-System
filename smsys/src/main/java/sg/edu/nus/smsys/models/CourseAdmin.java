package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CADM")
public class CourseAdmin extends Staff {

	@OneToMany(mappedBy="approvedByStaffID")
	private List<Leave> approvedLeaveList;
	private final int accessLevel = 1;

	//CONSTRUCTORS
	public CourseAdmin() {
		super();
	}

	public CourseAdmin(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status, int annualLeaveBalance,
			int annualLeaveEntitled, List<Leave> annualLeaveList, Staff manager, List<Leave> approvedLeaveList) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email, status, annualLeaveBalance,
				annualLeaveEntitled, annualLeaveList, manager);
		this.approvedLeaveList = approvedLeaveList;
	}
	
	public List<Leave> getApprovedLeaveList() {
		return approvedLeaveList;
	}

	public void setApprovedLeaveList(List<Leave> approvedLeaveList) {
		this.approvedLeaveList = approvedLeaveList;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	@Override
	public String toString() {
		return "CourseAdmin [approvedLeaveList=" + approvedLeaveList + ", accessLevel=" + accessLevel + "]";
	}
	
	
}

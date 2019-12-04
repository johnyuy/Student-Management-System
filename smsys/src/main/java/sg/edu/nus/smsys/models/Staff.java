package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance 
@Table(name="STAFF_TABLE")
@DiscriminatorColumn(name="STAFF_TYPE")
public abstract class Staff extends Person {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int staffId;
	private String status;
	private int annualLeaveBalance;
	private int annualLeaveEntitled;
	
	@OneToMany (mappedBy = "staff")
	private List<Leave> annualLeaveList; 
	
	@OneToOne (cascade={CascadeType.ALL})
	private Staff manager;

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		// TODO Auto-generated constructor stub
	}
	// Main Constructor
	public Staff(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email,String status, int annualLeaveBalance, int annualLeaveEntitled,
			List<Leave> annualLeaveList, Staff manager) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		this.status = status;
		this.annualLeaveBalance = annualLeaveBalance;
		this.annualLeaveEntitled = annualLeaveEntitled;
		this.annualLeaveList = annualLeaveList;
		this.manager = manager;	
	}
	
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAnnualLeaveBalance() {
		return annualLeaveBalance;
	}

	public void setAnnualLeaveBalance(int annualLeaveBalance) {
		this.annualLeaveBalance = annualLeaveBalance;
	}

	public int getAnnualLeaveEntitled() {
		return annualLeaveEntitled;
	}

	public void setAnnualLeaveEntitled(int annualLeaveEntitled) {
		this.annualLeaveEntitled = annualLeaveEntitled;
	}

	public List<Leave> getAnnualLeaveList() {
		return annualLeaveList;
	}

	public void setAnnualLeaveList(List<Leave> annualLeaveList) {
		this.annualLeaveList = annualLeaveList;
	}

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", status=" + status + ", annualLeaveBalance=" + annualLeaveBalance
				+ ", annualLeaveEntitled=" + annualLeaveEntitled + ", annualLeaveList=" + annualLeaveList + ", manager="
				+ manager + "]";
	}
	

}

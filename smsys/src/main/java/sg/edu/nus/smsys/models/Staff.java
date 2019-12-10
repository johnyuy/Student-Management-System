package sg.edu.nus.smsys.models;

import java.time.LocalDate;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance 
@Table(name="STAFF_TABLE")
@DiscriminatorColumn(name="STAFF_TYPE")
@SequenceGenerator(name="staff_id_seq", initialValue = 50001)

public class Staff extends Person {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "staff_id_seq")
	@Min(50001)
	@Max(59999)
	private int staffId;
	private String status;
	@Min(0)
	private int annualLeaveBalance;
	@Min(0)
	private final int annualLeaveEntitled=21;
	public final int accessLevel = 2;
	
	@OneToMany (mappedBy = "submittedByStaffID")
	private List<Leave> annualLeaveList;
	
	@OneToOne (cascade={CascadeType.ALL})
	private Staff manager;

	
	//CONSTRUCTORS
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Main Constructor
	public Staff(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email,String status, int annualLeaveBalance, int annualLeaveEntitled,
			List<Leave> annualLeaveList, Staff manager) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		this.status = status;
		this.annualLeaveBalance = annualLeaveBalance;
		//this.annualLeaveEntitled = annualLeaveEntitled;
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

	/*public void setAnnualLeaveEntitled(int annualLeaveEntitled) {
		this.annualLeaveEntitled = annualLeaveEntitled;
	}*/

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

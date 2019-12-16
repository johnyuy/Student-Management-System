package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name="Leave_Table")
@SequenceGenerator(name="leave_id_seq", initialValue = 300000)
public class Leave {
	
	@Id
	@NotNull
	@Max(399999)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "leave_id_seq")
	private int leaveId;
	
	@NotNull
	@Future(message ="Please enter future date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateStart;

	@NotNull(message ="This field cannot be left empty")
	//@Min(1)
	@Max(21)
	private int duration=1;
	
	@Size(max=100)
	private String reason;
	
	private String status = "Pending" ;
	
	@ManyToOne
	private CourseAdmin approvedByStaffID;
	
	@ManyToOne
	private Staff submittedByStaffID;
	
	
	
	//CONSTRUCTORS
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Leave(int leaveId, LocalDate dateStart, int duration, String reason, String status,
			CourseAdmin approvedByStaffID, Staff submittedByStaffID) {
		super();
		this.leaveId = leaveId;
		this.dateStart = dateStart;
		this.duration = duration;
		this.reason = reason;
		this.status = "Pending";
		this.approvedByStaffID = approvedByStaffID;
		this.submittedByStaffID = submittedByStaffID;
		
		//status=“Pending”;
	}
	
	//GETTERS AND SETTERS
	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CourseAdmin getApprovedByStaffID() {
		return approvedByStaffID;
	}

	public void setApprovedByStaffID(CourseAdmin approvedByStaffID) {
		this.approvedByStaffID = approvedByStaffID;
	}

	public Staff getSubmittedByStaffID() {
		return submittedByStaffID;
	}

	public void setSubmittedByStaffID(Staff submittedByStaffID) {
		this.submittedByStaffID = submittedByStaffID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leaveId;
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
		Leave other = (Leave) obj;
		if (leaveId != other.leaveId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + "]";
	}



 }

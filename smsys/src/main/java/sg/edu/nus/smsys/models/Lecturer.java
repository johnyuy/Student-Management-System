package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("LECT")
public class Lecturer extends Staff {
	
	@ManyToOne
	private Department department;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
	private List<Subject> subjectList;

	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(int staffId, String status, int annualLeaveBalance, int annualLeaveEntitled,
			List<Leave> annualLeaveList, Staff manager) {
		super(staffId, status, annualLeaveBalance, annualLeaveEntitled, annualLeaveList, manager);
		// TODO Auto-generated constructor stub
	}

	public Lecturer(String firstName, String middleName, String lastName, String gender, LocalDateTime birthDate,
			String title, String address, String mobile, String email) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email);
		// TODO Auto-generated constructor stub
	}

	public Lecturer(Department department, List<Subject> subjectList) {
		super();
		this.department = department;
		this.subjectList = subjectList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	@Override
	public String toString() {
		return "Lecturer [department=" + department + ", subjectList=" + subjectList + "]";
	}

}

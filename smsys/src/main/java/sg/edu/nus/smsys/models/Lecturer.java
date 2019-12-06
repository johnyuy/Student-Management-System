package sg.edu.nus.smsys.models;

import java.time.LocalDate;
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
	
	@ManyToMany
	private List<CourseClass> classList;
	
	private final int accessLevel = 2;

	//CONSTRUCTORS
	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(String firstName, String middleName, String lastName, String gender, LocalDate birthDate,
			String title, String address, String mobile, String email, String status, int annualLeaveBalance,
			int annualLeaveEntitled, List<Leave> annualLeaveList, Staff manager, Department department, List<Subject> subjectList, List<CourseClass> classList) {
		super(firstName, middleName, lastName, gender, birthDate, title, address, mobile, email, status, annualLeaveBalance,
				annualLeaveEntitled, annualLeaveList, manager);
		this.department = department;
		this.subjectList = subjectList;
		this.classList = classList;
	}

	public Department getDepartment() {
		return department;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public List<CourseClass> getClassList() {
		return classList;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public void setClassList(List<CourseClass> classList) {
		this.classList = classList;
	}

	
	@Override
	public String toString() {
		return "Lecturer [department=" + department + ", subjectList=" + subjectList + ", classList=" + classList
				+ ", accessLevel=" + accessLevel + "]";
	}

}

package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int departmentId;
	private String departmentName;
	
	@OneToMany(mappedBy = "department")
	private List<Lecturer> lecturerList;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int departmentId, String departmentName, List<Lecturer> lecturerList) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.lecturerList = lecturerList;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", lecturerList="
				+ lecturerList + "]";
	}

}

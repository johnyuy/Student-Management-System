package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subjectId;
	private String subjectName;
	private String subjectDesc;
	private int units;
	
	@ManyToMany
	private List<Lecturer> lecturerList;
	
	@OneToMany(mappedBy = "subject")
	private List<Schedule> schedule;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(int subjectId, String subjectName, String subjectDesc, int units, List<Lecturer> lecturerList) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.units = units;
		this.lecturerList = lecturerList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectDesc=" + subjectDesc
				+ ", units=" + units + ", lecturerList=" + lecturerList + "]";
	}

}

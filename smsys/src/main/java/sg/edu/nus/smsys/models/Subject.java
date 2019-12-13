package sg.edu.nus.smsys.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="subject_id_seq", initialValue = 1)
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "subject_id_seq")
	private int subjectId;
	@NotNull
	private String subjectName;
	@Size(min=2, max=1000)
	private String subjectDesc;
	@Min(0)
	private int units;
	
	@ManyToMany(mappedBy ="subjectList")
	private List<Lecturer> lecturerList;
	
	@OneToMany(mappedBy = "subject")
	private List<Schedule> scheduleList;
	
	@OneToMany(mappedBy = "subject")
	private List<Grade> gradeList;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subject(String subjectName, String subjectDesc, int units) {
		super();
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.units = units;
	}
	
	public Subject(int subjectId, String subjectName, String subjectDesc, int units, List<Lecturer> lecturerList,
			List<Schedule> scheduleList, List<Grade> gradeList) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.units = units;
		this.lecturerList = lecturerList;
		this.scheduleList = scheduleList;
		this.gradeList = gradeList;
	}
	
	public Subject(String subjectName, String subjectDesc, int units, List<Lecturer> lecturerList,
			List<Schedule> scheduleList, List<Grade> gradeList) {
		super();
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.units = units;
		this.lecturerList = lecturerList;
		this.scheduleList = scheduleList;
		this.gradeList = gradeList;
	}

	public List<Schedule> getSchedule() {
		return scheduleList;
	}

	public void setSchedule(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
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

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectDesc=" + subjectDesc
				+ ", units=" + units + ", lecturerList=" + lecturerList + ", scheduleList=" + scheduleList
				+ ", gradeList=" + gradeList + "]";
	}

	

}

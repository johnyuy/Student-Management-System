package sg.edu.nus.smsys.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="semester")
public class Semester {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int semId;
	private String semCode;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	@ManyToMany(mappedBy = "semesterList")
	private List<Class> classList;

	public Semester() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Semester(int id, String semCode, LocalDateTime startDate, LocalDateTime endDate, List<Class> classList) {
		super();
		this.semId = id;
		this.semCode = semCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.classList = classList;
	}
	

	public int getId() {
		return semId;
	}


	public void setId(int id) {
		this.semId = id;
	}



	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public List<Class> getClassList() {
		return classList;
	}

	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}

	@Override
	public String toString() {
		return "Semester [id=" + semId + ", semCode=" + semCode + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", classList=" + classList + "]";
	}

	public String getSemCode() {
		return semCode;
	}

	public void setSemCode(String semCode) {
		this.semCode = semCode;
	}
	
	
}

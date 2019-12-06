package sg.edu.nus.smsys.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="semester")
public class Semester {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int semId;
	@NotNull
	private String semCode;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	
	@ManyToMany(mappedBy = "semesterList")
	private List<CourseClass> classList;

	public Semester() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Semester(LocalDate startDate, LocalDate endDate, List<CourseClass> classList) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.classList = classList;
		this.semCode = String.valueOf(startDate.getYear()%100) + "/" + String.valueOf(startDate.getYear()%100+1);
		if(startDate.getMonthValue()>=7 && startDate.getMonthValue()<=12) {
			this.semCode += "/1";
		} else {
			this.semCode += "/2";
		}
	}
	
	//GETTERS AND SETTERS
	public int getSemId() {
		return semId;
	}

	public String getSemCode() {
		return semCode;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public List<CourseClass> getClassList() {
		return classList;
	}

	public void setSemId(int semId) {
		this.semId = semId;
	}

	public void setSemCode(String semCode) {
		this.semCode = semCode;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setClassList(List<CourseClass> classList) {
		this.classList = classList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + semId;
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
		Semester other = (Semester) obj;
		if (semId != other.semId)
			return false;
		return true;
	}
	
	
	
}

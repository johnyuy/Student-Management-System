package sg.edu.nus.smsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.smsys.models.Semester;

public interface SemesterService {
	@Autowired
	public String semestersToString(List<Semester> semlist);
}

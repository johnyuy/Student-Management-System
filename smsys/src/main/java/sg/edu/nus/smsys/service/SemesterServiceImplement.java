package sg.edu.nus.smsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.Semester;

@Service
public class SemesterServiceImplement implements SemesterService{
	// List for semesters' semcode converted into a string separated by commas
	public String semestersToString(List<Semester> semlist) {
		String str ="";
		for (int i = 0; i < semlist.size(); i++) {
			if (i != 0)
				str += ", ";
			str += semlist.get(i).getSemCode();
		}
		return str;
	}
}

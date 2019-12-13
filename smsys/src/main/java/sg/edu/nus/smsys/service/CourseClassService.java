package sg.edu.nus.smsys.service;

import java.util.List;

import sg.edu.nus.smsys.models.CourseClass;

public interface CourseClassService {
	public boolean canViewClass(int id);
	
	public List<CourseClass> getClassesByUser();
}

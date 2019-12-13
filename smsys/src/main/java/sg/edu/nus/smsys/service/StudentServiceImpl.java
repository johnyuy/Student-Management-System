package sg.edu.nus.smsys.service;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.smsys.models.*;
import sg.edu.nus.smsys.repository.GradeRepository;
import sg.edu.nus.smsys.repository.StudentRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;

@Service
public class StudentServiceImpl {
	
	@Autowired
	private StudentRepository srepo;
	@Autowired
	private GradeRepository grepo;

	
	public float CalulateGPA(Student student, CourseClass cc) {
		ArrayList<Grade> gradelist = new ArrayList<Grade>();
		gradelist.addAll(grepo.findByStudentAndClas(student,cc));
		float gpa= sumOfGpa(gradelist)/sumOfCredits(gradelist);
		return gpa;	
	}
	
	public float sumOfGpa(ArrayList<Grade> gradelist) {
		float sumofgrades = (float) gradelist.stream().mapToDouble(g -> g.getGradeToGPA()*g.getSubject().getUnits()).sum();
		return sumofgrades;
	}
	
	public float sumOfCredits(ArrayList<Grade> subjectlist) {
		float sumofcredits = (float) subjectlist.stream().mapToDouble(g -> g.getSubject().getUnits()).sum();		
				return sumofcredits;
	}

}

package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.smsys.models.Semester;
import sg.edu.nus.smsys.repository.SemesterRepository;

@Controller
@RequestMapping("/semester")
public class SemesterController
{
	@Autowired
	private SemesterRepository semRepo;
	
	@GetMapping("/list")
	public String listAll(Model semester)
	{
		List<Semester> semList = new ArrayList<Semester>();
		semList = semRepo.findAll();
		semester.addAttribute("semester",semList);
		return "semester";
				
	}
	@GetMapping("/add")
	public String showAddForm(Model model) 
	{
		Semester semester = new Semester();
		model.addAttribute("semester",semester);
		return "semesterform";
	}
	@PostMapping("/insert")
	public String insertSemester(@ModelAttribute Semester semester)
	{
		semRepo.save(semester.updatedSemster());
		return "redirect:/semester/list";
	}
}

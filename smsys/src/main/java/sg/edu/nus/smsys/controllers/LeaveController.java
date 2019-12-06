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
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.StaffRepository;



@Controller
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	LeaveRepository lrepo;
	
	@Autowired 
	StaffRepository srepo;
	
	
	@GetMapping("/alllist")
	public  String listAll(Model model){
		List<Leave> alllist = new ArrayList<Leave>();
		alllist = lrepo.findAll();
		model.addAttribute("leave",alllist);
		return "leavemanagement";
	}
		
	
	@GetMapping("/leavelist")
	public  String leaveList(Model model, @RequestParam("id") int id){
	Staff staff = srepo.findByStaffId(id);
	ArrayList<Leave> leavelist = new ArrayList<Leave>();			
	leavelist.addAll(lrepo.findBysubmittedByStaffID(staff));
	model.addAttribute("leaves",leavelist);
	return "leaves";	
	}
	

	@GetMapping("/add")
	public String showAddForm(Model model) {
		Leave leave=new Leave();
		model.addAttribute("leave",leave);
		return "LeaveApplication";
	}

	@PostMapping("/submit")
	public String submitLeave(@ModelAttribute Leave leave) {
		lrepo.save(leave);
		return "redirect:/leave/selflist?id=50001";
	}
//	
/*	@GetMapping(value="/view",params= {"viewleavedetails"})
	public String viewLeavedetails() {
		return "leavedetails";
		
		
	}*/
	

}

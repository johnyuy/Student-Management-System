package sg.edu.nus.smsys.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.CourseAdmin;
import sg.edu.nus.smsys.models.Leave;
//import sg.edu.nus.smsys.models.MyAnnotation;
import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.models.User;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StaffRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;
import sg.edu.nus.smsys.service.UserService;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	LeaveRepository lrepo;

	@Autowired
	StaffRepository srepo;

	@Autowired
	CourseAdminRepository crepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	LecturerRepository lerepo;
	@Autowired
	UserService us;
	

	@GetMapping("/alllist")
	public String listAll(Model model) {
		List<Leave> alllist = new ArrayList<Leave>();
		alllist = lrepo.findAll();
		model.addAttribute("leave", alllist);
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		if(suds.getAuthUserAccessLevel()==1)
			return "leavemanagement";
		else
			return "redirect:/";
	}

	@GetMapping("/leavelist")
	public String leaveList(Model model, @RequestParam(defaultValue = "") String id) {
		ArrayList<Leave> leavelist = new ArrayList<Leave>();
		if (!id.equals("")) {
			Staff staff = srepo.findByStaffId(Integer.parseInt(id));
			leavelist.addAll(lrepo.findBysubmittedByStaffID(staff));
		} else {
			leavelist.addAll(lrepo.findAll());
		}
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		model.addAttribute("leave", leavelist);
		return "leaves";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Leave leave = new Leave();
		model.addAttribute("leave", leave);
		User user=us.getUserByUsername(suds.getAuthUsername());
		Staff staff=new Staff();
		if(user.getAccessLevel()==1)
		{
			staff=us.getCourseAdminByUser(user);
		}
		else {
			staff=us.getLecturerByUser(user);
			
		}
		System.out.println(staff.getStaffId());
		
		model.addAttribute("staff",staff);
		
		return "leaveapplication";
	}

	@PostMapping("/add")
	public String applyLeave(@Valid @ModelAttribute Leave l, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream()
					.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
			return "leaveapplication";
		}
		
		User user=us.getUserByUsername(suds.getAuthUsername());
		Staff staff=new Staff();
		if(user.getAccessLevel()==1)
		{
			staff=us.getCourseAdminByUser(user);
		}
		else {
			staff=us.getLecturerByUser(user);
			
		}
		String staffId =String.valueOf(staff.getStaffId());
		l.setSubmittedByStaffID(staff);
		
		lrepo.save(l);
		
		return "redirect:/leave/leavelist?id=" + staffId;
	}

		@PostMapping("/edit/{id}")
		public String submitLeave(@Valid Leave l, BindingResult bindingResult, @PathVariable("id") Integer id) {
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().stream()
						.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
				return "leaveapplication";
			}
			l.setLeaveId(id);
			
			//String staffId = String.valueOf(l.getSubmittedByStaffID().getStaffId());
			User user=us.getUserByUsername(suds.getAuthUsername());
			Staff staff=new Staff();
			if(user.getAccessLevel()==1)
			{
				staff=us.getCourseAdminByUser(user);
			}
			else {
				staff=us.getLecturerByUser(user);
				
			}
			String staffId =String.valueOf(staff.getStaffId());
			l.setSubmittedByStaffID(staff);
			
			lrepo.save(l);
			
			return "redirect:/leave/leavelist?id=" + staffId;
		}
	
		@GetMapping("/edit/{id}")
		public String showEditForm(Model model, @PathVariable("id") Integer id) {
			model.addAttribute("access", suds.getAuthUserAccessLevel());
			
			//show a specific leave on page by leave id
			Leave leave = lrepo.findById(Integer.valueOf(id)).get();
			model.addAttribute("leave", leave);
			
			User user=us.getUserByUsername(suds.getAuthUsername());
			Staff staff=new Staff();
			if(user.getAccessLevel()==1)
			{
				staff=us.getCourseAdminByUser(user);
			}
			else {
				staff=us.getLecturerByUser(user);
				
			}
			
			model.addAttribute("staff",staff);
			return "leaveapplication";
		}

	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		Leave leave = lrepo.findById(id).get();
		lrepo.delete(leave);
		//String staffId = String.valueOf(leave.getSubmittedByStaffID().getStaffId());
		
		User user=us.getUserByUsername(suds.getAuthUsername());
		Staff staff=new Staff();
		if(user.getAccessLevel()==1)
		{
			staff=us.getCourseAdminByUser(user);
		}
		else {
			staff=us.getLecturerByUser(user);
			
		}
		String staffId =String.valueOf(staff.getStaffId());
		
		return "redirect:/leave/leavelist?id=" + staffId;
	}

	@GetMapping("/view/{id}")
	public String viewLeaveDetails(Model model, @ModelAttribute Leave leave, @PathVariable("id") Integer id) {
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		leave = lrepo.findById(id).get();

		model.addAttribute("leave", leave);
		model.addAttribute("access", suds.getAuthUserAccessLevel());
		
		
		User user=us.getUserByUsername(suds.getAuthUsername());
		
		
		CourseAdmin	ca=us.getCourseAdminByUser(user);
	
		leave.setApprovedByStaffID(ca);
		
		model.addAttribute("courseadmin",ca);		
		
		if(suds.getAuthUserAccessLevel()==1)
			return "leavedetails";
		else
			return "redirect:/";
	}

	@PostMapping("/reply")
	public String replyLeave(@ModelAttribute Leave leave,Model model) {
		
		User user=us.getUserByUsername(suds.getAuthUsername());
		CourseAdmin	ca=us.getCourseAdminByUser(user);
		leave.setApprovedByStaffID(ca);
		model.addAttribute("courseadmin",ca);
		
		lrepo.save(leave);

		Staff staff = new Staff();
		staff = srepo.findByStaffId(leave.getSubmittedByStaffID().getStaffId());

		if (leave.getStatus().equals("Approve")) {

			staff.setAnnualLeaveBalance(leave.getSubmittedByStaffID().getAnnualLeaveBalance() - leave.getDuration());

		} else {
			staff.setAnnualLeaveBalance(leave.getSubmittedByStaffID().getAnnualLeaveBalance());
		}
		srepo.save(staff);

		System.out.println("duration = " + leave.getDuration());
		System.out.println("reason = " + leave.getReason());
		System.out.println("status= " + leave.getStatus());
		System.out.println("id = " + leave.getLeaveId());

		return "redirect:/leave/alllist";
	}

}

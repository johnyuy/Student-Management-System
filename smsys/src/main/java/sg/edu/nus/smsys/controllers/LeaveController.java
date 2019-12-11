package sg.edu.nus.smsys.controllers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.smsys.models.CourseAdmin;
import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
//import sg.edu.nus.smsys.models.MyAnnotation;
import sg.edu.nus.smsys.models.Staff;
import sg.edu.nus.smsys.repository.CourseAdminRepository;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.StaffRepository;

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
	LecturerRepository lerepo;

	@GetMapping("/alllist")
	public String listAll(Model model) {
		List<Leave> alllist = new ArrayList<Leave>();
		alllist = lrepo.findAll();
		model.addAttribute("leave", alllist);
		return "leavemanagement";
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

		model.addAttribute("leave", leavelist);
		return "leaves";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		Leave leave = new Leave();
		model.addAttribute("leave", leave);
		return "leaveapplication";
	}

	@PostMapping("/addleave")
	public String applyLeave(@Valid Leave l, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream()
					.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
			return "leaveapplication";
		}
		System.out.println(l.getDateStart().toString());

		lrepo.save(l);
		String staffId = String.valueOf(l.getSubmittedByStaffID().getStaffId());
		// return "redirect:/leave/leavelist?id="+staffId;
		return "redirect:/leave/leavelist?id=" + staffId;
	}

	@PostMapping("/edit/{id}")
	public String submitLeave(@Valid  @ModelAttribute Leave l, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream()
					.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
			return "leaveapplication";
		}
		l.setLeaveId(id);
		lrepo.save(l);
		String staffId = String.valueOf(l.getSubmittedByStaffID().getStaffId());
		return "redirect:/leave/leavelist?id=" + staffId;
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") String id) {
		//show a specific leave on page by leave id
		Leave leave = lrepo.findById(Integer.valueOf(id)).get();
		model.addAttribute("leave", leave);
		return "leaveapplication.html";
	}

	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		Leave leave = lrepo.findById(id).get();
		lrepo.delete(leave);
		String staffId = String.valueOf(leave.getSubmittedByStaffID().getStaffId());
		return "redirect:/leave/leavelist?id=" + staffId;
	}

	@GetMapping("/view/{id}")
	public String viewLeaveDetails(Model model, @ModelAttribute Leave leave, @PathVariable("id") Integer id) {
		leave = lrepo.findById(id).get();

		model.addAttribute("leave", leave);

		return "leavedetails";
	}

	@PostMapping("/reply")
	public String replyLeave(@ModelAttribute Leave leave) {
		lrepo.save(leave);

		Staff staff = new Staff();
		staff = srepo.findByStaffId(leave.getSubmittedByStaffID().getStaffId());

		if (leave.getStatus().equals("approve")) {

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

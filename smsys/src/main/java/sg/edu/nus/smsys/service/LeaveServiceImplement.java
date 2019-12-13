package sg.edu.nus.smsys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sg.edu.nus.smsys.models.Leave;
import sg.edu.nus.smsys.models.Lecturer;
import sg.edu.nus.smsys.repository.LeaveRepository;
import sg.edu.nus.smsys.security.SmsUserDetailsService;

@Service
public class LeaveServiceImplement {
	@Autowired
	private LeaveRepository lrepo;
	@Autowired
	private SmsUserDetailsService suds;
	@Autowired
	private UserService us;
	
	public boolean canViewLeave(int id) {
		List<Leave> leavelist = new ArrayList<Leave>();
		boolean output = false;
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			// access denied
			return false;
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list
			leavelist = lecturer.getAnnualLeaveList();
		} else if (accesslevel == 1) {
			//full access;
			return true;
		}
		if (!leavelist.isEmpty()) {
			for (Leave leave : leavelist) {
				if(leave.getLeaveId()==id)
					return true;
			}
		}
		return output;
	}
	
	public List<Leave> getLeaveListByUser(){
		List<Leave> leavelist = new ArrayList<Leave>();
		int accesslevel = suds.getAuthUserAccessLevel();
		if (accesslevel == 3) {
			//access denied
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access denied");
		} else if (accesslevel == 2) {
			// get lecturer
			Lecturer lecturer = us.getLecturerByUser(us.getUserByUsername(suds.getAuthUsername()));
			// get list of classes taught
			leavelist = lecturer.getAnnualLeaveList();
		} else if (accesslevel == 1) {
			//full access
			leavelist = lrepo.findAll();;
		}
		return leavelist;
	}
}

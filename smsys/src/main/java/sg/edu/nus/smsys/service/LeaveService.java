package sg.edu.nus.smsys.service;

import java.util.List;

import sg.edu.nus.smsys.models.Leave;

public interface LeaveService {
	public boolean canViewLeave(int id);
	public List<Leave> getLeaveListByUser();
}

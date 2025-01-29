package in.umesh.service;

import in.umesh.dto.DashboardResponse;
import in.umesh.entities.Counsellor;

public interface CounsellorService {
	
	public Counsellor findByEmail(String email);
	
	public boolean register(Counsellor counsellor);
	
	public Counsellor login(String email, String password);
	
	public DashboardResponse getDashboardInfo(Integer counsellorId);

}

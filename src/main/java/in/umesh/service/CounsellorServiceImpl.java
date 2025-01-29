package in.umesh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.umesh.dto.DashboardResponse;
import in.umesh.entities.Counsellor;
import in.umesh.entities.Enquriy;
import in.umesh.repository.CounsellorRepository;
import in.umesh.repository.EnquiryRepository;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	private CounsellorRepository counsellorRepository;
	
	@Autowired
	private EnquiryRepository enquiryRepository;
	
	@Override
	public Counsellor findByEmail(String email) {
		
	return	counsellorRepository.findByEmail(email);
	}
	
	
	@Override
	public boolean register(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepository.save(counsellor);
		
		if(null != savedCounsellor.getCounsellorId()) {
			return true;
		}
		
		return false;
	}

	@Override
	public Counsellor login(String email, String password) {
		Counsellor counsellor = counsellorRepository.findByEmailAndPassword(email, password);
		return counsellor;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
	    // Initialize the response object
	    DashboardResponse response = new DashboardResponse();

	    // Get the list of enquiries for the counsellorId
	    List<Enquriy> enqList = enquiryRepository.getEnquiriesByCounsellorId(counsellorId);

	    // Calculate total enquiries
	    int totalEnq = enqList.size();

	    // Calculate enrolled enquiries
	    int enrolledEnq = enqList.stream()
	                              .filter(e -> e.getEnqStatus().equals("Enrolled"))
	                              .collect(Collectors.toList())
	                              .size();

	    // Calculate lost enquiries
	    int lostEnqs = enqList.stream()
	                          .filter(e -> e.getEnqStatus().equals("Lost"))
	                          .collect(Collectors.toList())
	                          .size();

	    // Calculate open enquiries
	    int openEnqs = enqList.stream()
	                          .filter(e -> e.getEnqStatus().equals("Open"))
	                          .collect(Collectors.toList())
	                          .size();

	    // Set the values into the response object
	    response.setTotalEnqs(totalEnq);        // Set total enquiries
	    response.setEnrolledEnqs(enrolledEnq);  // Set enrolled enquiries
	    response.setLostEnqs(lostEnqs);        // Set lost enquiries
	    response.setOpenEnqs(openEnqs);        // Set open enquiries

	    // Return the populated response object
	    return response;
	}

	
}

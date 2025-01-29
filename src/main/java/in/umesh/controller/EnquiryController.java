package in.umesh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.umesh.dto.ViewEnqsFilterRequest;
import in.umesh.entities.Enquriy;
import in.umesh.service.CounsellorService;
import in.umesh.service.EnquriyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	private EnquriyService enquriyService;

	public EnquiryController(EnquriyService enquriyService) {
		this.enquriyService = enquriyService;
	}

	@PostMapping("/filter-enqs")
	public String filterEnquiries(ViewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession();
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquriy> enqsList = enquriyService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
		model.addAttribute("enquiries", enqsList);
		return "viewEnqsPage";

	}

	@GetMapping("/view-enquiries")
	public String gerEnquiries(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquriy> enqList = enquriyService.getAllEnquiries(counsellorId);
		model.addAttribute("enquiries", enqList);

		ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
		model.addAttribute("viewEnqsFilterRequest", new ViewEnqsFilterRequest());

		return "viewEnqsPage";

	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		Enquriy enqObj = new Enquriy();
		model.addAttribute("enq", enqObj);
		return "enquiryForm"; // Ensure the template name is correct
	}

//    @GetMapping("/editEnq")
//    public String editEnquriy(@RequestParam("enqId")  Integer enqId, Model model) {
//    	Enquriy enquiry = enquriyService.getEnquiryById(enqId);
//    	model.addAttribute("enquiry", enquiry);
//		 return "enquiryForm";
//    	 
//    	
//    }

	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model) {
		Enquriy enquiry = enquriyService.getEnquiryById(enqId);
		model.addAttribute("enq", enquiry); // Use the same attribute name expected by the form
		return "enquiryForm"; // Redirects to the enquiry form for editing
	}

	@PostMapping("/addEnq")
	public String handleAddEnquiry(@ModelAttribute("enq") Enquriy enq, HttpServletRequest request, Model model)
			throws Exception {

		HttpSession session = request.getSession(false);

		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		boolean isSaved = enquriyService.addEnquiry(enq, counsellorId);

		if (isSaved) {
			model.addAttribute("smsg", "Enquiry Added");
		} else {
			model.addAttribute("emsg", "Failed to Add Enquiry");
		}

		return "enquiryForm"; // Ensure this matches your template
	}
}

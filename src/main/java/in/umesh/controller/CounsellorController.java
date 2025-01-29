package in.umesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.umesh.dto.DashboardResponse;
import in.umesh.entities.Counsellor;
import in.umesh.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

    private  CounsellorService counsellorService;

    public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
    }  

    @GetMapping("/")
    public String index(Model model) {
        Counsellor cobj = new Counsellor();
        model.addAttribute("counsellor", cobj);
        return "index";
    }

    @PostMapping("/login")  
    public String handleLoginBtn(Counsellor counsellor, HttpServletRequest request, Model model) {
        Counsellor c = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());

        if (c == null) {
            model.addAttribute("emsg", "Invalid Credentials");
            return "index";
        } else {
        	
        	HttpSession session = request.getSession(true);
        	session.setAttribute("counsellorId", c.getCounsellorId());
        	
            DashboardResponse dbobj = counsellorService.getDashboardInfo(c.getCounsellorId());
            model.addAttribute("dashboardInfo", dbobj);
            return "dashboard";
        	
        	//return "redirect:/dasboard";
        }
    }
    
    @GetMapping("/dashboard")
    public String displayDashboard(HttpServletRequest req, Model model) {
    	
    	HttpSession session = req.getSession(false);
    	Integer counsellerId = (Integer) session.getAttribute("counsellorId");
    	DashboardResponse dbresobj = counsellorService.getDashboardInfo(counsellerId);
    	model.addAttribute("dashboardInfo", dbresobj);
    	
    
    	
		return "dashboard";
    	
    }
    
    @GetMapping("/register")
    public String register(Model model) {
    	Counsellor cobj = new Counsellor();
    	model.addAttribute("counsellor", cobj);
		return "register";
    	
    }
    
    @PostMapping("/register")
    public String handleRegistration(Counsellor counsellor, Model model) {
    	
    	Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
    	
    	if (byEmail != null) {
    		model.addAttribute("emsg", "Duplicate Email");
    		return "register";
    		
			
		}
    	
    	
    	boolean isRegister = counsellorService.register(counsellor);
    	
    	if (isRegister) {
			//success
    		model.addAttribute("smsg", "Registration success...!!");
		}
    	
    	else {
    		model.addAttribute("emsg", "Registration Failed");
			
		}
    	return "register";
    	
    	
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
    	HttpSession session = req.getSession(false);
    	session.invalidate();
    	return "redirect:/";
    	
    }
}

package cyb.rms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectionController {
	
	@RequestMapping("/empl")
	public String empPage(){
		return "../app/emp-manager/index";
	}
	
	@RequestMapping("/user")
	public String userPage(){
		return "../app/user/index";
	}
	

	@RequestMapping("/admin")
	public String adminPage(){
		return "../app/admin/index";
	}

}

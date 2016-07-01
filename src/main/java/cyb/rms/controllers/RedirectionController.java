package cyb.rms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectionController {

	@RequestMapping(path="/admin")
	public String adminPage(){
		return "app/admin/index";
	}
	
	@RequestMapping(path="/app")
	public String userPage(){
		return "app/user/index";
	}
}

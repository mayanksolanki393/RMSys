package cyb.rms.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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

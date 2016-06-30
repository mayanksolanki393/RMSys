package cyb.rms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectionController {

	@RequestMapping(path="/admin")
	public String adminPage(){
		return "app/emp-manager/index";
	}
}

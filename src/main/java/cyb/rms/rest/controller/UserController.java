package cyb.rms.rest.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.daos.IUserDao;
import cyb.rms.exceptions.DaoException;

@RestController
@RequestMapping(path="/user")
@Transactional
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	IUserDao userDao;
	
	@RequestMapping(method=RequestMethod.GET,path="/current",produces="text/plain")
	public String getUsername(Principal principal) throws DaoException{
		if(principal == null){
			return "Hey! who are you?";
		}
		return principal.getName();
	}

}

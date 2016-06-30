package cyb.rms.rest.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IUserSevrice;

@RestController
@RequestMapping(path="/user")
@Transactional
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	IUserSevrice userService;
	
	@RequestMapping(method=RequestMethod.GET,path="/current",produces="text/plain")
	public String getUsername(Principal principal) throws DaoException{
		if(principal == null){
			return "Hey! who are you?";
		}
		return principal.getName();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public User addUser(@RequestBody User user) throws DaoException
	{
		return userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/{userId}")
	public User removeUser(@PathVariable("userId") long userId) throws DaoException
	{
		User user = userService.findUserById(userId);
		return userService.removeUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public User updateUser(@RequestBody User user) throws DaoException
	{
		return userService.updateUser(user);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getAllUsers() throws DaoException
	{
		return userService.listUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{userId}")
	public User getUserById(@PathVariable("userId") long userId) throws DaoException
	{
		return userService.findUserById(userId);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/check/{username}")
	public User getUserByUsername(@PathVariable("username") String userName) throws DaoException
	{
		return userService.findUsersByUsername(userName);
	}

}

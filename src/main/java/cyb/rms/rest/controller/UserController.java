package cyb.rms.rest.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.User;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@RequestMapping(method=RequestMethod.PUT)
	public User addUser(@RequestBody User user)
	{
		return user;
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/userid")
	public User removeUser(@PathVariable long userId)
	{
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public User updateUser(@RequestBody User user)
	{
		return user;
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Set<User> getAllUsers()
	{
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/userId")
	public User getUserById(@PathVariable long userId)
	{
		return null;
		
	}

}

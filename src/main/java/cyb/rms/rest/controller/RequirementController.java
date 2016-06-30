package cyb.rms.rest.controller;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.*;
import cyb.rms.entities.Project;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IEmployeeService;

@RestController
@RequestMapping(path="/requirement")
public class RequirementController {
	
	@RequestMapping(method=RequestMethod.PUT)
	public Requirement addRequirement(@RequestBody Requirement requirement)
	{
		return requirement;
	}
	@RequestMapping(method=RequestMethod.DELETE)
	public Requirement removeRequirement(@PathVariable long reqId)
	{
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Requirement updateRequirement(@RequestBody Requirement requirement)
	{
		return requirement;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/projId")
	public Set<Requirement> getAllRequirementsByProjId(@PathVariable long projId)
	{
		return null;
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/reqId")
	public Requirement getRequirementsById(@PathVariable long reqId)
	{
		return null;
		
	}
	

}

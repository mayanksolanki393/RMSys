package cyb.rms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.exceptions.DaoException;
import cyb.rms.rest.view.RequirementView;
import cyb.rms.services.IProjectService;
import cyb.rms.services.IRequirementService;

@RestController
@RequestMapping(path="/requirement")
@Transactional
public class RequirementController {

	@Autowired
	IRequirementService reqServ;
	
	@Autowired
	IProjectService projServ;
	
	@RequestMapping(method=RequestMethod.PUT)
	public Requirement addRequirement(@RequestBody Requirement requirement) throws DaoException
	{
		return reqServ.addRequirement(requirement);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/{reqId}")
	public Requirement removeRequirement(@PathVariable("reqId") long reqId) throws DaoException
	{
		Requirement req = reqServ.findRequirementById(reqId);
		return reqServ.removeRequirement(req);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Requirement updateRequirement(@RequestBody Requirement requirement) throws DaoException
	{
		return reqServ.updateRequirement(requirement);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	public Requirement getAllRequirementByReqId(@PathVariable("reqId") long reqId) throws DaoException
	{
		return reqServ.findRequirementById(reqId);
	}
	/*
	@RequestMapping(method=RequestMethod.GET, path="/proj/{projId}")
	@JsonView(RequirementView.Minimal.class)
	public List<Requirement> getAllRequirementsByProjId(@PathVariable("projId") long projId) throws DaoException
	{
		Project project = projServ.findProjectById(projId);
		return project.getRequirements();
	}*/
	
	@RequestMapping(method=RequestMethod.PUT, path="/{reqId}")
	public Requirement getRequirementsById(@PathVariable("reqId") long reqId) throws DaoException
	{
		return reqServ.findRequirementById(reqId);
	}
	
}

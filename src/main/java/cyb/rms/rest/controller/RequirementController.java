package cyb.rms.rest.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import cyb.rms.entities.Elaboration;
import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;
import cyb.rms.rest.view.RequirementView;
import cyb.rms.services.IProjectService;
import cyb.rms.services.IRequirementService;
import cyb.rms.services.IUserService;
import cyb.rms.spring.security.ISecuredController;

@RestController
@RequestMapping(path="/requirement")
@Transactional
public class RequirementController implements ISecuredController{

	@Autowired
	IRequirementService reqServ;
	
	@Autowired
	IProjectService projServ;
	
	@Autowired 
	IUserService userServ;
	
	@RequestMapping(method=RequestMethod.PUT)
	@JsonView(RequirementView.Detailed.class)
	public Requirement addRequirement(@RequestBody Requirement requirement,Authentication authentication) throws DaoException
	{
		Project project = projServ.findProjectById(requirement.getProject().getId());
		User creator = userServ.findUsersByUsername(authentication.getName());
		
		requirement.getContributors().add(creator);
		requirement.getCreators().add(creator);
		requirement.setCreatedOn(new Date());
		requirement.setLastModifiedOn(new Date());
		requirement.setProject(project);
		
		return reqServ.addRequirement(requirement);
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/child")
	@JsonView(RequirementView.Minimal.class)
	public Requirement addChildRequirementsByReqId(@RequestBody Requirement requirement,Authentication authentication) throws DaoException
	{

		User creator = userServ.findUsersByUsername(authentication.getName());
		Requirement parent = reqServ.findRequirementById(requirement.getParent().getId());
		Project project = projServ.findProjectById(parent.getProject().getId());
		
		requirement.getContributors().add(creator);
		requirement.getCreators().add(creator);
		requirement.setCreatedOn(new Date());
		requirement.setLastModifiedOn(new Date());
		requirement.setProject(project);
		requirement.setParent(parent);
		
		requirement = reqServ.addRequirement(requirement);
		parent.getChildRequirements().add(requirement);
		reqServ.updateRequirement(parent);
		return requirement;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	public Requirement removeRequirement(@PathVariable("reqId") long reqId) throws DaoException
	{
		Requirement req = reqServ.findRequirementById(reqId);
		return reqServ.removeRequirement(req);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@JsonView(RequirementView.Detailed.class)
	public Requirement updateRequirement(@RequestBody Requirement requirement) throws DaoException
	{
		Requirement updReq = reqServ.findRequirementById(requirement.getId());
		String[] ignoreProperties = {
		            	"id",
		            	"creators",
		            	"contributors",
		            	"createdOn",
		            	"lastModifiedOn",
		            	"elaborations",
		            	"parent",
		            	"files",
		            	"project",
		            	"childRequirements"
						};
		BeanUtils.copyProperties(requirement, updReq,ignoreProperties);
		return reqServ.updateRequirement(updReq);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	@Secured("ROLE_USER")
	public Requirement getRequirementByReqId(@PathVariable("reqId") long reqId) throws DaoException
	{
		Requirement req =  reqServ.findRequirementById(reqId);
		return req;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/child/{reqId}")
	@JsonView(RequirementView.Minimal.class)
	public List<Requirement> getChildRequirementsByReqId(@PathVariable("reqId") long reqId) throws DaoException
	{
		Requirement req =  reqServ.findRequirementById(reqId);
		return req.getChildRequirements();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/proj/{projId}")
	@JsonView(RequirementView.Minimal.class)
	public List<Requirement> getAllRequirementsByProjId(@PathVariable("projId") long projId) throws DaoException
	{
		Project project = projServ.findProjectById(projId);
		return project.getRequirements();
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, path="/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	public Requirement getRequirementsById(@PathVariable("reqId") long reqId) throws DaoException
	{
		return reqServ.findRequirementById(reqId);
	}*/
	
}

package cyb.rms.rest.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;
import cyb.rms.rest.view.RequirementView;
import cyb.rms.services.IProjectService;
import cyb.rms.services.IRequirementService;
import cyb.rms.services.IUserService;

@RestController
@RequestMapping(path = "/requirement")
@Transactional
public class RequirementController {

	@Autowired
	IRequirementService reqServ;

	@Autowired
	IProjectService projServ;

	@Autowired
	IUserService userServ;

	static Logger LOG = Logger.getLogger(RequirementController.class);

	@RequestMapping(method = RequestMethod.PUT)
	@JsonView(RequirementView.Detailed.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Requirement addRequirement(@RequestBody Requirement requirement,
			Authentication authentication) throws DaoException {
		Project project = projServ.findProjectById(requirement.getProject()
				.getId());
		User creator = userServ.findUsersByUsername(authentication.getName());

		requirement.getContributors().add(creator);
		requirement.getCreators().add(creator);
		requirement.setCreatedOn(new Date());
		requirement.setLastModifiedOn(new Date());
		requirement.setProject(project);

		return reqServ.addRequirement(requirement);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/child")
	@JsonView(RequirementView.Minimal.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Requirement addChildRequirementsByReqId(
			@RequestBody Requirement requirement, Authentication authentication)
			throws DaoException {
		User creator = userServ.findUsersByUsername(authentication.getName());
		Requirement parent = reqServ.findRequirementById(requirement
				.getParent().getId());
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Requirement removeRequirement(@PathVariable("reqId") long reqId)
			throws DaoException {
		Requirement req = reqServ.findRequirementById(reqId);
		return reqServ.removeRequirement(req);
	}

	@RequestMapping(method = RequestMethod.POST)
	@JsonView(RequirementView.Detailed.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Requirement updateRequirement(@RequestBody Requirement requirement)
			throws DaoException {
		Requirement updReq = reqServ.findRequirementById(requirement.getId());
		String[] ignoreProperties = { "id", "creators", "contributors",
				"createdOn", "lastModifiedOn", "elaborations", "parent",
				"files", "project", "childRequirements" };
		BeanUtils.copyProperties(requirement, updReq, ignoreProperties);
		return reqServ.updateRequirement(updReq);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{reqId}")
	@JsonView(RequirementView.Detailed.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Requirement getRequirementByReqId(@PathVariable("reqId") long reqId)
			throws DaoException {
		Requirement req = reqServ.findRequirementById(reqId);
		return req;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/child/{reqId}")
	@JsonView(RequirementView.Minimal.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<Requirement> getChildRequirementsByReqId(
			@PathVariable("reqId") long reqId) throws DaoException {
		Requirement req = reqServ.findRequirementById(reqId);
		return req.getChildRequirements();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/proj/{projId}")
	@JsonView(RequirementView.Minimal.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<Requirement> getAllRequirementsByProjId(
			@PathVariable("projId") long projId) throws DaoException {
		Project project = projServ.findProjectById(projId);
		return project.getRequirements();
	}

	/*
	 * @RequestMapping(method=RequestMethod.PUT, path="/{reqId}")
	 * 
	 * @JsonView(RequirementView.Detailed.class) public Requirement
	 * getRequirementsById(@PathVariable("reqId") long reqId) throws
	 * DaoException { return reqServ.findRequirementById(reqId); }
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)// 404
	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public @ResponseBody String handleConflict(
			JpaObjectRetrievalFailureException ex) {
		LOG.error(ex);
		return "We could not find the requirement you are looking for may be it was deleted by a member of your team";
	}

}

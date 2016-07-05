package cyb.rms.rest.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;
import cyb.rms.rest.view.ProjectView;
import cyb.rms.rest.view.ProjectView.ProjectDetailsView;
import cyb.rms.rest.view.UserView;
import cyb.rms.services.IProjectService;
import cyb.rms.services.IUserService;

@RestController
@RequestMapping(path="/project")
@Transactional
public class ProjectController {
	private static final Logger LOG = Logger.getLogger(ProjectController.class);
	
	@Autowired
	IProjectService projService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(method=RequestMethod.PUT)
	@Secured("hasRole('ROLE_ADMIN')")
	public Project addProject(@RequestBody Project project) throws DaoException
	{
		return projService.addProject(project);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/{projId}")
	@Secured("hasRole('ROLE_ADMIN')")
	public Project removeProject(@PathVariable("projId") long projId) throws DaoException
	{
		Project proj = projService.findProjectById(projId);
		return projService.removeProject(proj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Secured("hasRole('ROLE_ADMIN')")
	public Project updateProject(@RequestBody Project project) throws DaoException
	{
		return projService.updateProject(project);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@JsonView(ProjectView.List.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<Project> getAllProjects(Principal principal) throws DaoException
	{
		if(principal != null){
			User u = userService.findUsersByUsername(principal.getName());
			return u.getProjects();
		}
		throw new RuntimeException("user not found");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{projId}")
	@JsonView(ProjectView.ProjectDetailsView.class)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Project getProjectById(@PathVariable("projId") long projId) throws DaoException
	{
		return projService.findProjectById(projId);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)// 404
	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public @ResponseBody String handleConflict(
			JpaObjectRetrievalFailureException ex) {
		LOG.error(ex);
		return "We could not find the project you are looking for may be it was deleted by a member of your team";
	}

}

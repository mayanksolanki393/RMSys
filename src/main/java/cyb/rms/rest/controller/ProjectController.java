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

import cyb.rms.entities.Employee;
import cyb.rms.entities.Project;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IEmployeeService;
import cyb.rms.services.IProjectService;

@RestController
@RequestMapping(path="/project")
public class ProjectController {
	private static final Logger LOG = Logger.getLogger(Project.class);
	
	@Autowired
	IProjectService projService;
	
	@RequestMapping(method=RequestMethod.PUT)
	public Project addProject(@RequestBody Project project) throws DaoException
	{
		return projService.addProject(project);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/{projId}") 
	public Project removeProject(@PathVariable("projId") long projId) throws DaoException
	{
		Project proj = projService.findProjectById(projId);
		return projService.removeProject(proj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Project updateProject(@RequestBody Project project) throws DaoException
	{
		return projService.updateProject(project);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Project> getAllProjects() throws DaoException
	{
		return getAllProjects();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{projId}")
	public Project getProjectById(@PathVariable("projId") long projId) throws DaoException
	{
		return projService.findProjectById(projId);
	}

}

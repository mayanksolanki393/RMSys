package cyb.rms.services;

import java.util.List;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

public interface IProjectService {
	
	public Project addProject(Project proj) throws DaoException;

	public Project removeProject(Project proj) throws DaoException;

	public Project updateProject(Project proj) throws DaoException;

	public List<Project> listProjects() throws DaoException;

	public Project findProjectById(long id) throws DaoException;


}

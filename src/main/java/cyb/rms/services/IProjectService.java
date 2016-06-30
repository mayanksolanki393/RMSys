package cyb.rms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

@Service
public interface IProjectService {
	
	public Project addProject(Project proj) throws DaoException;

	public Project removeProject(Project proj) throws DaoException;

	public Project updateProject(Project proj) throws DaoException;

	public List<Project> listProjects() throws DaoException;

	public Project findProjectById(long id) throws DaoException;

}

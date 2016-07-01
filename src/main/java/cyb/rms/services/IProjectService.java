package cyb.rms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.*;
import cyb.rms.exceptions.DaoException;

@Service
@Transactional
public interface IProjectService {
	
	public Project addProject(Project proj) throws DaoException;

	public Project removeProject(Project proj) throws DaoException;

	public Project updateProject(Project proj) throws DaoException;

	@Transactional(readOnly=true)
	public List<Project> listProjects() throws DaoException;

	@Transactional(readOnly=true)
	public Project findProjectById(long id) throws DaoException;

}

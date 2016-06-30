package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cyb.rms.entities.Project;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IProjectDao {

	@Transactional
	public Project addProject(Project prj) throws DaoException;
	
	@Transactional
	public Project updateProject(Project prj) throws DaoException;
	
	@Transactional
	public Project removeProject(Project prj) throws DaoException;

	@Transactional(readOnly=true)
	public List<Project> listProjects() throws DaoException;

	@Transactional(readOnly=true)
	public Project getProjectById(long prjId) throws DaoException;

	@Transactional
	public List<Project> getProjectsByTitle(String prjTitle) throws DaoException;
	
}

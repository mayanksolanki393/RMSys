package cyb.rms.daos.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cyb.rms.daos.IGenericDao;
import cyb.rms.daos.IProjectDao;
import cyb.rms.entities.Project;
import cyb.rms.enums.RmsEnums.ProjectStatus;
import cyb.rms.exceptions.DaoException;

@Repository
public class ProjectDao implements IProjectDao {

	@Autowired
	@Qualifier(value="projectGenericDao")
	IGenericDao<Project, Long> projectGenericDao;
	
	@Override
	public Project addProject(Project prj) throws DaoException {
		
		return projectGenericDao.save(prj);
	}

	@Override
	public Project updateProject(Project prj) throws DaoException {
		
		return projectGenericDao.update(prj);
	}

	@Override
	public Project removeProject(Project prj) throws DaoException {
		
		prj.setStatus(ProjectStatus.DELETED);
		
		return projectGenericDao.update(prj);
	}

	@Override
	public List<Project> listProjects() throws DaoException {
		
		return projectGenericDao.list();
	}

	@Override
	public Project getProjectById(long prjId) throws DaoException {
		
		return projectGenericDao.get(prjId);
	}

	@Override
	public List<Project> getProjectsByTitle(String prjTitle) throws DaoException {
		String query = "Select p from Project p where p.title like '%"+prjTitle+"%' AND p.status NOT IN('DELETED')";
		return projectGenericDao.list(query);
	}

}

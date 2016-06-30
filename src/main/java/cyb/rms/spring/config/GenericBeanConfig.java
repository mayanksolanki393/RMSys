package cyb.rms.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cyb.rms.daos.impls.GenericDao;
import cyb.rms.entities.AppFile;
import cyb.rms.entities.Elaboration;
import cyb.rms.entities.Employee;
import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.exceptions.DaoException;

@Configuration
@EnableTransactionManagement
public class GenericBeanConfig {
	
	@Bean(name="empGenericDao")
	public GenericDao<Employee, Long> getEmployeeGenericDao() throws DaoException{
		return new GenericDao<Employee,Long>(Employee.class);
	}
	
	@Bean(name="appFileGenericDao")
	public GenericDao<AppFile, Long> getAppFileGenericDao() throws DaoException{
		return new GenericDao<AppFile,Long>(AppFile.class);
	}
	
	@Bean(name="projectGenericDao")
	public GenericDao<Project, Long> getProjectGenericDao() throws DaoException{
		return new GenericDao<Project,Long>(Project.class);
	}
	@Bean(name="elaborationGenericDao")
	public GenericDao<Elaboration, Long> getElaborationGenericDao() throws DaoException{
		return new GenericDao<Elaboration,Long>(Elaboration.class);
	}
	@Bean(name="userGenericDao")
	public GenericDao<User, Long> getUserGenericDao() throws DaoException{
		return new GenericDao<User,Long>(User.class);
	}
	@Bean(name="requirementGenericDao")
	public GenericDao<Requirement, Long> getRequirementGenericDao() throws DaoException{
		return new GenericDao<Requirement,Long>(Requirement.class);
	}
}

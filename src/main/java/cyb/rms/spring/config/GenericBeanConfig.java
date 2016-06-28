package cyb.rms.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cyb.rms.daos.impls.GenericDao;
import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;

@Configuration
@EnableTransactionManagement
public class GenericBeanConfig {
	
	@Bean(name="empGenericDao")
	public GenericDao<Employee, Long> getEmployeeGenericDao() throws DaoException{
		return new GenericDao<Employee,Long>(Employee.class);
	}
}

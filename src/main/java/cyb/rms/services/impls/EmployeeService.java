package cyb.rms.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyb.rms.daos.IEmployeeDao;
import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IEmployeeService;

@Service
//we will use the service class for data validation afterwards
public class EmployeeService implements IEmployeeService{

	@Autowired
	IEmployeeDao empDao;

	@Override
	public Employee addEmployee(Employee emp) throws DaoException {
		return empDao.addEmployee(emp);
	}

	@Override
	public Employee removeEmployee(Employee emp) throws DaoException {
		return empDao.removeEmployee(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) throws DaoException {
		return empDao.updateEmployee(emp);
	}

	@Override
	public List<Employee> listEmployees() throws DaoException {
		return empDao.listEmployees();
	}

	@Override
	public Employee findEmployeeById(long id) throws DaoException {
		return empDao.getEmployee(id);
	}

	@Override
	public List<Employee> findEmployeesByName(String empName) throws DaoException {
		return empDao.getEmployeesByName(empName);
	}
	
}

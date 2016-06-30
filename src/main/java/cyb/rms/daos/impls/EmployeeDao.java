package cyb.rms.daos.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IEmployeeDao;
import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;

@Repository
public class EmployeeDao implements IEmployeeDao {

	@Autowired
	@Qualifier(value="empGenericDao")
	IGenericDao<Employee, Long> empGenereicDao;
	
	@Override
	public Employee addEmployee(Employee emp) throws DaoException {
		//if you write more than this one line please write a test case to test it
		return empGenereicDao.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) throws DaoException {
		//if you write more than this one line please write a test case to test it
		return empGenereicDao.update(emp);
	}

	@Override
	public Employee removeEmployee(Employee emp) throws DaoException {
		//if you write more than this one line please write a test case to test it
		emp = empGenereicDao.get(emp.getId());
		return empGenereicDao.remove(emp);
	}

	@Override
	public List<Employee> listEmployees() throws DaoException {
		//if you write more than this one line please write a test case to test it
		return empGenereicDao.list();
	}

	@Override
	public Employee getEmployee(long empId) throws DaoException {
		//if you write more than this one line please write a test case to test it
		return empGenereicDao.get(empId);
	}

	@Override
	public List<Employee> getEmployeesByName(String empName) throws DaoException {
		//a test case has been written for this pleas take a look
		String query = "Select e from Employee e where concat(e.firstName, ' ',e.lastName) like '%"+empName+"%'";
		return empGenereicDao.list(query);
	}

}

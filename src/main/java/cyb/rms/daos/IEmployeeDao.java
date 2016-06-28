package cyb.rms.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;

@Repository
public interface IEmployeeDao {
	
	@Transactional
	public Employee addEmployee(Employee emp) throws DaoException;
	
	@Transactional
	public Employee updateEmployee(Employee emp) throws DaoException;
	
	@Transactional
	public Employee removeEmployee(Employee emp) throws DaoException;

	@Transactional(readOnly=true)
	public List<Employee> listEmployees() throws DaoException;

	@Transactional(readOnly=true)
	public Employee getEmployee(long empId) throws DaoException;

	@Transactional
	public List<Employee> getEmployeesByName(String empName) throws DaoException;

}

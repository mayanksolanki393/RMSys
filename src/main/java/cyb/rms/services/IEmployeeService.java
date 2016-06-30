package cyb.rms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;

//Service layer will not have any hibernate logic
@Service
public interface IEmployeeService {

	public Employee addEmployee(Employee emp) throws DaoException;

	public Employee removeEmployee(Employee emp) throws DaoException;

	public Employee updateEmployee(Employee emp) throws DaoException;

	public List<Employee> listEmployees() throws DaoException;

	public Employee findEmployeeById(long id) throws DaoException;

	public List<Employee> findEmployeesByName(String empName) throws DaoException;
}

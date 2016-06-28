package cyb.rms.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IEmployeeService;

@RestController
@RequestMapping(path="/emp")
//we will use controllers to add rest support and to configure security
public class EmployeeController {

	@Autowired
	IEmployeeService empService;
	
	private static final Logger LOG = Logger.getLogger(EmployeeController.class);
	
	@RequestMapping(method=RequestMethod.PUT)
	public Employee addEmployee(@RequestBody Employee emp) throws DaoException{
		return empService.addEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Employee updateEmployee(@RequestBody Employee emp) throws DaoException{
		return empService.updateEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/{id}")
	public Employee removeEmployee(@PathVariable("id") long id) throws DaoException{
		Employee emp = empService.findEmployeeById(id);
		return empService.removeEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Employee> listEmployees() throws DaoException{
		return empService.listEmployees();
	}

	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public Employee findEmployeeById(@PathVariable("id") long id) throws DaoException{
		return empService.findEmployeeById(id);
	}
	
	@RequestMapping(params="name",method=RequestMethod.GET)
	public List<Employee> findEmployeeByName(@RequestParam("name") String name) throws DaoException{
		return empService.findEmployeesByName(name);
	}
}
package cyb.rms.daos.test;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IEmployeeDao;
import cyb.rms.entities.Employee;
import cyb.rms.exceptions.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class EmpDaoTest {

	@Autowired
	IEmployeeDao empDao;

	@Test
	@Rollback(value=true)
	public void getListByNameTest() throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);
		
		// add a sample employee
		empDao.addEmployee(emp);
		
		//get employee by name 
		List<Employee> emps = empDao.getEmployeesByName(emp.getFirstName());
		
		//fail test if result doesnot contains emp
		if(!emps.contains(emp)){
			Assert.fail();
		}
	
	}
	
	@Test
	@Rollback(value=true)
	public void deleteEmployee() throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);
		
		// add a sample employee
		empDao.addEmployee(emp);
		
		//get employee by name 
		empDao.removeEmployee(emp);
		
		//fail test if result doesnot contains emp
		try{
			empDao.getEmployee(emp.getId());
			Assert.fail();
		}
		catch(Exception ex){
			//test passed
		}
	}
}

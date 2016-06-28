package cyb.rms.daos.test;

import java.text.SimpleDateFormat;

import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class GenricDoaTest {

	@Autowired
	@Qualifier(value="empGenericDao")
	IGenericDao<Employee, Long> empDao;

	@Test
	@Rollback(value = true)
	public void saveTest() throws Exception {
		// creating new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		// saving employee
		emp = empDao.save(emp);

		// retrieving employee
		Employee emp2 = empDao.get((long) emp.getId());

		// checking all fields match
		Assert.assertEquals(emp.getFirstName(), emp2.getFirstName());
		Assert.assertEquals(emp.getLastName(), emp2.getLastName());
		Assert.assertEquals(emp.getSalary(), emp2.getSalary(), 5);
		Assert.assertEquals(emp.getDateOfBirth(), emp2.getDateOfBirth());
	}

	@Test
	@Rollback(value = true)
	public void updateTest() throws Exception {

		// creating a new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		// saving employee
		emp = empDao.save(emp);

		// updating employee
		emp.setFirstName("Saurabh");
		emp.setSalary(60000.23);
		emp.setLastName("Pandey");

		empDao.update(emp);

		// retrieving updated employee from db
		Employee updEmployee = empDao.get(emp.getId());

		// checking updates
		Assert.assertEquals(emp.getFirstName(), updEmployee.getFirstName());
		Assert.assertEquals(emp.getLastName(), updEmployee.getLastName());
		Assert.assertEquals(emp.getSalary(), updEmployee.getSalary(), 5);
	}

	@Test
	@Rollback(value = true)
	public void deleteTest() throws Exception {

		// creating a new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		// saving employee
		emp = empDao.save(emp);

		// updating employee
		empDao.remove(emp);

		try {
			emp = empDao.get(emp.getId());
			Assert.fail();
		} catch (EntityNotFoundException ex) {

		}
	}

	@Test
	@Rollback(value = true)
	public void listTest() throws Exception {

		// creating a new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		Employee emp2 = new Employee();
		emp2.setFirstName("Abhisehk");
		emp2.setLastName("Arya");
		emp2.setDateOfBirth(sdf.parse("23-04-1993"));
		emp2.setSalary(50000.98);

		// saving employee
		empDao.save(emp);
		empDao.save(emp2);

		if (empDao.list().size() < 2) {
			Assert.fail();
		}

	}

	@Test
	@Rollback(value = true)
	public void uniqueQueryTest() throws Exception {

		// creating a new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		empDao.save(emp);

		String query = "Select e from Employee e where e.id = " + emp.getId();

		Employee emp2 = empDao.getUniqueResult(query);

		// checking all fields match
		Assert.assertEquals(emp.getFirstName(), emp2.getFirstName());
		Assert.assertEquals(emp.getLastName(), emp2.getLastName());
		Assert.assertEquals(emp.getSalary(), emp2.getSalary(), 5);
		Assert.assertEquals(emp.getDateOfBirth(), emp2.getDateOfBirth());

	}

	@Test
	@Rollback(value = true)
	public void listQueryTest() throws Exception {

		// creating a new employee
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);

		Employee emp2 = new Employee();
		emp2.setFirstName("Abhisehk");
		emp2.setLastName("Arya");
		emp2.setDateOfBirth(sdf.parse("23-04-1993"));
		emp2.setSalary(50000.98);

		// saving employee
		empDao.save(emp);
		empDao.save(emp2);

		String query = "Select e from Employee e";
		
		if (empDao.list(query).size() < 2) {
			Assert.fail();
		}

	}
}

package cyb.rms.daos.test;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class EmployeeTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Employee emp = new Employee();
		emp.setFirstName("Mayank");
		emp.setLastName("Solanki");
		emp.setDateOfBirth(sdf.parse("03-05-1993"));
		emp.setSalary(50000.98);
		
		em.persist(emp);
	
	}
}

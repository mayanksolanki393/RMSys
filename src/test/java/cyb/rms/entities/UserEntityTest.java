package cyb.rms.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class UserEntityTest {

	@Autowired
	@Qualifier("userGenericDao")
	IGenericDao<User, Long> userGenericDao;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		User user = new User("mayank393","pass", "mayankso@cybage.com", Role.ROLE_ADMIN, "RMS Administrator", "Java", null);
		userGenericDao.save(user);
	}
}

package cyb.rms.daos.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IUserDao;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class UserDaoTest {
	@Autowired
	IUserDao userDao;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		String username = "mayank393"+new Date();
		User user = new User(username,"pass", "mayankso@cybage.com", Role.ROLE_ADMIN, "RMS Administrator", "Java", null);
		userDao.saveUser(user);
		
		User user2 = userDao.getUserByUsername(username);
		Assert.assertEquals(user.getEmail(),user2.getEmail());
		Assert.assertEquals(user.getPassword(),user2.getPassword());
		Assert.assertEquals(user.getTechnology(),user2.getTechnology());
		Assert.assertEquals(user.getProjects(),user2.getProjects());
		Assert.assertEquals(user.getRole(),user2.getRole());
		Assert.assertEquals(user.getId(),user2.getId());
		Assert.assertEquals(user.getDesignation(),user2.getDesignation());
	}
	
}

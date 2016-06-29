package cyb.rms.daos.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import cyb.rms.entities.Project;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.ProjectStatus;
import cyb.rms.enums.RmsEnums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class ProjectEntityTest {

	@Autowired
	@Qualifier("projectGenericDao")
	IGenericDao<Project, Long> projectGenericDao;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		User creator = new User("mayank393","pass", "mayankso@cybage.com", Role.ADMIN, "RMS Administrator", "Java", null);
		
		User user1 = new User("ajinkyakhar","pass", "ajinkyakhar@cybage.com", Role.USER, "Sr. Software Developer", "Java", null);
		User user2 = new User("saurabhpa","pass", "saurabhpa@cybage.com", Role.USER, "Sr. Software Developer", "Java", null);
		List<User> users = new LinkedList<User>();
		
		users.add(user1);
		users.add(user2);
		users.add(creator);
		
		Project project = new Project("Mis Project", "A project about mis for cybage", "rms", creator, new Date(), new Date(),ProjectStatus.ACTIVE, users);
		
		user1.getProjects().add(project);
		user2.getProjects().add(project);
		creator.getProjects().add(project);
		
		Assert.assertEquals(3, project.getUsers().size());
		projectGenericDao.save(project);
		
		project = projectGenericDao.get(project.getId());
		
		Assert.assertEquals(project.getUsers().size(), 3);
		Assert.assertTrue(project.getUsers().contains(user1));
		Assert.assertTrue(project.getUsers().contains(user2));
		Assert.assertTrue(project.getUsers().contains(creator));
	}
}

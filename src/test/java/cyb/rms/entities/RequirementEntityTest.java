package cyb.rms.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import cyb.rms.entities.AppFile;
import cyb.rms.entities.Elaboration;
import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.Constraints;
import cyb.rms.enums.RmsEnums.ElaborationStatus;
import cyb.rms.enums.RmsEnums.FileStatus;
import cyb.rms.enums.RmsEnums.FileType;
import cyb.rms.enums.RmsEnums.Priority;
import cyb.rms.enums.RmsEnums.ProjectStatus;
import cyb.rms.enums.RmsEnums.RequirementStatus;
import cyb.rms.enums.RmsEnums.Role;
import cyb.rms.enums.RmsEnums.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class RequirementEntityTest {

	@Autowired
	@Qualifier("requireGenericDao")
	IGenericDao<Requirement, Long> requirementGenericDao;
	
	@Test
	@Rollback(value=false)
	public void saveTest() throws Exception{
		User creator = new User("admin","pass", "admin@cybage.com", Role.ROLE_ADMIN, "RMS Administrator", "Java", null);
		
		List<User> creators = new LinkedList<User>();
		creators.add(creator);
		
		User user1 = new User("ajinkyakhar","pass", "ajinkyakhar@cybage.com", Role.ROLE_USER, "Sr. Software Developer", "Java", null);
		User user2 = new User("saurabhpa","pass", "saurabhpa@cybage.com", Role.ROLE_USER, "Sr. Software Developer", "Java", null);
		List<User> users = new LinkedList<User>();
		
		users.add(user1);
		users.add(user2);
		users.add(creator);
		
		Project project = new Project("Mis Project", "A project about mis for cybage", "rms", creator, new Date(), new Date(),ProjectStatus.ACTIVE, users);
		
		user1.getProjects().add(project);
		user2.getProjects().add(project);
		creator.getProjects().add(project);
		
		Assert.assertEquals(3, project.getUsers().size());
		
		//creating constraints
		Set<Constraints> constraints = new HashSet<Constraints>();
		constraints.add(Constraints.TECHNICAL_CONSTRAINTS);
		constraints.add(Constraints.EXTERNAL_CONSTRAINTS);
		
		//creating links
		Set<String> links = new HashSet<String>();
		links.add("http://www.google.com?search=hibernate");
		links.add("http://www.hibenate.org");
		
		//creating elaboration files
		AppFile file1 = new AppFile("FileName.txt", new Date(), FileStatus.ACTIVE, user1, FileType.ELABORATION_FILE,project);
		AppFile file2 = new AppFile("FileName2.txt", new Date(), FileStatus.ACTIVE, user1, FileType.ELABORATION_FILE,project);
		
		List<AppFile> elaboFiles1 = new LinkedList<AppFile>();
		elaboFiles1.add(file1);
		elaboFiles1.add(file2);
		Assert.assertEquals(2, elaboFiles1.size());
		
		//creating elaboration
		Elaboration elaboration1 = new Elaboration("add user requirement","elaboration description" ,user1, new Date(), new Date(), ElaborationStatus.ACTIVE, elaboFiles1);
		
		//creating elaboration files
		AppFile file3 = new AppFile("FileName3.txt", new Date(), FileStatus.ACTIVE, user2, FileType.ELABORATION_FILE,project);
		AppFile file4 = new AppFile("FileName4.txt", new Date(), FileStatus.ACTIVE, user2, FileType.ELABORATION_FILE,project);
				
		List<AppFile> elaboFiles2 = new LinkedList<AppFile>();
		elaboFiles2.add(file3);
		elaboFiles2.add(file4);
		Assert.assertEquals(2, elaboFiles2.size());
				
		//creating elaboration
		Elaboration elaboration2 = new Elaboration("add user requirement2","elaboration description2" ,user2, new Date(), new Date(), ElaborationStatus.ACTIVE, elaboFiles2);
		
		List<Elaboration> elaborations = new LinkedList<Elaboration>();
		elaborations.add(elaboration1);
		elaborations.add(elaboration2);
		Assert.assertEquals(2, elaborations.size());
		
		//creating elaboration files
		AppFile file5 = new AppFile("FileName5.txt", new Date(), FileStatus.ACTIVE, creator, FileType.REQUIREMENT_FILE,project);
		AppFile file6 = new AppFile("FileName6.txt", new Date(), FileStatus.ACTIVE, creator, FileType.REQUIREMENT_FILE,project);
		
		List<AppFile> requirFiles = new LinkedList<AppFile>();
		requirFiles.add(file5);
		requirFiles.add(file6);
		Assert.assertEquals(2, requirFiles.size());
		
		//creating requirement
		Requirement requirement = new Requirement("Backend Requirement", "A very robust backend with high flexibility required", "BR", creators, users, new Date(), new Date(),Type.CUST_REQ,constraints , links, elaborations, null, Priority.MAJOR, RequirementStatus.OPEN,requirFiles, project); 
		
		//saving requirement
		requirementGenericDao.save(requirement);
		
		Requirement reqFromDao = requirementGenericDao.get(requirement.getId());
		
		Assert.assertEquals(requirement.getTitle(),reqFromDao.getTitle());
		Assert.assertEquals(requirement.getDescription(),reqFromDao.getDescription());
		Assert.assertEquals(requirement.getShortTitle(),reqFromDao.getShortTitle());
		Assert.assertEquals(requirement.getCreatedOn(),reqFromDao.getCreatedOn());
		Assert.assertEquals(requirement.getLastModifiedOn(),reqFromDao.getLastModifiedOn());
		Assert.assertEquals(requirement.getConstraints(),reqFromDao.getConstraints());
		Assert.assertEquals(requirement.getContributors(),reqFromDao.getContributors());
		Assert.assertEquals(requirement.getCreators(),reqFromDao.getCreators());
		Assert.assertEquals(requirement.getElaborations(),reqFromDao.getElaborations());
		Assert.assertEquals(requirement.getLinks(),reqFromDao.getLinks());
		Assert.assertEquals(requirement.getPriority(),reqFromDao.getPriority());
		Assert.assertEquals(requirement.getProject(),reqFromDao.getProject());
		Assert.assertEquals(requirement.getParent(),reqFromDao.getParent());
		Assert.assertEquals(requirement.getType(),reqFromDao.getType());
		Assert.assertEquals(requirement.getFiles(),reqFromDao.getFiles());
	}
}

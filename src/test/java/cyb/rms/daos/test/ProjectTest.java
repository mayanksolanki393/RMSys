package cyb.rms.daos.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.entities.Elaboration;
import cyb.rms.daos.IEmployeeDao;
import cyb.rms.daos.IGenericDao;
import cyb.rms.entities.AppFile;
import cyb.rms.entities.Project;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.Constraints;
import cyb.rms.enums.RmsEnums.Priority;
import cyb.rms.enums.RmsEnums.ProjectStatus;
import cyb.rms.enums.RmsEnums.RequirementStatus;
import cyb.rms.enums.RmsEnums.Role;
import cyb.rms.enums.RmsEnums.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class ProjectTest {
	@PersistenceContext
	EntityManager em;
	Project prj;
	Requirement req;
	@Autowired
	IGenericDao<Project, Long> projectGenericDao;
	@Autowired
	IGenericDao<Requirement, Long> requirementGenericDao;
	

	@Before
	public void setUp() throws ParseException
	{

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		User usr=new User();
		List<User> userLst=new ArrayList<>();
		Set<Project> prjSet=new HashSet<>();
		prj=new Project();
		req=new Requirement();
		Set<Constraints> setConstraints=new HashSet<>();
		Set<String>links=new HashSet<>();
		links.add("google.com");
		links.add("wikipidea");
		links.add("mkyong");
		setConstraints.add(Constraints.ECONOMICAL_CONSTRAINTS);
		setConstraints.add(Constraints.EXTERNAL_CONSTRAINTS);
		usr.setDesignation("arcitect");
		usr.setEmail("email@gmail");
		usr.setPassword("pass@123");
		usr.setRole(Role.ADMIN);
		usr.setUsername("saurabh");
		usr.setTechnology("java");
		
		prj.setCreatedOn(sdf.parse("29-06-2016"));
		prj.setCreator(usr);
		prj.setDescription("hello india jai hind");
		prj.setLastModifiedOn(sdf.parse("29-06-2016"));
		prj.setShortTitle("my little project");
		prj.setTitle("main project");
		prj.setStatus(ProjectStatus.ACTIVE);
		
		userLst.add(usr);
		prj.setUsers(userLst);
		prjSet.add(prj);
		usr.setProjects(prjSet);
		//em.persist(usr);
		
		req.setConstraints(setConstraints);
		req.setTitle("requirement title");
		req.setDescription("this is requiremnt for add one more functionality");
		req.setCreators(userLst);
		req.setCreatedOn(sdf.parse("30-06-2016"));
		req.setContributors(userLst);
		req.setLastModifiedOn(sdf.parse("30-06-2016"));
		req.setPriority(Priority.CRITICAL);
		req.setLinks(links);
		req.setType(Type.BUG_ISSUE);
		req.setShortTitle("short title");
		req.setStatus(RequirementStatus.ASSIGNED);
		req.setProject(prj);
		req.setParent(req);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testIt1() throws Exception
	{
	
		projectGenericDao.save(prj);
		
	}
	@Test
	@Rollback(value=false)
	public void testIt2() throws Exception
	{
	
		requirementGenericDao.save(req);
	}

}

package cyb.rms.entities;

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
import cyb.rms.entities.AppFile;
import cyb.rms.entities.Elaboration;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.ElaborationStatus;
import cyb.rms.enums.RmsEnums.FileStatus;
import cyb.rms.enums.RmsEnums.FileType;
import cyb.rms.enums.RmsEnums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class ElaborationEntityTest {

	@Autowired
	@Qualifier("elaboGenericDao")
	IGenericDao<Elaboration, Long> elaboGenericDao;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		User user = new User("mayank393","pass", "mayankso@cybage.com", Role.ROLE_ADMIN, "RMS Administrator", "Java", null);
		AppFile file1 = new AppFile("FileName.txt", new Date(), FileStatus.ACTIVE, user, FileType.ELABORATION_FILE,null);
		AppFile file2 = new AppFile("FileName2.txt", new Date(), FileStatus.ACTIVE, user, FileType.ELABORATION_FILE,null);
		
		List<AppFile> files = new LinkedList<AppFile>();
		files.add(file1);
		files.add(file2);
		Assert.assertEquals(2, files.size());
		
		Elaboration elaboration = new Elaboration("add user requirement","elaboration description" , user, new Date(), new Date(), ElaborationStatus.ACTIVE, files);
		elaboGenericDao.save(elaboration);
		
		elaboration = elaboGenericDao.get(elaboration.getId());
		
		Assert.assertEquals(2, elaboration.getFiles().size());
		Assert.assertTrue(files.contains(file1));
		Assert.assertTrue(files.contains(file2));
	}
}

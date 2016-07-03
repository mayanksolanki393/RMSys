package cyb.rms.entities;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cyb.rms.daos.IGenericDao;
import cyb.rms.enums.RmsEnums.FileStatus;
import cyb.rms.enums.RmsEnums.FileType;
import cyb.rms.enums.RmsEnums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@Transactional
public class AppFileEntityTest {

	@Autowired
	@Qualifier("fileGenericDao")
	IGenericDao<AppFile, Long> fileGenericDao;
	
	@Test
	@Rollback(value=true)
	public void saveTest() throws Exception{
		User user = new User("mayank393","pass", "mayankso@cybage.com", Role.ROLE_ADMIN, "RMS Administrator", "Java", null);
		AppFile file = new AppFile("FileName.txt", new Date(), FileStatus.ACTIVE, user, FileType.ELABORATION_FILE,null);	
		fileGenericDao.save(file);
	}
}

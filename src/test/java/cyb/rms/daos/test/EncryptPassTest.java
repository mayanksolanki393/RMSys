package cyb.rms.daos.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cyb.rms.utilities.EncryptWithMd5;

@RunWith(SpringJUnit4ClassRunner.class)
public class EncryptPassTest {
	
	@Test
	public void CheckPass()
	{
		String pass1=EncryptWithMd5.cryptWithMD5("saurabh");
		String pass2=EncryptWithMd5.cryptWithMD5("saurabh");
		String pass3=EncryptWithMd5.cryptWithMD5("Saurabh");
		Assert.assertEquals(pass1, pass2);
		System.out.println("sucess");
		Assert.assertNotEquals(pass1, pass2);
		System.out.println("fail");
		Assert.assertNotEquals(pass1, pass3);
		System.out.println("sucess");
		
	}

}

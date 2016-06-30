package cyb.rms.daos.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class ByCryptTest {
	String hashedPassword;
	BCryptPasswordEncoder passwordEncoder;
	
	@Before
	public void setUp()
	{
		
		passwordEncoder=new BCryptPasswordEncoder();
		 System.out.println("in setup");
	}
	
	@Test
	public void runTest()
	{
		CharSequence pass="saurabh";
		String password = "saurabh";
		String hashedPassword1 = passwordEncoder.encode(password);
		String hashedPassword2 = passwordEncoder.encode(password);
		System.out.println("encrypted password"+hashedPassword);
		//matches(CharSequence,String)
		
		System.out.println(	passwordEncoder.matches(pass,hashedPassword1 ));
		System.out.println("checking for two encrytion of same password");
		Assert.assertEquals(hashedPassword1, hashedPassword2);// it will fail because both hashcode are different
		System.out.println("two hash code are same");
		
		
	}
	
	

}

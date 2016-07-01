package cyb.rms.controller.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-orm-config.xml")
@WebAppConfiguration
public class UserControllerTest{

	private MockMvc mockMvc;

	
	@Autowired
	WebApplicationContext wac;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		System.out.println("In setup");
	}

	protected MockMvc getMockMvc() {

		if (mockMvc == null) {
			throw new RuntimeException("MockMVC has not been initialized");
		}
		return mockMvc;
	}

	protected void initializeMockMvc() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	protected WebApplicationContext getWebApplicationContext() {

		if (wac == null) {
			throw new RuntimeException("WebApplicationContext is null");
		}
		return wac;
	}

	/*
	 * 
	 * <dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
</dependency>
	 * 
	 * */
	@Test
	public void testGetCountryDetails() throws Exception {
		
		System.out.println("In test method");
		
	//	  mockMvc.perform(get("https://www.google.co.in/"));
		this.getMockMvc().perform(get("/user/current"));
						
		 
			
			//System.out.println("\n\n\n"+content);
		 
		 
		 //assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><country><capital>Delhi</capital><country>india</country><currency>Rupees</currency></country>", a);

	}

}

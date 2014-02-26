package hu.sonrisa.spring.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.minidev.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml",
"classpath:test-applicationContext.xml", "classpath:test-securityContect.xml"})
//Setting the profile used to run tests
@ActiveProfiles(profiles = "dev")
public class LoginControllerTest {
	
	@Autowired
	private LoginController loginController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup( loginController ).build();
	}
	
	@Test
	public void testLogin() throws Exception{
		JSONObject loginUser = new JSONObject();
		loginUser.put("username", "palika");
		loginUser.put("password", "password");
		mockMvc.perform(post("/api/authenticate/login.json")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginUser.toString()))
			.andExpect(status().isOk());
	}

}

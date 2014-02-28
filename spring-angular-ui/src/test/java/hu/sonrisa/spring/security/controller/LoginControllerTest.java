package hu.sonrisa.spring.security.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import hu.sonrisa.spring.usermanager.domain.MyUser;
import hu.sonrisa.spring.usermanager.service.MyUserService;
import net.minidev.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations = { "classpath:test-applicationContext.xml",
		"classpath:test-securityContect.xml" })
public class LoginControllerTest {

	@Autowired
	private LoginController loginController;

	@Autowired
	MyUserService myUserService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController)
				.build();
		MyUser palika = new MyUser();
		palika.setActive(true);
		palika.setConfirmPassword("password");
		palika.setFamilyName("Palkó");
		palika.setFirstName("Balázs");
		palika.setPassword("password");
		palika.setUsername("palika");
		when(myUserService.findByName("palika")).thenReturn(palika);
	}

	@Test
	public void testLogin() throws Exception {
		JSONObject loginUser = new JSONObject();
		loginUser.put("username", "palika");
		loginUser.put("password", "password");
		this.mockMvc.perform(
						post("/api/authenticate/login.json").contentType(
								MediaType.APPLICATION_JSON).content(
								loginUser.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.loggedIn").value(true));
	}
	
	/**
	 * In case of a wrong password, loggedIn should be false in the JSON, and the user field
	 * should be null/not Existing
	 * @throws Exception
	 */
	@Test
	public void loginWithWrongPassword() throws Exception{
		JSONObject loginUser = new JSONObject();
		loginUser.put("username", "palika");
		loginUser.put("password", "wrongpassword");
		this.mockMvc.perform(
				post("/api/authenticate/login.json").contentType(
						MediaType.APPLICATION_JSON).content(
						loginUser.toString()))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.loggedIn").value(false))
					.andExpect(jsonPath("$.user").doesNotExist());
	}
	
	/**
	 * Test get current user json service while logged In
	 * @throws Exception 
	 */
	@Test
	public void testGetLoggedInCurrentUser() throws Exception{
		//First log in with the user
		JSONObject loginUser = new JSONObject();
		loginUser.put("username", "palika");
		loginUser.put("password", "password");
		this.mockMvc.perform(
						post("/api/authenticate/login.json").contentType(
								MediaType.APPLICATION_JSON).content(
								loginUser.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				get("/api/authenticate/current-user.json"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.loggedIn").value(true))
			.andExpect(jsonPath("$.username").value("palika"));
		
	}
	
	/**
	 * Test get current user json service while not logged In
	 * @throws Exception 
	 */
	@Test
	public void testGetNotLoggedInCurrentUser() throws Exception{		
		//Clear security context
		SecurityContextHolder.getContext().setAuthentication(null);
		this.mockMvc.perform(
				get("/api/authenticate/current-user.json"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.loggedIn").value(false))
			.andExpect(jsonPath("$.user").doesNotExist());
		
	}

}

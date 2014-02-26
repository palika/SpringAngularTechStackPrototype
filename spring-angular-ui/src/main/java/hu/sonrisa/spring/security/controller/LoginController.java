package hu.sonrisa.spring.security.controller;

import hu.sonrisa.spring.usermanager.domain.MyUser;
import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;
import hu.sonrisa.spring.usermanager.service.MyUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/authenticate/")
public class LoginController {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserService myUserService;

	@RequestMapping(value = "current-user.json", method = RequestMethod.GET)
	@ResponseBody
	public LoginStatus getStatus() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && !auth.getName().equals("anonymousUser")
				&& auth.isAuthenticated()) {
			return new LoginStatus(true, auth.getName(),
					myUserService.findByName(auth.getName()));
		} else {
			return new LoginStatus(false, null, null);
		}
	}

	@RequestMapping(value = "login.json", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public LoginStatus login(@RequestBody LoginUser loginUser) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				loginUser.getUsername(), loginUser.getPassword());
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			MyUser user = myUserService.findByName(auth.getName());
			return new LoginStatus(auth.isAuthenticated(), auth.getName(),
					user);
		} catch (BadCredentialsException e) {
			return new LoginStatus(false, null, null);
		}
	}

	public class LoginStatus {

		private final boolean loggedIn;
		private final String username;
		private final ClientSideUserModel user;

		public LoginStatus(boolean loggedIn, String username, MyUser myUser) {
			this.loggedIn = loggedIn;
			this.username = username;
			this.user = new ClientSideUserModel();
			if(myUser != null){
				this.user.setFirstName(myUser.getFirstName());
				this.user.setLastName(myUser.getFamilyName());
				for(SecurityRoleEntity role: myUser.getSecurityRoleCollection()){
					this.user.add(role);
				}
			}			
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public ClientSideUserModel getUser() {
			return user;
		}

		public String getUsername() {
			return username;
		}
		
		
	}

}

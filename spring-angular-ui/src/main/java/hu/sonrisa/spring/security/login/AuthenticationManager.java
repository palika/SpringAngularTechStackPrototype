package hu.sonrisa.spring.security.login;

import hu.sonrisa.spring.usermanager.domain.MyUser;
import hu.sonrisa.spring.usermanager.service.MyUserService;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationManager implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationManager.class);

	@Autowired
	private MyUserService myUserService;

	public MyUserService getMyUserService() {
		return myUserService;
	}

	public void setMyUserService(MyUserService myUserService) {
		this.myUserService = myUserService;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		MyUser user = myUserService.findByName(username);
		
		if(user == null){
			throw new UsernameNotFoundException("Username was not found");
		}

		Collection<GrantedAuthority> grantedCollection = new ArrayList<GrantedAuthority>();
		grantedCollection.add(new GrantedAuthorityImpl("ROLE_USER"));
		UserDetails userDetails = new User(username, user.getPassword(), grantedCollection);

		LOGGER.info("User logging in with username: ? password: ", username,
				user.getPassword());
		return userDetails;
	}

}

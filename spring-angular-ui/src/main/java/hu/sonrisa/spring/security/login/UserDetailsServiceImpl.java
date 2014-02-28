package hu.sonrisa.spring.security.login;

import hu.sonrisa.spring.usermanager.dao.MyUserDao;
import hu.sonrisa.spring.usermanager.dao.SecurityRoleEntityDao;
import hu.sonrisa.spring.usermanager.domain.MyUser;
import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;
import hu.sonrisa.spring.usermanager.service.MyUserService;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MyUserService myUserService;

//	@Autowired
//	private SecurityRoleEntityDao securityRoleEntityDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails userDetails = null;
		MyUser myUser = myUserService.findByName(username);
		if (myUser == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			return buildUserFromUserEntity(myUser);
		}
	}

	private UserDetails buildUserFromUserEntity(MyUser myUser) {
		Collection authorities = new ArrayList();
		
		for (SecurityRoleEntity role : myUser.getSecurityRoleCollection()) {
			authorities.add(new GrantedAuthorityImpl(role.getName()));
		}
		UserDetails user = new UserDetailsImpl(myUser,myUser.getPassword(), authorities);
//		UserDetails user = new User(myUser.getUsername(), myUser.getPassword(), myUser.getActive(), myUser.getActive(),  myUser.getActive(),  myUser.getActive(), authorities);
		return user;
	}

	public MyUserService getMyUserService() {
		return myUserService;
	}

	public void setMyUserService(MyUserService myUserService) {
		this.myUserService = myUserService;
	}

	

//	public SecurityRoleEntityDao getSecurityRoleEntityDao() {
//		return securityRoleEntityDao;
//	}
//
//	public void setSecurityRoleEntityDao(
//			SecurityRoleEntityDao securityRoleEntityDao) {
//		this.securityRoleEntityDao = securityRoleEntityDao;
//	}

}

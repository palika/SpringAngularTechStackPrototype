package hu.sonrisa.spring.security.login;

import hu.sonrisa.spring.usermanager.domain.MyUser;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	
	private SessionDetails sessionDetails;
	private MyUser myUser;
	
	public UserDetailsImpl( MyUser myUser) {
		this.myUser = myUser;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//TODO: Set Role of the user
		return Arrays.asList(new GrantedAuthorityImpl(""));
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}

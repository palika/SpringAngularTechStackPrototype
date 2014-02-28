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
	private String password;
	private Collection authorities;
	
	public UserDetailsImpl( MyUser myUser, String password, Collection authorities) {
		this.myUser = myUser;
		this.password = password;
		this.authorities = authorities;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	public String getPassword() {
		return this.password;
	}
	public String getUsername() {
		return this.myUser == null?"":this.myUser.getUsername();
	}
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

}

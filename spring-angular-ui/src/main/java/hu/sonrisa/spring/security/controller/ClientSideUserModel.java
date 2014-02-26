package hu.sonrisa.spring.security.controller;

import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a JSON object that's used to store user information
 * on client side in angular.
 * @author Pal
 *
 */
public class ClientSideUserModel {
	
	private String firstName;
	private String lastName;
	private Set<SecurityRoleEntity> roles = new HashSet(0);
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<SecurityRoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<SecurityRoleEntity> roles) {
		this.roles = roles;
	}
	public boolean add(SecurityRoleEntity e) {
		return roles.add(e);
	}
	
	

}

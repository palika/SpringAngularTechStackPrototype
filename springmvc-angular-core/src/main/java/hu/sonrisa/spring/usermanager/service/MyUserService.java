package hu.sonrisa.spring.usermanager.service;

import hu.sonrisa.spring.usermanager.domain.MyUser;

import java.util.List;
import java.util.Set;

public interface MyUserService {
	
	public MyUser findByName(String username);
	
	public List<MyUser> getList();
	
	public void addUser(MyUser user);
	
	public MyUser getUserById(Integer id);
	
	public Set getSecurityRolesForUsername(String username);
	
	public void updateUser(MyUser user);

}

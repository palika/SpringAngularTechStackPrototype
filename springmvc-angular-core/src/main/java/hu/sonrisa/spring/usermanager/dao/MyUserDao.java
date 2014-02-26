package hu.sonrisa.spring.usermanager.dao;

import hu.sonrisa.spring.usermanager.domain.MyUser;

import java.util.List;
import java.util.Set;

public interface MyUserDao {
	
	public List<MyUser> getList();
	
	public void addUser(MyUser user);
	
	public MyUser findByName(String username);
	
	public MyUser getUserById(Integer id);
	
	public void updateUser(MyUser user);
	
	public void removeUserByName(String username);
	
	public Set getSecurityRolesForUsername(String username);

}

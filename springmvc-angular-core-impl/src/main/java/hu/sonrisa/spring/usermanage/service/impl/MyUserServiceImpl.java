package hu.sonrisa.spring.usermanage.service.impl;

import hu.sonrisa.spring.usermanager.dao.MyUserDao;
import hu.sonrisa.spring.usermanager.domain.MyUser;
import hu.sonrisa.spring.usermanager.service.MyUserService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("myUserService")
public class MyUserServiceImpl implements MyUserService {

	@Autowired
	private MyUserDao userDao;

	public MyUser findByName(String username) {
		return userDao.findByName(username);
	}

	public List<MyUser> getList() {
		return userDao.getList();
	}

	@Transactional
	public void addUser(MyUser user) {
		userDao.addUser(user);
	}

	public MyUser getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	public Set getSecurityRolesForUsername(String username) {
		return userDao.getSecurityRolesForUsername(username);
	}

	@Transactional
	public void updateUser(MyUser user) {
		userDao.updateUser(user);
	}

}

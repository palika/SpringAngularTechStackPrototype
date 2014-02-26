package hu.sonrisa.spring.dao.impl;

import hu.sonrisa.spring.usermanager.dao.MyUserDao;
import hu.sonrisa.spring.usermanager.dao.SecurityRoleEntityDao;
import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:springmvc-datasource.xml",
		"classpath:test-applicationContext.xml" })
// Setting the profile used to run tests
@ActiveProfiles(profiles = "dev")
public class BaseDaoTest {

	@Autowired
	protected MyUserDao myUserDao;

	@Autowired
	protected SecurityRoleEntityDao securityRoleEntityDao;

	public MyUserDao getMyUserDao() {
		return myUserDao;
	}

	public void setMyUserDao(MyUserDao myUserDao) {
		this.myUserDao = myUserDao;
	}

	public SecurityRoleEntityDao getSecurityRoleEntityDao() {
		return securityRoleEntityDao;
	}

	public void setSecurityRoleEntityDao(
			SecurityRoleEntityDao securityRoleEntityDao) {
		this.securityRoleEntityDao = securityRoleEntityDao;
	}

}

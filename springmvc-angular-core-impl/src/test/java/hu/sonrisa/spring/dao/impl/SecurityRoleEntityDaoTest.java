package hu.sonrisa.spring.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityRoleEntityDaoTest extends BaseDaoTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRoleEntityDaoTest.class);
	SecurityRoleEntity securityRole;
	
	@Before
	public void setUp(){
		LOGGER.info("Starting SecurityRoleEntityDaoTest");
	}
	
	@Test
	@Transactional
	public void addSecurityRole(){
		this.securityRole = new SecurityRoleEntity();
		this.securityRole.setName("ROLE_Test");
		securityRoleEntityDao.addSecurityRoleEntity(this.securityRole);
		assertNotNull(this.securityRole.getId());
	}
	
	@Test
	@Transactional
	public void getSecurityRole(){
		this.securityRole = new SecurityRoleEntity();
		this.securityRole.setName("ROLE_Test");
		this.securityRoleEntityDao.addSecurityRoleEntity(securityRole);
		SecurityRoleEntity result = securityRoleEntityDao.getSecurityRoleEntityById(this.securityRole.getId());
		assertEquals(result.getName(), this.securityRole.getName());
	}
	
	@Test
	public void listSecurityRoles(){
		List<SecurityRoleEntity> list = securityRoleEntityDao.listSecurityRoleEntity();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Test
	@Transactional
	public void removeSecurityRole(){
		securityRole = new SecurityRoleEntity();
		securityRole.setName("ROLE_Test");
		securityRoleEntityDao.addSecurityRoleEntity(securityRole);
		
		List<SecurityRoleEntity> list = securityRoleEntityDao.listSecurityRoleEntity();
		int oldListSize = list.size();
		securityRoleEntityDao.removeSecurityRoleEntity(securityRole.getId());
		list = securityRoleEntityDao.listSecurityRoleEntity();
		assertTrue(oldListSize == (list.size() + 1));
	}

}

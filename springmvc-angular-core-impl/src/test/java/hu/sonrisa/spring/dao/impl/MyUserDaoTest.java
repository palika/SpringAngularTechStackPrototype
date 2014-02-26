package hu.sonrisa.spring.dao.impl;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hu.sonrisa.spring.usermanager.domain.MyUser;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
public class MyUserDaoTest extends BaseDaoTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDaoTest.class);
	
	private MyUser user = new MyUser();
	
	@Before
	public void setUp(){
		LOGGER.info("Starting MyUserDaoTest");
		this.user.setActive(true);
		this.user.setConfirmPassword("asdasd");
		this.user.setFamilyName("Palko");
		this.user.setFirstName("Balazs");
		this.user.setPassword("asdasd");
		this.user.setUsername("palikaTest");
	}
	
	@Test
	public void getList(){
		List<MyUser> list = myUserDao.getList();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Test
	@Transactional
	public void saveUser(){
		MyUser searchedUser = myUserDao.findByName(this.user.getUsername());
		assertNull(searchedUser);
		myUserDao.addUser(this.user);
		searchedUser = myUserDao.findByName(this.user.getUsername());
		assertNotNull(searchedUser);
		assertEquals(searchedUser.getUsername(), this.user.getUsername());
	}
	
	@Test
	//User palika is instantiated automaticly by insert-data.sql
	public void findUserByName(){
		MyUser user = myUserDao.findByName("palika");
		assertNotNull(user);
		assertEquals(user.getUsername(), "palika");
		assertEquals(user.getPassword(), "password");
	}

}

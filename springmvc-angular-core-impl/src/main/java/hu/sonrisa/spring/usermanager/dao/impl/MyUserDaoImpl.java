package hu.sonrisa.spring.usermanager.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.sonrisa.spring.usermanager.dao.MyUserDao;
import hu.sonrisa.spring.usermanager.domain.MyUser;

@Repository
public class MyUserDaoImpl implements MyUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<MyUser> getList() {
		return entityManager.createQuery("from MyUser", MyUser.class)
				.getResultList();
	}

	public void addUser(MyUser user) {
		entityManager.persist(user);
	}

	public MyUser findByName(String username) {
		try {
			return entityManager
					.createNamedQuery("MyUser.findByUsername", MyUser.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public MyUser getUserById(Integer id) {
		try {
			return entityManager
					.createNamedQuery("MyUser.findById", MyUser.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public void removeUserByName(String username) {
		// TODO Auto-generated method stub

	}

	public Set getSecurityRolesForUsername(String username) {
		MyUser user = findByName(username);
		if (user != null) {
			Set roles = (Set) user.getSecurityRoleCollection();
			if (roles != null && roles.size() > 0) {
				return roles;
			}
		}
		return null;
	}

	public void updateUser(MyUser user) {
		entityManager.persist(user);
	}
}

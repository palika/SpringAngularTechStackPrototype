package hu.sonrisa.spring.usermanager.dao.impl;

import hu.sonrisa.spring.usermanager.dao.SecurityRoleEntityDao;
import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SecurityRoleEntityDaoImpl implements SecurityRoleEntityDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	public void addSecurityRoleEntity(SecurityRoleEntity securityRoleEntity) {
		entityManager.persist(securityRoleEntity);
	}

	public List<SecurityRoleEntity> listSecurityRoleEntity() {
		return entityManager.createNamedQuery("SecurityRoleEntity.findAll", SecurityRoleEntity.class).getResultList();
	}

	public SecurityRoleEntity getSecurityRoleEntityById(Integer id) {
		return entityManager.createNamedQuery("SecurityRoleEntity.findById", SecurityRoleEntity.class).setParameter("id", id).getSingleResult();
	}

	public void removeSecurityRoleEntity(Integer id) {
		SecurityRoleEntity role = getSecurityRoleEntityById(id);
		entityManager.remove(role);
	}


}

package hu.sonrisa.spring.usermanage.service.impl;

import hu.sonrisa.spring.usermanager.dao.SecurityRoleEntityDao;
import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;
import hu.sonrisa.spring.usermanager.service.SecurityRoleEntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("securityRoleEntityService")
public class SecurityRoleEntityServiceImpl implements SecurityRoleEntityService{
	
	@Autowired
	SecurityRoleEntityDao securityRoleEntityServiceDao;

	@Transactional
	public void addSecurityRoleEntity(SecurityRoleEntity securityRoleEntity) {
		securityRoleEntityServiceDao.addSecurityRoleEntity(securityRoleEntity);
	}

	public List<SecurityRoleEntity> listSecurityRoleEntity() {
		return securityRoleEntityServiceDao.listSecurityRoleEntity();
	}
	
	@Transactional
	public void removeSecurityRoleEntity(Integer id) {
		securityRoleEntityServiceDao.removeSecurityRoleEntity(id);
		
	}

	public SecurityRoleEntity getSecurityRoleEntityById(Integer id) {
		return securityRoleEntityServiceDao.getSecurityRoleEntityById(id);
	}

}

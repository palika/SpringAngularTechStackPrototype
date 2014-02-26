package hu.sonrisa.spring.usermanager.service;

import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import java.util.List;

public interface SecurityRoleEntityService {

	public void addSecurityRoleEntity(SecurityRoleEntity securityRoleEntity);

	public List<SecurityRoleEntity> listSecurityRoleEntity();
	
	public void removeSecurityRoleEntity(Integer id);
	
	public SecurityRoleEntity getSecurityRoleEntityById(Integer id);

}

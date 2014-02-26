package hu.sonrisa.spring.usermanager.dao;

import hu.sonrisa.spring.usermanager.domain.SecurityRoleEntity;

import java.util.List;

public interface SecurityRoleEntityDao {
	
	/**
	 * Save Security Role to DB
	 * @param securityRoleEntity
	 */
	public void addSecurityRoleEntity(SecurityRoleEntity securityRoleEntity);
	
	/**
	 * List the Security Roles
	 * @return
	 */
	public List<SecurityRoleEntity> listSecurityRoleEntity();
	
	/**
	 * Get specified Role
	 * @param id
	 * @return
	 */
	public SecurityRoleEntity getSecurityRoleEntityById(Integer id);
	
	/**
	 * Deletes Security Role
	 * @param id
	 */
	public void removeSecurityRoleEntity(Integer id);

}

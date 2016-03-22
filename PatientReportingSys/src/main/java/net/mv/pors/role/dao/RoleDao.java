package net.mv.pors.role.dao;

import java.util.List;

import net.mv.pors.role.domain.Role;

public interface RoleDao {

	public Role getRole(Long roleId);
	public Role getRole(String roleTitle);
	public List<Role> getAllRoles();
}

package net.mv.pors.role.service;

import java.util.List;

import net.mv.pors.role.dto.RoleDto;

public interface RoleService {

	public RoleDto getRole(Long roleId);
	public RoleDto getRole(String roleTitle);
	public List<RoleDto> getAllRoles();
}

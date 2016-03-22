package net.mv.pors.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mv.pors.role.dao.RoleDao;
import net.mv.pors.role.domain.Role;
import net.mv.pors.role.dto.RoleDto;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Override
	public RoleDto getRole(Long roleId) {
		RoleDto roleDto = new RoleDto(roleDao.getRole(roleId));
		return roleDto;
	}
	@Override
	public RoleDto getRole(String roleTitle) {
		RoleDto roleDto = new RoleDto(roleDao.getRole(roleTitle));
		return roleDto;
	}
	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		
		for(Role role: roles){
			roleDtos.add(new RoleDto(role));
		}
		return roleDtos;
	}

}

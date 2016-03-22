package net.mv.pors.role.dto;

import net.mv.pors.role.domain.Role;

public class RoleDto {

	private Long id;
	private String title;
	
	
	public RoleDto() {
		super();
	}
	public RoleDto(Role role) {
		super();
		this.id = role.getRoleId();
		this.title = role.getTitle();
	}
	
	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", title=" + title + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

package net.mv.pors.role.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.mv.pors.role.dto.RoleDto;
import net.mv.pors.user.domain.User;

@Entity
@Table(name="ROLES")
public class Role {

	@Id
	@Column(name="R_ID")
	private Long roleId;
	private String title;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES",
	joinColumns={@JoinColumn(name="R_ID", referencedColumnName="R_ID")},
	inverseJoinColumns={@JoinColumn(name="U_ID",referencedColumnName="U_ID")})
	private List<User> users;
	
	
	
	public Role() {
		super();
	}
	
	
	public Role(RoleDto roleDto) {
		super();
		this.roleId = roleDto.getId();

	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", title=" + title + "]";
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}

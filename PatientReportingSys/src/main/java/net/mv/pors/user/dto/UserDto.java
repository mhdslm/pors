package net.mv.pors.user.dto;

import net.mv.pors.user.domain.User;

public class UserDto {

	private long id;
	private String username;
	private String password;
	private boolean authenticated;
	private String message;
	private Long roleId;
	private Long clientId;
	
	
	public UserDto() {
		super();
	}
	public UserDto(User user, boolean authenticated, Long roleId) {
		super();
		this.id = user.getUserId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authenticated = authenticated;
		this.roleId = roleId;
	}
	
	
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", authenticated="
				+ authenticated + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	
}

package net.mv.pors.user.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.mv.pors.patient.domain.Patient;
import net.mv.pors.role.domain.Role;
import net.mv.pors.user.dto.UserDto;

@Entity
@Table(name="USERS", uniqueConstraints=@UniqueConstraint(columnNames={"USERNAME"}))
public class User {

	@Id
	@Column(name="U_ID")
	@GeneratedValue
	//@OneToOne(fetch = FetchType.LAZY, mappedBy= "user", cascade = CascadeType.ALL)
	private Long userId;
	private String username;
	private String password;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES",
	joinColumns={@JoinColumn(name="U_ID",referencedColumnName="U_ID")},
	inverseJoinColumns={@JoinColumn(name="R_ID",referencedColumnName="R_ID")})
	private Role role;
	
	public User() {
		super();
	}
	

	public User(UserDto userDto) {
		super();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

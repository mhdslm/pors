package net.mv.pors.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mv.pors.role.dao.RoleDao;
import net.mv.pors.role.domain.Role;
import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.domain.User;
import net.mv.pors.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private RoleDao roleDaoImpl;

	@Override
	public void registerUser(UserDto userDto) {
		User user = new User(userDto);
		Role role = roleDaoImpl.getRole(userDto.getRoleId());
		//System.out.println("The user's role is: " + role);
		user.setRole(role);
		userDaoImpl.registerUser(user);
	}

	@Override
	public UserDto authenticateUser(UserDto userDto) {
		User user = userDaoImpl.retrieveUserByUsername(userDto.getUsername());
		Role userRole = null;
		if (user != null) {
			User userFromDB = userDaoImpl.retrieveUserByUsername(userDto.getUsername());
			userRole = userFromDB.getRole();
			System.out.println("userRole = " + userRole);
			
			UserDto userDtoFromDB = new UserDto(userFromDB, false, userDto.getRoleId());
			
			if (userDtoFromDB != null && userDtoFromDB.getUsername().equals(userDto.getUsername())
					&& userDtoFromDB.getPassword().equals(userDto.getPassword())
					&& userRole.getRoleId().equals(userDto.getRoleId())) {
				userDto.setId(userDtoFromDB.getId());
				userDto.setAuthenticated(true);
			} else {
				userDto.setAuthenticated(false);
			}
		}
		return userDto;
	}

	@Override
	public User findUserByUsername(String username) {
		return userDaoImpl.retrieveUserByUsername(username);
	}

	@Override
	public boolean getUserByUsername(String username) {
		return userDaoImpl.getUserByUsername(username);
	}

	@Override
	public User getUserById(Long userId) {
		return userDaoImpl.getUserById(userId);
	}

}

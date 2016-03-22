package net.mv.pors.user.service;

import net.mv.pors.user.domain.User;
import net.mv.pors.user.dto.UserDto;

public interface UserService {

	public User findUserByUsername(String username);
	public void registerUser(UserDto userDto);
	public UserDto authenticateUser(UserDto userDto);
	public User getUserById(Long userId);
	//For JUnit test
	public boolean getUserByUsername(String username);
}

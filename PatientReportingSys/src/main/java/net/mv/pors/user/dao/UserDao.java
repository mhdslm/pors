package net.mv.pors.user.dao;

import net.mv.pors.user.domain.User;

public interface UserDao {

	//public User getUserByUsername(String username);
	public User retrieveUserByUsername(String username);
	public User getUserById(Long userId);
	//public boolean authenticateUser(User user);
	public void registerUser(User user);
	
	//just for JUnit test
	public boolean getUserByUsername(String username);
}

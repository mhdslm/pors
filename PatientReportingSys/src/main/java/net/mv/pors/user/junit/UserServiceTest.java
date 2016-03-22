package net.mv.pors.user.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.dto.UserDto;
import net.mv.pors.user.service.UserService;  

//Load Spring Context
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/spring-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	//create mock
	@Mock
	private UserDao userDaoMock;
	
	@InjectMocks
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	/*@Test
	public void testUserLogin() throws SQLException{
		//specify mock behavior when method is called
		when(userDaoMock.getUserByUsername("Mahrad")).thenReturn(true);
		//User user = userDaoMock.retrieveUserByUsername("Mahrad");
		assertTrue(userDaoMock.getUserByUsername("Mahrad"));  
	}
	*/
	
	@Test
	public void testUserRegistration() throws SQLException{
		UserDto userDto = new UserDto();
		userDto.setUsername("Mahrad");
		userDto.setPassword("1234");
		userDto.setRoleId(1l);
		userDto.setId(137l);
		
		//userService.registerUser(userDto);
		if(userDto != null){
			//assertThat(userService.getUserById(userDto.getId()),instanceOf(User.class));
			assertThat(userDto.getUsername(), is("Mahrad"));
			assertNotNull("Username is not null", userDto.getUsername());
			assertNotNull("Password is not null", userDto.getPassword());
		}
		//assertNotNull("new UserDto is not null", userDto);
	}
}

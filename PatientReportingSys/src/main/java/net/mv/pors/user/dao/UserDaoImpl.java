package net.mv.pors.user.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.user.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User retrieveUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.createQuery("from User where lower(username) like lower(:username)")
				.setString("username", username).uniqueResult();
		return user;
	}
	
	@Override
	@Transactional
	public void registerUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	@Override
	@Transactional
	public User getUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();

		return (User) session.createQuery("from User where u_id = :userId").setLong("userId", userId).uniqueResult();
	}

	@Override
	@Transactional
	public boolean getUserByUsername(String username) {
		boolean exists = false;
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.createQuery("from User where lower(username) like lower(:username)")
				.setString("username", username).uniqueResult();
		if(user != null){
			exists = true;
		}
		return exists;
	}

}

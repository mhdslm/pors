package net.mv.pors.role.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.role.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Role getRole(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		return (Role) session.get(Role.class,roleId);
	}

	@Override
	@Transactional
	public Role getRole(String roleTitle) {
		Session session = sessionFactory.getCurrentSession();
		return (Role) session.createQuery("from Role where title = :roleTitle").setString("roleTitle", roleTitle)
				.uniqueResult();
	}

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = session.createQuery("from Role").list();
		return roles;
	}

}

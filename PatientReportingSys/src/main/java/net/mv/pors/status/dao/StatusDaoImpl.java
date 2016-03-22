package net.mv.pors.status.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.status.domain.Status;

@Repository
public class StatusDaoImpl implements StatusDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Status getStatus(String appStatus) {
		Session session = sessionFactory.getCurrentSession();
		
		Status status = (Status) session.createQuery("from Status where title = :appStatus")
				.setString("appStatus", appStatus).uniqueResult();
		return status;
	}

}

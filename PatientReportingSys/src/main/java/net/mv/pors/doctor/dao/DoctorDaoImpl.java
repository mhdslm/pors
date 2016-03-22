package net.mv.pors.doctor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.user.domain.User;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveDoctorInfo(Doctor doctor) {
		Session session = sessionFactory.getCurrentSession();

		session.save(doctor);
	}

	@Override
	@Transactional
	public Doctor retrieveDoctorById(Long doctorId) {
		Session session = sessionFactory.getCurrentSession();
		
		Doctor doctor = (Doctor) session.createQuery("from Doctor where d_id = :doctorId")
						.setLong("doctorId", doctorId).uniqueResult();
		return doctor;
	}

	@Override
	@Transactional
	public void addAppointment(Doctor doctor) {
		Session session = sessionFactory.getCurrentSession();
		session.update(doctor);
	}

	@Override
	@Transactional
	public Doctor retrieveDoctorByUser(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		return (Doctor) session.createQuery("from Doctor where u_id = :userId").setLong("userId", userId).uniqueResult();
	}

}

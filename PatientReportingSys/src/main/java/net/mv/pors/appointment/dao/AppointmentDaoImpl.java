package net.mv.pors.appointment.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.patient.domain.Patient;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Appointment createAppointment(Appointment appointment) {
		Session session = sessionFactory.getCurrentSession();

		session.save(appointment);

		//session.get(Appointment.class, 1l);
		// Appointment appointmentFromDb = session
		// System.out.println(appointment.getAppDate());

		return appointment;
	}

	@Override
	@Transactional
	public Appointment actionOnAppointment(Appointment appointment) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Appointment in Dao: " + appointment);
		// appointment.getStatus().setTitle("approved");
		session.update(appointment);
		return appointment;
	}

	@Override
	@Transactional
	public Appointment retrieveAppointmentById(Long appId) {
		Session session = sessionFactory.getCurrentSession();
		return (Appointment) session.get(Appointment.class, appId);
	}

	@Override
	@Transactional
	public List<Appointment> retreiveAllAppointmentsByDoctorId(Long doctorId) {
		Session session = sessionFactory.getCurrentSession();
		List<Appointment> appointments = session.createQuery("from Appointment where d_id = :doctorId")
				.setLong("doctorId", doctorId).list();
		return appointments;
	}

	@Override
	@Transactional
	public List<Appointment> retireveAppointmentsByPatient(Patient patient) {
		Session session = sessionFactory.getCurrentSession();

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments = session.createQuery("from Appointment where patient = :patient").setEntity("patient", patient)
				.list();
		return appointments;
	}

	@Override
	@Transactional
	public List<Appointment> retreivePendingAppointmentsByDoctorId(Long doctorId) {
		Session session = sessionFactory.getCurrentSession();
		List<Appointment> appointments = session.createQuery("from Appointment where d_id = :doctorId and s_id = 1")
				.setLong("doctorId", doctorId).list();
		return appointments;
	}

}

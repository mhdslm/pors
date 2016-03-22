package net.mv.pors.patient.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mv.pors.doctor.dao.DoctorDao;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.user.domain.User;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DoctorDao doctorDaoImpl;


	@Override
	@Transactional
	public Patient retrievePatientById(Long patientId) {
		Session session = sessionFactory.getCurrentSession();
		Patient patient = (Patient) session.createQuery("from Patient where p_id = :patientId")
				.setLong("patientId", patientId).uniqueResult();
		return patient;
	}

	@Override
	@Transactional
	public boolean savePatientInfo(Patient patient) {
		Session session = sessionFactory.getCurrentSession();
		session.save(patient);
		return false;
	}

	@Override
	@Transactional
	public void addAppointment(Patient patient) {
		Session session = sessionFactory.getCurrentSession();
		// session.persist(patient);
		// System.out.println(patient);
		// return false;
	}

	@Override
	@Transactional
	public Patient retrievePatientByUser(User user) {
		Session session = sessionFactory.getCurrentSession();

		Patient patient = (Patient) session.createQuery("from Patient where user = :user").setEntity("user", user)
				.uniqueResult();
		// System.out.println("Patient Retrieved from DB" + patient);
		return patient;
	}

	@Override
	@Transactional
	public List<Doctor> retrieveDoctorByPatient(Patient patient) {
		Session session = sessionFactory.getCurrentSession();
		//int flag = 0;

		/*System.out.println("PATIENT LOGGED IN");
		System.out.println(patient);
		System.out.println("DOCTORS OF PATIENT LOGGED IN");
		System.out.println(patient.getDoctors());*/
		//List<Long> doctorsList = new ArrayList<Long>();
		//List<Long> doctorsListFull = new ArrayList<Long>();
		List<Doctor> doctors = new ArrayList<Doctor>();

		doctors = session.createQuery("select doctors from Patient patient where patient.patientId = :patientId")
				.setLong("patientId", patient.getPatientId()).list();

		//System.out.println("LIST OF DOCTORS RETURNED");
		//System.out.println(doctors);
		return doctors;
	}

	@Override
	@Transactional
	public List<Doctor> unregisteredDoctorByPatient(Patient patient) {
		Session session = sessionFactory.getCurrentSession();
		int flag = 0;
/*
		System.out.println("PATIENT LOGGED IN");
		System.out.println(patient);
		System.out.println("DOCTORS OF PATIENT LOGGED IN");
		System.out.println(patient.getDoctors());*/
		List<Long> doctorsList = new ArrayList<Long>();
		List<Long> doctorsListFull = new ArrayList<Long>();
		List<Doctor> doctors = new ArrayList<Doctor>();

		Criteria cr = session.createCriteria(Doctor.class, "doctor");
		cr.createAlias("doctor.patients", "patient");
		cr.add(Restrictions.eq("patient.patientId", patient.getPatientId()));
		cr.setProjection(Projections.distinct(Projections.property("doctorId")));

		Criteria cr1 = session.createCriteria(Doctor.class, "doctor");
		cr1.setProjection(Projections.distinct(Projections.property("doctorId")));

		doctorsListFull = cr1.list();
		doctorsList = cr.list();

		// System.out.println(doctorsList);
		// System.out.println(doctorsListFull);

		for (Long elementFull : doctorsListFull) {
			// System.out.println("ELEMENTFULL OUTTER LOOP");
			// System.out.println(elementFull);
			flag = 0;

			for (Long element : doctorsList) {
				/*System.out.println("ELEMENT INNER LOOP");
				System.out.println(element);
				System.out.println("IF STATEMENT");*/
				if (element == elementFull) {
					flag = 1;
				}
			}
			if (flag != 1) {
				// System.out.println("Doctor that's not registered to me "+
				// elementFull);
				doctors.add(doctorDaoImpl.retrieveDoctorById(elementFull));
			}
		}

		// System.out.println("LIST OF DOCTORS RETURNED");
		// System.out.println(doctors);
		return doctors;
	}

	@Override
	@Transactional
	public Patient retrievePatientByUserId(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		return (Patient) session.createQuery("from Patient where u_id = :userId").setLong("userId", userId)
				.uniqueResult();

	}

	@Override
	@Transactional
	public boolean registerToDoctor(Patient patient, Doctor doctor) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Patient: " + patient);
		System.out.println("Patient's doctos: "+patient.getDoctors());
		
		patient.getDoctors().add(doctor);

		session.update(patient);

		return false;
	}

	@Override
	@Transactional
	public boolean unregisterFromDoctor(Patient patient, Doctor doctor) {
		Session session = sessionFactory.getCurrentSession();

		 patient.getDoctors().remove(doctor);
		
		session.update(patient);
		

		return false;
	}

}

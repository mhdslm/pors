package net.mv.pors.patient.dao;

import java.util.List;

import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.user.domain.User;

public interface PatientDao {
	
	public Patient retrievePatientById(Long patientId);
	public Patient retrievePatientByUser(User user);
	public Patient retrievePatientByUserId(Long id);
	public boolean savePatientInfo(Patient patient);
	public void addAppointment(Patient patient);
	public List<Doctor> retrieveDoctorByPatient(Patient patient);
	public List<Doctor> unregisteredDoctorByPatient(Patient patient);
	public boolean registerToDoctor (Patient patient, Doctor doctor);
	public boolean unregisterFromDoctor(Patient patient, Doctor doctor);
	//public void createAppointmentForPatient(Patient Patient);

}

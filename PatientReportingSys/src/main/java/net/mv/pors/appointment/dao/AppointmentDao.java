package net.mv.pors.appointment.dao;

import java.util.List;

import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.patient.domain.Patient;

public interface AppointmentDao {
	public Appointment retrieveAppointmentById(Long appId);
	public Appointment createAppointment(Appointment appointment);

	public List<Appointment> retireveAppointmentsByPatient(Patient patient);

	public Appointment actionOnAppointment(Appointment appointment);
	public List<Appointment> retreiveAllAppointmentsByDoctorId(Long doctorId);
	public List<Appointment> retreivePendingAppointmentsByDoctorId(Long doctorId);
	
}

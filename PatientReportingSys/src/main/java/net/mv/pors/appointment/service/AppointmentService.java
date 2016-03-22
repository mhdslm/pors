package net.mv.pors.appointment.service;

import java.util.List;

import net.mv.pors.appointment.dto.AppointmentDto;
import net.mv.pors.patient.dto.PatientDto;

public interface AppointmentService {

	public AppointmentDto findAppointmentById(Long appId);
	public void makeAppointment(AppointmentDto appointmentDto);
	public List<AppointmentDto> gatherAppointmentsByPatient(PatientDto patientDto);
	public void actionOnAppointment(AppointmentDto appointmentDto);
	public List<AppointmentDto> findAllAppointments(Long doctorId);
	public List<AppointmentDto> findPendingAppointments(Long doctorId);
	public boolean unregisterPatientFromDoctor (String username, Long doctorId);
	
	//================= mail methods ======================
	public void sendPreConfiguredMail(String message);
	public void sendEmailNotification(String to, String subject, String body);
}

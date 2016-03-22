package net.mv.pors.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.mv.pors.appointment.dao.AppointmentDao;
import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.appointment.dto.AppointmentDto;
import net.mv.pors.doctor.dao.DoctorDao;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.dao.PatientDao;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.status.dao.StatusDao;
import net.mv.pors.status.domain.Status;
import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.domain.User;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDaoImpl;

	@Autowired
	private StatusDao statusDaoImpl;

	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private PatientDao patientDaoImpl;

	@Autowired
	private DoctorDao doctorDaoImpl;

	// ============== mail variables ===================

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	// ============= end of mail variables ==============

	@Override
	public void makeAppointment(AppointmentDto appointmentDto) {

		User user = userDaoImpl.retrieveUserByUsername(appointmentDto.getUsername());
		System.out.println(user);
		Patient patient = patientDaoImpl.retrievePatientByUserId(user.getUserId());

		System.out.println(patient);


		Doctor doctor = doctorDaoImpl.retrieveDoctorById(appointmentDto.getDoctorId());
		
		Appointment appointment = new Appointment(appointmentDto);
		Status appStatus = statusDaoImpl.getStatus("pending");

		appointment.setStatus(appStatus);
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointmentDaoImpl.createAppointment(appointment);// returns appointment

	}

	@Override
	public void actionOnAppointment(AppointmentDto appointmentDto) {
		Appointment appointment = appointmentDaoImpl.retrieveAppointmentById(appointmentDto.getAppId());
		Status appStatus = statusDaoImpl.getStatus(appointmentDto.getStatus());
		System.out.println("App status in service: " + appStatus);
		System.out.println("Appointment in service: " + appointment);

		appointment.setStatus(appStatus);

		// System.out.println("Appointment's status: " +
		// appointment.getStatus());
		appointmentDaoImpl.actionOnAppointment(appointment);
	}

	@Override
	public AppointmentDto findAppointmentById(Long appId) {
		return new AppointmentDto(appointmentDaoImpl.retrieveAppointmentById(appId));
	}

	@Override
	public List<AppointmentDto> findAllAppointments(Long doctorId) {
		List<Appointment> appointments = appointmentDaoImpl.retreiveAllAppointmentsByDoctorId(doctorId);
		List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();

		for (Appointment appointment : appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}

	@Override
	public List<AppointmentDto> gatherAppointmentsByPatient(PatientDto patientDto) {

		User user = userDaoImpl.retrieveUserByUsername(patientDto.getUsername());
		Patient patient = patientDaoImpl.retrievePatientByUser(user);

		List<Appointment> appointments = new ArrayList<Appointment>();
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();

		appointments = appointmentDaoImpl.retireveAppointmentsByPatient(patient);

		for (Appointment element : appointments) {
			AppointmentDto appointmentDto = new AppointmentDto(element);
			appointmentsDto.add(appointmentDto);
		}

		return appointmentsDto;
	}

	@Override
	public List<AppointmentDto> findPendingAppointments(Long doctorId) {
		List<Appointment> appointments = appointmentDaoImpl.retreivePendingAppointmentsByDoctorId(doctorId);
		List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();

		for (Appointment appointment : appointments) {
			appointmentDtos.add(new AppointmentDto(appointment));
		}
		return appointmentDtos;
	}

	/**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    
	@Override
	public void sendEmailNotification(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
	}

	@Override
	public boolean unregisterPatientFromDoctor(String username, Long doctorId) {
		
		User user = userDaoImpl.retrieveUserByUsername(username);
		
		Patient patient = patientDaoImpl.retrievePatientByUserId(user.getUserId());
		
		Doctor doctor = doctorDaoImpl.retrieveDoctorById(doctorId);
		
		patientDaoImpl.unregisterFromDoctor(patient, doctor);		
		return false;
	}
}

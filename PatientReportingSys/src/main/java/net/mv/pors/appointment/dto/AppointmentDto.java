package net.mv.pors.appointment.dto;

import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.user.domain.User;

public class AppointmentDto {

	private Long appId;
	private String appDate;
	private String reason;
	private String username;
	private Long doctorId;
	private Long patientId;
	private Patient patient;
	private Doctor doctor;

	private User user;
	private String status;

	public AppointmentDto() {
		super();
	}

	public AppointmentDto(Appointment appointment) {
		super();
		this.appId = appointment.getAppId();
		this.appDate = appointment.getAppDate();
		this.reason = appointment.getReason();
		/*
		 * this.doctor = appointment.getDoctor(); this.patient =
		 * appointment.getPatient();
		 */
		this.doctorId = appointment.getDoctor().getDoctorId();
		this.patientId = appointment.getPatient().getPatientId();
		//this.doctorId = appointment.getDoctor().getDoctorId();
		this.status = appointment.getStatus().getTitle();
	}

	
	@Override
	public String toString() {
		return "AppointmentDto [appId=" + appId + ", appDate=" + appDate + ", reason=" + reason + ", username="
				+ username + ", doctorId=" + doctorId + ", patientId=" + patientId + ", user=" + user + ", status="
				+ status + "]";
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

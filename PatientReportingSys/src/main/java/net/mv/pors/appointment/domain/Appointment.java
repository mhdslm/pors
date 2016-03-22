package net.mv.pors.appointment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.mv.pors.appointment.dto.AppointmentDto;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.status.domain.Status;

@Entity
@Table(name = "APPOINTMENT", schema = "SPRINGADMIN")
public class Appointment {

	@Id
	@Column(name = "A_ID")
	@SequenceGenerator(allocationSize = 1, sequenceName = "APP_SEQ", name = "appSeq")
	@GeneratedValue(generator = "appSeq", strategy = GenerationType.SEQUENCE)
	private Long appId;
	@Column(name = "APP_DATE")
	private String appDate;
	@Column(name = "REASON")
	private String reason;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "APPOINTMENT_STATUS", joinColumns = {
			@JoinColumn(name = "A_ID", referencedColumnName = "A_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "S_ID", referencedColumnName = "S_ID") })
	private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="P_ID", nullable=false)
	private Patient patient;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="D_ID", nullable=false)
	private Doctor doctor;

	public Appointment() {
		super();
	}

	public Appointment(AppointmentDto appointmentDto) {
		super();
		this.appId = appointmentDto.getAppId();
		this.appDate = appointmentDto.getAppDate();
		this.reason = appointmentDto.getReason();
	}

	@Override
	public String toString() {
		return "Appointment [appId=" + appId + ", appDate=" + appDate + ", reason=" + reason + "]";
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
	

}

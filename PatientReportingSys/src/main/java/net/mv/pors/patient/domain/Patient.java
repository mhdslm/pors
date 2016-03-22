package net.mv.pors.patient.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.patient.dto.PatientDto;
import net.mv.pors.user.domain.User;

@Entity
@Table(name = "PATIENT_INFO", schema = "SPRINGADMIN")
public class Patient {

	@Id
	@Column(name = "P_ID")
	@SequenceGenerator(allocationSize = 1, sequenceName = "PATIENT_INFO_SEQ", name = "patientInfoSeq")
	@GeneratedValue(generator = "patientInfoSeq", strategy = GenerationType.SEQUENCE)
	private Long patientId;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNumber;
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "U_ID")
	private User user;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "PATIENT_DOCTOR", joinColumns = {
	@JoinColumn(name = "P_ID", referencedColumnName = "P_ID") }, inverseJoinColumns = {
	@JoinColumn(name = "D_ID", referencedColumnName = "D_ID") })
	private Set<Doctor> doctors;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
	private Set<Appointment> appointments;

	public Patient(PatientDto patientDto) {
		super();
		this.firstName = patientDto.getFirstName();
		this.lastName = patientDto.getLastName();
		this.street = patientDto.getStreet();
		this.city = patientDto.getCity();
		this.state = patientDto.getState();
		this.zipcode = patientDto.getZipcode();
		this.phoneNumber = patientDto.getZipcode();
		this.email = patientDto.getEmail();

	}

	public Patient() {
		super();
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", street="
				+ street + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", phoneNumber="
				+ phoneNumber + ", email=" + email + "]";
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

}

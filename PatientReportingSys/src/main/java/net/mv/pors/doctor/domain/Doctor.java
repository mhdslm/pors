package net.mv.pors.doctor.domain;

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
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.patient.domain.Patient;
import net.mv.pors.user.domain.User;

@Entity
@Table(name = "DOCTOR_INFO")
public class Doctor {

	@Id
	@Column(name = "D_ID")
	@SequenceGenerator(allocationSize = 1, sequenceName = "DOCTOR_INFO_SEQ", name = "doctorInfoSeq")
	@GeneratedValue(generator = "doctorInfoSeq", strategy = GenerationType.SEQUENCE)
	private Long doctorId;
	private String firstName;
	private String lastName;
	private String email;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="U_ID")
	private User user;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE},   mappedBy="doctors")
	private Set<Patient> patients;
	
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name ="DOCTOR_APPOINTMENT",
	joinColumns={@JoinColumn(name="D_ID",referencedColumnName="D_ID")},
	inverseJoinColumns={@JoinColumn(name="A_ID", referencedColumnName="A_ID")})*/
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="doctor")
	private Set<Appointment> appointments;
	
	public Doctor() {
		super();
	}

	public Doctor(DoctorDto doctorDto) {
		super();
		this.doctorId = doctorDto.getDoctorId();
		this.email = doctorDto.getEmail();
		this.firstName = doctorDto.getFirstName();
		this.lastName = doctorDto.getLastName();
	}

	public Doctor(String email) {
		super();
		this.email = email;
	}

	
	
	/*
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", user=" + user + ", patients=" + patients + ", appointments=" + appointments + "]";
	}*/
	
	

	public Long getDoctorId() {
		return doctorId;
	}

	

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", user=" + user + "]";
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doctorId == null) ? 0 : doctorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (doctorId == null) {
			if (other.doctorId != null)
				return false;
		} else if (!doctorId.equals(other.doctorId))
			return false;
		return true;
	}
	
	
	
	
}

package net.mv.pors.doctor.dto;

import net.mv.pors.doctor.domain.Doctor;

public class DoctorDto {

	private Long doctorId;
	private String email;
	private String firstName;
	private String lastName;
	private String username;

	public DoctorDto() {
		super();
	}

	public DoctorDto(Doctor doctor) {
		super();
		this.doctorId = doctor.getDoctorId();
		this.email = doctor.getEmail();
		this.firstName = doctor.getFirstName();
		this.lastName = doctor.getLastName();
	}
	
	

	

	@Override
	public String toString() {


		return "DoctorDto [doctorId=" + doctorId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", username=" + username + "]";
	}

	public Long getDoctorId() {
		return doctorId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

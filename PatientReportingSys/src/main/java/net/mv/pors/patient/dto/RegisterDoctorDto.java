package net.mv.pors.patient.dto;

public class RegisterDoctorDto {

	private Long id;
	private Long registered;
	private String username;

	@Override
	public String toString() {
		return "RegisterDoctorDto [Current id=" + id + ", Doctor being registered=" + registered + ", Current username="
				+ username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegistered() {
		return registered;
	}

	public void setRegistered(Long registered) {
		this.registered = registered;
	}

}

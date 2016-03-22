package net.mv.pors.status.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.mv.pors.appointment.domain.Appointment;
import net.mv.pors.status.dto.StatusDto;

@Entity
@Table(name="STATUS", schema="SPRINGADMIN")
public class Status {

	@Id
	@Column(name="S_ID")
	private Long statusId;
	private String title;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="APPOINTMENT_STATUS",
	joinColumns={@JoinColumn(name="S_ID", referencedColumnName="S_ID")},
	inverseJoinColumns={@JoinColumn(name="A_ID", referencedColumnName="A_ID")})
	private List<Appointment> appointments;
	
	
	public Status() {
		super();
	}
	
	
	public Status(StatusDto statusDto) {
		super();
		this.statusId = statusDto.getId();
		this.title = statusDto.getTitle();
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", title=" + title + "]";
	}

	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}

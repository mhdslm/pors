package net.mv.pors.status.dto;

import net.mv.pors.status.domain.Status;

public class StatusDto {

	private Long id;
	private String title;
	
	
	public StatusDto() {
		super();
	}
	
	public StatusDto(Status status) {
		super();
		this.id = status.getStatusId();
		this.title = status.getTitle();
	}
	
	@Override
	public String toString() {
		return "StatusDto [id=" + id + ", title=" + title + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

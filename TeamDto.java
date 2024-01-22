package com.ust.allowance.dto;

import java.util.Date;

import javax.persistence.Column;

public class TeamDto {
	
	private int teamId;
	private String teamName;
	private String status;
	private Date created;
	private int managerId;
	private String description;
	private int index;
	private String managerName;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "TeamDto [teamId=" + teamId + ", teamName=" + teamName + ", status=" + status + ", created=" + created
				+ ", managerId=" + managerId + ", description=" + description + ", index=" + index + ", managerName="
				+ managerName + "]";
	}
	
	
	

}

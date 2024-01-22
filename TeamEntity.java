package com.ust.allowance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="team")
public class TeamEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "team_id")
	private int teamId;
	@Column(name = "team")
	private String team;
	@Column(name = "team_status")
	private String status;
	@Column(name = "team_created")
	private Date created;
	@Column(name = "manager_id")
	private String managerId;
	@Column(name = "team_description")
	private String teamDescription;
	
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getTeamDescription() {
		return teamDescription;
	}
	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}
	
	
	
	
	
	
	

}

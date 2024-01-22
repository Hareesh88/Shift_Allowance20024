package com.ust.allowance.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "project_id")
	private int projectId;
	@Column(name = "project_name")
	private String projectName;
	@Column(name = "project_status")
	private String status;
	@Column(name = "project_created")
	private Date created;
	@Column(name = "project_description")
	private String projectDescription;
	@Column(name = "project_start_date")
	private Date startDate;
	@Column(name = "project_end_date")
	private Date endDate;
	@Column(name = "project_type")
	private String projectType;
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public void setCreated(Date localDate) {
		this.created = localDate;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	
	
	
	

}

package com.ust.allowance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* Admin Entity
*
* @author 112015
* @since 29 OCT 2023
* @version 1.0.0
*
*/
@Entity
@Table(name="allowance")
public class AllowanceEntity {
	
	@Id
    @Column(name = "allowanceId")
	private int allowanceId;
	@Column(name = "userId")
	private int userId;
	@Column(name = "allowancedate")
	private Date allowanceDate;
	@Column(name = "allowancetype")
	private String allowanceType;
	@Column(name = "allowancestatus")
	private String allowanceStatus;
	@Column(name = "project_id")
	private int projectId;
	@Column(name = "approved_by")
	private String approvedBy;
	@Column(name = "approved_date")
	private Date approvedDate;
	
	public int getAllowanceId() {
		return allowanceId;
	}
	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getAllowanceDate() {
		return allowanceDate;
	}
	public void setAllowanceDate(Date allowanceDate) {
		this.allowanceDate = allowanceDate;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getAllowanceStatus() {
		return allowanceStatus;
	}
	public void setAllowanceStatus(String allowanceStatus) {
		this.allowanceStatus = allowanceStatus;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	
	
	
	
	
	
	
	

}

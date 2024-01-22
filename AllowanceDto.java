package com.ust.allowance.dto;


import java.util.Date;


public class AllowanceDto {
	
	
	/**
	 * 
	 */
	
	private int allowanceId;
	private Date allowanceDate;
	private String status;
	private String type;
	private int userId;
	private String firstName;
	private String lastNmae;
	private String ustr_id;
	private String email;
	private String password;
	private int role_id;
	private String allowanceStatus;
	private String allowanceType;
	private boolean disableButton;
	private int index;
	private String approvedBy;
	private Date approveDate;
	private String strDate;
	

	
	
	public AllowanceDto() {
		
	}
	
	public AllowanceDto(int allowanceId, Date allowanceDate, String status, String type, int userId, String firstName,
			String lastNmae, String ustr_id, String email, String password, int role_id, String allowanceStatus,
			String allowanceType, boolean disableButton, int index) {

		this.allowanceId = allowanceId;
		this.allowanceDate = allowanceDate;
		this.status = status;
		this.type = type;
		this.userId = userId;
		this.firstName = firstName;
		this.lastNmae = lastNmae;
		this.ustr_id = ustr_id;
		this.email = email;
		this.password = password;
		this.role_id = role_id;
		this.allowanceStatus = allowanceStatus;
		this.allowanceType = allowanceType;
		this.disableButton = disableButton;
		this.index = index;
	}
	
	
	
	
	public int getAllowanceId() {
		return allowanceId;
	}
	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}
	public Date getAllowanceDate() {
		return allowanceDate;
	}
	public void setAllowanceDate(Date allowanceDate) {
		this.allowanceDate = allowanceDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastNmae() {
		return lastNmae;
	}
	public void setLastNmae(String lastNmae) {
		this.lastNmae = lastNmae;
	}
	public String getUstr_id() {
		return ustr_id;
	}
	public void setUstr_id(String ustr_id) {
		this.ustr_id = ustr_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getAllowanceStatus() {
		return allowanceStatus;
	}
	public void setAllowanceStatus(String allowanceStatus) {
		this.allowanceStatus = allowanceStatus;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public boolean isDisableButton() {
		return disableButton;
	}
	public void setDisableButton(boolean disableButton) {
		this.disableButton = disableButton;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	
	
	
	@Override
	public String toString() {
		return "AllowanceDto [allowanceId=" + allowanceId + ", allowanceDate=" + allowanceDate + ", status=" + status
				+ ", type=" + type + ", userId=" + userId + ", firstName=" + firstName + ", lastNmae=" + lastNmae
				+ ", ustr_id=" + ustr_id + ", email=" + email + ", password=" + password + ", role_id=" + role_id
				+ ", allowanceStatus=" + allowanceStatus + ", allowanceType=" + allowanceType + ", disableButton="
				+ disableButton + ", index=" + index + "]";
	}

	

	
	
	

	
	
	
	

}

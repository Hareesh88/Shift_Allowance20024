package com.ust.allowance.dto;


public class UserDto {
	
	private int userId;
	private String firstName;
	private String lastNmae;
	private String ustr_id;
	private String email;
	private String password;
	private int role_id;
	private String role;
	private String ustTeam;
	private int projectId;
	private String projectType;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUstTeam() {
		return ustTeam;
	}
	public void setUstTeam(String ustTeam) {
		this.ustTeam = ustTeam;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastNmae=" + lastNmae + ", ustr_id="
				+ ustr_id + ", email=" + email + ", password=" + password + ", role_id=" + role_id + ", role=" + role
				+ ", ustTeam=" + ustTeam + "]";
	}
	
	
	
	
	
	
	
	
	
	

}

package com.ust.allowance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* User Role Entity
*
* @author 112015
* @since 30 OCT 2023
* @version 1.0.0
*
*/
@Entity
@Table(name="userrole")
public class UserRole {
	
	@Id
    @Column(name = "role_id")
	private int roleId;
	@Column(name = "role")
	private String role;
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}

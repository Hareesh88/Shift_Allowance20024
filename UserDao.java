package com.ust.allowance.dao;

import java.util.List;

import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.UserRole;
import com.ust.allowance.entity.Users;

public interface UserDao {
	
	public Users fetchUserById (String userName, String password);
	
	public List<Users> getAllUserDetails();
	
	public UserRole getUserRoleById(int role_id);
	
	public boolean saveUser(Users users);
	
	public List<TeamEntity> getAllTeamDetails();
	
	public List<ProjectEntity> getAllProjects();
	
	public TeamEntity getTeamByManager(int teamId);
	
	public Users getUserDetailsByUserId(String ustID);
	
	public Users fetchUserDetails(int userId);
	
	public boolean updateUser(Users user);
	
	public List<TeamEntity> getTeamDetailsByManagerID(String ustID);
	
	public ProjectEntity fetchProjectByType(String projectType);

}

package com.ust.allowance.dao;

import java.util.Date;
import java.util.List;

import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.Users;

public interface AdminDao {
	
	List<AllowanceEntity> getAllShiftAllowance();
	Users getUserById(int userId);
	List<Users>fetchUserByUserId(String userId,String team,String ustId);
	List<AllowanceEntity> getAllowanceByuserId(int UserId, String allowanceStatus);
	void approveAllowance(int allowanceId,String ustId);
	void rejectAllowance(int allowanceId);
	List<AllowanceEntity> getAllShiftAllowanceByDate(Date startDate,Date endDate,String allowanceStatus);
	public void saveproject(ProjectEntity projectEnitity);
    List<ProjectEntity> getAllProject();
    List<ProjectEntity> getProjectByID(int projectId);
    ProjectEntity fetchProjectById(int projectId);
    public void updateProject(int projectId);
    public void saveTeam(TeamEntity teamEnitity);
    List<TeamEntity>getAllTeam();
    List<Users>getAllManagers();
    public Users getUserByUstId(String ManagerId);
    public Users getUserByUstID(String ustId);
    public List<Users>fetchUserByManagerId(String ustId);
}

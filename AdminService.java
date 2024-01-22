package com.ust.allowance.service;

import java.util.Date;
import java.util.List;

import com.ust.allowance.controller.UserController;
import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;

public interface AdminService {
	
	public List<AllowanceDto> getAllShiftAllowance();	
	public List<AllowanceDto> searchShiftAllowance(String allowanceStatus,String userId,String team, String ustId);
	public void approveAllowance(List<AllowanceDto>selectedAllowance, String ustId);
	public void rejectAllowance(List<AllowanceDto>selectedAllowance, String rejectNote);
	public List<AllowanceDto> getAllShiftAllowanceByDate(Date startDate, Date endDate, String allowanceStatus);	
	public void saveproject(String projectName,String projectDescription,String projectType,Date startDate,Date endDate);
	public List<ProjectDto> getAllProject();
	public List<ProjectDto> getProjectByID(int projectId);
	public void updateProject(String projectName,String projectDescription,int projectId);
	public void deletProject(int ProjectId);
	public void saveTeam(String teamName,String teamDescription,String managerId);
	public List<TeamDto> getAllTeam();
	public List<UserDto> getAllManagers();
	public List<AllowanceDto>getAllInprogressAllowance(String ustId);
	
	
}

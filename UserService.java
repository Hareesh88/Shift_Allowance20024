package com.ust.allowance.service;

import java.util.List;

import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;

public interface UserService {
	
	public UserDto fetchUserById(String userName, String password);
	List<UserDto>getAllUserDetails();
	public boolean saveUser(UserDto userDto);
	public List<TeamDto>getAllTeamDetails();
	public List<ProjectDto> getAllProjects();
	public UserDto getUserDetailsByUserId(String ustID);
	public boolean resetPassword(int userId, String password);
	public List<TeamDto> getTeamDetailsByManagerID(String ustID);
	public ProjectDto fetchProjectByType(String ProjectType);
	
	

}

package com.ust.allowance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ust.allowance.dao.UserDao;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.UserRole;
import com.ust.allowance.entity.Users;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto fetchUserById(String userName, String password) {
		UserDto userDto = new UserDto();
		try {
			Users user = userDao.fetchUserById(userName, password);
			if(user.getUserId()>0) {
			userDto.setRole_id(user.getRole_id());
			userDto.setEmail(user.getEmail());
			userDto.setUserId(user.getUserId());
			userDto.setUstr_id(user.getUstr_id());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastNmae(user.getLastNmae());
			userDto.setProjectType(user.getProjectType());
			}
		}catch(Exception e) {
			System.out.println("fetchUserById"+e);
		}
		return userDto;
	}
	
	
	@Override
	public List<UserDto> getAllUserDetails(){
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		try {
			List<Users>userList = userDao.getAllUserDetails();
			for(Users users :userList) {
				UserDto userDto = new UserDto();
				userDto.setUserId(users.getUserId());
				userDto.setUstr_id(users.getUstr_id());
				userDto.setFirstName(users.getFirstName());
				userDto.setLastNmae(users.getLastNmae());
				userDto.setEmail(users.getEmail());
				UserRole userRole = userDao.getUserRoleById(users.getRole_id());
				userDto.setRole(userRole.getRole());
				userDtoList.add(userDto);
			}
		}catch(Exception e) {
			
			
		}
		return userDtoList;
	}

	@Override
	public boolean saveUser(UserDto userDto) {
		boolean retval = false;
		try {
			Users users = new Users();
			users.setUstr_id(userDto.getUstr_id());
			users.setFirstName(userDto.getFirstName());
			users.setLastNmae(userDto.getLastNmae());
			users.setEmail(userDto.getEmail());
			users.setPassword(userDto.getPassword());
			users.setRole_id(userDto.getRole_id());
			users.setProjectType(userDto.getProjectType());
			if(userDto.getRole_id()>2) {
				users.setTeamId(Integer.valueOf(userDto.getUstTeam()));
				TeamEntity teamEntity = userDao.getTeamByManager(Integer.valueOf(userDto.getUstTeam()));
				users.setManagerId(teamEntity.getManagerId());
			}else {
				users.setTeamId(0);
				users.setManagerId(null);
			}
			retval = userDao.saveUser(users);
		}catch(Exception e) {
		 System.out.println(e);	
		}
		return retval;
	}
	
	@Override
	public List<TeamDto>getAllTeamDetails(){
		List<TeamDto>teamDtoList = new ArrayList<TeamDto>();
		List<TeamEntity>teamEntityList = null;
		try {
			teamEntityList = userDao.getAllTeamDetails();
			for(TeamEntity teamEntity : teamEntityList) {
				TeamDto teamDto = new TeamDto();
				teamDto.setTeamId(teamEntity.getTeamId());
				teamDto.setTeamName(teamEntity.getTeam());
				teamDtoList.add(teamDto);
			 }
		  }catch(Exception e) {
		  System.out.println(e);	
		  }
		return teamDtoList;
	}
	
	@Override
	public List<ProjectDto> getAllProjects(){
		List<ProjectDto>projectDtoList = new ArrayList<ProjectDto>();
		List<ProjectEntity>projectEntityList = null;
		try {
			projectEntityList = userDao.getAllProjects();
			for(ProjectEntity projectEntity : projectEntityList) {
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProjectId(projectEntity.getProjectId());
				projectDto.setProjectName(projectEntity.getProjectName());
				projectDtoList.add(projectDto);
			 }
		  }catch(Exception e) {
		  System.out.println(e);	
		  }
		return projectDtoList;
	}
	
	@Override
	public UserDto getUserDetailsByUserId(String ustID) {
		UserDto userDto = new UserDto();
		try {
			Users user = userDao.getUserDetailsByUserId(ustID);
			userDto.setRole_id(user.getRole_id());
			userDto.setEmail(user.getEmail());
			userDto.setUserId(user.getUserId());
			userDto.setUstr_id(user.getUstr_id());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastNmae(user.getLastNmae());
		}catch(Exception e) {
			System.out.println("getUserDetailsByUserId...."+e);
		}
		return userDto;	
		
	}

	@Override
	public boolean resetPassword(int userId, String password) {
		try {
			Users user = userDao.fetchUserDetails(userId);
			user.setPassword(password);
			boolean retval = userDao.updateUser(user);
		}catch(Exception e) {
			System.out.println(e);	
		}
		return false;
	}

	@Override
	public List<TeamDto> getTeamDetailsByManagerID(String ustID) {
		List<TeamDto>teamDtoList = new ArrayList<TeamDto>();
		List<TeamEntity>teamEntityList = null;
		try {
			teamEntityList = userDao.getTeamDetailsByManagerID(ustID);
			for(TeamEntity teamEntity : teamEntityList) {
				TeamDto teamDto = new TeamDto();
				teamDto.setTeamId(teamEntity.getTeamId());
				teamDto.setTeamName(teamEntity.getTeam());
				teamDtoList.add(teamDto);
			 }
		  }catch(Exception e) {
		  System.out.println(e);	
		  }
		return teamDtoList;
	}
	
	@Override
	public ProjectDto fetchProjectByType(String projectType) {
		ProjectDto projectDto = new ProjectDto();
		try {
			ProjectEntity prj = userDao.fetchProjectByType(projectType);
			System.out.println(".............."+prj.getProjectName());
		}catch(Exception e) {
			System.out.println(e);		
		}
		return projectDto;
	}
	
	
	
}

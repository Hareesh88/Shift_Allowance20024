package com.ust.allowance.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ust.allowance.dao.AdminDao;
import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;
import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.Users;
import com.ust.allowance.util.MailHelper;

public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public List<AllowanceDto> getAllShiftAllowance() {
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity> allowanceEntityList = null;
		try {
			allowanceEntityList = adminDao.getAllShiftAllowance();
			for(AllowanceEntity allowanceEntity : allowanceEntityList) {
				Users user = adminDao.getUserById(allowanceEntity.getUserId());
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setUserId(user.getUserId());
				allowanceDto.setUstr_id(user.getUstr_id());
				allowanceDto.setFirstName(user.getFirstName());
				allowanceDto.setLastNmae(user.getLastNmae());
				allowanceDto.setType(allowanceEntity.getAllowanceType());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				allowanceDto.setStatus(allowanceEntity.getAllowanceStatus());
				allowanceDtoList.add(allowanceDto);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}
	
	@Override
	public List<AllowanceDto> searchShiftAllowance(String allowanceStatus,String userId,String team, String ustId){
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity> allowanceEntityList = null;
		try {
			List<Users> userList = adminDao.fetchUserByUserId(userId,team,ustId);
			for(Users users: userList) {
				allowanceEntityList = adminDao.getAllowanceByuserId(users.getUserId(),allowanceStatus);
				for(AllowanceEntity allowanceEntity :allowanceEntityList) {
					AllowanceDto allowanceDto = new AllowanceDto();
					allowanceDto.setUserId(users.getUserId());
					allowanceDto.setUstr_id(users.getUstr_id());
					allowanceDto.setFirstName(users.getFirstName());
					allowanceDto.setLastNmae(users.getLastNmae());
					allowanceDto.setType(allowanceEntity.getAllowanceType());
					DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
					String strDate = dateFormat.format(allowanceEntity.getAllowanceDate());  
					allowanceDto.setStrDate(strDate);
					allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
					allowanceDto.setStatus(allowanceEntity.getAllowanceStatus());
					allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
					allowanceDtoList.add(allowanceDto);
					}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}
	
	
	@Override
	public void approveAllowance(List<AllowanceDto>selectedAllowance,String ustId) {
		MailHelper mailHelper = new MailHelper();
		try {
			for(AllowanceDto allowanceDto : selectedAllowance) {
				
				 adminDao.approveAllowance(allowanceDto.getAllowanceId(),ustId);
			      Users user = adminDao.getUserByUstID(allowanceDto.getUstr_id());
				  mailHelper.sendEmail(user.getEmail(), "Allowance Status", "Allowance was approved ");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public void rejectAllowance(List<AllowanceDto>selectedAllowance, String rejectNote) {
		MailHelper mailHelper = new MailHelper();
		try {
			for(AllowanceDto allowanceDto : selectedAllowance) {
				adminDao.rejectAllowance(allowanceDto.getAllowanceId());
				Users user = adminDao.getUserByUstID(allowanceDto.getUstr_id());
				 mailHelper.sendEmail(user.getEmail(), "Allowance Rejected", rejectNote);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	public List<AllowanceDto> getAllShiftAllowanceByDate(Date startDate, Date endDate, String allowanceStatus){
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity> allowanceEntityList = null;
		int index =1;
		try {
			allowanceEntityList = adminDao.getAllShiftAllowanceByDate(startDate,endDate,allowanceStatus);
			for(AllowanceEntity allowanceEntity : allowanceEntityList) {
				Users user = adminDao.getUserById(allowanceEntity.getUserId());
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setIndex(index);
				allowanceDto.setUserId(user.getUserId());
				allowanceDto.setUstr_id(user.getUstr_id());
				allowanceDto.setFirstName(user.getFirstName());
				allowanceDto.setLastNmae(user.getLastNmae());
				allowanceDto.setType(allowanceEntity.getAllowanceType());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				allowanceDto.setStatus(allowanceEntity.getAllowanceStatus());
				allowanceDtoList.add(allowanceDto);
				index++;
			}
			
			System.out.println(allowanceEntityList.size());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}
	
	@Override
	public void saveproject(String projectName,String projectDescription,String projectType,Date startDate,Date endDate) {
		ProjectEntity projectEnitity =  new ProjectEntity();
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = new Date();
			String str = formatter.format(date);
			Date date3=formatter.parse(str);
			projectEnitity.setProjectName(projectName);
			projectEnitity.setStatus("Active");
			projectEnitity.setCreated(date3);
			projectEnitity.setProjectDescription(projectDescription);
			projectEnitity.setProjectType(projectType);
			projectEnitity.setStartDate(startDate);
			projectEnitity.setEndDate(endDate);
			adminDao.saveproject(projectEnitity);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public List<ProjectDto> getAllProject(){
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();
		List<ProjectEntity>projectEntitylist = null;
		int index =1;
		try {
			projectEntitylist = adminDao.getAllProject();
			for(ProjectEntity projectEntity :projectEntitylist) {
				ProjectDto projectDto = new ProjectDto();
				projectDto.setProjectId(projectEntity.getProjectId());
				projectDto.setDescription(projectEntity.getProjectDescription());
				projectDto.setProjectName(projectEntity.getProjectName());
				projectDto.setCreated(projectEntity.getCreated());
				projectDto.setStatus(projectEntity.getStatus());
				projectDto.setProjectType(projectEntity.getProjectType());
				DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
				String strDate = dateFormat.format(projectEntity.getStartDate()); 
				String edDate = dateFormat.format(projectEntity.getEndDate());
				projectDto.setStrStartDate(strDate);
				projectDto.setStrEndDate(edDate);
				projectDto.setEndDate(projectEntity.getEndDate());
				if(projectEntity.getStatus()=="Active") {
					projectDto.setFontColor("Green");
				}else {
					projectDto.setFontColor("Red");
				}
				projectDto.setIndex(index);
				projectDtoList.add(projectDto);
				index++;
			}
		}catch(Exception e) {
		 System.out.println(e);
		 }
		return projectDtoList;
	}
	
	

	@Override
	public List<ProjectDto> getProjectByID(int projectId){
		List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();
		List<ProjectEntity>projectEntitylist = null;
		try {
			projectEntitylist = adminDao.getProjectByID(projectId);
		}catch(Exception e) {
		 System.out.println(e);
		 }
		return projectDtoList;
	}
	
	
	@Override
	public void updateProject(String projectName,String projectDescription,int projectId) {
		ProjectEntity projectEnitity =  new ProjectEntity();
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = new Date();
			String str = formatter.format(date);
			Date date3=formatter.parse(str);
			projectEnitity.setProjectName(projectName);
			projectEnitity.setStatus("Active");
			projectEnitity.setCreated(date3);
			projectEnitity.setProjectDescription(projectDescription);
			adminDao.saveproject(projectEnitity);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}

	@Override
	public void deletProject(int projectId) {
		try {
			adminDao.updateProject(projectId);
		}catch(Exception e) {
			System.out.println(e);	
		}
		
	}
	
	@Override
	public void saveTeam(String teamName,String teamDescription,String managerId) {
		TeamEntity teamEnitity =  new TeamEntity();
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = new Date();
			String str = formatter.format(date);
			Date date3=formatter.parse(str);
			teamEnitity.setTeam(teamName);
			teamEnitity.setStatus("Active");
			teamEnitity.setCreated(date3);
			teamEnitity.setManagerId(managerId);
			teamEnitity.setTeamDescription(teamDescription);
			adminDao.saveTeam(teamEnitity);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<TeamDto> getAllTeam(){
		List<TeamDto> teamDtoList = new ArrayList<TeamDto>();
		List<TeamEntity>teamEntitylist = null;
		int index =1;
		try {
			teamEntitylist = adminDao.getAllTeam();
			for(TeamEntity teamEntity :teamEntitylist) {
				TeamDto teamDto = new TeamDto();
				teamDto.setTeamId(teamEntity.getTeamId());
				teamDto.setTeamName(teamEntity.getTeam());
				teamDto.setDescription(teamEntity.getTeamDescription());
				teamDto.setCreated(teamEntity.getCreated());
				teamDto.setStatus(teamEntity.getStatus());
				Users users = adminDao.getUserByUstId(teamEntity.getManagerId());
				teamDto.setManagerName(users.getFirstName()+","+ users.getLastNmae());
				teamDto.setIndex(index);
				teamDtoList.add(teamDto);
				index++;
			}
		}catch(Exception e) {
		 System.out.println(e);
		 }
		return teamDtoList;
	}

	@SuppressWarnings("unused")
	@Override
	public List<UserDto> getAllManagers(){
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		List<Users>userlist = null;
		
		try {
			userlist = adminDao.getAllManagers();
			for(Users users :userlist) {
				UserDto userDto = new UserDto();
				userDto.setUstr_id(users.getUstr_id());
				userDto.setFirstName(users.getFirstName());
				userDtoList.add(userDto);
			}
		}catch(Exception e) {
		 System.out.println(e);
		 }
		return userDtoList;
	}

	@SuppressWarnings("unused")
	@Override
	public List<AllowanceDto>getAllInprogressAllowance(String ustId){
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity> allowanceEntityList = null;
		String allowanceStatus = "Inprogress";
		try {
			List<Users> userList = adminDao.fetchUserByManagerId(ustId);
			for(Users users :userList) {
				allowanceEntityList = adminDao.getAllowanceByuserId(users.getUserId(),allowanceStatus);
				for(AllowanceEntity allowanceEntity :allowanceEntityList) {
					AllowanceDto allowanceDto = new AllowanceDto();
					allowanceDto.setUserId(users.getUserId());
					allowanceDto.setUstr_id(users.getUstr_id());
					allowanceDto.setFirstName(users.getFirstName());
					allowanceDto.setLastNmae(users.getLastNmae());
					allowanceDto.setType(allowanceEntity.getAllowanceType());
					allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
					DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
					String strDate = dateFormat.format(allowanceEntity.getAllowanceDate());  
					allowanceDto.setStrDate(strDate);
					allowanceDto.setStatus(allowanceEntity.getAllowanceStatus());
					allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
					allowanceDtoList.add(allowanceDto);
					}
			}
			
		}catch(Exception e) {
		 System.out.println(e);
		 }
		return allowanceDtoList;
	}
	
	
}

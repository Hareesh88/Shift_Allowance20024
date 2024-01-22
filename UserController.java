package com.ust.allowance.controller;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;

import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;
import com.ust.allowance.service.AllowanceService;
import com.ust.allowance.service.UserService;




/**
*
* User Managed Bean
*
* @author 112015
* @since 26 OCT 2023
* @version 1.0.0
*
*/
@ManagedBean(name="userMB")
@SessionScoped
public class UserController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String userName;
	private String password;
	
	//Spring User Service is injected...
	 @ManagedProperty(value="#{UserService}")
	private UserService userService;
	 
	 @ManagedProperty(value="#{AllowanceService}")
	private AllowanceService allowanceService;
	 
	 
	
	private List<UserDto>userList;
	private UserDto selectedUser;
	static int userLoginID;
    static String ustID;
	
	private int userId;
	private String firstName;
	private String lastNmae;
	private String ustr_id;
	private String email;
	private int role_id;
	private String role;
	private String ustTeam;
	private String usrPassword;
	private String newPassword;
	private List<TeamDto>teamDtoList;
	private Map<String,String> allteams = new HashMap<String, String>();
	private Map<String,String> allprojects = new HashMap<String, String>();
	private List<ProjectDto>projectDtoList;
	private String projectId;
	private String userLoginName;
	static String projectType;
	
	
	
	
	



	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public List<ProjectDto> getProjectDtoList() {
		return projectDtoList;
	}

	public void setProjectDtoList(List<ProjectDto> projectDtoList) {
		this.projectDtoList = projectDtoList;
	}

	public Map<String, String> getAllprojects() {
		return allprojects;
	}

	public void setAllprojects(Map<String, String> allprojects) {
		this.allprojects = allprojects;
	}

	public Map<String, String> getAllteams() {
		return allteams;
	}

	public void setAllteams(Map<String, String> allteams) {
		this.allteams = allteams;
	}

	public List<TeamDto> getTeamDtoList() {
		return teamDtoList;
	}

	public void setTeamDtoList(List<TeamDto> teamDtoList) {
		this.teamDtoList = teamDtoList;
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

	public String getUsrPassword() {
		return usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public UserDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	public String userLogin() {
		String retval =null;
		allteams = new HashMap<String, String>();
		UserDto userDto = new UserDto (); 
		try {
			if((userName.equals("")) || (password.equals("")) ){
				retval = "/pages/login.xhtml";
			}else {
				userDto = userService.fetchUserById(userName,password );
				if(userDto.getUserId()>0) {
				userLoginName = userDto.getFirstName()+" "+userDto.getLastNmae()+ ",  "+userDto.getUstr_id();
				userLoginID = userDto.getUserId();
				ustID = userDto.getUstr_id();
				projectType = userDto.getProjectType();
				if(userDto.getRole_id()==4) {
					userLoginID = userDto.getUserId();
					retval = "/pages/dashBoard.xhtml";
				}else if(userDto.getRole_id()==2) {
					teamDtoList = userService.getTeamDetailsByManagerID(ustID);
					for(TeamDto teamDto :teamDtoList) {
						allteams.put(teamDto.getTeamName(), String.valueOf(teamDto.getTeamId()));
					}
					retval = "/pages/allowanceList.xhtml";
				}else if(userDto.getRole_id()==1){
					retval = "/pages/admin.xhtml";
				}else {
					retval = "/pages/login.xhtml";
				}
			 }
			} 
		 
		}catch(Exception e) {
			System.out.println("userLogin.."+e);
		}
		return retval;
	}
	
	public String goToEditUserProfile() {
		try {
			UserDto userDto = userService.getUserDetailsByUserId(ustID);
			setFirstName(userDto.getFirstName());
			setLastNmae(userDto.getLastNmae());
		    setEmail(userDto.getEmail());
		    setUstr_id(userDto.getUstr_id());
		    setRole(userDto.getRole());
		    setUstTeam(userDto.getUstTeam());
		}catch(Exception e) {
			System.out.println(e);
		}
		return "/pages/editUser.xhtml";
	}
	
	
	public String goToResetPassword() {
		try {
			UserDto userDto = userService.getUserDetailsByUserId(ustID);
			setFirstName(userDto.getFirstName());
			setLastNmae(userDto.getLastNmae());
		    setEmail(userDto.getEmail());
		    setUstr_id(userDto.getUstr_id());
		    setRole(userDto.getRole());
		    setUstTeam(userDto.getUstTeam());
		}catch(Exception e) {
			System.out.println(e);
		}
		return "/pages/passwordReset.xhtml";
	}
	
	public String resetPassword() {
		try {
			userService.resetPassword(userLoginID,newPassword);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reset Password", "Successfully");
	        PrimeFaces.current().dialog().showMessageDynamic(message);	
		}catch(Exception e) {
			System.out.println(e);
		}
		
	return null;	
	}
	
	public String forgetPassword() {
		try {
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return "/pages/forgetpassword.xhtml";
	}
	
	
	public String getAllUserDetails() {
		allteams = new HashMap<String, String>();
		try {
			userList = userService.getAllUserDetails();
			teamDtoList = userService.getAllTeamDetails();
			//projectDtoList = userService.getAllProjects();
			for(TeamDto teamDto :teamDtoList) {
				allteams.put(teamDto.getTeamName(), String.valueOf(teamDto.getTeamId()));
			}
			/*
			 * for(ProjectDto projectDto :projectDtoList) {
			 * allprojects.put(projectDto.getProjectName(),
			 * String.valueOf(projectDto.getProjectId())); }
			 */
		}catch(Exception e) {
			System.out.println(e);
		}
	return "/pages/User.xhtml";	
	}
	
	
	public String  saveUser() {
		System.out.println(firstName);
		boolean retval = false;
		try {
			UserDto userDto = new UserDto();
			userDto.setFirstName(firstName);
			userDto.setLastNmae(lastNmae);
			userDto.setEmail(email);
			userDto.setRole_id(role_id);
			userDto.setUstTeam(ustTeam);
			userDto.setUstr_id(ustr_id);
			userDto.setPassword(ustr_id+"123");
			userDto.setProjectType(projectType);
			retval = userService.saveUser(userDto);
			userList = userService.getAllUserDetails();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return "";
	}
	
	
	
     public String gotoHomePage() {
		
		return "/pages/admin.xhtml";	
	}
	
	
	public String toCreateNewUser() {
		
		return "/pages/newuser.xhtml";	
	}
	
	
	
	
	public String retruntoLogin() {
		
	return "/pages/login.xhtml";
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public AllowanceService getAllowanceService() {
		return allowanceService;
	}

	public void setAllowanceService(AllowanceService allowanceService) {
		this.allowanceService = allowanceService;
	}
	
	
	
	
	
	
	

}

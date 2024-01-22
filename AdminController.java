package com.ust.allowance.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.TeamDto;
import com.ust.allowance.dto.UserDto;
import com.ust.allowance.service.AdminService;

/**
*
* Admin Managed Bean
*
* @author 112015
* @since 29 OCT 2023
* @version 1.0.0
*
*/

@ManagedBean(name="adminMB")
@SessionScoped
public class AdminController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{AdminService}")
	private AdminService adminService;
	
	
	private List<AllowanceDto> allowanceDtoList;
	private Date startDate;
	private Date endDate;
	private List<AllowanceDto> selectedAllowance ;
	private String allowanceStatus;
    private AllowanceDto selectallowance;
	private String selectedvalue;
	private int selectedValue; 
	String  userId = UserController.ustID;
	private String ustr_id = null;
   	private String projectName;
   	private String projectDescription;
    private List<ProjectDto>projectDtoList;
   	private int projectId;
   	private ProjectDto selectedProject;
   	private String teamName;
    private String teamDescription;
    private String managerId;
    private List<TeamDto>teamDtoList;
    private TeamDto selectedTeam;
    private List<UserDto>managerList;
    private Map<String,String> managers = new HashMap<String, String>();
    private String ustTeam = null;
    private String rejectNote;
    private String projectType;
    
    
    
    
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public String getUstTeam() {
		return ustTeam;
	}

	public void setUstTeam(String ustTeam) {
		this.ustTeam = ustTeam;
	}

	public Map<String, String> getManagers() {
		return managers;
	}

	public void setManagers(Map<String, String> managers) {
		this.managers = managers;
	}

	public List<UserDto> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<UserDto> managerList) {
		this.managerList = managerList;
	}

	public TeamDto getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(TeamDto selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public ProjectDto getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(ProjectDto selectedProject) {
		this.selectedProject = selectedProject;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public List<ProjectDto> getProjectDtoList() {
		return projectDtoList;
	}

	public void setProjectDtoList(List<ProjectDto> projectDtoList) {
		this.projectDtoList = projectDtoList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public int getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(int selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String getSelectedvalue() {
		return selectedvalue;
	}

	public void setSelectedvalue(String selectedvalue) {
		this.selectedvalue = selectedvalue;
	}

	public AllowanceDto getSelectallowance() {
		return selectallowance;
	}

	public void setSelectallowance(AllowanceDto selectallowance) {
		this.selectallowance = selectallowance;
	}

	public String getAllowanceStatus() {
		return allowanceStatus;
	}

	public void setAllowanceStatus(String allowanceStatus) {
		this.allowanceStatus = allowanceStatus;
	}

	public List<AllowanceDto> getSelectedAllowance() {
		return selectedAllowance;
	}

	public void setSelectedAllowance(List<AllowanceDto> selectedAllowance) {
		this.selectedAllowance = selectedAllowance;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUstr_id() {
		return ustr_id;
	}

	public void setUstr_id(String ustr_id) {
		this.ustr_id = ustr_id;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	
	
	
	
	public List<TeamDto> getTeamDtoList() {
		return teamDtoList;
	}

	public void setTeamDtoList(List<TeamDto> teamDtoList) {
		this.teamDtoList = teamDtoList;
	}

	public void getAllShiftAllowance() {
		try {
			allowanceDtoList = adminService.getAllShiftAllowance();	
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	 @PostConstruct
	    public void init(){
		 allowanceDtoList = adminService.getAllInprogressAllowance(UserController.ustID);
	    }
	
	
	public void onPageLoad(){
		
		allowanceDtoList = adminService.getAllInprogressAllowance(UserController.ustID);
		
	 }
	
	
	public void searchShiftAllowance() {
		try {
			allowanceDtoList = adminService.searchShiftAllowance(allowanceStatus,ustr_id,ustTeam,UserController.ustID);	
		}catch(Exception e) {
			System.out.println(e);	
		}
	}
	
	public String approveAllowance() {
		String customMessage ="";
		try {
			if(selectedAllowance.size()>0) {
			   adminService.approveAllowance(selectedAllowance,UserController.ustID );
			   allowanceDtoList = adminService.searchShiftAllowance(allowanceStatus,userId,ustTeam,UserController.ustID);	
			   customMessage ="Successfully Approved";
			}
			showMessage(customMessage);
			selectedAllowance = new ArrayList<AllowanceDto>();
		}catch(Exception e) {
			System.out.println(e);	
		}
		return null;
	}
	
	
	public String rejectAllowance() {
		String customMessage ="";
		try {
			if(selectedAllowance.size()>0) {
			   adminService.rejectAllowance(selectedAllowance,rejectNote);
			   allowanceDtoList = adminService.searchShiftAllowance(allowanceStatus,userId,ustTeam,UserController.ustID);	
			   customMessage ="Rejected";
			}
			showMessage(customMessage);
			selectedAllowance = new ArrayList<AllowanceDto>();
		}catch(Exception e) {
			System.out.println(e);	
		}
		return null;
	}

	 public void showMessage(String custmessage) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Shift Allowance ", custmessage);
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	    }
	
	
	 public void getAllShiftAllowanceByDate() {
			System.out.println("getAllShiftAllowance"+startDate);
			try {
				allowanceDtoList = adminService.getAllShiftAllowanceByDate(startDate, endDate,allowanceStatus);	
				
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	
	 
	 public String toprojectPage() {
			try {
				projectDtoList = adminService.getAllProject();
			}catch(Exception e) {
				System.out.println(e);
			}
		return "/pages/project.xhtml";	
		}
	
	 public String toTeamPage() {
		 managers = new HashMap<String, String>();
		 try {
			 teamDtoList = adminService.getAllTeam();
			 managerList = adminService.getAllManagers();
			 for(UserDto userDto : managerList ) {
				 managers.put(userDto.getFirstName(), userDto.getUstr_id());
			 }
			 
			}catch(Exception e) {
				System.out.println(e);
			}
		return "/pages/team.xhtml";	
	 }
	 
	 
	 
	 public void saveproject() {
		 try {
			 adminService.saveproject(projectName, projectDescription,projectType,startDate,endDate);
			 projectDtoList = adminService.getAllProject();
			}catch(Exception e) {
				System.out.println(e);
			}
	 }
	 
	 public void getAllProject() {
		 try {
			projectDtoList = adminService.getAllProject();
			}catch(Exception e) {
				System.out.println(e);
			} 
		 
	 }
	 
	 public void getProjectById() {
		 try {
				projectDtoList = adminService.getProjectByID(projectId);
				}catch(Exception e) {
					System.out.println(e);
				}  
	 }
	 
	 public void updateProject() {
		 adminService.updateProject(projectName, projectDescription,projectId);
		 
	 }
	 
	 public void deleteProject() {
		 String customMessage ="";
		 try {
			 adminService.deletProject(selectedProject.getProjectId());
			 projectDtoList = adminService.getAllProject();
			 customMessage = "  SuccessFully InActivated  ";
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Project", customMessage);
		     PrimeFaces.current().dialog().showMessageDynamic(message);
		 }catch(Exception e) {
			 
		 }
	 }
	 
	
	 
	 public void saveTeam() {
		 try {
			 adminService.saveTeam(teamName, teamDescription,managerId);
			 teamDtoList = adminService.getAllTeam();
			 
			}catch(Exception e) {
				System.out.println(e);
			}
	 }
	 
	 public void getAllTeam() {
		 try {
			 teamDtoList = adminService.getAllTeam();
			}catch(Exception e) {
				System.out.println(e);
			} 
		 
	 }
	 
	 
	
	 
	 
	 
	public AdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public List<AllowanceDto> getAllowanceDtoList() {
		return allowanceDtoList;
	}


	public void setAllowanceDtoList(List<AllowanceDto> allowanceDtoList) {
		this.allowanceDtoList = allowanceDtoList;
	}
	
	
	
	
	
	
	
	

}

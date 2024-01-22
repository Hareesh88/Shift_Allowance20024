package com.ust.allowance.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.ust.allowance.service.AllowanceService;

/**
*
* Allowance Managed Bean
*
* @author 112015
* @since 01 Nov 2023
* @version 1.0.0
*
*/

@ManagedBean(name="allowanceMB")
@SessionScoped
public class AllowanceController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{AllowanceService}")
	private AllowanceService allowanceService;
	
	private List<AllowanceDto> selectedAllowance;
	private int noOfDays;
	private List<Date> invalidDates;
	private List<Integer> invalidDays;
	private Date minDate;
    private Date maxDate;
    private Date minTime;
    private Date maxTime;
    private Date minDateTime;
    private Date maxDateTime;
    private Date dateDe;
    private Date dateTimeDe;
    private List<AllowanceDto> allowanceDtoList;
	private String location;
    private AllowanceDto selectedDayAllow;
    private List<ProjectDto>projectDtoList;
	private String projectId;
	private Map<String,String> projects = new HashMap<String, String>();
    private Date fromDate;
    private Date toDate;
    private String allowanceStatus;
    private String allowanceType;
    private List<Date> daymulti;
    private List<Date> nightmulti;
    private List<AllowanceDto>mainAllowanceDtoList;
	private String prjType;
    static int prjId;
    private String hideDialogue;
    
    
    
	
	public String getHideDialogue() {
		return hideDialogue;
	}

	public void setHideDialogue(String hideDialogue) {
		this.hideDialogue = hideDialogue;
	}

	public String getPrjType() {
		return prjType;
	}

	public void setPrjType(String prjType) {
		this.prjType = prjType;
	}

	public List<AllowanceDto> getMainAllowanceDtoList() {
		return mainAllowanceDtoList;
	}

	public void setMainAllowanceDtoList(List<AllowanceDto> mainAllowanceDtoList) {
		this.mainAllowanceDtoList = mainAllowanceDtoList;
	}

	public List<Date> getDaymulti() {
		return daymulti;
	}

	public void setDaymulti(List<Date> daymulti) {
		this.daymulti = daymulti;
	}

	public List<Date> getNightmulti() {
		return nightmulti;
	}

	public void setNightmulti(List<Date> nightmulti) {
		this.nightmulti = nightmulti;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getAllowanceStatus() {
		return allowanceStatus;
	}

	public void setAllowanceStatus(String allowanceStatus) {
		this.allowanceStatus = allowanceStatus;
	}

	public String getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}

	public List<ProjectDto> getProjectDtoList() {
		return projectDtoList;
	}

	public void setProjectDtoList(List<ProjectDto> projectDtoList) {
		this.projectDtoList = projectDtoList;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Map<String, String> getProjects() {
		return projects;
	}

	public void setProjects(Map<String, String> projects) {
		this.projects = projects;
	}

	public List<Date> getInvalidDates() {
		return invalidDates;
	}

	public void setInvalidDates(List<Date> invalidDates) {
		this.invalidDates = invalidDates;
	}

	public List<Integer> getInvalidDays() {
		return invalidDays;
	}

	public void setInvalidDays(List<Integer> invalidDays) {
		this.invalidDays = invalidDays;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public AllowanceService getAllowanceService() {
		return allowanceService;
	}

	public void setAllowanceService(AllowanceService allowanceService) {
		this.allowanceService = allowanceService;
	}

	public List<AllowanceDto> getSelectedAllowance() {
		return selectedAllowance;
	}

	public void setSelectedAllowance(List<AllowanceDto> selectedAllowance) {
		this.selectedAllowance = selectedAllowance;
	}
	
	public List<AllowanceDto> getAllowanceDtoList() {
		return allowanceDtoList;
	}

	public void setAllowanceDtoList(List<AllowanceDto> allowanceDtoList) {
		this.allowanceDtoList = allowanceDtoList;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AllowanceDto getSelectedDayAllow() {
		return selectedDayAllow;
	}

	public void setSelectedDayAllow(AllowanceDto selectedDayAllow) {
		this.selectedDayAllow = selectedDayAllow;
	}
	
	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	public Date getMinDateTime() {
		return minDateTime;
	}

	public void setMinDateTime(Date minDateTime) {
		this.minDateTime = minDateTime;
	}

	public Date getMaxDateTime() {
		return maxDateTime;
	}

	public void setMaxDateTime(Date maxDateTime) {
		this.maxDateTime = maxDateTime;
	}

	public Date getDateDe() {
		return dateDe;
	}

	public void setDateDe(Date dateDe) {
		this.dateDe = dateDe;
	}

	public Date getDateTimeDe() {
		return dateTimeDe;
	}

	public void setDateTimeDe(Date dateTimeDe) {
		this.dateTimeDe = dateTimeDe;
	}

	
	@PostConstruct
    public void init() {
        invalidDates = new ArrayList<>();
        Date today = new Date();
        invalidDates.add(today);
        long oneDay = 24 * 60 * 60 * 1000;
        for (int i = 0; i < 5; i++) {
            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
        }
 
        invalidDays = new ArrayList<>();
        invalidDays.add(0); /* the first day of week is disabled */
        invalidDays.add(3);
 
        minDate = new Date(today.getTime() - (365 * oneDay));
        maxDate = new Date(today.getTime() + (365 * oneDay));
 System.out.println("minDate,,,,"+ minDate);
        Calendar tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 9);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        minTime = tmp.getTime();
        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 17);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        maxTime =  tmp.getTime();
        minDateTime = new Date(today.getTime() - (25 * oneDay));
        maxDateTime = new Date(today.getTime() + (1 * oneDay));
 
        dateDe = GregorianCalendar.getInstance().getTime();
        dateTimeDe = GregorianCalendar.getInstance().getTime();
    }
 
       public String gotoHomePage() {
		try {
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return "/pages/dashBoard.xhtml";
	}

	public void approveAllowance() {
		try {
			allowanceService.approveAllowance(selectedAllowance);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public String  addNightAllowance() {
		int userId = UserController.userLoginID;
		String retval ="";
		try {
			boolean checkDate = allowanceService.checkDate(daymulti,userId);
			if(checkDate){
			allowanceService.addNightAllowance(nightmulti,userId,prjId);
			allowanceDtoList = allowanceService.getWeekendNightallowance(UserController.userLoginID);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Night Shift Allowance ", "Successfully Added");
	        PrimeFaces.current().dialog().showMessageDynamic(message);	
			retval = "/pages/NightshiftAllowance.xhtml";
			}else {
				
			}
		}catch(Exception e) {
		 System.out.println(e);	
		}
		return retval;
	}
	
	public String addDayAllowance() {
		int userId = UserController.userLoginID;
		String retval ="";
		try {
			boolean checkDate = allowanceService.checkDate(daymulti,userId);
			if(checkDate){
				allowanceService.addDayAllowance(daymulti,userId,prjId);
				 allowanceDtoList = allowanceService.getWeekendDayallowance(userId);
				 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Day Shift Allowance ", "Successfully Added");
			     PrimeFaces.current().dialog().showMessageDynamic(message);	
			     
			}else {
				
				 hideDialogue = "PF('userDialog').show()";
			}
			
		}catch(Exception e) {
		 System.out.println(e);	
		}
		return retval;
	}

	public String deleteDayAllowance() {
		int userId = UserController.userLoginID;
		try {
			allowanceService.deleteAllowanceByID(selectedDayAllow.getAllowanceId());
			allowanceDtoList = allowanceService.getWeekendDayallowance(userId);
			
		}catch(Exception e) {
			System.out.println(e);	
		}
		return null;
	}
	
	public String deleteNightAllowance() {
		try {
			allowanceService.deleteAllowanceByID(selectedDayAllow.getAllowanceId());
			allowanceDtoList = allowanceService.getWeekendNightallowance(UserController.userLoginID);
			
		}catch(Exception e) {
			System.out.println(e);	
		}
		return null;
	}
	
	public String gotoNewAllowancePage() {
		try {
			
			
		 }catch(Exception e) {
		 	System.out.println(e);	
		 }
		return "/pages/allowanceAddPage.xhtml";
	}
	
	public String toWeekendDayShiftPage() {
		try {
			
		  allowanceDtoList = allowanceService.getWeekendDayallowance(UserController.userLoginID);
		  projectDtoList = allowanceService.getAllProjects();
		  for(ProjectDto projectDto :projectDtoList) {
			  projects.put(projectDto.getProjectName(), String.valueOf(projectDto.getProjectId()));
		  }
		  String projectType = UserController.projectType;
		  ProjectDto project = allowanceService.fetchProjectByType(projectType);
		  prjType = project.getProjectName();
		  prjId = project.getProjectId();
		  daymulti = new ArrayList<Date>();
		}catch(Exception e) {
		 System.out.println(e);
		}
		return "/pages/DayshiftAllowance.xhtml";
	}
	
	public String toWeekendNightShiftPage() {
		try {
		  allowanceDtoList = allowanceService.getWeekendNightallowance(UserController.userLoginID);
		  projectDtoList = allowanceService.getAllProjects();
		  for(ProjectDto projectDto :projectDtoList) {
			  projects.put(projectDto.getProjectName(), String.valueOf(projectDto.getProjectId()));
		  }
		  String projectType = UserController.projectType;
		  ProjectDto project = allowanceService.fetchProjectByType(projectType);
		  prjType = project.getProjectName();
		  prjId = project.getProjectId();
		  nightmulti = new ArrayList<Date>();
		}catch(Exception e) {
		 System.out.println(e);
		}
		return "/pages/NightshiftAllowance.xhtml";
	}
	
	public String toOnCallShiftPage() {
		try {
		  allowanceDtoList = allowanceService.getOnCallallowance(UserController.userLoginID);
		}catch(Exception e) {
		 System.out.println(e);
		}
		return "/pages/onCallAllowance.xhtml";
	}
	
	public void searchAllowanceByDate() {
		try {
			
			mainAllowanceDtoList = allowanceService.searchAllowanceByDate(fromDate,toDate,allowanceStatus,allowanceType,UserController.userLoginID);	
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	
}

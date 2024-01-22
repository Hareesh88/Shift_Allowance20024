package com.ust.allowance.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ust.allowance.dao.AllowanceDao;
import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.UserDto;
import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.Users;
import com.ust.allowance.util.MailHelper;

public class AllowanceServiceImpl implements AllowanceService{
	
	@Autowired
	private AllowanceDao allowanceDao;

	public AllowanceDao getAllowanceDao() {
		return allowanceDao;
	}

	public void setAllowanceDao(AllowanceDao allowanceDao) {
		this.allowanceDao = allowanceDao;
	}

	@Override
	public void approveAllowance(List<AllowanceDto> selectedAllowance) {
		System.out.println("approveAllowance");
		try {
			for(AllowanceDto allowanceDto :selectedAllowance) {
				
				System.out.println("approveAllowance........"+allowanceDto.getFirstName());
			}
			
		}catch(Exception e) {
			System.out.println(e);
			
		}
		
	}

	@Override
	public List<AllowanceDto> allowanceById(int userLoginId) {
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity>allowanceEntityList = null;
		try {
			allowanceEntityList = allowanceDao.allowanceById(userLoginId);
			for(AllowanceEntity allowanceEntity :allowanceEntityList ) {
				AllowanceDto allowanceDto = new AllowanceDto();
				//allowanceDto.setUserId("");
				//allowanceDto.setUstr_id(userDto.getUstr_id());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
				allowanceDto.setAllowanceStatus(allowanceEntity.getAllowanceStatus());
				allowanceDto.setAllowanceType(allowanceEntity.getAllowanceType());
				allowanceDtoList.add(allowanceDto);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}

	@Override
	public void addNightAllowance(List<Date> dateList,int userId,int project) {
		AllowanceEntity allowanceEntity = new AllowanceEntity();
		MailHelper mailHelper = new MailHelper();
		String subject ="";
		String text ="";
		try {
			for(Date newDate : dateList) {
			allowanceEntity.setAllowanceStatus("Inprogress");
			allowanceEntity.setAllowanceType("Night");
			allowanceEntity.setAllowanceDate(newDate);
			allowanceEntity.setUserId(userId);
			allowanceEntity.setProjectId(project);
			boolean retval = allowanceDao.addNightAllowance(allowanceEntity);
			if(retval){
				  Users user = allowanceDao.fetchUserDetails(userId);
			      Users newUser = allowanceDao.getUserByUstId(user.getManagerId());
			      subject = "Day Shift Allowance Approval Request is pending";
			      text = "Hi"+newUser.getFirstName()+newUser.getLastNmae()+"<br/> Please approve/reject the Night Shift.";
				  mailHelper.sendEmail(newUser.getEmail(), subject, text);
			     }
			}
		}catch(Exception e) {
		System.out.println(e);
		}
		
	}

	@Override
	public List<AllowanceDto> getWeekendDayallowance(int userLoginId) {
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity>allowanceEntityList = null;
		int index =1;
		try {
			allowanceEntityList = allowanceDao.getWeekendDayallowance(userLoginId);
			for(AllowanceEntity allowanceEntity :allowanceEntityList ) {
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setIndex(index);
				//allowanceDto.setUserId("");
				//allowanceDto.setUstr_id(userDto.getUstr_id());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
				String strDate = dateFormat.format(allowanceEntity.getAllowanceDate());  
				allowanceDto.setStrDate(strDate);
				allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
				allowanceDto.setAllowanceStatus(allowanceEntity.getAllowanceStatus());
				allowanceDto.setAllowanceType(allowanceEntity.getAllowanceType());
				if(allowanceEntity.getAllowanceStatus().equals("Inprogress")) {
					allowanceDto.setDisableButton(false);	
				}else {
					allowanceDto.setDisableButton(true);
				}
				allowanceDtoList.add(allowanceDto);
				index++;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}

	@Override
	public List<AllowanceDto> getWeekendNightallowance(int userLoginId) {
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity>allowanceEntityList = null;
		int index =1;
		try {
			allowanceEntityList = allowanceDao.getWeekendNightallowance(userLoginId);
			for(AllowanceEntity allowanceEntity :allowanceEntityList ) {
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setIndex(index);
				//allowanceDto.setUserId("");
				//allowanceDto.setUstr_id(userDto.getUstr_id());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
				String strDate = dateFormat.format(allowanceEntity.getAllowanceDate());  
				allowanceDto.setStrDate(strDate);
				allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
				allowanceDto.setAllowanceStatus(allowanceEntity.getAllowanceStatus());
				allowanceDto.setAllowanceType(allowanceEntity.getAllowanceType());
				if(allowanceEntity.getAllowanceStatus().equals("Inprogress")) {
					allowanceDto.setDisableButton(false);	
				}else {
					allowanceDto.setDisableButton(true);
				}
				allowanceDtoList.add(allowanceDto);
				index++;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}

	@Override
	public List<AllowanceDto> getOnCallallowance(int userLoginId) {
		List<AllowanceDto> allowanceDtoList = new ArrayList<AllowanceDto>();
		List<AllowanceEntity>allowanceEntityList = null;
		int index =1;
		try {
			allowanceEntityList = allowanceDao.getOnCallallowance(userLoginId);
			for(AllowanceEntity allowanceEntity :allowanceEntityList ) {
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setIndex(index);
				//allowanceDto.setUserId("");
				//allowanceDto.setUstr_id(userDto.getUstr_id());
				allowanceDto.setAllowanceDate(allowanceEntity.getAllowanceDate());
				allowanceDto.setAllowanceId(allowanceEntity.getAllowanceId());
				allowanceDto.setAllowanceStatus(allowanceEntity.getAllowanceStatus());
				allowanceDto.setAllowanceType(allowanceEntity.getAllowanceType());
				if(allowanceEntity.getAllowanceStatus()=="Inprogress") {
					allowanceDto.setDisableButton(false);	
				}else {
					allowanceDto.setDisableButton(true);
				}
				allowanceDtoList.add(allowanceDto);
				index++;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return allowanceDtoList;
	}

	@Override
	public void addDayAllowance(List<Date> dateList, int userId,int projectId) {
		AllowanceEntity allowanceEntity = new AllowanceEntity();
		MailHelper mailHelper = new MailHelper();
		String subject ="";
		String text ="";
		try {
			for(Date newDate : dateList) {
			allowanceEntity.setAllowanceStatus("Inprogress");
			allowanceEntity.setAllowanceType("Day");
			allowanceEntity.setAllowanceDate(newDate);
			allowanceEntity.setUserId(userId);
			allowanceEntity.setProjectId(projectId);
			boolean retval = allowanceDao.addNightAllowance(allowanceEntity);
			if(retval) {
				  Users user = allowanceDao.fetchUserDetails(userId);
			      Users newUser = allowanceDao.getUserByUstId(user.getManagerId());
			      subject = "Day Shift Allowance Approval Request is pending";
			      text = "Hi"+newUser.getFirstName()+newUser.getLastNmae()+"<br/> Please approve/reject the Day Shift.";
				  mailHelper.sendEmail(newUser.getEmail(), subject, text);
				
			}
			
			}
		}catch(Exception e) {
		System.out.println(e);
		}
		
	}
	
	@Override
	public List<ProjectDto> getAllProjects(){
		List<ProjectDto>projectDtoList = new ArrayList<ProjectDto>();
		List<ProjectEntity>projectEntityList = null;
		try {
			projectEntityList = allowanceDao.getAllProjects();
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
	public List<AllowanceDto>searchAllowanceByDate(Date fromDate,Date toDate,String allowanceStatus,String allowanceType,int ustrId){
		  List<AllowanceDto> alowanceList = new ArrayList<AllowanceDto>();
		  List<AllowanceEntity> entityList = null;
		  int index =1;
		try {
			entityList = allowanceDao.searchAllowanceByDate(fromDate, toDate, allowanceStatus, allowanceType, ustrId);
			System.out.println(entityList.size());
			for(AllowanceEntity allEntity :entityList) {
				AllowanceDto allowanceDto = new AllowanceDto();
				allowanceDto.setIndex(index);
				allowanceDto.setAllowanceId(allEntity.getAllowanceId());
				allowanceDto.setAllowanceDate(allEntity.getAllowanceDate());
				allowanceDto.setAllowanceStatus(allEntity.getAllowanceStatus());
				allowanceDto.setAllowanceType(allEntity.getAllowanceType());
				allowanceDto.setApprovedBy(allEntity.getApprovedBy());
				allowanceDto.setAllowanceDate(allEntity.getAllowanceDate());
				alowanceList.add(allowanceDto);
				index++;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return alowanceList;
	}
	
	@Override
	public void deleteAllowanceByID(int allowanceId) {
		
		try {
			allowanceDao.deleteAllowanceByID(allowanceId);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public ProjectDto fetchProjectByType(String projectType) {
		   ProjectDto projectDto = new ProjectDto();
		   List<ProjectEntity> prjEntityList = null;
		try {
			prjEntityList = allowanceDao.fetchProjectByType(projectType);
			for(ProjectEntity prj : prjEntityList) {
				projectDto.setProjectName(prj.getProjectName());	
			}
		}catch(Exception e) {
			System.out.println(e);		
		}
		return projectDto;
	}
	
	@Override
	public boolean checkDate(List<Date> dateList,int userId) {
		boolean checkval = false;
		List<AllowanceEntity> allowanceEntity = null;
		try {
			for(Date newDate : dateList) {
				allowanceEntity = allowanceDao.checkDate(newDate,userId);	
				if(allowanceEntity.isEmpty()) {
					checkval = true;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return checkval;
	}
	

}

package com.ust.allowance.service;

import java.util.Date;
import java.util.List;

import com.ust.allowance.dto.AllowanceDto;
import com.ust.allowance.dto.ProjectDto;
import com.ust.allowance.dto.UserDto;

public interface AllowanceService {
	
	public void approveAllowance(List<AllowanceDto> selectedAllowance);
	
	List<AllowanceDto>allowanceById(int userLoginId);
	
	public void addNightAllowance(List<Date> dateList,int userId,int project);
	
	public void addDayAllowance(List<Date> dateList,int userId,int project);
	
	
	List<AllowanceDto>getWeekendDayallowance(int userLoginId);
	
	List<AllowanceDto>getWeekendNightallowance(int userLoginId);
	
	List<AllowanceDto>getOnCallallowance(int userLoginId);
	
	List<ProjectDto> getAllProjects();
	
	List<AllowanceDto>searchAllowanceByDate(Date fromDate,Date toDate,String allowanceStatus,String allowanceType,int ustrId);
	
	public void deleteAllowanceByID(int allowanceId);
	
	public ProjectDto fetchProjectByType(String ProjectType);
	
	public boolean checkDate(List<Date> dateList,int userId);
	

}

package com.ust.allowance.dao;

import java.util.Date;
import java.util.List;

import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.Users;

public interface AllowanceDao {
	
	List<AllowanceEntity> allowanceById(int Ustr_id);
	
	public boolean addNightAllowance(AllowanceEntity allowanceEntity);
	
	List<AllowanceEntity> getWeekendDayallowance(int Ustr_id);
	
	List<AllowanceEntity> getWeekendNightallowance(int Ustr_id);
	
	List<AllowanceEntity> getOnCallallowance(int Ustr_id);
	
	public List<ProjectEntity> getAllProjects();
	
	public List<AllowanceEntity>searchAllowanceByDate(Date fromDate,Date toDate,String allowanceStatus,String allowanceType,int ustrId);
	
	public void deleteAllowanceByID(int allowanceId);
	
	public Users getUserByUstId(String ManagerId);
	
    public Users fetchUserDetails(int ustId);
	
    public List<ProjectEntity> fetchProjectByType(String projectType);
    
    public List<AllowanceEntity> checkDate(Date newDate,int userId);

}

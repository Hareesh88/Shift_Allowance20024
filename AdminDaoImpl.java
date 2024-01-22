package com.ust.allowance.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.Users;

public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<AllowanceEntity> getAllShiftAllowance() {
		List<AllowanceEntity> personsList = new ArrayList<AllowanceEntity>();
		try {
		
		 personsList = sessionFactory.getCurrentSession().createQuery("from AllowanceEntity").list();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return personsList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public Users getUserById(int userId) {
		Users user = new Users();
		try {
		
			 user = (Users) sessionFactory.getCurrentSession().createQuery("FROM Users U WHERE U.userId = :userId").setParameter("userId", userId)
		            .uniqueResult();
			
		System.out.println("User Id ..."+user.getUserId());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<Users> fetchUserByUserId(String ustr_id,String team,String ustId) {
		
		System.out.println("ustr_id....."+ustr_id);
		System.out.println("ustId....."+ustId);
		
		List<Users> list = new ArrayList<Users>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
			if(ustr_id!=null && ustr_id!="") {
			crit.add(Restrictions.eq("ustr_id",ustr_id));
			}
			crit.add(Restrictions.eq("managerId",ustId));
			if(team!=null && team!="") {
			int teamId = Integer.parseInt(team);	
			crit.add(Restrictions.eq("teamId",teamId));
			}
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<AllowanceEntity> getAllowanceByuserId(int ustId, String allowanceStatus) {
		List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
			crit.add(Restrictions.eq("userId",ustId));
			crit.add(Restrictions.eq("allowanceStatus",allowanceStatus));
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}
	
  public void approveAllowance(int allowanceId, String ustId) {
	  Transaction transaction = null;
	    try{
	        transaction = getSessionFactory().openSession().beginTransaction();
	        String hql = "UPDATE AllowanceEntity as a set approvedBy = :approvedBy, allowanceStatus = :allowanceStatus WHERE allowanceId = :allowanceId";
	        Query query = getSessionFactory().openSession().createQuery(hql);
	        query.setParameter("approvedBy", ustId);
	        query.setParameter("allowanceStatus", "Approved");
	        query.setParameter("allowanceId", allowanceId);
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	  
  }
  
  
  public void rejectAllowance(int allowanceId) {
	  Transaction transaction = null;
	    try{
	        transaction = getSessionFactory().openSession().beginTransaction();
	        String hql = "UPDATE AllowanceEntity set allowanceStatus = :allowanceStatus " + "WHERE allowanceId = :allowanceId";
	        Query query = getSessionFactory().openSession().createQuery(hql);
	        query.setParameter("allowanceStatus", "Rejected");
	        query.setParameter("allowanceId", allowanceId);
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);

	        // commit transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	  
  }

 @SuppressWarnings("unchecked")
 @Transactional
 @Override
 public List<AllowanceEntity> getAllShiftAllowanceByDate(Date startDate,Date endDate, String allowanceStatus){
	 List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
			crit.add(Restrictions.between("allowanceDate", startDate, endDate));
			crit.add(Restrictions.like("allowanceStatus", allowanceStatus));
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
  }
 
 
 @SuppressWarnings("unchecked")
	@Transactional
 @Override
	public void saveproject(ProjectEntity projectEnitity) {
		try {
			 getSessionFactory().getCurrentSession().save(projectEnitity);
			
		}catch(Exception e) {
		 System.out.println(e);	
		}
		
	}
  
 
 @SuppressWarnings("unchecked")
 @Transactional
 @Override
 public List<ProjectEntity> getAllProject(){
	    List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ProjectEntity.class);
			crit.addOrder(Order.desc("created"));
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
  }

 @SuppressWarnings("unchecked")
 @Transactional
 @Override
 public List<ProjectEntity> getProjectByID(int projectId){
	    List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ProjectEntity.class);
			crit.add(Restrictions.eq("projectId", projectId));
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
  }
 
 
    @SuppressWarnings("unchecked")
	@Transactional
    @Override
	public ProjectEntity fetchProjectById(int projectId) {
    	ProjectEntity projectEntity = new ProjectEntity();
		try {
		
			projectEntity = (ProjectEntity) sessionFactory.getCurrentSession().createQuery("FROM ProjectEntity U WHERE U.projectId = :projectId").setParameter("projectId", projectId)
		            .uniqueResult();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return projectEntity;
	}
	
    @SuppressWarnings("unchecked")
	@Transactional
    @Override
	public void updateProject(int projectId) {
    	 Transaction transaction = null;
 	    try{
 	        // start a transaction
 	    	
 	        transaction = getSessionFactory().openSession().beginTransaction();

 	        // save the student object
 	        String hql = "UPDATE ProjectEntity set status = :status " + "WHERE projectId = :projectId";
 	        Query query = getSessionFactory().openSession().createQuery(hql);
 	        query.setParameter("status", "InActive");
 	        query.setParameter("projectId", projectId);
 	        int result = query.executeUpdate();
 	        System.out.println("Rows affected: " + result);

 	        // commit transaction
 	        transaction.commit();
 	    } catch (Exception e) {
 	        if (transaction != null) {
 	            transaction.rollback();
 	        }
 	        e.printStackTrace();
 	    }
	}

    @SuppressWarnings("unchecked")
   	@Transactional
    @Override
   	public void saveTeam(TeamEntity teamEnitity) {
   		try {
   			 getSessionFactory().getCurrentSession().save(teamEnitity);
   			
   		}catch(Exception e) {
   		 System.out.println(e);	
   		}
   		
   	}
   
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<TeamEntity> getAllTeam(){
   	    List<TeamEntity> list = new ArrayList<TeamEntity>();
   		try {
   			Criteria crit = sessionFactory.getCurrentSession().createCriteria(TeamEntity.class);
   			crit.addOrder(Order.desc("created"));
   			list = crit.list();
   		}catch(Exception e) {
   			System.out.println(e);
   		}
   		  return list;
     }
    
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Users>getAllManagers(){
   	    List<Users> list = new ArrayList<Users>();
   		try {
   			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
   			crit.add(Restrictions.eq("role_id", 2));
   			list = crit.list();
   		}catch(Exception e) {
   			System.out.println(e);
   		}
   		  return list;
     }
    
      @SuppressWarnings("unchecked")
      @Transactional
       @Override
       public Users getUserByUstId(String managerId) {
    	  Users users = new Users();
   		try {
   		
   			users = (Users) sessionFactory.getCurrentSession().createQuery("FROM Users U WHERE U.ustr_id = :ustr_id").setParameter("ustr_id", managerId)
   		            .uniqueResult();
   		}catch(Exception e) {
   			System.out.println(e);
   		}
   		return users;
   	}
    
      @SuppressWarnings("unchecked")
      @Transactional
      @Override
      public Users getUserByUstID(String ustId) {
    	  Users users = new Users();
     		try {
     		
     			users = (Users) sessionFactory.getCurrentSession().createQuery("FROM Users U WHERE U.ustr_id = :ustr_id").setParameter("ustr_id", ustId)
     		            .uniqueResult();
     		}catch(Exception e) {
     			System.out.println(e);
     		}
     		return users;  
      }
      
      @SuppressWarnings("unchecked")
      @Transactional
      @Override
      public List<Users>fetchUserByManagerId(String ustId){
    	  List<Users>userslist = new ArrayList<Users>();
    	  try {
    		  Criteria crit = sessionFactory.getCurrentSession().createCriteria(Users.class);
     			crit.add(Restrictions.eq("managerId", ustId));
     			userslist = crit.list();  
    	  }catch(Exception e) {
    		  System.out.println(e);  
    	  }
    	 return userslist; 
      }
      
      
    
}

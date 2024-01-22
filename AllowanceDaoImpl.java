package com.ust.allowance.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ust.allowance.entity.AllowanceEntity;
import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.Users;

public class AllowanceDaoImpl implements AllowanceDao{
	
	
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
	public List<AllowanceEntity> allowanceById(int userId) {
		List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
		try {
			
			list = sessionFactory.getCurrentSession()
			           .createQuery("from AllowanceEntity where userId = :userId")
			                 .setParameter("userId", userId).list();	
			
		System.out.println("size...."+list.size());
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public boolean addNightAllowance(AllowanceEntity allowanceEntity) {
		boolean retval = false;
		try {
			getSessionFactory().getCurrentSession().save(allowanceEntity);
			 retval = true;
		}catch(Exception e) {
			retval = false;
		    System.out.println(e);	
		}
		return retval;
	}
   
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AllowanceEntity> getWeekendDayallowance(int Ustr_id) {
		List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
			crit.add(Restrictions.eq("userId",Ustr_id));
			crit.add(Restrictions.eq("allowanceType","Day"));
			crit.addOrder(Order.desc("allowanceDate"));
			crit.setMaxResults(7);
			list = crit.list();
			/*
			 * list = sessionFactory.getCurrentSession()
			 * .createQuery("from AllowanceEntity where userId = :userId")
			 * .setParameter("userId", Ustr_id).list();
			 * 
			 * System.out.println("size...."+list.size());
			 */
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}
 
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AllowanceEntity> getWeekendNightallowance(int Ustr_id) {
		   List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
			crit.add(Restrictions.eq("userId",Ustr_id));
			crit.add(Restrictions.eq("allowanceType","Night"));
			crit.addOrder(Order.desc("allowanceDate"));
			crit.setMaxResults(7);
			list = crit.list();
			/*
			 * list = sessionFactory.getCurrentSession()
			 * .createQuery("from AllowanceEntity where userId = :userId")
			 * .setParameter("userId", Ustr_id).list();
			 * 
			 * System.out.println("size...."+list.size());
			 */
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<AllowanceEntity> getOnCallallowance(int Ustr_id) {
		  List<AllowanceEntity> list = new ArrayList<AllowanceEntity>();
			try {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
				crit.add(Restrictions.eq("userId",Ustr_id));
				crit.add(Restrictions.eq("allowanceType","OnCall"));
				list = crit.list();
				/*
				 * list = sessionFactory.getCurrentSession()
				 * .createQuery("from AllowanceEntity where userId = :userId")
				 * .setParameter("userId", Ustr_id).list();
				 * 
				 * System.out.println("size...."+list.size());
				 */
			}catch(Exception e) {
				System.out.println(e);
			}
			  return list;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<ProjectEntity> getAllProjects() {
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
			
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ProjectEntity.class);
			crit.add(Restrictions.eq("status","Active"));
			list = crit.list();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
   public List<AllowanceEntity>searchAllowanceByDate(Date fromDate,Date toDate,String allowanceStatus,String allowanceType,int ustrId){
	   List<AllowanceEntity>allowanceEntityList = null;
	try {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
		crit.add(Restrictions.eq("allowanceStatus",allowanceStatus));
		crit.add(Restrictions.between("allowanceDate", fromDate, toDate));
		crit.add(Restrictions.eq("allowanceType",allowanceType));
		crit.add(Restrictions.eq("userId",ustrId));
		crit.addOrder(Order.desc("allowanceDate"));
		allowanceEntityList = crit.list();
		
	}catch(Exception e) {
		System.out.println(e);
	}
	 return allowanceEntityList;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public void deleteAllowanceByID(int allowanceId) {
		
	try {
		
		String SQL_QUERY =" DELETE AllowanceEntity e WHERE allowanceId IN (:allowanceId)";
		Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		query.setParameter("allowanceId", allowanceId);
		query.executeUpdate();
		
		/*
		 * AllowanceEntity allowanceEntity = (AllowanceEntity )
		 * sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class)
		 * .add(Restrictions.eq("allowanceId", allowanceId)).uniqueResult();
		 * sessionFactory.getCurrentSession().delete(allowanceEntity);
		 */
		 
	}catch(Exception e) {
		System.out.println(e);	
	}
		
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
	public Users fetchUserDetails(int userId) {
		Users users = new Users();
		try {
			users = (Users) sessionFactory.getCurrentSession().createQuery("FROM Users U WHERE U.userId = :ustID").setParameter("ustID", userId)
		            .uniqueResult();
		}catch(Exception e) {
			System.out.println(e);
		}
		return users;
	}
	
    
    @SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ProjectEntity> fetchProjectByType(String projectType) {
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ProjectEntity.class);
			crit.add(Restrictions.like("projectType",projectType));
			crit.add(Restrictions.like("status","Active"));
			list = crit.list();
			/*
			 * list = sessionFactory.getCurrentSession()
			 * .createQuery("from AllowanceEntity where userId = :userId")
			 * .setParameter("userId", Ustr_id).list();
			 * 
			 * System.out.println("size...."+list.size());
			 */
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}
    
    @SuppressWarnings("unchecked")
	@Transactional
	@Override
    public List<AllowanceEntity> checkDate(Date newDate,int userId) {
    	List<AllowanceEntity> allowanceEntity = null;
    	try {
    		Criteria crit = sessionFactory.getCurrentSession().createCriteria(AllowanceEntity.class);
    		crit.add(Restrictions.between("allowanceDate", newDate, newDate));
			crit.add(Restrictions.eq("userId",userId));
			allowanceEntity = crit.list();
    	}catch(Exception e) {
			System.out.println(e);
		}
     return allowanceEntity;	
    }
    
    
    
}

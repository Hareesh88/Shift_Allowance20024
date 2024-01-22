package com.ust.allowance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ust.allowance.entity.ProjectEntity;
import com.ust.allowance.entity.TeamEntity;
import com.ust.allowance.entity.UserRole;
import com.ust.allowance.entity.Users;

public class UserDaoImpl implements UserDao{
	
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
	public Users fetchUserById(String ustr_id, String password) {
		List list = new ArrayList();
		try {
		String SQL_QUERY =" from Users as o where o.ustr_id=? and o.password=?";
		Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		query.setParameter(0,ustr_id);
		query.setParameter(1,password);
		list = query.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return (Users)list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<Users> getAllUserDetails() {
		List<Users> usersList = new ArrayList<Users>();
		try {
			
			usersList = sessionFactory.getCurrentSession().createQuery("from Users").list();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return usersList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public UserRole getUserRoleById(int role_id) {
		UserRole userRole = new UserRole();
		try {
			userRole = (UserRole) sessionFactory.getCurrentSession().createQuery("FROM UserRole U WHERE U.roleId = :roleId").setParameter("roleId", role_id)
			            .uniqueResult();
			System.out.println(userRole.getRoleId());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return userRole;
	}

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public boolean  saveUser(Users users) {
		boolean retval = false;
		 try {
			 getSessionFactory().getCurrentSession().save(users);
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
	public List<TeamEntity> getAllTeamDetails() {
		List<TeamEntity> list = new ArrayList<TeamEntity>();
		try {
			
			list = sessionFactory.getCurrentSession().createQuery("from TeamEntity").list();
			
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
			
			list = sessionFactory.getCurrentSession().createQuery("from ProjectEntity").list();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public TeamEntity getTeamByManager(int teamId) {
		TeamEntity teamEntity = new TeamEntity();
		try {
			teamEntity = (TeamEntity) sessionFactory.getCurrentSession().createQuery("FROM TeamEntity U WHERE U.teamId = :teamId").setParameter("teamId", teamId)
			            .uniqueResult();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return teamEntity;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Users getUserDetailsByUserId(String ustID) {
		Users users = new Users();
		try {
			
			users = (Users) sessionFactory.getCurrentSession().createQuery("FROM Users U WHERE U.ustr_id = :ustID").setParameter("ustID", ustID)
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
	public boolean updateUser(Users user) {
		boolean reval = false;
		Transaction transaction = null;
	    try{
		  transaction = getSessionFactory().openSession().beginTransaction();

	        // save the student object
	        String hql = "UPDATE Users set password = :password " + "WHERE userId = :userId";
	        Query query = getSessionFactory().openSession().createQuery(hql);
	        query.setParameter("password", user.getPassword());
	        query.setParameter("userId", user.getUserId());
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);

	        // commit transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
		
	     }
	    return reval; 
	}

	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public List<TeamEntity> getTeamDetailsByManagerID(String ustID) {
		List<TeamEntity> list = new ArrayList<TeamEntity>();
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(TeamEntity.class);
			crit.add(Restrictions.eq("managerId", ustID));
			list = crit.list();
		}catch(Exception e) {
			System.out.println(e);
		}
		  return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public ProjectEntity fetchProjectByType(String projectType) {
		 ProjectEntity prj = new ProjectEntity();
		try {
			
			prj = (ProjectEntity) sessionFactory.getCurrentSession().createQuery("FROM ProjectEntity U WHERE U.projectType = :projectType").setParameter("projectType", projectType)
		            .uniqueResult();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return prj;
	}
	
	
	
	
	/*Users user = (Users) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
            .uniqueResult();
	if (user != null && user.getPassword().equals(password)) {
        return true;
    }*/
	
	
	/*Query query = (Query)sessionFactory.getCurrentSession().createSQLQuery("from Users where(:ustr_id, :password)")
			   .addEntity(Users.class)
			   .setParameter("ustr_id", ustr_id)
			   .setParameter("password", password).uniqueResult();*/
	
	//UserDetails userDetails = (UserDetails) sessionFactory.getCurrentSession().get(UserDetails.class, id);
	/*@SuppressWarnings("unchecked")
	@Transactional
    @Override
	public Users fetchUserById(String userName, String password) {
		System.out.println("inside dao");
		List list = sessionFactory.getCurrentSession()
		           .createQuery("from Users where firstName=?")
		                 .setParameter(0, userName).list();
		System.out.println(list.size());
		  return (Users)list.get(0);
	}*/
	
	
	

}

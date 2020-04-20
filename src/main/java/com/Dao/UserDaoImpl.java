package com.Dao;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Model.User;
import com.Dao.UserDao;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean addUser(User user) {
		
		
		try
		{
			
			sessionFactory.getCurrentSession().save(user);
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateUser(User user) {
		try
		{
			User userToUpdate=getUser(user.getId());
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setEnabled(user.isEnabled());
			userToUpdate.setRole(user.getRole());
			
			
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public User getUser(int id) {
		Session session=sessionFactory.openSession();
		User user=(User)session.get(User.class, id);
		session.close();
		return user;
	}

	@Transactional
	@Override
	public List<User> retrieveUser(int otp) {
		
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User where password="+otp+" order by id desc");
		query.setMaxResults(1);
		List<User>listUser=query.list();
		session.close();
		return listUser;
	}

	@Transactional
	@Override
	public List<User> retrieveUser1(int id) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User where id="+id+" order by id desc");
		query.setMaxResults(1);
		List<User>listUser=query.list();
		session.close();
		return listUser;	
	}

	@Transactional
	@Override
	public List<User> getUserByEmail(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select u.username, u.password, u.name, u.phone from User u where u.username=:username");
		query.setParameter("username", username);
		query.setMaxResults(1);
		List<User> user=query.getResultList();
		session.close();
		return user;
	}

	@Transactional
	@Override
	public boolean updateUserByEmail(String username,String password) {
		try
		{
			Session session=sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Query query=session.createQuery("update User u set u.password=:password where u.username= :username");
			query.setParameter("password", password);
			query.setParameter("username", username);
			query.executeUpdate();
			txn.commit();
			return true;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public long countRow(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select count(*) from User u where u.username=:username");
		query.setParameter("username", username);
		long markernoList=(long) query.getSingleResult();
		long countRow=0;
        if (markernoList!=0) {
        	countRow = markernoList;
            System.out.println("Total Results:" + countRow);
        }
        else
        {
        	countRow = 0;
            System.out.println("Total Results:" + countRow);

        }
        session.close(); 
        
		return countRow;
	}

	@Transactional
	@Override
	public List<User> getNameByEmail(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select u.name from User u where u.username=:username");
		query.setParameter("username", username);
		query.setMaxResults(1);
		List<User> user=query.getResultList();
		session.close();
		return user;
	}

	@Transactional
	@Override
	public List<User> getPhoneByEmail(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select u.phone from User u where u.username=:username");
		query.setParameter("username", username);
		query.setMaxResults(1);
		List<User> user=query.getResultList();
		session.close();
		return user;
	}

	@Override
	public String getIdByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select u.id from User u where u.username=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String userId=query.getResultList().toString();
		session.close();
		return userId;
	}	
	

}

package com.Dao;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Model.Temperature;
import com.Model.User;


@Repository("temperatureDao")
public class TemperatureDaoImpl implements TemperatureDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean updateTempById(String loginId, String temp, String lat,String lng, String datetime,String address,String fever,String cough,String tiredness, String breathingprob,String symptoms,String futureDate,String markerno, String cluster, String currentPosition,String startPosition,File tracker, String travelWithin10Days) {
		try
		{
			Session session=sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Query query=session.createQuery("update Temperature t set t.temp=:temp, t.lat=:lat, t.lng=:lng, t.datetime=:datetime, t.address=:address, t.fever=:fever, t.cough=:cough, t.tiredness=:tiredness, t.breathingProb=:breathingProb, t.symptoms=:symptoms, t.userStatus=1, t.futureDate=:futureDate, t.markerno=:markerno, t.cluster=:cluster, t.currentPosition=:currentPosition, t.startPosition=:startPosition,t.tracker=:tracker,t.travelWithin10Days=:travelWithin10Days where t.loginId= :loginId");
			query.setParameter("loginId", loginId);
			query.setParameter("temp", temp);
			query.setParameter("lat", lat);
			query.setParameter("lng", lng);
			query.setParameter("datetime", datetime);
			query.setParameter("address", address);
			query.setParameter("fever", fever);
			query.setParameter("cough", cough);
			query.setParameter("tiredness", tiredness);
			query.setParameter("breathingProb", breathingprob);
			query.setParameter("symptoms", symptoms);
			query.setParameter("futureDate", futureDate);
			query.setParameter("markerno", markerno);
			query.setParameter("cluster", cluster);
			query.setParameter("currentPosition", currentPosition);
			query.setParameter("startPosition", startPosition);
			query.setParameter("tracker", tracker);
			query.setParameter("travelWithin10Days", travelWithin10Days);
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

	@Transactional
	@Override
	public boolean addTemperature(Temperature temperature) {
		try
		{
			
			sessionFactory.getCurrentSession().save(temperature);
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public List<Temperature> getUserByloginId(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Temperature t where t.loginId=:username");
		query.setParameter("username", username);
		query.setMaxResults(1);
		List<Temperature> temp=query.getResultList();
		session.close();
		return temp;
	}

	

	@Override
	public List<Temperature> retrieveLat() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.lat from Temperature t where t.temp > 98.4");
		List<Temperature> latList=query.getResultList();
		session.close();
		return latList;
	}

	@Override
	public List<Temperature> retrieveLng() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.lng from Temperature t where t.temp > 98.4");
		List<Temperature> lngList=query.getResultList();
		session.close();
		return lngList;
	}

	@Override
	public List<Temperature> retrieveTemp() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.temp from Temperature t where t.temp > 98.4");
		List<Temperature> tempList=query.getResultList();
		session.close();
		return tempList;
	}

	@Override
	public List<Temperature> retrieveAddress() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.address from Temperature t where t.temp > 98.4");
		List<Temperature> addressList=query.getResultList();
		session.close();
		return addressList;
	}

	@Override
	public List<Temperature> retrieveMarkerno() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.markerno from Temperature t where t.temp > 98.4");
		List<Temperature> markernoList=query.getResultList();
		session.close();
		return markernoList;
	}

	public long countRow() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select count(*) from Temperature t where t.temp > 98.4");
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

	@Override
	public List<Temperature> retrieveTemperature() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Temperature t where t.temp > 98.4");
		List<Temperature>listTemperature=query.list();
		session.close();
		return listTemperature;
	}

	@Override
	public String retrieveDateTimeByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.datetime from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String datetime=query.getResultList().toString();
		session.close();
		return datetime;
	}

	@Override
	public String retrievefutureDateByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.futureDate from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String futureDate=query.getResultList().toString();
		session.close();
		return futureDate;
	}

	@Override
	public List<Temperature> retrieveCluster() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.cluster from Temperature t where t.temp > 98.4");
		List<Temperature> clusterList=query.list();
		session.close();
		return clusterList;
	}

	@Override
	public String retrieveTempIdByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.id from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String tempId=query.getResultList().toString();
		session.close();
		return tempId;
	}

	@Override
	public String getClusterByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.cluster from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String cluster=query.getResultList().toString();
		session.close();
		return cluster;
	}
	

	@Transactional
	@Override
	public boolean updateCurrentPositionByEmail(String currentPosition,String email){
	    try
		{
			Session session=sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Query query=session.createQuery("update Temperature t set t.currentPosition=:currentPosition where t.loginId= :email");
			query.setParameter("email", email);
			query.setParameter("currentPosition", currentPosition);
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
	public List<Temperature> getCurrentPosition() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.currentPosition from Temperature t where t.temp > 98.4");
		List<Temperature> currentPosition=query.list();
		session.close();
		return currentPosition;
	}

	@Override
	public String getCurrentPositionbByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.currentPosition from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String cposition=query.getResultList().toString();
		session.close();
		return cposition;
	}

	@Override
	public String getStartPositionbByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.startPosition from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String sposition=query.getResultList().toString();
		session.close();
		return sposition;
	}

	@Transactional
	@Override
	public boolean updateMarkerNoByEmail(String email,String markerno) {
		try
		{
			Session session=sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Query query=session.createQuery("update Temperature t set t.markerno=:markerno where t.loginId= :email");
			query.setParameter("markerno", markerno);
			query.setParameter("email", email);
			
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
	public String getIdByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.id from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String sposition=query.getResultList().toString();
		session.close();
		return sposition;
	}

	@Override
	public String getNameByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.name from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String name=query.getResultList().toString();
		session.close();
		return name;
	}

	@Override
	public String getPhoneByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.phone from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String phone=query.getResultList().toString();
		session.close();
		return phone;
	}
	
	@Override
	public String getTempByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.temp from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String temp=query.getResultList().toString();
		session.close();
		return temp;
	}

	@Override
	public String getFileByEmail(String email) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.tracker from Temperature t where t.loginId=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		String tracker=query.getResultList().toString();
		session.close();
		return tracker;
	}

	public List<Temperature> retrievePersonsWithHighFever() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Temperature t where t.temp > 98.4");
		List<Temperature> highFever=query.list();
		session.close();
		return highFever;
	}

	@Override
	public List<Temperature> retrievePersonsWithSymptomsPositive() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Temperature t where t.temp > 98.4 and symptoms='positive'");
		List<Temperature> symptomsPositive=query.list();
		session.close();
		return symptomsPositive;
	}

	@Override
	public String getCurrentPositionByMarkerNo(String markerno) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select t.currentPosition from Temperature t where t.markerno=:markerno");
		query.setParameter("markerno", markerno);
		query.setMaxResults(1);
		String cposition=query.getResultList().toString();
		session.close();
		return cposition;
	}

	@Override
	public String countPersonWithHighFever() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select count(*) from Temperature t where t.temp > 98.4");
		query.setMaxResults(1);
		String cfever=query.getResultList().toString();
		session.close();
		return cfever;	
		
	}

	@Override
	public String countPersonWithSymptomsPositive() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select count(*) from Temperature t where t.symptoms='positive'");
		query.setMaxResults(1);
		String cpositive=query.getResultList().toString();
		session.close();
		return cpositive;	
	}

	@Override
	public String countPersonAddedBodyTemperature() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select count(*) from Temperature t");
		query.setMaxResults(1);
		String caddedtemp=query.getResultList().toString();
		session.close();
		return caddedtemp;
	}

	

}

package com.Dao;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;

import com.Model.Temperature;


public interface TemperatureDao {
	
	public boolean updateTempById(String loginId,String temp,String lat,String lng, String datetime,String address,String fever,String cough,String tiredness, String breathingprob,String symptoms,String futureDate,String markerno,String cluster,String currentPosition,String startPosition,File tracker, String travelWithin10Days);
    public boolean addTemperature(Temperature temperature);
    public List<Temperature> getUserByloginId(String username);
    public List<Temperature>retrieveLat();
    public List<Temperature>retrieveLng();
    public List<Temperature>retrieveTemp();
    public List<Temperature>retrieveAddress();
    public List<Temperature>retrieveMarkerno();
    public long countRow();
    public String retrieveTempIdByEmail(String email);
    public List<Temperature>retrieveTemperature();
    public String retrieveDateTimeByEmail(String email);
    public String retrievefutureDateByEmail(String email);
    public List<Temperature>retrieveCluster();
    public List<Temperature>getCurrentPosition();
    public String getClusterByEmail(String email);
    public boolean updateCurrentPositionByEmail(String email,String currentPosition);
    
    public String getCurrentPositionbByEmail(String email);
    public String getStartPositionbByEmail(String email);
    public String getIdByEmail(String email);
    public boolean updateMarkerNoByEmail(String email,String markerno);
    
    public String getNameByEmail(String email);
    public String getPhoneByEmail(String email);
    
    public String getTempByEmail(String email);
    
    public String getFileByEmail(String email);
	public List<Temperature> retrievePersonsWithHighFever();
	public List<Temperature> retrievePersonsWithSymptomsPositive();
	
	public String getCurrentPositionByMarkerNo(String markerno);
	
	public String countPersonWithHighFever();
	
	public String countPersonWithSymptomsPositive();
	
	public String countPersonAddedBodyTemperature();
}

package com.Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Dao.TemperatureDao;
import com.Model.Temperature;



@Async
@Controller
public class AdminPanelController {
	
	@Autowired
	TemperatureDao temperatureDao;
	
	/*
	 * @RequestMapping(value="/affectedAreaMap",method= RequestMethod.GET) public
	 * String affectedArea(Model m,HttpSession session,HttpServletRequest request) {
	 * if(session.getAttribute("username") != null) {
	 * List<Temperature>listLat=temperatureDao.retrieveLat();
	 * m.addAttribute("listLat",listLat);
	 * 
	 * request.setAttribute("listLat", listLat);
	 * System.out.println("Lat"+request.getAttribute("listLat"));
	 * 
	 * List<Temperature>listLng=temperatureDao.retrieveLng();
	 * m.addAttribute("listLng",listLng);
	 * 
	 * request.setAttribute("listLng", listLng);
	 * System.out.println("Lng"+request.getAttribute("listLng"));
	 * 
	 * List<Temperature>listMarkerno=temperatureDao.retrieveMarkerno();
	 * m.addAttribute("listMarkerno",listMarkerno);
	 * 
	 * request.setAttribute("listMarkerno", listMarkerno);
	 * System.out.println("Markerno"+request.getAttribute("listMarkerno"));
	 * 
	 * List<Temperature>listAddress=temperatureDao.retrieveAddress();
	 * m.addAttribute("listAddress",listAddress);
	 * 
	 * request.setAttribute("listAddress", listAddress);
	 * System.out.println("Address"+request.getAttribute("listAddress"));
	 * session.setAttribute("addresslist", listAddress);
	 * 
	 * Temperature temperature=new Temperature(); m.addAttribute(temperature);
	 * 
	 * List<Temperature>listTemperature=temperatureDao.retrieveTemperature();
	 * m.addAttribute("temperatureList",listTemperature);
	 * 
	 * List<Temperature>clusterList=temperatureDao.retrieveCluster();
	 * m.addAttribute("clusterList",clusterList);
	 * session.setAttribute("clusterList", clusterList);
	 * 
	 * String cluster=temperatureDao.getClusterByEmail((String)
	 * session.getAttribute("username")); session.setAttribute("cluster", cluster);
	 * 
	 * 
	 * 
	 * return "affectedAreaMap"; } else { return "pleaselogin"; }
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	@RequestMapping(value="/highFever",method= RequestMethod.GET)
    public String highFever(Model m,HttpSession session,HttpServletRequest request)
    {
		if(session.getAttribute("username") != null)
		{
			
			List<Temperature>highFever=temperatureDao.retrievePersonsWithHighFever();
			m.addAttribute("highFever",highFever);
			
			
	        return "highFever";
		}
		else
		{
			return "pleaselogin";
		}
		
    }
	
	
	
	@RequestMapping(value="/symptomsPositive",method= RequestMethod.GET)
    public String symptomsPositive(Model m,HttpSession session,HttpServletRequest request)
    {
		if(session.getAttribute("username") != null)
		{
			
			List<Temperature>symptomsPositive=temperatureDao.retrievePersonsWithSymptomsPositive();
			m.addAttribute("symptomsPositive",symptomsPositive);
			
			
			return "symptomsPositive";
			
	        
		}
		else
		{
			return "pleaselogin";
		}
		
    }
	
	
	@RequestMapping(value="/trackPerson/{loginId:.+}",method= RequestMethod.GET)
    public String tracker(Model m,HttpSession session,HttpServletRequest request,@PathVariable("loginId") String loginId)
    {
		if(session.getAttribute("username") != null)
		{
		   
		   ArrayList alist=new ArrayList();	
		   try
		   {
			    Class.forName("org.relique.jdbc.csv.CsvDriver");

			    
			     Properties props = new java.util.Properties();
                 
			     props.put("fileExtension", ".csv");
			     //props.put("suppressHeaders", true);
			     props.put("separator", ",");
			     props.put("headerline", "EMAIL,NAME,PHONE,STARTPOSITION,CLUSTERPOSITION,CURRENTPOSITION,TEMPERATURE");
			     props.put("columnTypes", "String,String,String,String,String,String,String");
			     props.put("suppressHeaders", "true");
			     String url = "jdbc:relique:csv:" + "D:\\eclipse-workspace-2019\\Coronoid\\src\\main\\webapp\\tracker\\";
			     Connection conn = DriverManager.getConnection(url);

			    
			    Statement stmt = conn.createStatement();

			    
			    ResultSet results = stmt.executeQuery("SELECT CURRENTPOSITION, EMAIL FROM tracker where EMAIL='"+loginId+"'");

			   
			    while(results.next())
			    {
			   
			    String c=results.getString("CURRENTPOSITION");
			    
			    alist.add(c);	
			    String current=alist.toString();
			    System.out.println("JDBC"+current);
				session.setAttribute("current", current);
				System.out.println("currentList"+current);
				
				String lastcp=temperatureDao.getCurrentPositionbByEmail(loginId);
				String lastcurrentposition=lastcp.substring(lastcp.indexOf("[")+1, lastcp.indexOf("]"));
				System.out.println("lastcurrentposition"+lastcurrentposition);
				session.setAttribute("lastcurrentposition", lastcurrentposition);
				}
				
		        conn.close();
				
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   System.out.println("No Records");
			   
			   return "norecords";
			     
		   }
			
			
			
			return "tracker";
			
	        
		}
		else
		{
			return "pleaselogin";
		}
		
    }
}
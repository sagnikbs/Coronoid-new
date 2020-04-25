package com.Controller;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Dao.TemperatureDao;
import com.Model.Temperature;
import com.Model.User;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


@SuppressWarnings({ "unused", "deprecation" })
@Async
@Controller
public class TemperatureController {
	
	@Autowired
	TemperatureDao temperatureDao;
	
	
	
	@RequestMapping(value="/addBodyTemperature",method=RequestMethod.POST)
	public String register1(Model m,HttpSession session,@RequestParam("temp") String temp,
			@RequestParam("loginId") String loginId,
			@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam("datetime") String datetime,
			@RequestParam("address") String address,
			@RequestParam("fever") String fever,
			@RequestParam("cough") String cough,
			@RequestParam("tiredness") String tiredness,
			@RequestParam("breathingProb") String breathingProb,
			@RequestParam("symptoms") String symptoms,
			@RequestParam("travelWithin10Days") String travelWithin10Days,HttpServletRequest request) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InvalidFormatException
	{
		if(session.getAttribute("username") != null)
		{
			Temperature temperature=new Temperature();
			temperature.setTemp(temp);
			temperature.setLoginId((String) session.getAttribute("username"));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.println(now);
			temperature.setDatetime(dtf.format(now));
			
			LocalDateTime futureDate=LocalDateTime.from(now).plusDays(7);
			System.out.println(futureDate);
			temperature.setFutureDate(dtf.format(futureDate));
			
			temperature.setLat(lat);
			temperature.setLng(lng);
			
			temperature.setName(session.getAttribute("getNameByEmail").toString());
			temperature.setPhone(session.getAttribute("getPhoneByEmail").toString());

			
		    String length=temperatureDao.retrieveTempIdByEmail((String) session.getAttribute("username"));
		    
		    temperature.setMarkerno(length);
		    
		    temperature.setAddress("["+"'"+temperature.getName().substring(temperature.getName().indexOf("[")+1,temperature.getName().indexOf("]"))+"'"+","+lat+","+lng+","+length+""+"]");
			
		    
		    
		    temperature.setStartPosition("{lat: "+lat+","+"lng: "+lng+"}");
		    
		    temperature.setCurrentPosition("{lat: "+lat+","+"lng: "+lng+"}");
		    
		    temperature.setCluster("{lat: "+lat+","+"lng: "+lng+"}");
		    
		    temperature.setFever(fever);
		    temperature.setCough(cough);
		    temperature.setTiredness(tiredness);
		    temperature.setBreathingProb(breathingProb);
		    temperature.setTravelWithin10Days(travelWithin10Days);
		    
			
		    if(temperature.getFever().equals("YES") && temperature.getCough().equals("YES")
		    	&& temperature.getTiredness().equals("YES") && temperature.getBreathingProb().equals("YES"))
		    {
		    	temperature.setSymptoms("positive");
		    }
		    else
		    {
		    	temperature.setSymptoms("negative");
		    }
			
		    temperature.setUserStatus("1");
		    
		    
		    
		    
			if(temperatureDao.getUserByloginId((String) session.getAttribute("username")).isEmpty())
			{
				
				
				
				temperatureDao.addTemperature(temperature);	
				String marker=temperatureDao.getIdByEmail((String) session.getAttribute("username"));
				temperature.setMarkerno(marker.substring(marker.indexOf("[")+1,marker.indexOf("]")));
				temperatureDao.updateMarkerNoByEmail((String) session.getAttribute("username"), temperature.getMarkerno());
				session.setAttribute("datetime", temperature.getDatetime());
				session.setAttribute("futureDate", temperature.getFutureDate());
				
				String lengthnew=temperatureDao.retrieveTempIdByEmail((String) session.getAttribute("username"));
				
				
				try 
			    {
		            
					/* Fillo fillo=new Fillo(); */

	                String email=session.getAttribute("username").toString();
			    	String name=session.getAttribute("getNameByEmail").toString();
			    	String phone=session.getAttribute("getPhoneByEmail").toString();
			    	String startPosition="{lat: "+lat+","+"lng: "+lng+"}";
			    	String clusterPosition="{lat: "+lat+","+"lng: "+lng+"}";
			    	String currentPosition="{lat: "+lat+","+"lng: "+lng+"}";
			    	String temp1=temp;
			    	String markerno=lengthnew;
			    	
			    	//File file = new File("D:\\eclipse-workspace-2019\\Coronoid\\src\\main\\webapp\\tracker\\tracker.csv"); 	    
			    	//File file = new File("https://jboss-webserver31-tomcat-coronoid-sb-new.apps.ca-central-1.starter.openshift-online.com/Coronoid/tracker.csv".toString());
			    	
			    	File file=ResourceUtils.getFile("tracker.csv");
					
					System.out.println("a->"+file.getAbsolutePath());
					System.out.println("c->"+file.getCanonicalPath());
			    	
			    	FileWriter outputfile = new FileWriter(file,true); 
			    	  
			        // create CSVWriter object filewriter object as parameter 
			        CSVWriter writer = new CSVWriter(outputfile); 
			  
			        FileReader filereader = new FileReader(file); 
			        CSVReader reader=new CSVReaderBuilder(filereader).build();
			        List<String[]> allData = reader.readAll();
			        
			        if(allData.isEmpty())
			        {
			        	String[] header= {"EMAIL", "NAME", "PHONE", "STARTPOSITION", "CLUSTERPOSITION", "CURRENTPOSITION", "TEMPERATURE"};
			        	String[] data1 = { email, name, phone, startPosition, clusterPosition, currentPosition, temp1}; 
			        	
			        	List<String[]> list = new ArrayList<String[]>();		        	
			        	list.add(header);
			        	list.add(data1);
			        	
				        writer.writeAll(list);
				        System.out.println("CSV Header+Data");
			        }
			        else
			        {
			        	String[] data1 = { email, name, phone, startPosition, clusterPosition, currentPosition, temp1}; 
				        writer.writeNext(data1); 
				        
				        System.out.println("CSV Only Data");
			        }
			        
					
			        
			        
			  
			        // closing writer connection 
			        writer.close(); 
		     	    
		        }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        }
				
				
			}
			else
			{
				String lengthnew=temperatureDao.retrieveTempIdByEmail((String) session.getAttribute("username"));
				temperature.setMarkerno(lengthnew.substring(lengthnew.indexOf("[")+1,lengthnew.indexOf("]")));
				String startPosition=temperatureDao.getStartPositionbByEmail((String) session.getAttribute("username"));
				String startPosition1=startPosition.substring(startPosition.indexOf("[")+1,startPosition.indexOf("]"));
				
				try 
			    {
					/* Fillo fillo=new Fillo(); */
					String email=(String) session.getAttribute("username").toString();
					String name=session.getAttribute("getNameByEmail").toString();
					String phone=session.getAttribute("getPhoneByEmail").toString();
					String startPosition2=startPosition1;
					String clusterPosition="{lat: "+lat+","+"lng: "+lng+"}";
					String currentPosition="{lat: "+lat+","+"lng: "+lng+"}";
					String temp1=temperature.getTemp();
					String markerno=temperature.getMarkerno();
					
                    //File file = new File("D:\\eclipse-workspace-2019\\Coronoid\\src\\main\\webapp\\tracker\\tracker.csv"); 	    
					//File file = new File("https://jboss-webserver31-tomcat-coronoid-sb-new.apps.ca-central-1.starter.openshift-online.com/Coronoid/tracker.csv".toString());
					File file=ResourceUtils.getFile("tracker.csv");
					
					System.out.println("a->"+file.getAbsolutePath());
					System.out.println("c->"+file.getCanonicalPath());
						
			    	
					FileWriter outputfile = new FileWriter(file,true); 
			    	  
			        // create CSVWriter object filewriter object as parameter 
			        CSVWriter writer = new CSVWriter(outputfile); 
			  
			        FileReader filereader = new FileReader(file); 
			        CSVReader reader=new CSVReaderBuilder(filereader).build();
			        List<String[]> allData = reader.readAll();
			        
			        if(allData.isEmpty())
			        {
			        	String[] header= {"EMAIL", "NAME", "PHONE", "STARTPOSITION", "CLUSTERPOSITION", "CURRENTPOSITION", "TEMPERATURE"};
			        	String[] data1 = { email, name, phone, startPosition2, clusterPosition, currentPosition, temp1};
			        	
			        	List<String[]> list = new ArrayList<String[]>();		        	
			        	list.add(header);
			        	list.add(data1);
			        	
			        	writer.writeAll(list);
				        
				        System.out.println("CSV Header+Data");
			        }
			        else
			        {
			        	String[] data1 = { email, name, phone, startPosition2, clusterPosition, currentPosition, temp1}; 
				        writer.writeNext(data1); 
				        
				        System.out.println("CSV Only Data");
			        }
					
			        
			        
			  
			        // closing writer connection 
			        writer.close(); 
					
		            
		     	    
		        }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        }
				
				temperatureDao.updateTempById(temperature.getLoginId(), temperature.getTemp(), temperature.getLat(), temperature.getLng(), temperature.getDatetime(),temperature.getAddress(),temperature.getFever(),temperature.getCough(),temperature.getTiredness(),temperature.getBreathingProb(),temperature.getSymptoms(),temperature.getFutureDate(),temperature.getMarkerno(),temperature.getCluster(),temperature.getCurrentPosition(),startPosition1,temperature.getTracker(),temperature.getTravelWithin10Days());
				temperature.setTracker(temperature.getTracker());
				session.setAttribute("datetime", temperature.getDatetime());
				session.setAttribute("futureDate", temperature.getFutureDate());
				System.out.println("LAT"+temperature.getLat());
				System.out.println("LNG"+temperature.getLng());
			}
			
			return "addBodyTemperature";
		}
		else
		{
			return "pleaselogin";
		}
		
	}
	
	@RequestMapping(value="/addBodyTemperatureAndDetails",method= {RequestMethod.GET,RequestMethod.POST})
    public String addBodyTemperature1(Model m,HttpSession session,HttpServletRequest request)
    {
		if(session.getAttribute("username") != null)
		{
			long countRow=temperatureDao.countRow();
			m.addAttribute("countRow",countRow);
			
			request.setAttribute("countrow",countRow);
		    System.out.println("countRow"+countRow);
		    
			/*
			 * Temperature temperature=new Temperature();
			 * temperature.setCurrentPosition(currentPosition);
			 * temperatureDao.updateCurrentPositionByEmail(currentPosition,(String)
			 * session.getAttribute("username"));
			 */
		    
		    String datetime=temperatureDao.retrieveDateTimeByEmail((String) session.getAttribute("username"));
		    if(datetime.isEmpty() || datetime.equals("[]"))
		    {
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(now);
				
		    	m.addAttribute("datetime", dtf.format(now));
			    session.setAttribute("datetime", dtf.format(now));
		    }
		    else
		    {
		    	m.addAttribute("datetime", datetime);
			    session.setAttribute("datetime", datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")));
		    }
		    
		    
		    String futureDate=temperatureDao.retrievefutureDateByEmail((String) session.getAttribute("username"));
		    if(futureDate.isEmpty() || futureDate.equals("[]"))
		    {
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(now);
				
				 m.addAttribute("futureDate", dtf.format(now));
				 session.setAttribute("futureDate", dtf.format(now));
		    }
		    else
		    {
		    	 m.addAttribute("futureDate", futureDate.substring(futureDate.indexOf("[")+1, futureDate.indexOf("]")));
				 session.setAttribute("futureDate",futureDate.substring(futureDate.indexOf("[")+1, futureDate.indexOf("]")));
		    }
		    
	        
	        return "addBodyTemperature";
		}
		else
		{
			return "pleaselogin";
		}
		
		
    }
	
	@RequestMapping(value="/affectedArea",method= {RequestMethod.GET,RequestMethod.POST})
    public String affectedArea(Model m,HttpSession session,HttpServletRequest request)
    {
		if(session.getAttribute("username") != null)
		{
			List<Temperature>listLat=temperatureDao.retrieveLat();
			m.addAttribute("listLat",listLat);
	        
			request.setAttribute("listLat", listLat);
			System.out.println("Lat"+request.getAttribute("listLat"));
			
			List<Temperature>listLng=temperatureDao.retrieveLng();
			m.addAttribute("listLng",listLng);
	        
			request.setAttribute("listLng", listLng);
			System.out.println("Lng"+request.getAttribute("listLng"));
			
			List<Temperature>listMarkerno=temperatureDao.retrieveMarkerno();
			m.addAttribute("listMarkerno",listMarkerno);
	        
			request.setAttribute("listMarkerno", listMarkerno);
			System.out.println("Markerno"+request.getAttribute("listMarkerno"));
			
			List<Temperature>listAddress=temperatureDao.retrieveAddress();
			m.addAttribute("listAddress",listAddress);
	        
			request.setAttribute("listAddress", listAddress);
			System.out.println("Address"+request.getAttribute("listAddress"));
			session.setAttribute("addresslist", listAddress);
			
			Temperature temperature=new Temperature();
			m.addAttribute(temperature);
			
			List<Temperature>listTemperature=temperatureDao.retrieveTemperature();
			m.addAttribute("temperatureList",listTemperature);
			
			List<Temperature>clusterList=temperatureDao.retrieveCluster();
			m.addAttribute("clusterList",clusterList);
			session.setAttribute("clusterList", clusterList);
			
			String cluster=temperatureDao.getClusterByEmail((String) session.getAttribute("username"));
			session.setAttribute("cluster", cluster);
			
			String currentPositionList=temperatureDao.getCurrentPosition().toString();
			String currentPositionList1=currentPositionList.substring(currentPositionList.indexOf("[")+1,currentPositionList.indexOf("]"));
			session.setAttribute("currentPositionList1", currentPositionList1);
            System.out.println("currentPositionList1"+currentPositionList1);		
            
            String currentPositionByEmail=temperatureDao.getCurrentPositionbByEmail((String) session.getAttribute("username"));
            String currentPositionByEmail1=currentPositionByEmail.substring(currentPositionByEmail.indexOf("[")+1,currentPositionByEmail.indexOf("]"));
            session.setAttribute("currentPositionByEmail1", currentPositionByEmail1);
            System.out.println("currentPositionByEmail1"+currentPositionByEmail1);		
			
	        return "affectedArea";
		}
		else
		{
			return "pleaselogin";
		}
		
		
		
    }
	
	  @RequestMapping(value = "/pleaselogin", method = RequestMethod.GET)
	  public String loginerror(HttpSession session) 
	  {
	        session.invalidate();
	        return "pleaselogin";
	  }
	  
	  @RequestMapping(value = "/covid-19_info_resource", method = RequestMethod.GET)
	  public String covidinfo(Model m,HttpSession session,HttpServletRequest request) 
	  {
		  if(session.getAttribute("username") != null)
		  {
		  
		    String datetime=temperatureDao.retrieveDateTimeByEmail((String) session.getAttribute("username"));
		    if(datetime.isEmpty() || datetime.equals("[]"))
		    {
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(now);
				
		    	m.addAttribute("datetime", dtf.format(now));
			    session.setAttribute("datetime", dtf.format(now));
		    }
		    else
		    {
		    	m.addAttribute("datetime", datetime);
			    session.setAttribute("datetime", datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")));
		    }
		    
		    
		    String futureDate=temperatureDao.retrievefutureDateByEmail((String) session.getAttribute("username"));
		    if(futureDate.isEmpty() || futureDate.equals("[]"))
		    {
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(now);
				
				 m.addAttribute("futureDate", dtf.format(now));
				 session.setAttribute("futureDate", dtf.format(now));
		    }
		    else
		    {
		    	 m.addAttribute("futureDate", futureDate.substring(futureDate.indexOf("[")+1, futureDate.indexOf("]")));
				 session.setAttribute("futureDate",futureDate.substring(futureDate.indexOf("[")+1, futureDate.indexOf("]")));
		    }
		    
	     
	          return "covid-19_info_resource";
		  }
		  else
		  {
			  return "pleaselogin";
		  }
	  }

	  
	  
}

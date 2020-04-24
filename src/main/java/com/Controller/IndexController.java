package com.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Inheritance;
import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.EmailValidator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Dao.TemperatureDao;
import com.Dao.UserDao;
import com.Model.Temperature;
import com.Model.User;
import com.opencsv.CSVWriter;
import com.opencsv.processor.RowProcessor;







@SuppressWarnings({ "unused", "deprecation" })
@Async
@Controller
public class IndexController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	TemperatureDao temperatureDao;
	
	
	  
	
	
	  @RequestMapping(value="/login_success", method= {RequestMethod.GET,RequestMethod.POST}) 	  
	  public String success(Model m,HttpSession session,HttpServletRequest request) throws MalformedURLException { 
		  
      String page_url=null; 
      String role_name=null;
	  
	  boolean loggedIn=false; 
	  Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
	  String logged_UserName=authentication.getName();
	  
	  session.setAttribute("username",logged_UserName);
	  Collection<GrantedAuthority>
	  authorities=(Collection<GrantedAuthority>)authentication.getAuthorities();
	  
	  for(GrantedAuthority role:authorities) {
	  if(role.getAuthority().equals("ROLE_ADMIN")) 
	  { 
		  loggedIn=true;
	  
	      role_name="ROLE_ADMIN";
	      
	        String countHighFever=temperatureDao.countPersonWithHighFever();
		    
		    session.setAttribute("countHighFever", countHighFever);
		    
	        String countSymptomsPositive=temperatureDao.countPersonWithSymptomsPositive();
		    
		    session.setAttribute("countSymptomsPositive", countSymptomsPositive);
		    
	        String countAddedTemp=temperatureDao.countPersonAddedBodyTemperature();
		    
		    session.setAttribute("countAddedTemp", countAddedTemp);

	      
	      
	  
	      page_url="adminDashboard";
	      
	      
	      
	  
	  
	  
	  }
	  
	  
	  else if(role.getAuthority().equals("ROLE_USER"))
	  
	  {
	  
	  loggedIn=true;
	  
	  role_name="ROLE_USER";
	  
	  
		
	    System.out.println("countRow"+temperatureDao.countRow());
	    
	    User user=new User();
	    m.addAttribute(user);
	    
	    String countHighFever=temperatureDao.countPersonWithHighFever();
	    
	    session.setAttribute("countHighFever", countHighFever);
	    
        String countSymptomsPositive=temperatureDao.countPersonWithSymptomsPositive();
	   
	    session.setAttribute("countSymptomsPositive", countSymptomsPositive);
	    //System.out.println("countSymptomsPositive"+countSymptomsPositive);
	    
        String countAddedTemp=temperatureDao.countPersonAddedBodyTemperature();
	    
	    session.setAttribute("countAddedTemp", countAddedTemp);
	    
	    List<User> getPhoneByEmail=userDao.getPhoneByEmail(logged_UserName);
	    m.addAttribute("getPhoneByEmail", getPhoneByEmail);
	    session.setAttribute("getPhoneByEmail", getPhoneByEmail);
	    
	    List<User> getNameByEmail=userDao.getNameByEmail(logged_UserName);
	    m.addAttribute("getNameByEmail", getNameByEmail);
	    session.setAttribute("getNameByEmail", getNameByEmail);
	    
	    String userId=temperatureDao.getIdByEmail(logged_UserName);
	    session.setAttribute("userId", userId);
	    
	    String datetime=temperatureDao.retrieveDateTimeByEmail(logged_UserName);
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
	    	m.addAttribute("datetime", datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")));
		    session.setAttribute("datetime", datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")));
	    }
	    
	    
	    String futureDate=temperatureDao.retrievefutureDateByEmail(logged_UserName);
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
	   
	    
	    
	    page_url="dashboard";

	  
	  } 
	  else 
	  {
	  
	     page_url="loginsorry"; 
	  }
	  
	  }
	  
	  session.setAttribute("loggedIn", loggedIn); 
	  session.setAttribute("role",role_name);
	  
	  
	  
	  return page_url;
	  
	  }
	 
	  @RequestMapping(value = "/loginsorry", method = RequestMethod.GET)
	  public String loginerror() 
	  {
	        
	        return "loginsorry";
	  }
	  
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Model m)
	{
		
		
		
		return "index";
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(Model m)
	{
		
		
		
		return "register";
		
	}
	
	@RequestMapping(value="/generateOTP",method=RequestMethod.POST)
	public String register1(Model m,HttpSession session,@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("enabled") Boolean enabled,
			@RequestParam("role") String role,
			@RequestParam("name") String name,
			@RequestParam("phone") String phone) throws AddressException, MessagingException
	{
		User user=new User();
		user.setUsername(username);
		session.setAttribute("username", username);
		int randomPin   =(int) (Math.random()*9000)+1000; 
        String otp  = String.valueOf(randomPin);
        System.out.println(otp);
		session.setAttribute("otp", otp);
		user.setPassword(otp);
		user.setEnabled(enabled);
		user.setRole(role);
		user.setName(name);
		user.setPhone(phone);
		
		
			boolean isValid = false;
			String recipient = username; 
			  
		      // email ID of  Sender. 
		      String sender = "application.coronoid@gmail.com"; 
		  
		      // using host as localhost 
		      //String host = "jboss-webserver31-tomcat-coronoid-sb-new.apps.ca-central-1.starter.openshift-online.com"; 
		      String user1 = "application.coronoid@gmail.com";
		      String pass1 = "Sagnik@7980";
		      // Getting system properties 
		      Properties properties = System.getProperties(); 
		  
		      // Setting up mail server 
		      properties.setProperty("mail.transport.protocol", "smtp");
		      properties.setProperty("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		      properties.put("mail.smtp.auth", "true");
		      //properties.put("mail.smtp.starttls.enable", "true");
		      properties.put("mail.smtp.port", "465");
		      
		      //properties.put("mail.smtp.port", "8081");
		  
		      // creating session object to get properties 
		      Session session1 = Session.getInstance(properties,
		                new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(user1, pass1);
		                    }
		                });
		      
		     
		         // MimeMessage object. 
		         MimeMessage message = new MimeMessage(session1); 
		  
		         // Set From Field: adding senders email to from field. 
		         message.setFrom(new InternetAddress(sender)); 
		  
		         InternetAddress internetAddress=new InternetAddress(recipient);
		         // Set To Field: adding recipient's email to from field. 
		         message.addRecipient(Message.RecipientType.TO, internetAddress); 
		  
		         // Set Subject: subject of the email 
		         message.setSubject("Generated OTP"); 
		        
		         // set body of the email. 
		         message.setContent("Your OTP is "+otp+" and Email is "+username+" <br> You can now login with this OTP.","text/html; charset=utf-8"); 
		  
		         
		         
		     	 if(userDao.countRow(username)==0)
		         {
		        	
		        	 internetAddress.validate();
		        	// Send email. 
			         Transport.send(message);
			         
			         System.out.println("Personal"+internetAddress.getPersonal());
			         System.out.println("Email sent successfully.");
			         userDao.addUser(user);
			        	
		        	  List<User>listUser=userDao.retrieveUser(Integer.parseInt(otp));
		 			  m.addAttribute("userList",listUser); 
		 			  session.setAttribute("password",listUser.get(0).getPassword()); 
		 			  session.setAttribute("name",listUser.get(0).getUsername()); 
		 			  session.setAttribute("id",listUser.get(0).getId());
		 			  
		 			return "registernew";
		         }
		         else if(userDao.countRow(username)!=0)
		         {
		        	 System.out.println("Email already registered");
		        	 return "registersorry";
		         }
		         else
		         {
		        	 return "emailnotvalid";
		         }
		          
	
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.GET)
    public String updateUser(Model m,HttpSession session)
    {
        
		//id=(int) session.getAttribute("id");
		//User user=userDao.getUser(id);
        //m.addAttribute(user);                                       //To fetch the data and display it.
        
        //List<User> listUser=userDao.retrieveUser1(id);
        //m.addAttribute("userList",listUser);
        
        return "forgotpassword";
		
		
    }
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
    public String updateUserSendEmail(@RequestParam("emailid")String emailid,HttpSession session,Model m) throws MessagingException
    {
		    
		/*
		 * int randomPin =(int) (Math.random()*9000)+1000; String otp =
		 * String.valueOf(randomPin); System.out.println(otp);
		 * session.setAttribute("otp", otp); user.setPassword(otp);
		 * userDao.updateUser(user);
		 * 
		 * List<User> listUser=userDao.retrieveUser1(id);
		 * m.addAttribute("userList",listUser);
		 * 
		 * session.setAttribute("password", listUser.get(0).getPassword());
		 * session.setAttribute("name", listUser.get(0).getUsername());
		 * session.setAttribute("id", listUser.get(0).getId());
		 */
		
		  
		    int randomPin   =(int) (Math.random()*9000)+1000; 
	        String otp  = String.valueOf(randomPin);
	        System.out.println(otp);

		
		  
		  
		  String recipient = emailid; 
		  
	      // email ID of  Sender. 
	      String sender = "application.coronoid@gmail.com"; 
	  
	      // using host as localhost 
	      //String host = "jboss-webserver31-tomcat-coronoid-sb-new.apps.ca-central-1.starter.openshift-online.com"; 
	      String user = "application.coronoid@gmail.com";
	      String pass = "Sagnik@7980";
	      // Getting system properties 
	      Properties properties = System.getProperties(); 
	  
	      // Setting up mail server 
	      properties.setProperty("mail.transport.protocol", "smtp");
	      properties.setProperty("mail.smtp.socketFactory.port", "465");
	      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.auth", "true");
	      //properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.port", "465");
	      
	      //properties.put("mail.smtp.port", "8081");
	  
	      // creating session object to get properties 
	      Session session1 = Session.getInstance(properties,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(user, pass);
	                    }
	                });
	      
	     
	         // MimeMessage object. 
	         MimeMessage message = new MimeMessage(session1); 
	  
	         // Set From Field: adding senders email to from field. 
	         message.setFrom(new InternetAddress(sender)); 
	  
	         // Set To Field: adding recipient's email to from field. 
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
	  
	         // Set Subject: subject of the email 
	         message.setSubject("Generated OTP"); 
	        
	         // set body of the email. 
	         message.setContent("Your OTP is "+otp+" and Email is "+emailid+" <br> You can now login with this OTP.","text/html; charset=utf-8"); 
	  
	         // Send email. 
	         Transport.send(message); 
	         System.out.println("Email sent successfully."); 
	         
	         userDao.getUserByEmail(emailid);
	         userDao.updateUserByEmail(emailid, otp);
	         
		
		return "forgotpasswordnew";
		
    }
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "index";
		
	}
	
	@RequestMapping(value="/emailnotvalid",method=RequestMethod.GET)
    public String emailnotvalid(Model m,HttpSession session)
    {
        
		//id=(int) session.getAttribute("id");
		//User user=userDao.getUser(id);
        //m.addAttribute(user);                                       //To fetch the data and display it.
        
        //List<User> listUser=userDao.retrieveUser1(id);
        //m.addAttribute("userList",listUser);
        
        return "emailnotvalid";
		
		
    }
	
	@RequestMapping(value="/registernew",method=RequestMethod.GET)
    public String registernew(Model m,HttpSession session)
    {
        
		//id=(int) session.getAttribute("id");
		//User user=userDao.getUser(id);
        //m.addAttribute(user);                                       //To fetch the data and display it.
        
        //List<User> listUser=userDao.retrieveUser1(id);
        //m.addAttribute("userList",listUser);
        
        return "registernew";
		
		
    }
	@RequestMapping(value="/registersorry",method=RequestMethod.GET)
    public String registersorry(Model m,HttpSession session)
    {
        
		//id=(int) session.getAttribute("id");
		//User user=userDao.getUser(id);
        //m.addAttribute(user);                                       //To fetch the data and display it.
        
        //List<User> listUser=userDao.retrieveUser1(id);
        //m.addAttribute("userList",listUser);
        
        return "registersorry";
		
		
    }
	
	@ExceptionHandler({NullPointerException.class,IllegalStateException.class,TransactionRequiredException.class})
	@RequestMapping(value="/dashboardnew",method= RequestMethod.GET)
    public String dashboard1(Model m,HttpSession session)
    {
        
		if(session.getAttribute("username") != null)
		{  
		
		    System.out.println("countRow"+temperatureDao.countRow());
		    
		    User user=new User();
		    m.addAttribute(user);
		    
		    List<User> getPhoneByEmail=userDao.getPhoneByEmail((String) session.getAttribute("username"));
		    m.addAttribute("getPhoneByEmail", getPhoneByEmail);
		    session.setAttribute("getPhoneByEmail", getPhoneByEmail);
		    
		    List<User> getNameByEmail=userDao.getNameByEmail((String) session.getAttribute("username"));
		    m.addAttribute("getNameByEmail", getNameByEmail);
		    session.setAttribute("getNameByEmail", getNameByEmail);
		    
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
		    	m.addAttribute("datetime", datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")));
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
 
		    ThreadPoolTaskScheduler scheduledExecutorService = new ThreadPoolTaskScheduler();
	        // always set the poolsize
	        scheduledExecutorService.setPoolSize(10);
	        // for logging add a threadNamePrefix
	        scheduledExecutorService.setThreadNamePrefix("myTaskScheduler-");
	        // do not wait for completion of the task
	        scheduledExecutorService.setWaitForTasksToCompleteOnShutdown(false);
	        scheduledExecutorService.initialize(); 
	        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
	    		@Async
		        @Override
		        public void run(){
		        	try
		        	{
		        		
		        		
		        		temperatureDao.updateCurrentPositionByEmail(session.getAttribute("username").toString(), session.getAttribute("latlng").toString());
			            System.out.println("latlngsession->"+session.getAttribute("latlng").toString());
		        		
		        	}
		        	catch(Exception e)
		        	{
		        		
		        		scheduledExecutorService.shutdown();
		        		//System.out.println("Destroy");
		        	}
		            
		        }
		        
		    }, 7200000);
		    
	        
	       
	    
		    return "dashboardnew";
		}
		else
		{
			return "pleaselogin";
		}
    }
	
	@RequestMapping(value="/dashboardnew",method=RequestMethod.POST)
    public String dashboard(Model m,HttpSession session,HttpServletRequest request,@RequestParam("currentPosition") String currentPosition)
    {
		if(session.getAttribute("username") != null)
		{
        
		Temperature temperature=new Temperature();
	    temperature.setCurrentPosition(currentPosition);
	    temperatureDao.updateCurrentPositionByEmail(currentPosition,(String) session.getAttribute("username"));
	    
	    String latlng=request.getParameter("currentPosition");
	    session.setAttribute("latlng", latlng);
	    System.out.println("latlng"+latlng);
	    System.out.println("cp"+currentPosition);
	    
		if(temperatureDao.getNameByEmail(session.getAttribute("username").toString()) != null) 
		{
			try 
	        {
	        	
					

	            String email=session.getAttribute("username").toString();
		    	String name=session.getAttribute("getNameByEmail").toString();
		    	String phone=session.getAttribute("getPhoneByEmail").toString();
		    	String startPosition=temperatureDao.getStartPositionbByEmail(session.getAttribute("username").toString());
		    	String clusterPosition=session.getAttribute("latlng").toString();
		    	String currentPosition1=session.getAttribute("latlng").toString();
		    	String temp1=temperatureDao.getTempByEmail(session.getAttribute("username").toString());
		    	String markerno=temperatureDao.getIdByEmail((String) session.getAttribute("username"));
		    	
					
		    	File file = new File("https://jboss-webserver31-tomcat-coronoid-sb-new.apps.ca-central-1.starter.openshift-online.com/Coronoid/tracker.csv"); 	    
		    	
		    	FileWriter outputfile = new FileWriter(file,true); 
		    	  
		        // create CSVWriter object filewriter object as parameter 
		        CSVWriter writer = new CSVWriter(outputfile); 
		  
				
		        String[] data1 = { email, name, phone, startPosition.substring(startPosition.indexOf("[")+1,startPosition.indexOf("]")), clusterPosition, currentPosition1, temp1.substring(temp1.indexOf("[")+1,temp1.indexOf("]"))}; 
		        writer.writeNext(data1); 
		        
		  
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
			System.out.println("Welcome");
		}
        
	    
        return "dashboard";
		}
		else
		{
			return "pleaselogin";
		}
		
		
    }



	

	

	
}

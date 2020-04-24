package com.HibernateConfig;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.Model.Temperature;
import com.Model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com")
public class HibernateConfig {
	
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://${env.OPENSHIFT_MYSQL_DB_HOST}:${env.OPENSHIFT_MYSQL_DB_PORT}/coronoid");
		
		  ds.setUsername("${env.OPENSHIFT_MYSQL_DB_USERNAME}"); 
		  ds.setPassword("${env.OPENSHIFT_MYSQL_DB_PASSWORD}");
		 
		/*
		 * ds.setUsername("root"); ds.setPassword("");
		 */
		return ds;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties p=new Properties();
		p.setProperty("hibernate.hbm2ddl.auto","update");
		p.setProperty("batch_size", "20");
		p.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		p.put("hibernate.show_sql", "true");
		p.put("mail.smtp.host", "localhost");
		
		
		LocalSessionFactoryBuilder sf=new LocalSessionFactoryBuilder(getH2DataSource());
		sf.addProperties(p);
		sf.addAnnotatedClasses(User.class,Temperature.class);
		SessionFactory sessionFactory=sf.buildSessionFactory();
		System.out.println("Session Factory Object Created");
		return sessionFactory;
		
	}
	
	
	@Bean
	HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hm=new HibernateTransactionManager(sessionFactory);
		return hm;
		
	}
	
	

}

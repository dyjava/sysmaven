package com.sys.applet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.domain.user.User;
import com.sys.service.account.AccountService;
import com.sys.service.account.DiaryService;
import com.sys.service.account.IncomeService;
import com.sys.service.account.KindService;
import com.sys.service.user.UserService;

/** 
 * by dyong 2010-9-1
 */
public class ConstService {

	public static String ROOT = System.getProperty("user.dir") ;
	public static User user = new User() ;
	
	public static UserService userService ;
	public static IncomeService incomeService ;
	public static AccountService accService ;
	public static DiaryService diaryService ;
	public static KindService kindService ;
	public static void initService(){

//		InputStreamResource resource = new InputStreamResource(
//				new FileInputStream("./spring-service.xml"));
//		BeanFactory factory = new XmlBeanFactory(resource);
//		userService = (UserService)(factory.getBean("dataSource"));
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"spring-config.xml"});
		
		userService = (UserService)context.getBean("userService");
		accService = (AccountService)context.getBean("accountService");
		incomeService = (IncomeService)context.getBean("incomeService");
		diaryService = (DiaryService)context.getBean("diaryService");
		kindService = (KindService)context.getBean("kindService");
//		System.getProperty("user.dir") ;
		System.out.println("========="+System.getProperty("user.dir")) ;
	}
	
	public static void setUser(User u){
		user = u ;
	}
	public static void main(String[] args){
		initService() ;
	}
}

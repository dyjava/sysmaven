package com.sys.applet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.spring.account.service.AccountService;
import com.sys.spring.account.service.DiaryService;
import com.sys.spring.account.service.IncomeService;
import com.sys.spring.account.service.KindService;
import com.sys.spring.user.domain.User;
import com.sys.spring.user.service.UserService;

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
				new String[]{"spring-dao.xml","spring-service.xml"});
		
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

package com.sys.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * by dyong 2011-3-24
 */
public class Logs {
	private static final Logger log = LoggerFactory.getLogger(LogAop.class);
//	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	public static void logInfo(String user,String message,String sql){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:ss,SSS");
		log.info("|USER LOG|"+user+"|"+dateFormatter.format(new Date())+"|"+message+"|"+sql) ;
	}
	
	public static void info(Object msg){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("|SYS|info|"+dateFormatter.format(new Date())+"|"+msg.toString()) ;
	}
	public static void debug(Object msg){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("|SYS|debug|"+dateFormatter.format(new Date())+"|"+msg.toString()) ;
	}
	public static void error(Object msg){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("|SYS|error|"+dateFormatter.format(new Date())+"|"+msg.toString()) ;
	}
}

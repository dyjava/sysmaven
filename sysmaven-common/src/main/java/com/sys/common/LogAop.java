package com.sys.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 切面日志
 * @author diyong
 *
 */
public class LogAop {
	private static final Logger log = LoggerFactory.getLogger(LogAop.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	private static final Gson gson = new GsonBuilder().serializeNulls()
    		.enableComplexMapKeySerialization().create() ;

	/**
	 * 切面日志记录方法
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object logRoundMethod(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();

//		String className = pjp.getTarget().getClass().getName() ;
		String simpleClassName = pjp.getTarget().getClass().getSimpleName() ;
		String methodName = pjp.getSignature().getName() ;
		
		StringBuffer buf = new StringBuffer("AOP_LOG:");
		buf
		.append("|").append(dateFormat.format(new Date()))
		.append("|").append(simpleClassName)
		.append("|").append(methodName)
		.append("|").append(this.getArgsTypeAndValue(pjp.getArgs())) ;
		
		log.info(buf+"|start|" );
		try {
			Object result = pjp.proceed();
			
			long end = System.currentTimeMillis();
			log.info(buf +"|end|"+ (end-start)) ;
			
			return result;
		} catch (Exception e) {
			long end = System.currentTimeMillis();
			log.info(buf +"|ex|"+ (end-start) +"|"+ e.getMessage());
			throw e;
		}
	}
	/**
	 * 处理参数
	 * 拼接参数类型和值，获取请求IP地址
	 * @param args
	 * @return
	 */
	private StringBuffer getArgsTypeAndValue(Object[] args){
		String host = "172.0.0.1" ;
		StringBuffer buf = new StringBuffer() ;
		if(args==null || args.length==0){
			return buf.append("|") ;
		}
		
		for (Object obj:args) {
			try {
				if(!(obj instanceof HttpServletRequest || obj instanceof HttpServletResponse)){
					buf
					.append("[").append(obj.getClass().getSimpleName()).append("]")
					.append(gson.toJson(obj)).append(";") ;
				}
				if(obj instanceof HttpServletRequest){
					host = ((HttpServletRequest)obj).getRemoteHost() ;
				}
			} catch (Exception e) {
				log.error(e.getMessage() , e);
			}
		}
		return buf.append("|").append(host) ;
	}
	
}

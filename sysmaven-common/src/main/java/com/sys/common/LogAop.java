package com.sys.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.alibaba.fastjson.JSONObject;

public class LogAop {
	private static final Logger log = Logger.getLogger(LogAop.class);

	public Object logRoundMethod(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		
		StringBuffer buf = new StringBuffer();
		buf.append("|").append(className)
		.append("|").append(methodName)
		.append("|");
		String[] argsClassName = getArgsType(pjp.getArgs());
		buf.append(arrayToString(argsClassName, ","));
		
		//拼接请求参数数据
		String argsData = getArgsValue(pjp.getArgs());
		buf.append("|").append(argsData) ;
		
		log.info("[method]开始准备执行{}，" + buf );
		
		try {
			
			Object result = pjp.proceed();
			
			long end = System.currentTimeMillis();
			log.info("[method]{}执行完成，耗时{}" + buf +"|"+ (end-start)) ;
			
			return result;
		} catch (Exception e) {
			log.error("[method]"+e.getMessage() , e);
			
			long end = System.currentTimeMillis();
			log.info("[method]{}执行完成，耗时{}，发生异常{}|" +buf +"|"+ (end-start) +"|"+ e.getMessage());
			
			throw e;
		}
	}

	/**
	* <p>Title: getArgsType</p> 
	* <p>Description: 获取参数类型 </p>
	* @param args 参数
	* @return 参数性类型名称
	 */
	private String[] getArgsType(Object[] args){
		if(!arrayHasElement(args)){
			return null;
		}
		String[] result = new String[args.length];
		for (int i = 0; i < result.length; i++) {
			if(null != args[i]){
				result[i] = args[i].getClass().getName();
			}
		}
		return result;
	}
	
	private String getArgsValue(Object[] args){
		StringBuffer argsData = new StringBuffer();
		int argsLeng = args.length;
		for (int i = 0; i < argsLeng; i++) {
			try {
				Object obj = args[i];
				if(! (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse)){
					argsData.append(JSONObject.toJSONString(args[i]));
					if(i != argsLeng - 1){
						argsData.append(",");
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage() , e);
			}
		}
		return argsData.toString();
	}

	/**
	 * 将集合使用指定的分隔符合并为字符串
	 * objs is null , return null
	 * objs.length == 0 , return ""
	 * objs{"aaa","bbb","ccc"} , return "aaa,bbb,ccc"
	 * @param objs 带合并的数组
	 * @param splitChar 分隔符，默认为英文逗号
	 * @return 拼接后的字符串结果
	 */
	private String arrayToString(Object[] objs , String splitChar){
		if(null == objs){
			return null;
		}
		if(null == splitChar){
			splitChar = ",";
		}
		int max = objs.length;
		if(max < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(Object obj : objs){
			if(null != obj){
				sb.append(String.valueOf(obj));
				if(i<max-1){
					sb.append(splitChar);
				}
			} else if (i == max -1 && sb.length() > 0){
				sb.delete(sb.length() - splitChar.length(), sb.length());
			}
			i++;
		}
		return sb.toString();
	}
	
	/**
	* <p>Title: hasElement</p> 
	* <p>Description: 判断当前数组中是否包含元素 </p>
	* @param objs 待验证的数组
	* @return 包含true，否则false
	 */
	private boolean arrayHasElement(Object[] objs){
		if(null ==objs){
			return false;
		}
		return objs.length > 0 ? true : false ;
	}
	
}

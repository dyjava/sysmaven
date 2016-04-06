package com.sys.applet.util;

/** 
 * by dyong 2010-9-29
 */
public class DoubleUtil {

	public static String money(double m){
		String money = String.valueOf(m) ;
		if(money.indexOf(".")==-1){
			return money+".00" ;
		}
		money = money+"000" ;
		return money.substring(0,money.indexOf(".")+3) ;
	}
}

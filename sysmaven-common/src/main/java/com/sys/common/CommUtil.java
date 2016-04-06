package com.sys.common;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(emailRegex("dyong525@163.com")) ;
//		System.out.println(emailRegex("dyong525@umessage.com.cn")) ;
		System.out.println(CommUtil.md5("test")) ;
	}

	/**
	 * 获取随即序号
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取日期字符串
	 * 格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		return dateFormat.format(new Date()) ;
	}
	
	/**
	 * MD5加密
	 * @param string
	 * @return
	 */
	public static String md5(String string){
		
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
		
		try {
            byte[] btInput = string.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * 邮箱验证
	 * @param email
	 * @return
	 */
	public static boolean emailRegex(String email){
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*" ;
		return email.matches(regex) ;
	}
}

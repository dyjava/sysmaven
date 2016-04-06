package com.sys.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.common.JsonUtils;

public class BaseController {
	protected long start ;

	protected StringBuffer getLogs(HttpServletRequest request){
		StringBuffer buf = new StringBuffer()
			.append("|").append("uuid")
			.append("|").append(System.currentTimeMillis()-start)
			.append("|").append(request.getRemoteHost())
			.append("|").append(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[2].getMethodName())
			.append("|").append(request.getQueryString()).append("|") ;
		return buf ;
	}
	// 输出内容
	protected void printOut(Object obj, HttpServletResponse response) {
		String out = "";

		try {
			response.setCharacterEncoding("utf-8");

			out = JsonUtils.bean2json(obj);
			response.setContentType("application/json");

			byte[] ss = out.toString().getBytes("utf-8");
			response.setContentLength(ss.length);
			ServletOutputStream os = response.getOutputStream();
			os.write(ss);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected List<?> pagingList(List<?> list, HttpServletRequest request){
		int page=1, rows=10 ;
		String obj = request.getParameter("page") ;
		if(obj!=null && obj.trim().length()>0){
			page = Integer.parseInt(obj) ;
		}
		obj = request.getParameter("rows") ;
		if(obj!=null && obj.trim().length()>0){
			rows = Integer.parseInt(obj) ;
		}
		if(list.size()<rows){
			return list ;
		}
		int begin = (page-1)*rows ;
		if(begin<0){
			begin =0 ;
		}
		int end = begin+rows ;
		if(end>list.size()){
			end=list.size() ;
		}
		return list.subList(begin, end) ;
	}

}

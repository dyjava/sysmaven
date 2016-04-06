package com.sys.controler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.domain.account.Kind;
import com.sys.service.account.KindService;

@Controller
public class KindControler extends BaseController{
	@Resource(name = "kindService")
	private KindService kindService;
	
	@RequestMapping("kind/list")
	public void list(HttpServletRequest request, HttpServletResponse response,int parentid) {
		List<Kind> list = new ArrayList<Kind>() ;
		if(parentid!=0){
			list = kindService.findKindList(parentid) ;
		} else {
			list = kindService.findAllKindList() ;
		}
		this.printOut(list, response);
	}

	@RequestMapping("kind/add")
	public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Kind kind) {
		
		int res = kindService.insertKind(kind) ;
		this.printOut(res, response);
	}

	@RequestMapping("kind/info")
	public void info(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Kind kind) {
		
		Kind info = kindService.findKindById(kind.getId()) ;
		this.printOut(info, response);
	}
	
	@RequestMapping("kind/update")
	public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Kind kind) {
		
		int res = kindService.updateKind(kind) ;
		this.printOut(res, response);
	}
	
	
}

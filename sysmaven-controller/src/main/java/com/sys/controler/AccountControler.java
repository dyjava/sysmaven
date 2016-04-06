package com.sys.controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.domain.account.Account;
import com.sys.service.account.AccountService;


@Controller
@RequestMapping(value = "/account")
public class AccountControler extends BaseController{
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Account account, String begin, String end) {
		if(begin==null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-01") ;
			begin = dateFormat.format(new Date()) ;
		}
		if(end==null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			end = dateFormat.format(new Date()) ;
		}
		
		List<Account> list = accountService.findAccountList(begin, end, account) ;
		this.printOut(list, response);
	}

	@RequestMapping("/info")
	public void info(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Account account) {
		
		Account info = accountService.findAccountById(account.getId()) ;
		this.printOut(info, response);
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Account account) {
		int res = 0;
		if(account.getId()>0){
			res = accountService.updateAccount(account) ;
		} else {
			res = accountService.insertAccount(account) ;
		}
		this.printOut(res, response);
	}
}

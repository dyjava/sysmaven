package com.sys.spring.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.spring.account.domain.Account;
import com.sys.spring.account.service.AccountService;

@Controller
public class AccountControler extends BaseController{
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@RequestMapping("account/list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Account account, String begin, String end) {
		
		List<Account> list = accountService.findAccountList(begin, end, account) ;
		this.printOut(list, response);
	}

	@RequestMapping("account/add")
	public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Account account) {
		
		int res = accountService.insertAccount(account) ;
		this.printOut(res, response);
	}

	@RequestMapping("account/info")
	public void info(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Account account) {
		
		Account info = accountService.findAccountById(account.getId()) ;
		this.printOut(info, response);
	}
	
	@RequestMapping("account/update")
	public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Account account) {
		
		int res = accountService.updateAccount(account) ;
		this.printOut(res, response);
	}
	
	
}

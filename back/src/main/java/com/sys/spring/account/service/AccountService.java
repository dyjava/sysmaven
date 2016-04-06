package com.sys.spring.account.service;

import java.util.List;

import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.AccountTable;


/** 
 * by dyong 2010-6-16
 */
public interface AccountService {
	
	public int insertAccount(Account account) ;
	
	public int updateAccount(Account account) ;
	
	public List<Account> findAccountList(String begin,String end,Account account) ;
	
	public Account findAccountById(int id) ;
	
	public List<AccountTable> findAccountTableList(String begin,String end,Account account,String type) ;
	
}

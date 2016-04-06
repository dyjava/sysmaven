package com.sys.service.account;

import java.util.List;

import com.sys.domain.account.Account;
import com.sys.domain.account.AccountTable;

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

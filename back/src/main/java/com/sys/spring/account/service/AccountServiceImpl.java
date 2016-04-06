package com.sys.spring.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.sys.spring.account.dao.AccountDao;
import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.AccountTable;


/** 
 * by dyong 2010-6-16
 */
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao ;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		Account acc = accountDao.findAccountById(id);
		acc.setMoney(this.round(acc.getMoney(), 2, 1)) ;
		return acc ;
	}

	public List<Account> findAccountList(String begin, String end,Account account) {
		// TODO Auto-generated method stub
		return accountDao.findAccountList(begin, end, account);
	}

	public int insertAccount(Account account) {
		// TODO Auto-generated method stub
		account.setUid(UUID.randomUUID().toString()) ;
//		account.setUserId(user.getUid()) ;
//		account.setUsername(user.getUsername()) ;
		return accountDao.insertAccount(account);
	}

	public int updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}

	public List<AccountTable> findAccountTableList(String begin, String end,
			Account account,String type) {
		return accountDao.findAccountTableList(begin, end, account,type);
	}


    private double round(double value, int scale,
             int roundingMode) {  
        BigDecimal bd = new BigDecimal(value);  
        bd = bd.setScale(scale, roundingMode);  
        double d = bd.doubleValue();  
        bd = null;  
        return d;  
    }   
}

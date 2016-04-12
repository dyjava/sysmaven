package com.sys.service.account.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.account.AccountDao;
import com.sys.domain.account.Account;
import com.sys.domain.account.AccountTable;
import com.sys.service.account.AccountService;


/** 
 * by dyong 2010-6-16
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao ;
	
	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		Account acc = accountDao.selectById(id);
		acc.setMoney(this.round(acc.getMoney(), 2, 1)) ;
		return acc ;
	}
	
	@Override
	public List<Account> findAccountList(String begin, String end,Account account) {
		// TODO Auto-generated method stub
		return accountDao.findAccountList(begin, end, account);
	}

	@Override
	public int insertAccount(Account account) {
		// TODO Auto-generated method stub
		account.setUid(UUID.randomUUID().toString()) ;
//		account.setUserId(user.getUid()) ;
//		account.setUsername(user.getUsername()) ;
		return accountDao.insert(account);
	}

	@Override
	public int updateAccount(Account account) {
		return accountDao.updateById(account);
	}

	@Override
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

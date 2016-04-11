package com.sys.dao.account;

import java.util.List;

import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.account.Account;
import com.sys.domain.account.AccountTable;

/** 
 * by dyong 2010-6-16
 */
//@MyBatisRepository
public interface AccountDao {

	public int insertAccount(Account account) ;	//insert
	
	public int updateAccount(Account account) ;	//update
	
//	public void deleteAccount(Account account) ;	//delete
	
	public Account findAccountById(int id) ;	//find one object by id
	
	public List<Account> findAccountList(String begin,String end,Account account) ; 	//find object list by parames
	
	/**
	 * 按条件查询统计结果
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @param account 查询条件
	 * @param type 统计类型
	 * @return
	 */
	public List<AccountTable> findAccountTableList(String begin,String end,Account account,String type) ;	// find tables by count
}

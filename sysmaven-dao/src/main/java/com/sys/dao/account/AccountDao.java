package com.sys.dao.account;

import java.io.Serializable;
import java.util.List;

import com.sys.dao.BaseDao;
import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.account.Account;
import com.sys.domain.account.AccountTable;

/** 
 * by dyong 2010-6-16
 */
@MyBatisRepository
public interface AccountDao extends BaseDao<Account, Serializable>{

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

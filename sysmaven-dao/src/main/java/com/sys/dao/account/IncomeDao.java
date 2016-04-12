package com.sys.dao.account;

import java.io.Serializable;
import java.util.List;

import com.sys.dao.BaseDao;
import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.account.Income;

/** 
 * by dyong 2010-9-29
 */
//@MyBatisRepository
public interface IncomeDao extends BaseDao<Income, Serializable>{
	
	/**
	 * 按条件查询列表
	 * @param begin
	 * @param end
	 * @param income
	 * @return
	 */
	public List<Income> findIncomeList(String begin,String end,Income income) ;
	
}

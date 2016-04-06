package com.sys.dao.account;

import java.util.List;

import com.sys.domain.account.Income;

/** 
 * by dyong 2010-9-29
 */
public interface IncomeDao {

	public int insertIncome(Income income) ;
	
	public int updateIncome(Income income) ;
	
	public void deleteIncome(int id) ;

	public Income findIncomeById(int id) ;
	
	public List<Income> findIncomeList(String begin,String end,Income income) ;
	
}
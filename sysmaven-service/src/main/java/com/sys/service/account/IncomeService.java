package com.sys.service.account;

import java.util.List;

import com.sys.domain.account.Income;

/** 
 * by dyong 2010-9-29
 */
public interface IncomeService {

	public int insertIncome(Income income) ;
	
	public int updateIncome(Income income) ;
	
	public List<Income> findIncomeList(String begin,String end,Income income) ;
	
	public Income findIncomeById(int id) ;
	
}

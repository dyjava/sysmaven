package com.sys.spring.account.service;

import java.util.List;
import java.util.UUID;

import com.sys.spring.account.dao.IncomeDao;
import com.sys.spring.account.domain.Income;

/** 
 * by dyong 2010-9-29
 */
public class IncomeServiceImpl implements IncomeService {
	private IncomeDao incomeDao ;
	
	public IncomeDao getIncomeDao() {
		return incomeDao;
	}

	public void setIncomeDao(IncomeDao incomeDao) {
		this.incomeDao = incomeDao;
	}

	public Income findIncomeById(int id) {
		// TODO Auto-generated method stub
		return incomeDao.findIncomeById(id);
	}

	public List<Income> findIncomeList(String begin, String end, Income income) {
		// TODO Auto-generated method stub
		return incomeDao.findIncomeList(begin, end, income);
	}

	public int insertIncome(Income income) {
		// TODO Auto-generated method stub
		income.setUid(UUID.randomUUID().toString()) ;
		return incomeDao.insertIncome(income);
	}

	public int updateIncome(Income income) {
		return incomeDao.updateIncome(income);
	}

}

package com.sys.service.account.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sys.dao.account.IncomeDao;
import com.sys.domain.account.Income;
import com.sys.service.account.IncomeService;

/** 
 * by dyong 2010-9-29
 */
@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {
	private IncomeDao incomeDao ;
	
	public IncomeDao getIncomeDao() {
		return incomeDao;
	}

	public void setIncomeDao(IncomeDao incomeDao) {
		this.incomeDao = incomeDao;
	}

	@Override
	public Income findIncomeById(int id) {
		// TODO Auto-generated method stub
		return incomeDao.findIncomeById(id);
	}

	@Override
	public List<Income> findIncomeList(String begin, String end, Income income) {
		// TODO Auto-generated method stub
		return incomeDao.findIncomeList(begin, end, income);
	}

	@Override
	public int insertIncome(Income income) {
		// TODO Auto-generated method stub
		income.setUid(UUID.randomUUID().toString()) ;
		return incomeDao.insertIncome(income);
	}

	@Override
	public int updateIncome(Income income) {
		return incomeDao.updateIncome(income);
	}

}

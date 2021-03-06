package com.sys.dao.account.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.common.Logs;
import com.sys.dao.AbstractDBDao;
import com.sys.dao.account.IncomeDao;
import com.sys.domain.account.Income;

/** 
 * by dyong 2010-6-16
 */
@Repository("incomeDao")
public class IncomeDaoImpl extends AbstractDBDao implements IncomeDao {
	private String table = "income" ;
	
	@Override
	public int insert(Income in) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertIncome") ;
		
		String sql = "INSERT INTO income(uid,title,money,kindId,kindTitle,datetime,userId,userName) VALUES (?,?,?,?,?,?,?,?)" ;
//		log.info(sql+"	"+kind.getTitle()+"	"+kind.getNote()+"	"+kind.getParentId()) ;
		Object[] params = {in.getUid(),in.getTitle(),in.getMoney(),in.getKindid(),in.getKindtitle(),in.getDatetime(),in.getUserId(),in.getUsername()} ;
		
		int result = this.update(sql, params) ;

		buf.append("|").append(sql)
		.append("|").append(params.toString())
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	@Override
	public int updateById(Income income) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("updateIncome") ;
		
		HashMap<String,Object> map = new HashMap<String,Object>() ;
		map.put("title", income.getTitle()) ;
		map.put("kid", income.getKid()) ;
		map.put("kindId", income.getKindid()) ;
		map.put("kindTitle", income.getKindtitle()) ;
		map.put("money", income.getMoney()) ;
		map.put("datetime", income.getDatetime()) ;
		
		int result = super.updateById(table, income.getId(), map) ;
		
		buf.append("|").append("")
		.append("|").append("")
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	@Override
	public Income selectById(Object id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertIncome") ;
		
		Income result = super.findByID(table, id, Income.class) ;

		buf.append("|").append("")
		.append("|").append(id)
		.append("|").append(result.getId())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	@Override
	public List<Income> findIncomeList(String begin, String end, Income income) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findIncomeList") ;
		
		StringBuffer sql = new StringBuffer("select * from income where datetime>=? and datetime<=? ") ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(begin) ;
		params.add(end) ;
		
		if(income.getKid()>0){
			sql.append(" and kid=?") ;
			params.add(income.getKid()) ;
		}
		if(income.getKindid()!=null && income.getKindid().length()>0){
			sql.append(" and kindid=?") ;
			params.add(income.getKindid()) ;
		}
		if(income.getUserId()!=null){
			sql.append(" and userid=?") ;
			params.add(income.getUserId()) ;
		}
		if(income.getTitle()!=null){
			sql.append(" and title like ?") ;
			params.add("%"+income.getTitle()+"%") ;
		}
		if(income.getMoney()>0){
			sql.append(" and money>?") ;
			params.add(income.getMoney()) ;
		}
		sql.append(" order by datetime desc") ;
		
		buf.append("|").append(sql) ;
		buf.append("|").append(this.list2String(params)) ;
		List<Income> list = this.selectList(sql.toString(), params.toArray(), Income.class) ;
		
		buf.append("|").append(list.size()) ;
		buf.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return list ;
	
	}


	@Override
	public List<Income> select(Income record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Object id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

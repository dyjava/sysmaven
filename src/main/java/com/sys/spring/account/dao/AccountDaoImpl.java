package com.sys.spring.account.dao;

import java.util.ArrayList;
import java.util.List;



import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.AccountTable;
import com.sys.spring.dao.AbstractDBDao;
import com.sys.util.Logs;

/** 
 * by dyong 2010-6-16
 */
public class AccountDaoImpl extends AbstractDBDao implements AccountDao {
	
	private String table = "account" ;
	public List<Account> findAccountList(String begin, String end,Account account) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findAccountList") ;
		
		StringBuffer sql = new StringBuffer("select * from account where date(datetime)>=? and date(datetime)<=? ") ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(begin) ;
		params.add(end) ;
		
		if(account.getKid()>0){
			sql.append(" and kid=?") ;
			params.add(account.getKid()) ;
		}
		if(account.getKindid()!=null && account.getKindid().length()>0){
			sql.append(" and kindid=?") ;
			params.add(account.getKindid()) ;
		}
		if(account.getUserId()!=null){
			sql.append(" and userid=?") ;
			params.add(account.getUserId()) ;
		}
		if(account.getTitle()!=null){
			sql.append(" and title like ?") ;
			params.add("%"+account.getTitle()+"%") ;
		}
		if(account.getMoney()>0){
			sql.append(" and money>?") ;
			params.add(account.getMoney()) ;
		}
		sql.append(" order by datetime desc") ;
		
		buf.append("|").append(sql) ;
		buf.append("|").append(this.list2String(params)) ;
		List<Account> list = this.selectList(sql.toString(), params.toArray(), Account.class) ;
		
		buf.append("|").append(list.size()) ;
		buf.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return list ;
	}

	public int insertAccount(Account acc) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertAccount") ;
		
		String sql = "insert into account (uid,title,money,kid,kindid,kindtitle,datetime,userid,username) values(?,?,?,?,?,?,?,?,?)" ;
//		log.info(sql+"	"+acc.getUid()+"	"+acc.getTitle()+"	"+acc.getMoney()+"	"+acc.getKindId()+"	"+acc.getKindTitle()
//				+"	"+acc.getDatetime()+"	"+acc.getUserId()+"	"+acc.getUserName()) ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(acc.getUid()) ;
		params.add(acc.getTitle()) ;
		params.add(acc.getMoney()) ;
		params.add(acc.getKid()) ;
		params.add(acc.getKindid()) ;
		params.add(acc.getKindtitle()) ;
		params.add(acc.getDatetime()) ;
		params.add(acc.getUserId()) ;
		params.add(acc.getUsername()) ;
		
		int result = this.update(sql, params.toArray()) ;
		
		buf.append("|").append(sql)
		.append("|").append(this.list2String(params))
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	public int updateAccount(Account acc) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("updateAccount") ;

		StringBuffer sql = new StringBuffer("update account set ") ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		
		if(acc.getTitle()!=null){
			sql.append(" title=? ,") ;
			params.add(acc.getTitle()) ;
		}
		if(acc.getMoney()>0){
			sql.append(" money=? ,") ;
			params.add(String.valueOf(acc.getMoney())) ;
		}
		if(acc.getDatetime()!=null){
			sql.append(" datetime=? ,") ;
			params.add(acc.getDatetime()) ;
		}
		if(acc.getKindid()!=null){
			sql.append(" kindid=? ,") ;
			params.add(acc.getKindid()) ;
		}
		if(acc.getKid()>0){
			sql.append(" kid=? ,") ;
			params.add(acc.getKid()) ;
		}
		if(acc.getKindtitle()!=null){
			sql.append(" kindtitle=? ,") ;
			params.add(acc.getKindtitle()) ;
		}
		sql.append(" id=?").append(" where id=?") ;
		params.add(acc.getId()) ;
		params.add(acc.getId()) ;
		
		int result = this.update(sql.toString(), params.toArray()) ;

		buf.append("|").append(sql)
		.append("|").append(this.list2String(params))
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return result ;
	}

	public Account findAccountById(int id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findAccountById") ;

		Account account = super.findByID(table, id, Account.class) ;
		
		buf.append("|").append("")
		.append("|").append(id)
		.append("|").append("")
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return account ;
	}

	public List<AccountTable> findAccountTableList(String begin, String end,
			Account account, String type) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findAccountTableList") ;
		
		StringBuffer sql = new StringBuffer(" from account where date(datetime)>=? and date(datetime)<=? ") ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(begin) ;
		params.add(end) ;
		
		if(account.getKid()>0){
			sql.append(" and kid=?") ;
			params.add(account.getKid()) ;
		}
		if(account.getKindid()!=null && account.getKindid().length()>0){
			sql.append(" and kindid=?") ;
			params.add(account.getKindid()) ;
		}
		if(account.getTitle()!=null){
			sql.append(" and title like ?") ;
			params.add("%"+account.getTitle()+"%") ;
		}
		if(account.getMoney()>0){
			sql.append(" and money>?") ;
			params.add(account.getMoney()) ;
		}
		
		String presql = "select count(*) countNum,sum(money) money" ;
		//
		if(type.equals("day")){
			sql.append(" group by substring(datetime,1,10)") ;
			presql+= ",'day' other,substring(datetime,1,10) type" ;
		} else if(type.equals("month")){
			sql.append(" group by substring(datetime,1,7)") ;
			presql+= ",'month' other,substring(datetime,1,7) type" ;
		} else if(type.equals("year")){
			sql.append(" group by substring(datetime,1,4)") ;
			presql+= ",'year' other,substring(datetime,1,4) type" ;
		} else {
			sql.append(" group by kindid") ;
			presql+= ",'kind' other,kindtitle type" ;
		}
		
		sql .append(" order by other ") ;
		
		buf.append("|").append(presql+sql) ;
		buf.append("|").append(this.list2String(params)) ;
		List<AccountTable> list = this.selectList(presql+sql.toString(), params.toArray(), AccountTable.class) ;
		
		buf.append("|").append(list.size()) ;
		buf.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return list ;
	}

}

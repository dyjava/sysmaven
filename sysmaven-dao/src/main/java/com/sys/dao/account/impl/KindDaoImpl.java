package com.sys.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.common.Logs;
import com.sys.dao.AbstractDBDao;
import com.sys.dao.account.KindDao;
import com.sys.domain.account.Kind;

/** 
 * by dyong 2010-6-16
 */
@Repository("kindDao")
public class KindDaoImpl extends AbstractDBDao implements KindDao {
	private String table = "kind" ;
	

	@Override
	public int insert(Kind kind) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertKind") ;
		
		String sql = "insert into kind (uid,title,note,parentId) values(?,?,?,?)" ;
//		log.info(sql+"	"+kind.getTitle()+"	"+kind.getNote()+"	"+kind.getParentId()) ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(kind.getUid()) ;
		params.add(kind.getTitle()) ;
		params.add(kind.getNote()) ;
		params.add(kind.getParentId()) ;
		
		int result = this.update(sql, params.toArray()) ;

		buf.append("|").append(sql)
		.append("|").append(this.list2String(params))
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	@Override
	public int updateById(Kind kind) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("updateKind") ;

		StringBuffer sql = new StringBuffer("update kind set ") ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		
		if(kind.getTitle()!=null){
			sql.append(" title=? ,") ;
			params.add(kind.getTitle()) ;
		}
		if(kind.getNote()!=null){
			sql.append(" note=? ,") ;
			params.add(kind.getNote()) ;
		}
		if(kind.getParentId()!=null){
			sql.append(" parentId=? ,") ;
			params.add(kind.getParentId()) ;
		}
		sql.append(" id=?").append(" where id=?") ;
		params.add(kind.getId()) ;
		params.add(kind.getId()) ;
		
		int result = this.update(sql.toString(), params.toArray()) ;
		buf.append("|").append(sql)
		.append("|").append(this.list2String(params))
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return result ;
	}

	@Override
	public Kind selectById(Object id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findKindById") ;
		
		Kind kind = super.findByID(table, id, Kind.class) ;
		
		buf.append("|").append("")
		.append("|").append(id)
		.append("|").append("")
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return kind ;
	}

	@Override
	public List<Kind> select(Kind record) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("select") ;
		
		StringBuffer sql = new StringBuffer("select * from kind where 1=1 ") ;
		ArrayList<Object> params = new ArrayList<Object>() ;
		
		if(record.getParentId()!=null && record.getParentId().trim().length()>0){
			sql.append(" and parentId=?") ;
			params.add(record.getParentId()) ;
		}
		if(record.getUid()!=null && record.getUid().trim().length()>0){
			sql.append(" and uid=?") ;
			params.add(record.getUid()) ;
		}
		
		sql.append(" order by id desc") ;
		List<Kind> list = this.selectList(sql.toString(), params.toArray(), Kind.class) ;
		
		buf.append("|").append(sql)
		.append("|").append(this.list2String(params))
		.append("|").append(list.size())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		if(list==null || list.size()==0){
			return new ArrayList<Kind>() ;
		} else {
			return list ;
		}
	}

	@Override
	public int deleteById(Object id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

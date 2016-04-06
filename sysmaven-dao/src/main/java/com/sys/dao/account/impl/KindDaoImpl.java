package com.sys.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import com.sys.common.Logs;
import com.sys.dao.AbstractDBDao;
import com.sys.dao.account.KindDao;
import com.sys.domain.account.Kind;

/** 
 * by dyong 2010-6-16
 */
public class KindDaoImpl extends AbstractDBDao implements KindDao {
	private String table = "kind" ;
	
	public List<Kind> findAllKindList() {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findAllKindList") ;
		
		List<Kind> list = super.findAllList(table, Kind.class) ;

		buf.append("|").append("")
		.append("|").append("")
		.append("|").append(list.size())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return list;
	}

	public int insertKind(Kind kind) {
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

	public int updateKind(Kind kind) {
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

	public Kind findKindById(int id) {
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

	public List<Kind> findKindListByParentId(int parentId) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findKindListByParentId") ;
		
		String sql = "select * from kind where parentId = ? ";
		
		List<Kind> list = this.selectList(sql, new Object[] {parentId}, Kind.class) ;
		
		buf.append("|").append(sql)
		.append("|").append(parentId)
		.append("|").append(list.size())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		if(list==null || list.size()==0){
			return new ArrayList<Kind>() ;
		} else {
			return list ;
		}
	}

	public int deleteKindById(int id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("deleteKindById") ;
		
		String sql = "delete from kind where id = ? ";
		
		int result = this.update(sql, new Object[] {id}) ;
		
		buf.append("|").append(sql)
		.append("|").append(id)
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		
		return result ;
	}
}

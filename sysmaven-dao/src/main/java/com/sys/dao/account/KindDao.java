package com.sys.dao.account;

import java.util.List;

import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.account.Kind;

/** 
 * by dyong 2010-6-16
 */
@MyBatisRepository
public interface KindDao {

	public int insertKind(Kind kind) ;
	
	public int updateKind(Kind kind) ;

	public Kind findKindById(int id) ;
	
	public List<Kind> findAllKindList() ;
	
	/**
	 * 按上级ID查询
	 * @param parentId
	 * @return
	 */
	public List<Kind> findKindListByParentId(int parentId) ;
	
//	public int deleteKindById(int id) ;
	
}

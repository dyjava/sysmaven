package com.sys.spring.account.service;

import java.util.List;

import com.sys.spring.account.domain.Kind;


/** 
 * by dyong 2010-6-16
 */
public interface KindService {

	public int insertKind(Kind kind) ;
	
	public int updateKind(Kind kind) ;
	
//	按父级ID查询
	public List<Kind> findKindList(int parentId) ;
	//消耗分类
	public List<Kind> findOutKindList() ;
	//收入分类
	public List<Kind> findIncomeKindList() ;
	//所有分类
	public List<Kind> findAllKindList() ;
	
	public Kind findKindById(int id) ;
	
	public void deleteKindById(int id) ;
}

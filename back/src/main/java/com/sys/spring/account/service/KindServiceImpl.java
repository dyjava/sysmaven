package com.sys.spring.account.service;

import java.util.List;
import java.util.UUID;

import com.sys.spring.account.dao.KindDao;
import com.sys.spring.account.domain.Kind;


/** 
 * by dyong 2010-6-16
 */
public class KindServiceImpl implements KindService {

	private KindDao kindDao ;
	
	public KindDao getKindDao() {
		return kindDao;
	}
	public void setKindDao(KindDao kindDao) {
		this.kindDao = kindDao;
	}
	public int insertKind(Kind kind) {
		kind.setUid(UUID.randomUUID().toString()) ;
		return kindDao.insertKind(kind);
	}
	public int updateKind(Kind kind) {
		return kindDao.updateKind(kind);
	}
	public Kind findKindById(int id) {
		return kindDao.findKindById(id);
	}

	public List<Kind> findKindList(int parentId) {
		return kindDao.findKindListByParentId(parentId);
	}
	public void deleteKindById(int id) {
		kindDao.deleteKindById(id) ;
	}
	public List<Kind> findOutKindList() {
		return kindDao.findKindListByParentId(-1);
	}
	public List<Kind> findIncomeKindList() {
		return kindDao.findKindListByParentId(1);
	}
	public List<Kind> findAllKindList() {
		return kindDao.findAllKindList() ;
	}

}

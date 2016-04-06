package com.sys.service.account.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.account.KindDao;
import com.sys.domain.account.Kind;
import com.sys.service.account.KindService;

/** 
 * by dyong 2010-6-16
 */
@Service("kindService")
public class KindServiceImpl implements KindService {

	@Autowired
	private KindDao kindDao ;
	
	@Override
	public int insertKind(Kind kind) {
		kind.setUid(UUID.randomUUID().toString()) ;
		return kindDao.insertKind(kind);
	}
	@Override
	public int updateKind(Kind kind) {
		return kindDao.updateKind(kind);
	}
	@Override
	public Kind findKindById(int id) {
		return kindDao.findKindById(id);
	}
	@Override
	public List<Kind> findKindList(int parentId) {
		return kindDao.findKindListByParentId(parentId);
	}
	@Override
	public List<Kind> findOutKindList() {
		return kindDao.findKindListByParentId(-1);
	}
	@Override
	public List<Kind> findIncomeKindList() {
		return kindDao.findKindListByParentId(1);
	}
	@Override
	public List<Kind> findAllKindList() {
		return kindDao.findAllKindList() ;
	}

}

package com.sys.dao.account;

import java.io.Serializable;
import java.util.List;

import com.sys.dao.BaseDao;
import com.sys.dao.mybatis.MyBatisRepository;
import com.sys.domain.account.Diary;
import com.sys.domain.user.User;

/** 
 * by dyong 2010-6-16
 */
//@MyBatisRepository
public interface DiaryDao extends BaseDao<Diary, Serializable>{

	/**
	 * 按用户及查询条件查询日记列表
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @param diary 查询条件
	 * @param user 查询人信息
	 * @return
	 */
	public List<Diary> findDiaryListByUser(String begin, String end, Diary diary,User user) ;	//find list by params
	
}

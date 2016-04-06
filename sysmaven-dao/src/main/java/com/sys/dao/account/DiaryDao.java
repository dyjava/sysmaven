package com.sys.dao.account;

import java.util.List;

import com.sys.domain.account.Diary;
import com.sys.domain.user.User;

/** 
 * by dyong 2010-6-16
 */
public interface DiaryDao {

	public int insertDiary(Diary diary) ;	//insert
	
	public int updateDiary(Diary diary) ;	//update
	
	/**
	 * 按用户及查询条件查询日记列表
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @param diary 查询条件
	 * @param user 查询人信息
	 * @return
	 */
	public List<Diary> findDiaryListByUser(String begin, String end, Diary diary,User user) ;	//find list by params
	
	public Diary findDiaryById(int id) ;	//find one boject by id
}

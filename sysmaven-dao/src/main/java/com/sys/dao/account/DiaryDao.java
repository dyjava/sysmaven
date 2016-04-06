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
	
	public List<Diary> findDiaryListByUser(String begin, String end, Diary diary,User user) ;	//find list by params
	
	public Diary findDiaryById(int id) ;	//find one boject by id
}

package com.sys.service.account;

import java.util.List;

import com.sys.domain.account.Diary;
import com.sys.domain.user.User;


/** 
 * by dyong 2010-6-16
 */
public interface DiaryService {

	public int insertDiary(Diary diary,User user) ;
	
	public int updateDiary(Diary diary,User user) ;

	public List<Diary> findDiaryListByUser(User user) ;
	
	public List<Diary> findDiaryListByUser(String begin, String end, Diary diary,User user) ;
	
	public Diary findDiaryById(int id) ;
}

package com.sys.spring.account.service;

import java.util.List;

import com.sys.spring.account.domain.Diary;
import com.sys.spring.user.domain.User;


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

package com.sys.service.account.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sys.dao.account.DiaryDao;
import com.sys.domain.account.Diary;
import com.sys.domain.user.User;
import com.sys.service.account.DiaryService;

/** 
 * by dyong 2010-6-16
 */
@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

	private DiaryDao diaryDao ;
	
	public DiaryDao getDiaryDao() {
		return diaryDao;
	}

	public void setDiaryDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}
	
	@Override
	public Diary findDiaryById(int id) {
		return diaryDao.findDiaryById(id);
	}

	@Override
	public List<Diary> findDiaryListByUser(User user) {
		String end = "" ;
		String begin = "" ;
		return this.findDiaryListByUser(begin, end, new Diary(), user);
	}
	
	@Override
	public List<Diary> findDiaryListByUser(String begin, String end, Diary diary,User user) {
		return diaryDao.findDiaryListByUser(begin,end,diary,user);
	}

	@Override
	public int insertDiary(Diary diary,User user) {
		diary.setUid(UUID.randomUUID().toString()) ;
		diary.setUserId(user.getUid()) ;
		diary.setUsername(user.getUsername()) ;
		return diaryDao.insertDiary(diary);
	}

	@Override
	public int updateDiary(Diary diary,User user) {
		diary.setUserId(user.getUid()) ;
		diary.setUsername(user.getUsername()) ;
		return diaryDao.updateDiary(diary);
	}

}

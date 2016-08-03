package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.SurveyDao;
import com.wxpt.site.entity.Survey;
import com.wxpt.site.service.SurveyService;

public class SurveyServiceImpl implements SurveyService{
	SurveyDao surveyDao;
	@Transactional
	@Override
	public List<Survey> getAllSurvey(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return surveyDao.getAllSurvey(sql, page, pageSize);
	}
	public SurveyDao getSurveyDao() {
		return surveyDao;
	}
	public void setSurveyDao(SurveyDao surveyDao) {
		this.surveyDao = surveyDao;
	}
	@Override
	@Transactional
	public List<Survey> getAllSurveyList(String sql) {
		// TODO Auto-generated method stub
		return surveyDao.getAllSurveyList(sql);
	}
	@Override
	@Transactional
	public void deletedeleteSurvey(String sql, int enterId) {
		// TODO Auto-generated method stub
		surveyDao.deletedeleteSurvey(sql, enterId)
		;
	}
	@Transactional
	public void saveSurVey(Survey sur,int enterId) {
		surveyDao.saveSurVey(sur,enterId);
		
	}
	@Transactional
	public Survey findByFromUsername(String fromUsername,int enterId) {
		// TODO Auto-generated method stub
		return surveyDao.findByFromUsername(fromUsername, enterId);
	}
	
	

}

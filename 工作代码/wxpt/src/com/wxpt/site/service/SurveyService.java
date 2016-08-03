package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Survey;

public interface SurveyService {
	public List< Survey> getAllSurvey(String sql, int page, int pageSize) ;
	public List< Survey> getAllSurveyList(String sql) ;
	public void deletedeleteSurvey(String sql,int enterId);
	public void saveSurVey(Survey sur,int enterId);
	public Survey findByFromUsername(String fromUsername,int enterId);
}

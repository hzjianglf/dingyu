package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Answer;
import com.wxpt.site.entity.Idiom;
import com.wxpt.site.entity.Question;
import com.wxpt.site.entity.QuestionRule;
import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.entity.QuestionType;
import com.wxpt.site.entity.TrainNumber;

public interface QuestionService {
	public void addQuestion(int enterId,Question question);
	public List<Question> getQuestionList();
	public void deleQuestion(int enterId,Question question);
	public Question getQuestionByID(int enterId,int questionId);
	public Answer getMaxAnswer(int enterId,String sendUser);
	public void updateAnswer(int enterId,Answer answer,int questionId);
	public List<Answer> getDayAnswer(int enterId,String user);
	public List<Question> getQtByIds(int enterId,String ids);
	public void addAnswer(int MaxId,int enterId,int state,String foruser,String time, int id);
	public Answer getMaxAnswerById(String sendUser);
	public void updateBySql(int enterId,String sendUser);
	public Answer getMaxAnswer(int enterId,String sendUser,int state);
	public List<Answer> getAnAnswerList(int enterId,String sendUser);
	public void updateBySql(String sendUser,int state);
	public List<QuestionType> getQsTypeList(int enterId);
	public QuestionType getTypeById(int enterId,int typeId);
	public List<Question> getQuestionList(int enterId,int typeNum);
	public List<Question> getQtByIds(int enterId,String ids,int typeNum);
	public QuestionType getTypeByNum(int enterId,int typeNum);
	public List<Question> getQuestionList(int enterId,int page, int rows);
	public int getCount(String string);
	public int getKeyWordsCount(String sql);
	public int getquestionCount(String sql);
	public void updateBy(int enterId,Question question,int typeId);
	public int updateBySql2(int enterId, String fromUsername);
	//
	public int questionIdById(int enterId,int questionId);
	public String typeName(int enterId,int typeId);
	public int getQuestionId(int enterId,int answerId);
	
	//添加规则
	public void addRule(String sql);
	
	//查询规则
	public List<QuestionRule> getAllRule(String sql, int page,int pageSize);
	
	//查询规则
	public List<QuestionRule> getRuleList(int enterId);
	
	//修改规则
	public void updateRule(String sql,int enterId);
	
	//查询提示
	public List<QuestionTishi> getAllTishi(String sql);
	
	//最大ID
	public Answer getMaxId(String sql);
	
	//判断是否存在这个成语
	public boolean getidiom(String sql);
	
	//随机取成语Idiom.java
	public List<Idiom> getIdiom(String sql);
	
	//余票查询
	public TrainNumber getTrain(String sql);
}

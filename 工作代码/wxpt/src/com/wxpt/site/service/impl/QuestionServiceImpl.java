package com.wxpt.site.service.impl;

import java.util.List;

import javax.persistence.TableGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.QuestionDao;
import com.wxpt.site.entity.Answer;
import com.wxpt.site.entity.Idiom;
import com.wxpt.site.entity.Question;
import com.wxpt.site.entity.QuestionRule;
import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.entity.QuestionType;
import com.wxpt.site.entity.TrainNumber;
import com.wxpt.site.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDao questionDao;

	@Transactional
	@Override
	public void addQuestion(int enterId,Question question) {
		// TODO Auto-generated method stub
		questionDao.addQuestion(enterId,question);
	}

	@Transactional
	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return questionDao.getQuestionList();
	}

	@Transactional
	@Override
	public void deleQuestion(int enterId,Question question) {
		// TODO Auto-generated method stub
		questionDao.deleQuestion(enterId,question);
	}

	@Transactional
	@Override
	public Question getQuestionByID(int enterId,int questionId) {
		// TODO Auto-generated method stub
		return questionDao.getQuestionByID(enterId,questionId);
	}

	@Transactional
	@Override
	public Answer getMaxAnswer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		return questionDao.getMaxAnswer(enterId,sendUser);
	}

	@Transactional
	@Override
	public void updateAnswer(int enterId,Answer answer,int questionId) {
		// TODO Auto-generated method stub
		questionDao.updateAnswer(enterId,answer,questionId);
	}

	@Transactional
	@Override
	public List<Answer> getDayAnswer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		return questionDao.getDayAnswer(enterId,sendUser);
	}

	@Transactional
	@Override
	public List<Question> getQtByIds(int enterId,String ids) {
		// TODO Auto-generated method stub
		return questionDao.getQtByIds(enterId,ids);
	}

	@Transactional
	@Override
	public void addAnswer(int MaxId,int enterId,int state,String foruser,String time, int id) {
		// TODO Auto-generated method stub
		questionDao.addAnswer(MaxId,enterId,state,foruser,time,id);
	}

	@Transactional
	@Override
	public Answer getMaxAnswerById(String sendUser) {
		// TODO Auto-generated method stub
		return questionDao.getMaxAnswerById(sendUser);
	}

	@Transactional
	@Override
	public void updateBySql(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		questionDao.updateBySql(enterId,sendUser);
	}

	@Transactional
	@Override
	public Answer getMaxAnswer(int enterId,String sendUser, int state) {
		// TODO Auto-generated method stub
		return questionDao.getMaxAnswer(enterId,sendUser, state);
	}

	@Transactional
	@Override
	public List<Answer> getAnAnswerList(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		return questionDao.getAnAnswerList(enterId,sendUser);
	}

	@Transactional
	@Override
	public void updateBySql(String sendUser, int state) {
		// TODO Auto-generated method stub
		questionDao.updateBySql(sendUser, state);
	}

	@Transactional
	@Override
	public List<QuestionType> getQsTypeList(int enterId) {
		// TODO Auto-generated method stub
		return questionDao.getQsTypeList(enterId);
	}

	@Transactional
	@Override
	public QuestionType getTypeById(int enterId,int typeId) {
		// TODO Auto-generated method stub
		return questionDao.getTypeById(enterId,typeId);
	}

	@Transactional
	@Override
	public List<Question> getQuestionList(int enterId,int typeNum) {
		// TODO Auto-generated method stub
		return questionDao.getQuestionList(enterId,typeNum);
	}

	@Transactional
	@Override
	public List<Question> getQtByIds(int enterId,String ids, int typeNum) {
		// TODO Auto-generated method stub
		return questionDao.getQtByIds(enterId,ids, typeNum);
	}

	@Transactional
	@Override
	public QuestionType getTypeByNum(int enterId,int typeNum) {
		// TODO Auto-generated method stub
		return questionDao.getTypeByNum(enterId,typeNum);
	}

	@Transactional
	@Override
	public List<Question> getQuestionList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return questionDao.getQuestionList(enterId,page, rows);
	}

	@Transactional
	@Override
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return questionDao.getCount(hql);
	}
	@Transactional
	@Override
	public void updateBy(int enterId,Question question,int typeId) {
		// TODO Auto-generated method stub
		questionDao.updateBy(enterId,question,typeId);
	}

	@Override
	public int questionIdById(int enterId, int questionId) {
		// TODO Auto-generated method stub
		return questionDao.questionIdById(enterId, questionId);
	}

	@Override
	public String typeName(int enterId, int typeId) {
		// TODO Auto-generated method stub
		return questionDao.typeName(enterId, typeId);
	}
	@Transactional
	@Override
	public int getKeyWordsCount(String sql) {
		// TODO Auto-generated method stub
		return questionDao.getKeyWordsCount(sql);
	}
	@Transactional
	@Override
	public int getquestionCount(String sql) {
		// TODO Auto-generated method stub
		return questionDao.getquestionCount(sql);
	}
	@Transactional
	@Override
	public int getQuestionId(int enterId,int answerId) {
		// TODO Auto-generated method stub
		return questionDao.getQuestionId(enterId,answerId);
	}
	@Transactional
	@Override
	public void addRule(String sql) {
		// TODO Auto-generated method stub
		questionDao.addRule(sql);
	}
	@Transactional
	@Override
	public List<QuestionRule> getAllRule(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return questionDao.getAllRule(sql, page, pageSize);
	}
	@Transactional
	@Override
	public void updateRule(String sql, int enterId) {
		// TODO Auto-generated method stub
		questionDao.updateRule(sql, enterId);
	}
	@Transactional
	@Override
	public List<QuestionTishi> getAllTishi(String sql) {
		// TODO Auto-generated method stub
		return questionDao.getAllTishi(sql);
	}
	@Transactional
	@Override
	public List<QuestionRule> getRuleList(int enterId) {
		// TODO Auto-generated method stub
		return questionDao.getRuleList(enterId);
	}
@Transactional
	@Override
	public int updateBySql2(int enterId, String fromUsername) {
		// TODO Auto-generated method stub
		return questionDao.updateBySql2(enterId, fromUsername);
	}

@Override
public Answer getMaxId(String sql) {
	// TODO Auto-generated method stub
	return questionDao.getMaxId(sql);
}

@Override
public boolean getidiom(String sql) {
	// TODO Auto-generated method stub
	return questionDao.getidiom(sql);
}

@Override
@Transactional
public List<Idiom> getIdiom(String sql) {
	// TODO Auto-generated method stub
	return questionDao.getIdiom(sql);
}
@Transactional
@Override
public TrainNumber getTrain(String sql) {
	// TODO Auto-generated method stub
	return questionDao.getTrain(sql);
}



}

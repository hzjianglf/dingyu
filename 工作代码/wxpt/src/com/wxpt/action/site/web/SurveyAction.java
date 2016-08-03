package com.wxpt.action.site.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surquestion;
import com.wxpt.site.entity.Surrecord;
import com.wxpt.site.entity.Survey;
import com.wxpt.site.entity.pojo.QuestionPojo;
import com.wxpt.site.service.SuroptionService;
import com.wxpt.site.service.SurquestionService;
import com.wxpt.site.service.SurrecordService;
import com.wxpt.site.service.SurveyService;

public class SurveyAction extends ActionSupport{
	private List<Surquestion> surquestionList;
	private List<Suroption> SuroptionList;
	private List<QuestionPojo> questionList=new ArrayList<QuestionPojo>();
	List<Suroption>optionList;
	Survey survey;
	public List<Suroption> getOptionList() {
		return optionList;
	}




	public void setOptionList(List<Suroption> optionList) {
		this.optionList = optionList;
	}

	private int enterId;
	private String optionName;
	private 
	@Autowired
	SurquestionService surquestionService;
	@Autowired
	SuroptionService suroptionService;
public String execute() throws Exception {
	System.out.println("dssdsdsd");
	QuestionPojo  question = new QuestionPojo();
	Surquestion surquestion=new Surquestion();
	
	surquestionList=surquestionService.findAll(enterId);
	System.out.println(surquestionList);
	for(int i=0;i<surquestionList.size();i++){
		question=new QuestionPojo();
		surquestion=surquestionList.get(i);
		System.out.println(surquestion);
		System.out.println(optionList);
		optionList=suroptionService.findByquestionId(surquestion.getSurquestionId(),enterId);
		question.setSurquestion(surquestion);
		question.setOptionList(optionList);
		questionList.add(question);
	}
	String sql="SELECT * FROM wxpt"+enterId+".suroption ";
	optionList=suroptionService.getAllSuroptionList(sql);
	System.out.println(optionList.size());
	return "survey";
}



private String fromUsername;
private String surveyTime;
private Integer surveyCode;
private String surveyUserName;
private String surveyUserPhone;
private String surveyUserQq;
private String surveyUserEmail;
private Integer surveyUerSex;
private Integer surveyUserAge;
private String surveyUserEdu;
private String surveyUserWork;
private String optionId;
private String optionStrId;
@Autowired
SurveyService surveyService;
@Autowired
SurrecordService surrecordService;
public void save(){
	Survey	sur3=surveyService.findByFromUsername(fromUsername,enterId);
	if(sur3 == null){
		System.out.println(optionStrId);
		String[] opstr=optionStrId.split(";");
		String surStr=opstr[0];
		String surStrduo=opstr[1];
		String[] surStrindex=surStr.split(",");
		surveyUerSex=Integer.parseInt(surStrindex[1]);
		surveyUserAge=Integer.parseInt(surStrindex[2]);
		surveyUserEdu=surStrindex[3];
		surveyUserWork=surStrindex[4];
		surveyTime=TimeUtil.getTime();
		surveyCode=0;//默认赋值为0  保留字段
		Survey sur=new Survey();
		sur.setSurveyUser(fromUsername);
		sur.setSurveyCode(surveyCode);
		sur.setSurveyTime(surveyTime);
		sur.setSurveyUserName(surveyUserName);
		sur.setSurveyUserPhone(surveyUserPhone);
		sur.setSurveyUserEmail(surveyUserEmail);
		sur.setSurveyUerSex(surveyUerSex);
		sur.setSurveyUserEdu(surveyUserEdu);
		sur.setSurveyUserWork(surveyUserWork);
		sur.setSurveyUserAge(surveyUserAge);
		sur.setSurveyUserQq(surveyUserQq);
		surveyService.saveSurVey(sur,enterId);
		//存储单选
			
		
		Surrecord surrecord=new Surrecord();
		Suroption option=new Suroption();
		Survey sur2=new Survey();
		int questionId=0;
		int surveyId=0;
		String sql="";
		for(int i=5;i<surStrindex.length;i++){
			if(surStrindex.length > i){
			option=surrecordService.findByOptionId(Integer.parseInt(surStrindex[i]),enterId);
			questionId=option.getSurquestion().getSurquestionId();
			sur2=surveyService.findByFromUsername(fromUsername,enterId);
			surveyId=sur2.getSurveyId();
			sql="INSERT INTO wxpt"+enterId+".surrecord(`surquestion_id`, `option_id`, `survey_id`) " +
					"VALUES ("+questionId+","+surStrindex[i]+","+surveyId+")";
			surrecordService.save(sql,enterId);
			}
		}
		
		//存储多选
		
		
		String[] surStrinduodex=surStrduo.split(",");
		for(int i=1;i<surStrinduodex.length;i++){
			if(surStrinduodex.length > i){
				option=surrecordService.findByOptionId(Integer.parseInt(surStrinduodex[i]),enterId);
				questionId=option.getSurquestion().getSurquestionId();
				sur2=surveyService.findByFromUsername(fromUsername,enterId);
				surveyId=sur2.getSurveyId();
				sql="INSERT INTO wxpt"+enterId+".surrecord(`surquestion_id`, `option_id`, `survey_id`) " +
						"VALUES ("+questionId+","+surStrinduodex[i]+","+surveyId+")";
				surrecordService.save(sql,enterId);
			}
			
		}
	}else{
		System.out.println("已经提交过！");
	}

	}



public Survey getSurvey() {
	return survey;
}




public void setSurvey(Survey survey) {
	this.survey = survey;
}









public String getFromUsername() {
	return fromUsername;
}




public void setFromUsername(String fromUsername) {
	this.fromUsername = fromUsername;
}




public String getSurveyTime() {
	return surveyTime;
}




public void setSurveyTime(String surveyTime) {
	this.surveyTime = surveyTime;
}




public Integer getSurveyCode() {
	return surveyCode;
}




public void setSurveyCode(Integer surveyCode) {
	this.surveyCode = surveyCode;
}




public String getSurveyUserName() {
	return surveyUserName;
}




public void setSurveyUserName(String surveyUserName) {
	this.surveyUserName = surveyUserName;
}




public String getSurveyUserPhone() {
	return surveyUserPhone;
}




public void setSurveyUserPhone(String surveyUserPhone) {
	this.surveyUserPhone = surveyUserPhone;
}




public String getSurveyUserQq() {
	return surveyUserQq;
}




public void setSurveyUserQq(String surveyUserQq) {
	this.surveyUserQq = surveyUserQq;
}




public String getSurveyUserEmail() {
	return surveyUserEmail;
}




public void setSurveyUserEmail(String surveyUserEmail) {
	this.surveyUserEmail = surveyUserEmail;
}




public Integer getSurveyUerSex() {
	return surveyUerSex;
}




public void setSurveyUerSex(Integer surveyUerSex) {
	this.surveyUerSex = surveyUerSex;
}




public Integer getSurveyUserAge() {
	return surveyUserAge;
}




public void setSurveyUserAge(Integer surveyUserAge) {
	this.surveyUserAge = surveyUserAge;
}




public String getSurveyUserEdu() {
	return surveyUserEdu;
}




public void setSurveyUserEdu(String surveyUserEdu) {
	this.surveyUserEdu = surveyUserEdu;
}




public String getSurveyUserWork() {
	return surveyUserWork;
}




public void setSurveyUserWork(String surveyUserWork) {
	this.surveyUserWork = surveyUserWork;
}




public String getOptionId() {
	return optionId;
}




public void setOptionId(String optionId) {
	this.optionId = optionId;
}




public List<Surquestion> getSurquestionList() {
	return surquestionList;
}
public void setSurquestionList(List<Surquestion> surquestionList) {
	this.surquestionList = surquestionList;
}
public List<Suroption> getSuroptionList() {
	return SuroptionList;
}
public void setSuroptionList(List<Suroption> suroptionList) {
	SuroptionList = suroptionList;
}
public int getEnterId() {
	return enterId;
}
public void setEnterId(int enterId) {
	this.enterId = enterId;
}

public String getOptionName() {
	return optionName;
}

public void setOptionName(String optionName) {
	this.optionName = optionName;
}

public List<QuestionPojo> getQuestionList() {
	return questionList;
}

public void setQuestionList(List<QuestionPojo> questionList) {
	this.questionList = questionList;
}




public String getOptionStrId() {
	return optionStrId;
}




public void setOptionStrId(String optionStrId) {
	this.optionStrId = optionStrId;
}


}

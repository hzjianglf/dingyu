package com.wxpt.site.entity.pojo;


public class QuestionPoro {
	private Integer questionid;
	private String questionTypeStr;//类型编号：困难,简单
	private int questionType;//类型编号：困难,简单
	private String qustionContent;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String rightAnswer;
	private String questionTitle;
	private Integer type;
	private String typeStr;//文本，消息
	public Integer getQuestionid() {
		return questionid;
	}
	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}
	public String getQuestionTypeStr() {
		return questionTypeStr;
	}
	public void setQuestionTypeStr(String questionTypeStr) {
		this.questionTypeStr = questionTypeStr;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getQustionContent() {
		return qustionContent;
	}
	public void setQustionContent(String qustionContent) {
		this.qustionContent = qustionContent;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public QuestionPoro(Integer questionid, String questionTypeStr,
			int questionType, String qustionContent, String answerA,
			String answerB, String answerC, String answerD, String rightAnswer,
			String questionTitle, Integer type, String typeStr) {
		super();
		this.questionid = questionid;
		this.questionTypeStr = questionTypeStr;
		this.questionType = questionType;
		this.qustionContent = qustionContent;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.rightAnswer = rightAnswer;
		this.questionTitle = questionTitle;
		this.type = type;
		this.typeStr = typeStr;
	}
	public QuestionPoro() {
		// TODO Auto-generated constructor stub
	}
}

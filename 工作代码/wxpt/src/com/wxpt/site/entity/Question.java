package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	private Integer questionid;
	private QuestionType questionType;
	private String qustionContent;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String rightAnswer;
	private String questionTitle;
	private Integer type;
	private Set answers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(QuestionType questionType, String qustionContent,
			Integer type) {
		this.questionType = questionType;
		this.qustionContent = qustionContent;
		this.type = type;
	}

	/** full constructor */
	public Question(QuestionType questionType, String qustionContent,
			String answerA, String answerB, String answerC, String answerD,
			String rightAnswer, String questionTitle, Integer type, Set answers) {
		this.questionType = questionType;
		this.qustionContent = qustionContent;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.rightAnswer = rightAnswer;
		this.questionTitle = questionTitle;
		this.type = type;
		this.answers = answers;
	}

	// Property accessors

	public Integer getQuestionid() {
		return this.questionid;
	}

	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQustionContent() {
		return this.qustionContent;
	}

	public void setQustionContent(String qustionContent) {
		this.qustionContent = qustionContent;
	}

	public String getAnswerA() {
		return this.answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return this.answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return this.answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return this.answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public String getRightAnswer() {
		return this.rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getQuestionTitle() {
		return this.questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set answers) {
		this.answers = answers;
	}

}
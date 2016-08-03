package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Survey entity. @author MyEclipse Persistence Tools
 */

public class Survey implements java.io.Serializable {

	// Fields

	private Integer surveyId;
	private String surveyUser;
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
	private Set surrecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Survey() {
	}

	/** minimal constructor */
	public Survey(String surveyUser, String surveyTime, Integer surveyCode,
			String surveyUserName, String surveyUserPhone, String surveyUserQq,
			String surveyUserEmail, Integer surveyUerSex,
			Integer surveyUserAge, String surveyUserEdu, String surveyUserWork) {
		this.surveyUser = surveyUser;
		this.surveyTime = surveyTime;
		this.surveyCode = surveyCode;
		this.surveyUserName = surveyUserName;
		this.surveyUserPhone = surveyUserPhone;
		this.surveyUserQq = surveyUserQq;
		this.surveyUserEmail = surveyUserEmail;
		this.surveyUerSex = surveyUerSex;
		this.surveyUserAge = surveyUserAge;
		this.surveyUserEdu = surveyUserEdu;
		this.surveyUserWork = surveyUserWork;
	}

	/** full constructor */
	public Survey(String surveyUser, String surveyTime, Integer surveyCode,
			String surveyUserName, String surveyUserPhone, String surveyUserQq,
			String surveyUserEmail, Integer surveyUerSex,
			Integer surveyUserAge, String surveyUserEdu, String surveyUserWork,
			Set surrecords) {
		this.surveyUser = surveyUser;
		this.surveyTime = surveyTime;
		this.surveyCode = surveyCode;
		this.surveyUserName = surveyUserName;
		this.surveyUserPhone = surveyUserPhone;
		this.surveyUserQq = surveyUserQq;
		this.surveyUserEmail = surveyUserEmail;
		this.surveyUerSex = surveyUerSex;
		this.surveyUserAge = surveyUserAge;
		this.surveyUserEdu = surveyUserEdu;
		this.surveyUserWork = surveyUserWork;
		this.surrecords = surrecords;
	}

	// Property accessors

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyUser() {
		return this.surveyUser;
	}

	public void setSurveyUser(String surveyUser) {
		this.surveyUser = surveyUser;
	}

	public String getSurveyTime() {
		return this.surveyTime;
	}

	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}

	public Integer getSurveyCode() {
		return this.surveyCode;
	}

	public void setSurveyCode(Integer surveyCode) {
		this.surveyCode = surveyCode;
	}

	public String getSurveyUserName() {
		return this.surveyUserName;
	}

	public void setSurveyUserName(String surveyUserName) {
		this.surveyUserName = surveyUserName;
	}

	public String getSurveyUserPhone() {
		return this.surveyUserPhone;
	}

	public void setSurveyUserPhone(String surveyUserPhone) {
		this.surveyUserPhone = surveyUserPhone;
	}

	public String getSurveyUserQq() {
		return this.surveyUserQq;
	}

	public void setSurveyUserQq(String surveyUserQq) {
		this.surveyUserQq = surveyUserQq;
	}

	public String getSurveyUserEmail() {
		return this.surveyUserEmail;
	}

	public void setSurveyUserEmail(String surveyUserEmail) {
		this.surveyUserEmail = surveyUserEmail;
	}

	public Integer getSurveyUerSex() {
		return this.surveyUerSex;
	}

	public void setSurveyUerSex(Integer surveyUerSex) {
		this.surveyUerSex = surveyUerSex;
	}

	public Integer getSurveyUserAge() {
		return this.surveyUserAge;
	}

	public void setSurveyUserAge(Integer surveyUserAge) {
		this.surveyUserAge = surveyUserAge;
	}

	public String getSurveyUserEdu() {
		return this.surveyUserEdu;
	}

	public void setSurveyUserEdu(String surveyUserEdu) {
		this.surveyUserEdu = surveyUserEdu;
	}

	public String getSurveyUserWork() {
		return this.surveyUserWork;
	}

	public void setSurveyUserWork(String surveyUserWork) {
		this.surveyUserWork = surveyUserWork;
	}

	public Set getSurrecords() {
		return this.surrecords;
	}

	public void setSurrecords(Set surrecords) {
		this.surrecords = surrecords;
	}

}
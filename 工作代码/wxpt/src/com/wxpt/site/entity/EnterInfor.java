package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * EnterInfor entity. @author MyEclipse Persistence Tools
 */

public class EnterInfor implements java.io.Serializable {

	// Fields

	private Integer enterInforId;
	private String userName;
	private String enterName;
	private String enterPerson;
	private String enterPhone;
	private String enterAddress;
	private String registTime;
	private String email;
	private String url;
	private String token;
	private String powerCode;
	private String powerTime;
	private Integer state;
	private String appSecret;
	private String appId;
	private String accessToken;
	private String banner;
	private String chilendQyId;
	private String merId;
	private String curyId;
	private String transtType;
	private String version;
	private String gateId;
	private String privl;
	private String partner;
	private String key;
	private String sellerEmail;
	private String body;
	private Set userTemplates = new HashSet(0);

	// Constructors

	/** default constructor */
	public EnterInfor() {
	}

	/** minimal constructor */
	public EnterInfor(String userName) {
		this.userName = userName;
	}

	/** full constructor */
	public EnterInfor(String userName, String enterName, String enterPerson,
			String enterPhone, String enterAddress, String registTime,
			String email, String url, String token, String powerCode,
			String powerTime, Integer state, String appSecret, String appId,
			String accessToken, String banner, String chilendQyId,
			String merId, String curyId, String transtType, String version,
			String gateId, String privl, String partner, String key,
			String sellerEmail, String body, Set userTemplates) {
		this.userName = userName;
		this.enterName = enterName;
		this.enterPerson = enterPerson;
		this.enterPhone = enterPhone;
		this.enterAddress = enterAddress;
		this.registTime = registTime;
		this.email = email;
		this.url = url;
		this.token = token;
		this.powerCode = powerCode;
		this.powerTime = powerTime;
		this.state = state;
		this.appSecret = appSecret;
		this.appId = appId;
		this.accessToken = accessToken;
		this.banner = banner;
		this.chilendQyId = chilendQyId;
		this.merId = merId;
		this.curyId = curyId;
		this.transtType = transtType;
		this.version = version;
		this.gateId = gateId;
		this.privl = privl;
		this.partner = partner;
		this.key = key;
		this.sellerEmail = sellerEmail;
		this.body = body;
		this.userTemplates = userTemplates;
	}

	// Property accessors

	public Integer getEnterInforId() {
		return this.enterInforId;
	}

	public void setEnterInforId(Integer enterInforId) {
		this.enterInforId = enterInforId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnterName() {
		return this.enterName;
	}

	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	public String getEnterPerson() {
		return this.enterPerson;
	}

	public void setEnterPerson(String enterPerson) {
		this.enterPerson = enterPerson;
	}

	public String getEnterPhone() {
		return this.enterPhone;
	}

	public void setEnterPhone(String enterPhone) {
		this.enterPhone = enterPhone;
	}

	public String getEnterAddress() {
		return this.enterAddress;
	}

	public void setEnterAddress(String enterAddress) {
		this.enterAddress = enterAddress;
	}

	public String getRegistTime() {
		return this.registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPowerCode() {
		return this.powerCode;
	}

	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}

	public String getPowerTime() {
		return this.powerTime;
	}

	public void setPowerTime(String powerTime) {
		this.powerTime = powerTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getChilendQyId() {
		return this.chilendQyId;
	}

	public void setChilendQyId(String chilendQyId) {
		this.chilendQyId = chilendQyId;
	}

	public String getMerId() {
		return this.merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getCuryId() {
		return this.curyId;
	}

	public void setCuryId(String curyId) {
		this.curyId = curyId;
	}

	public String getTranstType() {
		return this.transtType;
	}

	public void setTranstType(String transtType) {
		this.transtType = transtType;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGateId() {
		return this.gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getPrivl() {
		return this.privl;
	}

	public void setPrivl(String privl) {
		this.privl = privl;
	}

	public String getPartner() {
		return this.partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSellerEmail() {
		return this.sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Set getUserTemplates() {
		return this.userTemplates;
	}

	public void setUserTemplates(Set userTemplates) {
		this.userTemplates = userTemplates;
	}

}
package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * FansUser entity. @author MyEclipse Persistence Tools
 */

public class FansUser implements java.io.Serializable {

	// Fields

	private Integer fansUserId;
	private String fansUserName;
	private String updateTime;
	private String signature;
	private String nickname;
	private String touxiang;
	private Set fansImages = new HashSet(0);
	private Set fansImageVisits = new HashSet(0);

	// Constructors

	/** default constructor */
	public FansUser() {
	}
	public FansUser(String fansUserName, String updateTime) {
		this.fansUserName = fansUserName;
		this.updateTime = updateTime;
	}
	/** full constructor */
	public FansUser(String fansUserName, String updateTime, String signature,
			String nickname, String touxiang, Set fansImages,
			Set fansImageVisits) {
		this.fansUserName = fansUserName;
		this.updateTime = updateTime;
		this.signature = signature;
		this.nickname = nickname;
		this.touxiang = touxiang;
		this.fansImages = fansImages;
		this.fansImageVisits = fansImageVisits;
	}

	// Property accessors

	public Integer getFansUserId() {
		return this.fansUserId;
	}

	public void setFansUserId(Integer fansUserId) {
		this.fansUserId = fansUserId;
	}

	public String getFansUserName() {
		return this.fansUserName;
	}

	public void setFansUserName(String fansUserName) {
		this.fansUserName = fansUserName;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTouxiang() {
		return this.touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public Set getFansImages() {
		return this.fansImages;
	}

	public void setFansImages(Set fansImages) {
		this.fansImages = fansImages;
	}

	public Set getFansImageVisits() {
		return this.fansImageVisits;
	}

	public void setFansImageVisits(Set fansImageVisits) {
		this.fansImageVisits = fansImageVisits;
	}

}
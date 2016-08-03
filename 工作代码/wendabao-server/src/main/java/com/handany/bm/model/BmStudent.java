package com.handany.bm.model;

import java.util.Date;

import com.handany.rbac.model.PmUser;

import java.math.BigDecimal;

public class BmStudent {
    private String id;

    private String userId;

    private String name;

    private String region1;

    private String region2;

    private String region3;

    private String region1Txt;

    private String region2Txt;

    private String region3Txt;

    private String grade;

    private String userPic;

    private BigDecimal qaTime;

    private String status;

    private Date lastModified;
    
    private PmUser user;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion3(String region3) {
        this.region3 = region3;
    }

    public String getRegion3() {
        return region3;
    }

    public void setRegion1Txt(String region1Txt) {
        this.region1Txt = region1Txt;
    }

    public String getRegion1Txt() {
        return region1Txt;
    }

    public void setRegion2Txt(String region2Txt) {
        this.region2Txt = region2Txt;
    }

    public String getRegion2Txt() {
        return region2Txt;
    }

    public void setRegion3Txt(String region3Txt) {
        this.region3Txt = region3Txt;
    }

    public String getRegion3Txt() {
        return region3Txt;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setQaTime(BigDecimal qaTime) {
        this.qaTime = qaTime;
    }

    public BigDecimal getQaTime() {
        return qaTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

	public PmUser getUser() {
		return user;
	}

	public void setUser(PmUser user) {
		this.user = user;
	}
}

package com.wxpt.site.entity.pojo;

import java.util.Date;


/**
 * Member entity. @author MyEclipse Persistence Tools
 */

public class Member2  implements java.io.Serializable {


    // Fields    

     private Integer memberId;
     public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getMemberGrade() {
		return memberGrade;
	}


	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}


	public String getMemberFreeze() {
		return memberFreeze;
	}


	public void setMemberFreeze(String memberFreeze) {
		this.memberFreeze = memberFreeze;
	}

	public String getAddTime() {
		return addTime;
	}


	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String cardId;
     private String password;
     private String weixinId;
     private String username;
     private String sex;
     private String age;
     private String addTime;
     private String endTime;
     private String address;
     private String phone;
     private String memberPoints;
     private String saveMoney;
     private String memberGrade;
     private String memberFreeze;


    // Constructors


    
    /** full constructor */

   
    // Property accessors

    public Integer getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCardId() {
        return this.cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeixinId() {
        return this.weixinId;
    }
    
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }



    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemberPoints() {
        return this.memberPoints;
    }
    
    public void setMemberPoints(String memberPoints) {
        this.memberPoints = memberPoints;
    }

    public String getSaveMoney() {
        return this.saveMoney;
    }
    
    public void setSaveMoney(String saveMoney) {
        this.saveMoney = saveMoney;
    }









}
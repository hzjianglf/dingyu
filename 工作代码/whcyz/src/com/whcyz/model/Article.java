package com.whcyz.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
/**
 * 文章
 * @author 牟孟孟
 *
 */
public class Article extends Model<Article> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="article";
	public static Article dao=new Article();
	/**
	 * 文章类型定义 创业故事
	 */
	public static final int CATEGORY_STORY=1;
	/**
	 * 文章类型定义创业项目
	 */
	public static final int CATEGORY_FINANCING=2;
	/**
	 * 文章类型定义 创业视点
	 */
	public static final int CATEGORY_VIEWPOINT=3;
	/**
	 * 文章类型定义 活动交流
	 */
	public static final int CATEGORY_COMMUNICATION=4;
	public Integer getId(){
		return this.get("id");
	}
	public Integer getCategoryId(){
		return this.get("categoryId");
	}
	public String getTitle(){
		return this.get("title");
	}
	
	public String getAuthor(){
		return this.get("author");
	}
	public void setAuthor(String author){
		this.set("author",author);
	}
	public Date getPostTime(){
		return this.getDate("postTime");
	}
	public Date getLastUpdateTime(){
		return this.getDate("lastUpdateTime");
	}
	public void setPostTime(Date postTime){
		this.set("postTime", postTime);
	}
	public void setLastUpdateTime(Date lastUpdateTime){
		this.set("lastUpdateTime", lastUpdateTime);
	}
	public void setAccountId(Integer accountId) {
		this.set("accountId", accountId);
	}
	public void setAccountName(String accountName) {
		this.set("accountName", accountName);
	}
	public void setLastAccountId(Integer accountId) {
		this.set("lastAccountId", accountId);
	}
	public void setLastAccountName(String accountName) {
		this.set("lastAccountName", accountName);
	}
}

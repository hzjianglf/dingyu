package com.whcyz.model;

import com.jfinal.plugin.activerecord.Model;
/**
 * 讨论回复
 * @author 牟孟孟
 *
 */
public class Comment extends Model<Comment> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="comment";
	public static Comment dao=new Comment();
	public Integer getId(){
		return this.get("id");
	}
	public Integer getForId(){
		return this.get("forId");
	}
	public Integer getArticleId(){
		return this.get("articleId");
	}
	public Integer getAccountId(){
		return this.get("accountId");
	}
	public String getAccountName(){
		return this.get("accountName");
	}
}

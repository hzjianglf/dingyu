package com.whcyz.service.impl;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.whcyz.model.Comment;
import com.whcyz.util.SqlKeyword;

public class CommentService extends BaseServiceImpl {
	public static CommentService me=new CommentService();
	@Override
	public String table() {
		return Comment.TABLE;
	}

	@Override
	public Model<Comment> dao() {
		return Comment.dao;
	}

	public List<Comment> getArticleComments(Integer articleId) {
		return getList(SqlKeyword.ALL, "articleId=?", articleId);
	}

	public boolean deleteComment(Integer id, Integer accountId) {
		return delete("id=? and accountId=?", id,accountId)>=1;
	}

}

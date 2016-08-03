package com.whcyz.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.whcyz.model.Account;
import com.whcyz.model.Article;
import com.whcyz.model.Comment;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.ArticleService;
import com.whcyz.service.impl.CommentService;

/**
 * 文章回复评论controller
 * @author 牟孟孟
 *
 */
@Before(PermissionInterceptor.class)
public class CommentController extends BaseController {
	@Before(NoUrlPara.class)
	@UnCheck
	public void index() {

	}
	@Permission(Account.PERMISSION_ALL)
	@Before(Tx.class)
	public void add(){
		String content=getPara("content");
		if(StrKit.isBlank(content)){
			renderJsonFail("请输入评论内容！");
			return;
		}
		content=content.replace("<script>", "").replace("</script>", "");
		Integer articleId=getParaToInt("articleId");
		if(articleId==null){
			renderJsonFail("评论失败，参数错误！");
			return;
		}
		Article article=ArticleService.me.findById(articleId, "id");
		if(article==null){
			renderJsonFail("评论失败，参数错误！");
			return;
		}
		Comment comment=new Comment();
		comment.set("postTime", new Date());
		comment.set("articleId", articleId);
		comment.set("accountId", getSessionAccountId());
		comment.set("accountName", getSessionAccountNickname());
		Integer forId=getParaToInt("forId");
		if(forId!=null&&forId>0){
			Comment forComment=CommentService.me.findById(forId, "id,accountId,accountName");
			if(forComment!=null){
				comment.set("forId", forId);
				comment.set("content", "<span class='t-blue ataccount' accountId='"+forComment.getAccountId()+"'> @"+forComment.getAccountName()+" </span> "+content);
			}
		}else{
			comment.set("content", content);
		}
		boolean success=CommentService.me.save(comment);
		if(!success){
			renderJsonFail("评论保存失败！");
			return;
		}
		success=ArticleService.me.addOneArticleCommentCount(articleId);
		if(success){
			renderJsonData(true, comment);
		}else{
			renderJsonFail("修改评论数据失败，请重试！");
		}
		return;
	}
	/**
	 * 谁都有权删除自己的评论 管理员和编辑可以删除其他人的
	 */
	@Permission(Account.PERMISSION_ALL)
	@Before(Tx.class)
	public void delete(){
		if(getParamModelId()==null){
			renderError(404);
		}
		Comment comment=CommentService.me.findById(getParamModelId());
		if(comment==null){
			renderJsonFail("删除失败,数据不存在！");
			return;
		}
		Integer articleId=comment.getArticleId();
		if(isAdmin()||isEditor()){
			boolean success=comment.delete();
			if(success){
				success=ArticleService.me.minusOneArticleCommentCount(articleId);
				if(success){
					renderJsonSuccess();
				}else{
					renderJsonFail("修改评论数据失败，请重试！");
				}
				return;
			}else{
				renderJsonFail("删除失败");
			}
			return;
		}else if(isNormal()){
			boolean success=CommentService.me.deleteComment(getParamModelId(),getSessionAccountId());
			if(success){
				success=ArticleService.me.minusOneArticleCommentCount(articleId);
				if(success){
					renderJsonSuccess();
				}else{
					renderJsonFail("修改评论数据失败，请重试！");
				}
				return;
			}else{
				renderJsonFail("删除失败!");
			}
			return;
		}
	}
	/**
	 * 读取一个article的所有所有评论数据
	 */
	@UnCheck
	public void articles(){
		Integer articleId=getParaToInt();
		if(articleId==null){
			renderJsonFail("读取评论失败，参数错误！");
			return;
		}
		List<Comment> commnets=CommentService.me.getArticleComments(articleId);
		renderJsonData(true, commnets);
	}
}

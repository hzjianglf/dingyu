package com.whcyz.controller;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.model.Account;
import com.whcyz.model.Article;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.ArticleService;
import com.whcyz.util.HtmlRegexpUtil;
/**
 * 文章controller
 * @author 牟孟孟
 *
 */
@Before(PermissionInterceptor.class)
public class ArticleController extends BaseController {
	/**
	 * 首页
	 */
	@UnCheck
	@Before(NoUrlPara.class)
	public void index() {
		Integer category=getParaToInt("category",0);
		setAttr("category", category);
		Page<Article> articlePage=ArticleService.me.getListByPage(category,getParaToInt("pageNumber",1));
		setAttr("articleInfo", articlePage);
		render("index.jsp");
	}
	@UnCheck
	public void page() {
		Integer category=getParaToInt(0,0);
		setAttr("category", category);
		Page<Article> articlePage=ArticleService.me.getListByPage(category,getParaToInt(1,1));
		setAttr("articleInfo", articlePage);
		render("index.jsp");
	}
	@UnCheck
	public void hot(){
		List<Article> articles=ArticleService.me.getHotList(10);
		if(articles==null||articles.size()==0){
			renderJsonFail();
			return;
		}
		renderJsonData(true, articles);
	}
	@UnCheck
	public void addreadcount(){
		Integer articleId=getParaToInt();
		if(articleId!=null&&articleId>0){
			boolean success=ArticleService.me.addOneArticleReadCount(articleId);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("增加阅读数失败！");
			}
			return;
		}
	}
	@UnCheck
	public void mdetail(){
		Integer category=getParaToInt(0);
		Integer articleId=getParaToInt(1);
		if(category==0||articleId==0){
			renderHtml("<center><h2>网页不存在</h2></center>");
		}else{
			Article article=ArticleService.me.findById(articleId);
			if(article==null){
				renderHtml("<center><h2>网页不存在</h2></center>");
			}else{
			setAttr("article", article);
			setAttr("category", category);
			render("mdetail.jsp");
			}
		}
		
	}
	@UnCheck
	public void detail(){
		System.out.println(getRequest().getHeader("User-Agent"));
		Integer category=getParaToInt(0);
		Integer articleId=getParaToInt(1);
		if(category==0||articleId==0){
			renderError(404);
		}
		Article article=ArticleService.me.findById(articleId);
		if(article==null){
			renderError(404);
		}
		setAttr("article", article);
		setAttr("category", category);
		setAttr("account",getSessionAccount());
		setAttr("relPersonId", article.getInt("relPersonId"));
		setAttr("relCompanyId", article.getInt("relCompanyId"));
		render("detail.jsp");
	}
	@ActionKey("articlemgr")
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_NORMAL,Account.PERMISSION_EDITOR})
	public void list(){
		render("list.jsp");
	}
	/**
	 * 进入编辑页面
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void edit(){
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","simpleupdate");
			Article article= ArticleService.me.findById(id,"id,title,category,postTime,author,relPersonId,relPersonName,relCompanyId,relCompanyName");
			if(article==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("article",article);
			}
		}else{
			setAttr("action","add");
		}
		render("edit.jsp");
	}
	/**
	 * 进入编辑页面 简介
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void editContent(){
		setAttr("category", getPara("category", "0"));
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","update");
			Article article= ArticleService.me.findById(id);
			if(article==null){
				setErrorMsg("数据不存在！");
			}else{
				String content=article.getStr("content");
				if(StrKit.notBlank(content)){
					Document doc = Jsoup.parse(content);
					Elements imgs=doc.getElementsByTag("img");
					for(Element img:imgs){
						if(img.hasClass("lazy")&&img.hasAttr("data-original")){
							img.attr("src",img.attr("data-original")).removeClass("lazy").removeAttr("data-original");
						}
					}
					content=doc.toString();
					article.set("content",content);
				}
				setAttr("article",article);
			}
		}else{
			setAttr("action","add");
		}
		render("editcontent.jsp");
	}
	
	/**
	 * 新增保存
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void add(){
		Article article=getModel(Article.class, "article");
		if(article==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			article.setAccountId(getSessionAccountId());
			article.setAccountName(getSessionAccountNickname());
			processArticle(article);
			boolean success=ArticleService.me.save(article);
			if(success){
				renderJsonData(true,article.getId());
			}else{
				renderJsonFail("新增文章失败,请重试!");
			}
		}
	
	}
	/**
	 * 处理内容 article的
	 * @param article
	 */
	private void processArticle(Article article) {
		String content=article.getStr("content");
		if(StrKit.isBlank(content)){
			renderJsonFail("请输入文章内容！");
			return;
		}
		if(StrKit.isBlank(article.getAuthor())){
			article.setAuthor(getSessionAccountNickname());
		}
		if(article.getPostTime()==null){
			article.setPostTime(new Date());
		}
		article.setLastUpdateTime(new Date());
		article.setLastAccountId(getSessionAccountId());
		article.setLastAccountName(getSessionAccountNickname());
		String smcontent=article.getStr("smcontent");
		if(StrKit.isBlank(smcontent)){
			String c=HtmlRegexpUtil.filterHtml(content);
			if(c.length()>100){
				article.set("smcontent",c.substring(0, 100));
			}else{
				article.set("smcontent",c);
			}
		}
		String imgUrl=article.getStr("imgUrl");
		if(StrKit.isBlank(imgUrl)||imgUrl.indexOf("articledefaultimg.png")!=-1){
			article.set("imgUrl","assets/css/imgs/news.png");
		}
		
		Document doc = Jsoup.parse(content);
		Elements imgs=doc.getElementsByTag("img");
		for(Element img:imgs){
			if(img.hasAttr("src")&&StrKit.notBlank(img.attr("src"))){
				img.attr("data-original",img.attr("src")).addClass("lazy").attr("src", "assets/css/imgs/news.png")
				.attr("alt",article.getTitle()).attr("title",article.getTitle());
			}
		}
		content=doc.toString();
		article.set("content",content);
	}
	/**
	 * 简单更新
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void simpleupdate(){
		Article article=getModel(Article.class, "article");
		if(article==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			if(StrKit.isBlank(article.getAuthor())){
				article.setAuthor(getSessionAccountNickname());
			}
			if(article.getPostTime()==null){
				article.setPostTime(new Date());
			}
			article.setLastUpdateTime(new Date());
			article.setLastAccountId(getSessionAccountId());
			article.setLastAccountName(getSessionAccountNickname());
			boolean success=ArticleService.me.update(article);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改文章信息失败,请重试!");
			}
		}
	}
	/**
	 * 全部更新
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void update(){
		Article article=getModel(Article.class, "article");
		if(article==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			processArticle(article);
			boolean success=ArticleService.me.update(article);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改文章信息失败,请重试!");
			}
		}
	}
	/**
	 * 删除
	 */
	@Permission({Account.PERMISSION_ADMIN})
	public void delete(){
		Integer id=getParamModelId();
		if(id==null||id==0){
			renderJsonFail("数据不存在!");
		}
		boolean success=ArticleService.me.deleteById(id);
		if(success){
			renderJsonSuccess();
			
		}else{
			renderJsonFail("删除失败，请重试！");
		}
	}
	
	/**
	 * ajax获取数据
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void ajax(){
		renderJson(ArticleService.me.getListByPage(this));
	}
}

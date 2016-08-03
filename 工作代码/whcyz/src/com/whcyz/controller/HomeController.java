package com.whcyz.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.model.Article;
import com.whcyz.service.impl.ArticleService;

/**
 * 网站前台首页
 * @author 牟孟孟
 *
 */
public class HomeController extends BaseController {
	@Before(NoUrlPara.class)
	public void index(){
		List<Article> articles=ArticleService.me.getWebHomeArticles(15);
		setAttr("articles",articles);
		render("index1.jsp");
	}
	public void home(){
		List<Article> articles=ArticleService.me.getWebHomeArticles(15);
		setAttr("articles",articles);
		render("index1.jsp");
	}
	public void m(){
		List<Article> articles=ArticleService.me.getWebHomeArticles(15);
		setAttr("articles",articles);
		render("m.jsp");
	}
	@ActionKey("search")
	public void search(){
		String keywords=getPara("q","");
		Integer pageNumber=getParaToInt("p",1);
		Page<Article> articlePage=ArticleService.me.searchListByPage(keywords, pageNumber);
		setAttr("articleInfo",articlePage);
		setAttr("q",keywords);
		render("search.jsp");
	}
	
}

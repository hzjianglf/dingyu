package com.whcyz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.ArticleController;
import com.whcyz.model.Article;
import com.whcyz.model.Company;
import com.whcyz.util.SqlKeyword;

public class ArticleService extends BaseServiceImpl {
	public static ArticleService me=new ArticleService();
	@Override
	public String table() {
		return Article.TABLE;
	}

	@Override
	public Model<Article> dao() {
		return Article.dao;
	}
	public Map<String, Object> getListByPage(ArticleController c) {
		Map<String, Object> m = new HashMap<String, Object>();
		Page<Company> companyList = new Page<Company>(null, 0, 0, 0, 0);
		String sSearch = c.getPara("sSearch");					//查询内容
		String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
		int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
		String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
		int length = c.getParaToInt("iDisplayLength");			//查询长度
		int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
		String where = "";
		int category=c.getParaToInt("category",0);
		if(category>0){
			where+="category="+category;
			if(StrKit.notBlank(sSearch)){
				where += " and (title like '%"+sSearch+"%' or smcontent like '%"+sSearch+"%' or author like '%"+sSearch+"%')";
			}
		}else{
			if(StrKit.notBlank(sSearch)){
				where = " (title like '%"+sSearch+"%' or smcontent like '%"+sSearch+"%' or author like '%"+sSearch+"%')";
			}
		}
		
		companyList = (Page<Company>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort," id,category,author,postTime,readCount,commentCount,title ", where);
		m.put("iTotalDisplayRecords", companyList.getTotalRow());
		m.put("iTotalRecords", companyList.getTotalRow());
		m.put("data", companyList.getList());
		return m;
	}
	
	public Page<Article> getListByPage(Integer category, Integer pageNumber) {
		if(pageNumber==null||pageNumber==0){
			pageNumber=1;
		}
		if(category>0){
			return (Page<Article>) paginateWithExtras(pageNumber, 15, "id,category,author,postTime,content,smcontent,readCount,commentCount,title,imgUrl", "category=?",SqlKeyword.ORDER_BY+" postTime desc ", category);
		}else{
			return (Page<Article>) paginateWithExtras(pageNumber, 15, "id,category,author,postTime,content,smcontent,readCount,commentCount,title,imgUrl",SqlKeyword.ORDER_BY+" postTime desc ");
		}
		
	}

	public List<Article> getHotList(int count) {
		return getListWithExtras("id,category,title", null, SqlKeyword.ORDER_BY+"commentCount desc,readCount desc,postTime desc limit 0,"+count);
	}

	public boolean addOneArticleCommentCount(Integer articleId) {
		return updateWithSet("commentCount=commentCount+1", "id=?", articleId)>=1;
	}

	public List<Article> getWebHomeArticles(int count) {
		return getListWithExtras("id,category,title,postTime,author,smcontent,imgUrl", null, SqlKeyword.ORDER_BY+" postTime desc limit 0,"+count);
	}

	public boolean addOneArticleReadCount(Integer articleId) {
		return updateWithSet("readCount=readCount+1", "id=?", articleId)>=1;
	}

	public Page<Article> searchListByPage(String keywords, Integer pageNumber) {
		if(StrKit.isBlank(keywords)){
			return (Page<Article>) paginateWithExtras(pageNumber, 15,"id,category,author,postTime,content,smcontent,readCount,commentCount,title,imgUrl"," order by postTime desc" );
		}
		return (Page<Article>) paginateWithExtras(pageNumber, 15,"id,category,author,postTime,content,smcontent,readCount,commentCount,title,imgUrl"," title like '%"+keywords+"%' or content like '%"+keywords+"%' " ," order by postTime desc" );
	}

	public boolean minusOneArticleCommentCount(Integer articleId) {
		return updateWithSet("commentCount=commentCount-1", "id=?", articleId)>=1;
	}


}

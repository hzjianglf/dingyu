package com.whcyz.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.LinkController;
import com.whcyz.model.Link;
import com.whcyz.util.SqlKeyword;

public class LinkService extends BaseServiceImpl {
	public static LinkService me=new LinkService();
	@Override
	public String table() {
		return Link.TABLE;
	}

	@Override
	public Model<Link> dao() {
		return Link.dao;
	}

	public Map<String, Object> getListByPage(LinkController c) {
		Map<String, Object> m = new HashMap<String, Object>();
		Page<Link> linkList = new Page<Link>(null, 0, 0, 0, 0);
		String sSearch = c.getPara("sSearch");					//查询内容
		String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
		int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
		String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
		int length = c.getParaToInt("iDisplayLength");			//查询长度
		int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
		String where = "";
		if(StrKit.notBlank(sSearch)){
			where = " name like '%"+sSearch+"%' ";
		}
		linkList = (Page<Link>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort, SqlKeyword.ALL, where);
		m.put("iTotalDisplayRecords", linkList.getTotalRow());
		m.put("iTotalRecords", linkList.getTotalRow());
		m.put("data", linkList.getList());
		return m;
	}

}

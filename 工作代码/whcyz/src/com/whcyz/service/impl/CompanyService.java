package com.whcyz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.CompanyController;
import com.whcyz.model.Company;
import com.whcyz.util.SqlKeyword;

public class CompanyService extends BaseServiceImpl {
	public static CompanyService me=new CompanyService();
	@Override
	public String table() {
		return Company.TABLE;
	}

	@Override
	public Model<Company> dao() {
		return Company.dao;
	}
	public List<Company> getListByPyIndex(String py) {
		return getList("id,name,imgUrl", "pinyinFirst=?", py.trim().toLowerCase());
	}

	public Map<String, Object> getListByPage(CompanyController c) {
		Map<String, Object> m = new HashMap<String, Object>();
		Page<Company> companyList = new Page<Company>(null, 0, 0, 0, 0);
		String sSearch = c.getPara("sSearch");					//查询内容
		String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
		int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
		String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
		int length = c.getParaToInt("iDisplayLength");			//查询长度
		int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
		String where = "";
		if(StrKit.notBlank(sSearch)){
			where = " (name like '%"+sSearch+"%' or industry like '%"+sSearch+"%' or stage like '%"+sSearch+"%')";
		}
		companyList = (Page<Company>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort,"id,name,imgUrl,stage,rank,website,industry,foundTime,address", where);
		m.put("iTotalDisplayRecords", companyList.getTotalRow());
		m.put("iTotalRecords", companyList.getTotalRow());
		m.put("data", companyList.getList());
		return m;
	}



}

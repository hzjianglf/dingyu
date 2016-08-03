package com.whcyz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.PersonController;
import com.whcyz.model.Company;
import com.whcyz.model.Person;
import com.whcyz.util.SqlKeyword;

public class PersonService extends BaseServiceImpl {
	public static PersonService me=new PersonService();
	@Override
	public String table() {
		return Person.TABLE;
	}

	@Override
	public Model<Person> dao() {
		return Person.dao;
	}
	public Map<String, Object> getListByPage(PersonController c) {
		Map<String, Object> m = new HashMap<String, Object>();
		Page<Person> personList = new Page<Person>(null, 0, 0, 0, 0);
		String sSearch = c.getPara("sSearch");					//查询内容
		String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
		int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
		String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
		int length = c.getParaToInt("iDisplayLength");			//查询长度
		int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
		String where = "";
		if(StrKit.notBlank(sSearch)){
			where = " (name like '%"+sSearch+"%' or industry like '%"+sSearch+"%' or sex like '%"+sSearch+"%' or jobTitle like '%"+sSearch+"%')";
		}
		personList = (Page<Person>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort, "id,name,sex,website,companyId,companyName,jobTitle,foundTime,rank,frontshow,address", where);
		m.put("iTotalDisplayRecords", personList.getTotalRow());
		m.put("iTotalRecords", personList.getTotalRow());
		m.put("data", personList.getList());
		return m;
	}

	public List<Person> getHotList(int count) {
		return getListWithExtras("id,name", "frontshow=true", SqlKeyword.ORDER_BY+"rank asc,foundTime desc limit 0,"+count);
	}
	public List<Person> getListByPyIndex(String py) {
		return getList("id,name,imgUrl", "pinyinFirst=?", py.trim().toLowerCase());
	}

}

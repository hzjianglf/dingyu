package com.whcyz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.StationController;
import com.whcyz.model.Person;
import com.whcyz.model.Station;
import com.whcyz.util.SqlKeyword;

public class StationService extends BaseServiceImpl{
	public static StationService me=new StationService();
	@Override
	public String table() {
		return Station.TABLE;
	}

	@Override
	public Model<Station> dao() {
		return Station.dao;
	}
	public Map<String, Object> getListByPage(StationController c) {
		Map<String, Object> m = new HashMap<String, Object>();
		Page<Station> stationList = new Page<Station>(null, 0, 0, 0, 0);
		String sSearch = c.getPara("sSearch");					//查询内容
		String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
		int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
		String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
		int length = c.getParaToInt("iDisplayLength");			//查询长度
		int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
		String where = "";
		if(StrKit.notBlank(sSearch)){
			where = " (c_name like '%"+sSearch+"%')";
		}
		stationList = (Page<Station>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort, "n_id,c_name", where);
		m.put("iTotalDisplayRecords", stationList.getTotalRow());
		m.put("iTotalRecords", stationList.getTotalRow());
		m.put("data", stationList.getList());
		return m;
	}

	
	public List<Station> getListByPyIndex(String py) {
		return getList("nid,c_name", py.trim().toLowerCase());
	}

}



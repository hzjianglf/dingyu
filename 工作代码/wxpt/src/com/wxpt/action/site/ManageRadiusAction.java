package com.wxpt.action.site;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.dao.RadiusDao;
import com.wxpt.site.entity.Radius;
import com.wxpt.site.service.RadiusService;
import com.wxpt.site.service.UserService;



@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "radius", type = "json", params = { "root",
		"result" }) })
public class ManageRadiusAction extends ParentAction {
	private int radiusContent;
	@Autowired
	UserService userService;
	@Autowired
	RadiusService radiusService;
	@Autowired
	RadiusDao radiusDao;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	

	JSONArray jsonls;
	int count;// 总记录数
	int page;
	int rows;

	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	List<Radius> indList;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	
		try {
			indList = radiusService.getRadiusList(enterId,page,rows);
			JsonConfig jsonConfig = new JsonConfig();
			jsonls = JSONArray.fromObject(indList,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = radiusService.getRadiusCount(enterId);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "radius";
	}
	private int radiusId;
	private String centerName;
	public String update(){
		Radius radius = radiusService.getRadiusByID(enterId,radiusId);
		radius.setRadiusContent(radiusContent);
		radius.setCenterName(centerName);
		radiusDao.updateRadius(enterId, radius);
		return "radius";
	}
	
	
	

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getRadiusContent() {
		return radiusContent;
	}

	public void setRadiusContent(int radiusContent) {
		this.radiusContent = radiusContent;
	}

	public int getRadiusId() {
		return radiusId;
	}

	public void setRadiusId(int radiusId) {
		this.radiusId = radiusId;
	}

	public RadiusDao getRadiusDao() {
		return radiusDao;
	}

	public void setRadiusDao(RadiusDao radiusDao) {
		this.radiusDao = radiusDao;
	}

}

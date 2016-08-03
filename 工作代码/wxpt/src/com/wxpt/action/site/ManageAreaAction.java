package com.wxpt.action.site;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.GetCurrentUser;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.AreaDao;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.User;
import com.wxpt.site.service.AreaService;
import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.UserService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "area", type = "json", params = { "root",
		"result" }) })
public class ManageAreaAction extends ParentAction {
	private String areaName;
	private int userId;
	private ManageUser manageUser;
	@Autowired
	UserService userService;
	@Autowired
	AreaService areaService;
	@Autowired
	AreaDao areaDao;
	private Double locationX;
	private Double locationY;
	private String areaNo;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	public String add() {
		Area area = new Area();
		area.setAreaName(areaName);
		area.setAddTime(TimeUtil.getTime());
		area.setUpdateTime(TimeUtil.getTime());
		userId = GetCurrentUser.getUserID();
		manageUser = userService.getManageUserById(enterId, userId);
		area.setUpdateUser(userId);
		area.setAreaNo(areaNo);
		area.setLocationX(locationX+"");
		area.setLocationY(locationY+"");
		areaService.addArea(enterId,area);
		return "area";
		
	}

	JSONArray jsonls;
	int count;// 总记录数
	int page;
	int rows;

	
	
	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

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

	List<Area> indList;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(page);
		int page=0;
		indList = areaService.getAreaList(enterId,page,rows);
		System.out.println(indList.size());
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "manageUser","customerses" });
			jsonls = JSONArray.fromObject(indList,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = areaService.getAreaCount(enterId);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "area";
	}
	private int areaId;
	public String update(){
		Area area = areaService.getAreaByID(enterId,areaId);
		area.setAreaName(areaName);
		area.setAreaNo(areaNo);
		area.setUpdateTime(TimeUtil.getTime());
		area.setUpdateUser(GetCurrentUser.getUserID());
		area.setLocationX(locationX+"");
		area.setLocationY(locationY+"");
		areaDao.updateArea(enterId, area);
		return "area";
	}
	@Autowired
	CustomersService customersService;
	public String delete(){
		System.out.println(areaName);
		String[] ids = areaName.split(",");
		for (int i = 0; i < ids.length; i++) {
			List<Customers> l = customersService.getCustomersByAreaId(enterId,Integer.parseInt(ids[i]));
			if(l.size()==0){
				areaService.deleteById(enterId,Integer.parseInt(ids[i]));
			}
		}
		
		return "area";
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

}

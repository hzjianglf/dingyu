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
import com.wxpt.site.dao.CantonDao;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.service.CantonService;
import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.UserService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "canton", type = "json", params = { "root",
		"result" }) })
public class ManageCantonAction extends ParentAction{
	private String cantonName;
	private int userId;
	private ManageUser manageUser;
	@Autowired
	UserService userService;
	@Autowired
	CantonService cantonService;
	@Autowired
	CantonDao cantonDao;
	private String cantonNo;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	public String add() {
		Canton canton = new Canton();
		canton.setCantonName(cantonName);
		canton.setAddTime(TimeUtil.getTime());
		canton.setUpdateTime(TimeUtil.getTime());
		userId = GetCurrentUser.getUserID();
		manageUser = userService.getManageUserById(enterId, userId);
		canton.setUpdateUser(userId);
		canton.setCantonNo(cantonNo);
		cantonService.addCanton(enterId, canton);
		return "canton";
	}

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

	List<Canton> indList;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			indList = cantonService.getCantonList(enterId,page,rows);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "manageUser","customerses" });
			jsonls = JSONArray.fromObject(indList,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = cantonService.getCantonCount(enterId);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "canton";
	}
	private int cantonId;
	public String update(){
		Canton canton = cantonService.getCantonByID(enterId,cantonId);
		canton.setCantonName(cantonName);
		canton.setUpdateTime(TimeUtil.getTime());
		canton.setUpdateUser(GetCurrentUser.getUserID());
		canton.setCantonNo(cantonNo);
		cantonDao.updateCanton(enterId, canton);
		return "canton";
	}
	@Autowired
	CustomersService customersService;
	public String delete(){
		System.out.println(cantonName);
		String[] ids = cantonName.split(",");
		for (int i = 0; i < ids.length; i++) {
			List<Customers> l = customersService.getCustomersByCantonId(enterId,Integer.parseInt(ids[i]));
			if(l.size()==0){
				cantonService.deleteById(enterId,Integer.parseInt(ids[i]));
			}
		}
		return "area";
	}
	
	public String getCantonName() {
		return cantonName;
	}

	public void setCantonName(String cantonName) {
		this.cantonName = cantonName;
	}

	public int getCantonId() {
		return cantonId;
	}

	public void setCantonId(int cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonNo() {
		return cantonNo;
	}

	public void setCantonNo(String cantonNo) {
		this.cantonNo = cantonNo;
	}

	public CantonDao getCantonDao() {
		return cantonDao;
	}

	public void setCantonDao(CantonDao cantonDao) {
		this.cantonDao = cantonDao;
	}

}

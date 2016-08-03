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
import com.wxpt.site.dao.IndustryDao;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.entity.ManageUser;

import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.IndustryService;
import com.wxpt.site.service.UserService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "industry", type = "json", params = { "root",
		"result" }) })
public class ManageIndustryAction extends ParentAction {
	private String industryName;
	private int userId;
	private ManageUser manageruser;
	@Autowired
	UserService userService;
	@Autowired
	IndustryService industryService;
	@Autowired
	IndustryDao industryDao;
	private String industryNo;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	public String add() {
		Industry industry = new Industry();
		industry.setIndustryName(industryName);
		industry.setAddTime(TimeUtil.getTime());
		industry.setUpdateTime(TimeUtil.getTime());
		userId = GetCurrentUser.getUserID();
		manageruser = userService.getManageUserById(enterId, userId);
		industry.setUpdateUser(userId);
		industry.setIndustryNo(industryNo);
		industryService.addIndustry(enterId, industry);
		
		return "industry";
	}

	JSONArray jsonls;
	int count;// 总记录数
	int page;
	int rows;
	int pageCount;
	
	
	
	
	
	public String getIndustryNo() {
		return industryNo;
	}

	public void setIndustryNo(String industryNo) {
		this.industryNo = industryNo;
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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	List<Industry> indList;

	@Override
	public String execute() throws Exception {
	
		// TODO Auto-generated method stub
		indList = industryService.getIndustryList(enterId,page,rows);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "manageUser","customerses" });
			jsonls = JSONArray.fromObject(indList,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = industryService.getIndustryCount(enterId);
		//pageCount = (count+rows-1)/rows;
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "industry";
	}
	private int industryId;
	public String update(){
		Industry industry = industryService.getIndustryByID(enterId,industryId);
		industry.setIndustryName(industryName);
		industry.setUpdateTime(TimeUtil.getTime());
		industry.setUpdateUser(GetCurrentUser.getUserID());
		industry.setIndustryNo(industryNo);
		industryDao.updateIndustry(enterId, industry);
		return "industry";
	}
	
	
	@Autowired
	CustomersService customersService;
	public String delete(){
		System.out.println(industryName);
		String[] ids = industryName.split(",");
		for (int i = 0; i < ids.length; i++) {
			List<Customers> l = customersService.getCustomersByInId(enterId,Integer.parseInt(ids[i]));
			if(l.size()==0){
				industryService.deleteById( enterId,Integer.parseInt(ids[i]));
			}
		}
		
		return "industry";
	}
	
	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public IndustryDao getIndustryDao() {
		return industryDao;
	}

	public void setIndustryDao(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}

}

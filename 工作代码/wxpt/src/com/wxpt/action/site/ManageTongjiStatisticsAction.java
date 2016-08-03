package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.entity.Page;
import com.wxpt.site.entity.pojo.TongjiStaticsPojo;
import com.wxpt.site.service.WebSiteService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "page", location = "success.jsp") })
public class ManageTongjiStatisticsAction extends ParentAction {
	JSONArray jsonls;
	private int count;
	private int page;
	private int rows;
	private List<Page> pageList;
	private List<TongjiStaticsPojo> tongjiStaticsList;
	private String sql;
	@Autowired
	WebSiteService webSiteService;
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sql = "select * from wxpt" + super.getCookieByEnterID() + ".page";
		pageList = webSiteService.getPageLists(sql, page, rows);
		count = webSiteService.getCountBySql(sql);
		tongjiStaticsList = new ArrayList<TongjiStaticsPojo>();
		for (Page page : pageList) {
			TongjiStaticsPojo tjsp = new TongjiStaticsPojo();
			sql = "select distinct `forward_user_id` from wxpt" + super.getCookieByEnterID() + ".page_statistics where `page_id` = " + page.getPageId();
			tjsp.setForwardUserCount(webSiteService.getCountBySql(sql));
			tjsp.setPageTitle(page.getPageTitle());
			sql = "select * from wxpt" + super.getCookieByEnterID() + ".page_statistics where `page_id` = "+page.getPageId()+" and `forward_type` = 1 ";
			tjsp.setSendUserCount(webSiteService.getCountBySql(sql));
			sql = "select * from wxpt" + super.getCookieByEnterID() + ".page_statistics where `page_id` = "+page.getPageId()+" and `forward_type` = 2 ";
			tjsp.setSendFriendCount(webSiteService.getCountBySql(sql));
			sql = "select * from wxpt" + super.getCookieByEnterID() + ".page_statistics where `page_id` = "+page.getPageId()+" and `forward_type` = 3 ";
			tjsp.setSendWeiboCount(webSiteService.getCountBySql(sql));
			tongjiStaticsList.add(tjsp);
		}
		jsonls = JSONArray.fromObject(tongjiStaticsList);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
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
}

package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.site.entity.SiteStatistics;
import com.wxpt.site.service.StatistiesService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "linksinfo", type = "json", params = { "root",
		"result" }) })
public class BaiduAction extends ActionSupport {
	

	SiteStatistics siteStatistics;
	@Autowired
	StatistiesService statisticsService;
	JSONArray jsonls;
	String rows;// 每页显示的记录数
	String page;// 当前第几页
	int listCount; // 总数据条数
	int pageCount;// 总页数
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected PrintWriter out = null;
	private List<SiteStatistics> stList;
	int query = -1;
	int type = 1;
	String sql;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public BaiduAction() {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
	}
	public String getAll() throws Exception {
		try {
			this.PageList(page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		net.sf.json.JsonConfig jsonConfig = new net.sf.json.JsonConfig();
		jsonConfig.setExcludes(new String[] { "user" });
		jsonls = JSONArray.fromObject(stList, jsonConfig);
		System.out.println(jsonls.toString());
		out.print("{\"total\":" + listCount + ",\"rows\":" + jsonls + "}");
		out.flush();
		out.close();
		return "linksinfo";
	}

	String domain1;
	String domain2;

	public String getDom(){
		int userId = 0;
		try {
			userId = statisticsService.getUserId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户不存在");
		}
		
		String message = "[{\"id\":\"-1\",\"text\":\"全部\"}";
		message += ",{\"id\":\"0\",\"text\":\"www.ifinding.cn\"}";
		message +="]";
		out.print(message);
		out.flush();
		out.close();
		return "linksinfo";
	}
	
	public void PageList(String page, String rows) {
		int userId = 0;
		stList = new ArrayList<SiteStatistics>();
		try {
			userId = statisticsService.getUserId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("用户不存在");
		}
		if (type > 3) {
			sql="SELECT * FROM uniqyw.site_statistics where type="+type+" and user_id="+userId;
//			hql = "from SiteStatistics where " + " type=" + type;
		} else {
			sql="SELECT * FROM uniqyw.site_statistics where user_id="+userId;
//			hql = "from SiteStatistics where user.userId=" + userId
//					+ " and type=" + type;
		}
		if (query != -1) {
			if (query == 0) {
				sql += " and domain='www.ifinding.cn'";
			} else if (query == 1) {
				sql += " and domain='www.ifinding.cn'";
			}
			System.out.println(sql);

		}

		listCount = statisticsService.getTotalCount(sql);
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		stList = (List<SiteStatistics>)statisticsService.getAll(sql, start, number);
		// System.out.println(linkList.get(0).getUser().getUserName());
		if (listCount % number == 0) {
			pageCount = listCount / number;
		} else {
			pageCount = listCount / number + 1;
		}
		//
	}

	//
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getQuery() {
		return query;
	}

	public void setQuery(int query) {
		this.query = query;
	}

	public static void main(String[] args) {
		String url = "http://www.uniqyw.com/finad..";
		int start = -1;
		int end = -1;
		String domain = url
				.substring(url.indexOf(":") + 3, url.indexOf("/", 8));
		System.out.println(domain);
	}

	public String getDomain1() {
		return domain1;
	}

	public void setDomain1(String domain1) {
		this.domain1 = domain1;
	}

	public String getDomain2() {
		return domain2;
	}

	public void setDomain2(String domain2) {
		this.domain2 = domain2;
	}

	public StatistiesService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatistiesService statisticsService) {
		this.statisticsService = statisticsService;
	}

}

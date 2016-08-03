package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.PageStatistics;
import com.wxpt.site.entity.pojo.TongjiPojo;
import com.wxpt.site.service.MemberService;

import net.sf.json.JSONArray;
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "page", location = "success.jsp") })
public class ManageTongjiAction extends ParentAction{
	JSONArray jsonls;
	private int count;
	private int page;
	private int rows;
	private List<PageStatistics> pageStatisticList;
	private List<TongjiPojo> tongjiPojoList;
	private String sql;
	@Autowired
	MemberService memberService;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sql = "select * from wxpt" + super.getCookieByEnterID() + ".page_statistics";
		pageStatisticList = webSiteService.getpageStatisticLists(sql, page, rows);
		count = webSiteService.getCountBySql(sql);
		tongjiPojoList = new ArrayList<TongjiPojo>();
		for (PageStatistics pageStatistics : pageStatisticList) {
			Member member;
			try {
				member = memberService.findByWeiInId(
						super.getCookieByEnterID(),
						pageStatistics.getForwardUserId()).get(0);
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			String typeStr = "";
			if(pageStatistics.getForwardType()==1){
				typeStr = "转发个人";
			}
			if(pageStatistics.getForwardType()==2){
				typeStr = "分享朋友圈";
			}
			if(pageStatistics.getForwardType()==3){
				typeStr = "分享微博";
			}
			
			TongjiPojo tongji = new TongjiPojo(pageStatistics.getPageStatisticsId(), pageStatistics.getPageId(), pageStatistics.getForwardUserId(), pageStatistics.getForwardTime(), pageStatistics.getForwardType(), pageStatistics.getPageTitle(), pageStatistics.getForwardUrl(), typeStr, member.getWeixinHao(), member.getUsername());
			tongji.setPartName(member.getBumen());
			tongjiPojoList.add(tongji);
		}
		
		try {
			jsonls = JSONArray.fromObject(tongjiPojoList);
		} catch (Exception e) {
			// TODO: handle exception
		}
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

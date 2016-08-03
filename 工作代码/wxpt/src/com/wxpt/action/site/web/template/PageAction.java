package com.wxpt.action.site.web.template;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.action.site.ParentAction;
import com.wxpt.common.HttpRequest;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Page;
import com.wxpt.site.service.WebSiteService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "error", location = "/page/error.jsp"),
		@Result(name = "page", location = "/page/page.jsp")})
public class PageAction extends ParentAction {
	private int pageId;
	private int enterID;
	private Page page;
	@Autowired
	WebSiteService webSiteService;
	private String sql;
	private String message;
	private String fromUserId;
	private int type;
	private String pageContent;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sql = "select * from  wxpt"
				+ enterID
				+ ".page where `page_id` = " + pageId;
		try {
			page = webSiteService.getPageLists(sql, 1, 1).get(0);
			//pageContent = PageCommon.getPageStr(page.getPageJumpUrl());
		} catch (Exception e) {
			// TODO: handle exception
			message = "该文章已删除";
			return "error";
		}
		return "page";
	}

	public String getTest(){
		message = "友情提示：请用微信浏览器查看";
		return "error";
	}
	
	public String tongji(){
		sql = "select * from  wxpt"
				+ enterID
				+ ".page where `page_id` = " + pageId;
		try {
			page = webSiteService.getPageLists(sql, 1, 1).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			
		} 
		sql = "insert into wxpt"
				+ enterID
				+ ".page_statistics(`page_id`, `forward_user_id`, `forward_time`, `forward_type`, `page_title`,`forward_url`) VALUES("+page.getPageId()+",'"+fromUserId+"','"+TimeUtil.getTime()+"',"+type+",'"+page.getPageTitle()+"','"+page.getPageJumpUrl()+"')";
		webSiteService.executeSql(sql);
		return "success";
	}
	private String action_type;
	private String uin;
	private String __biz;
	private String appmsgid;
	private String itemidx;
	private String sign;
	public void wTongji(){
		System.out.println(HttpRequest.sendPost("http://mp.weixin.qq.com/mp/appmsg/show",
				"__biz="+__biz+"&appmsgid="+appmsgid+"&itemidx="+itemidx+"&sign="+sign+"&action_type="+action_type+"&uin="+uin));
	}
	
	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getEnterID() {
		return enterID;
	}

	public void setEnterID(int enterID) {
		this.enterID = enterID;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String get__biz() {
		return __biz;
	}

	public void set__biz(String __biz) {
		this.__biz = __biz;
	}

	public String getAppmsgid() {
		return appmsgid;
	}

	public void setAppmsgid(String appmsgid) {
		this.appmsgid = appmsgid;
	}

	public String getItemidx() {
		return itemidx;
	}

	public void setItemidx(String itemidx) {
		this.itemidx = itemidx;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	
}

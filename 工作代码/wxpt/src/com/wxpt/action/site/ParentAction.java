package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import com.wxpt.site.entity.TemplateMenuType;
import com.wxpt.site.entity.TemplatePageProperty;
import com.wxpt.site.entity.TemplateProperty;
import com.wxpt.site.entity.UserSiteMenu;
import com.wxpt.site.entity.UserSiteOption;
import com.wxpt.site.entity.UserSitePage;
import com.wxpt.site.entity.UserSitePagemeta;
import com.wxpt.site.entity.UserTemplate;
import com.wxpt.site.entity.pojo.Pageabout;
import com.wxpt.site.service.WebSiteService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "privile", type = "json", params = { "root", "result" }),
	@Result(name = "error", location = "error.html") })
public class ParentAction extends ActionSupport {

	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected PrintWriter out = null;
	private JSONArray jsonls;

	public ParentAction() {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
	}

	public int getCookieByEnterID() {
		GetCookie cookies = new GetCookie();
		int enterId2 = cookies.getCookie();
		return enterId2;
	}


	@Autowired
	WebSiteService webSiteService;

	public UserTemplate getEnterUserTemplate() {
		String sql = "select * from webchat.user_template where enter_infor_id = "
				+ this.getCookieByEnterID();
		UserTemplate userTemplate = webSiteService.getUserTemplate(sql);
		return userTemplate;
	}

	
	public TemplateProperty getEnterTemplateProperty() {
		UserTemplate userTemplate = this.getEnterUserTemplate();
		String sql = "select * from webchat.template_property where template_id = "
				+ userTemplate.getTemplates().getTemplateId();
		TemplateProperty templateProperty = webSiteService
				.getTemplateProperty(sql);
		return templateProperty;
	}

	public TemplateProperty getEnterTemplateProperty(int enterID) {
		UserTemplate userTemplate = this.getEnterUserTemplate(enterID);
		String sql = "select * from webchat.template_property where template_id = "
				+ userTemplate.getTemplates().getTemplateId();
		TemplateProperty templateProperty = webSiteService
				.getTemplateProperty(sql);
		return templateProperty;
	}
	
	public TemplateMenuType getEnterTemplateMenuType(int menuNameID) {
		UserTemplate userTemplate = this.getEnterUserTemplate();
		String sql = "select * from webchat.template_menu_type where template_id = "
				+ userTemplate.getTemplates().getTemplateId()
				+ " and template_type_menu_id = " + menuNameID;
		TemplateMenuType templateMenuType = webSiteService
				.getTemplateMenuType(sql);
		return templateMenuType;
	}

	public UserSiteMenu getUserSiteMenu(int menuId) {
		String sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_menu where menu_id = " + menuId;
		return webSiteService.getUserSiteMenuList(sql).get(0);
	}

	public void pagePrintList(int menuId, int menuNameID, int page, int rows) {
		TemplateMenuType templateMenuType = this
				.getEnterTemplateMenuType(menuNameID);
		String sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_page ";
		if (templateMenuType.getParseNum() == 0) {
			sql += "where page_menu = " + menuId;
		} else {
			sql += "where page_menu in ( select menu_id from  wxpt"
					+ this.getCookieByEnterID()
					+ ".user_site_menu where menu_parent = " + menuId + ")";
		}
		int count = webSiteService.getTemplatesCount(sql);
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, page, rows);
		List<Pageabout> pageAboutList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {

			Pageabout pageabout = new Pageabout();
			UserSiteMenu userSiteMenu = this.getUserSiteMenu(menuId);
			pageabout.setPageUrl(userSitePage.getPageUrl());
			pageabout.setPageMenu(menuId);
			pageabout.setMenuName(userSiteMenu.getMenuName());
			pageabout.setPageTitle(userSitePage.getPageTitle());
			pageabout.setPageId(userSitePage.getPageId());
			if (templateMenuType.getParseNum() == 1) {
				sql = "select * from wxpt" + this.getCookieByEnterID()
						+ ".user_site_menu  where menu_id = " + userSitePage.getUserSiteMenu().getMenuId();
				UserSiteMenu childMenu = webSiteService.getUserSiteMenuList(sql).get(0);
				pageabout.setChildMenuID(childMenu.getMenuId());
				
				pageabout.setChildMenuName(childMenu.getMenuName());
			}
			pageabout = this.setPageAboutValue(userSitePage.getPageId(), pageabout);
			
			pageAboutList.add(pageabout);
		}

		
		
		
		try {
			jsonls = JSONArray.fromObject(pageAboutList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");

		this.out.flush();
		this.out.close();
	}

	public Pageabout setPageAboutValue(int pageID,Pageabout pageabout){
		String sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_pagemeta where meta_page = "
				+ pageID
				+ " and meta_key = 'txt_editor_1' ";
		UserSitePagemeta userSitePagemeta = webSiteService
				.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_editor_1");
			pageabout.setMetaValue(userSitePagemeta.getMetaValue());

		}
		sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_pagemeta where meta_page = "
				+ pageID + " and meta_key = 'img_big' ";
		userSitePagemeta = webSiteService.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey1("img_big");
			pageabout.setMetaImageValue("<img src = \"../web/images/"
					+ this.getCookieByEnterID() + "/" +userSitePagemeta.getMetaValue()
					+ "\"  style=\"width:200px ; height:100px ;\" />");
		}
		sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_pagemeta where meta_page = "
				+ pageID + " and meta_key = 'txt_text' ";
		userSitePagemeta = webSiteService.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_text");
			pageabout.setMetaDetail(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_address");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_address");
			pageabout.setAddress(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_hotline");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_hotline");
			pageabout.setHotline(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_telephone");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_telephone");
			pageabout.setTelephone(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_landline");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_landline");
			pageabout.setLandline(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_localtion");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_localtion");
			pageabout.setLocaltion( "<img src = \"http://api.map.baidu.com/staticimage?center=" + userSitePagemeta.getMetaValue() + "&width=400&height=300&zoom=11&markers=" + userSitePagemeta.getMetaValue() + "&markerStyles=l,A\"  style=\"width:200px ; height:100px ;\" />");
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_email");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_emial");
			pageabout.setEmail(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "txt_contacts");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_contacts");
			pageabout.setContacts(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "msg_contacts");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_contacts");
			pageabout.setMsgContacts(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "msg_way");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_way");
			pageabout.setMsgWay(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				pageID, "msg_content");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_content");
			pageabout.setMsgContent(userSitePagemeta.getMetaValue());
		}
		return pageabout;
	}
	
	
	public TemplatePageProperty getEnterTemplatePageProperty(
			int templateTypeMenuId) {
		// TODO Auto-generated method stub
		String sql = "select * from webchat.template_page_property where template_id = "
				+ this.getEnterUserTemplate().getTemplates().getTemplateId()
				+ " and template_page_menu_id = " + templateTypeMenuId;
		TemplatePageProperty templatePageProperty = webSiteService
				.getTemplatePagePropery(sql);
		return templatePageProperty;
	}

	public TemplatePageProperty getEnterTemplatePageProperty(int enterID,
			int templateTypeMenuId) {
		// TODO Auto-generated method stub
		String sql = "select * from webchat.template_page_property where template_id = "
				+ this.getEnterUserTemplate(enterID).getTemplates().getTemplateId()
				+ " and template_menu_type_id = " + templateTypeMenuId;
		TemplatePageProperty templatePageProperty = webSiteService
				.getTemplatePagePropery(sql);
		return templatePageProperty;
	}
	
	public void outPrint(String message, int state) {
		this.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		this.out.flush();
		this.out.close();
	}

	public UserSitePagemeta getUserSitePagemeta(int pageID, String meta_key) {
		String sql = "select * from wxpt" + this.getCookieByEnterID()
				+ ".user_site_pagemeta where meta_page = " + pageID
				+ " and meta_key = '" + meta_key + "' ";
		UserSitePagemeta userSitePagemeta = webSiteService
				.getUserSitePageMetaList(sql);
		return userSitePagemeta;
	}

	public UserSitePagemeta getUserSitePagemeta(int enterID,int pageID, String meta_key) {
		String sql = "select * from wxpt" + enterID
				+ ".user_site_pagemeta where meta_page = " + pageID
				+ " and meta_key = '" + meta_key + "' ";
		UserSitePagemeta userSitePagemeta = webSiteService
				.getUserSitePageMetaList(sql);
		return userSitePagemeta;
	}
	
	public int updateUerSitePagemeta(int pageID, String meta_value,
			String meta_key) {
		String sql = "update wxpt" + this.getCookieByEnterID()
				+ ".user_site_pagemeta set meta_value = '" + meta_value
				+ "' where meta_page = " + pageID + " and meta_key = '"
				+ meta_key + "' ";
		int remark = webSiteService.executeSql(sql);
		return remark;
	}

	public int updateUserSitePage(int pageID, String pageTitle, String pageTime) {
		String sql = "update wxpt" + this.getCookieByEnterID()
				+ ".user_site_page set page_title = '" + pageTitle
				+ "', page_time = '" + pageTime + "' where page_id = " + pageID;
		int remark = webSiteService.executeSql(sql);
		return remark;
	}

	public int updateUserSitePage(int pageMenu,int pageID, String pageTitle, String pageTime) {
		String sql = "update wxpt" + this.getCookieByEnterID()
				+ ".user_site_page set page_title = '" + pageTitle
				+ "', page_time = '" + pageTime + "', page_menu = "+pageMenu+" where page_id = " + pageID;
		int remark = webSiteService.executeSql(sql);
		return remark;
	}
	
	public int addUserSitePagemeta(int pageID, String meta_key,
			String meta_value) {
		String sql = "insert into wxpt"
				+ this.getCookieByEnterID()
				+ ".user_site_pagemeta (meta_page,meta_key,meta_value) values('"
				+ pageID + "','" + meta_key + "','" + meta_value + "')";
		int remark = webSiteService.executeSql(sql);
		return remark;
	}
	public int addUserSitePagemeta(int enterID,int pageID, String meta_key,
			String meta_value) {
		String sql = "insert into wxpt"
				+ enterID
				+ ".user_site_pagemeta (meta_page,meta_key,meta_value) values('"
				+ pageID + "','" + meta_key + "','" + meta_value + "')";
		int remark = webSiteService.executeSql(sql);
		return remark;
	}
	public void addCookie(String name ,String value){
		Cookie wxpt = new Cookie(name,value);
		wxpt.setPath("/");
		ServletActionContext.getResponse().addCookie(
				wxpt);
	}
	public String getValue(String name){
		GetCookie cookies = new GetCookie();
		if(cookies.getCookieValue(name)==null){
			return "error";
		}else{
			return cookies.getCookieValue(name);
		}
		
	}
	
	public TemplateMenuType getEnterTemplateMenuType(int enterID,int menuNameID) {
		UserTemplate userTemplate = this.getEnterUserTemplate(enterID);
		String sql = "select * from webchat.template_menu_type where template_id = "
				+ userTemplate.getTemplates().getTemplateId()
				+ " and template_type_menu_id = " + menuNameID;
		TemplateMenuType templateMenuType = webSiteService
				.getTemplateMenuType(sql);
		return templateMenuType;
	}
	
	public UserTemplate getEnterUserTemplate(int enterID) {
		String sql = "select * from webchat.user_template where enter_infor_id = "
				+ enterID;
		UserTemplate userTemplate = webSiteService.getUserTemplate(sql);
		return userTemplate;
	}
	public List<UserSiteOption> getEnterSiteOption(int enterID,String key){
		String sql = "select * from wxpt" + enterID
				+ ".user_site_option where option_name = '"+key+"'";
		List<UserSiteOption> siteOptionList = webSiteService.getUserOptionList(sql);
		return siteOptionList;
	}
	
	public Pageabout setPageAboutValue(int enterID,int pageID,Pageabout pageabout){
		String sql = "select * from wxpt" + enterID
				+ ".user_site_pagemeta where meta_page = "
				+ pageID
				+ " and meta_key = 'txt_editor_1' ";
		UserSitePagemeta userSitePagemeta = webSiteService
				.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_editor_1");
			pageabout.setMetaValue(userSitePagemeta.getMetaValue());

		}
		sql = "select * from wxpt" + enterID
				+ ".user_site_pagemeta where meta_page = "
				+ pageID + " and meta_key = 'img_big' ";
		userSitePagemeta = webSiteService.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey1("img_big");
			pageabout.setMetaImageValue("<img src = \"../web/images/"
					+ enterID + "/" +userSitePagemeta.getMetaValue()
					+ "\"  style=\"width:200px ; height:100px ;\" />");
		}
		sql = "select * from wxpt" + enterID
				+ ".user_site_pagemeta where meta_page = "
				+ pageID + " and meta_key = 'txt_text' ";
		userSitePagemeta = webSiteService.getUserSitePageMetaList(sql);
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_text");
			pageabout.setMetaDetail(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_address");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_address");
			pageabout.setAddress(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_hotline");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_hotline");
			pageabout.setHotline(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_telephone");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_telephone");
			pageabout.setTelephone(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_landline");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_landline");
			pageabout.setLandline(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_localtion");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_localtion");
			pageabout.setLocaltion( "<img src = \"http://api.map.baidu.com/staticimage?center=" + userSitePagemeta.getMetaValue() + "&width=400&height=300&zoom=11&markers=" + userSitePagemeta.getMetaValue() + "&markerStyles=l,A\"  style=\"width:200px ; height:100px ;\" />");
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_email");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_emial");
			pageabout.setEmail(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "txt_contacts");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("txt_contacts");
			pageabout.setContacts(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "msg_contacts");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_contacts");
			pageabout.setContacts(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "msg_way");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_way");
			pageabout.setContacts(userSitePagemeta.getMetaValue());
		}
		userSitePagemeta = this.getUserSitePagemeta(
				enterID,pageID, "msg_content");
		if (userSitePagemeta != null) {
			pageabout.setMeatKey("msg_content");
			pageabout.setContacts(userSitePagemeta.getMetaValue());
		}
		return pageabout;
	}
	
	
}

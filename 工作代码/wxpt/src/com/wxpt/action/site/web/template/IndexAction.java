package com.wxpt.action.site.web.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.action.site.ParentAction;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.TemplateMenuType;
import com.wxpt.site.entity.TemplatePageProperty;
import com.wxpt.site.entity.TemplateProperty;
import com.wxpt.site.entity.UserSiteMenu;
import com.wxpt.site.entity.UserSiteOption;
import com.wxpt.site.entity.UserSitePage;
import com.wxpt.site.entity.UserSitePagemeta;
import com.wxpt.site.entity.UserTemplate;
import com.wxpt.site.entity.pojo.Pageabout;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.WebSiteService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "divienIndex", location = "/templates/divien/index.jsp"),
		@Result(name = "divienPage", location = "/templates/divien/about.jsp"),
		@Result(name = "divienMap", location = "/templates/divien/map.jsp"),
		@Result(name = "divienList", location = "/templates/divien/news.jsp"),
		@Result(name = "divienGuList", location = "/templates/divien/price.jsp"),
		@Result(name = "divienListPage", location = "/templates/divien/price1.jsp"),
		@Result(name = "divienMsg", location = "/templates/divien/message.jsp"),
		@Result(name = "divienShare", location = "/templates/divien/share.jsp"),
		@Result(name = "divienContacts", location = "/templates/divien/contant.jsp"),
		@Result(name = "yinzuoIndex", location = "/templates/yinzuo/index.jsp"),
		@Result(name = "yinzuoGuList", location = "/templates/yinzuo/xsms.jsp"),
		@Result(name = "yinzuoListPage", location = "/templates/yinzuo/xiangxi.jsp"),
		@Result(name = "yinzuoPage", location = "/templates/yinzuo/yzkb.jsp"),
		@Result(name = "yinzuoList", location = "/templates/yinzuo/yhq.jsp"),
		@Result(name = "yinzuoChildMenu", location = "/templates/yinzuo/fenlei.jsp"),
		@Result(name = "yinzuoLogin", location = "/templates/yinzuo/login.jsp"),
		@Result(name = "yinzuoSearch", location = "/templates/yinzuo/search.jsp"),
		@Result(name = "qiyeIndex", location = "/templates/qiye/index.jsp"),
		@Result(name = "qiyePage", location = "/templates/qiye/aboutus.jsp"),
		@Result(name = "qiyeList", location = "/templates/qiye/product.jsp"),
		@Result(name = "qiyeListPage", location = "/templates/qiye/product_page.jsp"),
		@Result(name = "qiyeContacts", location = "/templates/qiye/contant.jsp"),
		@Result(name = "wptIndex", location = "/templates/wpt/index.jsp"),
		@Result(name = "wptTwo", location = "/templates/wpt/contant.jsp"),
		@Result(name = "wptChildMenu", location = "/templates/wpt/tuiguang.jsp"),
		@Result(name = "wptFour", location = "/templates/wpt/lbs.jsp"),
		@Result(name = "wptFive", location = "/templates/wpt/aboutus.jsp"),
		@Result(name = "wptSix", location = "/templates/wpt/hezuo.jsp"),
		@Result(name = "wptOne", location = "/templates/wpt/success.jsp"),
		@Result(name = "wptThree", location = "/templates/wpt/product.jsp"),
		@Result(name = "cityinIndex", location = "/templates/cityin/index.jsp"),
		@Result(name = "cityinOne", location = "/templates/cityin/jingdian.jsp"),
		@Result(name = "cityinTwo", location = "/templates/cityin/jingdian.jsp"),
		@Result(name = "cityinThree", location = "/templates/cityin/food.jsp"),
		@Result(name = "cityinFour", location = "/templates/cityin/dingyue.jsp"),
		@Result(name = "cityinListPage", location = "/templates/cityin/page.jsp")})
public class IndexAction extends ParentAction {
	private int enterID;
	private List<UserSiteMenu> siteMenuList;
	@Autowired
	WebSiteService webSiteService;
	@Autowired
	EnterService enterService;

	private TemplatePageProperty templatePageProperty;
	private TemplateProperty templateProperty;
	private TemplateMenuType templateMenuType;
	private EnterInfor enterInfor;
	private UserTemplate userTemplate;
	private String templateName;
	private List<UserSiteOption> siteOptionList;
	private String logoName;
	private Pageabout contactUs;
	@SuppressWarnings("unused")
	private List<UserSitePage> sitePageList;
	private int menuID;
	private String menuName;
	private Pageabout pageAbout;
	
	public String index() {
		super.addCookie("wxptsite", enterID + "");
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		// banner logo 广告
		this.getFile();
		// 推荐栏目
		if (templateProperty.getRecomMenuNum() != 0) {
			this.tuijian();
		}
		this.enterInfo();
		return templateName+"Index";
	}

	private int menuNameID;
	private List<Pageabout> pageList;
	private int type;
	
	//
	public String getMenuPage() {
		this.getFile();
		this.enterInfo();
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_id = " + menuID;
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		menuName = siteMenuList.get(0).getMenuName();
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateMenuType = super.getEnterTemplateMenuType(enterID, menuNameID);
		templatePageProperty  = super.getEnterTemplatePageProperty(enterID,templateMenuType.getTemplateMenuTypeId());
		sql = "select * from wxpt" + enterID
				+ ".user_site_page ";
		//if (templateMenuType.getParseNum() == 0 ) {
			sql += "where page_menu = " + menuID;
		//} 
			/*else {
			sql += "where page_menu in ( select menu_id from  wxpt"
					+ enterID
					+ ".user_site_menu where menu_parent = " + menuID + ")";
		}*/
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 999);
		pageList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {
			Pageabout tuijianPageAbout = new Pageabout();
			tuijianPageAbout.setPageUrl(userSitePage.getPageUrl());
			tuijianPageAbout.setMenuName(siteMenuList.get(0).getMenuName());
			tuijianPageAbout.setPageMenu(siteMenuList.get(0).getMenuId());
			tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
			tuijianPageAbout.setPageTime(userSitePage.getPageTime());
			tuijianPageAbout.setPageId(userSitePage.getPageId());
			tuijianPageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), tuijianPageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				tuijianPageAbout.setMeatKey1("img_big");
				tuijianPageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			pageList.add(tuijianPageAbout);
		}

		templateName = userTemplate.getTemplates().getFolder();
		if(templateName.equals("wpt")||templateName.equals("cityin")){
			if(menuNameID == 1){
				return templateName+"One";
			}
			if(menuNameID == 2){
				return templateName+"Two";
			}
			if(menuNameID == 3){
				return templateName+"Three";
			}
			if(menuNameID == 4){
				return templateName+"Four";
			}
			if(menuNameID==5){
				return templateName+"Five";
			}
			if(menuNameID==6){
				return templateName+"Six";
			}
		}
		
		
		if (templateMenuType.getMenuTypeId() == 0) {
			return templateName+"Page";
		}else if(templateMenuType.getMenuTypeId() == 1) {
			if(templatePageProperty.getTextboxNum() != 0){
				return templateName+"GuList";
			}else{
				return templateName+"List";
			}
			
		}else{
			return templateName+"Contacts";
		}
			
		
		
	}
	
	
	
	
	private int count;
	private int pageNo;
	private int pageCount;
	
	public String getFenye(){
		this.getFile();
		this.enterInfo();
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_id = " + menuID;
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		menuName = siteMenuList.get(0).getMenuName();
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateMenuType = super.getEnterTemplateMenuType(enterID, menuNameID);
		templatePageProperty  = super.getEnterTemplatePageProperty(enterID,templateMenuType.getTemplateMenuTypeId());
		sql = "select * from wxpt" + enterID
				+ ".user_site_page ";
		//if (templateMenuType.getParseNum() == 0 ) {
			sql += "where page_menu = " + menuID;
		//} 
			/*else {
			sql += "where page_menu in ( select menu_id from  wxpt"
					+ enterID
					+ ".user_site_menu where menu_parent = " + menuID + ")";
		}*/
		count = (webSiteService.getUserSitePageList( sql, 1, 999999).size()+pageCount-1)/pageCount;
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, pageNo, pageCount);
		pageList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {
			Pageabout tuijianPageAbout = new Pageabout();
			tuijianPageAbout.setPageUrl(userSitePage.getPageUrl());
			tuijianPageAbout.setMenuName(siteMenuList.get(0).getMenuName());
			tuijianPageAbout.setPageMenu(siteMenuList.get(0).getMenuId());
			tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
			tuijianPageAbout.setPageTime(userSitePage.getPageTime());
			tuijianPageAbout.setPageId(userSitePage.getPageId());
			tuijianPageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), tuijianPageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				tuijianPageAbout.setMeatKey1("img_big");
				tuijianPageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			pageList.add(tuijianPageAbout);
		}

		templateName = userTemplate.getTemplates().getFolder();
		if(templatePageProperty.getTextboxNum() != 0){
			return templateName+"GuList";
		}else{
			return templateName+"List";
		}
	}
	
	private List<UserSiteMenu> childMenuList;
	public String getChildMenu(){
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		this.getFile();
		this.enterInfo();
		this.menuList();
		this.childMenu(menuID);
		menuName = siteMenuList.get(menuNameID-1).getMenuName();
		if(templateName.equals("wpt")){
			if(childMenuList.size()>1){
				String sql = "select * from wxpt" + enterID
						+ ".user_site_page ";
				//if (templateMenuType.getParseNum() == 0 ) {
					sql += "where page_menu = " + childMenuList.get(0).getMenuId();
					List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
							sql, 1, 999);
					pageList = new ArrayList<Pageabout>();
					for (UserSitePage userSitePage : sitePageList) {
						Pageabout tuijianPageAbout = new Pageabout();
						tuijianPageAbout.setPageUrl(userSitePage.getPageUrl());
						tuijianPageAbout.setMenuName(siteMenuList.get(0).getMenuName());
						tuijianPageAbout.setPageMenu(siteMenuList.get(0).getMenuId());
						tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
						tuijianPageAbout.setPageTime(userSitePage.getPageTime());
						tuijianPageAbout.setPageId(userSitePage.getPageId());
						tuijianPageAbout = super.setPageAboutValue(enterID,
								userSitePage.getPageId(), tuijianPageAbout);
						sql = "select * from wxpt" + enterID
								+ ".user_site_pagemeta where meta_page = "
								+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
						UserSitePagemeta userSitePagemeta = webSiteService
								.getUserSitePageMetaList(sql);
						if (userSitePagemeta != null) {
							tuijianPageAbout.setMeatKey1("img_big");
							tuijianPageAbout.setMetaImageValue(userSitePagemeta
									.getMetaValue());
						}
						pageList.add(tuijianPageAbout);
					}
				
			}
			if(menuNameID == 3){
				return templateName+"One";
			}
			if(menuNameID == 1){
				return templateName+"Three";
			}
		}
		return templateName+"ChildMenu";
	}
	public String getAll(){
		this.getFile();
		this.enterInfo();
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_id = " + menuID;
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		menuName = siteMenuList.get(0).getMenuName();
		super.addCookie("wxptsite", enterID + "");
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateMenuType = super.getEnterTemplateMenuType(enterID, menuNameID);
		templatePageProperty  = super.getEnterTemplatePageProperty(enterID,templateMenuType.getTemplateMenuTypeId());
		sql = "select * from wxpt" + enterID
				+ ".user_site_page ";
		//if (templateMenuType.getParseNum() == 0 ) {
			//sql += "where page_menu = " + menuID;
		//} 
			//else {
			sql += "where page_menu in ( select menu_id from  wxpt"
					+ enterID
					+ ".user_site_menu where menu_parent = " + menuID + ")";
		//}
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 999);
		pageList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {
			Pageabout tuijianPageAbout = new Pageabout();
			tuijianPageAbout.setPageUrl(userSitePage.getPageUrl());
			tuijianPageAbout.setMenuName(siteMenuList.get(0).getMenuName());
			tuijianPageAbout.setPageMenu(siteMenuList.get(0).getMenuId());
			tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
			tuijianPageAbout.setPageTime(userSitePage.getPageTime());
			tuijianPageAbout.setPageId(userSitePage.getPageId());
			tuijianPageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), tuijianPageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				tuijianPageAbout.setMeatKey1("img_big");
				tuijianPageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			pageList.add(tuijianPageAbout);
		}

		templateName = userTemplate.getTemplates().getFolder();
		
		if (templateMenuType.getMenuTypeId() == 0) {
			return templateName+"Page";
		}else if(templateMenuType.getMenuTypeId() == 1) {
			if(templatePageProperty.getTextboxNum() != 0){
				return templateName+"GuList";
			}else{
				return templateName+"List";
			}
			
		}else{
			return templateName+"Contacts";
		}
			
	}
	
	public void childMenu(int menuID){
		String sql = "select * from  wxpt"
					+ enterID
					+ ".user_site_menu where menu_parent = " + menuID + "";
		childMenuList = webSiteService.getUserSiteMenuList(sql);
	}
	
	
	private int pageID;
	
	public String getPage(){
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		// banner logo 广告
		this.getFile();
		String sql = "select * from wxpt" + enterID
				+ ".user_site_page  where page_id = "
				+ pageID;
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 4);
		pageAbout = new Pageabout();
		for (UserSitePage userSitePage : sitePageList) {
			sql = "select * from wxpt" + enterID
					+ ".user_site_menu where menu_id = " + userSitePage.getUserSiteMenu().getMenuId();
			UserSiteMenu u = webSiteService.getUserSiteMenuList(sql).get(0);
			pageAbout.setMenuName(u.getMenuName());
			pageAbout.setPageMenu(u.getMenuId());
			pageAbout.setPageTitle(userSitePage.getPageTitle());
			pageAbout.setPageTime(userSitePage.getPageTime());
			pageAbout.setPageId(userSitePage.getPageId());
			pageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), pageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				pageAbout.setMeatKey1("img_big");
				pageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			userSitePagemeta = this.getUserSitePagemeta(
					enterID, userSitePage.getPageId(), "txt_localtion");
			if (userSitePagemeta != null) {
				contactUs.setMeatKey("txt_localtion");
				contactUs.setLocaltion(userSitePagemeta.getMetaValue());
			}
		}
		return templateName+"ListPage";
	}
	
	// 获取栏目
	public void menuList() {
		templateProperty = super.getEnterTemplateProperty(enterID);
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_parent = 0";
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		for (int i = 0; i < siteMenuList.size(); i++) {
			
			templateMenuType = super.getEnterTemplateMenuType(enterID, i + 1);
			if (templateMenuType.getMenuTypeId() == 3) {
				siteMenuList.remove(i);
			}
			if (templateMenuType.getMenuLocation() != 0) {
				siteMenuList.remove(i);
			}
			if (templateMenuType.getMenuTypeId() == 2) {
				this.lianxi(siteMenuList.get(i));
			}
			if(i+1 == templateProperty.getMenuNum()){
				break;
			}
			
		}
	}

	// 获取企业
	public void enterInfo() {
		String sql = "SELECT * FROM webchat.enter_infor where enter_infor_id="
				+ enterID;

		enterInfor = enterService.getAll(sql, 1, 999).get(0);
	}

	private List<UserSiteMenu> tuijianMenuList;
	private List<Pageabout> tuijianPageAboutList;

	// 推荐
	private void tuijian() {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_group != 0";
		tuijianMenuList = webSiteService.getUserSiteMenuList(sql);
		if(tuijianMenuList.size()==0){
			return;
		}
		sql = "select * from wxpt" + enterID
				+ ".user_site_page  where page_menu = "
				+ tuijianMenuList.get(0).getMenuId();
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 4);
		tuijianPageAboutList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {
			Pageabout tuijianPageAbout = new Pageabout();
			tuijianPageAbout.setMenuName(tuijianMenuList.get(0).getMenuName());
			tuijianPageAbout.setPageMenu(tuijianMenuList.get(0).getMenuId());
			tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
			tuijianPageAbout.setPageTime(userSitePage.getPageTime());
			tuijianPageAbout.setPageId(userSitePage.getPageId());
			tuijianPageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), tuijianPageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				tuijianPageAbout.setMeatKey1("img_big");
				tuijianPageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			tuijianPageAboutList.add(tuijianPageAbout);
		}
	}
	private String bannerName;
	// 获取所有的广告，banner，logo
	public void getFile() {
		// 判断是否有广告
		userTemplate = super.getEnterUserTemplate(enterID);
		templateProperty = super.getEnterTemplateProperty(enterID);
		if (templateProperty.getAdvertisementProperty() != 2) {
			if(menuNameID == 0){
				siteOptionList = super.getEnterSiteOption(enterID, "advert");
			}else{
				siteOptionList = super.getEnterSiteOption(enterID, "advert_"+enterID+"_"+menuNameID);
			}
		}
		if(templateProperty.getBannerProperty() == 0){
			List<UserSiteOption> siteOptionListByLogo = super.getEnterSiteOption(
					enterID, "banner");
			if (siteOptionListByLogo.size() != 0) {
				bannerName = siteOptionListByLogo.get(0).getOptionValue();
			}
		}else if(templateProperty.getBannerProperty() == 1){
			if(menuNameID == 0){
				List<UserSiteOption> siteOptionListByLogo = super.getEnterSiteOption(
						enterID, "banner");
				if (siteOptionListByLogo.size() != 0) {
					bannerName = siteOptionListByLogo.get(0).getOptionValue();
				}
			}else{
				List<UserSiteOption> siteOptionListByLogo = super.getEnterSiteOption(
						enterID, "banner_"+enterID+"_"+menuNameID);
				if (siteOptionListByLogo.size() != 0) {
					bannerName = siteOptionListByLogo.get(0).getOptionValue();
				}
			}
		}
		// logo
		List<UserSiteOption> siteOptionListByLogo = super.getEnterSiteOption(
				enterID, "logo");
		if (siteOptionListByLogo.size() != 0) {
			logoName = siteOptionListByLogo.get(0).getOptionValue();
		}
	}

	private void lianxi(UserSiteMenu userSiteMenu) {
		// TODO Auto-generated method stub
		contactUs = new Pageabout();
		contactUs.setMenuName(userSiteMenu.getMenuName());
		contactUs.setPageMenu(userSiteMenu.getMenuId());
		String sql = "select * from wxpt" + enterID
				+ ".user_site_page  where page_menu = "
				+ userSiteMenu.getMenuId();
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 4);

		for (UserSitePage userSitePage : sitePageList) {

			contactUs.setPageTitle(userSitePage.getPageTitle());
			contactUs.setPageId(userSitePage.getPageId());
			contactUs = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), contactUs);
			UserSitePagemeta userSitePagemeta = this.getUserSitePagemeta(
					enterID, userSitePage.getPageId(), "txt_localtion");
			if (userSitePagemeta != null) {
				contactUs.setMeatKey("txt_localtion");
				contactUs.setLocaltion(userSitePagemeta.getMetaValue());
			}
		}
	}
	public String getMap(){
		super.addCookie("wxptsite", enterID + "");
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		// banner logo 广告
		this.getFile();
		
		
		return templateName+"Map";
	}
	
	public String getMsg(){
		super.addCookie("wxptsite", enterID + "");
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		// banner logo 广告
		this.getFile();
		return templateName+"Msg";
	}
	private String msgContent;
	private String msgContacts;
	private String msgWay;
	
	private String message;
	public String saveMsg(){
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_parent = 0";
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		for (int i = 0; i < siteMenuList.size(); i++) {
			templateMenuType = super.getEnterTemplateMenuType(enterID, i + 1);
			if (templateMenuType.getMenuTypeId() == 3) {
				sql = "insert into wxpt" + enterID
						+ ".user_site_page (page_title,page_menu,page_time) values('"
						+ "留言板" + "'," + siteMenuList.get(i).getMenuId() + ",'" + TimeUtil.getTime()
						+ "')";
				int remark = webSiteService.executeSql(sql);
				if (remark == 1) {
					message = "添加失败!";
				} else {
					sql = "select * from  wxpt"
							+ enterID
							+ ".user_site_page  where page_id = (select max(page_id) from wxpt"
							+ enterID + ".user_site_page )";
					UserSitePage userSitePage = webSiteService.getUserSitePageList(sql,
							1, 999).get(0);
					super.addUserSitePagemeta(enterID,userSitePage.getPageId(),
							"msg_contacts", msgContacts);
					super.addUserSitePagemeta(enterID,userSitePage.getPageId(),
							"msg_way", msgWay);
					super.addUserSitePagemeta(enterID,userSitePage.getPageId(),
							"msg_content", msgContent);
					message = "留言成功!";
				}
				super.outPrint(message,remark);
				break;
			}
		}
		
		return "success";
	}
	
	public String getShare(){
		super.addCookie("wxptsite", enterID + "");
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		// banner logo 广告
		this.getFile();
		
		
		return templateName+"Share";
	}
	
	public String getLogUrl(){
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		return templateName+"Login";
	}
	
	public String getSearch(){
		this.menuList();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateName = userTemplate.getTemplates().getFolder();
		templateProperty = super.getEnterTemplateProperty(enterID);
		return templateName+"Search";
	}
	/**
	 *获取某一个栏目下的所有数据已json返回 
	 * @return
	 */
	JSONArray jsonls;
	public String getMenuPageJson(){
		String sql = "select * from wxpt" + enterID
				+ ".user_site_menu where menu_id = " + menuID;
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		menuName = siteMenuList.get(0).getMenuName();
		userTemplate = super.getEnterUserTemplate(enterID);
		templateMenuType = super.getEnterTemplateMenuType(enterID, menuNameID);
		templatePageProperty  = super.getEnterTemplatePageProperty(enterID,templateMenuType.getTemplateMenuTypeId());
		sql = "select * from wxpt" + enterID
				+ ".user_site_page ";
		//if (templateMenuType.getParseNum() == 0 ) {
			sql += "where page_menu = " + menuID;
		//} 
			/*else {
			sql += "where page_menu in ( select menu_id from  wxpt"
					+ enterID
					+ ".user_site_menu where menu_parent = " + menuID + ")";
		}*/
		List<UserSitePage> sitePageList = webSiteService.getUserSitePageList(
				sql, 1, 999);
		pageList = new ArrayList<Pageabout>();
		for (UserSitePage userSitePage : sitePageList) {
			Pageabout tuijianPageAbout = new Pageabout();
			tuijianPageAbout.setMenuName(siteMenuList.get(0).getMenuName());
			tuijianPageAbout.setPageMenu(siteMenuList.get(0).getMenuId());
			tuijianPageAbout.setPageTitle(userSitePage.getPageTitle());
			tuijianPageAbout.setPageTime(userSitePage.getPageTime());
			tuijianPageAbout.setPageId(userSitePage.getPageId());
			tuijianPageAbout = super.setPageAboutValue(enterID,
					userSitePage.getPageId(), tuijianPageAbout);
			sql = "select * from wxpt" + enterID
					+ ".user_site_pagemeta where meta_page = "
					+ userSitePage.getPageId() + " and meta_key = 'img_big' ";
			UserSitePagemeta userSitePagemeta = webSiteService
					.getUserSitePageMetaList(sql);
			if (userSitePagemeta != null) {
				tuijianPageAbout.setMeatKey1("img_big");
				tuijianPageAbout.setMetaImageValue(userSitePagemeta
						.getMetaValue());
			}
			pageList.add(tuijianPageAbout);
		}
		jsonls = JSONArray.fromObject(pageList);
		super.out.print(jsonls);
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgContacts() {
		return msgContacts;
	}

	public void setMsgContacts(String msgContacts) {
		this.msgContacts = msgContacts;
	}

	

	public String getMsgWay() {
		return msgWay;
	}

	public void setMsgWay(String msgWay) {
		this.msgWay = msgWay;
	}

	public List<UserSiteMenu> getTuijianMenuList() {
		return tuijianMenuList;
	}

	public void setTuijianMenuList(List<UserSiteMenu> tuijianMenuList) {
		this.tuijianMenuList = tuijianMenuList;
	}

	public int getEnterID() {
		return enterID;
	}

	public void setEnterID(int enterID) {
		this.enterID = enterID;
	}

	public List<UserSiteMenu> getSiteMenuList() {
		return siteMenuList;
	}

	public void setSiteMenuList(List<UserSiteMenu> siteMenuList) {
		this.siteMenuList = siteMenuList;
	}

	public EnterInfor getEnterInfor() {
		return enterInfor;
	}

	public void setEnterInfor(EnterInfor enterInfor) {
		this.enterInfor = enterInfor;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<UserSiteOption> getSiteOptionList() {
		return siteOptionList;
	}

	public void setSiteOptionList(List<UserSiteOption> siteOptionList) {
		this.siteOptionList = siteOptionList;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public List<Pageabout> getTuijianPageAboutList() {
		return tuijianPageAboutList;
	}

	public void setTuijianPageAboutList(List<Pageabout> tuijianPageAboutList) {
		this.tuijianPageAboutList = tuijianPageAboutList;
	}

	public Pageabout getContactUs() {
		return contactUs;
	}

	public void setContactUs(Pageabout contactUs) {
		this.contactUs = contactUs;
	}

	public int getMenuNameID() {
		return menuNameID;
	}

	public void setMenuNameID(int menuNameID) {
		this.menuNameID = menuNameID;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public List<Pageabout> getPageList() {
		return pageList;
	}

	public void setPageList(List<Pageabout> pageList) {
		this.pageList = pageList;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Pageabout getPageAbout() {
		return pageAbout;
	}

	public void setPageAbout(Pageabout pageAbout) {
		this.pageAbout = pageAbout;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public List<UserSiteMenu> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<UserSiteMenu> childMenuList) {
		this.childMenuList = childMenuList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	


}

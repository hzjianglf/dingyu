package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.FileViewer;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.TemplateMenuType;
import com.wxpt.site.entity.TemplatePageProperty;
import com.wxpt.site.entity.TemplateProperty;
import com.wxpt.site.entity.Templates;
import com.wxpt.site.entity.UserSiteMenu;
import com.wxpt.site.entity.UserSiteOption;
import com.wxpt.site.entity.UserSitePage;
import com.wxpt.site.entity.UserSitePagemeta;
import com.wxpt.site.entity.UserTemplate;
import com.wxpt.site.entity.pojo.TemplatesPojo;
import com.wxpt.site.entity.pojo.UserSiteMenuPojo;
import com.wxpt.site.entity.pojo.UserSiteOptionPojo;
import com.wxpt.site.service.WebSiteService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "page", location = "success.jsp") })
public class ManageWebsiteAction extends ParentAction {
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	private List<Templates> templateList;

	@Autowired
	WebSiteService webSiteService;

	
	
	
	public String getTemplate() {
		String hql = "SELECT * FROM webchat.templates";
		templateList = webSiteService.getTemplates(hql, page, rows);
		List<TemplatesPojo> templatesPojos = new ArrayList<TemplatesPojo>();
		for (Templates template : templateList) {
			String type = "";
			if (template.getTemplateType() == 0) {
				type = "标准版";
			}
			if (template.getTemplateType() == 1) {
				type = "发展版";
			}
			if (template.getTemplateType() == 2) {
				type = "商务版";
			}
			TemplatesPojo templatesPojo = new TemplatesPojo(
					template.getTemplateId(), type, template.getTemplateType(),
					template.getTemplatePrice(), "<img src=\"../"
							+ template.getThumbnail()
							+ "\"  style=\"width:200px ; height:100px ;\"/>",
					template.getUrl(), template.getFolder(),
					template.getTemplateAddTime(),
					template.getTemplateRemark(),
					template.getTemplateUpdateTime());
			templatesPojos.add(templatesPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "userTemplates" });
			jsonls = JSONArray.fromObject(templatesPojos, jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = webSiteService
				.getTemplatesCount("SELECT * FROM webchat.templates");
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private int templateId;
	private String message;

	public String chooseTemplate() {

		String sql = "select * from webchat.user_template where enter_infor_id = "
				+ super.getCookieByEnterID();
		UserTemplate userTemplate = webSiteService.getUserTemplate(sql);
		if (userTemplate == null) {
			sql = "INSERT INTO webchat.user_template( template_id, enter_infor_id, user_template_addTime) "
					+ "VALUES ("
					+ templateId
					+ ","
					+ super.getCookieByEnterID()
					+ ",'"
					+ TimeUtil.getTime()
					+ "')";
			int remark = webSiteService.executeSql(sql);
			if (remark == 1) {
				message = "添加失败";
			} else {
				message = "添加成功";
			}
		} else {
			sql = "update webchat.user_template set template_id = "
					+ templateId + " where enter_infor_id = "
					+ super.getCookieByEnterID();
			int remark = webSiteService.executeSql(sql);
			if (remark == 1) {
				message = "修改失败";
			} else {
				message = "修改成功";
			}
		}
		super.out.print("{\"message\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 获取所有栏目
	 * 
	 * @return
	 */
	private List<UserSiteMenu> siteMenuList;

	public String menuEdit() {
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = 0";
		siteMenuList = webSiteService.getUserSiteMenuList(sql, page, rows);
		List<UserSiteMenuPojo> userSiteMenuPojos = new ArrayList<UserSiteMenuPojo>();
		for (int i = 0; i < siteMenuList.size(); i++) {
			String menuNameStr = "";
			switch (i + 1) {
			case 1:
				menuNameStr = "栏目一";
				break;
			case 2:
				menuNameStr = "栏目二";
				break;
			case 3:
				menuNameStr = "栏目三";
				break;
			case 4:
				menuNameStr = "栏目四";
				break;
			case 5:
				menuNameStr = "栏目五";
				break;
			case 6:
				menuNameStr = "栏目六";
				break;
			case 7:
				menuNameStr = "栏目七";
				break;
			case 8:
				menuNameStr = "栏目八";
				break;
			case 9:
				menuNameStr = "栏目九";
				break;
			case 10:
				menuNameStr = "栏目十";
				break;
			}
			UserSiteMenuPojo userSiteMenuPojo = new UserSiteMenuPojo(
					siteMenuList.get(i).getMenuId(), siteMenuList.get(i)
							.getMenuName(),
					siteMenuList.get(i).getMenuParent(), siteMenuList.get(i)
							.getMenuGroup(), menuNameStr, i + 1 + "");
			userSiteMenuPojo.setMenuImageState(templateProperty.getMenuImageProterty());
			userSiteMenuPojos.add(userSiteMenuPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "userSitePages" });
			jsonls = JSONArray.fromObject(userSiteMenuPojos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = webSiteService.getTemplatesCount("SELECT * FROM wxpt"
				+ super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = 0");
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 判读当前用户能否继续添加栏目
	 * 
	 * @return
	 */
	public String getMenu() {
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = 0";
		siteMenuList = webSiteService.getUserSiteMenuList(sql);
		int state = 0;// 判读栏目数是否达到上限
		if (siteMenuList.size() >= templateProperty.getMenuNum()) {
			message = "当前模板栏目数已达上限";
			state = 1;
		} else {
			switch (siteMenuList.size() + 1) {
			case 1:
				message = "栏目一";
				break;
			case 2:
				message = "栏目二";
				break;
			case 3:
				message = "栏目三";
				break;
			case 4:
				message = "栏目四";
				break;
			case 5:
				message = "栏目五";
				break;
			case 6:
				message = "栏目六";
				break;
			case 7:
				message = "栏目七";
				break;
			case 8:
				message = "栏目八";
				break;
			case 9:
				message = "栏目九";
				break;
			case 10:
				message = "栏目十";
				break;
			}
		}
		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\",\"menuImageState\":\""+templateProperty.getMenuImageProterty()+"\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private String menuName;
	private File menuImage;
	
	public String addMenu() {
		String imageName = "menuImage_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, menuImage,super.getCookieByEnterID()+"");
		String sql = "insert into wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu (menu_name,menu_parent,menu_group,image_value) values('"
				+ menuName + "',0,0,'"+imageName+"')";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "添加失败";
		} else {
			message = "添加成功";
		}
		return "page";
	}

	private int menuId;

	public String updateMenu() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu  where menu_id = " + menuId;
		UserSiteMenu menu = webSiteService.getUserSiteMenuList(sql).get(0);
		String imageName = "menuImage_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, menuImage,super.getCookieByEnterID()+"");
		if(menuImage != null){
			fileUploadBean.deletefile(super.getCookieByEnterID()+"/"+menu.getImageValue());
		}else{
			imageName = menu.getImageValue();
		}
		sql = "update wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu set" + " menu_name = '" + menuName
				+ "',image_value ='"+imageName+"' where menu_id = " + menuId;
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "修改失败";
		} else {
			message = "修改成功";
		}
		
		return "page";
	}

	/**
	 * 获取模板Banner的数量
	 * 
	 * @return
	 */
	private List<UserSiteOption> siteOptionList;

	public String getBannerNum() {
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		int state = 0;// 0表示banner不能上传；1表示banner可以上传
		
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = 'banner'";
		if(menuNameID != 0){
			sql = "select * from wxpt" + super.getCookieByEnterID()
					+ ".user_site_option where option_name = 'banner_"+super.getCookieByEnterID()+"_"+menuNameID+"'";
		}
		siteOptionList = webSiteService.getUserOptionList(sql);
		if (templateProperty.getBannerNumber() == 0) {
			message = "该模板无需上传banner！";
		} else if (siteOptionList.size() >= templateProperty.getBannerNumber()) {
			message = "该模板banner已达上限！";
		} else {
			switch (siteOptionList.size() + 1) {
			case 1:
				message = "banner一";
				break;
			case 2:
				message = "banner二";
				break;
			case 3:
				message = "banner三";
				break;
			case 4:
				message = "banner四";
				break;
			case 5:
				message = "banner五";
				break;
			case 6:
				message = "banner六";
				break;
			case 7:
				message = "banner七";
				break;
			case 8:
				message = "banner八";
				break;
			case 9:
				message = "banner九";
				break;
			case 10:
				message = "banner十";
				break;
			}
			state = 1;
		}
		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private File optionValue;

	/**
	 * 上传banner
	 * 
	 * @return
	 */
	private FileUploadBean fileUploadBean = new FileUploadBean();
	private String bannerKey;
	
	
	public String uploadBanner() {
		try {
			if(menuNameID != 0){
				bannerKey = "banner_"+super.getCookieByEnterID()+"_"+menuNameID;
			}else{
				bannerKey ="banner";
			}
			String imageName = "banner_" + super.getCookieByEnterID() + "_"
					+ TimeUtil.getImagesTime() + ".jpg";
			fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"");
			String sql = "insert into wxpt"
					+ super.getCookieByEnterID()
					+ ".user_site_option (option_name,option_value) values('"+bannerKey+"','"
					+ imageName + "')";
			int remark = webSiteService.executeSql(sql);
			if (remark == 1) {
				message = "上传失败!";
			} else {
				message = "上传成功!";
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = "上传失败!";
		}
		return "page";
	}

	/**
	 * 获取所有banner
	 * 
	 * @return
	 */
	public String bannerEdit() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = 'banner'";
		if(menuNameID != 0){
			sql = "select * from wxpt" + super.getCookieByEnterID()
					+ ".user_site_option where option_name = 'banner_"+super.getCookieByEnterID()+"_"+menuNameID+"'";
		}
		siteOptionList = webSiteService.getUserOptionList(sql);
		List<UserSiteOptionPojo> userSiteOptionPojos = new ArrayList<UserSiteOptionPojo>();
		for (int i = 0; i < siteOptionList.size(); i++) {

			switch (i + 1) {
			case 1:
				message = "banner一";
				break;
			case 2:
				message = "banner二";
				break;
			case 3:
				message = "banner三";
				break;
			case 4:
				message = "banner四";
				break;
			case 5:
				message = "banner五";
				break;
			case 6:
				message = "banner六";
				break;
			case 7:
				message = "banner七";
				break;
			case 8:
				message = "banner八";
				break;
			case 9:
				message = "banner九";
				break;
			case 10:
				message = "banner十";
				break;
			}
			UserSiteOptionPojo userSiteOptionPojo = new UserSiteOptionPojo(
					siteOptionList.get(i).getOptionId(), siteOptionList.get(i)
							.getOptionName(), "<img src = \"../web/images/"+super.getCookieByEnterID()+"/"
							+ siteOptionList.get(i).getOptionValue()
							+ "\"  style=\"width:200px ; height:100px ;\" />",
					message);
			userSiteOptionPojos.add(userSiteOptionPojo);
		}
		try {
			jsonls = JSONArray.fromObject(userSiteOptionPojos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = siteOptionList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private int optionId;

	/**
	 * 设置修改banner
	 * 
	 * @return
	 */
	public String updateBanner() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_id = " + optionId;
		UserSiteOption userSiteOption = webSiteService.getUserOptionList(sql)
				.get(0);
		fileUploadBean.deletefile(super.getCookieByEnterID()+"/"+userSiteOption.getOptionValue());
		String imageName = "banner_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"");
		sql = "update  wxpt" + super.getCookieByEnterID()
				+ ".user_site_option set option_value = '" + imageName
				+ "' where option_id = '" + optionId + "'";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "修改失败!";
		} else {
			message = "修改成功!";
		}
		return "page";
	}

	/**
	 * 获取广告数量
	 * 
	 * @return
	 */
	public String getAdvertNum() {
		if(menuNameID != 0){
			advertKey = "advert_"+super.getCookieByEnterID()+"_"+menuNameID;
		}else{
			advertKey = "advert";
		}
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		int state = 0;// 0表示banner不能上传；1表示banner可以上传
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = '"+advertKey+"'";
		siteOptionList = webSiteService.getUserOptionList(sql);
		if(menuNameID ==0){
			if (templateProperty.getAdvertisingNum() == 0
					|| templateProperty.getAdvertisementProperty() == 2) {
				message = "该模板无需上传广告！";
				super.outPrint(message, 0);
				return "success";
			} else if (siteOptionList.size() >= templateProperty
					.getAdvertisingNum()) {
				message = "该模板广告已达上限！";
				super.outPrint(message, 0);
				return "success";
			}
		}else{
			TemplatePageProperty templatePageProperty = super.getEnterTemplatePageProperty(super.getEnterTemplateMenuType(menuNameID).getTemplateTypeMenuId());
			if(siteOptionList.size()>=templatePageProperty.getAdvertisingNum()){
				message = "该栏目下广告已达上限！";
				super.outPrint(message, 0);
				return "success";
			}
		}
		switch (siteOptionList.size() + 1) {
		case 1:
			message = "广告一";
			break;
			case 2:
				message = "广告二";
				break;
			case 3:
				message = "广告三";
				break;
			case 4:
				message = "广告四";
				break;
			case 5:
				message = "广告五";
				break;
			case 6:
				message = "广告六";
				break;
			case 7:
				message = "广告七";
				break;
			case 8:
				message = "广告八";
				break;
			case 9:
				message = "广告九";
				break;
			case 10:
				message = "广告十";
				break;
			}
			state = 1;
		
		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 上传广告
	 * 
	 * @return
	 */
	private String url;
	public String uploadAdvert() {
		if(menuNameID != 0){
			advertKey = "advert_"+super.getCookieByEnterID()+"_"+menuNameID;
		}else{
			advertKey = "advert";
		}
		try {
			String imageName = "ad_" + super.getCookieByEnterID() + "_"
					+ TimeUtil.getImagesTime() + ".jpg";
			
			
			fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"/");
			String sql = "insert into wxpt"
					+ super.getCookieByEnterID()
					+ ".user_site_option (option_name,option_value,url) values('"+advertKey+"','"
					+ imageName + "','"+url+"')";
			int remark = webSiteService.executeSql(sql);
			if (remark == 1) {
				message = "上传失败!";
			} else {
				message = "上传成功!";
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = "上传失败!";
		}
		return "page";
	}

	/**
	 * 获取广告
	 * 
	 * @return
	 */
	private String advertKey;
	public String advertEdit() {
		if(menuNameID != 0){
			advertKey = "advert_"+super.getCookieByEnterID()+"_"+menuNameID;
		}else{
			advertKey = "advert";
		}
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = '"+advertKey+"'";
		siteOptionList = webSiteService.getUserOptionList(sql);
		List<UserSiteOptionPojo> userSiteOptionPojos = new ArrayList<UserSiteOptionPojo>();
		for (int i = 0; i < siteOptionList.size(); i++) {

			switch (i + 1) {
			case 1:
				message = "广告一";
				break;
			case 2:
				message = "广告二";
				break;
			case 3:
				message = "广告三";
				break;
			case 4:
				message = "广告四";
				break;
			case 5:
				message = "广告五";
				break;
			case 6:
				message = "广告六";
				break;
			case 7:
				message = "广告七";
				break;
			case 8:
				message = "广告八";
				break;
			case 9:
				message = "广告九";
				break;
			case 10:
				message = "广告十";
				break;
			}
			UserSiteOptionPojo userSiteOptionPojo = new UserSiteOptionPojo(
					siteOptionList.get(i).getOptionId(), siteOptionList.get(i)
							.getOptionName(), "<img src = \"../web/images/"+
							super.getCookieByEnterID()+"/"+ siteOptionList.get(i).getOptionValue()
							+ "\"  style=\"width:200px ; height:100px ;\" />",
					message);
			userSiteOptionPojo.setUrl(siteOptionList.get(i).getUrl());
			userSiteOptionPojos.add(userSiteOptionPojo);
		}
		try {
			jsonls = JSONArray.fromObject(userSiteOptionPojos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = siteOptionList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 修改上传广告
	 * 
	 * @return
	 */
	public String updateAdvert() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_id = " + optionId;
		UserSiteOption userSiteOption = webSiteService.getUserOptionList(sql)
				.get(0);
		fileUploadBean.deletefile(super.getCookieByEnterID()+"/"+userSiteOption.getOptionValue());
		String imageName = "ad_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"");
		sql = "update  wxpt" + super.getCookieByEnterID()
				+ ".user_site_option set option_value = '" + imageName
				+ "', url = '"+url+"' where option_id = '" + optionId + "'";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "修改失败!";
		} else {
			message = "修改成功!";
		}
		return "page";
	}

	/**
	 * logo上传
	 * 
	 * @return
	 */
	public String logoEdit() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = 'logo'";
		siteOptionList = webSiteService.getUserOptionList(sql);
		List<UserSiteOptionPojo> userSiteOptionPojos = new ArrayList<UserSiteOptionPojo>();
		for (int i = 0; i < siteOptionList.size(); i++) {
			UserSiteOptionPojo userSiteOptionPojo = new UserSiteOptionPojo(
					siteOptionList.get(i).getOptionId(), siteOptionList.get(i)
							.getOptionName(), "<img src = \"../web/images/"
							+ super.getCookieByEnterID() +"/" + siteOptionList.get(i).getOptionValue()
							+ "\"  style=\"width:200px ; height:100px ;\" />",
					"logo");
			userSiteOptionPojos.add(userSiteOptionPojo);
		}
		try {
			jsonls = JSONArray.fromObject(userSiteOptionPojos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = siteOptionList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 获取logo数量
	 * 
	 * @return
	 */
	public String getLogoNum() {
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		int state = 0;// 0表示banner不能上传；1表示banner可以上传
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = 'logo'";
		siteOptionList = webSiteService.getUserOptionList(sql);
		if (templateProperty.getLogoProperty() == 0) {
			message = "该模板无需上传Logo！";
		} else if (siteOptionList.size() >= templateProperty.getLogoProperty()) {
			message = "该模板Logo已上传！";
		} else {
			state = 1;
			message = "Logo";
		}
		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 上传Logo
	 * 
	 * @return
	 */
	public String upLoadLogo() {
		try {
			String imageName = "logo_" + super.getCookieByEnterID() + "_"
					+ TimeUtil.getImagesTime() + ".jpg";
			fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"");
			String sql = "insert into wxpt"
					+ super.getCookieByEnterID()
					+ ".user_site_option (option_name,option_value) values('logo','"
					+ imageName + "')";
			int remark = webSiteService.executeSql(sql);
			if (remark == 1) {
				message = "上传失败!";
			} else {
				message = "上传成功!";
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = "上传失败!";
		}
		return "page";
	}

	/**
	 * 修改Logo
	 * 
	 * @return
	 */
	public String updateLogo() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_id = " + optionId;
		UserSiteOption userSiteOption = webSiteService.getUserOptionList(sql)
				.get(0);
		fileUploadBean.deletefile(super.getCookieByEnterID()+"/"+ userSiteOption.getOptionValue());
		String imageName = "logo_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, optionValue,super.getCookieByEnterID()+"");
		sql = "update  wxpt" + super.getCookieByEnterID()
				+ ".user_site_option set option_value = '" + imageName
				+ "' where option_id = '" + optionId + "'";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "修改失败!";
		} else {
			message = "修改成功!";
		}
		return "page";
	}

	private int menuNameID;
	private TemplateMenuType templateMenuType;

	/**
	 * 推荐
	 * 
	 * @return
	 */
	public String tuijian() {
		templateMenuType = super.getEnterTemplateMenuType(menuNameID);
		if (templateMenuType.getMenuRecom() == 0) {
			message = "该栏目无法设置推荐!";
		} else {
			String sql = "update wxpt" + super.getCookieByEnterID()
					+ ".user_site_menu set" + " menu_group = '"
					+ templateMenuType.getMenuLocation() + "' where menu_id = "
					+ menuId;
			int remark = webSiteService.executeSql(sql);
			if (remark == 0) {
				message = "推荐栏目设置成功!";
			} else {
				message = "推荐栏目设置失败!";
			}
		}
		super.out.print("{\"message\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 设置子栏目
	 * 
	 * @return
	 */
	public String childMenu() {
		templateMenuType = super.getEnterTemplateMenuType(menuNameID);
		int state = 0;
		if (templateMenuType.getParseNum() == 0) {
			message = "该栏目无法设置子栏目!";
		} else {
			state = 1;
		}
		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 获取所有子栏目
	 * 
	 * @return
	 */
	public String childMenuEdit() {
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = " + menuId;
		siteMenuList = webSiteService.getUserSiteMenuList(sql, page, rows);
		List<UserSiteMenuPojo> userSiteMenuPojos = new ArrayList<UserSiteMenuPojo>();
		for (int i = 0; i < siteMenuList.size(); i++) {

			UserSiteMenuPojo userSiteMenuPojo = new UserSiteMenuPojo(
					siteMenuList.get(i).getMenuId(), siteMenuList.get(i)
							.getMenuName(),
					siteMenuList.get(i).getMenuParent(), siteMenuList.get(i)
							.getMenuGroup(), "", i + 1 + "");
			userSiteMenuPojos.add(userSiteMenuPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "userSitePages" });
			jsonls = JSONArray.fromObject(userSiteMenuPojos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		count = webSiteService.getTemplatesCount("SELECT * FROM wxpt"
				+ super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = " + menuId);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 添加子栏目
	 * 
	 * @return
	 */
	public String addChildMenu() {
		String sql = "insert into wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu (menu_name,menu_parent,menu_group) values('"
				+ menuName + "'," + menuId + ",0)";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "添加失败";
		} else {
			message = "添加成功";
		}
		super.out.print("{\"message\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 删除栏目
	 * 
	 * @return
	 */
	public String deletMenu() {
		String sql = "delete from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu  where menu_id = " + menuId;
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "删除失败";
		} else {
			message = "删除成功";
		}
		super.out.print("{\"message\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 显示栏目下的所有内容
	 * 
	 * @return
	 */
	public String pageEdit() {
		super.pagePrintList(menuId, menuNameID, page, rows);
		return "success";
	}

	private JSONObject json;

	/**
	 * 获取模板栏目属性
	 * 
	 * @return
	 */
	public String chooseShow() {
		TemplateMenuType templateMenuType = super
				.getEnterTemplateMenuType(menuNameID);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "templatePageProperties",
					"templates" });
			json = JSONObject.fromObject(templateMenuType, jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.out.print(json.toString());
		super.out.flush();
		super.out.close();
		return "success";
	}

	private int templateTypeMenuId;

	/**
	 * 获取页面属性
	 * 
	 * @return
	 */
	public String checkTyepMenu() {
		TemplatePageProperty templatePageProperty = super
				.getEnterTemplatePageProperty(templateTypeMenuId);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "templateMenuType",
					"templates" });
			json = JSONObject.fromObject(templatePageProperty, jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(json.toString());
		super.out.print(json.toString());
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 添加单页详细信息
	 * 
	 * @return
	 */
	private File bigImage;
	private String pageTitle;
	private String metaValue;
	private String metaDetail;
	private int childMenuId;
	private String pageUrl;
	public String addPage() {
		if(childMenuId != 0){
			menuId = childMenuId;
		}
		String sql = "insert into wxpt" + super.getCookieByEnterID()
				+ ".user_site_page (page_title,page_menu,page_time,page_url) values('"
				+ pageTitle + "'," + menuId + ",'" + TimeUtil.getTime() + "','"+pageUrl+"')";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "添加失败!";
		} else {
			sql = "select * from  wxpt"
					+ super.getCookieByEnterID()
					+ ".user_site_page  where page_id = (select max(page_id) from wxpt"
					+ super.getCookieByEnterID() + ".user_site_page )";
			UserSitePage userSitePage = webSiteService.getUserSitePageList(sql,
					1, 999).get(0);
			String imageName = userSitePage.getPageId() + "_"
					+ TimeUtil.getImagesTime() + ".jpg";
			fileUploadBean.upLoad(imageName, bigImage,super.getCookieByEnterID()+"");
			remark = super.addUserSitePagemeta(userSitePage.getPageId(),
					"img_big", imageName);
			if (remark == 1) {
				message = "图片添加失败!";
			} else {
				sql = "insert into wxpt"
						+ super.getCookieByEnterID()
						+ ".user_site_pagemeta (meta_page,meta_key,meta_value) values('"
						+ userSitePage.getPageId() + "','txt_editor_1','"
						+ metaValue + "')";
				remark = super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_editor_1", metaValue);
				if (remark == 1) {
					message = "内容添加失败!";
					fileUploadBean.deletefile(super.getCookieByEnterID()+"/" + imageName);
				} else {
					remark = super.addUserSitePagemeta(
							userSitePage.getPageId(), "txt_text", metaDetail);
					if (remark == 1) {
						message = "概述添加失败!";
						fileUploadBean.deletefile(super.getCookieByEnterID()+"/" + imageName);
					} else {
						message = "添加成功";
					}
				}
			}
		}
		return "page";
	}

	/**
	 * 检查单页是否能继续添加
	 * 
	 * @return
	 */
	public String checkAdd() {
		TemplateMenuType templateMenuType = super
				.getEnterTemplateMenuType(menuNameID);
		int state = 0;
		if (templateMenuType.getMenuTypeId() == 0
				|| templateMenuType.getMenuTypeId() == 2) {
			String sql = "select * from wxpt" + this.getCookieByEnterID()
					+ ".user_site_page where page_menu = " + menuId;
			int count = webSiteService.getTemplatesCount(sql);
			if (count != 0) {
				message = "当前栏目下内容已添加!";
			} else {
				state = 1;
			}
		}
		if (templateMenuType.getMenuTypeId() == 1) {
			state = 1;
		}
		if (templateMenuType.getMenuTypeId() == 3) {
			message = "当前栏目无需添加内容!";
		}
		super.outPrint(message, state);
		return "success";
	}

	private int pageID;

	/**
	 * 修改单页
	 * 
	 * @return
	 */
	FileViewer fileViewer = new FileViewer();
	public String updatePage() {
		TemplateMenuType templateMenuType = super
				.getEnterTemplateMenuType(menuNameID);
		TemplatePageProperty templatePageProperty = super
				.getEnterTemplatePageProperty(templateMenuType
						.getTemplateTypeMenuId());
		if (templatePageProperty.getBigImageNum() != 0) {
			if (bigImage != null) {
				String imageName = pageID + "_" + TimeUtil.getImagesTime()
						+ ".jpg";
				fileUploadBean.upLoad(imageName, bigImage,super.getCookieByEnterID()+"");
				UserSitePagemeta userSitePagemeta = super.getUserSitePagemeta(
						pageID, "img_big");
				fileUploadBean.deletefile(super.getCookieByEnterID()+"/"+userSitePagemeta.getMetaValue());
				int remark = super.updateUerSitePagemeta(pageID, imageName,
						"img_big");
				if (remark == 1) {
					message = "修改失败!";
					return "page";
				}
			}
		}
		
		int remark = super.updateUerSitePagemeta(pageID, metaValue,
				"txt_editor_1");
		if (remark == 1) {
			message = "修改失败!";
			return "page";
		}
		if(metaDetail != null){
			remark = super.updateUerSitePagemeta(pageID, metaDetail, "txt_text");
		}
		
		if(templateMenuType.getParseNum() == 1){
			remark = super
					.updateUserSitePage(childMenuId,pageID, pageTitle, TimeUtil.getTime());
		}else{
			remark = super
				.updateUserSitePage(pageID, pageTitle, TimeUtil.getTime());
		}
		String sql = "update wxpt" + this.getCookieByEnterID()
				+ ".user_site_page set page_url = '" + pageUrl
				+ "'  where page_id = " + pageID;
		remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "修改失败!";
			return "page";
		}
		message = "修改成功!";
		return "page";
	}

	public String deletePage() {
		TemplateMenuType templateMenuType = super
				.getEnterTemplateMenuType(menuNameID);
		TemplatePageProperty templatePageProperty = super
				.getEnterTemplatePageProperty(templateMenuType
						.getTemplateTypeMenuId());
		UserSitePagemeta userSitePagemeta = super.getUserSitePagemeta(
				pageID, "img_big");
		String sql = "DELETE FROM wxpt" + this.getCookieByEnterID()
				+ ".user_site_page where page_id = " + pageID;
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "删除失败!";
		}else{
			if (templatePageProperty.getBigImageNum() != 0) {
				
				try {
					fileUploadBean.deletefile(super.getCookieByEnterID() + "/"
							+ userSitePagemeta.getMetaValue());
				} catch (Exception e) {
					// TODO: handle exception
				}
				message = "删除成功!";
			}else{
				message = "删除成功!";
			}
		}
		super.outPrint(message, remark);
		return "success";
	}

	private int touchPageID;
	private String address;
	private String hotline;// 热线电话
	private String telephone;// 手机
	private String landline;// 座机
	private String localtion;// 地图中心坐标
	private String email;// emial
	private String contacts;
	private File touchBigImage;
	private String touchPageTitle;

	public String addTelPage() {

		String sql = "insert into wxpt" + super.getCookieByEnterID()
				+ ".user_site_page (page_title,page_menu,page_time) values('"
				+ touchPageTitle + "'," + menuId + ",'" + TimeUtil.getTime()
				+ "')";
		int remark = webSiteService.executeSql(sql);
		if (remark == 1) {
			message = "添加失败!";
		} else {
			sql = "select * from  wxpt"
					+ super.getCookieByEnterID()
					+ ".user_site_page  where page_id = (select max(page_id) from wxpt"
					+ super.getCookieByEnterID() + ".user_site_page )";
			UserSitePage userSitePage = webSiteService.getUserSitePageList(sql,
					1, 999).get(0);
			if (touchBigImage != null) {
				String imageName = userSitePage.getPageId() + "_"
						+ TimeUtil.getImagesTime() + ".jpg";
				fileUploadBean.upLoad(imageName, touchBigImage);
				remark = super.addUserSitePagemeta(userSitePage.getPageId(),
						"img_big", imageName);
				if (remark == 1) {
					message = "图片添加失败!";
					return "page";
				} 
			}else {
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_address", address);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_hotline", hotline);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_telephone", telephone);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_landline", landline);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_email", email);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_contacts", contacts);
				super.addUserSitePagemeta(userSitePage.getPageId(),
						"txt_localtion", localtion);
			}
		}
		message = "添加成功!";
		return "page";
	}
	
	public String updateTelPage() {
		if (touchBigImage != null) {
			String imageName = touchPageID + "_" + TimeUtil.getImagesTime()
					+ ".jpg";
			fileUploadBean.upLoad(imageName, touchBigImage);
			UserSitePagemeta userSitePagemeta = super.getUserSitePagemeta(
					touchPageID, "img_big");
			fileUploadBean.deletefile(userSitePagemeta.getMetaValue());
			int remark = super.updateUerSitePagemeta(touchPageID, imageName,
					"img_big");
			if (remark == 1) {
				message = "修改失败!";
				return "page";
			}
		}
		if(address != null){
			super.updateUerSitePagemeta(touchPageID, address,
					"txt_address");
		}
		if(hotline != null){
			super.updateUerSitePagemeta(touchPageID, hotline,
					"txt_hotline");
		}
		if(telephone != null){
			super.updateUerSitePagemeta(touchPageID, telephone,
					"txt_telephone");
		}
		if(landline != null){
			super.updateUerSitePagemeta(touchPageID, landline,
					"txt_landline");
		}
		if(localtion != null){
			super.updateUerSitePagemeta(touchPageID, localtion,
					"txt_localtion");
		}
		if(email != null){
			super.updateUerSitePagemeta(touchPageID, email,
					"txt_email");
		}
		if(contacts != null){
			super.updateUerSitePagemeta(touchPageID, contacts,
					"txt_contacts");
		}
		if(touchPageTitle != null){
			super
			.updateUserSitePage(touchPageID, touchPageTitle, TimeUtil.getTime());
		}
		message="修改成功!";
		return "page";
	}

	public String getchildMenuList(){
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_menu where menu_parent = " + menuId;
		siteMenuList = webSiteService.getUserSiteMenuList(sql, 1, 999);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "userSitePages" });
			jsonls = JSONArray.fromObject(siteMenuList,jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		super.out.print(jsonls);
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	public String checkBanner(){
		TemplateProperty templateProperty = super.getEnterTemplateProperty();
		int state = 0;// 0表示banner不能上传；1表示banner可以上传
		String sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".user_site_option where option_name = 'banner_"+super.getCookieByEnterID()+"_"+menuNameID+"'";
		siteOptionList = webSiteService.getUserOptionList(sql);
		if (templateProperty.getBannerProperty() == 0) {
			message = "该栏目下无需banner！";
			state = 0;
		}else{
			state = 1;
		}
		super.outPrint(message, state);
		return "success";
	}
	
	
	public String getTouchPageTitle() {
		return touchPageTitle;
	}

	public void setTouchPageTitle(String touchPageTitle) {
		this.touchPageTitle = touchPageTitle;
	}

	public int getTouchPageID() {
		return touchPageID;
	}

	public void setTouchPageID(int touchPageID) {
		this.touchPageID = touchPageID;
	}

	public File getTouchBigImage() {
		return touchBigImage;
	}

	public void setTouchBigImage(File touchBigImage) {
		this.touchBigImage = touchBigImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getLocaltion() {
		return localtion;
	}

	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMetaDetail() {
		return metaDetail;
	}

	public void setMetaDetail(String metaDetail) {
		this.metaDetail = metaDetail;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public File getBigImage() {
		return bigImage;
	}

	public void setBigImage(File bigImage) {
		this.bigImage = bigImage;
	}

	public int getTemplateTypeMenuId() {
		return templateTypeMenuId;
	}

	public void setTemplateTypeMenuId(int templateTypeMenuId) {
		this.templateTypeMenuId = templateTypeMenuId;
	}

	public int getMenuNameID() {
		return menuNameID;
	}

	public void setMenuNameID(int menuNameID) {
		this.menuNameID = menuNameID;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(File optionValue) {
		this.optionValue = optionValue;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getChildMenuId() {
		return childMenuId;
	}

	public void setChildMenuId(int childMenuId) {
		this.childMenuId = childMenuId;
	}

	public String getCookie(){
		super.out.print("{\"enterID\":\""+super.getCookieByEnterID()+"\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	public String getCheckChooseTemp(){
		String sql = "select * from webchat.user_template where enter_infor_id = "
				+ super.getCookieByEnterID();
		System.out.println(super.getCookieByEnterID());
		UserTemplate userTemplate = webSiteService.getUserTemplate(sql);
	System.out.println(userTemplate);
		int state = 0;
		if (userTemplate == null) {
			state = 1;
		}
		super.out.println("{\"state\":\""+state+"\",\"templateId\":\""+userTemplate.getTemplates().getTemplateId()+"\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(File menuImage) {
		this.menuImage = menuImage;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
}

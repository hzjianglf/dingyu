package com.wxpt.action.site;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.wxpt.common.FileUploadBean;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.service.EnterService;

public class VshopAction extends ParentAction {
	EnterInfor enter;
	EnterService enterService;
	private File banner;
	JSONArray jsonls;
	// 全局变量
	static String fileName;
	FileUploadBean bean = new FileUploadBean();
	public void uploadBanner() {

		String value = "";
		enter = enterService.getById(super.getCookieByEnterID());
		try {

			fileName = "banner.jpg";
			if (banner != null) {
				bean.upLoad(super.getCookieByEnterID()+fileName, banner,super.getCookieByEnterID()+"");
					String sql = "UPDATE webchat.enter_infor SET `banner`='"+super.getCookieByEnterID()+fileName+"' WHERE `enter_infor_id`="+super.getCookieByEnterID();
					enterService.updateVshopBanenr(sql);
					value = "1";
			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public EnterInfor getEnter() {
		return enter;
	}
	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}
	public EnterService getEnterService() {
		return enterService;
	}
	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}
	public File getBanner() {
		return banner;
	}
	public void setBanner(File banner) {
		this.banner = banner;
	}
	public JSONArray getJsonls() {
		return jsonls;
	}
	public void setJsonls(JSONArray jsonls) {
		this.jsonls = jsonls;
	}
	public static String getFileName() {
		return fileName;
	}
	public static void setFileName(String fileName) {
		VshopAction.fileName = fileName;
	}
	public FileUploadBean getBean() {
		return bean;
	}
	public void setBean(FileUploadBean bean) {
		this.bean = bean;
	}

}

package com.ticket.master.service.impl;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BCommonWebService;

public class VetechAsmsB2BCommonWebServiceImpl implements VetechAsmsB2BCommonWebService{
	ParentService parentService = new ParentServiceImpl();
	public String queryPic(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryPic").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryPic");
		}
		return parentService.getgonggongService("queryPic", xml, account, md5);
	}

	public String queryHelpMenu(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryHelpMenu").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryHelpMenu");
		}
		return parentService.getgonggongService("queryHelpMenu", xml, account, md5);
	}

	public String queryHelpContent(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryHelpContent").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryHelpContent");
		}
		return parentService.getgonggongService("queryHelpContent", xml, account, md5);
	}

	public String queryOffice_ggtz(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml, "queryOffice_ggtz").equals("ok")) {
			return yanzhengUser(account, key, xml, "queryOffice_ggtz");
		}
		return parentService.getgonggongService("queryOffice_ggtz", xml, account, key);
	}

	public String queryOffice_ggtz_detail(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml, "queryOffice_ggtz_detail").equals("ok")) {
			return yanzhengUser(account, key, xml, "queryOffice_ggtz_detail");
		}
		return parentService.getgonggongService("queryOffice_ggtz_detail", xml, account, key);
	}

	
	public String yanzhengUser(String account, String md5, String xml,String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml,method);
	}
}

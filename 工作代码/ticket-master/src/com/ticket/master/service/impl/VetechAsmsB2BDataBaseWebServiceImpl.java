package com.ticket.master.service.impl;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BDataBaseWebService;

public class VetechAsmsB2BDataBaseWebServiceImpl implements VetechAsmsB2BDataBaseWebService{


	
	private ParentService parentService=new ParentServiceImpl(); 
	/**
	 * 接口：获得城市信息
	 * 
	 */
	public String queryB_city(String account, String md5, String xml) {
		// TODO Auto-generated method stub
		if (!yanzhengUser(account, md5, xml, "queryB_city").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryB_city");
		}
		String result = parentService.getMothod2("queryB_city", xml, account, md5);
		return result;
	}
	/**
	 * 接口：获得航空公司
	 * 
	 */
	public String queryAirway(String account, String md5, String xml) {
		// TODO Auto-generated method stub
		if (!yanzhengUser(account, md5, xml, "queryAirway").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryAirway");
		}
		String result = parentService.getMothod2("queryAirway", xml, account, md5);
		return result;
	}
	
	public String queryLinks(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryLinks").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryLinks");
		}
		String result = parentService.getMothod2("queryLinks", xml, account, md5);
		return result;
	}
	public String queryParameter(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryParameter").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryParameter");
		}
		String result = parentService.getMothod2("queryParameter", xml, account, md5);
		return result;
	}
	public String queryDatebase(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryDatebase").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryDatebase");
		}
		String result = parentService.getMothod2("queryDatebase", xml, account, md5);
		return result;
	}
	public String queryBClass(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryBClass").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryBClass");
		}
		String result = parentService.getMothod2("queryBClass", xml, account, md5);
		return result;
	}
	public String queryPaySubject(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryPaySubject").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryPaySubject");
		}
		String result = parentService.getMothod2("queryPaySubject", xml, account, md5);
		return result;
	}
	public String queryCanPayPaySubject(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryCanPayPaySubject").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryCanPayPaySubject");
		}
		String result = parentService.getMothod2("queryCanPayPaySubject", xml, account, md5);
		return result;
	}
	public String queryFxsDatebase(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryFxsDatebase").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryFxsDatebase");
		}
		String result = parentService.getMothod2("queryFxsDatebase", xml, account, md5);
		return result;
	}
	public String yanzhengUser(String account, String md5, String xml,
			String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml, method);
	}
}

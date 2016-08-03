package com.ticket.master.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.common.Md5;
import com.ticket.master.dao.impl.UserDaoImpl;
import com.ticket.master.entity.User;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BInsuranceWebService;

public class VetechAsmsB2BInsuranceWebServiceImpl 
		implements VetechAsmsB2BInsuranceWebService {
	ParentService parentService=new ParentServiceImpl(); 
	UserDaoImpl userDaoImpl = new UserDaoImpl();
	public String queryInsuranceSpecies(String account, String key, String xml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '"+account+"'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if(user == null){
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户"+account+"不存在\"}}";
			}else if(key.equals(Md5.GetMd5(xml
									+ Md5.GetMd5(user.getPassword())))==false){
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return parentService.getInsuranceSpecies("queryInsuranceSpecies", xml,account,key);
	}
	public String createInsuranceOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"createInsuranceOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml,"createInsuranceOrder");
		}

		String result = parentService.getInsuranceSpecies("createInsuranceOrder", xml,
				account, md5);
		return result;
	}
	public String queryInsuranceOrder(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml,"queryInsuranceOrder").equals("ok")) {
			return yanzhengUser(account, key, xml,"queryInsuranceOrder");
		}

		String result = parentService.getInsuranceSpecies("queryInsuranceOrder", xml,
				account, key);
		return result;
	}
	public String createInsurance(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml,"createInsurance").equals("ok")) {
			return yanzhengUser(account, key, xml,"createInsurance");
		}

		String result = parentService.getInsuranceSpecies("createInsurance", xml,
				account, key);
		return result;
	}
	public String refunInsurance(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml,"refunInsurance").equals("ok")) {
			return yanzhengUser(account, key, xml,"refunInsurance");
		}

		String result = parentService.getInsuranceSpecies("refunInsurance", xml,
				account, key);
		return result;
	}
	public String cancelInsuranceOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"cancelInsuranceOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml,"cancelInsuranceOrder");
		}

		String result = parentService.getInsuranceSpecies("cancelInsuranceOrder", xml,
				account, md5);
		return result;
	}
	public String queryInsuranceJournal(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"queryInsuranceJournal").equals("ok")) {
			return yanzhengUser(account, md5, xml,"queryInsuranceJournal");
		}

		String result = parentService.getInsuranceSpecies("queryInsuranceJournal", xml,
				account, md5);
		return result;
	}
	public String queryInsuranceDetail(String account, String key, String xml) {
		if (!yanzhengUser(account, key, xml,"queryInsuranceDetail").equals("ok")) {
			return yanzhengUser(account, key, xml,"queryInsuranceDetail");
		}

		String result = parentService.getInsuranceSpecies("queryInsuranceDetail", xml,
				account, key);
		return result;
	}

	public String yanzhengUser(String account, String md5, String xml,String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml,method);
	}
}

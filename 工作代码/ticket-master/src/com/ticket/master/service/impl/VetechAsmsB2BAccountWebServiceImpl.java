package com.ticket.master.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.common.Md5;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BAccountWebService;

public class VetechAsmsB2BAccountWebServiceImpl implements
		VetechAsmsB2BAccountWebService {
	private ParentService parentService=new ParentServiceImpl(); 
	public String xykTopay(String account, String md5, String xml) {
		// TODO Auto-generated method stub
		
//		try {
//			Document document = DocumentHelper.parseText(xml);
//			Element root = document.getRootElement();
//			xml = xml.replace(root.elementText("LoginUserId"), account);
//			if(account.equals("SDJNHD18")){
//				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//						+"<Response>"
//						+"<Status>-1</Status>"
//						+"<Error>用户" + account
//						+ "不存在</Error>"
//						+"</Response>";
//			}else if(md5.equals(Md5.GetMd5(xml
//									+ Md5.GetMd5("28bbz8dhuh0dugt2egc4")))==false){
//				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//						+"<Response>"
//						+"<Status>-1</Status>"
//						+"<Error>校验出错，密钥验证的失败</Error>"
//						+"</Response>";
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		if (!yanzhengUser(account, md5, xml, "xykTopay").equals("ok")) {
			return yanzhengUser(account, md5, xml, "xykTopay");
		}
		
		return parentService.getPayInterface("xykTopay", xml,account,md5);
	}

	public String yanzhengUser(String account, String md5, String xml,
			String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml, method);
	}
}

package com.ibm.struts;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.UserInfo;
import com.ibm.hibernate.UserInfoDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoffAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(LogoffAction.class);
	private static final long serialVersionUID = 1L;
	UserInfoDAO dao = new UserInfoDAO();
	
	/**
	 * @return
	 */
	public String logoff() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo uinfo = new UserInfo();
		
		for(Iterator<UserInfo> i = dao.findByUsername((String)session.get("username")).iterator(); i.hasNext();) {			
			uinfo = (UserInfo)i.next();
			uinfo.setStatus("0");
			dao.update(uinfo);
			log.info("用户 " + uinfo.getUsername() + " 退出系统。");
		}		
		
		if (session != null) {
			session.clear();						
		} 		
		
		return SUCCESS;
	}
}
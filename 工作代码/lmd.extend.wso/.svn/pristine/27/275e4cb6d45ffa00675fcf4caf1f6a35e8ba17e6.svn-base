package lmd.extend.wso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.risesoft.soa.framework.session.SessionUser;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egov.appservice.ac.model.Resource;
import egov.appservice.ac.service.AccessControlService;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
@Controller
@RequestMapping
public class FwzcController{
	@Autowired
	  private SessionFactory sessionFactory;
  //分辨率小 显示两列
	@RequestMapping({"/fwzcportal.do"})
	  public String fwzcportal(HttpServletResponse res, HttpServletRequest req,@RequestParam(value = "id", required = false) String id)
	  {
		 String url="";
		  id = StringUtils.defaultIfEmpty(id, "_OlwmEQXnEeWkVNsIwPc8Aw");
		  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
		  try {
			  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
		      Map map = new HashMap();	
		      SessionUser su = (SessionUser)req.getSession().getAttribute("session.User");
		    //  System.out.println(">>>>>>>>su>>>>>>>>>"+su);
		      String uid = su.getUserUID();	   
		      
		      List r = acs.getSubResources(uid, "add", id, map);
		      if(0==r.size()){
		    	  url="http://10.10.10.207:8000/jsp/notice/default.jsp";
		      }else{
		    	  url="http://10.10.10.207:8000/jsp/wsoindex.jsp?id=_08o0UQ6NEeWobedMQdjMkA";
		      }
	      }catch (Exception e) {
	         e.printStackTrace();
	     }
		  return "redirect:"+url;
	  }
	
}
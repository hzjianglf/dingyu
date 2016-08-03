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
@RequestMapping({"/portlecontrol"})
public class PortletController{
  @Autowired
  private SessionFactory sessionFactory; 
  @RequestMapping({"/getcolumn.do"})
  public void getColumn(HttpServletResponse res, HttpServletRequest req,@RequestParam(value = "id", required = false) String id,@RequestParam(value = "userid", required = false) String userid){
	  id = StringUtils.defaultIfEmpty(id, "_OlwmEQXnEeWkVNsIwPc8Aw");
	  userid = StringUtils.defaultIfEmpty(userid, "_OlwmEQXnEeWkVNsIwPc8Aw");
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
      try {
	      AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	      Map map = new HashMap();	
//	      SessionUser su = (SessionUser)req.getSession().getAttribute("session.User");
//	      String uid = su.getUserUID();	  
	      System.out.println("<<<<<<<<<<<acs>>>>>>>>"+acs+"<<<>>>"+userid+"<<<<<<>>>>>"+id);
	      List r = acs.getSubResources(userid, "add", id, map);	      	      
	      StringBuffer treejson = new StringBuffer();
	      for (int j = 0; j < r.size(); j++) {
		      // 存在子节点
	    	  String url=((Resource)r.get(j)).getUrl();
	    	  treejson.append("{id:'" + ((Resource)r.get(j)).getUID() + "',");
	    	  treejson.append("text:'" + ((Resource)r.get(j)).getName() + "',");
	    	  treejson.append("url:'" + url + "'}");
          }	
	      
	      res.getWriter().write(treejson.toString());
      }catch (Exception e) {
         e.printStackTrace();
      }
  }
}
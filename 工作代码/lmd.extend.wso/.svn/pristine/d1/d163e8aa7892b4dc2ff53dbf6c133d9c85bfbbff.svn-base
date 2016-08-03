package lmd.extend.wso.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.framework.session.SessionConst;
import net.risesoft.soa.framework.session.SessionUser;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import egov.appservice.ac.exception.AccessControlException;
import egov.appservice.ac.model.Resource;
import egov.appservice.ac.service.AccessControlService;
import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
@Controller
@RequestMapping
public class PageController{
	@Autowired
	  private SessionFactory sessionFactory;
  //中间件监控
  @RequestMapping({"/zjjlist.html"})
  public ModelAndView getXtResource(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  String rid=req.getParameter("id")==null?"null":req.getParameter("id");
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	  String imagePath = req.getContextPath()+"/jsp/yyjk/images/";
	   Map map = new HashMap();	
	   List xtlist = acs.getSubResources(userid, "add", rid, map);
	   StringBuffer sb = new StringBuffer();
	   for(int i=0;i<xtlist.size();i++){
		   Resource reseach = (Resource) xtlist.get(i);
		   if(i%4==0){
			   sb.append("<tr>");
			   sb.append(" <td><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"show('"+reseach.getUrl()+"','"+reseach.getOpenType()+"')\" /><br /><a href=\"javascript:show('"+reseach.getUrl()+"','"+reseach.getOpenType()+"')\"><strong>"+reseach.getName()+"</strong></a></td>");
		   }else{
			   sb.append(" <td><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"show('"+reseach.getUrl()+"','"+reseach.getOpenType()+"')\" /><br /><a href=\"javascript:show('"+reseach.getUrl()+"','"+reseach.getOpenType()+"')\"><strong>"+reseach.getName()+"</strong></a></td>");
		   }
		   
		   if((i+1)%4==0){
			   sb.append("</tr>");
		   }
		  
	   }
	   ModelAndView mv = new ModelAndView("../../jsp/yyjk/zjjlist"); 
	   mv.addObject("trStr", sb.toString());
	   return mv;
  }
  //分辨率小 显示两列
  @RequestMapping({"/home_two.html"})
  public void getHome(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  String rid=req.getParameter("id")==null?"null":req.getParameter("id");
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	  String imagePath = req.getContextPath()+"/jsp/shouye/images/";
	   Map map = new HashMap();	
	   List xtlist = acs.getSubResources(userid, "add", rid, map);
	   StringBuffer sb = new StringBuffer();
	   for(int i=0;i<xtlist.size();i++){
		   Resource reseach = (Resource) xtlist.get(i);
		   if(i%2==0){
			   sb.append("<div class=\"homdiv\">");
			   sb.append("<div class=\"homdeme flaZ\">");
			   sb.append("	<a href=\"javascript:show('"+reseach.getUrl()+"')\" target=\"\" title=\""+reseach.getName()+"\"><img onclick=\"show('"+reseach.getUrl()+"')\" src=\""+imagePath+reseach.getIcon()+"\" alt=\""+reseach.getName()+"\"></a>");
			   sb.append("    <p class=\"homep\">"+reseach.getDescription()==null?"":reseach.getDescription()+"</p>");
			   sb.append(" </div>");
			}else{
				 sb.append("<div class=\"homdeme flaY\">");
				   sb.append("	<a href=\"javascript:show('"+reseach.getUrl()+"')\" target=\"\" title=\""+reseach.getName()+"\"><img onclick=\"show('"+reseach.getUrl()+"')\" src=\""+imagePath+reseach.getIcon()+"\" alt=\""+reseach.getName()+"\"></a>");
				   sb.append("    <p class=\"homep\">"+reseach.getDescription()==null?"":reseach.getDescription()+"</p>");
				   sb.append(" </div>");
		   }
		   
		   if((i+1)%2==0){
			   sb.append(" <div class=\"cler\"></div>");
			   sb.append(" </div>");
		   }
		  
	   }
//	   ModelAndView mv = new ModelAndView("../../jsp/shouye/home"); 
//	   mv.addObject("trStr", sb.toString());
	   res.getWriter().write(sb.toString());
//	   return mv;
  }
  
  //分辨率大显示三列
  @RequestMapping({"/home_three.html"})
  public void getHome2(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  String rid=req.getParameter("id")==null?"null":req.getParameter("id");
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	  String imagePath = req.getContextPath()+"/jsp/shouye/images/";
	   Map map = new HashMap();	
	   List xtlist = acs.getSubResources(userid, "add", rid, map);
	   StringBuffer sb = new StringBuffer();
	   int index=0;
	   for(int i=0;i<xtlist.size();i++){
		   Resource reseach = (Resource) xtlist.get(i);
		   index++;
		   if(i%3==0){
			   sb.append("<div class=\"homdiv\">");
			   sb.append("<div class=\"homdeme flaZ\">");
			   sb.append("	<a href=\"javascript:show('"+reseach.getUrl()+"')\" target=\"\" title=\""+reseach.getName()+"\"><img onclick=\"show('"+reseach.getUrl()+"')\" src=\""+imagePath+reseach.getIcon()+"\" alt=\""+reseach.getName()+"\"></a>");
			   sb.append("    <p class=\"homep\">"+reseach.getDescription()==null?"":reseach.getDescription()+"</p>");
			   sb.append(" </div>");
			}else{
				if(index==2){
					sb.append("<div class=\"homdeme flaZ homedL20\">");
					   sb.append("	<a href=\"javascript:show('"+reseach.getUrl()+"')\" target=\"\" title=\""+reseach.getName()+"\"><img onclick=\"show('"+reseach.getUrl()+"')\" src=\""+imagePath+reseach.getIcon()+"\" alt=\""+reseach.getName()+"\"></a>");
					   sb.append("    <p class=\"homep\">"+reseach.getDescription()==null?"":reseach.getDescription()+"</p>");
					   sb.append(" </div>");
				}
				 
				 if(index==3){
					   sb.append("<div class=\"homdeme flaY\">");
					   sb.append("	<a href=\"javascript:show('"+reseach.getUrl()+"')\" target=\"\" title=\""+reseach.getName()+"\"><img onclick=\"show('"+reseach.getUrl()+"')\" src=\""+imagePath+reseach.getIcon()+"\" alt=\""+reseach.getName()+"\"></a>");
					   sb.append("    <p class=\"homep\">"+reseach.getDescription()==null?"":reseach.getDescription()+"</p>");
					   sb.append(" </div>");
					}
		   }
		   
		   if((i+1)%3==0){
			   sb.append(" <div class=\"cler\"></div>");
			   sb.append(" </div>");
			   index=0;
		   }
		  
	   }
//	   ModelAndView mv = new ModelAndView("../../jsp/shouye/home"); 
//	   mv.addObject("trStr", sb.toString());
	   res.getWriter().write(sb.toString());
//	   return mv;
  }
  
//分辨率大显示三列
  @RequestMapping({"/app_portal.html"})
	  public ModelAndView getAppPortal(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  String rid=req.getParameter("id")==null?"null":req.getParameter("id");
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	  String imagePath = req.getContextPath()+"/jsp/yyjk/images/";
	   Map map = new HashMap();	
	   List xtlist = acs.getSubResources(userid, "add", rid, map);
	   StringBuffer sb = new StringBuffer();
	   for(int i=0;i<xtlist.size();i++){
		   Resource reseach = (Resource) xtlist.get(i);
		   if(i%4==0){
			   sb.append("<tr>");
			   sb.append(" <td><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"show('"+reseach.getUrl()+"')\" /><br /><a href=\"javascript:show('"+reseach.getUrl()+"')\">"+reseach.getName()+"</a></td>");
		   }else{
			   sb.append(" <td><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"show('"+reseach.getUrl()+"')\" /><br /><a href=\"javascript:show('"+reseach.getUrl()+"')\">"+reseach.getName()+"</a></td>");
		   }
		   
		   if((i+1)%4==0){
			   sb.append("</tr>");
		   }
		  
	   }
	   System.out.println(sb.toString());
	   ModelAndView mv = new ModelAndView("../../jsp/appportal/app_portal"); 
	   mv.addObject("trStr", sb.toString());
	   return mv;
  }
}
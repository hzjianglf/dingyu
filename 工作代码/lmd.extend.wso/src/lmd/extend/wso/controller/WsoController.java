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
public class WsoController{
	@Autowired
	  private SessionFactory sessionFactory;
	
/*  @RequestMapping({"/index.do"})
  public void index(HttpServletResponse res, HttpServletRequest req){
    try{
      res.sendRedirect("/jsp/index.jsp");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/
  public String getRes(String userid,String rid){
	  //{id:'_I4eyAQYaEeWEv4RnsAlS-A',text:'标准规范查询',url:'/fulltext/',openType:'panel',leaf:true},
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  StringBuffer treejson = new StringBuffer();
	  try {
		AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
		   Map map = new HashMap();	
		   List r = acs.getSubResources(userid, "add", rid, map);
		 
		      for (int j = 0; j < r.size(); j++) {
		    	   List s = acs.getSubResources(userid, "add", ((Resource)r.get(j)).getUID(), map);
		  		 
		    	       String url=((Resource)r.get(j)).getUrl();
		    
		    		  treejson.append("{id:'" + ((Resource)r.get(j)).getUID() + "',");
			    	  treejson.append("text:'" + ((Resource)r.get(j)).getName() + "',");
			    	  treejson.append("url:'" +url + "',");
			    	  treejson.append("openType:'"+ ((Resource)r.get(j)).getOpenType() +"',");
			    		if(s.size()>0){
			    			 treejson.append("leaf:false,children:[]}");
			    			 for(int j1=0;j1<s.size();j1++){
			    				 
			    			 }
				    	}
			    		else{
			    			
			    			 treejson.append("leaf:true}");
			    		}
		
		    	 
		    	  if (j != r.size() - 1) {
		    		  treejson.append(",");
		    	  }
	      }
		     	
	} catch (ServiceClientException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return treejson.toString();
  }
 
 
  @RequestMapping({"/operlist.do"})
  public void operlist(HttpServletResponse res, HttpServletRequest req,@RequestParam(value = "id", required = false) String id)
  {
	  id = StringUtils.defaultIfEmpty(id, "_OlwmEQXnEeWkVNsIwPc8Aw");
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
      try {
	      AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	      Map map = new HashMap();	
	      SessionUser su = (SessionUser)req.getSession().getAttribute("session.User");
	    //  System.out.println(">>>>>>>>su>>>>>>>>>"+su);
	      String uid = su.getUserUID();	   
	      
	      List r = acs.getSubResources(uid, "add", id, map);
	      	      
	      StringBuffer treejson = new StringBuffer();
	      treejson.append("[{");
	      treejson.append("id:'001',");
	      treejson.append("text:'\u529f\u80fd\u8282\u70b9',");//功能节点
	      treejson.append("url:'',");
	      treejson.append("leaf:false,");	      
	      treejson.append("children:[");
	      for (int j = 0; j < r.size(); j++) {
		    	 // Map map1 = new HashMap();	
		    	//  List s = acs.getSubResources(uid, "add", ((Resource)r.get(j)).getUID(), map1);
		    	  //存在子节点

	    	  String url=((Resource)r.get(j)).getUrl();
	    	  treejson.append("{id:'" + ((Resource)r.get(j)).getUID() + "',");
	    	  treejson.append("text:'" + ((Resource)r.get(j)).getName() + "',");
	    	  treejson.append("url:'" + url + "',");
	    	  treejson.append("openType:'"+ ((Resource)r.get(j)).getOpenType() +"',");
	    	 
	    	  
	    	  try{
	    		  	 List s = acs.getSubResources(uid, "add", ((Resource)r.get(j)).getUID(), map);
	    		  	//存在子节点
		    	  if(s.size()>0){
		    		  treejson.append("leaf:false,children:[");
		    		//  r= acs.getSubResources(uid, "add", ((Resource)r.get(j)).getUID(), map);
		    		  for(int z=0;z<s.size();z++){
		    			  treejson.append("{id:'" + ((Resource)s.get(z)).getUID() + "',");
				    	  treejson.append("text:'" + ((Resource)s.get(z)).getName() + "',");
				    	  treejson.append("url:'" + ((Resource)s.get(z)).getUrl() + "',");
				    	  treejson.append("openType:'"+ ((Resource)s.get(z)).getOpenType() +"',");
				    	  List s1 = acs.getSubResources(uid, "add", ((Resource)s.get(z)).getUID(), map);
				    	  if(s1.size()>0){
				    		  treejson.append("leaf:false,children:[");
				    		  for(int z1=0;z1<s1.size();z1++){
				    			  treejson.append("{id:'" + ((Resource)s1.get(z1)).getUID() + "',");
						    	  treejson.append("text:'" + ((Resource)s1.get(z1)).getName() + "',");
						    	  treejson.append("url:'" + ((Resource)s1.get(z1)).getUrl() + "',");
						    	  treejson.append("openType:'"+ ((Resource)s1.get(z1)).getOpenType() +"',");
						    	 
						    	  List s2= acs.getSubResources(uid, "add", ((Resource)s1.get(z1)).getUID(), map);
						    	  if(s2.size()>0){
						    		  treejson.append("leaf:false,children:[");
						    		  for(int z2=0;z2<s2.size();z2++){
						    			  treejson.append("{id:'" + ((Resource)s2.get(z2)).getUID() + "',");
								    	  treejson.append("text:'" + ((Resource)s2.get(z2)).getName() + "',");
								    	  treejson.append("url:'" + ((Resource)s2.get(z2)).getUrl() + "',");
								    	  treejson.append("openType:'"+ ((Resource)s2.get(z2)).getOpenType() +"',");
								    	  treejson.append("leaf:true}"); 
								    	  if (z2 != s2.size() - 1) {
								    		  treejson.append(",");
								    	  }
						    		  }
						    		  treejson.append("]}");
						    	  }
						    	  else{
						    		  treejson.append("leaf:true}"); 
						    
						    	  }
						    	  if (z1 != s1.size() - 1) {
						    		  treejson.append(",");
						    	  }
				    		  }
				    		  treejson.append("]}");
				    	  }
				    	  else{
				    		  treejson.append("leaf:true}");
				    	  }
				    	  if (z != s.size() - 1) {
				    		  treejson.append(",");
				    	  }
		    		  }
		    		 
		    		  treejson.append("]}");
		    		 
		    	  }
		    	  else{
		    		  treejson.append("leaf:true}"); 
		    	  }
	    		  
	    	  }catch(Exception e){}
		  

	    	 
	    	  if (j != r.size() - 1) {
	    		  treejson.append(",");
	    	  }
      }
	      treejson.append("]");
	      treejson.append("}]");	
	      System.out.print("<><><><><>"+treejson);
	      res.getWriter().write(treejson.toString());
     }catch (Exception e) {
         e.printStackTrace();
     }
  }
  
  
  @RequestMapping({"/index.html"})
  public ModelAndView getResource(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  String rid = "_MIfoQGW0EeWsur5iomzP9Q";//服务器id
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	   Map map = new HashMap();	
	   List r = acs.getSubResources(userid, "add", rid, map);
	   List<Resource> rlist = new ArrayList<Resource>();
	   List<List> alllist = new ArrayList<List>();
	   List eachlist = new ArrayList();
	   List resIds = new ArrayList();
	   for(int i=0;i<r.size();i++){
		   Resource resource = (Resource) r.get(i);
		   System.out.println(resource.getName());
//		   boolean ishas = acs.hasPermission(userid, resource.getUID(), "add");
		   eachlist = acs.getSubResources(userid, "add", resource.getUID(), map);
		   for(int j=0;j<eachlist.size();j++){
			   Resource reseach = (Resource) eachlist.get(j);
			   resIds.add(reseach.getUID());
		   }
		   String[] ary=(String[]) resIds.toArray(new String[resIds.size()]);
//		   for(int j=0;j<eachlist.size();j++){
//			   Resource reseach = (Resource) eachlist.get(j);
//			   System.out.println(reseach.getName()+" "+reseach.getUID());
//			   boolean ishas2 = acs.hasPermission(userid, reseach.getUID(), "add");
//			   System.out.println(ishas);
//		   }
		   alllist.add(eachlist);
		   rlist.add(resource);
	   }
	   
	   List ptlist = acs.getSubResources(userid, "add", "_7iYQkX3bEeWCULhNoJUBDQ", map);//获取平台管理
	   ModelAndView mv = new ModelAndView("../../index2"); 
	   mv.addObject("rlist", rlist);
	   mv.addObject("alllist", alllist);
	   mv.addObject("ptlist", ptlist);
	   return mv;
  }
  
  
  @RequestMapping({"/pie.do"})
  public void getQueryStr(HttpServletResponse res, HttpServletRequest req) throws IOException{
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs0 = null;
  	  ResultSet rs1 = null;
  	  ResultSet rs2 = null;
  	  String name = "";
  	  String id = "";
  	  String xjid="";
  	  String shornum = "";
  	  String value = "";
  	 StringBuffer sb = new StringBuffer();
	  sb.append("<chart caption='全省服务类别统计' palette='2' showValues='0'  animation='1' formatNumberScale='0'  pieSliceDepth='30' startingAngle='125' baseFontSize='15'  bgColor='#AAC8D7'>");
	  try {
		conn = session.connection();
		st= conn.createStatement();
		int count=0;
		//获取十大类    全省服务组件资源目录 id;_08o0UQ6NEeWobedMQdjMkA
		rs0 = st.executeQuery("select name,id,aliasname from ac_resource where parent_id='_08o0UQ6NEeWobedMQdjMkA'");
		while (rs0.next()) {
			name = rs0.getString("name");
			id = rs0.getString("id");
			shornum = rs0.getString("aliasname");
			//获取每一个大类下的二级类
			conn = session.connection();
			st= conn.createStatement();
			rs1 = st.executeQuery("select id from ac_resource where parent_id='"+id+"'");
			while (rs1.next()){
				xjid=rs1.getString("id");
				//统计每一个二级类下的数据
				conn = session.connection();
				st= conn.createStatement();
				rs2 = st.executeQuery("select count(*) count from ac_resource where parent_id='"+xjid+"'");
				while (rs2.next()){
					count += Integer.parseInt(rs2.getString("count"));
				}
			}
//			System.out.println(shornum+" "+name+":"+count);
			//infoID是公共模板ID 写的死值
			String url="/info/index.jsp?infoID=_y_0ZkFePEeWBastpJ6SSjA&shortacnum="+shornum+"&acid="+id;
			sb.append("<set label='"+name+"' value='"+count+"' isSliced='1' link='javascript:openUrl(\""+url+"\")'/>");
//			sb.append("<set label='"+name+"' value='"+count+"' isSliced='1' link='n- /info/index.jsp?infoID=_y_0ZkFePEeWBastpJ6SSjA&shortacnum="+shornum+"&acid="+id+"'/>");
			count=0;
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally{
		try {
			conn.close();
			rs0.close();
			rs1.close();
			rs2.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	  sb.append("</chart>");
	  res.getWriter().write(sb.toString());
  }
  //应用系统资源
  @RequestMapping({"/xtlist.html"})
  public ModelAndView getXtResource(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  String rid=req.getParameter("id")==null?"null":req.getParameter("id");
	  SessionUser sessionUser = (SessionUser)req.getSession().getAttribute(SessionConst.USER);
	  String userid = sessionUser.getUserUID();
	  ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
	  AccessControlService acs = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
	  String imagePath = req.getContextPath()+"/jsp/yyxtzy/images/";
	   Map map = new HashMap();	
	   List xtlist = acs.getSubResources(userid, "add", rid, map);
	   StringBuffer sb = new StringBuffer();
	   for(int i=0;i<xtlist.size();i++){
		   Resource reseach = (Resource) xtlist.get(i);
		   if(i%4==0){
			   sb.append("<tr>");
			   sb.append(" <td  width=\"25%\"><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"linkurl('"+reseach.getUrl()+"')\" /><br /><a href=\"javascript:linkurl('"+reseach.getUrl()+"')\"><strong>"+reseach.getName()+"</strong></a>")
			   .append("<div style=\"text-align:left; font-size:12px; color:#A3A3A3; font-family:'宋体'; padding:0px 10px;\">")
			   .append("<b>").append(reseach.getDescription()==null?"暂无描述":reseach.getDescription()).append("</b>")
			   .append("</div></td>");
		   }else{
			   sb.append(" <td   width=\"25%\"><img src=\""+imagePath+reseach.getIcon()+"\" onclick=\"linkurl('"+reseach.getUrl()+"')\" /><br /><a href=\"javascript:linkurl('"+reseach.getUrl()+"')\"><strong>"+reseach.getName()+"</strong></a>")
			   .append("<div style=\"text-align:left; font-size:12px; color:#A3A3A3; font-family:'宋体'; padding:0px 10px;\">")
			   .append("<b>").append(reseach.getDescription()==null?"暂无描述":reseach.getDescription()).append("</b>")
			   .append("</div></td>");
		   }
		   
		   if((i+1)%4==0){
			   sb.append("</tr>");
		   }
		  
	   }
	   ModelAndView mv = new ModelAndView("../../jsp/yyxtzy/xtlist"); 
	   mv.addObject("trStr", sb.toString());
	   return mv;
  }
}
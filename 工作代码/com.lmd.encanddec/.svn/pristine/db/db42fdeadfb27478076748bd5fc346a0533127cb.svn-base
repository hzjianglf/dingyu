package com.lmd.encanddec.manager.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmd.encanddec.service.PermissionService;
import com.lmd.encanddec.service.QueryService;

import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
import egov.appservice.org.service.PersonManager;
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	  private SessionFactory sessionFactory;
	@Override
	public Boolean hasPermission(String username, String wsdl) {
		boolean has = false;
	  	 ServiceClient serviceClient = ServiceClientFactory.getServiceClient();
		  try {
			  Session session = this.sessionFactory.openSession();
		      Connection conn = null; 
		  	  Statement  st= null;
		  	  ResultSet rs0 = null;
		  	  conn = session.connection();
			  st= conn.createStatement();
			  PersonManager personManager = (PersonManager)serviceClient.getServiceByName("org.PersonManager");
			  String actorUID = personManager.getPersonByLoginName(username).getUID();
			  System.out.println(actorUID);
			 StringBuffer sql = new StringBuffer();
//          	sql.append("select shornum from info_main_ggmb where zydz='"+zydz+"'");
          	sql.append("select ac.id resourceUID from ac_resource ac where exists ");
          	sql.append("( select * from info_main_ggmb where zydz='").append(wsdl).append("'and shortacnum=ac.aliasname)");
          	//sql.append("( select * from info_main_ggmb where mburl='").append(zydz).append("'and shortacnum=ac.aliasname)");//部署时用这个
          	//获取资源地址编号并且 在 ac_resource 获取 resourceUID 信息发布组件
			//获取十大类    全省服务组件资源目录 id;_08o0UQ6NEeWobedMQdjMkA
			rs0 = st.executeQuery(sql.toString());
			String resourceUID="";
			while(rs0.next()){
				 resourceUID=rs0.getString("resourceUID");
			}
          	
          //1，判断用户是否有权限
        	sql.setLength(0);
        	sql.append("select count(*) count from ac_permission");
        	sql.append(" where actor_id='").append(actorUID).append("'");
        	sql.append(" and resource_id='").append(resourceUID).append("'");
        	sql.append(" and operation like '%add%'");
        	rs0 = st.executeQuery(sql.toString());
        	String count="";
			while(rs0.next()){
				count=rs0.getString("COUNT");
			}
        	System.out.println(actorUID+"  "+resourceUID);
        	if("0".equals(count)){
        		//2,判断用户所属角色 是否有权限:获取用户角色Id
        		sql.setLength(0);
        		sql.append("select count(*) count from ac_permission ac");
        		sql.append(" where exists(select * from org_role_persons orp ");
        		sql.append(" where ac.actor_id = orp.org_role_id and orp.persons_id='").append(actorUID).append("'  )");
        		sql.append(" and resource_id='").append(resourceUID).append("'");
        		sql.append(" and operation like '%add%'");
        		rs0 = st.executeQuery(sql.toString());
    			while(rs0.next()){
    				count=rs0.getString("COUNT");
    			}
        		if("0".equals(count)){
        			//3，判断用户所属组是否有权限：获取用户组ID
        			sql.setLength(0);
        			sql.append(" select count(*) count from ac_permission ac");
        			sql.append(" where exists( select * from org_group_persons ogp ");
        			sql.append(" where ac.actor_id = ogp.org_group_id and ogp.persons_id='").append(actorUID).append("')");
        			sql.append(" and resource_id='").append(resourceUID).append("'");
        			sql.append(" and operation like '%add%'");
        			rs0 = st.executeQuery(sql.toString());
        			while(rs0.next()){
        				count=rs0.getString("COUNT");
        			}
        			if("0".equals(count)){
//        				 SOAPException soapExc = new SOAPException("您不具备操作该资源的权限！");  
//                         throw new Fault(soapExc);  
        				has=false;
        			}else{
        			has=true;	
        			}
        		}else{
        			has=true;
        		}
        	}else{
        		has=true;
        	}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		// TODO 自动生成的方法存根
		return has;
	}

}

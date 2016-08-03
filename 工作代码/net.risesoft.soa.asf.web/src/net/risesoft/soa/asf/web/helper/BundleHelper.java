package net.risesoft.soa.asf.web.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.risesoft.soa.framework.bizlog.PageRecord;
import net.risesoft.soa.framework.service.sso.log.LoginLog;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.stereotype.Component;

@Component
public class BundleHelper implements BundleContextAware
{
  
  @Autowired
  private SessionFactory sessionFactory;
	
  private BundleContext bundleContext;

  public void setBundleContext(BundleContext context)
  {
    this.bundleContext = context;
  }

  public boolean matches(Bundle b, String filter) {
    String symName = b.getSymbolicName();
    String name = (String)b.getHeaders().get("Bundle-Name");
    return ((symName.contains(filter)) || ((name != null) && (name.contains(filter))));
  }

  public String toStateString(int bundleState) {
    switch (bundleState)
    {
    case 2:
      return "已安装";
    case 4:
      return "已解析";
    case 8:
      return "启动中";
    case 32:
      return "已启动";
    case 16:
      return "停止中";
    case 1:
      return "已卸载";
    }
    return "未知状态: " + bundleState;
  }

  public boolean isDevMode()
  {
    String osgiDev = System.getProperty("osgi.dev");
    return ((osgiDev != null) && (osgiDev.length() > 0));
  }

  public String getRC7Version() {
    BundleContext ctx = this.bundleContext;
    Bundle bundle = ctx.getBundle();
    String bundleVer = (String)bundle.getHeaders().get("Bundle-Version");
    return "RC7-7.0.1 Build: " + bundleVer.substring(bundleVer.lastIndexOf(46) + 1);
  }

  public String getOSGiConsole() {
    return System.getProperty("osgi.console");
  }
  
  public List findUploadComponent(){
	List uploadcomponent = new ArrayList();
    Session session = this.sessionFactory.openSession();
    try {
    	Connection conn = session.connection(); // createQuery("from LoginLog t where t.userDN like ? or t.loginName like ? order by t.loginTime desc");
    	Statement  st=conn.createStatement();
    	ResultSet  rs = st.executeQuery("select * from asf_uploadbundles where isinstall='1'");
    	while(rs.next()){
        	Map map = new HashMap();
        	map.put("name", rs.getString("NAME"));
        	map.put("description", rs.getString("DESCRIPTION"));
        	map.put("isinstall", rs.getString("ISINSTALL"));
        	map.put("operation", ("1").equals(rs.getString("ISINSTALL"))?"<a href=\"javascript:operation(\'setup\',\'"+rs.getString("ID")+"\')\">安装</a>":"已安装");//不是从数据库里面调出
        	map.put("delete","<a href=\"javascript:operation(\'delete\',\'"+rs.getString("ID")+"\')\">删除</a>");//不是从数据库里面调出
        	uploadcomponent.add(map);    		
    	}    	    	
    	rs.close();
    	st.close();
    	conn.close();
    }catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
      session.close();
    }
    return uploadcomponent;
  }

}
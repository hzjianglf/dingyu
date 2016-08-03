package net.risesoft.soa.asf.egov;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import net.risesoft.soa.framework.service.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egov.appservice.asf.exception.ServiceManagerException;
import egov.appservice.asf.service.BundleManager;
 public class BundleManagerImpl   implements BundleManager{
	 private static final Logger log = LoggerFactory.getLogger(BundleManagerImpl.class);
	 public String setupComponent(String id) throws ServiceManagerException{
		 //id 为数据库中该条数据的id
        String url_str = getSystemURI()+"/asf/bundles/setup/"+id;
        return this.opration(url_str);
	 }
	 public String startComponent(String bundleid) throws ServiceManagerException{
		//java如何通过url调用远程接口并读取返回信息?
	    String url_str = getSystemURI()+"/asf/bundles/start/"+bundleid;
	    return this.opration(url_str);
	 }
	 public String stopComponent(String bundleid) throws ServiceManagerException{
		  //java如何通过url调用远程接口并读取返回信息?
          String url_str = getSystemURI()+"/asf/bundles/stop/"+bundleid;
          return this.opration(url_str);
	 }
	 public String logoutComponent(String bundleid) throws ServiceManagerException{
		 //java如何通过url调用远程接口并读取返回信息?
        String url_str = getSystemURI()+"/asf/bundles/logout/"+bundleid;
        return this.opration(url_str);
	 }    
//   public String uploadComponent(String uid) throws ServiceManagerException {	   
//	   return "";
//   }
	 public String getSystemURI(){
	       URL sysConfigUrl = Config.getConfigFileAsURL("/META-INF/config/system.properties");
	       String systemuri="";
	       if (sysConfigUrl != null) {
	            Properties prop = new Properties();
	            try {
	               prop.load(sysConfigUrl.openStream());
	            } catch (Exception ex) {
	               System.err.println("IO Error: " + ex.getMessage());
	            }
	            Properties directProp = System.getProperties();
	            systemuri=directProp.getProperty("system.uri");
	        }       
		    return systemuri;
	 }
	 public String opration(String url_str){
		    String returnmess="{status: \"error\", message:\"unknown\"}";
	        try {
				URL url = new URL(url_str);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.connect();
				int code = connection.getResponseCode();
				if (code == 404) {
				    throw new Exception("认证无效，找不到此次认证的会话信息！");
				}
				if (code == 500) {
				    throw new Exception("认证服务器发生内部错误！");
				}
				if (code != 200) {
				    throw new Exception("发生其它错误，认证服务器返回 " + code);
				}				
		        InputStream is = connection.getInputStream();
		        byte[] response = new byte[is.available()];
		        is.read(response);
		        is.close();
		        if (response == null || response.length == 0) {
		            throw new Exception("认证无效，找不到此次认证的会话信息！");
		        }
		        returnmess = new String(response, "UTF-8");
		        log.info(returnmess);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
	        return returnmess;
	   }
 }

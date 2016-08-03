package com.lmd.interceptor.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPException;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lmd.encanddec.service.IpService;
import com.lmd.encanddec.service.QueryService;
import com.lmd.encanddec.util.ProperUtil;

import egov.appservice.ac.service.AccessControlService;
import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
import egov.appservice.info.InfoService;
import egov.appservice.org.exception.AuthenticateFailException;
import egov.appservice.org.model.Person;
import egov.appservice.org.service.AuthenticateService;
import egov.appservice.org.service.PersonManager;

import com.lmd.encanddec.manager.JjmServiceManager;  
  
public class AuthIntercetpr extends AbstractPhaseInterceptor<SoapMessage> {  
    public static final String xml_namespaceUR_att = "http://gd.chinamobile.com//authentication";  
    public static final String xml_header = "soap:Header";  
    public static final String xml_authentication = "auth:authentication";  
    public static final String xml_sysname = "auth:sysname";  //系统名
    public static final String xml_password = "auth:password";  //密码
    protected Logger log = LoggerFactory.getLogger(super.getClass());
    private AuthenticateService authService;
    private QueryService queryService ;
    private ServiceClient serviceClient;
    private PersonManager personManager;
    private AccessControlService accessControlService;
    private IpService ipService;
    private InfoService infoService;
    public AuthIntercetpr() {  
        // 指定该拦截器在哪个阶段被激发  
        super(Phase.PRE_INVOKE);  
    }  
  
    // 处理消息  
    public void handleMessage(SoapMessage message) {  
    	HttpServletRequest request =(HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
    	HttpServletResponse response =(HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
    	String bjip = request.getLocalAddr();
    	System.out.println("本机IP："+bjip);
    	String address="";
    	String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
//            System.out.println(ip);
        }
        address=ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;   
        System.out.println("客户端Ip："+address);
    	String mbip=ProperUtil.getProperty("asf.mip");
    	if(mbip.contains(address)){
    		
    	}else{
    		SOAPException soapExc = new SOAPException("该路径没有调用此服务的权限，请联系管理员");  
    		throw new Fault(soapExc);  
    	}
    	//获取资源地址
//    	System.out.println("---------------/////////////////////------------");
//    	String zydz=(message.get("org.apache.cxf.request.url")+"?wsdl");
//    	System.out.println(zydz);
        // 获取SOAP消息的全部头  
//        List<Header> headers = message.getHeaders();  
//        if (null == headers || headers.size() < 1) {  
//            throw new Fault(new SOAPException("SOAP消息头格式不正确！"));  
//        }  
//        for (Header header : headers) {  
//            SoapHeader soapHeader = (SoapHeader) header;  
//            // 取出SOAP的Header元素  
//            Element element = (Element) soapHeader.getObject();  
//            XMLUtils.printDOM(element);  
//            NodeList userIdNodes = element  
//                    .getElementsByTagName(xml_sysname);  
//            NodeList pwdNodes = element  
//                    .getElementsByTagName(xml_password);  
//        	String userName = userIdNodes.item(0).getTextContent();
//        	String passWord = pwdNodes.item(0).getTextContent();
//            if(authenticate(userName, passWord)){//验证系统名和密码通过
//            	//获取actorUID 根据用户登录名  获取 操作者唯一标识 actorUID  人员服务组件查询获取（search）
//            	String actorUID = personManagerData(userName);
////            	System.out.println(actorUID);
//            	
//            	StringBuffer sql = new StringBuffer();
////            	sql.append("select shornum from info_main_ggmb where zydz='"+zydz+"'");
//            	sql.append("select ac.id resourceUID from ac_resource ac where exists ");
//            	sql.append("( select * from info_main_ggmb where zydz='").append(zydz).append("'and shortacnum=ac.aliasname)");
//            	//sql.append("( select * from info_main_ggmb where mburl='").append(zydz).append("'and shortacnum=ac.aliasname)");//部署时用这个
//            	//获取资源地址编号并且 在 ac_resource 获取 resourceUID 信息发布组件
//            	String resourceUID=queryServiceData(sql.toString(),"resourceUID");
//
//            	//1，判断用户是否有权限
//            	sql.setLength(0);
//            	sql.append("select count(*) count from ac_permission");
//            	sql.append(" where actor_id='").append(actorUID).append("'");
//            	sql.append(" and resource_id='").append(resourceUID).append("'");
//            	sql.append(" and operation like '%add%'");
//            	
////            	String count=accessControlServiceData(actorUID,resourceUID);
//            	String count=queryServiceData(sql.toString(),"COUNT");
////            	System.out.println(actorUID+"  "+resourceUID);
//            	if("0".equals(count) || "".equals(count)){
//            		//2,判断用户所属角色 是否有权限:获取用户角色Id
//            		sql.setLength(0);
//            		sql.append("select count(*) count from ac_permission ac");
//            		sql.append(" where exists(select * from org_role_persons orp ");
//            		sql.append(" where ac.actor_id = orp.org_role_id and orp.persons_id='").append(actorUID).append("'  )");
//            		sql.append(" and resource_id='").append(resourceUID).append("'");
//            		sql.append(" and operation like '%add%'");
//            		count=queryServiceData(sql.toString(),"COUNT");
//            		if("0".equals(count)  || "".equals(count)){
//            			//3，判断用户所属组是否有权限：获取用户组ID
//            			sql.setLength(0);
//            			sql.append(" select count(*) count from ac_permission ac");
//            			sql.append(" where exists( select * from org_group_persons ogp ");
//            			sql.append(" where ac.actor_id = ogp.org_group_id and ogp.persons_id='").append(actorUID).append("')");
//            			sql.append(" and resource_id='").append(resourceUID).append("'");
//            			sql.append(" and operation like '%add%'");
//            			count=queryServiceData(sql.toString(),"COUNT");
//            			if("0".equals(count)  || "".equals(count)){
//            				 SOAPException soapExc = new SOAPException("您不具备操作该资源的权限！");  
//                             throw new Fault(soapExc);  
//            			}else{
//            				
//            			}
//            		}
//                	
//            	}
//            	
//            	
//            	
//            	
//            	
//            }
//            else {//认证失败则抛出异常，停止继续操作  
//                SOAPException soapExc = new SOAPException("用户名和密码不匹配！");  
//                throw new Fault(soapExc);  
//            }  
//              
//        }  
        
        
    }  
    
    private boolean authenticate(String userName, String password) {
        initServiceIfNeed();
        if (this.authService != null) {
          try {
            return this.authService.authenticate(userName, password).equals("true");
          } catch (AuthenticateFailException ex) {
            this.log.warn(ex.getMessage());
            return false;
          }
        }
        return false;
      }
    
    private void initServiceIfNeed() {
        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.authService != null) return;
        try {
          this.authService = ((AuthenticateService)this.serviceClient.getServiceByName("org.AuthenticateService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("unable init org.AuthenticateService!, retry next invoke.");
        }
      }
    
    public String queryServiceData(String sql,String getStr){
    	initQueryServiceIfNeed();
    	 if (this.queryService != null) {
    		 return this.queryService.getDataBySql(sql,getStr);
           }else{
        	   return "";
           }
    }
    
    private void initQueryServiceIfNeed() {
        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.queryService != null) return;
        try {
          this.queryService = ((QueryService)this.serviceClient.getServiceByName("encanddec.QueryService"));
//          this.queryService = ((QueryService)this.serviceClient.getServiceByWSDL("encanddec.QueryService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("queryService!, retry next invoke.");
        }
      }
    
    
    //查询是否有该资源权限
    private String accessControlServiceData(String actorUID,String resourceUID ){
    	initPersonManagerDatafNeed();
    	if (this.accessControlService != null) {
    		Map<String,String> map = new HashMap<String, String>();
    		List list = accessControlService.getSubResources(actorUID, "add", resourceUID, map);
   		 return list.size()+"";
          }else{
       	   return "0"; 
          }
    	
    }
    
    private void accessControlServiceDatafNeed(){

        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.accessControlService != null) return;
        try {
          this.accessControlService = ((AccessControlService)this.serviceClient.getServiceByName("ac.AccessControlService "));
//          this.queryService = ((QueryService)this.serviceClient.getServiceByWSDL("encanddec.QueryService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("AccessControlService!, retry next invoke.");
        }
      
    }
    
    //查询人员personid
    private String personManagerData(String loginName){
    	initPersonManagerDatafNeed();
    	if (this.personManager != null) {
    		Person person = personManager.getPersonByLoginName(loginName);
   		 return person.getUID();
          }else{
       	   return ""; 
          }
    	
    }
    
    private void initPersonManagerDatafNeed(){

        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.personManager != null) return;
        try {
          this.personManager = ((PersonManager)this.serviceClient.getServiceByName("org.PersonManager"));
//          this.queryService = ((QueryService)this.serviceClient.getServiceByWSDL("encanddec.QueryService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("personManager!, retry next invoke.");
        }
      
    }
    
  //查询人员personid
    private String infoServiceData(String condition){
    	infoServiceDatafNeed();
    	if (this.infoService != null) {
    		List list = infoService.getListByCondition("", 1);
    		if(list.size()>0){
    			return list.get(0).toString();
    		}else{
    			return "";
    		}
          }else{
       	   return ""; 
          }
    	
    }
    
    private void infoServiceDatafNeed(){

        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.infoService != null) return;
        try {
          this.infoService = ((InfoService)this.serviceClient.getServiceByName("info.InfoService"));
//          this.queryService = ((QueryService)this.serviceClient.getServiceByWSDL("encanddec.QueryService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("info.InfoService!, retry next invoke.");
        }
      
    }
    private void ipServiceDatafNeed(){

        if (this.serviceClient == null) {
          this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
        }
        if (this.ipService != null) return;
        try {
          this.ipService = ((IpService)this.serviceClient.getServiceByName("encanddec.IpService"));
//          this.queryService = ((QueryService)this.serviceClient.getServiceByWSDL("encanddec.QueryService"));
        } catch (ServiceClientException localServiceClientException) {
          this.log.warn("encanddec.IpService!, retry next invoke.");
        }
      
    }
}  
  
  
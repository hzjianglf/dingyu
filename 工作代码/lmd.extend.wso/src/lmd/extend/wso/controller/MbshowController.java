package lmd.extend.wso.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lmd.extend.wso.model.Mbshow;
import lmd.extend.wso.portlet.GetColumn;
import lmd.extend.wso.util.DateUtil;

import net.risesoft.soa.framework.service.config.Config;
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

import com.ibm.broker.config.proxy.ApplicationProxy;
import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.broker.config.proxy.MessageFlowProxy;

import egov.appservice.ac.exception.AccessControlException;
import egov.appservice.ac.model.Resource;
import egov.appservice.ac.service.AccessControlService;
import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
@Controller
@RequestMapping
public class MbshowController{
	@Autowired
	  private SessionFactory sessionFactory;
	
	@RequestMapping({"/mbshow.do"})//net.risesoft.soa.info��display/panel.jsp��display/panel2.jsp����
	  public ModelAndView mbShow(HttpServletRequest request){
		 String acnum=request.getParameter("acnum").trim();
		 String wmburi = "";
		 Session session = this.sessionFactory.openSession();
	      Connection conn = null; 
	  	  Statement  st= null;
	  	  ResultSet rs = null;
	  	  ResultSet rs2 = null;
	  	  Mbshow mb = new Mbshow();
	  	  mb.setZybh(acnum);//资源编号
	      try {
	    	  conn = session.connection();
		  	  st= conn.createStatement();   
			  rs = st.executeQuery("select  mbpz,acnum,acversion,xtbm,zymc,mburl from  info_main_ggmb  where acnum='"+acnum+"'");
			  while(rs.next()){
	        	  wmburi =rs.getString(1);
	        	  if(wmburi!=null&&!("").equals(wmburi)){       		  
	        		  wmburi = wmburi.trim()+":"+rs.getString(2);
	        		  if(rs.getString(3)!=null&&!("").equals(rs.getString(3))){
	        			  wmburi = wmburi.trim()+":"+rs.getString(3);
	        		  }
	        		  if(rs.getString(4)!=null&&!("").equals(rs.getString(4))){
	        			  wmburi = wmburi.trim()+":M"+rs.getString(4);
	        		  }
		    		  String wmburis[] = wmburi.split(":");// 例：wmburi=2414:IB9QMGR:default:ProxyService_ServiceProxy_Flows:FormServcieProxy
		    		  int listerport=Integer.parseInt(wmburis[0]) ;
		    		  String qmanage=wmburis[1] ;//节点名称
		    		  String egName=wmburis[2] ;//集成服务名称
		    		  String appName=wmburis[3] ;//服务名称
		    		  String flowName=wmburis[4] ;
		    		  mb.setJdmc(qmanage);
		    		  mb.setFwmc(appName);
		    		  mb.setZymc(rs.getString("ZYMC"));
		    		  mb.setMburl(rs.getString("MBURL"));
		    		  mb.setBbh(rs.getString("ACVERSION"));
		    		  String vversion="";
		    		  if(wmburis.length>4){
		    		     vversion=wmburis[5].replace(".", "_") ;
		    		  }
		    		  String sysnum ="";
		    		  if(wmburis.length>5){
		    			  sysnum=wmburis[6] ;
			    	  }
		    		  try {			 
		    			   BrokerConnectionParameters bcp = new MQBrokerConnectionParameters("10.10.10.207",listerport,qmanage);	           
		    			   BrokerProxy b = BrokerProxy.getInstance(bcp);		        		    	
		    		       if (b != null) {
		    				  ExecutionGroupProxy eg =b.getExecutionGroupByName(egName);
		    				  mb.setJcfwmc(egName);
		    				  if (eg != null) {				  
		    					  ApplicationProxy appproxy = eg.getApplicationByName(appName);
		    					  if(appproxy!=null){				  
		    						  MessageFlowProxy mf = appproxy.getMessageFlowByName(sysnum+"_"+flowName+"_"+vversion);	
		    						  mb.setLfwmc(mf.getFullName());//流程服务名称
		    						  String[] ary = mf.getRuntimePropertyNames();
		    						  mb.setDeployTime(mf.getDeployTime().toLocaleString());//部署时间
		    						  mb.setRuntime(DateUtil.getDatePoor(new Date(), mf.getDeployTime()));
		    						  mb.setAdditionalInstances((mf.getAdditionalInstances()+1)+"");//分配给消息流的附件线程实例数目
		    						  mb.setCommitCount(mf.getCommitCount()+"");//返回“提交数”参数配置的值，这是MQ消息处理在同步点取数。
		    						  mb.setCommitInterval(mf.getCommitInterval()+"");//返回提交区间参数配置的值，这是在MQ消息的时间有。
		    						  mb.setFileExtension(mf.getFileExtension());//返回与消息流相关的文件扩展名
		    						  mb.setStartMode(mf.getStartMode());//返回此消息流的启动模式的当前值
		    						  mb.setStatisticsAccountingOrigin(mf.getStatisticsAccountingOrigin(true));//返回消息流的会计原点  参数snapshot：快照
		    						  if(mf.isRunning()){
		    							  mb.setYxzt("已启动");
		    						  }else{
		    							  mb.setYxzt("已停止");
		    						  }
		    						  if (mf != null) {						 						  
					    		  		 boolean isRunning = mf.isRunning();		  		    		  		
					    		  			    		  				    		  		
		    						  } else {
		    							 System.err.println("No such flow "+sysnum+"_"+flowName+"_"+vversion);
		    						  }
		    					  }
		    		          } else {
		    		              System.err.println("No such exegrp "+egName+"!");
		    		          }   
		    			      b.disconnect();
		    			   }  
		    	       }catch(Exception ex) {
		    	          System.err.println("Comms problem! "+ex);
		    	       }  
	              }
	          }	 
			  conn = session.connection();
		  	  st= conn.createStatement();   
		  	 StringBuffer sql= new StringBuffer();
		  	sql.append("select a.*,b.*,c.* from");
		  	sql.append(" (select count(*)allcount,max(fn_timestamp_cha(enddate,startdate)) maxtime,");
		  	sql.append(" min(fn_timestamp_cha(enddate,startdate)) mintime,");
		  	sql.append(" avg(fn_timestamp_cha(enddate,startdate)) avgtime");
		  	sql.append(" from monitor_messageflow where strfind(attribute,'_',1,2)='").append(acnum).append("') a,");
		  	sql.append(" (select  count(*) errorcount from monitor_messageflow ");
		  	sql.append(" where strfind(attribute,'_',1,2)='").append(acnum).append("'");
		  	sql.append(" and resstatus ='500') b,");
		  	sql.append(" (select  count(*) sucesscount from monitor_messageflow ");
		  	sql.append(" where strfind(attribute,'_',1,2)='").append(acnum).append("'");
		  	sql.append(" and resstatus ='200') c");
			  rs2 = st.executeQuery(sql.toString());
			  while (rs2.next()){
				  String count = rs2.getInt("ALLCOUNT")+"";
				  String maxtime = rs2.getInt("MAXTIME")+" ms";
				  String mintime = rs2.getInt("MINTIME")+" ms";
				  String avgtime = rs2.getInt("AVGTIME")+" ms";
				  String errorcount = rs2.getInt("ERRORCOUNT")+"";
				  String sucesscount = rs2.getInt("SUCESSCOUNT")+"";
				  mb.setCount(count);
				  mb.setMaxtime(maxtime);
				  mb.setMintime(mintime);
				  mb.setAvgtime(avgtime);
				  mb.setErrorcount(errorcount);
				  mb.setSucesscount(sucesscount);
			  }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		 
		 
		 ModelAndView mv = new ModelAndView("../../mbshow"); 
		 mv.addObject("mb",mb);
		 return mv;
	}
	
	
	  public String getSystemHost(){// 获取系统所在主机host
	       URL sysConfigUrl = Config.getConfigFileAsURL("/META-INF/config/system.properties");
	       String systemhost="";
	       if (sysConfigUrl != null) {
	            Properties prop = new Properties();
	            try {
	               prop.load(sysConfigUrl.openStream());
	            } catch (Exception ex) {
	               System.err.println("IO Error: " + ex.getMessage());
	            }
	            Properties directProp = System.getProperties();
	            systemhost=directProp.getProperty("wmb.host");
	        }       
		    return systemhost;
	   }
	  
	  @RequestMapping({"/mbavg.do"})
	  public void getAvgTime(HttpServletRequest request,HttpServletResponse res){

			 String acnum=request.getParameter("acnum").trim();
			 StringBuffer avgtime = new StringBuffer();
			 Properties props = new Properties();
			 try {
				 URL url = Config.getConfigFileAsURL("time.properties");//必须每次读取
				 props.load(url.openStream());
				 avgtime.append(props.getProperty(acnum));
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				avgtime.append("0");
				e1.printStackTrace();
			}
			 
			 try {
				 if(null==avgtime || "null".equals(avgtime.toString()) || "".equals(avgtime.toString())){
					 res.getWriter().write("0");
				 }else{
					 res.getWriter().write(avgtime.toString());
				 }
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				
			}
		
	  }
}
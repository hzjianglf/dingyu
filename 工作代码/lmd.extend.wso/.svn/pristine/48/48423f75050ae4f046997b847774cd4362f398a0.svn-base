package lmd.extend.wso.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lmd.extend.wso.util.SbJsonUtil;

import net.risesoft.soa.framework.service.config.Config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.broker.config.proxy.ApplicationProxy;
import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.broker.config.proxy.MessageFlowProxy;
@Controller
@RequestMapping({"/mnicontro"})
public class MonitorController{
//  @Autowired
//  private MonitorMFDao monitorMFDao;
  @Autowired
  private SessionFactory sessionFactory;
  @RequestMapping({"/monitor.do"})
  public ModelAndView monitor(HttpServletResponse res, HttpServletRequest req){
      String strLine2D = null;
      Map<Integer, Integer> map = new HashMap();
      Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
	  try {
	      Calendar day = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      String date = sdf.format(day.getTime());	 
	      conn = session.connection();
	  	  st= conn.createStatement();   
	      for (int i = 0; i < 24; ++i) {
	          String hoursstart = null;
	          String hoursend = null;
	          if (i < 10){
	            hoursstart = date + " 0" + i;
	            hoursend = date + " 0" + (i+1);
	          }else {
	            hoursstart = date + " " + i;
	            hoursend   = date + " " + (i+1);
	          }	    
	          rs = st.executeQuery("select count(*) from monitor_messageflow  mmf where  to_char(mmf.startdate,'yyyy-MM-dd HH24:mm:ss')> '"+hoursstart+"' and to_char(mmf.startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
	          while(rs.next()){
		          map.put(Integer.valueOf(i),Integer.valueOf(rs.getString(1)));
	          }
	      }	      
	      strLine2D = "<graph caption='实时消息流数据折线图' subcaption='" + date + "' xAxisName='时间(按小时统计)' yAxisMinValue='0' yAxisName='Transactions' decimalPrecision='0' " + 
	        "formatNumberScale='0' numberPrefix='' showNames='1' showValues='0' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' " + 
	        "divLineAlpha='20' alternateHGridAlpha='5' baseFontSize='12' bgColor='E9E9E9'>";
	      for (int i = 0; i < 24; ++i) {
	        strLine2D = strLine2D + "<set name='" + i + "' value='" + map.get(Integer.valueOf(i)).toString() + "' hoverText='" + i + "点到" + (i + 1) + "点'/>";
	      }
	      strLine2D = strLine2D + "</graph>";
	   }catch (Exception re) {
//	      log.error("find all failed", re);
	   }  finally {
		   try {
			 rs.close();
			 st.close();
			 conn.close();
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		   }
		   session.close();
	   }
//	   req.setAttribute("strXMLmap",map);
	   return new ModelAndView("redirect:/jsp/FusionChartsRenderer.jsp?strXML="+URLEncoder.encode(URLEncoder.encode(strLine2D)));
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
  
  @RequestMapping({"/mfstatusoper.do"})//net.risesoft.soa.info��display/panel.jsp��display/panel2.jsp����
  public void mfStatusOper(HttpServletRequest request){
	  String infoid=request.getParameter("infoid");
	  String oper = request.getParameter("oper");
	  String wmburi = "";
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
	  try {	 
	      conn = session.connection();
	  	  st= conn.createStatement();     
	      rs = st.executeQuery("select  mbpz,acnum,acversion,xtbm,xuhao  from  info_main_ggmb  where id='"+infoid+"'");
          while(rs.next()){
        	  wmburi =rs.getString(1);
        	  String xuhao = rs.getString("XUHAO");
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
	    		  String qmanage=wmburis[1] ;
	    		  String egName=wmburis[2] ;
	    		  String appName=wmburis[3] ;
	    		  if(xuhao!=null&&!("").equals(xuhao)){
	    			  appName=appName+"_"+xuhao;
	    		  }
	    		  String flowName=wmburis[4] ;
	    		  String vversion="";
	    		  if(wmburis.length>4){
	    		     vversion=wmburis[5].replace(".", "_") ;
	    		  }
	    		  String sysnum ="";
	    		  if(wmburis.length>5){
	    			  sysnum=wmburis[6] ;
		    	  }
	    		  try {			 
	    			   BrokerConnectionParameters bcp = new MQBrokerConnectionParameters(getSystemHost(),listerport,qmanage);	           
	    			   BrokerProxy b = BrokerProxy.getInstance(bcp);		        		    	
	    		       if (b != null) {
	    				  ExecutionGroupProxy eg =b.getExecutionGroupByName(egName);
	    				  if (eg != null) {				  
	    					  ApplicationProxy appproxy = eg.getApplicationByName(appName);
	    					  if(appproxy!=null){				  
	    						  MessageFlowProxy mf = appproxy.getMessageFlowByName(sysnum+"_"+flowName+"_"+vversion);	
	    						  
	    						  if (mf != null) {						 						  
				    		  		 boolean isRunning = mf.isRunning();		  		    		  		
				    		  		 if (isRunning) {
				    		  			if(("disable").equals(oper)){
				    		  			   mf.stop();
				    		  			}
				    		  		 } else {
				    		  			if(("enable").equals(oper)){
					    		  		   mf.start();
					    		  	    }
				    		  		 }		    		  				    		  		
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
	   }catch (Exception re) {
//	      log.error("find all failed", re);
	   }finally{
		   try {
			 rs.close();
			 st.close();
			 conn.close();
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		   }
		   session.close();
	   }
  }
  @RequestMapping(value={"/getmbwsurl.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void getMBWSURL(HttpServletRequest request,HttpServletResponse response){
	  String infoid=request.getParameter("infoid");
	  String wmburi = "";
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
  	  String wmburl="";
	  try {	 
	      conn = session.connection();
	  	  st= conn.createStatement();     
	      rs = st.executeQuery("select  mbpz,acnum,acversion,xtbm,xuhao from  info_main_ggmb  where id='"+infoid+"'");
          while(rs.next()){
        	  wmburi =rs.getString(1);
        	  String xuhao = rs.getString("XUHAO");
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
	    		  String qmanage=wmburis[1] ;
	    		  String egName=wmburis[2] ;
	    		  String appName=wmburis[3] ;
	    		  if(xuhao!=null&&!("").equals(xuhao)){
	    			  appName=appName+"_"+xuhao;
	    		  }
	    		  String flowName=wmburis[4] ;
	    		  String vversion="";
	    		  if(wmburis.length>4){
	    		     vversion=wmburis[5].replace(".", "_") ;
	    		  }
	    		  String sysnum ="";
	    		  if(wmburis.length>5){
	    			  sysnum=wmburis[6] ;
		    	  }
	    		  try {			 
	    			   BrokerConnectionParameters bcp = new MQBrokerConnectionParameters(getSystemHost(),listerport,qmanage);	           
	    			   BrokerProxy b = BrokerProxy.getInstance(bcp);		        		    	
	    		       if (b != null) {
	    		    	  String httpport = b.getHTTPListenerProperty("HTTPConnector/port"); 	    		    	   
	    				  ExecutionGroupProxy eg =b.getExecutionGroupByName(egName);
	    				  if (eg != null) {		
	    					  String port=eg.getProperties().getProperty("ExecutionGroupRuntimeProperty/HTTPConnector/port");
	    					  ApplicationProxy appproxy = eg.getApplicationByName(appName);
	    					  if(appproxy!=null){				  
	    						  MessageFlowProxy mf = appproxy.getMessageFlowByName(sysnum+"_"+flowName+"_"+vversion);		
	    						  if (mf != null) {			    							  
	    							  String  nodepro[] = mf.getProperties().getProperty("messageflow.node.1").split(" ");	    							    
	    							  for(int i=0;i<nodepro.length;i++){		
    							    	 if(nodepro[i].indexOf("urlSelector=")>-1){
    							    		wmburl = "http://"+getSystemHost()+":"+port+nodepro[i].substring(12).replace("\"", "")+"?wsdl";	
    							    		st.executeUpdate("update info_main_ggmb set mburl='"+wmburl+"' where id='"+infoid+"'");
    							    	 }else if(nodepro[i].indexOf("URLSpecifier=")>-1){
    							    		wmburl = "http://"+getSystemHost()+":"+httpport+nodepro[i].substring(13).replace("\"", "");
    							    		st.executeUpdate("update info_main_ggmb set mburl='"+wmburl+"' where id='"+infoid+"'");
    							    	 }
	    							   }		    		  				    		  		
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
	   }catch (Exception re) {
//	      log.error("find all failed", re);
	   }finally{
		   try {
			 rs.close();
			 st.close();
			 conn.close();
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		   }
		   session.close();
	   }
	  PrintWriter out=null;
	  try {
			out = response.getWriter();
			out.write(wmburl); 
	  } catch (IOException e) {
			e.printStackTrace();
	  }   
  }
  
  @RequestMapping({"/xqtj.do"})
  public void getXqTj(HttpServletResponse res, HttpServletRequest req){
	  int index=0;
	  String[] array = {"0000AA","CD4F39","66CD00","00F5FF","8E388E","FF0000","B8860B","121212","DC143C","B03060"};
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
  	  ResultSet rs1 = null;
	  StringBuffer sb = new StringBuffer();
	  sb.append("<chart caption='服务调用统计' outCnvBaseFontSize ='11'  yAxisName='调用次数' xAxisName='时间'  numdivlines='20' lineThickness='2' showValues='0' numVDivLines='22' formatNumberScale='1' labelDisplay='ROTATE' slantLabels='1' anchorRadius='2' anchorBgAlpha='50' showAlternateVGridColor='1' anchorAlpha='100' animation='1' limitsDecimalPrecision='0' divLineDecimalPrecision='1'>");
	  sb.append("<categories >");
	  for (int i = 0; i < 24; ++i) {
		  sb.append("<category label='"+i+":00-"+(i+1)+":00' />"); 
//		  if(i<10){
//			  sb.append("<category label='0"+i+":00' />");  
//		  }else{
//			  sb.append("<category label='"+i+":00' />");
//		  }
		  
	  }
	  sb.append("</categories>");
	  try {
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String date = sdf.format(day.getTime());
		String date = req.getParameter("date")==null?sdf.format(day.getTime()):req.getParameter("date");
		conn = session.connection();
		st = conn.createStatement();
		StringBuffer sql = new StringBuffer();
//		sql.append(" select m.att,o.xtlb mc  from(");
//		sql.append("select substr(attribute,0,instr(attribute,'_',1,1)-1) att  from monitor_messageflow");
//		sql.append(" group by substr(attribute,0,instr(attribute,'_',1,1)-1)");
//		sql.append(" )m,info_main_yyxt o");
//		sql.append(" where m.att = o.xtbh(+)");
		
		sql.append("select xtlb mc from info_main_yyxt group by xtlb");//修改后 mc为类别
		
		rs = st.executeQuery(sql.toString());
		while(rs.next()){
			sb.append("<dataset seriesName='"+(rs.getString("mc")==null?"":rs.getString("mc"))+"' color='"+array[index]+"' anchorBorderColor='"+array[index]+"'>");
			conn = session.connection();
			st = conn.createStatement();
			for (int i = 0; i < 24; ++i) {
				String hoursstart = null;
				String hoursend = null;
				if (i < 10) {
					hoursstart = date + " 0" + i;
					if(i==9){
						hoursend = date + " " + (i + 1);
					}else{
						hoursend = date +" 0" + (i + 1);	
					}
					
				} else {
					hoursstart = date + " " + i;
					hoursend = date + " " + (i + 1);
				}
				sql.setLength(0);
//				sql.append("select count(*) count from monitor_messageflow");
//				sql.append(" where to_char(startdate,'yyyy-MM-dd HH24:mm:ss')> '"+hoursstart+"'");
//				sql.append(" and to_char(startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
//				sql.append(" and substr(attribute,0,instr(attribute,':',1,1)-1)='"+rs.getString("mc")+"'");
				
				sql.append("select count(*) count from monitor_messageflow");
				sql.append(" where to_char(startdate,'yyyy-MM-dd HH24:mm:ss')> '"+hoursstart+"'");
				sql.append(" and to_char(startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
//				sql.append(" and substr(attribute,0,instr(attribute,'_',1,1)-1)='"+rs.getString("att")+"'");
				sql.append(" and substr(attribute,0,instr(attribute,'_',1,1)-1) in");
				sql.append(" (select 'M'||xtbh xtbh from info_main_yyxt where xtlb='"+rs.getString("mc")+"')");
				rs1 = st.executeQuery(sql.toString());
				while (rs1.next()) {
//					sb.append("<set value='"+rs1.getString("count")+"' link='n- xqlist.jsp?mc="+rs.getString("att")+"&hoursstart="+hoursstart+"&hoursend="+hoursend+"'/>");
					String url="xqlist.jsp?mc="+URLEncoder.encode(rs.getString("mc"))+"&hoursstart="+hoursstart+"&hoursend="+hoursend;
					sb.append("<set value='"+rs1.getString("count")+"' link='javascript:openUrl(\""+url+"\")'/>");
				}
			}
			sb.append("</dataset>");
			index++;
		}
		sb.append("</chart>");
//		res.getWriter().write(new String(sb.toString().getBytes("ISO-8859-1"),"UTF-8"));
//		res.setHeader("pageEncoding", "utf-8");
		res.getWriter().write(sb.toString());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}	 finally{
		   try {
			   conn.close();
				rs1.close();
				rs.close();
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		   }
		   session.close();
	   }
  }
  
  @RequestMapping({"/detail.do"})
  public void operlist(HttpServletResponse res, HttpServletRequest req) throws IOException{
	  String start =  req.getParameter("start")==null?"0":req.getParameter("start");
	  String limit =  req.getParameter("limit")==null?"0":req.getParameter("limit");
//	  String mc = req.getParameter("mc");
	  String mc = java.net.URLDecoder.decode(req.getParameter("mc"), "UTF-8");
	  String hoursstart = req.getParameter("hoursstart");
	  String hoursend = req.getParameter("hoursend");
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
	  StringBuffer sql = new StringBuffer();
//	  sql.append("select * from(");
//	  sql.append(" select m.*, rownum rn from monitor_messageflow m ");
//	  sql.append(" where substr(m.attribute,0,instr(m.attribute,':',1,1)-1)='"+mc+"'");
//	  sql.append(" and to_char(m.startdate,'yyyy-MM-dd HH24:mm:ss')>'"+hoursstart+"'");
//	  sql.append(" and to_char(m.startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
//	  sql.append(" and rownum <='").append(Integer.parseInt(start)+Integer.parseInt(limit)).append("'");
//	  sql.append(") where rn > '").append(Integer.parseInt(start)).append("' ");
	  
	  sql.append("select * from(");
//	  sql.append(" select m.*,y.name xtmc, rownum rn from monitor_messageflow m,org_person y ");
//	  sql.append(" where substr(m.attribute,0,instr(m.attribute,'_',1,1)-1)='"+mc+"'");
	  sql.append(" select m.*,y.dn xtmc,f.zymc, rownum rn from monitor_messageflow m,org_person y,info_main_ggmb f");
	  sql.append(" where substr(m.attribute,0,instr(m.attribute,'_',1,1)-1) in (select 'M'||xtbh xtbh from info_main_yyxt where xtlb='"+mc+"')");
	  sql.append(" and to_char(m.startdate,'yyyy-MM-dd HH24:mm:ss')>'"+hoursstart+"'");
	  sql.append(" and to_char(m.startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
	  sql.append(" and rownum <='").append(Integer.parseInt(start)+Integer.parseInt(limit)).append("'");
	  sql.append(" and m.requesting=y.loginname(+)");
	  sql.append(" and substr(m.attribute,instr(m.attribute,'_',1,1)+1,instr(m.attribute,'_',1,2)-6)= f.acnum(+)");//新加条件
	  sql.append(") where rn > '").append(Integer.parseInt(start)).append("' ");
	  StringBuffer countsql = new StringBuffer();
	  countsql.append("select count(*) rowcount from monitor_messageflow ");
//	  countsql.append(" where substr(attribute,0,instr(attribute,'_',1,1)-1)='"+mc+"'");
	  countsql.append(" where substr(attribute,0,instr(attribute,'_',1,1)-1) in (select 'M'||xtbh xtbh from info_main_yyxt where xtlb='"+mc+"')");
	  countsql.append(" and to_char(startdate,'yyyy-MM-dd HH24:mm:ss')>'"+hoursstart+"'");
	  countsql.append(" and to_char(startdate,'yyyy-MM-dd HH24:mm:ss')<'"+hoursend+"'");
	  
	  try {
		  conn = session.connection();
		  st = conn.createStatement();
		  rs = st.executeQuery(countsql.toString());
		  int rowCount = 0 ;
		  while(rs.next()){
			  rowCount = rs.getInt("rowcount");
		  }
		  int over = rowCount-Integer.parseInt(start);
		  int index=0;
		  int overIndex=0;
		  if(over>=10){
			  overIndex=10;
		  }
		  if(over>0 && over<10){
			  overIndex=over;
		  }
		  if(over<=0){
			  overIndex=rowCount;
		  }
		  rs = st.executeQuery(sql.toString());
		  StringBuffer sbjson = new StringBuffer();
		  sbjson.append("{results:[");
		 
		  while(rs.next()){
			  index++;
			  String departname;
			try {
				departname = rs.getString("xtmc").split(",")[1].split("=")[1];
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				departname="";
			}
			
			String status=rs.getString("resstatus")==null?"":rs.getString("resstatus");
			String resstatus="失败";
			if("200".equals(status)){
				resstatus="成功";
			}
			if("403".equals(status)){
				resstatus="非法调用";
			}
			  sbjson.append("{")
			  .append("xtmc:'").append(departname).append("',")
			  .append("monitorid:'").append(rs.getString("monitorid")==null?"":rs.getString("monitorid")).append("',")
			  .append("enddate:'").append(rs.getString("enddate")==null?"":rs.getString("enddate")).append("',")
			  .append("maxwaittime:'").append(rs.getString("maxwaittime")==null?"":rs.getString("maxwaittime")).append("',")
			  .append("remoteip:'").append(rs.getString("remoteip")==null?"":rs.getString("remoteip")).append("',")
			  .append("startdate:'").append(rs.getString("startdate")==null?"":rs.getString("startdate")).append("',")
			  .append("wsurl:'").append(rs.getString("wsurl")==null?"":rs.getString("wsurl")).append("',")
//			  .append("attribute:'").append(rs.getString("attribute")==null?"":rs.getString("attribute")).append("',")
			  .append("attribute:'").append(rs.getString("zymc")==null?"":rs.getString("zymc")).append("',")//修改后
			  .append("resstatus:'").append(resstatus).append("'");
			  if(index<overIndex){
				  sbjson.append("},");
			  }else{
				  sbjson.append("}");
			  }
			  
		  }
		  sbjson.append("],totalCount:'").append(rowCount).append("'}");
		  String data = SbJsonUtil.toJason(sbjson);
//		  String jsonStr="[{id:'1',name:'zhang'},{id:'2',name:'wang'}]";
		  //res.getWriter().write("{results:[{xtmc:'测政服务',monitorid:'04c4a323-790a-4eaa-9f01-c4ba81cebbe9',enddate:'2015-09-24 13:01:21',maxwaittime:'',remoteip:'10.10.23.29',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7803/TLWComponentWeb/SPJGGS/TLW_SPJGGS_YDYS.asmx HTTP/1.1',attribute:'M007_020020001000033700001000_1_0',resstatus:''},{xtmc:'管理员',monitorid:'ff1e5c7c-46c2-40b6-be88-fa4c12f1a949',enddate:'2015-10-08 15:12:48.464815',maxwaittime:'',remoteip:'10.10.23.21',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7801/services/org/PersonManager HTTP/1.1',attribute:'M007_020020001000033700001000_1_0',resstatus:'200'},{xtmc:'管理员',monitorid:'b6e54f82-9d50-4976-b4ef-452ad6027f76',enddate:'2015-10-08 15:12:57.324628',maxwaittime:'',remoteip:'10.10.23.21',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7801/services/org/PersonManager HTTP/1.1',attribute:'M007_M020020001000033700001000',resstatus:'200'}],totalCount:'3'}");
		  res.getWriter().write(data);
	} catch (HibernateException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}

	  
  }
}
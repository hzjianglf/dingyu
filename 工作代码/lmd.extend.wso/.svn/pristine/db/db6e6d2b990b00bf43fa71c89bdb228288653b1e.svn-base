<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="lmd.extend.wso.portlet.*"%>
<%@ page import="net.risesoft.soa.framework.session.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="keywords" content="山东国土资源局国土局综合管理平台,山东国土">
		<meta name="description" content="山东国土资源局国土局综合管理平台">
		<title>综合管理门户</title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<%
	   String width=request.getParameter("");
	   String height=request.getParameter("");	  
	   String fwzxid =  request.getParameter("rootid");
	   System.out.println(">>>>>>>"+fwzxid);	   
	   SessionUser su = (SessionUser)request.getSession().getAttribute("session.User");
	   String uid = su.getUserUID();	  
	   GetColumn  getcolumn = new GetColumn();	   
	   System.out.println(">>>>>>>"+fwzxid+"<<<<<>>>>"+uid);
	   List fwzxlist = getcolumn.getColumn(fwzxid,uid);
	%>
	<body>
        <!--综合管理服务平台-->
    	<div class="Rdmdiv magt10">
        	<img src="images/zhglttbg.png">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ipmitm1">
              <tr>
                <td class="ipmitd" align="center">
                	<div class="ipmttnm" style="background:url(images/cmput.gif) center left no-repeat; width:100px;">服务中心</div>
                    <table width="250" border="0" cellspacing="0" cellpadding="0" align="center">
                 <%
                    for(int i=0;i<fwzxlist.size();i++){
                         Map  map = (Map)fwzxlist.get(i);
                 %>
	                 <tr>
	                   <td><a href="javascript:window.open('','');"><%=map.get("text")%></a></td>
	<!--                   <td><a href="javascript:window.open('','');">构件管理</a></td>-->
	                 </tr>
                 <%}%>
                    </table>
                </td>
                <td class="ipmitd" align="center">
                	<div class="ipmttnm" style="background:url(images/resous.gif) center left no-repeat; width:120px;">资源中心</div>
                    <table width="170" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr>
                        <td><a href="#">资源库统计信息</a></td>
                        <td><a href="#">资源查询</a></td>
                      </tr>                                                                 
                      <tr>
                        <td><a href="#">组织机构身份资源</a></td>
                        <td><a href="#">服务资源</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">应用系统资源</a></td>
                        <td><a href="#">数据资源</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">标准资源</a></td>
                        <td><a href="#">门户资源</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">业务信息资源</a></td>
                        <td><a href="#">构件资源</a></td>
                      </tr>
                    </table>
                </td>
                <td class="ipmitd" align="center" style="background:none;">
                	<div class="ipmttnm" style="background:url(images/deve.gif) center left no-repeat; width:105px;">开发中心</div>
                    <table width="160" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr>
                        <td><a href="#">项目进度管理</a></td>          
                        <td><a href="#">需求管理</a></td>
                      </tr>                                                                 
                      <tr>
                        <td><a href="#">服务及构件设计</a></td>
                        <td><a href="#">业务建模</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">服务及构件开发</a></td>
                        <td><a href="#">应用测试</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">应用部署</a></td>
                        <td><a href="#">项目统计</a></td>
                      </tr>
                      <tr>
                        <td><a href="#">项目查询</a></td>
                        <td></td>
                      </tr>
                    </table>
                </td>
              </tr>
            </table>
            <div class="ipmifg"></div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ipmitm1">
              <tr>
                <td class="ipmitd" align="center">
               	  <div class="ipmttnm" style="background:url(images/longla.gif) center left no-repeat; width:270px;">服务中心</div>
                    <table width="320" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr>
                        <td><a href="#">统计分析 </a></td>
                        <td><a href="#">决策支持</a></td>
                        <td>视频监控</td>
                        <td>业务系统</td>
                      </tr>
                      <tr>
                        <td><a href="#">中间件</a></td>
                        <td><a href="#">应用服务 </a></td>
                        <td>数据库</td>
                        <td>存储设备</td>
                      </tr>
                      <tr>
                        <td><a href="#">操作系统</a></td>
                        <td><a href="#">虚拟化</a></td>
                        <td>服务器硬件</td>
                        <td>网络与安全设备</td>
                      </tr>
                    </table>
                </td>
                <td class="ipmitd" align="center" style="background:none;">
                	<div class="ipmttnm" style="background:url(images/shiled.gif) center left no-repeat; width:270px;">开发中心</div>
                    <table width="320" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr>
                        <td><a href="#">物理安全 </a></td>
                        <td><a href="#">网络安全</a></td>
                        <td>系统安全</td>
                        <td>数据安全</td>
                      </tr>
                      <tr>
                        <td><a href="#">安全管理制度</a></td>
                        <td><a href="#">安全应急 </a></td>
                        <td>安全监督</td>
                        <td>&nbsp;</td>
                      </tr>
                    </table>
                </td>
              </tr>
            </table>
        </div>
        <!--综合管理服务平台结束-->
		<!--内容结束-->
	</body>
</html>

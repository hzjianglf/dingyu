<%@page import="java.util.ArrayList"%>
<%@page import="lmd.extend.jicheng.util.GetDaiBan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<% String imagePath = request.getContextPath()+"/images/";
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userLogin = request.getParameter("name");
    //System.out.println("userLogin------"+userLogin);
    if(userLogin.indexOf("/")>0){
		userLogin=userLogin.substring(userLogin.indexOf("/")+1,userLogin.length());
		
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/door.css">

</head>

<body>
  <div style="width: 179px;margin: 0px;padding-left: 5px; " >
       <table width="179px"  height="388px" style="background-image: url('')">
         <tr> 
            <td align="center" valign="top">
            
        
        <div class="tzggdiv2 mgt4">
        	<div class="colwit " style="padding-left:40px;  margin:0px 10px; text-align:left; background:url(<%=request.getContextPath()%>/img/person/laba.png) no-repeat; line-height:36px;">通知公告</div>
            <ul style="width:179px;">
            	<li>省厅项目工作议程 </li>
                <li>各项目测试工作安排</li>
            </ul>
        </div>
        
        <div class="tzggdiv3 mgt4">
        	<div class="colwit " style="padding-left:44px; margin:0px 10px; text-align:left; background:url(<%=request.getContextPath()%>/img/person/dbthing.png) no-repeat; line-height:36px;">待办列表</div>
            <ul style="width:179px;">
            
            	<li>您未处理的审批件&nbsp;<span><a href="http://10.10.10.8/sdzw/ForwardToZW.do?ywtype=comRunYW&u_name=${loginjp }" target="_blank"><font color="#ffffff" size="2"><b>[${shenpi }]</b></font></a></span></li>
            	
                <li>您未处理的公文件&nbsp;<span><a href="http://10.10.10.8/sdzw/ForwardToZW.do?ywtype=comRunOA&u_name=${loginjp }" target="_blank"><font color="#ffffff" size="2"><b>[${daiban }]</b></font></a></span></li>
                <%
                if(Integer.parseInt((String)request.getAttribute("xdzzwdb"))>=0){
                 %>
                <li>新电子政务系统件&nbsp;<span><a href="http://10.10.10.29/sdgt/Portal/IAutoLogin/AutoLogin.aspx?logonid=${loginjp }&LogType=logon" target="_blank"><font color="#ffffff" size="2"><b>[${xdzzwdb }]</b></font></a></span></li>
                <%
                }
                if(Integer.parseInt((String)request.getAttribute("jsyddb"))>=0){
                 %>
                <li>建设用地未审批件&nbsp;<span><a href="http://10.10.10.29/sdydbp/YDBP_SD/Controls/JumpPage.aspx?logonid=${loginjp }&urlid=101010" target="_blank"><font color="#ffffff" size="2"><b>[${jsyddb }]</b></font></a></span></li>
                <%
                }
                if(Integer.parseInt((String)request.getAttribute("ghbjdb"))>=0){
                 %>
                <li>规划调整修改办件&nbsp;<span><a href="http://10.10.10.29/sdgt/Portal/IAutoLogin/AutoLogin.aspx?logonid=${loginjp }&LogType=logon" target="_blank"><font color="#ffffff" size="2"><b>[${ghbjdb }]</b></font></a></span></li>
                <%
                }
                if(Integer.parseInt((String)request.getAttribute("gdbhdb"))>=0){
                 %>
                <li>耕地保护动态监管&nbsp;<span><a href="http://10.10.10.135/gbsp/Login.aspx?DoType=FromPort&uid=${userLogin }" target="_blank"><font color="#ffffff" size="2"><b>[${gdbhdb }]</b></font></a></span></li>
                <%
                }
                if(Integer.parseInt((String)request.getAttribute("zjggdb"))>=0){
                 %>
                <li>增减挂钩未审批件&nbsp;<span><a href="http://10.10.10.29/sdgt/Portal/IAutoLogin/AutoLogin.aspx?logonid=${userLogin }&LogType=logon" target="_blank"><font color="#ffffff" size="2"><b>[${zjggdb }]</b></font></a></span></li>
                <%} %>
            </ul>
        </div>
        
           </td>
         </tr>
         
         
       </table>
      
 </div>
</body>
</html>

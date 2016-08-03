<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache"><!-- 禁止浏览器在本地计算机缓存当前页面 -->
	<meta http-equiv="cache-control" content="no-cache"><!-- 清除浏览器中的缓存 -->
	<meta http-equiv="expires" content="0"> <!-- 刷新 -->
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"><!-- 为搜索引擎提供的关键字列表 -->
	<meta http-equiv="description" content="This is my page"><!--  Description用来告诉搜索引擎你的网站主要内容。 -->

  </head>
  
  <body>
  <div class="row mt10 pd20">
 	<div class="col-lg-3">
 		<div class="panel panel-info">
 			<div class="panel-heading">
		    <h3 class="panel-title">整站数据汇总</h3>
		  </div>
		  <div class="panel-body">
				<div class="list-group">
				  <a href="accountmgr" class="list-group-item navitem">总注册用户 <span class="badge">${accountCount }</span></a>
				  <a href="companymgr" class="list-group-item navitem">变电站总数 <span class="badge">${companyCount }</span></a>
				  <a href="personmgr" class="list-group-item navitem">主站总数 <span class="badge">${personCount }</span></a>
				<%--   <a href="linkmgr" class="list-group-item navitem">友链总数 <span class="badge">${linkCount }</span></a> --%>
				  <a href="articlemgr" class="list-group-item navitem">录波器总数 <span class="badge">${articleCount }</span></a>
				 <%--  <a href="javascript:void(0)" onclick="return false;" class="list-group-item">评论总数 <span class="badge">${commentCount }</span></a> --%>
				</div>
		  </div>
</div>
 	</div>
 	<div class="col-lg-5">
 	<div class="panel panel-info">
 			<div class="panel-heading">
		    <h3 class="panel-title">当前登录用户信息</h3>
		  </div>
		  <div class="panel-body">
		  <div class="list-group">
				  <li class="list-group-item navitem">当前用户<span class="badge">${session.user.nickname }</span></li>
				  <li class="list-group-item navitem">权限组<span class="badge" id="userpermission"></span></li>
				  <li class="list-group-item navitem">本次登录时间 <span class="badge"><fmt:formatDate value="${session.user.loginTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></span></li>
				  <li class="list-group-item navitem">本次登录时IP<span class="badge">${session.user.loginIp }</span></li>
				  <li class="list-group-item navitem">上次登录时间 <span class="badge"><fmt:formatDate value="${session.user.lastLoginTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></span></li>
				  <li class="list-group-item navitem">上次登录时IP<span class="badge">${session.user.lastLoginIp }</span></li>
				</div>
		  </div>
		  <script type="text/javascript">
				   var pstr="未知";
		        	 switch(${session.user.permission}){
		        	 case 1:
		        		 pstr="超级管理员";
		        		break;
		        	 case 2:
		        		 pstr="普通用户";
		        		break;
		        	 case 3:
		        		 pstr="网站编辑";
		        		break;
		        	 }
		        	 $("#userpermission").text(pstr);
				  </script>
		  </div>
 	</div>
  </div>
  </body>
</html>

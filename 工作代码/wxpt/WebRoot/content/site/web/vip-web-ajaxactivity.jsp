<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>优惠活动</title>
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="/wxpt/web/css/jquery.mobile-1.2.0.min.css" />
    <link rel="stylesheet" href="/wxpt/web/css/jquery.mobile.theme-1.2.1.css" />
	<script src="/wxpt/web/js/jquery-1.8.3.js"></script>
	<script src="/wxpt/web/js/jquery.mobile-1.2.1.min.js"></script>
  
  </head>
  
  <body>
  
  <div data-role="page" id="allactivity">
     <div data-role="header" data-theme="b">
			<h4 align="center">活动详细内容</h4>
	<a href="/wxpt/site/web/vip-web!showActivity?enterId=${enterId }&cardId=${cardId }" data-role="button" data-ajax="false" data-theme="b" data-icon="arrow-l">返回</a>
	
   </div><!-- /header -->
   <div data-role="content" id="result">   
     <s:iterator value="listActivity2" var="list">
      <center><h4 style="background-color: #AFFAFD;"><s:property value="#list.activityTitle"/>|<s:property value="#list.activityContent" /> </h4>
      <img src="/wxpt/web/images/<s:property value="#list.imageUrl" />" style="width: 100%;height: 100%;"/>
      </center>
      
      </s:iterator>
   
   </div><!-- /content -->
</div><!-- /page -->
   </body>
</html>
    

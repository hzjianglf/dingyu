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
  
   <div data-role="page" id="pageOne">
    <div data-role="header" data-theme="b">
			<h4 align="center">活动优惠券</h4>
	<a href="/wxpt/site/web/vip-web!showTicket?enterId=${enterId }&cardId=${cardId }&memberId=${memberId}" data-role="button" data-ajax="false"  data-theme="b" data-icon="arrow-l">返回</a>
   </div><!-- /header -->
   <div data-role="content" class="content">
		<s:iterator value="listCoupons2" var="list">
		<table>
		  <tr><td>活动标题：<s:property value="#list.activityTitle" /></td></tr>
		  <tr><td>优惠主题：<s:property value="#list.couponsTitle" /></td></tr>
		  <tr><td>优惠券：<s:property value="#list.couponsContent" /></td></tr>
	       <tr><td>
	                 有效期:<s:property value="#list.activityStarttime" />-<s:property value="#list.activityEndtime" />
	       </td></tr>
	       <tr><td><font color="red">注：<s:property value="#list.couponsRemark" /></font></tr>
		</table>
		</s:iterator>
   </div><!-- /content -->
   <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
   <h4>微信通</h4>
   </div><!-- /footer -->
</div><!-- /page -->
   </body>
</html>
    

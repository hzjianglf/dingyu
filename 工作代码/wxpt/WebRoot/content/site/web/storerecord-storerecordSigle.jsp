<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html  class="ui-mobile">
  <head>
    
    <title>会员管理</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
  <script src="../../web/js/jquery-1.8.3.js"></script>
  <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
  </head>

  <body class="ui-mobile-viewport">
       <div data-role="page">
    		<div data-role="header" data-theme="b">
    			<h1>详细</h1>
    			
    		<a href="/wxpt/site/web/storerecord!storerecordMessage?memberId=<s:property value="memberId" />&enterId=${enterId }&carId=<s:property value='member.cardId'/>" style="text-decoration: none;float: right;">
    			返回</a>
    		</div>
    		<div data-role="content" align="left" width="100%">
    			<table>
    				
    				<tr>
    					<td width="20%" valign="top">店面：</td><td><s:property value="storerecord.businessName" /></td>
    				</tr>
    				<tr>
    					<td>充值：</td><td><s:property value="storerecord.money" /></td>
    				</tr>
    				<tr>
    					<td>时间：</td><td>${storerecord.recordtime }</td>
    				</tr>
    				<tr>
    				<td>金额：</td><td>${storerecord.presentMoney }</td>
    				</tr>
    			</table>
   			</div>
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微信通</h4>
   </div><!-- /footer -->
    	</div>
  </body>
</html>

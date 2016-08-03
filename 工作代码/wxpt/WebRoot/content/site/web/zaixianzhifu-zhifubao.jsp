<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE HTML>
<html  class="ui-mobile">
  <head>
    
    <title>确定支付？</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
    <script src="../../web/js/jquery-1.8.3.js"></script>
    <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
    <script src="../../web/js/zaixianzhifu.js"></script>
  </head>
  
  <body  class="ui-mobile-viewport">
  <div data-role="header" data-theme="b">
    			<h1>您选择了银联支付</h1>
    			
  </div>
  
  <div><input type="text" value="${ItemUrl}"/></div>
 
  <div class="pay">s
  
    
     <a href="${ItemUrl}">确定支付</a>
    <a href="/uniqyw/site/web/index!find">返回首页</a>
    
   </div>
   
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微商城支付页面</h4>
   </div>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE HTML>
<html  class="ui-mobile">
 <head>
    
    <title>支付成功</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
    <script src="../../web/js/jquery-1.8.3.js"></script>
    <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
 <script src="../../web/js/zaixianzhifu.js"></script>
  </head>

<body class="ui-mobile-viewport">

     <div data-role="page">
    		<div data-role="header" data-theme="b">
    			<%-- <h1>请选择支付方式</h1>
    			<a href="../../site/web/vip-web?enterId=${enterId }&cardId=${cardId }" style="text-decoration: none;float: right;">返回</a>
    			<a href="../../site/web/vip-web!getList?enterId=${enterId }&id=<s:property value='integ.memberId' />&cardId=${cardId }" style="text-decoration: none;float: right;">查看历史</a> --%>
    		</div>
    支付成功
   			
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微商城支付页面</h4>
   </div><!-- /footer -->
    	</div>


</body>
</html> 

 
 

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
    
    <title>商家回复</title>
    
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
  <script> 
  function back()
  {
		 window.location.href="/wxpt/site/web/vip-web!setMessages?enterId=${enterId }&cardId=${cardId }";
  }
 </script>
  </head>
  
  <body>
  <div data-role="page" id="allmessage">
     <div data-role="header" data-theme="b">
			<h4 align="center">回复信息详情</h4>
	<a href="/wxpt/site/web/vip-web!setOneMessage?enterId=${enterId }&cardId=${cardId }" data-ajax="false" data-role="button" data-theme="b" data-icon="arrow-l">返回</a>
	
   </div><!-- /header -->
   <div data-role="content" id="result">   
        <s:iterator value="listMessage2" var="list">
      <h4>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#list.messageTitle"/> </h4>
      <s:iterator value="listMj" var="lists">
      <p>我：<s:property value="#lists.messageContents"/></p>
      </s:iterator>
      <p>商家：<s:property value="#list.messageContent"/></p>
      </s:iterator>
   	<table>
		<tr>
		<td>		
		<button style="background-image:url(/wxpt/web/images/button.png)" onclick="back()">添加留言</button>
		</td>
		</tr>
	</table>
   </div><!-- /content -->
   <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
   <h4>微信通</h4>
   </div><!-- /footer -->
</div><!-- /page -->
 
  </body>
</html>
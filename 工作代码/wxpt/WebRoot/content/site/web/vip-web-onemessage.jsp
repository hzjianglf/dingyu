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
 
 function allmessage(messageId)
 {
	 window.location.href="/wxpt/site/web/vip-web!backMessage?enterId=${enterId }&cardId=${cardId }&id="+messageId;
 }
 </script>

  </head>
  
  <body>
        <div data-role="page" id="replyMessage">
   <div data-role="header" data-theme="b">
			<h4 align="center">回复信息</h4>
	<a href="javascript:back()" data-role="button" data-theme="b" data-icon="arrow-l">返回</a>
	
   </div><!-- /header -->
   <div data-role="content" class="content" >
   
		<ul data-role="listview" data-inset="true" data-theme="c">
		    <s:iterator value="listMessage" var="list">
		    <li><a href="javascript:allmessage('<s:property value="#list.messageId" />')"><s:property value="#list.messageTitle" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#list.time" /></a></li>
		    </s:iterator>
		</ul>
		<center>
		<table>
			<tr>
		<td align="center">第${pagebean.prePage }/${pagebean.pageCnt }页</td>
		<td align="center">共${pagebean.recordCnt }条</td>
		</tr>
		<tr>
		<td>		
		<a href="/wxpt/site/web/vip-web!setOneMessage?curPage=${pagebean.prePage }&enterId=${enterId }&cardId=${cardId }" data-ajax="false" data-role="button" data-theme="b"data-icon="arrow-l">上一页</a>
		</td>
		<td>        
		<a href="/wxpt/site/web/vip-web!setOneMessage?curPage=${pagebean.nextPage }&enterId=${enterId }&cardId=${cardId }" data-ajax="false" data-role="button" data-theme="b" data-icon="arrow-r"data-iconpos="right">下一页</a>
		</td>
		</tr>
		</table>
   		</center>
   	</div><!-- /content -->
   <div data-role="footer" data-theme="b" data-id="myFooter" data-position="fixed" data-tap-toggle="false">
   <h4>微信通</h4>
   </div><!-- /footer -->
</div><!-- /page -->

  </body>
</html>

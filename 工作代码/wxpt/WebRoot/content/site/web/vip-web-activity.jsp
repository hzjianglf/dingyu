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
  
  
  <script>

  function back(cardId)
  {
	  window.location.href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId="+cardId;
  }
  function allactivity(activityId,cardId)
  {
	  window.location.href="/wxpt/site/web/vip-web!backActivity?enterId=${enterId }&cardId="+cardId+"&id="+activityId;	 
  }
  </script>
  
  </head>
  
  <body>
    
    <div data-role="page">
    <div data-role="header" data-theme="b">
			<h4 align="center">优惠活动</h4>
	<a href="javascript:back('${cardId }')" data-role="button" data-theme="b" data-icon="arrow-l">返回</a>
   </div><!-- /header -->
   <div data-role="content" class="content">
		<ul data-role="listview" data-inset="true" data-theme="c">
		<s:iterator value="listActivity" var="list">
		    <li title="<s:property value="#list.activityContent" />"><a href="javascript:allactivity('<s:property value="#list.activityId" />','<s:property value="cardId" />')"><s:property value="#list.activityTitle" /></a></li>
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
		<a href="/wxpt/site/web/vip-web!showActivity?curPage=${pagebean.prePage }&enterId=${enterId }&cardId=${cardId }" data-ajax="false" data-role="button" data-theme="b"data-icon="arrow-l" >上一页</a>
		</td>
		<td>        
		<a href="/wxpt/site/web/vip-web!showActivity?curPage=${pagebean.nextPage }&enterId=${enterId }&cardId=${cardId }" data-ajax="false" data-role="button" data-theme="b" data-icon="arrow-r"data-iconpos="right" >下一页</a>
		</td>
		
		</tr>
		</table>
   		</center>
   </div><!-- /content -->
   <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
   <h4>微聚家</h4>
   </div><!-- /footer -->
</div><!-- /page -->

  </body>
</html>

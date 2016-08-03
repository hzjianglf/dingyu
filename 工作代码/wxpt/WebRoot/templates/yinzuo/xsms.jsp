<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"+request.getAttribute("templateName")+"/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/"+request.getAttribute("enterID")+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><s:property value="menuName.replaceAll('<[^>]*>','')"/></title>
	<script src="js/jquery-1.4.4.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
<img src="<%=filePath%>/<s:property value="bannerName"/>"  width="100%" height="100"/>


<s:iterator value="pageList"  var="page" status="status">
	<s:if test="(#status.index+1)%2 != 0">
		<div style="margin-top:10px; width:100%">
	</s:if>
	<s:if test="(#status.index+1)%2 != 0">
		<div style="float:left; margin-left:5%;" id="ms">
	</s:if>
	<s:if test="(#status.index+1)%2 == 0">
		<div style="float:right; margin-right:5%;" id="ms">
	</s:if>
	
		<%--<div style="float:left; margin-left:5%;" id="ms">
			<div style="margin-top:10px; font-size:12px; font-weight:bold">还剩：</div> --%>
			<div id="images">
			<s:if test="#page.pageUrl==NULL || #page.pageUrl==''">
				<a href="javascript:getPage(<s:property value="#page.pageId"/>)">
			</s:if>
			<s:if test="#page.pageUrl!=NULL && #page.pageUrl!=''">
				<a href="<s:property value="#page.pageUrl"/>">
			</s:if>
			
			<img src="<%=filePath%>/<s:property value="#page.metaImageValue"/>" width="120" height="120" /></a></div>
			<div id="title"><s:property value="#page.pageTitle"/></div>
			<%-- <div style="margin-top:10px;">address.html<a href="#"><img src="images/an.png"  width="80" height="20"/></a></div>--%>
		</div>
		<s:if test="(#status.index+1)%2==0">
			</div>
			<div style="clear:both"></div>
		</s:if>
</s:iterator>
<div style="clear:both"></div>
<div style="height:50px;"></div>
<jsp:include page="footer.jsp"/>
</body>
</html>

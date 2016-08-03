<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script src="js/html5.js"></script>
	<script src="js/html5media.min.js"></script>
	<script src="js/url.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="../css/url_skip.css" rel="stylesheet" type="text/css" />
	<title><s:property value="enterInfor.enterName" /></title>
</head>

<body>
<div>
<div class="top">
			<h3><s:property value="menuName" escapeHtml="false"/></h3>	
	</div>
<div class="line"> </div>
<s:iterator value="pageList" var="page" status="status">
	<s:if test="#status.odd">
		<div class="food">
		<s:if test="#page.pageUrl=='' || #page.pageUrl==NULL">
		<a href="javascript:getPage('<s:property value="#page.pageId"/>')">
		</s:if>
		<s:if test="#page.pageUrl!='' && #page.pageUrl!=NULL">
		<a href="<s:property value="#page.pageUrl"/>">
		</s:if>
		<img src="<%=filePath %>/<s:property value="#page.metaImageValue"/>" width="97%" height="120" style="padding:5px;">
			<div style="width:99%; margin-top:-40px;">
				<img src="images/bg.png" style="width:95%; height:30px; margin-left:5px;">
				<div class="jieshao"><s:property value="#page.pageTitle"/></div>
			</div></a>
			<div class="xuxian"></div>
	</s:if>	
			
		
			
	<s:if test="#status.even">
			<s:if test="#page.pageUrl=='' || #page.pageUrl==NULL">
			<a href='javascript:getPage(<s:property value="#page.pageId"/>)' style="color:#666666">
			</s:if>
			<s:if test="#page.pageUrl!='' && #page.pageUrl!=NULL">
			<a href='<s:property value="#page.pageUrl"/>' style="color:#666666">
			</s:if>
			
			<div>
				<div class="share">
					<s:property value="#page.pageTitle"/>
				</div>
				<div style="float:right;width:35%;">
					<img src="<%=filePath %>/<s:property value="#page.metaImageValue"/>" style="width:95%; height:28%">
				</div>
			</div></a>
		
		</div>
	</s:if>
</s:iterator>
</div>

<div style="height:90px;"></div>
<div style="clear:both"></div>
<footer class="foot">
	<s:iterator value="siteOptionList" var="siteOption">
		<a href="<s:property value="#siteOption.url"/>"><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>"/></a> 
	</s:iterator>
</footer>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="../js/url_skip.js"></script>

<div class="box-guide hide">
	<div class="box-img">
		<img src="../images/guide.png" data-pinit="registered">
	</div>
</div>
<div id="share-shadow" class="shade"></div>
</body>
</html>

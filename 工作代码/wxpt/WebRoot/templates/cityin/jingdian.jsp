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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script src="js/html5.js"></script>
<script src="js/html5media.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/url.js"></script>
<title><s:property value="enterInfor.enterName" /></title>
<style type="text/css">
	a:link{ color:black;text-decoration:none;}
a:visited{color:#black;text-decoration:none;}
a:hover{color:#black;text-decoration:none;cursor:hand;}
a:active{color:#black;text-decoration:none;}
</style>
<link href="../css/url_skip.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="top">
		<h3><s:property value="menuName" escapeHtml="false"/></h3>
</div>
<div class="line"> </div>
<s:iterator value="pageList" var="pageAbout" status="status">
	<s:if test="#status.odd">
		<s:if test="#pageAbout.pageUrl=='' || #pageAbout.pageUrl==NULL">
		<a href="javascript:getPage(<s:property value="#pageAbout.pageId"/>)">
		</s:if>
		<s:if test="#pageAbout.pageUrl!='' && #pageAbout.pageUrl!=NULL">
		<a href="<s:property value="#pageAbout.pageUrl"/>">
		</s:if>
		<div class="dingyue">
			<div class="dingyue_left"><img src="<%=filePath %>/<s:property value="#pageAbout.metaImageValue"/>"></div>
			<div class="dingyue_right">
				<div class="dingyue_right right_content">
				  	<s:property value="#pageAbout.metaDetail" escapeHtml="false"/> 
				</div>
				<div style="clear:both"></div>
				<div class="right_title"><s:property value="#pageAbout.pageTitle"/></div>
				<div class="x"></div>
			</div>
		</div></a>
	</s:if>
	<s:if test="#status.even">
		<s:if test="#pageAbout.pageUrl=='' || #pageAbout.pageUrl==NULL">
		<a href="javascript:getPage(<s:property value="#pageAbout.pageId"/>)">
		</s:if>
		<s:if test="#pageAbout.pageUrl!='' && #pageAbout.pageUrl!=NULL">
		<a href="<s:property value="#pageAbout.pageUrl"/>">
		</s:if>
		<div class="dingyue">
		<div class="dingyue_right dingyue1_left">
				<div class="dingyue_right dingyue1_left right1_content">
				  <s:property value="#pageAbout.metaDetail"  escapeHtml="false"/>
				</div>
				<div style="clear:both"></div>
				<div class="right_title"><s:property value="#pageAbout.pageTitle"/></div>
				<div class="x"></div>
			</div>
			<div class="dingyue_left dingyue1_right"><img src="<%=filePath %>/<s:property value="#pageAbout.metaImageValue"/>"></div>
		</div></a>
	</s:if>
</s:iterator>
<div class="line" style="margin-top:5px;"> </div>
<div style="height:90px;"></div>
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

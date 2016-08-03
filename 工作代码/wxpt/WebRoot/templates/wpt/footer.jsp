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
<base href="<%=basePath%>">
<script type="text/javascript" src="js/url.js"></script>
<footer>
	<ul>
    	<li><a href=""><img src="images/grid.png" /></a></li>
        <li><a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[4]"/>','5')"><img src="images/user.png"/></a></li>
        <li><a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[4]"/>','5')"><img src="images/smarpho.png"/></a></li>
        <li><a href="javascript:getChildUrl('<s:property value="#attr.siteMenuList.{menuId}[3]"/>','4')"><img src="images/chat3.png"/></a></li>
    </ul>
</footer>    
   
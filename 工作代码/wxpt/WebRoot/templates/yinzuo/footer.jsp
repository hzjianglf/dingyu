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
<footer>
	<ul>
		<li>
			<div><img src="images/sy.png"  width="20" height="20"/></div>
			<div style="margin-top:5px;"><a href="javascript:getUrl(0,0)">首页</a></div>
		</li>
		<li>
			<div><img src="images/yz.png"  width="20" height="20"/></div>
			<div style="margin-top:5px;"><a href="javascript:getChildUrl(<s:property value="#attr.siteMenuList.{menuId}[4]"/>,5)"><s:property value="#attr.siteMenuList.{menuName}[4]"/></a></div>
		</li>
		<li>
			<div><img src="images/shop.png"  width="20" height="20"/></div>
			<div style="margin-top:5px;"><a href="javascript:getLogUrl()">购物车</a></div>
		</li>
		<li>
			<div><img src="images/ss.png"  width="20" height="20"/></div>
			<div style="margin-top:5px;"><a href="javascript:getSearch()">搜索</a></div>
		</li>
		<li>
			<div><img src="images/more.png"  width="20" height="20"/></div>
			<div style="margin-top:5px;"><a href="javascript:getAll(<s:property value="#attr.siteMenuList.{menuId}[4]"/>,5)">更多</a></div>
		</li>
	</ul>
</footer>
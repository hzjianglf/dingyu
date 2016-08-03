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
			<div><img src="images/yuyue.png"  width="20" height="20"/></div>
			<div><a href="javascript:getUrl(0,0)">首页</a></div>
		</li>
		<li>
			<div><img src="images/phone.png"  width="20" height="20"/></div>
			<div><a href="tel:<s:property value="contactUs.telephone"/>">电话</a> </div>
		</li>
		<li>
			<div><img src="images/map.png"  width="20" height="20"/></div>
			<div><a href="javascript:getMap()">地图</a></div>
		</li>
		<li>
			<div><img src="images/fenxiang.png"  width="20" height="20"/></div>
			<div><a href="javascript:getShare()">分享</a></div>
		</li>
		<li>
			<div><img src="images/message.png"  width="20" height="20"/></div>
			<div><a href="javascript:getMsg()">留言</a></div>
		</li>
	</ul>
	</footer>
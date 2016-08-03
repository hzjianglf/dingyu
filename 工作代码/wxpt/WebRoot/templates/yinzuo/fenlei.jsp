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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:property value="menuName.replaceAll('<[^>]*>','')"/></title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
<div id="rcyp">
	<s:property value="menuName"/>
</div>
<nav>
		<ul>
		<s:iterator value="childMenuList" var="childMenu" status="status">
			<a href="javascript:getUrl(<s:property value="#childMenu.menuId"/>,5)">
			<li>
				<ul>
					<li><img src="<%=filePath %>/<s:property value="#childMenu.imageValue"/>"  width="50" height="50"/></li>
					<li id="li2"><span id="list1"><s:property value="#childMenu.menuName"/></span></li>
					<li id="li1"><img src="images/jiantou.png" /></li>
					<div id="div1"><img src="images/xian.png"  width="100%"/></div>
				</ul>
			</li>
			</a>
		
		</s:iterator>
			
		<a href="javascript:getAll(<s:property value="#childMenu.menuParent"/>,5)">
			<li>
				<ul>
					<li><img src="images/all.png"  width="50" height="50"/></li>
					<li id="li2"><span id="list1">全部</span></li>
					<li id="li1"><img src="images/jiantou.png" /></li>
					<div id="div1" style="margin-bottom:50px;"><img src="images/xian.png"  width="100%"/></div>
				</ul>
			</li>
			</a>
		</ul>
	</nav>
	<jsp:include page="footer.jsp"/>
</body>
</html>

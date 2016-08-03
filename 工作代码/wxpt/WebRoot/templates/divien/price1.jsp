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
<title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>
<script type="text/javascript">
	function getUrl(menuID, menuNamID) {
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		if (menuID == 0) {
			window.location.href = url[0]+"!index?"+url[1].split("?")[1].split("&")[0];
		} else {
			
			window.location.href = url[0] + "!getMenuPage?"
			+ url[1].split("?")[1].split("&")[0] + "&menuNameID=" + menuNamID
					+ "&menuID=" + menuID;
			//$("#url").html(url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNamID="+menuNamID);
		}

	}
	
	function getMap(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMap?"+url[1].split("?")[1];
		//+"&menuNameID="+menuNamID +"&menuID="+menuID;
	}
	function getMsg(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMsg?"+url[1].split("?")[1];
	}
	function getShare(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getShare?"+url[1].split("?")[1];
	}
	
	function getPage(pageID){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0] + "!getPage?"
		+ url[1].split("?")[1].split("&")[0]+"&pageID="+pageID;
	}
</script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script
	src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<header>
	<div class="xiangxi"><s:property value="pageAbout.pageTitle" /></div>
</header>
	<section>
	<div class="xqbg">
		<img src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />" class="xqimg">
	</div>
	</section>
	<section>
	<div class="xqbt">详细信息</div>
	</section>
	<section class="xq">
	<s:property value="pageAbout.metaValue" escapeHtml="false"/>
	</section>
	<jsp:include page="foot.jsp"/>
</body>
</html>

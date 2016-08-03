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
<link rel="stylesheet" href="css/m_style.css" />
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title><s:property value="enterInfor.enterName" /></title>
<script type="text/javascript">
	function getUrl(menuID,menuNamID){
		var urlStr = window.location.href;
		if(menuID == 0){
			window.location.href=urlStr;
		}else{
			var url = urlStr.split("!");
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
		+ url[1].split("?")[1].split("&")[0] + "&pageID="+pageID;
	}
</script>

</head>

<body>
<header class="head">
	<img src="<%=filePath%>/<s:property value="logoName"/>"/>
</header>
<header id="box_header" class="headerhome">
<div class="columnSpace" id="elem-navigation" name="meun"> 
<div id="Columns_navigation" class="Columns_navigation">	
<nav class="navigation01" >
<div class="navBarL active" id="columnprev" onClick="goPrev();">				
<span ></span></div>
<div class="navBody" id="singleScroll">
<ul class="sc_scroller" id="singleScrollUl" style="width:6000px">
<li><span class="active" id="column1"><a href="javascript:getUrl(0,0)">首页</a></span></li>
<s:iterator status="i" value="siteMenuList" var="siteMenu">
<li><span class="active" id="column<s:property value="#i.index+1"/>" ><a href="javascript:getUrl(<s:property value="#siteMenu.menuId"/>,<s:property value="#i.index+1"/>)"><s:property value="#siteMenu.menuName" escapeHtml="false"/></a></span></li>
</s:iterator>
</ul>
</div>
<div class="navBarR active" id="columnnext" onClick="goNext();">
<span ></span></div>
</nav>
<script type="text/javascript">
function setColumnWidth(){getTotalWidth("1");getTotalWidth("2");getTotalWidth("3");getTotalWidth("4");getTotalWidth("5");$("#singleScrollUl").css("width",ulwidth);		
}
</script>
</div> 
</div>
</header>

<div class="FrontSlide" >
<div class="bannerScrollWrap" >
<div class="leftCover"></div>
<div id="bannerScroll">
<div class="prev"><span id="prev" class="active"></span></div>
<ul class="bannerList sc_scroller">
<s:iterator value="siteOptionList" var="siteOption">
<li style="width: 100%; height: 60%"><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>"  width="100%" height="100%" /></li>
</s:iterator>
</ul>

<div class="next"><span id="next" class="active"></span></div> 
</div>
<div class="rightCover"></div>
</div>
</div>


<div style="background:#d9c0f9; height:40px; color:#FFFFFF; width:100%; font-size:18px; font-family:微软雅黑;">
<s:iterator value="tuijianMenuList" var="menu">
	<p style="padding-top:15px;"><s:property value="#menu.menuName"/></p>
</s:iterator>
</div>
<div id="list">
	<ul>
	<s:iterator value="tuijianPageAboutList" var="pageAbout">
		<li><a href="javascript:getPage(<s:property value="#pageAbout.pageId"/>)"><img src="<%=filePath %>/<s:property value="#pageAbout.metaImageValue"/>" /></a></li>
	</s:iterator>
		
	</ul>
</div>
<div style="clear:both"></div>
<div style="height:50px;"></div>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<jsp:include page="foot.jsp"/>

</body>

</html>


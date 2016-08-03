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

<title><s:property value="menuName.replaceAll('<[^>]*>','')"/></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<link href="css/style.css" rel="stylesheet"/>
<link href="css/m_style.css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script language="JavaScript"> 
function DrawImage(thisp,w,h){
var a=new Image();   
a.src=thisp.src; 
if (a.width<w && a.height<h) {
thisp.width=a.width;/*使用原始图片大小*/ 
thisp.height=a.height;   
}else{
if(a.width/a.height>w/h){
   thisp.height=w*a.height/a.width; /*不对图片拉伸*/ 
   thisp.width=w;
}else{
   thisp.width=h*a.width/a.height; 
   thisp.height=h;
}
}
}

</script>
</head>

<body style="background:#FFFFFF">
<div style="height:60px; width:100%; background:#000000">
<div class="top">
<div class="logo">
	<img src="<%=filePath%>/<s:property value="logoName"/>"/>
</div>
<div class="break">
    <a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
</div>
</div>
</div>
<div style=" height:200px;">
	<img src="<%=filePath%>/<s:property value="bannerName"/>" height="100%"  width="100%" />
</div>
<!--banner结束-->
<div>
<div style="width:100%; height:45px; background:#84d1f9;">
	<span style="margin-left:15px; font-weight:bold; font-size:36px; color:#FFF;"><s:property value="menuName" escapeHtml="false"/></span>
</div>
<div style="clear:both"></div>
<div>
	<header id="box_header" class="headerhome">
		<div class="columnSpace" id="elem-navigation" name="meun"> 
		<div id="Columns_navigation" class="Columns_navigation">	
		<nav class="navigation01" >
		<div class="navBarL active" id="columnprev" onClick="goPrev();">				
		<span></span></div>
		<div class="navBody" id="singleScroll">
		<ul class="sc_scroller" id="singleScrollUl" style="width:6000px">
			<s:iterator value="childMenuList" var="childMenu" status="status">
				<li><span class="active" id="column<s:property value="#status.count"/>"><a onClick="show('<s:property value="#childMenu.menuId"/>')"><s:property value="#childMenu.menuName"/></a></span></li>
		
			</s:iterator>
		
		</ul>
		</div>
		<div class="navBarR active" id="columnnext" onClick="goNext();">
		<span></span></div>
		</nav>
		<script type="text/javascript">
			function setColumnWidth(){
				<s:iterator value="childMenuList" var="childMenu" status="status">
					getTotalWidth(<s:property value="#status.count"/>);
				</s:iterator>
				$("#singleScrollUl").css("width",ulwidth);		
			}
		</script>
		</div> 
		</div>
	</header>
</div>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<div class="yincang" id="div1" style="display:block">
	<s:iterator value="pageList" var="pageAbout">
		<p><s:property value="#pageAbout.metaValue" escapeHtml="false"/></p>
		<img src="<%=filePath%>/<s:property value="#pageAbout.metaImageValue"/>"/>
	</s:iterator>
</div>
<div id="overDiv" style="display:none;"></div>
</div>
<div style=" height:50px;"></div>
<div style=" clear:both"></div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
	function show(menuID){
		document.getElementById("overDiv").style.display = "block" ;
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		var urlstr= url[0] + "!getMenuPageJson?" + url[1].split("?")[1].split("&")[0] + "&menuNameID=1";
		$.ajax({
			url : urlstr,
			type :"post",
			data :{
				"menuID" : menuID
			},
			datatype : "json",
			success : function(date){
				var jsons = eval(date);
				var str ="";
				for(var i = 0;i<jsons.length;i++){
					str +="<p style='text-indent: 2em; margin: 10px;'>"+jsons[i].metaValue+"</p><img  src=\"<%=filePath%>/"+jsons[i].metaImageValue+"\" style='margin-left:5%; width: 90%; height: 60%;'/>";
				}
				$("#div1").html(str);
			},
			error: function() { 
				alert("网络异常!");
			}
		});
	}
</script>
</body>
</html>

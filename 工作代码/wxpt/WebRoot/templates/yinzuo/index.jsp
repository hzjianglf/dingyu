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
    
    <title><s:property value="enterInfor.enterName"/></title>
	<script src="js/jquery-1.4.4.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<script src="js/slides.min.jquery.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<script>
			$(function(){
				$('#slides').slides({
					preload: true,
					preloadImage: 'img/loading.gif',
					play: 5000,
					pause: 2500,
					hoverPause: true
				});
			});	
	</script>
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
	<header>
		<!--<div><img src="images/logo.png"></div>-->
        <div id="container">
          <div id="example">
            <div id="slides" style="width:100%">
              <div class="slides_container" > 
              <s:iterator value="siteOptionList" var="siteOption">
				<img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>"  width="100%" height="214" /> 
			  </s:iterator>
			   </div>
            </div>
          </div>
        </div>
	</header>
	<nav>
		<ul>
		<s:iterator  status="i" value="siteMenuList" var="siteMenu" begin="0" end="3">
			
			<a href="javascript:getUrl(<s:property value="#siteMenu.menuId"/>,<s:property value="#i.index+1"/>)">
				<li>
					<ul>
						<li><img src="<%=filePath %>/<s:property value="#siteMenu.imageValue"/>"  width="50" height="50"/></li>
						<li id="li2"><span id="list1"><s:property value="#siteMenu.menuName" escapeHtml="false"/></span></li>
						<li id="li3"><span id="list2">&nbsp;</span></li>
						<li id="li1"><img src="images/jiantou.png" /></li>
						<div id="div1"><img src="images/xian.png"  width="100%"/></div>
					</ul>
				</li>
			</a>
			
		</s:iterator>
		</ul>
	</nav>
	<div style="height: 50px;"></div>
	<jsp:include page="footer.jsp"/>
	
</body>
</html>
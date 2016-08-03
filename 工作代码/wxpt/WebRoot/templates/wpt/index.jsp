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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><s:property value="enterInfor.enterName"/></title>
    <script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/url.js"></script>
<link href="../css/url_skip.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="top">
<div class="logo">
	<img src="<%=filePath%>/<s:property value="logoName"/>"/>
</div>
<div class="break">
    <a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
</div>
</div>

<nav class="navigation01" >
<div class="navBody" id="singleScroll">
<ul class="sc_scroller" id="singleScrollUl" style="width:6000px"><li><span class="active" id="column1"></span></li><li><span class="active" id="column2"></span></li><li><span class="active" id="column3"></span></li><li><span class="active" id="column4"></span></li><li><span class="active" id="column5"></span></li></ul>
</div>
</nav>
<script type="text/javascript">
function setColumnWidth(){getTotalWidth("1");getTotalWidth("2");getTotalWidth("3");getTotalWidth("4");getTotalWidth("5");$("#singleScrollUl").css("width",ulwidth);		
}
</script>
<div class="FrontSlide" >
<div class="bannerScrollWrap" >
<div class="leftCover"></div>
<div id="bannerScroll">
<div class="prev"><span id="prev" class="active"></span></div>
<ul class="bannerList sc_scroller">
<s:iterator value="siteOptionList" var="siteOption">
	<li><a href="<s:property value="#siteOption.url"/>"><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>" width="100%" height="100%"/></a></li> 
</s:iterator>
</ul>

<div class="next"><span id="next" class="active"></span></div> 
</div>
<div class="rightCover"></div>
</div>
</div>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<div class="center">
	<div clas="center_row1">
    	<a href="javascript:getChildUrl('<s:property value="#attr.siteMenuList.{menuId}[0]"/>','1')"><div class="center_row1_one">
        	<ul>
            	<li><img src="images/grid.png" /></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[0]" escapeHtml="false"/></li>
            </ul>
        
        
        </div></a>
        <a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[1]"/>','2')"><div class="center_row1_two">
        	<ul>
            	<li><img src="images/smarpho.png"></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[1]" escapeHtml="false"/></li>
            </ul>
        
       </div></a>
        <a href="javascript:getChildUrl('<s:property value="#attr.siteMenuList.{menuId}[2]"/>','3')"><div class="center_row1_three">
        	<ul>
            	<li><img src="images/thumbs.png"></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[2]" escapeHtml="false"/></li>
            </ul>
        
        </div></a>
    </div>
	<div clas="center_row2">
    	<a href="javascript:getChildUrl('<s:property value="#attr.siteMenuList.{menuId}[3]"/>','4')"><div class="center_row2_one">
        	<ul>
            	<li><img src="images/chat3.png"></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[3]" escapeHtml="false"/></li>
            </ul>
        
        
        </div></a>
        <a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[4]"/>','5')"><div class="center_row2_two">
        	<ul>
            	<li><img src="images/user.png"></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[4]" escapeHtml="false"/></li>
            </ul>
        
        </div></a>
        <a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[5]"/>','6')"><div class="center_row2_three">
        	<ul>
            	<li><img src="images/pc.png"></li>
                <li><s:property value="#attr.siteMenuList.{menuName}[5]" escapeHtml="false"/></li>
            </ul>
        
        </div></a>
    </div>
</div>
<div style=" height:50px;"></div>
<div style=" clear:both"></div>
<jsp:include page="footer.jsp"></jsp:include>
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

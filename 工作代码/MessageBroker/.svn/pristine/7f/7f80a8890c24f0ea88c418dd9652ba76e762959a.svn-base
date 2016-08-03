<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,com.ibm.hibernate.*, com.opensymphony.xwork2.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
	
<!-- -->
<!-- Licensed Materials - Property of IBM -->
<!-- 5724-N72 5655-W17 -->
<!-- (c) Copyright IBM Corp. 2006, 2009 All Rights Reserved -->
<!-- US Government Users Restricted Rights - Use, duplication or -->
<!-- disclosure restricted by GSA ADP Schedule Contract with -->
<!-- IBM Corp. -->
<!-- -->
<!-- header.jsp R5 edition -->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="image/svg+xml; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>WebSphere Message Broker 控制台</title>

<style type="text/css">
/* The browser agent is mozilla/5.0 (windows; u; windows nt 5.1; zh-cn; rv:1.9.2.10) gecko/20100914 firefox/3.6.10 */
/* The browser type is firefox */
/* The browser version is 3 */
/* The agent locale is zh */
/* The agent OS is nt */
/* The font size multiplier is 1.2 */

</style>

<link rel="stylesheet" href="theme/standard/css/pageStyles_3c.css" type="text/css"/>

<style type="text/css">
body {
  font-size: 0.78em;
}

</style>

<link rel="stylesheet" type="text/css" href="theme/standard/css/hideRightColumn.css"/>
<link rel="stylesheet" type="text/css" href="theme/standard/css/showLeftColumn.css"/>

<script type="text/javascript" src='scripts/dojo/dojo.js' djConfig="parseOnLoad:true, locale:'zh-cn', gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript" src='scripts/menu.js'></script>
<script type="text/javascript" src='scripts/utils.js'></script>
<script type="text/javascript" src='scripts/preferences.js'></script>
<script type="text/javascript" src='scripts/customAction.js'></script>
<script type="text/javascript" src='scripts/widgets/DateTimePopup.js'></script>

<script type="text/javascript">
var user_name = "root";
var USER_NAME_ENCODED = encodeURIComponent(user_name);
</script>
</head>

<body id="mainbody" class="tundra firefox firefox3">

<script type="text/javascript">
// Load the required the Dojo modules
dojo.registerModulePath("com.ibm.sr.widgets", "../widgets");
dojo.require("com.ibm.sr.widgets.AutoSuggest");
dojo.require("dojo.parser");

var currentTheme="standard";
var contextRoot="MessageBroker";
var rtlMode="";
var isRTL=false;
if (rtlMode!="")
	isRTL=true;
var browserType="firefox";
var browserVersion="3";
var fontMultiplier=1.2;
var noInfoAvailable = "要获取字段帮助信息，请在帮助光标出现时选择一个字段标签或列表标记。";
var selectText = "选择";
var lookInPageHelp = "";
var TRANS_GENERAL_MSG_EXPAND = "展开";
var TRANS_GENERAL_MSG_COLLAPSE = "折叠";
var TRANS_GENERAL_ERROR_ALT = "错误";
var TRANS_GENERAL_WARNING_ALT = "警告";
var TRANS_GENERAL_INFO_ALT = "信息";
var textAJAXError = "服务器返回错误。请与系统管理员联系。";
var TRANS_SOURCE_FILE="源文件：";
var TRANS_FAILING_LINE="出现故障的行号：";

function submitPerspective()
{
	var form=document.getElementById("perspectiveForm");
	if (form)
		form.submit();
}

dojo.addOnLoad(buildMenus);
</script>

<div class="accessibility-jumps-top">
  辅助功能选项锚点链接：
  <a href="#title">跳转至标题</a>, 
  <a href="#main">跳转至主要内容</a>, 
  <a href="#important">跳转至重要消息</a>

</div>

<div class="gutters" id="gutters">
<div class="pagewrapper" id="pagewrapper">

<div class="banner">
  <div class="bannerlogo" id="bannerlogo">
    <div class="bannerleft">
      <div class="bannerright"></div>
		  <img src="theme/standard/images/productLogo.png" alt="" id="webspherelogo"/>
		  <img src="theme/standard/images/productName.png" alt="WebSphere Message Broker" title="WebSphere Message Broker" id="productname"/>
		  <img src="theme/standard/images/companyLogo.gif" alt="IBM Logo" title="IBM Logo" id="ibmlogo"/>
    </div>
  </div>

  <div class="bannerbottom">
    <div class="bannerbottomleft"></div>
    <div class="bannerbottomright"></div>
  </div>
</div>

<!-- 顶部菜单栏目 -->

<div class="bannermenu">

<jsp:include page="menu.jsp" flush="true" />

<div class="clearall"></div>
</div>

<!-- 顶部菜单栏目 -->
<div class="signoutcontainer">
  <form name="PerspectiveForm" method="get" action="SelectPerspective.do" id="perspectiveForm">

  <ul id="menubar2" class="menubar">
    <li class="signout">透视图：</li>
    <s:if test="#session.groups=='Administrators'">
    	<li><span style="color:white">&nbsp; 管理员 &nbsp;</span></li>
	  </s:if>
		<s:else>
			<li><span style="color:white">&nbsp; 用户 &nbsp;</span></li>
		</s:else>
  </ul>

  <span class="signout">
	  &nbsp;|&nbsp;
  	<span class="bold"><s:property value="#session.username"/></span>
  	&nbsp;|&nbsp;
		<a href="j_logoff.action">注销</a>
  </span>
  </form>
</div>


<div class="outercolumncontainer" id="outercolumncontainer">
<div class="innercolumncontainer" id="innercolumncontainer">

<!-- 左边菜单栏目 -->
<div class="leftcolumn" id="leftcolumn"> 
<div class="inside">

<jsp:include page="navtree3.jsp" flush="true" />

</div> <!-- inside -->
</div> <!-- leftcolumn -->
<!-- 左边菜单栏目结束 -->

<div class="contentcolumn" id="contentcolumn">
<div class="previewdocconfirmpage">
<div class="inside">

<div class="columnlayout widthalign">

<div id="column1" class="two-col">

<a name="title"></a>
<!-- Portlet title -->
<div class="portlet mainwindow" id="docconfview">
  <h2>
    <span class="righttitleicon">
			<a href="ShowPageHelp.do?topic=load.documents.docs.loaded.successfully" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1"><img src="theme/standard/images/title_help.gif" alt="查看该页面的更多相关信息（在新窗口中打开）"/></a>

    </span>
    <span id="docconfviewheading">注册代理</span>
  </h2>
  <div class="portletbody" id="docconfviewbody">

    <div class="mediumpad" id="docconfviewbodypadding">
      <a name="important"></a> 
			
			<!-- Breadcrumb trail -->
			<h3 class="breadcrumb" id="title-bread-crumb"><s:property value="message"/></h3>

      <a name="main"></a>
      <p>
				<span style="color:Green;font-weight:bold"><s:property value="comment1"/></span>
      </p>
      <p>
				<s:property value="comment2"/>
      </p>
      
      	
			<!-- results table -->
			<div class="widthalign horizontalscroll">
      <table id="collection-table" class="collectiontable" cellspacing="1" cellpadding="0" border="0" summary="列表">
				<tr>
	
					<th width="15%">
					  队列管理器名称
					</th>
					<th width="15%">
					  代理名称
					</th>
					<th width="14%">
					  主机名或IP地址
					</th>
					<th width="14%">
					  端口号
					</th>
					<th width="14%">
					  服务器连接通道
					</th>
					<th width="14%">
					  描述
					</th>		
					<th width="14%">
					  注册时间
					</th>									
				</tr>
	      
	      <s:iterator value="brokerList" status="stat">
        <tr>		
					<td><s:property value="qmgrname"/></td>
					<td><s:property value="bname"/></td>
					<td><s:property value="hostname"/></td>
					<td><s:property value="port"/></td>
					<td><s:property value="svrconn"/></td>
					<td><s:property value="description"/></td>
					<td><s:property value="registrytime"/></td>
				</tr>
				</s:iterator>
			</table>
			</div> <!-- widthalign -->
			<div class="spaceabove">

			</div>
    </div> <!-- end padding -->
  </div> <!-- end portletbody -->
</div> <!-- end portlet -->

</div> <!-- column1 -->


<div id="column2" class="two-col">
<div class="columntwopad widthalign">
<!-- page help -->

      <div id="wasHelpPortletPos" class="portlet helpwindow">
        <h2><span id="helpwindowheading">帮助</span></h2>
        <div class="portletbody" id="helpwindowbody">
          <div class="mediumpad">
            <div class="helptitle">字段帮助</div>

            <div id="fieldHelpPortlet">
              要获取字段帮助信息，请在帮助光标出现时选择一个字段标签或列表标记。
            </div>
   
            <div class="helptitle2">页面帮助</div>
            <div id="pageHelpLink">
              <a href="ShowPageHelp.do?topic=load.documents.docs.loaded.successfully" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1">有关此页面的更多信息</a>

              （在新窗口中打开。）
            </div>

          </div> <!-- end padding -->
        </div> <!-- end portletbody -->
      </div> <!-- end portlet -->

</div> <!-- column2 pad -->
</div> <!-- column2 -->


</div> <!-- columnlayout -->
</div> <!-- inside -->
</div> <!-- previewdocpage -->



</div> <!-- contentcolumn -->
<div class="rightcolumn" id="rightcolumn">
<div class="inside">


</div> <!-- inside -->
</div> <!-- rightcolumn -->

<div class="clearall"></div>
<!--  add the form needed for custom actions that submit the page -->
<form name="CustomActionForm" method="post" action="CustomAction.do" id="com.ibm.sr.ui.forms.CustomActionForm">
	<input type="hidden" name="submitAction" value="" id="submitactionhiddenfoot"/>
</form>


</div> <!-- innercontainer -->
</div> <!-- outercontainer -->

  </div> <!-- end page wrapper -->
</div> <!-- end gutters -->


<div class="footer" id="footer">
  <div class="footer-left">
  </div>
  <div class="footer-right">
  </div>
  <div class="footer-center">
    &copy; Copyright IBM Corp. 2006, 2009 All Rights Reserved
  </div>

</div>

<script type="text/javascript"> </script>
</body>
</html>
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

<jsp:include page="navtree.jsp" flush="true" />
	
</div> <!-- inside -->
</div> <!-- leftcolumn -->
<!-- 左边菜单栏目结束 -->

<div class="contentcolumn" id="contentcolumn">

<script type="text/javascript" src='/ServiceRegistry/scripts/ellipsis.js'></script>

<script type="text/javascript">
	if (typeof window != "undefined")
	    dojo.connect(window, 'onresize', 'displayHomePageFacets');  // window resize
</script>

<div class="homepage">
<div class="inside">

<script type="text/javascript">
function encodeFields()
{
	if (document.getElementById("localFilepath") != null)
		document.getElementById("escapedFilePath").value = encodeURI(document.getElementById("localFilepath").value);
	
	if (document.getElementById("documentDescription") != null)
		document.getElementById("escapedDescription").value = encodeURI(document.getElementById("documentDescription").value);
		
	if (document.getElementById("documentVersion") != null)		
		document.getElementById("escapedVersion").value = encodeURI(document.getElementById("documentVersion").value);
		
	if (document.getElementById("documentNamespace") != null)
	  document.getElementById("escapedNamespace").value=encodeURI(document.getElementById("documentNamespace").value);
}

function showHideNameSpace(objectId) {
  if (document.getElementById(objectId) != null) {
    if (document.getElementById("docType").value == "GenericDocument" || document.getElementById("docType").value == "XMLDocument" || document.getElementById("docType").value == "ZIP"  || document.getElementById("docType").value == "AUTO") {
      document.getElementById(objectId).style.display = "";
      state = "inline";
    } else {
      document.getElementById(objectId).style.display = "none";
      state = "none";
		}
  }
}
</script>
<script type="text/javascript" src='scripts/customAction.js'></script>


<a name="important"></a>
<span class="homepagemessages">
<!-- Message box included here -->


<div id="messagePortletDiv" class="messagePortlet invisible">

	<div class="sectiondivider" id="inlineMessages">
    <a href="javascript:toggleSection('inlineMessages')" class="blacknounderline" tabindex="1">
      <img id="img_inlineMessages" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
    </a>
    消息
	</div>
  <div class="expandablesection expanded" id="child_inlineMessages">
  	<div class="messages-indent">
			<div id="messagesTable" class="messagePortlet2">

			</div>
  	</div>
  </div>
</div>

</span>

<div id="facetMeasure">
<div class="widthalign">
<a name="title"></a>
<a name="main"></a>
<table class="homePageTable" border="0" cellpadding="5" cellspacing="0">
	<tr>
		
		<td class="mainColumn">			
			<table id="welcomePanel" border="0" cellpadding="0" cellspacing="0">
				<tr>										
					<td class="homePageWelcomePanel" style="background-image: url('theme/standard/images/welcomePanelBackground.jpg');">
						<div class="welcome-text-title">WebSphere Message Broker Monitoring and Management</div>
						<div class="welcome-text">使用此配置透视图可以监控和管理 WebSphere Message Broker。<br/><br/>
								<s:if test="#session.groups=='Administrators'">
										<h2 class="welcome-text-bold">代理监控</h2>"代理监控"包含 WebSphere Message Broker 的消息流处理记录和分析图表。可以使用"代理监控"菜单来查看消息流的实时监控记录、历史监控记录、处理失败记录、实时分析图表、历史分析图表以及代理运行状态，也可以根据根据条件查询消息流处理记录和统计分析图表。<br/><br/> 
										<h2 class="welcome-text-bold">系统监控</h2>"系统监控"包含 WebSphere Message Broker 运行环境的系统实时资源信息以及Broker所占用的实时资源信息。 <br/><br/> 
										<h2 class="welcome-text-bold">SLA管控</h2>"SLA管控"包含 WebSphere Message Broker 运行环境中的SLA告警，流量控制和超时控制等。<br/><br/>
										<h2 class="welcome-text-bold">代理管理</h2>"代理管理"包含 WebSphere Message Broker 的注册信息。您可以使用"代理管理"菜单来查看当前代理的系统信息、注册监控代理、管理代理信息以及服务质量的控制和管理。<br/><br/>
										<h2 class="welcome-text-bold">安全管理</h2>"安全管理"包含 WebSphere Message Broker 的用户管理机制。您可以使用"安全管理"菜单来创建和管理用户、用户组和角色，修改用户密码。<br/><br/>
										<!--<h2 class="welcome-text-bold">访问控制</h2>“访问控制”包含 WebSphere Message Broker 的用户访问控制、IP访问控制和IP访问记录。<br/><br/> -->
								</s:if>
								<s:else>
										<h2 class="welcome-text-bold">代理监控</h2>"代理监控"包含 WebSphere Message Broker 的消息流处理记录和分析图表。可以使用"代理监控"菜单来查看消息流的实时监控记录、历史监控记录、处理失败记录、实时分析图表、历史分析图表以及代理运行状态，也可以根据根据条件查询消息流处理记录和统计分析图表。<br/><br/> 
										<h2 class="welcome-text-bold">系统监控</h2>"系统监控"包含 WebSphere Message Broker 运行环境的系统实时资源信息以及Broker所占用的实时资源信息。 <br/><br/> 
								</s:else>	
						</div>
					</td>
				</tr>
			</table>
		</td>
				
		<td class="browseColumn">		
      <div class="widthalign">
					<table cellpadding="0" cellspacing="0" border="0" class="homePageReferencePanel">
						<thead>
							<tr>
								<th colspan="2" class="homePagePortletTitle"><h2>帮助</h2></th>
								<th class="homePagePortletTitleControls"><a href="javascript:toggleSection('wsrrReferencePanel1')" class="blacknounderline" tabindex="1"><img id="img_wsrrReferencePanel1" src="theme/standard/images/title_minimize.gif" alt="折叠"/></a></th>
							</tr>
						</thead>
						<tbody id="child_wsrrReferencePanel1">
							<tr>
								<td class="homePagePortletArea" colspan="3" id="wsrrReferencePanel1Area">
									
										<div class="referenceLink">
	
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www-01.ibm.com/software/integration/wbimessagebroker/" tabindex="1">IBM.com 上的 WebSphere Message Broker</a>
										</div>
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://publib.boulder.ibm.com/infocenter/wmbhelp/v7r0m0/index.jsp" tabindex="1">在线信息中心</a>
										</div>
									
								</td>
	
							</tr>
						</tbody>
					</table>
											
					<table cellpadding="0" cellspacing="0" border="0" class="homePageReferencePanel">
						<thead>
							<tr>
								<th colspan="2" class="homePagePortletTitle"><h2>Web 资源</h2></th>
								<th class="homePagePortletTitleControls"><a href="javascript:toggleSection('wsrrReferencePanel2')" class="blacknounderline" tabindex="1"><img id="img_wsrrReferencePanel2" src="theme/standard/images/title_minimize.gif" alt="折叠"/></a></th>

							</tr>
						</thead>
						<tbody id="child_wsrrReferencePanel2">
							<tr>
								<td class="homePagePortletArea" colspan="3" id="wsrrReferencePanel2Area">
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www-947.ibm.com/support/entry/portal/Overview/Software/WebSphere/WebSphere_Message_Broker" tabindex="1">支持</a>

										</div>
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www-01.ibm.com/software/integration/wbimessagebroker/wbimessagebroker-library/" tabindex="1">库</a>
										</div>
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www.ibm.com/developerworks/cn/" tabindex="1">IBM 开发者中文网</a>

										</div>
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www.ibm.com/developerworks/webservices/library/ws-soa-whitepaper/" tabindex="1">IBM SOA 基础：体系结构介绍与概述</a>
										</div>
									
										<div class="referenceLink">
			                <img src="theme/standard/images/HomeArrow.gif" alt=""/>
											<a target="_blank" href="http://www.ibm.com/software/websphere/" tabindex="1">IBM.com 上的产品信息</a>

										</div>
									
								</td>
							</tr>
						</tbody>
					</table>
	
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
								<td class="homePageAboutPanel">
									<h2 class="homePageAboutPanelTitle"><img src="theme/standard/images/about.gif" alt=""/>
										关于您的 WebSphere Message Broker
									</h2>
									
									<div>IBM WebSphere Message Broker，8.0.0.1</div>								
											
									<div>&nbsp;</div>								
									<div>Licensed Material - Property of IBM</div>								
									<div>© Copyright IBM Corp. 1997, 2011 All Rights Reserved.</div>									
									<div>U.S. Government users - RESTRICTED RIGHTS - Use, Duplication, or Disclosure restricted by GSA-ADP schedule contract with IBM Corp. IBM 是 IBM Corp. 的注册商标。</div>														
								</td>
						 </tr>
					</table>
      </div> <!-- widthalign -->
		</td>		
	</tr>

</table>
</div> <!-- widthalign -->
</div> <!--  facetMeasure -->

</div> <!-- inside -->
</div> <!-- homepage -->
<div id="textMeasureTR" style="display: none; visibility: hidden"><div class="textmeasurenoleft" id="textmeasure"></div></div>

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
    &copy; Copyright IBM Corp. 1997, 2010 All Rights Reserved
  </div>
</div>

<script type="text/javascript"> </script>
</body>
</html>
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

<script LANGUAGE="Javascript" SRC="FusionCharts/FusionCharts.js"></script>

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

<jsp:include page="navtree1.jsp" flush="true" />

</div> <!-- inside -->
</div> <!-- leftcolumn -->
<!-- 左边菜单栏目结束 -->

<div class="contentcolumn" id="contentcolumn">

<div class="detailview">

<div class="inside">

<!-- Detail.jsp R5 edition -->

<script type="text/javascript" src='scripts/ncname.js'></script>
<script type="text/javascript" src='scripts/detailView.js'></script>


<div class="columnlayout widthalign">

<div id="column1" class="two-col">

<a name="title"></a>
<!-- Portlet title -->
<div class="portlet mainwindow" id="detailview">
  <h2>
  	<span class="righttitleicon">
  		<a href="" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1"><img src="theme/standard/images/title_help.gif" alt="查看该页面的更多相关信息（在新窗口中打开）"/></a>

  	</span>
  	<span id="detailviewheading">
  		实时统计
  	</span>
  </h2>
<div class="portletbody" id="detailviewbody">
<div class="mediumpad" id="detailviewbodypadding">

<a name="important"></a> 
<!-- Message box included here -->

<!-- Breadcrumb trail -->
<h3 class="breadcrumb" id="title-bread-crumb">实时统计图表</h3>

<a name="main"></a>
	  <p id="detailviewdescription">
	    用户在该页面查看当天的消息流数据汇总和分析图表。
	  </p>

		<!-- Tab section -->
    <table class="tabtable" border="0" cellpadding="0" cellspacing="0" id="tabtable">
        <tr>
          <td class="tab-on">
            <div>实时图表信息</div>            
          </td> 
          <td class="blank-tab">
            <img src="theme/standard/images/onepix.gif" width="2" height="10" alt=""/> 
          </td>
         
        </tr>
     </table> <!-- end tab table -->

<!-- detailContent.jsp included here -->
<script type="text/javascript">
var optionalVarIndex={};
var optionalVars={};
var optionalVarList=[];
var flattenedProperties={"data":[{"propertyName":"name","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.name"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.name.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"ESB-SIMU-01.wsdl","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"location","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.location"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.location.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"ESB-SIMU-01.wsdl","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"description","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.description"},"displayAs":"textarea","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.description.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"TEXTAREA","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"namespace","targets":[],"deleteable":false,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.namespace"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.namespace.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/www.mboss.com.cn\/csb","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"owner","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.owner"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.owner.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"UNAUTHENTICATED","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"version","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.version"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.version.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"1.0","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"lastModified","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.last.modified"},"displayAs":"datetime","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.last.modified.field.help"},"originalValue":"1286291277871","defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"2010\u5e7410\u67085\u65e5\u661f\u671f\u4e8c \u4e0b\u534811\u65f607\u520657\u79d2\u4e2d\u56fd (\u4e0a\u6d77)","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"DATETIME","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"encoding","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"propertyMessage","key":"detail.view.encoding"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":false,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"fieldHelpMessage","key":"detail.view.encoding.field.help"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"UTF-8","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_wsdl","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_wsdl"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_wsdl"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/schemas.xmlsoap.org\/wsdl\/","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_wsdlsoap","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_wsdlsoap"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_wsdlsoap"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/schemas.xmlsoap.org\/wsdl\/soap\/","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_xsd","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_xsd"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_xsd"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/www.w3.org\/2001\/XMLSchema","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_intf","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_intf"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_intf"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/www.mboss.com.cn\/csb","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_apachesoap","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_apachesoap"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_apachesoap"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/xml.apache.org\/xml-soap","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"xmlns_impl","targets":[],"deleteable":true,"customAction":null,"followLink":true,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_impl"},"displayAs":"uri","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"xmlns_impl"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":false,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"http:\/\/www.mboss.com.cn\/csb","multiLanguageValues":null,"constraints":{"readOnly":false,"displayAs":"URI","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":false},"multipleLanguageVariants":false,"type":null},{"propertyName":"bsrURI","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"bsrURI"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"bsrURI"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"c917f7c9-7beb-4bd0.b22b.dc9a23dc2b06","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"creationTimestamp","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"creationTimestamp"},"displayAs":"datetime","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"creationTimestamp"},"originalValue":"1286291260613","defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"2010\u5e7410\u67085\u65e5\u661f\u671f\u4e8c \u4e0b\u534811\u65f607\u520640\u79d2\u4e2d\u56fd (\u4e0a\u6d77)","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"DATETIME","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"lastModifiedBy","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"lastModifiedBy"},"displayAs":"text","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"lastModifiedBy"},"originalValue":null,"defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"UNAUTHENTICATED","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"TEXT","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null},{"propertyName":"contentSize","targets":[],"deleteable":false,"customAction":null,"followLink":false,"name":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"contentSize"},"displayAs":"long","multiLanguageMessage":null,"mandatoryValue":false,"deleted":false,"fieldHelp":{"doNotFilter":false,"values":null,"doNotTranslate":true,"resourceBundleName":"com.ibm.sr.ui.resources.ApplicationResources","name":"DEFAULT_NAME","key":"contentSize"},"originalValue":"2564","defaultMultiLanguageValue":"","values":null,"multiLanguages":[],"readOnly":true,"constraintsString":"","submitAction":"","defaultValue":null,"submitOnLink":false,"value":"2,564","multiLanguageValues":null,"constraints":{"readOnly":true,"displayAs":"LONG","enumeration":false,"constraintsString":"","mandatoryValue":false,"maxCardinality":-1,"allowedValues":null,"minCardinality":0,"mustExist":true},"multipleLanguageVariants":false,"type":null}]};
var flattenedList=[];
var editableProperties=true;
</script>

<!-- detail two-column table -->
							
<div class="widthalign">

<% 
	ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
  String strArea2D = (String)vs.findValue("chartArea2D");
  String strLine2D = (String)vs.findValue("chartLine2D"); 
  String strPie3D = (String)vs.findValue("chartPie3D");                                                       
%>  
	
<form name="currentchart" method="post" action="getcurrentcharts.action" id="currentchart">
<table class="detailcontenttable fixedlayout" border="0" cellpadding="0" cellspacing="0" id="detailcontenttable">  
  <tr> 
     	
    <td colspan="20" class="nopad">     			
      <div class="editlinks" id="editlinks">      	
        <span id="connector1">&nbsp;&#x202a;&#x202c;&nbsp;</span>
        <span id="connector2">&nbsp;&#x202a;&#x202c;&nbsp;</span>	   
      </div>
    </td>
  </tr>   
  <tr>
    <td colspan="20">
        <div>
          <input type="hidden" name="itemType" value="">
          <input type="hidden" name="parentUri" value="">
        </div>
				
				<!-- properties here -->
        <div class="propertiessection" id="propertiessection">
      
          <div class="sectiondivider" id="propSection0">
            <table class="sectiontitletable" border="0" cellpadding="0" cellspacing="0">
              <tr>

                <td width="1%">
                  <a href="javascript:toggleSection('propSection0')" class="blacknounderline" tabindex="1">
                    <img id="img_propSection0" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
                  </a>
                </td>
                <td width="99%">
                  <div class="sectiontitle">
                    区域图
                  </div>
                </td>
              </tr>
            </table>
          </div>
          
          <div>
          	<br/>
          </div> 
  
          <div class="expandablesection expanded" id="child_propSection0">
	    			 <div align="center">
	        			<jsp:include page="Includes/FusionChartsRenderer.jsp" flush="true">
	              <jsp:param name="chartSWF" value="FusionCharts/FCF_Area2D.swf" />
	              <jsp:param name="strURL" value="" />
	              <jsp:param name="strXML" value="<%=strArea2D%>" />
	              <jsp:param name="chartId" value="curchart1" />
	              <jsp:param name="chartWidth" value="600" />
	              <jsp:param name="chartHeight" value="450" />
	              <jsp:param name="debugMode" value="false" />
	              <jsp:param name="registerWithJS" value="false" />
	          		</jsp:include>	
							</div>
		    
          </div>	
        </div> <!-- end properties section (expandablesection expanded) -->
               
        <div>
          <input type="hidden" name="itemType" value="">
          <input type="hidden" name="parentUri" value="">
        </div>
				
				<!-- properties here -->
        <div class="propertiessection" id="propertiessection">
          <div class="sectiondivider" id="propSection1">
            <table class="sectiontitletable" border="0" cellpadding="0" cellspacing="0">
              <tr>

                <td width="1%">
                  <a href="javascript:toggleSection('propSection1')" class="blacknounderline" tabindex="1">
                    <img id="img_propSection1" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
                  </a>
                </td>
                <td width="99%">
                  <div class="sectiontitle">
                    折线图
                  </div>
                </td>
              </tr>
            </table>
          </div>
          
          <div>
          	<br/>
          </div> 
                     
          <div class="expandablesection expanded" id="child_propSection1">
        			 <div align="center">
	          			<jsp:include page="Includes/FusionChartsRenderer.jsp" flush="true">
	                <jsp:param name="chartSWF" value="FusionCharts/FCF_Line.swf" />
	                <jsp:param name="strURL" value="" />
	                <jsp:param name="strXML" value="<%=strLine2D%>" />
	                <jsp:param name="chartId" value="curchart2" />
	                <jsp:param name="chartWidth" value="600" />
	                <jsp:param name="chartHeight" value="450" />
	                <jsp:param name="debugMode" value="false" />
	                <jsp:param name="registerWithJS" value="false" />
	            		</jsp:include>	
								</div>
          						    
          </div>	
        </div> <!-- end properties section (expandablesection expanded) -->
               
				<!-- properties here -->
        <div class="propertiessection" id="propertiessection">
          <div class="sectiondivider" id="propSection2">
            <table class="sectiontitletable" border="0" cellpadding="0" cellspacing="0">
              <tr>

                <td width="1%">
                  <a href="javascript:toggleSection('propSection2')" class="blacknounderline" tabindex="1">
                    <img id="img_propSection2" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
                  </a>
                </td>
                <td width="99%">
                  <div class="sectiontitle">
                    3D饼图
                  </div>
                </td>
              </tr>
            </table>
          </div>
          
          <div>
          	<br/>
          </div> 
                     
          <div class="expandablesection expanded" id="child_propSection2">
        			 <div align="center">
	          			<jsp:include page="Includes/FusionChartsRenderer.jsp" flush="true">
	                <jsp:param name="chartSWF" value="FusionCharts/FCF_Pie3D.swf" />
	                <jsp:param name="strURL" value="" />
	                <jsp:param name="strXML" value="<%=strPie3D%>" />
	                <jsp:param name="chartId" value="curchart3" />
	                <jsp:param name="chartWidth" value="600" />
	                <jsp:param name="chartHeight" value="450" />
	                <jsp:param name="debugMode" value="false" />
	                <jsp:param name="registerWithJS" value="false" />
	            		</jsp:include>	
								</div>
          						    
          </div>	
        </div> <!-- end properties section (expandablesection expanded) -->
    </td>
     
  </tr>   
</table> <!-- end content table -->
</form>

</div>  <!-- widthalign -->

</div> <!-- end padding -->
</div> <!-- end portletbody -->
</div> <!-- end portlet -->

</div> <!-- column1 -->

<div id="column2" class="two-col">
<div class="columntwopad widthalign">
<!-- page help -->

		<!-- Additional properties -->
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

              <a href="" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1">有关此页面的更多信息</a>

              （在新窗口中打开。）
            </div>
 
          </div> <!-- end padding -->
        </div> <!-- end portletbody -->
      </div> <!-- end portlet -->

</div> <!-- column2 pad -->
</div> <!-- column2 -->

</div> <!-- columnlayout -->
</div> <!-- inside -->
</div> <!-- detailpage -->


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
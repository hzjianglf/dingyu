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

<script type="text/javascript">
var TRANS_ADD_PROPERTY="添加属性";
var TRANS_ADD_PROPERTY_TITLE="添加新属性";
var TRANS_OPTIONAL_NAME="可选属性名";
var TRANS_OPTIONAL_NAME_TITLE="可选属性名。";
var TRANS_CHOOSE_OPTIONAL_NAME="选择要创建的可选属性名。";
var TRANS_CUSTOM_NAME="定制属性名";
var TRANS_CUSTOM_NAME_TITLE="定制属性名。";
var TRANS_CHOOSE_NEW_NAME="要创建的新属性名。";
var TRANS_BUTTON_ADD="添加";
var TRANS_BUTTON_CANCEL="取消";
var TRANS_BUTTON_OK="确定";
var TRANS_BUTTON_APPLY="应用";
var TRANS_BUTTON_RESET="重置";
var TRANS_DELETE_PROPERTY="删除名称为 \&quot;{0}\&quot; 的属性";
var TRANS_ERROR_EXCEPTION="与服务器通信时发生异常：";
var TRANS_SOURCE_FILE="源文件：";
var TRANS_FAILING_LINE="出现故障的行号：";
var TRANS_ERROR_RESERVED_NAME="该属性名已保留供系统使用。";
var TRANS_ERROR_INVALID_NAME="属性名未遵守命名规则。";
var TRANS_INT_PROMPT="输入整数值。";
var TRANS_INT_INVALID="值必须是介于 -2147483648 和 2147483647 之间的某个整数。";
var TRANS_SHORT_PROMPT="输入短整数值。";
var TRANS_SHORT_INVALID="值必须是介于 -32768 和 32767 之间的某个短整数。";
var TRANS_LONG_PROMPT="输入长整数值。";
var TRANS_LONG_INVALID="值必须是介于 -9223372036854775808 和 9223372036854775807 之间的某个长整数。";
var TRANS_INTEGER_PROMPT="输入大整数值。";
var TRANS_INTEGER_INVALID="该值必须是整数。";
var TRANS_FLOAT_PROMPT="输入浮点数。";
var TRANS_FLOAT_INVALID="值必须是浮点数。";
var TRANS_DOUBLE_PROMPT="输入双精度浮点数。";
var TRANS_DOUBLE_INVALID="该值必须是双精度浮点值。";
var TRANS_DATETIME_PROMPT="输入日期和时间。";
var TRANS_DATETIME_INVALID="无效的日期或时间。使用 ISO8601 格式：yyyy-mm-ddThh:mm:ss";
var TRANS_DATETIME_ICON="单击此处选择日期和时间";
var TRANS_DATE_PROMPT="输入日期。";
var TRANS_DATE_INVALID="无效日期。使用 ISO8601 格式：yyyy-mm-dd";
var TRANS_DATE_ICON="单击此处选择日期";
var TRANS_TIME_PROMPT="输入时间。";
var TRANS_TIME_INVALID="无效时间。使用 ISO8601 格式：hh:mm:ss";
var TRANS_TIME_ICON="单击此处选择时间";

function submitNavigation (action)
{
  var forSaveDiv=document.getElementById("forSave");
  var content="<input type='hidden' name='save' value='save' />";
  forSaveDiv.innerHTML=content;
  document.DetailForm.submitAction.value=action;
  document.DetailForm.submit();
}

function resetNavigation ()
{
  document.DetailForm.submitAction.value="";
}

</script>

<div class="columnlayout widthalign">

<div id="column1" class="two-col">

<a name="title"></a>
<!-- Portlet title -->
<div class="portlet mainwindow" id="detailview">
  <h2>
  	<span class="righttitleicon">
  		<a href="ShowPageHelp.do?topic=detail.view.wsdl" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1"><img src="theme/standard/images/title_help.gif" alt="查看该页面的更多相关信息（在新窗口中打开）"/></a>

  	</span>
  	<span id="detailviewheading">
  		创建告警条件
  	</span>
  </h2>
<div class="portletbody" id="detailviewbody">
<div class="mediumpad" id="detailviewbodypadding">

<a name="important"></a> 
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

<div class="msgannounce" id="msgannounce" style="display: none;">
  <p>消息</p>
  <p class="msgerror">

    <a href="javascript:toggleSection('extramessages');" id="errorExtraTwist" style="display: none;" class="blacknounderline">
      <img src="theme/standard/images/collapsed.gif" alt="展开" id="img_extramessages"/>
    </a>
    <img src="theme/standard/images/Error.gif" alt="错误"/>
    <span id="errorMsgContent"></span>
  </p>
  <div class="msgextra expandablesection collapsed" id="child_extramessages">
    <p id="errorExtraContent"></p>
  </div>
</div>

		
			<!-- Breadcrumb trail -->
			<h3 class="breadcrumb" id="title-bread-crumb">告警条件</h3>
			
			<a name="main"></a>
		  <p id="detailviewdescription">
		    	在该页面设置并创建告警条件信息。
		  </p>
		  <br/>
		  <p id="errormsg" style="color:Red;font-weight:bold"></p>
  		<p>
   			<span style="color:<s:property value="color"/>;font-weight:bold">
   				<s:property value="comment"/>
   			</span>
  		</p>
  		
		<!-- Tab section -->
    <table class="tabtable" border="0" cellpadding="0" cellspacing="0" id="tabtable">
        <tr>
          <td class="tab-on">
            <div>设置告警条件</div>            
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
<table class="detailcontenttable fixedlayout" border="0" cellpadding="0" cellspacing="0" id="detailcontenttable">  
  <tr>
    <td colspan="20" class="nopad">				
      <div class="editlinks" id="editlinks">
	  		
        <a href="javascript:editProperties()" tabindex="1" id="editlink1">
          
        </a>
        <span id="connector1">&nbsp;&#x202a;&#x202c;&nbsp;</span>
        
        <a href="EditRelationshipsPrepare.do?uri=c917f7c9-7beb-4bd0.b22b.dc9a23dc2b06" tabindex="1" id="editlink2">
          
        </a>

        <span id="connector2">&nbsp;&#x202a;&#x202c;&nbsp;</span>	  
        <a href="javascript:submitNavigation('ViewClassifications')" tabindex="1" id="editlink3">
          
        </a>
  
      </div>
    </td>
  </tr>

<script type="text/javascript">
var TRANS_LOGON_ERROR="必须指定告警名称和数量条件。";

var leaveMsg=false;
function clearMsg()
{
  var errorField=document.getElementById("errormsg");
  if (errorField)
  {
    errorField.innerHTML="";
    errorField.style.display="none";
  }
}

function handleFocus()
{
  clearMsg();
}

function handleKeypress(evt)
{
  clearMsg();
}

function handleSubmit(evt)
{
  var okToSubmit=false;
  var userNameInput=document.getElementById("alertname");
  var numberInput=document.getElementById("numberitem");
  if (userNameInput && numberInput){
    var userName=userNameInput.value;
    var num = numberInput.value;
    if (userName && num) {
       okToSubmit=true;
    } else {
      var errorField=document.getElementById("errormsg");
      if (errorField)
      {
        errorField.style.display="block";
	      errorField.innerHTML="<span class='required' style='color:Red;font-weight:bold'>"+TRANS_LOGON_ERROR+"</span>";
      }
    }
  }
  return(okToSubmit);
}
</script>

  <tr>
    <td colspan="20">
      <form name="createcondition" method="post" action="" id="createcondition" focus="alertname" onsubmit="return handleSubmit(event)">


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
                    常规属性
                  </div>
                </td>
              </tr>
            </table>
          </div>
          
          <div class="expandablesection expanded" id="child_propSection0">
	            <div class="readonlyproperty notmandatory" id="prop_0">
	              <label for="alertname" title="告警条件名称" id="label_0">
	                告警名称
	              </label>            
	              <br/>
	
	              <input type="text" name="alertname" id="alertname" title="告警条件的名称，这个值是唯一的且不可空。" tabindex="1" onfocus="handleFocus()" onkeypress="return handleKeypress(event)"/>    		  	     
	            </div>

	            <div class="readonlyproperty notmandatory" id="prop_1">
	              <label for="flowname" title="产生告警的消息流名称" id="label_4">
	                消息流名称
	              </label>            
	              <br/>
	
	              <input type="text" name="flowname" id="flowname" title="产生告警的消息流名称。" tabindex="1" />    		  	     
	            </div>
	            
	            <div class="readonlyproperty notmandatory" id="prop_2">
	              <label for="conditionitem" title="选择告警条件" id="label_2">
	                告警条件
	              </label>            
	              <br/>
	
	              <select name="conditionitem" id="conditionitem">	              	
	              	<option selected="selected" value="1">消息总数 </option>
									<option value="2">错误总数 </option>
									<option value="3">服务请求响应时间 </option>
									<option value="4">服务提供响应时间 </option>
									<option value="5">外部服务不可用</option>	
								</select>																												
	            </div>
	                	            
	            <div class="readonlyproperty notmandatory" id="prop_3">
	              <label for="numberitem" title="填写告警相关的数量条件" id="label_3">
	                数量条件
	              </label>            
	              <br/>
	
	              <input type="text" name="numberitem" id="numberitem" title="填写告警相关数量条件，如果是响应时间为条件，单位是毫秒。" tabindex="1" />
	            </div>

	            <div class="readonlyproperty notmandatory" id="prop_4">
	              <label for="interval" title="填写告警产生的单位时间间隔，单位为分钟" id="label_4">
	                单位时间间隔(分钟)
	              </label>            
	              <br/>
	
	              <input type="text" name="interval" id="interval" title="填写告警产生的单位时间间隔，单位为分钟。" tabindex="1" value="2"/>
	            </div>

	            <div class="readonlyproperty notmandatory" id="prop_5">
	              <label for="notice" title="选择告警通知方式" id="label_5">
	                告警通知方式
	              </label>            
	              <br/>
	
	              <select name="notice" id="notice">	              	
	              	<option selected="selected" value="1">仅界面显示</option>
									<option value="2">Email通知</option>
									<option value="3">MQ发布订阅</option>
								</select>																												
	            </div>
	            
	            <div class="readonlyproperty notmandatory" id="prop_6">
	              <label for="email" title="填写告警产生后通知的Email地址" id="label_6">
	                告警Email地址
	              </label>            
	              <br/>
	
	              <input type="text" name="email" id="email" title="填写告警产生后通知的Email地址。" tabindex="1" />																											
	            </div>

	            <div class="readonlyproperty notmandatory" id="prop_7">
	              <label for="mqtopic" title="填写告警产生后通过MQ发布的主题" id="label_7">
	                告警MQ发布主题
	              </label>            
	              <br/>
	
	              <input type="text" name="mqtopic" id="mqtopic" title="填写告警产生后通过MQ发布的主题。" tabindex="1" />																											
	            </div>
	            	            	            	            	            
	            <div class="readonlyproperty notmandatory" id="prop_8">
	              <label for="mydescription" title="告警信息描述" id="label_8">
	                告警信息描述
	              </label>            
	              <br/>
								
								<textarea rows="3" id="mydescription" name="mydescription" title="告警信息详细描述，包括条件和数量。" tabindex="1" ></textarea>	
								<input type="hidden" name="description" id="description" value=""/>
	            </div>
	        	            
          </div>
        </div> <!-- end properties section (expandablesection expanded) -->
        
        
        <div class="buttongroup">
        	&nbsp;
	        <span id="primarybuttons">
	          <input type="submit" name="submit" value="创建" class="standardbutton" id="navigationsubmit" tabindex="1" onclick="createAlert()"/>	          
	        </span>
					&nbsp;&nbsp;
					
				  <span id="customButtons">
						<input type="reset" name="reset" value="重设" class="standardbutton" id="navigationreset" tabindex="1"/>
				  </span>
			 	        	
		    </div>  <!-- properties -->
				<script language="javascript"> 
						function createAlert(){ 
							document.getElementById("description").value = document.getElementById("mydescription").value;
							document.createcondition.action="savealertcondition.action";
					   	document.createcondition.submit();
						}
						
				</script>
								
      </form>
    </td>
  </tr>   
</table> <!-- end content table -->
</div>

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

<script type="text/javascript">
document.createcondition.alertname.focus()
</script>

</body>
</html>
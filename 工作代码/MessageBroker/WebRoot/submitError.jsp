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
<link rel="stylesheet" type="text/css" href="scripts/dijit/themes/soria/soria.css"/>

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

<body id="mainbody" class="soria tundra firefox firefox3">

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
  		代理操作
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
<h3 class="breadcrumb" id="title-bread-crumb"><s:property value="title"/></h3>

<a name="main"></a>
	  <p id="detailviewdescription">
	    <s:property value="viewdescription"/>
	  </p>
	  <br/>
	  <p id="detailviewdescription">
	    <span style="color:Red;font-weight:bold"><s:property value="comment1"/></span>
	  </p>
	  
  	<!-- results table -->
    <div class="buttongroup">
    	&nbsp;
      <span id="primarybuttons">
      	<s:if test="operation=='deploybars'">
		      	<form name="return" method="post" action="flowdepolyment.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">
		      		<input type="hidden" name="qmgrname" value="<s:property value="qmgrname"/>" class="standardbutton" id="qmgrname" tabindex="1"/>
		        	<input type="submit" name="submit" value="返回部署" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	
				</s:if>
      	<s:elseif test="operation=='bkparametersetting'">
		      	<form name="return" method="post" action="returnruntimebroker.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">	
							 <input type="hidden" name="qmgrname" value="<s:property value="qmgrname"/>" class="standardbutton" id="qmgrname" tabindex="1"/>
					     <input type="hidden" name="brokername" value="<s:property value="brokername"/>" class="standardbutton" id="brokername" tabindex="1"/>					     
					     <input type="hidden" name="brokerRuntimeParam" value="<s:property value="brokerRuntimeParam"/>" class="standardbutton" id="brokerRuntimeParam" tabindex="1"/>			        	 		        	 
		        	 <input type="submit" name="submit" value="返回代理参数" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	
				</s:elseif>
      	<s:elseif test="operation=='egparametersetting'">
		      	<form name="return" method="post" action="returnruntimeexecutiongroup.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">	
							 <input type="hidden" name="qmgrname" value="<s:property value="qmgrname"/>" class="standardbutton" id="qmgrname" tabindex="1"/>
					     <input type="hidden" name="brokername" value="<s:property value="brokername"/>" class="standardbutton" id="brokername" tabindex="1"/>
					     <input type="hidden" name="egname" value="<s:property value="egname"/>" class="standardbutton" id="egname" tabindex="1"/>					     
		        	 <input type="hidden" name="egRuntimeParam" value="<s:property value="egRuntimeParam"/>" class="standardbutton" id="egRuntimeParam" tabindex="1"/>					              	
		        	 <input type="submit" name="submit" value="返回执行组参数" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	
				</s:elseif>	
      	<s:elseif test="operation=='configservice'">
		      	<form name="return" method="post" action="returnconfigservice.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">	
							 <input type="hidden" name="qmgrname" value="<s:property value="qmgrname"/>" class="standardbutton" id="qmgrname" tabindex="1"/>
					     <input type="hidden" name="brokername" value="<s:property value="brokername"/>" class="standardbutton" id="brokername" tabindex="1"/>
							 <input type="hidden" name="selectedConfigServiceType" value="<s:property value="selectedConfigServiceType"/>" class="standardbutton" id="selectedConfigServiceType" tabindex="1"/>					              	
					     <input type="hidden" name="selectedConfigServiceItems" value="<s:property value="selectedConfigServiceItems"/>" class="standardbutton" id="selectedConfigServiceItems" tabindex="1"/>					              						     					     
		        	 <input type="submit" name="submit" value="返回配置服务参数" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	
				</s:elseif>
      	<s:elseif test="operation=='createconfigservice'">
		      	<form name="return" method="post" action="createconfigservice.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">	
							  <input type="submit" name="submit" value="返回创建配置服务" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	
				</s:elseif>						        						
				<s:else>					
		      	<form name="return" method="post" action="brokertopology.action" id="com.ibm.sr.ui.forms.CollectionPrefsForm">
		      		<input type="hidden" name="qmgrname" value="<s:property value="qmgrname"/>" class="standardbutton" id="qmgrname" tabindex="1"/>
		      		<input type="hidden" name="appname" value="<s:property value="appname"/>" class="standardbutton" id="appname" tabindex="1"/>
		        	<input type="submit" name="submit" value="查看代理状态" class="standardbutton" id="navigationsubmit" tabindex="1"/>	
		        </form>	  
				</s:else>	
      </span>
	 	        	
    </div>  <!-- properties -->
		
			<div class="spaceabove">
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

<script type="text/javascript"> </script>
</body>
</html>
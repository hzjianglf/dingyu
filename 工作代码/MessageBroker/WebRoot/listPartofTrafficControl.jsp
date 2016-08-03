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

<jsp:include page="navtree5.jsp" flush="true" />

</div> <!-- inside -->
</div> <!-- leftcolumn -->
<!-- 左边菜单栏目结束 -->

<div class="contentcolumn" id="contentcolumn">

<script type="text/javascript">
  // Load the required the Dojo modules
  dojo.require("dojox.string.Builder");
  dojo.require("dojo.rpc.JsonService");
  dojo.require ("dojo.parser");
  
</script>
<script type="text/javascript" src='scripts/customAction.js'></script>

<!-- collection.jsp R5 edition -->
<div class="collectionview">
<div class="inside">

<script type="text/javascript">
	// Load the required the Dojo modules
	dojo.require("dojox.string.Builder");
	dojo.require("dojo.rpc.JsonService");

var singleSelect = false;
var oldTargetBsrUri = "";
var maxAllowedSelections = -1;
var maxSelectionMessage = "您已经选择了所允许的项目数最大值。";
		var numItems = 0;
function setRowStyles() {
	// First, retrieve the form from the page
 	var theForm = document.getElementById('com.ibm.sr.ui.forms.CollectionForm');
	   	
  for (i=0;i<theForm.length;i++) {
    if (theForm[i].type == "checkbox" || theForm[i].type == "radio") {
      if (theForm[i].type == "radio" && theForm[i].value == oldTargetBsrUri) {
        theForm[i].checked = true;
      }
	  	if(theForm[i].disabled == false && theForm[i].checked == true) {
        findParentRow(theForm.elements[i],"table-row-selected");
      }
    }
  } // FOR
}

function onItemSelected(chk) {

  // Set the appropriate style for the row
  checkChecks(chk);

  // Check to see if we are in single select mode
  if (!singleSelect && maxAllowedSelections != -1) {
    var form = dojo.byId("com.ibm.sr.ui.forms.CollectionForm");
    if (form) {
      // Determine how many check boxes are selected
      var numSelectedItems = 0;
      for (i = 0; i < form.length; i++) {
        if (form[i].type == "checkbox" && form[i].checked == true) {
          numSelectedItems++;
        }
      } // FOR
      
      // Check to see if we have selected all the items that we are allowed to select
      if (numSelectedItems >= maxAllowedSelections) {
        /*
         * The user has selected all of the items that they are allowed to
         * select.  Display the relevant message and disable any unchecked
         * checkboxes.
         */ 
        displayInformationalMessage(maxSelectionMessage);
        for (i = 0; i < form.length; i++) {
          if (form[i].type == "checkbox" && form[i].checked != true) {
            form[i].disabled = true;
          }
        } // FOR
      } else {
        /*
         * The user is still permitted to select items.  Make sure that all of
         * checkboxes are enabled.
         */ 
        clearMessages();
        for (i = 0; i < form.length; i++) {
          if (form[i].type == "checkbox") {
            form[i].disabled = false;
          }
        } // FOR
      }
    } // IF - form
  } // IF - !singleSelect
}
</script>

<div class="columnlayout widthalign">
<div id="column1" class="two-col">

<a name="title"></a>
<!-- Portlet title 中间内容 -->
<div class="portlet mainwindow" id="collectionview">
  <h2>
    <span class="righttitleicon">
      <a href="" title="查看该页面的更多相关信息（在新窗口中打开）" target="SR_help" tabindex="1"><img src="theme/standard/images/title_help.gif" alt="查看该页面的更多相关信息（在新窗口中打开）"/></a>
    </span>
    <span id="collectionviewheading">分量控制</span>
  </h2>
  
  <div class="portletbody" id="collectionviewbody">
    <div class="mediumpad" id="collectionviewbodypadding">
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
						
							</div>   <!-- messagesTable  -->
  					</div>  <!-- messages-indent  -->
  				</div>  <!-- expandablesection expanded  -->
			</div>  <!-- messagePortletDiv  -->

			<!-- Breadcrumb trail -->
			<h3 class="breadcrumb" id="title-bread-crumb">分量控制</h3>
					<a name="main"></a>
      		<p>
       				可以对单个消息流进行流量控制。
      		</p>
      		<div id="maincontainer">
					<br/>

					<!-- 展现表格主表 main form -->
					<form name="createpartofcontrol" method="post" action="" id="com.ibm.sr.ui.forms.CollectionForm">
										
							<!-- 按钮栏 button bar -->
							<div class="widthalign">
				          <table class="buttontable" cellspacing="0" cellpadding="0" border="0">				
				            <tbody>
					            <tr>
					              <td>													
					              	<input type="button"  name="create" value="创建分量" class="standardbutton" id="functions1" tabindex="1" onclick="createControl()" />												
					              	<input type="button"  name="create" value="修改阀值" class="standardbutton" id="functions1" tabindex="1" onclick="updateControl()" />
					              	<input type="button"  name="delete" value="删除分量" class="standardbutton" id="functions2" tabindex="1" onclick="deleteControl()" />	
					              	<input type="button"  name="start" value="启动分量" class="standardbutton" id="functions3" tabindex="1" onclick="startControl()" />
					              	<input type="button"  name="stop" value="停止分量" class="standardbutton" id="functions4" tabindex="1" onclick="stopControl()" />																              	
					              </td>
					            </tr>
				            </tbody>
				          </table>
							</div> 
							
							<script language="javascript">  		
									var result = '${result}';
									if (result != '') {
										alert(result);	
									}
									
									function createControl(){                   
									   document.createpartofcontrol.action="addpartofcontrol.action";  
									   document.createpartofcontrol.submit();
									}										

									function updateControl(){                   
										   document.createpartofcontrol.action="updatePartOfControl.action";  
										   document.createpartofcontrol.submit();
									}				
									
									function deleteControl(){ 
										 var close = confirm("确认删除分量控制？");
										 if (close) {
									   		document.createpartofcontrol.action="delpartofcontrol.action";  
									   		document.createpartofcontrol.submit();
									   }		
									}	

									function startControl(){                   
										   document.createpartofcontrol.action="startPartOfControl.action";  
										   document.createpartofcontrol.submit();
									}

									function stopControl(){                   
										   document.createpartofcontrol.action="stopPartOfControl.action";  
										   document.createpartofcontrol.submit();
									}									 
							</script>

							<!-- select/deselect -->
							<div class="widthalign">
			
								<table class="selecttable" cellspacing="0" cellpadding="0" border="0">
									<tbody>
			
										<tr>
											<td>
												<a class="blacknounderline" id="selectAllButton" tabindex="1" href="javascript:iscSelectAll('com.ibm.sr.ui.forms.CollectionForm')">
													<img id="selectAllImg" alt="选择所有项" title="选择所有项" src="theme/standard/images/wtable_select_all.gif"/>
												</a>
												<a class="blacknounderline" id="deselectAllButton" tabindex="1" href="javascript:iscDeselectAll('com.ibm.sr.ui.forms.CollectionForm')">
													<img id="deselectAllImg" alt="取消选择所有项" title="取消选择所有项" src="theme/standard/images/wtable_deselect_all.gif"/>
												</a>
											</td>
			
										</tr>
									</tbody>
								</table>

							</div> <!-- widthalign -->
														
							<!-- 展现表格 results table -->
							<div class="widthalign horizontalscroll">
				          <table id="collection-table" class="collectiontable" cellspacing="1" cellpadding="0" border="0" summary="列表">
				            <tr>
			            		<th id="selectCell" width="1%">
							         	选择
							        </th>
				              <th width="39%" title="消息流名称">												
													消息流名称																									
				              </th>
				               <th width="15%" title="单位时间间隔">												
													单位时间间隔												  
				              </th>
				              <th width="15%" title="时间间隔单位">												
												  时间间隔单位	
				              </th>	
				              <th width="15%" title="流量阈值">												
												  流量阈值	
				              </th>		
				              <th width="15%" title="状态">												
												  状态	
				              </th>						              			              	              				              		              
										</tr>
										
		  							<s:iterator value="pageControlList" status="stat">  		  	
											<tr>
					              <td width="1%">
										  	  <label class="collectionLabel" title="选择" for="<s:property value="flowname"/>"/>
					             	  <input type="checkbox" name="selectedItemIds" tabindex="1" value="<s:property value="flowname"/>" onclick="onItemSelected(this)" onkeypress="onItemSelected(this)" class="noborder nomargin" id="<s:property value="flowname"/>" title="选择">
					              </td>
												<td><s:property value="flowname"/></td>
	              				<td><s:property value="partofinterval"/></td>
												<s:if test="partofunit=='1'.toString()">				
	              					<td>秒</td>
	              				</s:if>
	              				<s:elseif test="partofunit=='2'.toString()">
              							<td>分钟</td>
              					</s:elseif>
	              				<s:elseif test="partofunit=='3'.toString()">
              							<td>小时</td>
              					</s:elseif> 	              				
	              				<td><s:property value="partofthreshold"/></td>	
	              				
	              				
	              				<s:if test="status=='1'.toString()">
	              						<td><img src="theme/standard/images/BrokerStarted.png" width="14" height="14"/>&nbsp;已启动</td>
	              				</s:if>
												<s:else>
														<td><img src="theme/standard/images/BrokerStopped.png" width="14" height="14"/>&nbsp;已停止</td>			
      									</s:else>	            		              				
	              				
	              				
	              			</tr>								    
							    	</s:iterator>
					      		<script type="text/javascript">								
											setRowStyles();
										</script>
				                      
									</table>
							</div> <!-- results table -->
	      								
						  <!-- paging form -->
						
							<div class="widthalign">
          				<table class="pagingtable" cellspacing="0" cellpadding="0" border="0" summary="用于显示页面调度功能的表">
            				<tr>
              					<td width="1%">             							
           									<s:if test="planNum==0">
              								&nbsp;&nbsp;&nbsp;&nbsp;
              								</td>
															<td width="1%">
		              								第 0 页，共 0 页
		              						</td>
			              					<td width="1%">   							
	              						</s:if>                						            							
              							<s:elseif test="page==1">
              								&nbsp;&nbsp;&nbsp;&nbsp;
              								</td>
															<td width="1%">
		              								第 <s:property value="page"/> 页，共 <s:property value="totalPage"/> 页
		              						</td>
		              						
		              						<s:if test="totalPage>1">
				              					<td width="1%">
					              					<form name="CollectionForm" method="post" action="listpartofcontrol.action?page=<s:property value="page+1"/>" id="com.ibm.sr.ui.forms.CollectionPagingForm">
					              							<div align="left">
					              									<input type="hidden" value="<s:property value="rowsPerPage"/>" name="rowsPerPage" id="rowsPerPage"/>
					              									<input type="submit" name="nextPage" value=" " title="下一页 &gt;" class="buttonnext" id="paging-next"/>
					              							</div>
					              					</form>
					              			</s:if>		  							
	              						</s:elseif>
	              						<s:elseif test="page==totalPage">	              									
		              							<form name="CollectionForm" method="post" action="listpartofcontrol.action?page=<s:property value="page-1"/>" id="com.ibm.sr.ui.forms.CollectionPagingForm">
																		<div align="right">
																				<input type="hidden" value="<s:property value="rowsPerPage"/>" name="rowsPerPage" id="rowsPerPage"/>
																				<input type="submit" name="previousPage" value=" " title="&lt; 上一页" class="buttonprevious" id="paging-prev"/>
																		</div>
																</form>
			              					</td>
				              				<td width="1%">		
		              								第 <s:property value="page"/> 页，共 <s:property value="totalPage"/> 页
		              						</td>
															<td width="1%">&nbsp;&nbsp;&nbsp;&nbsp;																	              					
      											</s:elseif>	      											
														<s:else>																																
																<form name="CollectionForm" method="post" action="listpartofcontrol.action?page=<s:property value="page-1"/>" id="com.ibm.sr.ui.forms.CollectionPagingForm">
																		<div align="right">
																				<input type="hidden" value="<s:property value="rowsPerPage"/>" name="rowsPerPage" id="rowsPerPage"/>
																				<input type="submit" name="previousPage" value=" " title="&lt; 上一页" class="buttonprevious" id="paging-prev"/>
																		</div>
																</form>
																</td>
																<td width="1%">
			              								第 <s:property value="page"/> 页，共 <s:property value="totalPage"/> 页
			              						</td>
			              						<td width="1%">	
																<form name="CollectionForm" method="post" action="listpartofcontrol.action?page=<s:property value="page+1"/>" id="com.ibm.sr.ui.forms.CollectionPagingForm">
			              								<div align="left">
			              										<input type="hidden" value="<s:property value="rowsPerPage"/>" name="rowsPerPage" id="rowsPerPage"/>
			              										<input type="submit" name="nextPage" value=" " title="下一页 &gt;" class="buttonnext" id="paging-next"/>
			              								</div>
			              						</form>		              					
      											</s:else>	
              					</td>	
              					<td width="97%">&nbsp;&nbsp;&nbsp; 总数：<s:property value="planNum"/> &nbsp;&nbsp;&nbsp;</td>
            				</tr>
          				</table>
          		</div> <!-- widthalign -->
        		</form>
		        	      								
      </div> <!-- main container -->
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
</div> <!-- collectionpage -->
<div id="textMeasureTR" style="display: none; visibility: hidden"><div class="textmeasurenoleft" id="textmeasure"></div></div>
</div> <!-- contentcolumn -->
<div class="rightcolumn" id="rightcolumn">
<div class="inside">

</div> <!-- inside -->
</div> <!-- rightcolumn -->
<div class="clearall"></div>
<!--  add the form needed for custom actions that submit the page -->
<form name="CustomActionForm" method="post" action="" id="com.ibm.sr.ui.forms.CustomActionForm">

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
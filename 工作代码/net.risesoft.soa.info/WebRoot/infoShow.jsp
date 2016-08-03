<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.dao.InformationIndexDao"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.info.model.Information"%>
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%@page import="net.risesoft.soa.info.model.InformationIndex"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%
SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
String id = request.getParameter("id");
InformationIndexDao informationIndexDao = SpringUtil.getBean("informationIndexDao");
InformationIndex informationIndex = informationIndexDao.findOne(id);
String infoID = informationIndex.getInfoID();
Information information = InformationList.get(infoID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源发布</title>
<LINK type="text/css" href="/info/css/style.css" rel="stylesheet" >
<LINK type="text/css" href="/info/css/colorbox.css" rel="stylesheet" >
<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>
<STYLE type="text/css">
.headWrap{-moz-box-shadow:0 1px 2px rgba(23,87,117,0.5);-webkit-box-shadow:0 1px 2px rgba(23,87,117,0.5);box-shadow:0 1px 2px rgba(23,87,117,0.5)}

.headWrap .headShadow{ opacity:.9; border-bottom:#2596c2 1px solid; background-color:#36a4d2;background:-webkit-gradient(linear,left top,left bottom,from(#45addd),to(#279cc8));background:-moz-linear-gradient(top,#45addd,#279cc8);background:transparent\9;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#45addd',endColorstr='#279cc8');background:-ms-linear-gradient(top,#45addd,#279cc8);}

.body {
    background: url("images/wrapbg_v0.0.1.jpg") no-repeat scroll center top #73CFF1;
    color: #333333;
    font: 12px/1.75 Tahoma,Arial,sans-serif,宋体;
    height: 100%;
}
.png{
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="css/images/info.png");
	background:none;
	width:118px;
}
.navradiu { 
	-moz-border-radius:5px;
	-webkit-border-radius:5px; 
	border-radius:5px; 
	-moz-box-shadow:2px 2px 15px #b9b9b9; 
	-webkit-box-shadow:2px 2px 15px #b9b9b9; 
	box-shadow:2px 2px 15px #b9b9b9;
	behavior: url(display/PIE.htc);
}
</STYLE>
</head>
<body class="body">
<DIV class="w_head_outer">
<DIV class="headWrap">
<DIV class="headInside">
<H1></H1>
</DIV>
<DIV class="headShadow">
<div id="headWrap">
<div class="png"></div>
<span style="left: 149px; top:8px; position: absolute;font-size: 14px;color: #FEFEFE;height: 18px;line-height: 18px;">
		欢迎您, <%=sessionUser.getUserName()%>
</span>
</div>
</DIV></DIV></DIV>
<DIV id="topWrap"></DIV>
<DIV id="mainWrapper" class="clear navradiu">
<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 960px; height: 600px; border-radius: 5px 5px 5px; background-color: rgb(255, 255, 255);">
	<tr>
		<td align="left" height="40" style="border-bottom: 1px solid #D1D1D1; padding-left: 20px;"><font class="font1"><%=information.getName()%></font></td>
		<td align="right" style="border-bottom: 1px solid #D1D1D1; padding-right: 20px;"><span id="docDownload_span" style="font-size:10pt;">&nbsp;</span></td>
	</tr>
	<tr>
		<td colspan=2 align="center" valign="top" height="100%"><DIV id="infoMainDiv">
		<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
		<tr><td height="10">&nbsp;</td></tr>
		<tr>
			<td><DIV id="formDIV"></DIV></td>
		</tr>
		</table>
		
		<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 85%;">
			<tr>
				<td height=40>&nbsp;</td>
			</tr>
			<tr>
				<td><DIV id="fileListDIV"></DIV></td>
			</tr>
		</table>
		</DIV></td>
	</tr>
</table>
</DIV>
<script language="javascript">
$("#formDIV").load("/info/infoTemplate.action", {"id":"<%=id%>", "operation":"display"});
$("#fileListDIV").load("/info/infoFileList.action", {"id":"<%=id%>","operation":"list"});
</script>
</body>
</html>


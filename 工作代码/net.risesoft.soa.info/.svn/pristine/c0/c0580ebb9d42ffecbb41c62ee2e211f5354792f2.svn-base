<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.framework.util.UUID"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%@page import="net.risesoft.soa.info.model.Information"%>
<%
SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
String id = request.getParameter("id");
if (id == null){
	id = UUID.generateUUID();
}
String width = request.getParameter("width");
if (width == null){
	width = "960";
}
String infoID = request.getParameter("infoID");
String operation = "create";
String name = "";
Information	information = InformationList.get(infoID);
if (information != null){
	name = information.getName();
}
String myid = request.getParameter("myid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK type="text/css" href="/info/css/style.css" rel="stylesheet" >
<LINK type="text/css" href="/jquery/css/jquery-ui-1.8.23.custom.css" rel="stylesheet" >
<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="/info/ckeditor/ckeditor.js"></script>
<LINK type="text/css" href="/info/css/fileuploader.css" rel="stylesheet" >
<script type="text/javascript" src="/info/js/fileuploader.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$fub = $('#fine-uploader-basic');
	$('#messages').hide();
	new qq.FileUploaderBasic({
		button: $fub[0],
		debug: true,
		multiple: false,
		inputName: 'file',
		action: '/info/infoFile.action',
		params: {"id":"<%=id%>","infoID":"<%=infoID%>"},
		//sizeLimit: 204800,
	 	onSubmit: function(id, fileName) {
	 		$('#messages').show();
			//$messages.append('<div id="file-' + id + '" class="alert" style="margin: 10px 0 0"></div>');
		},
		onUpload: function(id, fileName, loaded, total) {
			$('#messages').addClass('alert-info')
			 	.html('<img src="/info/css/images/loading.gif" alt="Saving. Please hold." class="alert-img"> ' +
			 	'<font size=2>正在保存</font>');
		},
		onProgress: function(id, fileName, loaded, total) {
			$('#messages').addClass('alert-info')
			 	.html('<img src="/info/css/images/loading.gif" alt="Saving. Please hold." class="alert-img"> ' +
			 	'<font size=2>正在保存</font>');
		},
		onComplete: function(id, fileName, responseJSON) {
			if (responseJSON.success) {
				$('#messages').removeClass('alert-info')
				 	.addClass('alert-success')
				 	.html('<font size=2>' + responseJSON.message + '</font>');
				 uploadComplete();
			} else {
				$('#messages').removeClass('alert-info')
				 	.addClass('alert-error')
				 	.html('<font size=2>' + responseJSON.message + '</font>');
			}
			$('#messages').delay(2000).hide(500);
		}
	});
});
 
function uploadComplete(){
	$("#fileListDIV").load("/info/infoFileList.action", {"id":"<%=id%>","operation":"<%=operation%>"});
}
</script>
</head>
<body>
<table align="center" cellpadding="0" cellspacing="0" border="0" width="<%=width%>" style="height: 600px; border-radius: 5px 5px 5px; background-color: rgb(255, 255, 255);">
	<tr>
		<td align="left" height="40" style="border-bottom: 1px solid #D1D1D1; padding-left: 20px;"><font class="font1"><%=name%></font></td>
		<!--<td align="right" style="border-bottom: 1px solid #D1D1D1; padding-right: 20px;">&nbsp;<a id="returnLink" href="javascript:returnHome();" class="link" style="display:none;">返回列表</a></td>-->
	</tr>
	<tr>
		<td colspan=2 align="center" valign="top" height="100%">
<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
	<tr><td height="10">&nbsp;</td></tr>
	<tr>
		<td><DIV id="formDIV"></DIV></td>
	</tr>
</table>
<table align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan=2 height="10">&nbsp;</td>
	</tr>
	<tr>
		<td valign="top"><img src="/info/css/images/save.png" alt="保存" style="cursor: pointer;" onMouseOver="this.src='/info/css/images/save_hover.png'" onMouseOut="this.src='/info/css/images/save.png'" onClick="javascript:infoFormSubmit()"></td>
		<td width="10px">&nbsp;</td>
		<!--<td><img src="../css/images/reset.png" alt="" style="cursor:hand" onMouseOver="this.src='../css/images/reset_hover.png'" onMouseOut="this.src='../css/images/reset.png'"></td> -->
		<td valign="top"><div id="fine-uploader-basic" class="qq-upload-button"></div></td>
	</tr>
</table>
<table align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><div id="messages" class="alert" style="margin: 10px 0 0;"></div></td>
	</tr>
</table>
<table align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td height=20>&nbsp;</td>
	</tr>
	<tr>
		<td><DIV id="fileListDIV"></DIV></td>
	</tr>
	<tr>
		<td height=20>&nbsp;</td>
	</tr>
</table>
</td>
	</tr>
</table>
</body>
<script language="javascript">
$("#formDIV").load("/info/infoTemplate.action", {"infoID":"<%=infoID%>", "id":"<%=id%>", "operation":"<%=operation%>","myid":"<%=myid%>"});
$("#fileListDIV").load("/info/infoFileList.action", {"id":"<%=id%>","operation":"<%=operation%>"});

function returnHome(){

}
</script>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.framework.util.UUID"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@page import="net.risesoft.soa.info.action.infoname"%>
<%
	SessionUser sessionUser = (SessionUser) request.getSession()
			.getAttribute(SessionConst.USER);
	String id = request.getParameter("id");
	String myid = "";
	try {
		myid = request.getParameter("myid");
	} catch (Exception e) {
	}

	 String shortacnum = "";
	try {
		shortacnum = request.getParameter("shortacnum");
	} catch (Exception e) {
	}

	String acid = "";
	try {
		acid = request.getParameter("acid");
	} catch (Exception e) {
	}

	infoname infos = new infoname();
	String infoshouname = "";
	try {
		infoshouname = infos.getinfoname(acid);
	} catch (Exception e) {
	}
	if ("null".equals(infoshouname) || null == infoshouname) {

		infoshouname = "";
	}

	if (id == null) {
		id = UUID.generateUUID();
	}
	String infoID = request.getParameter("infoID");
	String operation = request.getParameter("operation");
	//Information information = InformationList.get(infoID);
%>
<html>
<head>
<LINK type="text/css" href="/info/css/fileuploader.css" rel="stylesheet">
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
	 		$('#messages').empty();
	 		$('#messages').show();
			//$messages.append('<div id="file-' + id + '" class="alert" style="margin: 10px 0 0"></div>');
		},
		onUpload: function(id, fileName, loaded, total) {
			$('#messages').addClass('alert-info')
		 	.html('<img src="/info/css/images/loading.gif" alt="Saving. Please hold." class="alert-img">&nbsp;&nbsp;<span id="percent"><font size=2>正在保存</font></span>');
		},
		onProgress: function(id, fileName, loaded, total) {
			var percent = Math.ceil((loaded / total) * 100);
			$('#percent').html('<font size=2>正在保存  ' + percent + '%</font>');
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


//$("#formDIV :text").each(function () {
 //   var this_id = $(this).attr("id");
  //  alert(this_id);
//}


  //alert("<<<<<<<"+$("#formDIV"));
          //  alert("<<<<<<<"+document.getElementById("formDIV").innerHTML);

      //      var board = document.getElementById("fileListDIV").getElementByName("form1");
       //     var e = document.createElement("input");
        //    e.type = "text";
         //   e.value = "<%=shortacnum%>";
          //  var object = board.appendChild(e);
        
</script>
</head>
<body>
	<table align="center" cellpadding="0" cellspacing="0" border="0"
		style="width: 100%;">
		<tr>
			<td
				style="background: #e8eff4; line-height: 80px; text-align: center; color: #3d516f; font-size: 30px; font-weight: bold; border-bottom: 1px solid #9eadc3; background: #f6f9fb">

				<DIV style="width: 1000px"><%=infoshouname%></DIV>
			</td>

		</tr>
		<tr>

			<td><DIV id="formDIV"></DIV></td>
		</tr>
	</table>
	<table align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><div id="messages" class="alert" style="margin: 10px 0 0;"></div></td>
		</tr>
	</table>
	<table align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td height=10>&nbsp;</td>
		</tr>
		<tr>
			<td><DIV id="fileListDIV"></DIV></td>
		</tr>
		<tr>
			<td height=10>&nbsp;</td>
		</tr>
	</table>
	<table align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td valign="top"><img src="/info/css/images/save.png" alt="保存"
				style="cursor: pointer;"
				onMouseOver="this.src='/info/css/images/save_hover.png'"
				onMouseOut="this.src='/info/css/images/save.png'"
				onClick="javascript:infoFormSubmit()"></td>
			<td width="10px">&nbsp;</td>
			<!--<td><img src="../css/images/reset.png" alt="" style="cursor:hand" onMouseOver="this.src='../css/images/reset_hover.png'" onMouseOut="this.src='../css/images/reset.png'"></td> -->
			<td valign="top"><div id="fine-uploader-basic"
					class="qq-upload-button"></div></td>
		</tr>
	</table>
	<br>
</body>
<script language="javascript">
$("#returnLink").show();
$("#formDIV").load("/info/infoTemplate.action", {"infoID":"<%=infoID%>", "id":"<%=id%>", "shortacnum":"<%=shortacnum%>","operation":"<%=operation%>","myid":"<%=myid%>"});
$("#fileListDIV").load("/info/infoFileList.action", {"id":"<%=id%>","shortacnum":"<%=shortacnum%>","operation":"<%=operation%>
	"
	});
</script>
</html>


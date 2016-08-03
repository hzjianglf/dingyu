<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
String id = request.getParameter("id");
String infoID = request.getParameter("infoID");
%>
<style>
.doc-upload-button {
    display: block;
    /*or inline-block*/
    width: 112px;
    height: 30px;
    text-align: center;
    background: url("/info/css/images/uploadDoc.png") no-repeat;
}
.doc-upload-button:hover {
    background: url("/info/css/images/uploadDoc_hover.png") no-repeat;
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	$docfub = $('#doc-uploader-basic');
	$('#docMessages').hide();
	new qq.FileUploaderBasic({
		button: $docfub[0],
		debug: false,
		multiple: false,
		inputName: 'file',
		action: '/info/infoDocList.action',
		allowedExtensions: ['doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'pdf'],
		params: {"id":"<%=id%>","infoID":"<%=infoID%>","operation":"upload"},
		messages: {
            typeError: "{file} 文件类型不对。可上传文件类型： {extensions}。",
            emptyError: "{file} 文件不能为空。"
        },
		//sizeLimit: 204800,
	 	onSubmit: function(id, fileName) {
	 		$('#docMessages').show();
			//$messages.append('<div id="file-' + id + '" class="alert" style="margin: 10px 0 0"></div>');
		},
		onUpload: function(id, fileName, loaded, total) {
			$('#docMessages').addClass('alert-info')
		 	.html('<img src="/info/css/images/loading.gif" alt="Saving. Please hold." class="alert-img">&nbsp;&nbsp;<span id="percent"><font size=2>正在保存</font></span>');
		},
		onProgress: function(id, fileName, loaded, total) {
			var percent = Math.ceil((loaded / total) * 100);
			$('#percent').html('<font size=2>正在保存  ' + percent + '%</font>');
		},
		onComplete: function(id, fileName, responseJSON) {
			if (responseJSON.success) {
				$('#docMessages').removeClass('alert-info')
				 	.addClass('alert-success')
				 	.html('<font size=2>' + responseJSON.message + '</font>');
				 uploadDocComplete();
			} else {
				$('#docMessages').removeClass('alert-info')
				 	.addClass('alert-error')
				 	.html('<font size=2>' + responseJSON.message + '</font>');
			}
			$('#docMessages').delay(2000).hide(500);
		}
	});
});
 
function uploadDocComplete(){
	$("#docListDIV").load("/info/infoDocList.action", {"id":"<%=id%>","operation":"list"});
}
$("#docListDIV").load("/info/infoDocList.action", {"id":"<%=id%>","operation":"list"});
</script>
<table align=center height=120 width="90%" style="border: 3px solid #f9f9f9;">
	<tr><td align=center>
		<table border=0>
			<tr>
				<td height=40><div id="doc-uploader-basic" class="doc-upload-button"></div></td>
				<td><font size=3 color=#B3B3B3>*支持.doc,.docx,.xls,.xlsx,.ppt,.pptx文件</font></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table align="center" height=80 cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td><div id="docMessages" class="alert" style="margin: 10px 0 0; display:none;"></div></td>
			</tr>
			<tr>
				<td><DIV id="docListDIV"></DIV></td>
			</tr>
		</table>
		</td>
	</tr>
</table>




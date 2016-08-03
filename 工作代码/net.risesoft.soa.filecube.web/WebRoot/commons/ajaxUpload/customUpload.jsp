<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/ajaxUpload/fileuploader.css" />
<script type="text/javascript" src="<%=path %>/commons/ajaxUpload/fileuploader.js"></script>
<script type="text/javascript">
var _count = 0;
$(document).ready(function() {
	$fub = $('#uploadFileDiv');
	$('#messages').hide();
	new qq.FileUploaderBasic({		
		button: $fub[0],
		debug: true,
		multiple: false,
		inputName: 'uploadFile',
		action: _customUpload_url,
		params: _customUpload_params,
	 	onSubmit: function(id, fileName) {
	 		$('#messages').show();			
		},
		onUpload: function(id, fileName, loaded, total) {
			$('#messages').addClass('alert-info')
			 	.html('<img src="<%=path%>/commons/ajaxUpload/images/loading.gif" alt="Saving. Please hold." class="alert-img"> ' +
			 	'<font size=2>正在保存</font>');
		},
		onProgress: function(id, fileName, loaded, total) {
			$('#messages').addClass('alert-info')
			 	.html('<img src="<%=path%>/commons/ajaxUpload/images/loading.gif" alt="Saving. Please hold." class="alert-img"> ' +
			 	'<font size=2>正在保存</font>');
		},
		onComplete: function(id, fileName, rtnData) {
			_count++;
			if (rtnData.success) {
				$('#messages').removeClass('alert-info')
				 	.addClass('alert-success')
				 	.html('<font size=2>上传成功！</font>');				
				var tr = "";
				if(_count % 2 == 0){
					tr += "<tr class = 'changeColor' id='" + rtnData.id + "'>";
				}else{
					tr += "<tr id='" + rtnData.id + "'>";
				}
				tr += "<td>" + _count + "</td>";
				tr += "<td>" + fileName + "</td>";
				tr += "<td>";
				tr += "<img src='images/file/small/files/" + rtnData.extension + ".gif' " +
				"style='vertical-align: middle;margin-right:2px;' " +
				"onerror=this.src='images/file/small/files/unknown.gif'  >"				
				tr += "<td>" + rtnData.size + "</td>";
				//删除部分
				tr += "<td>";
				tr += "<img onclick=" + rtnData.deleteFun + ";";	
				tr += "deleteTrById('" + rtnData.id + "') ";
				tr += "style='cursor: pointer;' src='images/button_operation/delete.png' title='点击删除'>";
				tr += "</td>";
				tr += "</tr>";
				$('#uploadFileList').append(tr);
				$('#messages').hide('slow');
				if(rtnData.type == 'file'){
					_left_refreshTreeAndList(rtnData.folderUid);
				}if(rtnData.type == 'attachment'){
					/*Ext.getCmp('viewFile_AttachmentTab').load({
						url:'attachment_preList.action?fileInfo.fileUid=' + rtnData.fileUid,
						scripts:true
				  	})*/
				}					
			} else {
				$('#messages').removeClass('alert-info')
				 	.addClass('alert-error')
				 	.html('<font size=2>上传失败！</font>');
			}
			
		}
	});	
});
//隐藏删除的数据的tr
function deleteTrById(id){
	_count--;
	$("#" + id).hide('slow');
}
</script>
</head>
<body>
	<div id="uploadFileDiv" class="qq-upload-button">		
		     
	</div>
	<div id="messages" class="alert" style="margin: 10px 0 0;"></div>
	
	<div class="listDataTableStyle" style="padding-top: 5px;">	
		<table cellpadding="0"  cellspacing="0" style="text-align: center;">
			<thead >
				<tr >
					<th width="5%" style="border-top: 1px solid #E5E5E5;"></th>				
					<th width="50%" style="border-top: 1px solid #E5E5E5;">名称</th>
					<th width="15%" style="border-top: 1px solid #E5E5E5;">类型</th>
					<th width="15%" style="border-top: 1px solid #E5E5E5;">大小</th>
					<th width="15%" style="border-top: 1px solid #E5E5E5;">操作</th>
				</tr>			
			</thead>
			<tbody id="uploadFileList">
				
			</tbody>
		</table>
	</div>	
</body>
</html>
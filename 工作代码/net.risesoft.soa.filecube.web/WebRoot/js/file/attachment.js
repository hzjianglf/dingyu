var _file_persional_ItemType;

/**
 * 上传附件
 * @param fileUid -- 文件id
 */
var attachment_operationKey_modify;
function uploadAttachment(fileUid){
	var fileInfo = global_ajax('file_getFileItemType.action',{fileUid:fileUid},false);
	if(_file_persional_ItemType != fileInfo.itemType){
		var allow = permission_getPermissionByFileUid(fileUid,attachment_operationKey_modify);	
		if(!allow){
			alert("对不起，您没有权限为此文件添加附件！");
			return;
		}
	}	
	ajaxUpload_customUpload("attachment_upload.action",{"fileInfo.fileUid":fileUid});
}
/**
 * 删除附件
 * @param attachmentUid -- 附件编号
 * @returns
 */
function deleteAttachment(attachmentUid){
	$.ajax({
		  type: 'POST',
		  url: 'attachment_delete.action',
		  data: {attachmentUid:attachmentUid},
		  dataType: 'JSON',
		  success: function(data){
			  alert(data.success); 
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
}
/**
 * 附件下载
 * @param attachmentUid -- 附件唯一标识
 */
function downloadAttachment(attachmentUid){	
	location.href='attachment_download.action?attachmentUid=' + attachmentUid;	
}
/**
 * 打开附件
 * @param attrUid -- 附件uid
 */
function openAttrDoc(attrUid){
	var url = '/filecube/attachment_preViewFile.action?attachmentUid=' + attrUid;
	window.open(url) ;	
}
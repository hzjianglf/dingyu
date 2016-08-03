
var _customUpload_url ;
var _customUpload_params ;
/**
 * 上传组件的方法
 * @param url            -- 上传接收的url
 * @param params         -- 上传的参数
 */
function ajaxUpload_customUpload(url,params){	
	_customUpload_url = url;	
	params.itemType = global_FOLDER_TREE_TYPE_CUR;
	_customUpload_params = params;
	$('#global_uploadDiv').append("<div id='global_uploadFileDiv'></div>");
	$('#global_uploadFileDiv').load("commons/ajaxUpload/customUpload.jsp");	
	$('#global_uploadFileDiv').dialog({
		autoOpen: false,				
		modal: true,
		width:700,
		height:350,
		title:'上传文件',
		resizable: true,
		beforeClose:function(){		
			$('#global_uploadFileDiv').remove();
			$('#global_uploadDiv').html("");
		}
	});		
	$('#global_uploadFileDiv').dialog('open');
}
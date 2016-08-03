
/**
 * 预处理文件查看
 * @param fileUID -- 文件标识
 */
function preViewDocument(fileUID){
	var allowed = permission_getPermissionByFileUid(fileUID,'');
	if(!allowed){
		alert("对不起您没有查看此文件的权限！");
		return;
	}
	var url = 'file_preViewFile.action?acFileInfo.fileUid=' + fileUID;
	window.open(url) ;
}
/**
	获取文件的总评分，及评价的用户数
*/
function getScoreAndUserCount(){		
	$.ajax({
		  type: 'POST',
		  url: 'commentary_scoreAndUserCount.action',
		  data: {
			  	'fileInfo.fileUid':viewFile_FileUid
			  },
		  dataType: 'JSON',
		  success: function(rtnDate){
			  $('#userCountSpan').html(rtnDate.userCount);
			  $('#totalScore').html(rtnDate.score);
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
} 

$(function(){	
	
	$('.buttonSpan').button();
	getScoreAndUserCount();		
	//收藏的uid
	var _favoritedUid = '';
	//判断当前登录用户是否收藏了此文件
	$.ajax({
		  type: 'POST',
		  url: 'favorite_isFavorited.action',
		  data: {'fileInfo.fileUid':viewFile_FileUid},
		  dataType: 'JSON',
		  success: function(rtnDate){	
			  if(rtnDate.favorited == 'notFavorited'){
				  $('#notFavoritedButtonSpan').css('display','');
			  }else{					  
				  $('#favoritedButtonSpan').css('display','');
				  $('#notFavoritedButtonSpan').css('display','none');
				  _favoritedUid = rtnDate.favorited;
			  }
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
	//收藏按钮点击事件
	$('#notFavoritedButtonSpan').click(function(){
		$.ajax({
			  type: 'POST',
			  url: 'favorite_add.action',
			  data: {'favorites.fileInfo.fileUid':viewFile_FileUid},
			  dataType: 'JSON',
			  success: function(rtnDate){					  
				  _favoritedUid = rtnDate.favoritesUid;
				  $('#notFavoritedButtonSpan').css('display','none');
				  $('#favoritedButtonSpan').css('display','');
				  alert('收藏成功！');
			  },
			  error:function(){
				  alert('服务器出现错误请稍后再试！');
			  }			  
		});
	});
	//取消收藏按钮
	$('#favoritedButtonSpan').click(function(){
		$.ajax({
			  type: 'POST',
			  url: 'favorite_delete.action',
			  data: {'favorites.favoritesUid':_favoritedUid},
			  dataType: 'JSON',
			  success: function(rtnDate){
				  $('#notFavoritedButtonSpan').css('display','');
				  $('#favoritedButtonSpan').css('display','none');
				  alert('取消收藏成功！');
			  },
			  error:function(){
				  alert('服务器出现错误请稍后再试！');
			  }			  
		});
	});
	//更新打开次数的索引
	updateOpenTimes(viewFile_FileUid);
	//下载按钮点击事件
	$('#ViewFileDownload').click(function(){
		var fileInfo = global_ajax('file_getFileItemType.action',{fileUid:viewFile_FileUid},false);
		if(_file_persional_ItemType != fileInfo.itemType){
			var allow = permission_getPermissionByFileUid(viewFile_FileUid,viewFile_operationKey_download);	
			if(!allow){
				alert("对不起您没有下载此文件的权限！");
				return;
			}
		}
		//更新全文检索中的下载次数
		updateDownLoadTimes(viewFile_FileUid);
		location.href='file_download.action?acFileInfo.fileUid=' + viewFile_FileUid;
	});
	//共享点击按钮
	$('#ViewFileShare').click(function(){
		var fileInfo = global_ajax('file_getFileItemType.action',{fileUid:viewFile_FileUid},false);
		if(_file_persional_ItemType != fileInfo.itemType){
			var allow = permission_getPermissionByFileUid(viewFile_FileUid,viewFile_operationKey_share);	
			if(!allow){
				alert("对不起您没有下载此文件的权限！");
				return;
			}
		}
		$('#viewerPlaceHolder').toggle();
		$('#shareDialogParent').html('<div id="shareDialog" style="padding: 0px 0px;position: relative;"></div>');
		$('#shareDialog').load('share_preShare.action?fileShare.fileInfo.fileUid=' + viewFile_FileUid);
		$( "#shareDialog" ).dialog({
			resizable: false,
			
			width:300,
			height:455,
			modal: true,
			buttons: {
				"确定": function() {
					orgTree_doShare();
					$( this ).dialog( "close" );
					$('#shareDialog').remove();
					$('#shareDialogParent').html('');
				},
				'取消': function() {
					$( this ).dialog( "close" );
					$('#shareDialog').remove();
					$('#shareDialogParent').html('');
				}
			},
			beforeClose:function(){
				$('#shareDialog').remove();
				$('#shareDialogParent').html('');
				$('#viewerPlaceHolder').toggle();
			}
		});
		
	});
	//获取关联打开文件记录
	$.ajax({
		  type: 'POST',
		  url: 'openHistory_relationOpenHistory.action',
		  data: {'fileInfo.fileUid':viewFile_FileUid},
		  dataType: 'JSON',
		  async: true, 
		  success: function(rtnData){				  
			var str = '';
			for(var i = 0;i < rtnData.length;i++){
				str += '<span title="' + rtnData[i].fileName + '" onclick=preViewDocument("' + rtnData[i].fileUid + '") >';	
				str += '<img src="images/file/small/files/' + rtnData[i].fileExtension + '.gif" style="vertical-align: middle;margin-right:4px;">'
	      		str += '<font color="blue" style="cursor: pointer;" >' + rtnData[i].fileShortName + '</font>';	
	      		str += '</span>';	
	      		str += '<p style="margin-bottom: 10px;margin-top: 10px;"></p>';
			}
			$('#relationOpenHistoryDiv').html(str);
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
	//获取关联收藏文件记录
	$.ajax({
		  type: 'POST',
		  url: 'favorite_relationFavorite.action',
		  data: {'fileInfo.fileUid':viewFile_FileUid},
		  dataType: 'JSON',
		  success: function(rtnData){	
			var str = '';
			for(var i = 0;i < rtnData.length;i++){
				str += '<span title="' + rtnData[i].fileName + '" onclick=preViewDocument("' + rtnData[i].fileUid + '") >';		
				str += '<img src="images/file/small/files/' + rtnData[i].fileExtension + '.gif" style="vertical-align: middle;margin-right:4px;">'
	      		str += '<font color="blue" style="cursor: pointer;" >' + rtnData[i].fileShortName + '</font>';	
	      		str += '</span>';	
	      		str += '<p style="margin-bottom: 10px;margin-top: 10px;"></p>';
			}
			$('#relationFavoriteDiv').html(str);
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
	
});

var winWidth = 0;  
var winHeight = 0; 
//获取浏览器高、宽
function findDimensions() {  
	//获取窗口宽度  
	if (window.innerWidth)  
		winWidth = window.innerWidth;  
	else if ((document.body) && (document.body.clientWidth))  
		winWidth = document.body.clientWidth;  
	//获取窗口高度  
	if (window.innerHeight)  
		winHeight = window.innerHeight;  
	else if ((document.body) && (document.body.clientHeight))  
		winHeight = document.body.clientHeight;  
	//通过深入Document内部对body进行检测，获取窗口大小  
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){  
		winHeight = document.documentElement.clientHeight;  
		winWidth = document.documentElement.clientWidth;  
	}
	//alert("高度:" + winHeight + " 宽度：" + winWidth);
} 
$(document).ready(function(){
	findDimensions();		
	//$('#viewFileTable').attr("style","height:" + (winHeight-50) + "px")
	$('#viewerPlaceHolder').attr('style',"height:" + (winHeight-60) + "px");	
	$('#relevanceFileIframe').attr('height',(winHeight - 50));
	$('#commentaryIframe').attr('height',(winHeight - 50) + "px");		
	$('#info').tabs();	
	$.ajax({
		  type: 'POST',
		  url: 'file_convertFile.action',
		  dataType: 'json',
		  data:{fileUid:viewFile_FileUid},
		  success: function(data){	
			  var swfFilePath = 'file_viewFile.action?fileUid=' + viewFile_FileUid;
			  $('#viewerPlaceHolder').FlexPaperViewer( { config : {
			     FlexPaperViewerSrc:'commons/flexpaper/FlexPaperViewer.swf',
				 SwfFile : swfFilePath,
				 Scale : 0.6, 
				 ZoomTransition : 'easeOut',
				 ZoomTime : 0.5,
				 ZoomInterval : 0.2,
				 FitPageOnLoad : true,
				 FitWidthOnLoad : true,
				 FullScreenAsMaxWindow : false,
				 ProgressiveLoading : true,
				 MinZoomSize : 0.2,
				 MaxZoomSize : 3,
				 SearchMatchAll : false,
				 InitViewMode : 'Portrait',
				 PrintPaperAsBitmap : false,
				 
				 ViewModeToolsVisible : true,
				 ZoomToolsVisible : true,
				 NavToolsVisible : true,
				 CursorToolsVisible : true,
				 SearchToolsVisible : true,
				
				 localeChain: 'zh_CN'
			}});
		  },
		  error:function(){			 
			  alert("服务器异常，请稍后再试！");
		  }			  
	});
	$('#info').css('display','');	
	$('#viewFileRight').attr('style'," font-family:Verdana;font-size:9pt;border: 1px solid #F7F5F5;height:" + (winHeight-60) + "px");	
	$('#relation').accordion();	
});
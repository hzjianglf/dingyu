var imgViewFileUid = "";
/**
 * 获取所有被选中的文件或文件夹
 */
function getAllSelectData(){	
	file_allSelectDatas = [];
	$('.image_container').each(function(){
		var fileSelected = $(this).attr('fileSelect');
		var fileType = $(this).attr('fileType');
		//记录选择的文件、或文件夹的uid
		if(fileSelected == 'yes'){
			var tmp = file_allSelectDatas.length++;
			file_allSelectDatas[tmp] = {};
			file_allSelectDatas[tmp].uid = $(this).attr('fileUid');
			file_allSelectDatas[tmp].fileType = $(this).attr('fileType');
			file_allSelectDatas[tmp].extendFolderUids = $(this).attr('extendFolderUids');
		}	
	});
}
/**
 * 修改选中图片的背景颜色
 * @param id 		-- 文件或文件夹背景所处的dom id
 * @param selected  是否改变背景色
 */
function changeImgBgColor(id,selected){
	var bgDom = $('#' + id);
	if(true == selected){
		bgDom.css('background-color','#DCDCDC');	
		bgDom.css('border','#6495ED 1px solid');		
		bgDom.attr('fileSelect','yes');
	}else{
		bgDom.css('background-color','#f7f7f7');
		bgDom.css('border','#ccc 1px solid');
		bgDom.attr('fileSelect','no');
	}	
}

$(function() {	
	//选中文件复选框
	$('.imgCheckClick').click(function(){
		var fileUid = $(this).attr('fileUid');
		//改变背景色
		var iptCheck = this.checked;
		changeImgBgColor(fileUid,iptCheck);		
		//获取所有被选中的文件或文件夹
		getAllSelectData();
		//启用按钮的禁用启用状态
		file_controlButtonState();		
	});
	$('.image_container').hover(
		function () {			
			var selected = $(this).attr('fileSelect');			
			$(this).css('background-color','#DCDCDC');	
			$(this).css('border','#6495ED 1px solid');	
		},
		function () {
			var selected = $(this).attr('fileSelect');
			if(selected == 'no'){				
				$(this).css('background-color','#f7f7f7');
				$(this).css('border','#ccc 1px solid');
			}			
		}
	);
	/*
	$('div[id^="nav"]').hover(
		function () {
			$(this).removeClass('transparent');
		},
		function () {
			$(this).addClass('transparent');
		}
	);
	$('.star,.left,.right').hover(
		function () {
			$(this).addClass('transparent1');
		},
		function () {			
			$(this).removeClass('transparent1');
		}
	);
	*/
});


/**
 * 关联列表页面的 文件uid
 */
var _fileList_fileUid = "";
/**
 * 关联文件页面的文件名称
 */
var _fileList_fileName = "";

$(document).ready(function(){	
	var listHeight = $(top.document.getElementById('relevanceFileIframe')).attr('height');
	$('#fileListIframe').attr('height', parseInt(listHeight) - 10);
	
	var clickId = 'relevanceByName';
	var dom = $('#' + clickId);
	dom.css('background-color','#0099FF');	
	dom.attr('click','click');
	optionClick(clickId);
});

$(function(){
	$('.relevanceOption').click(function(){			
		$('.relevanceOption').each(function(){
			$(this).attr('click','');
			$(this).css('background-color','');
		});	
		$(this).css('background-color','#0099FF');	
		$(this).attr('click','click');		
		var clickId = this.id;
		optionClick(clickId);
	});
	//鼠标移上改变，选择搜索选项的背景色
	$('.relevanceOption').hover(
		function(){		
			$(this).css('background-color','#0099FF');			
		},
		function(){			
			if($(this).attr('click') != 'click'){
				$(this).css('background-color','');
			}
		}
	);	
});
//点击选择项调用的事件
function optionClick(clickId){
	//根据名称获取关联文件	
	if(clickId == 'relevanceByName'){
		relevanceByName();
	}
	//根据内容获取关联文件	
	if(clickId == 'relevanceByContent'){
		relevanceByContent();
	}	
}	
/**
 * 根据文件名称获取关联文件
 */
function relevanceByName(){		
	$.ajax({
		  type: 'POST',
		  url: '/fulltext/analyzer_terms.action',
		  dataType: 'JSON',
		  data:{str:_fileList_fileName},
		  async: false,      //ajax同步
		  success: function(data){
			  var postKeyword = "";
			  for(var i = 0;i < data.length;i++){
				  if(i < data.length - 1){
					  postKeyword += data[i] + " OR ";
				  }	else{
					  postKeyword += data[i];
				  }				  
			  }			  
			  postKeyword = "(" + encodeURIComponent(postKeyword) + ")";
			  //将内容加载在 显示区域
			  var url = '/fulltext/search_preCommonsList.action?keyWords=' + postKeyword;
			  $('#fileListIframe').attr('src',url);
		  },
		  error:function(){			 
			  
		  }			  
	});			
}

/**
 * 根据文件内容 获取关联文件
 */
function relevanceByContent(){
	$.ajax({
		  type: 'POST',
		  url: '/fulltext/analyzer_contentTop10Terms.action',
		  dataType: 'JSON',
		  data:{docId:_fileList_fileUid},
		  async: false,      //ajax同步
		  success: function(data){
			  var postKeyword = "";
			  for(var i = 0;i < data.length;i++){
				  if(i < data.length - 1){
					  postKeyword += data[i] + " OR ";
				  }	else{
					  postKeyword += data[i];
				  }				  
			  }	
			  postKeyword = "(" + encodeURIComponent(postKeyword) + ")";
			  //将内容加载在 显示区域
			  var url = '/fulltext/search_preCommonsList.action?keyWords=' + postKeyword;	
			  $('#fileListIframe').attr('src',url);
		  },
		  error:function(){			 
			  
		  }			  
	});	
}
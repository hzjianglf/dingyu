/**
 * 文件列表视图页面的js文件
 */
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
findDimensions();  
//调用函数，获取数值  
window.onresize=findDimensions; 

//页面数据行数
var pageSize = parseInt((winHeight-220) / 30);
var _cur_page = 0;
$(function(){
	//清空所有选择的数据
	getAllSelectData();
	file_controlButtonState();
	
	getListViewData(0,pageSize);
	
	//全选checkbox单击事件
	$('#selectAllData').click(function(){	
		//清空所有选择的数据
		file_allSelectDatas = [];
		var selectAllDataCk = $('#selectAllData').attr('checked');
		$(':input[class="listViewDataRow"]').each(function(){
			this.checked = selectAllDataCk;
		});			
		
		getAllSelectData();
		file_controlButtonState();
	});	
	
});
/**
 * 获取所有选择的数据
 */
function getAllSelectData(){
	file_allSelectDatas = [];
	$(':input[class="listViewDataRow"]').each(function(){
		if(this.checked == true){
			var tmp = file_allSelectDatas.length ++;
			file_allSelectDatas[tmp] = {};
			file_allSelectDatas[tmp].uid = this.value;
			file_allSelectDatas[tmp].extendFolderUids = $(this).attr('extendFolderUids');
			file_allSelectDatas[tmp].fileType = $(this).attr('fileType');				
		}
	});
}

/**
 * AJAX获取数据
 * @param start    - 行的起始位置
 * @param pageSize - 页面数据的行数
 */
function getListViewData(start,pageSize){
	$('#listViewTbody').html('<tr><td colspan="7"><div class="loading-indicator">加载中...</div></td></tr>');
	
	$.ajax({
		  type: 'POST',
		  url: 'file_listView.action',
		  data: {
			  folderUid:file_jsp_folderUid,
			  start:start, 
			  pageSize:pageSize,
			  searchStr:_searchStr,
			  sortStr:_sortStr
		  },
		  dataType: 'JSON',
		  async: false,
		  success: function(data){
			  //没有数据
			  if(data.total == 0){
				  $('#listViewTbody').html('<tr><td colspan="8" align = "center"><font color=red>暂无数据！</font></td></tr>');
			  }			  
			  writeDataToListView(data.root);
			  //分页
			  $("#Pagination").pagination(data.total, {
				  num_edge_entries: 2,
				  num_display_entries: 5,
				  callback: pageselectCallback,
				  current_page:_cur_page,
				  items_per_page:pageSize
			  });
			  //文件统计
			  $('#listViewCount').html("目录共 " + data.total + " 个项，当前共 " + data.totalSize);
		  },
		  error:function(){
			  //alert('查找失败！');
		  }			  
	});		
}
/**
 * 将数据写入到页面中
 * @param data  -- 返回的数据
 */
function writeDataToListView(data){	
	var rows = "";	
	for(var i = 0 ;i < data.length;i++){
		var file = data[i];
		var rowNM = _cur_page * pageSize + i + 1;		
		rows += "<tr class='listViewTr' style=background-color:" + ((i%2 == 1) ? '#F5F5F5' : '') + " index = " + i%2 + ">";
		rows += "<td style='text-align: center;'>" + rowNM + "</td>";
		rows += "<td style='text-align: center;'><input class='listViewDataRow' " +
				"value='" + file.id + "' type='checkbox' fileType=" + file.type + " extendFolderUids='" + file.extendFolderUids + "' /></td>";
		rows += "<td title='" + file.name + "'>" ;
		rows +=	"<img src='images/file/small/files/" + file.extension + ".gif' " +
				"style='vertical-align: middle;margin-right:2px;' " +
				"onerror=this.src='images/file/small/files/unknown.gif'  >";
		if(data[i].type == 'image'){
			rows +=	"<a href='file_viewImgFile.action?acFileInfo.fileUid=" + file.id + "' class='imagesView' title='" + file.name + "' >" 
			+ file.shortName + "</a></td>";
		}else{
			rows +=	"<a href=javascript:preView" + data[i].type + "('" + file.id +  "') >" 
				+ file.shortName + "</a></td>";		
		}	
		if(data[i].type == global_RESOURCE_TYPE_FOLDER_EN){
			rows += "<td>	&nbsp;</td>";
		}else{
			rows += "<td>" + file.size + "</td>";
		}
			
		rows += "<td>" + file_fileType[(file.type).toLowerCase()] + "</td>";	
		rows += "<td>" + file.createdate + "</td>";	
		rows += "<td>	&nbsp;" + file.modifieddate + "</td>";	
		rows += "<td>";		
		rows += "&nbsp;&nbsp;<img style='cursor: pointer;vertical-align: middle;margin-right:2px;' class='operationImg' src='images/icons/share_16.png'" +
				"title='共享' onclick=share('" + file.id + "','" + data[i].type + "') />";
		//不是个人文件夹  才出现收藏
		if(global_FOLDER_TREE_TYPE_CUR != global_FOLDER_TREE_TYPE_PERSONNEL){
			rows += "&nbsp;&nbsp;|&nbsp;&nbsp;<img style='cursor: pointer;vertical-align: middle;margin-right:2px;' class='operationImg' src='images/button_operation/star-" + data[i].favorite + ".png'" +
			"title='收藏' onclick=favorite('" + file.id + "','" + data[i].type + "',this); favoriteUid='" + data[i].favoriteUid + "' imgSrc='" + data[i].favorite + "'/>";	
	
		}
		rows += "</td>";	
		rows += "</tr>";
	}
	if(rows != "")
		$('#listViewTbody').html(rows);
	//置空
	rows = "";
	
	$('.listViewTr').hover(
		function(){			
			$(this).css('background-color','#C0C0C0');
		},
		function(){
			var index = $(this).attr('index');
			if(index == '1'){
				$(this).css('background-color','#F5F5F5');
			}else{
				$(this).css('background-color','');
			}			
		}
	);
	//行选择
	$('.listViewDataRow').click(function(){
		//获取所有选择的数据
		getAllSelectData();
		file_controlButtonState();
	})
} 

//分页回调函数
function pageselectCallback(start, jq){	
	var cur_start = start * pageSize;
	_cur_page = start;
	getListViewData(cur_start,pageSize);
}
/**
 * 按名称搜索文件或文件夹
 */
function searchByName(){   
	Ext.MessageBox.prompt("筛选","请输入文件或文件夹的名称：",function(button,txt){   
		if(button == 'cancel') return;		
		_searchStr = txt;
		getListViewData(0,pageSize);
	});
}
$(function(){
	//创建时间的排序
	$("#sortImg_createDate").click(function(){
		var sortType = $(this).attr('sortType');		
		if(sortType == 'desc'){			
			_sortStr = 'asc';
			$(this).attr('sortType','asc');
			this.src = 'images/icons/sort_asc.png';
		}else{
			_sortStr = 'desc';
			$(this).attr('sortType','desc');
			this.src = 'images/icons/sort_desc.png';
		}
		getListViewData(0,pageSize);
	});	
});
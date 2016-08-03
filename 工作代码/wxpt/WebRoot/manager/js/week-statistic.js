$(function() {
	$("#toolbar").css("visibility", "visible");
	$("#add").css("visibility", "visible");
	$('#orderAdd').hide();
	$('#order').datagrid({
		onDblClickRow : see,
		title : '网站受访统计',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		height : "auto",
		nowrap : false,
		striped : true,
		onRowContextMenu:onRowContextMenu,
		collapsible : true,
		singleSelect : true,
		url : '../site/baidu!getAll?type=5',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '受访页面',
			field : 'page',
			width : 290,
			rowspan : 2,
			align : 'center'
		}, {
			title : '域名',
			field : 'domain',
			width : 180,
			rowspan : 2,
			align : 'center'
		}, {
			title : '浏览量',
			field : 'pageviews',
			width : 70,
			rowspan : 2,
			align : 'center'
		}, {
			title : '访客数',
			field : 'visitors',
			width : 70,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ip数',
			field : 'ips',
			width : 60,
			rowspan : 2,
			align : 'center'
		}, {
			title : '入口页次数',
			field : 'entrances',
			width : 70,
			rowspan : 2,
			align : 'center'
		}, {
			title : '贡献下游流量',
			field : 'outwards',
			width : 90,
			rowspan : 2,
			align : 'center'
		}, {
			title : '退出页次数',
			field : 'exits',
			width : 70,
			rowspan : 2,
			align : 'center'
		}, {
			title : '平均停留时间',
			field : 'stayTime',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '退出率',
			field : 'exitRate',
			width : 70,
			rowspan : 2,
			align : 'center'
		},
		// {title:'用户Id',field:'userId',width : 80,rowspan:2,align:'center'},
		{
			title : '统计日期',
			field : 'recordTime',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, ] ],
		rownumbers : true,
		pagination : true,
		rownumbers : true,
		toolbar : "#toolbar" // 添加这句
	});
	displayMsg();
});

document.oncontextmenu=function(){
	var selection="";//定义鼠标拖选值
	if(document.selection){//***** IE
	selection=document.selection.createRange().text;
	}else{//***** 其他浏览器
	selection=document.getSelection();
	}
	//if来判断拖选值、被单击的区域是不是表单域值
	if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
	}
function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}
var s_caocuo = "false";
$("#order").datagrid({
	toolbar : "#toolbar" // 这里是工具条div的id

});

function fixWidthTable(percent) {
	return $(window).width() * percent;
}

function see() {
	$("#orderAdd").css("visibility", "visible");
	var select = $('#order').datagrid('getSelected');
	if (select) {
		$('#edit').window('open');
		$('#orderAdd').show();
		$('#orderAdd').appendTo('#ee');
		$('#page').html(select.page);
		$('#domain').html(select.domain);
		$('#pageviews').html(select.pageviews);
		$('#visitors').html(select.visitors);
		$('#ips').html(select.ips);
		$('#entrances').html(select.entrances);
		$('#outwards').html(select.outwards);
		$('#exits').html(select.exits);
		$('#stayTime').html(select.stayTime);
		$('#exitRate').html(select.exitRate);
		$('#recordTime').html(select.recordTime);
		id = select.id;
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function displayMsg() {
	$('#order').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function close1() {
	$('#add').window('close');
}
function close2() {
	$('#edit').window('close');
}
var id;
function getSelect() {
	var select = $('#order').datagrid('getSelected');
	if (select) {
		$('#edit').window('open');
		$('#orderAdd').show();
		$('#orderAdd').appendTo('#ee');
		$('#name').val(select.name);
		$('#age').val(select.age);
		$('#sex').val(select.sex);
		$('#birthday').val(select.birthday);
		$('#className').val(select.className);
		id = select.id;
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function query() {
	var query = $("#query").combobox("getValue");
	$('#order').datagrid({
		url : '../../baidu!getAll?query=' + query,

		queryParams : {
		// inforTitle : 1,
		// inforState : $("#inforState").combobox("getValue"),
		}
	});
	displayMsg();
}
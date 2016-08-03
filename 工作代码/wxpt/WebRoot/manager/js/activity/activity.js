$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt').datagrid({
		onDblClickRow : updateactivity,
		title : '活动专题管理',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		pageNumber : 1,
		nowrap : true,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/activity!getActivity',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [[{
			title : '活动专题名称',
			field : 'activityTitle',
			width : 130,
			rowspan : 2,
			align : 'center'
		}, {
			title : '活动时间',
			field : 'activityTime',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '活动地点',
			field : 'activityAdress',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '活动内容',
			field : 'activityContent',
			width : 500,
			rowspan : 2,
			align : 'center'
		}, {
			title : '添加时间',
			field : 'activityAddTime',
			width : 200,
			rowspan : 2,
			align : 'center'
		}]],
		rownumbers : true,
		pagination : true,
		toolbar : "#toolbar"
	});
	displayMsg();
});

function fixWidthTable(percent) {
	return $(window).width() * percent;
}
function displayMsg() {
	$('#tt').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function addActivity(){
	$.ajax({
		type : "POST",
		url : "../../site/manage/activity!saveActivity",
		data : {
			'title' : $("#title").val(),
			'activityTime' : $("#time").val(),
			'activityAdress' : $("#adress").val(),
			'activityContent' : $("#content").val()
		},
		dataType : "json",
		success : function(json) {
			if (json) {
				$.messager.alert("提示", "保存成功！", "info");
				$('#tt').datagrid('reload');
				close1();
			} else {
				$.messager.alert("提示", "保存失败，请稍后重试！", "info");
				$('#tt').datagrid('reload');
				close1();
			}
		}
	});
}
function updateactivity() {
	$("#update").css("visibility", "visible");
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#update').window('open');
		$('#activityForm').form('clear');
		$('#activityForm').show();
		$('#activityForm').appendTo('#infor1');
		 document.getElementById("idcontent").innerHTML = 
			   "\<textarea id='content' name='content' style='width:670px; height:200px;' class='ww'\>\</textarea\>";
		$("#time").val(select.activityTime);
		$("#title").val(select.activityTitle);
		$("#adress").val(select.activityAdress);
		$("#content").val(select.activityContent);
		content();
	}else{
		$.messager.alert("提示", "请先选中一行数据！", "info");
	}
}
function openactivity() {
	$('#add').window('open');
	$('#activityForm').form('clear');
	$('#activityForm').show();
	$('#activityForm').appendTo('#infor');
	 document.getElementById("idcontent").innerHTML = 
		   "\<textarea id='content' name='content' style='width:670px; height:200px;' class='ww'\>\</textarea\>";
	content();
}
function close1() {
	$('#add').window('close');
}
function close2() {
	$('#update').window('close');
}
function addinfor(){
	
}
function updateAct() {
	var select = $('#tt').datagrid('getSelected');
	var activityId = select.activityId;
	$.ajax({
		type : "POST",
		url : "../../site/manage/activity!updateActivity",
		data : {
			'activityId' : activityId,
			'title' : $("#title").val(),
			'activityTime' : $("#time").val(),
			'activityAdress' : $("#adress").val(),
			'activityContent' : $("#content").val()
		},
		dataType : "json",
		success : function(json) {
			if (json) {
				$.messager.alert("提示", "修改成功！", "info");
				$('#tt').datagrid('reload');
				close2();
			}else{
				$.messager.alert("提示", "修改失败，请稍后重试！", "info");
				$('#tt').datagrid('reload');
				close2();
			}
		}
	});
}
function delActivity() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('warning', '确认删除吗?', function(r) {
			var activityId = selected.activityId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/activity!deleteActivity",
					data : "activityId="+activityId,
					dataType : "json",
					success : function(json) {
						if (json) {
							$.messager.alert("提示", "删除成功！", "info");
							$('#tt').datagrid('reload');
						} else {
							$.messager.alert("提示", "删除失败，请稍后重试！", "info");
							$('#tt').datagrid('reload');
						}
					}
				});
				$('#tt').datagrid('reload');
			}
		});
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function query() {
	$('#tt').datagrid({
		url :'../../site/manage/activity!getActivity',
		queryParams : {
			title : $("#seltitle").val(),
			activityAdress : $("#seladress").val(),
			activityContent : $("#selcontent").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
		}
	});
	displayMsg();
}
function seeReserve(){
	  $("#ad").window('open');
}
$(function() {
	$('#ff1').hide();
	$('#tt1').datagrid({
//		onDblClickRow : see1,
		title : '',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/activity!getReserve',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '姓名',
			field : 'reserveName',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '微信账号',
			field : 'reserveWeixin',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '联系电话',
			field : 'reservePhone',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '活动标题',
			field : 'activityTitle',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '活动时间',
			field : 'activityTime',
			width : 150,
			rowspan : 2,
			align : 'center'
		}]],
		pagination : true,
		rownumbers : true,
		toolbar:"#toolbar1"

	});
	displayMsg1();
});
function queryReserve() {
	$('#tt1').datagrid({
		url :'../../site/manage/activity!getReserve',
		queryParams : {
			reserveName : $("#ReserveName").val(),
			reserveWeixin : $("#ReserveWeixin").val(),
		}
	});
	displayMsg1();
}
function displayMsg1() {
	$('#tt1').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function content(){
	new tqEditor(
			'content',
			{
				toolbar : 'full'
			});
}
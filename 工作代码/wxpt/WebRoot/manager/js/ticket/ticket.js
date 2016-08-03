$(function() {
	$("#toolbar").css("visibility", "visible");
	ticketStar();
	ticketEnd();
	trainNumb();
	$('#tt').datagrid({
		// onDblClickRow : updatetype,
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
		url : '../../site/manage/ticket!getTicket',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '姓名',
			field : 'ticketName',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '大学账号',
			field : 'userName',
			width : 100,
			rowspan : 2,
			align : 'center'
		},{
			title : '乘车时间',
			field : 'ticketTime',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '始发地',
			field : 'ticketStar',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '终点站',
			field : 'ticketEnd',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '车次/航班',
			field : 'trainNumber',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '证件类型',
			field : 'documentType',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '证件号码',
			field : 'documentNumber',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '联系电话',
			field : 'phone',
			width : 100,
			rowspan : 2,
			align : 'center'
		} ] ],
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
// function addTicketTime(){
// $.ajax({
// type : "POST",
// url : "../../site/manage/ticket!saveTicketTime",
// data : {
// 'title' : $("#title").val(),
// 'activityTime' : $("#time").val(),
// 'activityAdress' : $("#adress").val(),
// 'activityContent' : $("#content").val()
// },
// dataType : "json",
// success : function(json) {
// if (json) {
// $.messager.alert("提示", "保存成功！", "info");
// $('#tt').datagrid('reload');
// close1();
// } else {
// $.messager.alert("提示", "保存失败，请稍后重试！", "info");
// $('#tt').datagrid('reload');
// close1();
// }
// }
// });
// }
function updateTicket() {
	$("#tt1").datagrid("selectRow", 0);
	$("#update").css("visibility", "visible");
	var select = $('#tt1').datagrid('getSelected');
	$('#update').window('open');
	$('#activityForm').form('clear');
	$('#activityForm').show();
	$('#activityForm').appendTo('#infor1');
	// $("#time").val(select.activityTime);
	// $("#title").val(select.activityTitle);
	// $("#adress").val(select.activityAdress);
	// $("#content").val(select.activityContent);
}

function close1() {
	$('#add').window('close');
}
function close2() {
	$('#update').window('close');
}
function updateTicketTime() {
	var select = $('#tt1').datagrid('getSelected');
	if (document.getElementById("ticketTimeStar").value == ""
			|| document.getElementById("ticketTimeEnd").value == "") {
		$.messager.alert("提示", "请选择正确时间！", "info");
	} else {
		if (select) {
			var ticketTimeId = select.ticketTimeId;
			$.ajax({
				type : "POST",
				url : "../../site/manage/ticket!updateTicketTime",
				data : {
					'ticketTimeId' : ticketTimeId,
					'ticketTimeStar' : $("#ticketTimeStar").val(),
					'ticketTimeEnd' : $("#ticketTimeEnd").val()
				},
				dataType : "json",
				success : function(json) {
					if (json) {
						$.messager.alert("提示", "修改成功！", "info");
						$('#tt1').datagrid('reload');
						close2();
					} else {
						$.messager.alert("提示", "修改失败，请稍后重试！", "info");
						$('#tt1').datagrid('reload');
						close2();
					}
				}
			});

		} else {
			$.ajax({
				type : "POST",
				url : "../../site/manage/ticket!saveTicketTime",
				data : {
					'ticketTimeStar' : $("#ticketTimeStar").val(),
					'ticketTimeEnd' : $("#ticketTimeEnd").val()
				},
				dataType : "json",
				success : function(json) {
					if (json) {
						$.messager.alert("提示", "保存成功！", "info");
						$('#tt1').datagrid('reload');
						close2();
					} else {
						$.messager.alert("提示", "保存失败，请稍后重试！", "info");
						$('#tt1').datagrid('reload');
						close2();
					}
				}
			});
		}
	}
}
function delTicketTd() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('warning', '确认删除吗?', function(r) {
			var ticketId = selected.ticketId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/ticket!deleteTicket",
					data : "ticketId=" + ticketId,
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
		$.messager.alert('提示', '请选择一行数据', 'warning');
	}
}
function query() {
	var timestar = document.getElementById("startTime").value;
	var timeend = document.getElementById("endTime").value;
	$('#tt').datagrid({
		url : '../../site/manage/ticket!getTicket',
		queryParams : {
			ticketName : $("#selname").val(),
			ticketStar : $("#ticketStar").combobox("getValue"),
			ticketEnd : $("#ticketEnd").combobox("getValue"),
			trainNumber : $("#trainNum").combobox("getValue"),
			startTime : timestar,
			endTime : timeend,
		}
	});
	displayMsg();
}
function seeTicketTime() {
	$("#ad").window('open');
}
$(function() {
	$('#ff1').hide();
	$('#tt1').datagrid({
		// onDblClickRow : see1,
		title : '',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/ticket!getTicketTimeList',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '开始时间',
			field : 'ticketTimeStar',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '截止时间',
			field : 'ticketTimeEnd',
			width : 200,
			rowspan : 2,
			align : 'center'
		} ] ],
		pagination : true,
		rownumbers : true,
		toolbar : "#toolbar1"

	});
	displayMsg1();
});

function displayMsg1() {
	$('#tt1').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function ticketStar() {
	$("#ticketStar").combobox({
		url : "../../site/manage/ticket!getTicketStart",
		valueField : "value",
		textField : "name",
		editable : false,
		missingMessage : '请选择组别！',
		listHeight : '15xp',
		onLoadSuccess : function() { // 数据加载完毕事件
			var data = $('#ticketStar').combobox('getData');
			if (data.length > 0) {
				$("#ticketStar").combobox('select', data[0].value);
			}
		}
	});
}

function trainNumb() {
	$("#trainNum").combobox({
		url : "../../site/manage/ticket!getTrainNumb",
		valueField : "value",
		textField : "name",
		editable : false,
		missingMessage : '请选择组别！',
		listHeight : '15xp',
		onLoadSuccess : function() { // 数据加载完毕事件
			var data = $('#trainNum').combobox('getData');
			if (data.length > 0) {
				$("#trainNum").combobox('select', data[0].value);
			}
		}
	});
}
function ticketEnd() {
	$("#ticketEnd").combobox({
		url : "../../site/manage/ticket!getTicketEnd1",
		valueField : "value",
		textField : "name",
		editable : false,
		missingMessage : '请选择组别！',
		listHeight : '15xp',
		onLoadSuccess : function() { // 数据加载完毕事件
			var data = $('#ticketEnd').combobox('getData');
			if (data.length > 0) {
				$("#ticketEnd").combobox('select', data[0].value);
			}
		}
	});
}
$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt').datagrid({
		onDblClickRow : updatetype,
		title : '信息类别管理',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		pageNumber : 1,
		nowrap : true,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/teach!getTeachMajor',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '类别名称',
			field : 'majorName',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '添加或修改时间',
			field : 'majorAddTime',
			width : 300,
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

function updatetype() {
	$("#update").css("visibility", "visible");
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#update').window('open');
		$('#Infortype').form('clear');
		$('#Infortype').show();
		$('#Infortype').appendTo('#infor1');
		$("#majorName").val(select.majorName);
	}else{
		$.messager.alert("提示", "请先选中一行数据！", "info");
	}
}
function opentype() {
	$('#add').window('open');
	$('#Infortype').form('clear');
	$('#Infortype').show();
	$('#Infortype').appendTo('#infor');
}
function close1() {
	$('#add').window('close');
}
function close2() {
	$('#update').window('close');
}
function addMajor() {
	$.ajax({
		type : "POST",
		url : "../../site/manage/teach!saveMajor",
		data : {
			'majorName' : $("#majorName").val()
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
function updateMajor() {
	var select = $('#tt').datagrid('getSelected');
	var Id = select.majorId;
	$.ajax({
		type : "POST",
		url : "../../site/manage/teach!updateTeachMajor",
		data : {
			'majorId' : Id,
			'majorName' : $("#majorName").val()
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

function delMajor() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('warning', '确认删除吗?', function(r) {
			var Id = selected.majorId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/teach!updateTeachMajor",
					data : "majorId=" + Id,
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
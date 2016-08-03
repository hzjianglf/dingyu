$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt').datagrid({
		title : '搜索范围管理',
		iconCls : 'icon-save',
		fitColumns : true,
		width : '100%',
		height : '100%',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-radius',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu : onRowContextMenu,
		columns : [ [ {
			title : '搜索范围大小（单位：公里）',
			field : 'radiusContent',
			width : '450',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '城市名称',
			field : 'centerName',
			width : '620',
			align : 'center',
			visibility : 'hidden'
		} ] ],
		/*
		 * onDblClickRow : function() { var selected =
		 * $('#tt').datagrid('getSelected'); if (selected) { getSelect(); } },
		 */
		rownumbers : true,

		toolbar : "#toolbar"
	});
	displayMsg();
});
function displayMsg() {

	$('#tt')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 10,// 每页显示的记录条数，默认为10
						pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

var id = "";
var centerName = "";
function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.radiusId;
		$("#updateradius").css("visibility", "visible");
		$('#updateradius').window('open');
		$('#addRadius').form('clear');
		$("#addRadius").css("visibility", "visible");
		$('#addRadius').show();
		$("#radiusContent").val(select.radiusContent);
		$("#centerName").val(select.centerName);
		$('#addRadius').appendTo('#update');
	} else {
		$.messager.alert('消息', "请选中一行~");
	}
}

function update() {
	var r = /^\+?[1-9][0-9]*$/;
	if (r.test($("#radiusContent").val())) {
		var select = $('#tt').datagrid('getSelected');
		$.ajax({
			url : "../site/manage-radius!update",
			type : 'post',
			data : {
				'radiusId' : select.radiusId,
				'centerName' : $("#centerName").val(),
				'radiusContent' : $("#radiusContent").val()
			},
			dataType : "json",
			success : function() {
				$.messager.alert('消息', '修改成功!');
				close();
				$('#tt').datagrid('reload');
				close1();
			},
			error : function() {
				$.messager.alert('消息', '修改失败!');
				close();
				$('#tt').datagrid('reload');
				close1();
			}

		});
	} else {
		$.messager.alert('消息', "请输入正整数");
	}
}

function close1() {
	$('#add').window('close');
	$('#updateradius').window('close');
}

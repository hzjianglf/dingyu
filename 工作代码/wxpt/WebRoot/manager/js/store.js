$(function() {

	$('#tt')
			.datagrid(
					{
						title : '店管理',
						iconCls : 'icon-save',
						fitColumns : true,
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../weixin/manage-store!edit',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [
								{
									title : '店名称',
									field : 'storeName',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '用户名称',
									field : 'userName',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '联系方式',
									field : 'telephone',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '地址',
									field : 'address',
									width : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '纬度',
									field : 'locationX',
									width : '150',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '经度',
									field : 'locationY',
									width : '150',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '状态',
									field : 'stateStr',
									width : '50',
									align : 'center',
									visibility : 'hidden'
								},
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.storeId + "  />";
									}
								} ] ],
						onClickRow : function(value, rec) {
							var str = $("#option").html();
							if (s_caocuo == "true") {
								str = $("#option").html().replace(
										"id=\"caozuo\"",
										"id=\"caozuo\" checked=\"checked\"");
							} else {
								str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
							}
							$("#option").html(str);
						},
						onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								UpdateStore();
							}
						},
						pagination : true,
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

var id = "";

function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.areaId;
		$("#updatearea").css("visibility", "visible");
		$('#updatearea').window('open');
		$('#addArea').form('clear');
		$("#addArea").css("visibility", "visible");
		$('#addArea').show();
		$("#areaName").val(select.areaName);
		$("#location_X").val(select.locationX);
		$("#location_Y").val(select.locationY);
		$("#areaNo").val(select.areaNo);
		$('#addArea').appendTo('#update');
	}
}

function AddStore() {

	$("#addstore").css("visibility", "visible");
	$('#addstore').window('open');
	$('#addStore').form('clear');
	$("#addStore").css("visibility", "visible");
	$("#addStore").show();
	$("#addStore").appendTo('#eeXL');
}

function add() {
	$.ajax({
		url : "../weixin/manage-store!add",
		type : 'post',
		data : '' + $("#addStore").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '添加成功!');
			close();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '添加失败!');
			close();
			$('#tt').datagrid('reload');
			close1();
		}

	});

}

function UpdateStore() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.storeId;
		$("#updatestore").css("visibility", "visible");
		$('#updatestore').window('open');
		$('#addStore').form('clear');
		$("#addStore").css("visibility", "visible");
		$('#addStore').show();
		$("#storeName").val(select.storeName);
		$("#userName").val(select.userName);
		$("#telephone").val(select.telephone);
		$("#address").val(select.address);
		$("#locationX").val(select.locationX);
		$("#locationY").val(select.locationY);
		$('#addStore').appendTo('#update');
	} else {
		alert("请选中一行");
	}
}
function update() {
	$.ajax({
		url : "../weixin/manage-store!update",
		type : 'post',
		data : {
			'storeId' : id,
			'storeName' : $("#storeName").val(),
			'userName' : $("#userName").val(),
			'telephone' : $("#telephone").val(),
			'address' : $("#address").val(),
			'locationX' : $("#locationX").val(),
			'locationY' : $("#locationY").val()
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
}

function pass(stateStr) {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.storeId;
		if (select.state == stateStr && stateStr == 1) {
			alert("审核通过!");
		} else if (select.state == stateStr && stateStr == 0) {
			alert("已取消审核!");
		} else {
			$.ajax({
				url : "../weixin/manage-store!upState",
				type : 'post',
				data : {
					'storeId' : id,
					'state' : stateStr
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
		}
	} else {
		alert("请选中一行!");
	}
}

function close1() {
	$('#addstore').window('close');
	$('#add').window('close');
	$('#updatestore').window('close');
}

// 删除教室信息

function DeleteStore() {

	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			ids += checks[i].value + ",";

		}

	}
	if (ids == "") {
		$.messager.alert('消息', '请选中要删除的数据', 'warning');
		return;
	}

	$.messager.confirm('消息', '确认删除么?', function(id) {

		if (id) {

			$.ajax({
				type : "POST",
				url : "../weixin/manage-store!delete",
				data : {
					'storeIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', '删除成功!');
					$('#tt').datagrid('reload');
					window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					window.location.reload();
				}
			});
		}
		$('#tt').datagrid('reload');
	});

}
var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var str = $("#option").html();
	// alert(s_caocuo);
	// alert(str.indexOf("checked"));//值为-1
	if (s_caocuo == "true") {
		s_caocuo = "false";
		str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
	} else {
		s_caocuo = "true";
		str = $("#option").html().replace("id=\"caozuo\"",
				"id=\"caozuo\" checked=\"checked\"");

	}
	$("#option").html(str);
	if (str.indexOf("checked") != -1 || str.indexOf("CHECKED") != -1) {
		// checks2[0].checked=true;
		for ( var i = 0; i < checks.length; i++) {
			// alert("已经选择");
			checks[i].checked = true;
		}

	} else {
		for ( var i = 0; i < checks.length; i++) {
			// alert("为选择");
			checks[i].checked = false;
		}
	}

}
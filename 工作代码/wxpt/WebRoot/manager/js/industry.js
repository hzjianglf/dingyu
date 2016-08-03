$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt')
			.datagrid(
					{
						title : '行业管理',
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
						url : '../site/manage-industry',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						  onRowContextMenu:onRowContextMenu,
						columns : [ [
						             {
								field : 'Opt',
								title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
								width : 40,
								align : 'center',
								formatter : function(value, rec) {
									return "<input type=\"checkbox\" name=\"xuanze\" value="
											+ rec.industryId + "  />";
								}
					     	},
								{
									title : '行业编号',
									field : 'industryNo',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '行业名称',
									field : 'industryName',
									width : '900',
									align : 'center',
									visibility : 'hidden'
								}
								 ] ],
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
						/*onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								getSelect();
							}
						},*/
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

var id = "";

function getSelect() {
	
	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			ids += checks[i].value + ",";

		}

	}
	if (ids == "") {
		$.messager.alert('消息', '请选中要修改的数据', 'warning');
		return;
	}
	
	
	if (ids) {
		var select = $('#tt').datagrid('getSelected');
		
		id = select.industryId;
		$("#updateindustry").css("visibility", "visible");
		$('#updateindustry').window('open');
		$('#addIndustry').form('clear');
		$("#addIndustry").css("visibility", "visible");
		$('#addIndustry').show();
		$("#industryName").val(select.industryName);
		$("#industryNo").val(select.industryNo);
		$('#addIndustry').appendTo('#update');
	}else{
		$.messager.alert('消息', '请选择一行!');
	}
}

function AddIndustry() {

	$("#addindustry").css("visibility", "visible");
	$('#addindustry').window('open');
	$('#addIndustry').form('clear');
	$("#addIndustry").css("visibility", "visible");
	$('#addIndustry').show();
	$('#addIndustry').appendTo('#eeXL');
}

function add() {
	$.ajax({
		url : "../site/manage-industry!add",
		type : 'post',
		data : '' + $("#addIndustry").serialize(),
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

function update() {
	$.ajax({
		url : "../site/manage-industry!update",
		type : 'post',
		data : {
			'industryId' : id,
			'industryName' : $("#industryName").val(),
			'industryNo' : $("#industryNo").val()
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

function close1() {
	$('#addindustry').window('close');
	$('#add').window('close');
	$('#updateindustry').window('close');
}

// 删除教室信息

function DeleteIndustry() {

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
				url : "../site/manage-industry!delete",
				data : {
					'industryName' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', '删除成功!');
					$('#tt').datagrid('reload');
					
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					
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
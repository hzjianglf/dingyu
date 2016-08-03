$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '投票幸运粉丝',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-user!getUserLuck',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [{
							field : 'Opt',
							title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
							width : 40,
							align : 'center',
							formatter : function(value, rec) {
								return "<input type=\"checkbox\" name=\"xuanze\" value="
										+ rec.luckId + "  />";
							}
						} ,
								{
									title : '粉丝名',
									field : 'sendUser',
									width : '791',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '投票时间',
									field : 'sendTime',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '状态',
									field : 'stateStr',
									width : '150',
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
						onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								//UpdateQuestion();
							}
						},
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
function AddluckUser(){
	$("#ff1").css("visibility", "visible");
	$("#add1").css("visibility", "visible");
	$('#ff1').show();
	$('#ff1').form('clear');
	$('#ff1').appendTo('#aaa');
	$('#add1').window('open');
}
function close1(){
	$("#add1").window('close');
}
function add(){
	$.ajax({
		type:"post",
		url:"../site/manage-user!getLuckUser",
		data : {
			'xzLuck' : $("#xzLuck").val(),
			'luckNumber' : $("#luckNumber").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"抽取成功!");
			$('#tt').datagrid('reload');
			//window.location.reload();
		},
		error : function() {
			$.messager.alert('消息',"抽取失败!");
			$('#tt').datagrid('reload');
			//window.location.reload();
		}
	});
}
function changeLuck() {
	if ($("#xzLuck").val() == 0) {
		$("#luckNumber").val("");
	}
	if ($("#xzLuck").val() == 1) {
		$("#luckNumber").val("请输入前几名的幸运粉丝");
	}
	if ($("#xzLuck").val() == 2) {
		$("#luckNumber").val("请输入要抽取的幸运粉丝数");
	}
}

function UpdateluckUser(){
	var selected = $('#tt').datagrid('getSelected');
	if(selected){
		if(selected.state==1){
			$.messager.alert('消息',"已领取");
		}else{
			$.ajax({
				type:"post",
				url:"../site/manage-user!updateLuckUser",
				data : {
					'lucyId' : selected.luckId
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"设置成功!");
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息',"设置失败!");
					$('#tt').datagrid('reload');
					//window.location.reload();
				}
			});
		}
	}else{
		$.messager.alert('消息',"请选择一行!");
	}
}


function DeleteluckUser() {

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
				url : "../site/manage-user!deleLuckUser",
				data : {
					'lucyIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"删除成功!");
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息',"删除失败!");
					$('#tt').datagrid('reload');
					//window.location.reload();
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
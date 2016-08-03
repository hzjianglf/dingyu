$(function() {
	$("#toolbar").css("visibility", "visible");
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid({
		onRowContextMenu : onRowContextMenu,
		title : '企业信息',
		// iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-enter!getList',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,

		columns : [ [

		/*
		 * { field : 'Opt', title : '<div id=\"option\"><input
		 * type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"
		 * onclick=\"selectAll()\" /></div>', width : 50, align : 'center',
		 * formatter : function(value, rec) { return "<input type=\"checkbox\"
		 * name=\"xuanze\" value=" + rec.enterInforId + " />"; } },
		 */{
			title : '企业Id',
			field : 'enterInforId',
			width : '120',
			align : 'center',
			hidden : true
		}, {
			title : '用户名',
			field : 'userName',
			width : '120',
			align : 'center',
			visibility : 'hidden'
		},/*
			 * { title : '企业用户名', field : 'enterPerson', width : '100', align :
			 * 'center', visibility : 'hidden' },
			 */{
			title : '企业名称',
			field : 'enterName',
			width : '150',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : 'URL',
			field : 'url',
			width : '200',
			align : 'center',
			hidden : true,
			sortable : true
		}, {
			title : 'Token',
			field : 'token',
			width : '80',
			align : 'center',
			hidden : true,
			sortable : true
		}, {
			title : 'Email000',
			field : 'email',
			width : '80',
			align : 'center',
			hidden : true,
			sortable : true
		}, {
			title : '电话',
			field : 'enterPhone',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : '地址',
			field : 'enterAddress',
			width : '80',
			align : 'center'
		/*
		 * hidden : true, sortable : true
		 */
		}, {
			title : '注册时间',
			field : 'registTime',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : '授权码',
			field : 'powerCode',
			width : '80',
			align : 'center',
			hidden : true,
			sortable : true
		}, {
			title : '授权状态',
			field : 'state',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : '角色',
			field : 'roleId',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		},{
			title : '用户类型',
			field : 'yonghu',
			width : '180',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		},
		{
			title : '状态',
			field : 'zhuangtai',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		},
		 {
			title : '期限(月)',
			field : 'qixian',
			width : '80',
			align : 'center'
			
		},{
			title : '结束时间',
			field : 'enterPerson',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : '',
			field : 'appId',
			width : '80',
			align : 'center',
			hidden : true
		}, {
			title : '',
			field : 'appSecret',
			width : '80',
			align : 'center',
			hidden : true
		}] ],
		/*
		 * onClickRow : function(value, rec) { var str = $("#option").html(); if
		 * (s_caocuo == "true") { str = $("#option").html().replace(
		 * "id=\"caozuo\"", "id=\"caozuo\" checked=\"checked\""); } else { str = "<input
		 * type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"
		 * onclick=\"selectAll()\" />"; } $("#option").html(str); },
		 * onDblClickRow : function() { var selected =
		 * $('#tt').datagrid('getSelected'); if (selected) { //
		 * window.open("DataView.action?Id="+selected.ID); queryVote(selected); } },
		 */
		rownumbers : true,

		toolbar : "#toolbar"
	});
	displayMsg();
});
document.oncontextmenu = function() {
	var selection = "";// 定义鼠标拖选值
	if (document.selection) {// ***** IE
		selection = document.selection.createRange().text;
	} else {// ***** 其他浏览器
		selection = document.getSelection();
	}
	// if来判断拖选值、被单击的区域是不是表单域值
	if (selection == ""
			&& (event.srcElement.value == undefined || event.srcElement.value == 0)
			&& event.srcElement.value != 0)
		return false;
}

function onRowContextMenu(e, rowIndex, rowData) {
	/* e.preventDafault(); */
	e.preventDefault();
}

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
function queryVote(selected) {
	$('#enterInfor').window('open');
	$("#enterInfor").css("visibility", "visible");
	$("#enterName").val(selected.enterName);
	$("#userName").val(selected.userName);
	$("#url").val(selected.url);
	$("#tooken").val(selected.token);
	$("#phone").val(selected.enterPhone);
	$("#email").val(selected.email);
	$("#address").val(selected.enterAddress);
	$("#code").val(selected.powerCode);
	$("#appid").val(selected.appId);
	$("#appsecret").val(selected.appSecret);
	$("#role").val(selected.roleId);
	$("#quanxian").val(selected.privelege);
	$("#zhuangtai").val(selected.state);
	$("#seeId").val(selected.enterInforId);
	// $("#").val(selected.);

}

function DeleteUser() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$.ajax({
			type : "POST",
			url : "../site/manage-enter!delete",
			data : {
				'enterId' : select.enterInforId,
				'enId' : select.enterInforId
			},
			secureuri : false,// 一般设置为false
			// dataType:"xml",
			success : function() {
				alert("删除成功!");
				$('#tt').datagrid('reload');
				window.location.reload();
			},
			error : function() {
				$.messager.alert('消息', '删除失败!');
				$('#tt').datagrid('reload');
				window.location.reload();
			}
		});
		$('#tt').datagrid('reload');
	} else {
		$.messager.alert('消息', "请选择一行");
	}

}

function query() {
	$('#tt').datagrid({
		url : "../site/manage-enter!getList",
		queryParams : {
			queryName : $("#queryName").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val()
		}

	});
	displayMsg();
}
function UpdateUser() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateuser").css("visibility", "visible");
		$('#updateuser').window('open');
		$('#addUser').form('clear');
		$("#addUser").css("visibility", "visible");
		$("#addUser").show();

		$("#userName").val(select.userName);
		$("#enterName").val(select.enterPerson);
		$("#enter").val(select.enterName);
		$("#url").val(select.url);
		$("#token").val(select.token);
		$("#phone").val(select.enterPhone);
		$("#email").val(select.email);
		// alert(select.enterInforId);
		$("#enterId").val(select.enterInforId);
		$("#address").val(select.enterAddress);
		$("#state").val(select.state);

		// alert(select.powerCode);
		$("#code").val(select.powerCode);
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息', "请选择企业");
	}
}
function UpdateQiXian(){

	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateTime").css("visibility", "visible");
		$('#updateTime').window('open');
		$('#updateQiXian').form('clear');
		$("#updateQiXian").css("visibility", "visible");
		$("#updateQiXian").show();
		$("#qx").val(select.qixian);
		$("#enterId").val(select.enterInforId);
		$("#updateQiXian").appendTo('#time');
	} else {
		$.messager.alert('消息', "请选择企业");
	}

	
}
function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-enter!update",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '修改成功!');
			close();
			$('#tt').datagrid('reload');
			close();
		},
		error : function() {
			$.messager.alert('消息', '修改失败!');
			close();
			$('#tt').datagrid('reload');
			close();

		}
	});
}

function close1() {
	$("#updateuser").window("close");
	$("#adduser").window("close");
	$("#laPage").window("close");
	$("#updateTime").window("close");
}
function UpdateRole() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		getRole();
		$('#RenterId').val(select.enterInforId);

	} else {
		$.messager.alert('消息', '选择企业!');
	}
}

function EditRole(data) {
	//var select = $("#pageTt").datagrid('getSelected');
	/*if (select) {*/
		$.ajax({
			type : "POST",
			url : "../site/manage-enter!updateRole",
			data : {
				'enterId' : $("#RenterId").val(),
				'roleId' : data
			},
			dataType : "json",
			success : function() {
				$.messager.alert('消息', '修改成功!');
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

	/*} else {
		$.messager.alert('消息', '选择角色!');
	}*/
}

function getRole() {
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt")
			.datagrid(
					{
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						/* onRowContextMenu:onRowContextMenu, */
						url : '../site/manage-enter!getRole',
						/*
						 * queryParams : { menuId : menuId },
						 */
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [ {
							title : 'roleId',
							field : 'roleId',
							width : '150',
							align : 'center'
						},

						{
							title : '角色',
							field : 'roleName',
							width : '180',
							align : 'center',
							visibility : 'hidden'
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
							var selected = $('#pageTt').datagrid('getSelected');
							if (selected) { //
								EditRole(selected.roleId); 
							 } 
							},
						rownumbers : true,
						laPage : "#laPage"
					});
	displayPageMsg();
}
function displayPageMsg() {

	$('#pageTt')
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

function seeEnter() {

	var enterId = $("#seeId").val();
	var type = $('#type').val();
	$.messager
			.confirm(
					'系统提示',
					'您确定要切换?',
					function(r) {
						if (r) {

							var date = new Date();
							date.setTime(date.getTime() - 10000);
							document.cookie = "wxpts" + "=a;expires="
									+ date.toGMTString() + "; path=/";
							$
									.ajax({
										type : "POST",
										url : "../site/manage!qiehuan",
										data : {
											'enterId' : enterId
										},
										dataType : "json",
										success : function() {
											top.location.href = "http://www.uniqyw.com/wxpt/site/manage?enterId="
													+ enterId
													+ "&type=1&qiehuan=1";
											// top.location.replace("http://localhost:8080/wxpt/site/manage?enterId="+enterId+"&type=1");
										},
										error : function() {
											$.messager.alert('消息', '修改失败!');
											close();
											$('#tt').datagrid('reload');
											close1();

										}
									});
						}
					});
}
//修改期限
function updateQX(){
	$.ajax({
		type : "POST",
		url : "../site/manage-enter!updateQiXian",
		data : {
			'enterId' : $("#enterId").val(),
			'qixian':$("#qx").val()
		},
		dataType : "json",
		success : function() {
			$('#tt').datagrid('reload');
			$.messager.alert('消息', '修改成功!');
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
//修改状态
function UpdateState(){
	var select = $("#tt").datagrid('getSelected');
	if(select){
		$.ajax({
			type : "POST",
			url : "../site/manage-enter!updateState",
			data : {
				'enterId' : select.enterInforId
			},
			dataType : "json",
			success : function() {
				$.messager.alert('消息', '修改成功!');
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

	}else{
		$.messager.alert('消息','选择一行数据~~');
	}

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
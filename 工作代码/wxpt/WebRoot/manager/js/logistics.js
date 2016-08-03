$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{
						
						title : '物流信息',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../wxpt/site/logistic!getList',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						
						columns : [ [
	      								
										{
											field : 'Opt',
											title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
											width : 50,
											align : 'center',
											formatter : function(value, rec) {
												return "<input type=\"checkbox\" name=\"xuanze\" value="
														+ rec.logisticsId + "  />";
											}
										},	
										{
											title : '会员',
											field : 'logisticsId',
											width : '130',
											align : 'center',
											hidden : true
										},
																			{
																			title : '配送方式',
																			field : 'logisticsWay',
																			width : '230',
																			align : 'center',
																			visibility : 'hidden'
																		},
																		
																		{
																			title : '价格',
																			field : 'logisticsPrice',
																			width : '360',
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
								queryVote();
							}
						},
						rownumbers : true,
						toolbar : "#toolbar"
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

function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function AddLo() {
	$("#adduser").css("visibility", "visible");
	$('#adduser').window('open');
	$('#addUser').form('clear');
	$("#addUser").css("visibility", "visible");
	$("#addUser").show();
	$("#addUser").appendTo('#add');
}

// 去除两头空格
function delTrim(str){	 
	return str.replace(/^\s+|\s+$/g,"");
}

// 编号整数
function isNaturalNumber(id,msg){
	var objValue = delTrim(id);
	if(typeof(objValue)=="undefined"){
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	}
	if(objValue=="" || objValue==null){ 
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	} 
	var regExp = /^\d*$/;
	if(regExp.test(objValue)){
		return true;
	}else{
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	}
	return true;
}

// 非空验证
function isEmpty(iempty,msg){
	 iempty=delTrim(iempty);
	if(iempty.length==0||iempty==null){
		$.messager.alert('warning',msg+"不能为空");
		return false;
	}else{
		// txtName_focus(id);
		return true;
	}
}

function add() {
	var money=$("#paymoney").val();
	if(money==""){
		alert("请输入运输费用");
	}else{
		
	
	$.ajax({
		type : "POST",
		url : "../wxpt/site/logistic!save",
		data : '' + $("#addUser").serialize(),
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
}
function close1() {
	$("#adduser").window("close");
	$("#updateuser").window("close");
}
function UpdateUser() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateuser").css("visibility", "visible");
		$('#updateuser').window('open');
		$('#addUser').form('clear');
		$("#addUser").css("visibility", "visible");
		$("#addUser").show();
		$("#payway").val(select.logisticsWay);
		$("#paymoney").val(select.logisticsPrice);
		$("#payId").val(select.logisticsId);
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息','请选择一行');
	}
}
function update() {
	$.ajax({
		type : "POST",
		url : "../wxpt/site/logistic!update",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '修改成功!');
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

function query() {
	$('#tt').datagrid(
			{
				url : "../site/manage-user!getList",
				queryParams : {
					paixu:$("#paixu").val(),
					queryName:$("#queryName").val(),
					startTime:$("#startTime").val(),
					endTime:$("#endTime").val()
				}

			});
	displayMsg();
}
function Delete() {

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
				url : "../wxpt/site/logistic!delete",
				data : {
					'logisticsId' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功!");
					$('#tt').datagrid('reload');
					// window.location.reload();
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
function queryVote(){
	
	var select = $('#tt').datagrid('getSelected');
	if(select){
		$('#la').datagrid({
			iconCls : 'icon-save',
			fit : true,
			pagination : true,// 设置分页条显示
			pageSize : 5,
			pageList : [ 5, 10, 15, 20 ],
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			url : '../site/manage-user!getUserCount?userId='+select.userId,
			loadMsg : '数据装载中......',
			sortName : 'code',
			sortOrder : 'desc',
			remoteSort : false,
			columns : [ [ {
				title : '投票人',
				field : 'sendUser',
				width : 252,
				rowspan : 2,
				align : 'center'
			}, {
				title : '投票时间',
				field : 'sendTime',
				width : 252,
				rowspan : 2,
				align : 'center'
			} ] ],

			onDblClickRow : function() {
				var selected = $('#la').datagrid('getSelected');
				if (selected) {
					// window.open("DataView.action?Id="+selected.ID);
					// judgeGetSelected();
				}
			},
			rownumbers : true,
			toolbar : "#toolbar1"
		});
		displayMsg1();
		$("#userNamename").html(select.userName+"得票情况");
		$("#lan").css("visibility", "visible");
		$('#lan').window('open');
	}else{
		alert("请选择一行!");
	}
}
function displayMsg1() {

	$('#la').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
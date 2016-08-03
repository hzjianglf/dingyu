$(function() {
	var enId = $("#enId").val();
	$("#toolbar").css("visibility", "visible");
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid({

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
		url : '../site/manage-disposition!getQyList?enId=' + enId,
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		// onRowContextMenu:onRowContextMenu,
		columns : [ [

		{
			title : '用户名',
			field : 'userName',
			width : '130',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '企业Id',
			field : 'enterInforId',
			width : '160',
			align : 'center',
			hidden : true
		},

		{
			title : '企业名称',
			field : 'enterName',
			width : '160',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : 'URL',
			field : 'url',
			width : '230',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : 'Token',
			field : 'token',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : 'Email',
			field : 'email',
			width : '100',
			align : 'center',
			visibility : 'hidden',
			hidden : true,
			sortable : true
		}, {
			title : '电话',
			field : 'enterPhone',
			width : '100',
			align : 'center',
			visibility : 'hidden',
			hidden : true,
			sortable : true
		}, {
			title : '地址',
			field : 'enterAddress',
			width : '300',
			align : 'center',
			visibility : 'hidden',
			hidden : true,
			sortable : true
		}, {
			title : 'appId',
			field : 'appId',
			width : '250',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : 'appSecret',
			field : 'appSecret',
			width : '250',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		},

		{
			title : '注册时间',
			field : 'registTime',
			width : '80',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		} ] ],
		/*
		 * onClickRow : function(value, rec) { var str = $("#option").html(); if
		 * (s_caocuo == "true") { str = $("#option").html().replace(
		 * "id=\"caozuo\"", "id=\"caozuo\" checked=\"checked\""); } else { str = "<input
		 * type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"
		 * onclick=\"selectAll()\" />"; } $("#option").html(str); },
		 */
		/*
		 * onDblClickRow : function() { var selected =
		 * $('#tt').datagrid('getSelected'); if (selected) { //
		 * window.open("DataView.action?Id="+selected.ID); queryVote(); } },
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

/*function DeleteUser() {

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
		alert(ids);
		if (id) {

			$.ajax({
				type : "POST",
				url : "../site/manage-enter!delete",
				data : {
					'enterId' : ids
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
		}
		$('#tt').datagrid('reload');
	});

}*/

function AddUser() {
	$("#add").css("visibility", "visible");
	$('#add').window('open');
	$('#addUser').form('clear');
	$("#addUser").css("visibility", "visible");
	$("#addUser").show();
	/*
	 * $("#userName").val(select.userName);
	 * $("#enterName").val(select.enterPerson);
	 * $("#enter").val(select.enterName); $("#url").val(select.url);
	 * $("#token").val(select.token); $("#phone").val(select.enterPhone);
	 * $("#email").val(select.email); $("#appId").val(select.appId);
	 * $("#appSecret").val(select.appSecret); //alert(select.enterInforId);
	 * $("#enterId").val(select.enterInforId);
	 * $("#address").val(select.enterAddress); $("#appId").val(select.appId);
	 * $("#appSecret").val(select.appSecret);
	 */
	$("#addUser").appendTo('#add1');

}

function add() {
	var enId = $("#enId").val();
	$.ajax({
		type : "POST",
		url : "../site/manage-disposition!addQy?enId=" + enId,
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '添加成功!');
			close1();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '添加失败!');
			close1();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}

//非空验证
function isEmpty(id,msg){
	var iempty=delTrim(id);
	if(iempty.length==0){
		$.messager.alert('warning',"用户名不能为空");	
		return false;
	}else{
		
		return true;
	}
}
//去除两头空格
function delTrim(str){	 
	return str.replace(/^\s+|\s+$/g,"");
}
//非法用户字符检查
function illUserCheck(id,msg){
	var vid=delTrim(id);
	var patten=/^[a-zA-Z]{1,}[a-zA-Z0-9_]*$/;
	if(!patten.test(vid)){
		$.messager.alert('warning',"用户名不合法");	 		
		return false;
	}
	return true;
}
// 半段用户名是否存在
function panduan() {
	var name=$("#userName").val();
	//判断用户名合法性
	if(isEmpty(name,"用户名")==false){
		return false;
	}
	if(illUserCheck(name,"用户名")==false){
		return false;
	}
	$.ajax({
		type : "POST",
		url : "../site/manage-disposition!panduan",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function(data) {
			if (data == 0) {
				add();
			} else {
				$.messager.alert('消息', '该用户已存在！！');
			}
		},
		error : function(data) {
			$.messager.alert('消息', '网络错误！');
			close1();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}
// 删除
function DeleteChileUser() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$.ajax({
			type : "POST",
			url : "../site/manage-disposition!deleteChile",
			data : {
				'enId' : select.enterInforId
			},
			secureuri : false,// 一般设置为false
			// dataType:"xml",
			success : function() {
				$.messager.alert('消息', "删除成功!");
				$('#tt').datagrid('reload');
				$('#tt').datagrid('reload');
				// window.location.reload();
			},
			error : function() {
				$.messager.alert('消息', "删除失败!");
				$('#tt').datagrid('reload');
				// window.location.reload();
			}
		});
	} else {
		$.messager.alert('消息', '选择企业信息！');
	}
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
		$("#appId").val(select.appId);
		$("#appSecret").val(select.appSecret);
		//alert(select.enterInforId);
		$("#enterId").val(select.enterInforId);
		$("#address").val(select.enterAddress);
		$("#appId").val(select.appId);
		$("#appSecret").val(select.appSecret);
		$("#userName").css("visibility", "visible");
		
		
		
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息', "请选择一行");
	}
}

function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-disposition!update2",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '修改成功!');
			close1();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '修改失败!');
			close1();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}
function DeleteUser (){
	var enId = $("#enId").val();
	var select = $("#tt").datagrid('getSelected');
	if (select) {

			$.ajax({
				type : "POST",
				url : "../site/manage-enter!delete",
				data : {
					'enterId' : select.enterInforId,
					'enId':enId
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
		}else{
			$.messager.alert('消息', "请选择一行");
		}
		


}
function close1() {

	$("#updateuser").window("close");
	$("#add").window("close");
}
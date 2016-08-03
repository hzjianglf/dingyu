$(function() {
	var enId = $("#enId").val();
	$("#toolbar").css("visibility", "visible");
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid({

		title : '答题提示',
		// iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url :' ../site/manage-question-tishi!getTishi',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		// onRowContextMenu:onRowContextMenu,
		columns : [ [

		{
			title : '未中奖',
			field : 'meiJiang',
			width : '130',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : 'Id',
			field : 'questionTishiId',
			width : '160',
			align : 'center',
			hidden : true
		},

		{
			title : '答错',
			field : 'daCuo',
			width : '160',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '超时',
			field : 'chaoShi',
			width : '230',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		} 
		, {
			title : '已抽奖',
			field : 'choujiang',
			width : '230',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}, {
			title : '答过',
			field : 'daguo',
			width : '230',
			align : 'center',
			visibility : 'hidden',
			sortable : true
		}] ],
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

/*
 * function DeleteUser() {
 * 
 * var checks = document.getElementsByName("xuanze");
 * 
 * var ids = ""; for ( var i = 0; i < checks.length; i++) { if
 * (checks[i].checked == true) { ids += checks[i].value + ",";
 *  }
 *  } if (ids == "") { $.messager.alert('消息', '请选中要删除的数据', 'warning'); return; }
 * 
 * $.messager.confirm('消息', '确认删除么?', function(id) { alert(ids); if (id) {
 * 
 * $.ajax({ type : "POST", url : "../site/manage-enter!delete", data : {
 * 'enterId' : ids }, secureuri : false,// 一般设置为false // dataType:"xml", success :
 * function() { alert("删除成功!"); $('#tt').datagrid('reload');
 * window.location.reload(); }, error : function() { $.messager.alert('消息',
 * '删除失败!'); $('#tt').datagrid('reload'); window.location.reload(); } }); }
 * $('#tt').datagrid('reload'); });
 *  }
 */

function AddGrade() {
	$.ajax({
		type : "POST",
		url : "../site/manage-question-tishi!panduan",
		//data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function(data) {
			if(data=="0"){
				$("#add").css("visibility", "visible");
				$('#add').window('open');
				$('#addUser').form('clear');
				$("#addUser").css("visibility", "visible");
				$("#addUser").show();
				$("#addUser").appendTo('#add1');
			}else{
				$.messager.alert('消息', '请修改数据~~~');
			}
		},
		error : function() {
			$.messager.alert('消息', '系统繁忙~~~');
			close1();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}

function add() {
	$.ajax({
		type : "POST",
		url : "../site/manage-question-tishi!addTishi",
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

// 非空验证
function isEmpty(id, msg) {
	var iempty = delTrim(id);
	if (iempty.length == 0) {
		$.messager.alert('warning', "用户名不能为空");
		return false;
	} else {

		return true;
	}
}
// 去除两头空格
function delTrim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}
// 非法用户字符检查
function illUserCheck(id, msg) {
	var vid = delTrim(id);
	var patten = /^[a-zA-Z]{1,}[a-zA-Z0-9_]*$/;
	if (!patten.test(vid)) {
		$.messager.alert('warning', "用户名不合法");
		return false;
	}
	return true;
}
// 删除
function DeleteGrade() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$.ajax({
			type : "POST",
			url : "../site/member-grade!deleteGrade",
			data : {
				'gradeId' : select.memberGradeId
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
function UpdateGrade() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateuser").css("visibility", "visible");
		$('#updateuser').window('open');
		$('#addUser').form('clear');
		$("#addUser").css("visibility", "visible");
		$("#addUser").show();
		$("#wei").val(select.meiJiang);
		$("#dacuo").val(select.daCuo);
		$("#chaoshi").val(select.chaoShi);
		$("#tishiId").val(select.questionTishiId);
		$("#choujiang").val(select.choujiang);
		$("#daguo").val(select.daguo);
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息', "请选择一行数据~~~");
	}
}

function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-question-tishi!updateTishi",
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
function uploadImage(){
	$.ajaxFileUpload({
		url : "../site/member-grade!uploadeImage",
		secureuri : false,// 一般设置为false
		fileElementId : 'gradeImage',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{    
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
				$("#gradeImage").val("");
			} else if (data == -1) {
				$.messager.alert('提示信息', '上传失败');
			} else if (data == 0) {
				$.messager.alert('提示信息', '请选择上传图片');
				$("#gradeImage").val("");

			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			$.messager.alert('提示信息', '网络繁忙');
		}
	});
}
function close1() {

	$("#updateuser").window("close");
	$("#add").window("close");
}
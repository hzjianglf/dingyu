$(function() {
	var enId = $("#enId").val();
	$("#toolbar").css("visibility", "visible");
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid({

		title : '选项设置',
		// iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url :' ../site/manage-survey!querySuroption',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		// onRowContextMenu:onRowContextMenu,
		columns : [ [{
			field : 'Opt',
			title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
			width : 40,
			align : 'center',
			formatter : function(value, rec) {
				return "<input type=\"checkbox\" name=\"xuanze\" value="
					+ rec.optionId + "  />";
				}
			} ,

		         	{
		    			title : '题目编号',
		    			field : 'questionNumber',
		    			width : '100',
		    			align : 'center',
		    			visibility : 'hidden'
		    		},
		    		{
		    			title : '题目标题',
		    			field : 'questionContent',
		    			width : '300',
		    			align : 'center',
		    			visibility : 'hidden'
		    		},
		{
			title : '选项编号',
			field : 'optionNumber',
			width : '100',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '选项内容',
			field : 'optionContent',
			width : '300',
			align : 'center'
			//hidden : true
		},

		{
			title : '保留得分',
			field : 'optionCode',
			width : '100',
			align : 'center',
			visibility : 'hidden'
		},
		{
			title : '添加时间',
			field : 'optionAddtime',
			width : '130',
			align : 'center',
			visibility : 'hidden'
		},
		{
			title : '修改时间',
			field : 'optionUpdatetime',
			width : '130',
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

var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var str = $("#option").html();
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

//批量删除
function DeleteSuroption() {
	   
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
				url : '../site/manage-survey!deleteSuroption',
				data : {
					'idOptions' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function(data) {
				
						$.messager.alert('消息',"删除成功!");
						$('#tt').datagrid('reload');
					
					
					
				},
				error : function() {
					$.messager.alert('消息',"网络异常!");
					$('#tt').datagrid('reload');
					
				}
			});
		}
		$('#tt').datagrid('reload');
	});

}


function AddSuroption() {


				$("#add").css("visibility", "visible");
				$('#add').window('open');
				$('#addUser').form('clear');
				$("#addUser").css("visibility", "visible");
				$("#addUser").show();
				$("#addUser").appendTo('#add1');
				$("#questionnum1").css("display", "none");
				$("#questioncontent1").css("display", "none");
				$("#questionnum2").css("display", "block");
				$("#questioncontent2").css("display", "block");
				$("#questionnum2").css("display", "none");
				$("#questioncontent2").css("display", "none");
				$("#questionnum1").css("display", "block");
				$("#questioncontent1").css("display", "block");

}

function add() {
	$.ajax({
		type : "POST",
		url : "../site/manage-survey!addSuroption",
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
function UpdateSuroption() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateuser").css("visibility", "visible");
		$('#updateuser').window('open');
		$('#addUser').form('clear');
		$("#addUser").css("visibility", "visible");
		$("#questionnum1").css("display", "none");
		$("#questioncontent1").css("display", "none");
		$("#questionnum2").css("display", "block");
		$("#questioncontent2").css("display", "block");
		$("#addUser").show();
		$("#optionId").val(select.optionId);
		$("#questionSurNumber").val(select.questionNumber);
		$("#questionSurContent").val(select.questionContent);
		$("#optionNumber").val(select.optionNumber);
		$("#optionContent").val(select.optionContent);
		$("#optionCode").val(select.optionCode);
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息', "请选择一行");
	}
}

function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-survey!updateSuroption",
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
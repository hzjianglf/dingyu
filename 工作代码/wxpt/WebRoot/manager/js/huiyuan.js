$(function() {
	$("#toolbar").css("visibility", "visible");
	$("#huiyuanxingqing").css("visibility", "visible");

	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#huiyuan').datagrid({
		title : '会员基本信息管理',
		iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/member!getmemberall',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu : onRowContextMenu,
		columns : [ [/*
						 * { field : 'Opt', title : '<div id=\"option\"><input
						 * type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"
						 * onclick=\"selectAll()\" /></div>', width : 50, align :
						 * 'center', formatter : function(value, rec) { return "<input
						 * type=\"checkbox\" name=\"xuanze\" value=" +
						 * rec.explicitId + "/>"; } } ,
						 */
		{
			title : '会员id',
			field : 'memberId',
			width : '50',
			align : 'center',
			hidden : true
		}, {
			title : '会员卡号',
			field : 'cardId',
			width : '130',
			align : 'center'
		}, {
			title : '微信号',
			field : 'weixinId',
			width : '100',
			align : 'center'
		}, {
			title : '密码',
			field : 'password',
			width : '60',
			align : 'center'
		}, {
			title : '用户名',
			field : 'username',
			width : '70',
			align : 'center'
		}, {
			title : '性别',
			field : 'sex',
			width : '60',
			align : 'center'
		}, {
			title : '年龄',
			field : 'age',
			width : '50',
			align : 'center'
		}, {
			title : '添加时间',
			field : 'addTime',
			width : '90',
			align : 'center'
		},

		{
			title : '结束时间',
			field : 'endTime',
			width : '90',
			align : 'center'
		}, {
			title : '地址',
			field : 'address',
			width : '120',
			align : 'center'
		}, {
			title : '电话',
			field : 'phone',
			width : '80',
			align : 'center'
		}, {
			title : '积分',
			field : 'memberPoints',
			width : '70',
			align : 'center'
		}, {
			title : '存款',
			field : 'saveMoney',
			width : '70',
			align : 'center'
		}, {
			title : '会员级别',
			field : 'memberGrade',
			width : '60',
			align : 'center'
		}, {
			title : '状态',
			field : 'memberFreeze',
			width : '100',
			align : 'center'
		},

		] ],

		onDblClickRow : function() {
			var selected = $('#tt').datagrid('getSelected');
			if (selected) {
				// window.open("DataView.action?Id="+selected.ID);
				UpdateQuestion();
			}
		},
		rownumbers : true,
		toolbar : "#toolbar"
	});
	displayMsg();
});
function searchJob() {
	var memberName = $("#userName").val();
	var add_time = $("#add_time").val();
	var weixin_id = $("#weixin_id").val();
	var member_freeze = $("#member_freeze").val();
	$('#huiyuan').datagrid({
		url : '../site/member!findMember',
		queryParams : {
			memberName : memberName,
			weixin_id : weixin_id,
			member_freeze : member_freeze,
			add_time : add_time

		}
	});
	displayMsg();
}
function displayMsg() {

	$('#huiyuan')
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

function close2() {
	$('#enterprisepicture').window('close');
}
// 冻结
function dongjie() {

	// alert("jie冻结");

	var select = $('#huiyuan').datagrid('getSelected');

	if (select) {
		var memberId = select.memberId;
		$.messager.confirm('warning', '是否冻结？', function(id) {
			if (id) {

				$.ajax({
					type : "post",
					url : '../site/member!dongjie',
					data : {

						'memberId' : memberId
					},
					success : function(date) {
						if (date == 1) {
							$('#huiyuan').datagrid('reload');
							$.messager.alert('warning', '冻结成功');
							$('#huiyuan').datagrid('reload');
						}
					},
					error : function(date) {
						if (date == -1) {
							$('#huiyuan').datagrid('reload');
							$.messager.alert('warning', '冻结失败');
						}

						// $('#xx').datagrid('reload');

					}
				});
			}
		});

	} else {

		$.messager.alert('warning', '请选择一行数据', 'warning');
	}

}

// 解除冻结
function jiedong() {

	// alert("jie冻结");

	var select = $('#huiyuan').datagrid('getSelected');

	if (select) {
		var memberId = select.memberId;
		$.messager.confirm('warning', '是否解除冻结？', function(id) {
			if (id) {

				$.ajax({
					type : "post",
					url : '../site/member!jiedong',
					data : {

						'memberId' : memberId
					},
					success : function(date) {
						if (date == 1) {
							$('#huiyuan').datagrid('reload');
							$.messager.alert('warning', '解除冻结成功');
							$('#huiyuan').datagrid('reload');
						}
					},
					error : function(date) {
						if (date == -1) {
							$('#huiyuan').datagrid('reload');
							$.messager.alert('warning', '解除冻结失败');
						}

						// $('#xx').datagrid('reload');

					}
				});

			}
		});

	} else {

		$.messager.alert('warning', '请选择一行数据', 'warning');
	}

}

function huifu(id) {

	$('#' + id).css("display", "");

}

function tijiao(id, id2) {

	// alert($('#'+id).val()+"==yyyy=="+id2);

	$.ajax({
		type : "post",
		url : '../site/member!yanzhengyitiao',
		data : {
			'messageid' : id2
		},
		success : function(date) {
			if (date == 1) {

				$.ajax({
					type : "post",
					url : '../site/member!huifuliuyan',
					data : {

						'huifuneirong' : $('#' + id).val(),

						'messageid' : id2
					},
					success : function(text) {
						$("#liuyanban").html(text);
						$.messager.alert('warning', '回复成功');
					},
					error : function(text) {
						$.messager.alert('warning', '回复失败');

					}
				});

			} else {

				$.messager.alert('warning', '只能回复一条');

			}

		},
		error : function(date) {

		}
	});

	/*
	 * $.ajax({ type:"post", url: '../site/member!huifuliuyan', data: {
	 * 
	 * 'huifuneirong':$('#'+id).val(),
	 * 
	 * 'messageid':id2 }, success: function (text) { $("#liuyanban").html(text);
	 * $.messager.alert('warning','回复成功'); }, error: function (text) {
	 * $.messager.alert('warning','回复失败');
	 *  } });
	 */

}

function quxiao(id) {

	$('#' + id).css("display", "none");

}

function uploadUi() {
	$('#uploadok').css("display", "block");
	$('#ejob_form').css("display", "block");
	$('#enterprisepicture').window('open');
	$('#enterprisepicture').css("display", "block");
	$('#ejob_form').show();
	$('#ejob_form').appendTo('#ejobpicture');

}
function closeFileUpload() {
	$('#enterprisepicture').window('close');
}

function uploadFile() {
	$.ajaxFileUpload({
		url : '../site/member!uploadfile',
		secureuri : false,// 一般设置为false
		fileElementId : 'upload',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',
		success : function(data) // 服务器成功响应处理函数
		{
			if (data == "1") {
				$.messager.alert('提示信息', '上传成功');
			} else {
				$.messager.alert('提示信息', '上传失败');
			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});

}


function updateJiFen(){
	var select = $('#huiyuan').datagrid('getSelected');
	if(select){
	$('#update').window('open');
	$("#ff1").css("visibility", "visible");
	$("#update").css("visibility", "visible");
	$('#ff1').show();
	$('#ff1').form('clear');
	$('#ff1').appendTo('#update1');
	$('#jifen0').val(select.memberPoints);
	}else{
		$.messager.alert('提示信息', '选择一条数据~');
	}
}
function updatejiFen(){
	alert("修改积分");
	var select = $('#huiyuan').datagrid('getSelected');
	var jifen1=$("#jifen1").val();
	alert(jifen1);
	alert(select.memberId);
	$.ajax({
		type:'post',
		url:'../site/member!updateJiFen',
		data:{
			'jifen1':jifen1,
			'memberId':select.memberId
		},
		success:function(){
			$.messager.alert('提示信息', '修改成功~');
			$('#huiyuan').datagrid('reload');
			closes();
			
		},
		error:function(){
			$.messager.alert('提示信息', '修改失败~');
			$('#huiyuan').datagrid('reload');
			closes();
		}
	
	});
}
function closes(){
	$('#update').window('close');
}

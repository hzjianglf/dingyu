$(function() {
	$("#toolbar").css("visibility", "visible");

	$('#tt')
			.datagrid(
					{

						title : '摄影用户管理',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-sheying!getFas',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
								{
									title : 'FansId',
									field : 'fansUserId',
									width : '700',
									align : 'center',
									hidden : true
								},
								{
									title : 'FansName',
									field : 'fansUserName',
									width : '250',
									height : '300',
									align : 'center',
									hidden : true
								},
								{
									title : '昵称',
									field : 'nickname',
									width : '200',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '个性签名',
									field : 'signature',
									width : '400',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '头像',
									field : 'touxiang',
									width : '300',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '点赞人数',
									field : 'count',
									width : '100',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								}/*,
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : '40',
									height : '300',
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.userName + "  />";
									}
								} */] ],
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
						//onDblClickRow : seeImg,
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function close1(){
	$('#updateuser').window('close');
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

function seeImg() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$("#showImg").css("visibility", "visible");
		$('#showImg').window('open');
		$("#imgshow").html(select.fansImageValue);
	} else {
		$.messager.alert("消息", "请选择查看的数据");
	}
}
//修改
function UpdateUser() {
	var select = $("#tt").datagrid('getSelected');
	if (select) {
		$("#updateuser").css("visibility", "visible");
		$('#updateuser').window('open');
		$('#addUser').form('clear');
		$("#addUser").css("visibility", "visible");
		$("#addUser").show();
		$("#nickname").val(select.nickname);
		$("#signature").val(select.signature);
		$("#fansName").val(select.fansUserName);
		$("#addUser").appendTo('#update');
	} else {
		alert("请选择一行");
	}
}
function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-sheying!updateFans",
		data : '' + $("#addUser").serialize(),
		fileElementId : 'touxiang',
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
function uploads()
{
	var select = $("#tt").datagrid('getSelected');
	var fansName=select.fansUserName;
//	alert(fansName);
	$.ajaxFileUpload({
		url : "../site/manage-sheying!sheTouXiang?fansName="+fansName,
		secureuri : false,// 一般设置为false
		fileElementId : 'touxiang',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{    
		//alert(data);
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
			} else if (data == -1) {
				$.messager.alert('提示信息', '上传失败');
			} else if (data == 0) {
				$.messager.alert('提示信息', '请选择上传图片');

			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});

}
function close(){
	$("#updateuser").window("close");
}

// 审核
function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$.ajax({
			url : "../site/manage-sheying!check",
			type : "post",
			datetype : "json",
			data : {
				'fansId' : select.fansId
			},
			success : function(data){
				var str = eval(data);
				$.messager.alert("消息",str.message);
				$('#tt').datagrid('reload');
			},
			error : function(){
				$.messager.alert("消息", "网络异常");
			}
		});
	} else {
		$.messager.alert("消息", "请选择要审核的数据");
	}
}

var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var caozuo = $("#caozuo")[0].checked;
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
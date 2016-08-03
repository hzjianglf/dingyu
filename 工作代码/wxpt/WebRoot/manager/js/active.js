$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{
						title : '投票活动管理',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '/wxpt/site/manage-vote!getVoteList',
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
												+ rec.voteId + "  />";
									}
								},
									
									{
									title : '编号',
									field : 'voteId',
									width : '220',
									align : 'center',
									hidden:true
								},
								{
									title : '活动名称',
									field : 'voteUser',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '开始时间',
									field : 'voteStartTime',
									width : '220',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '结束时间',
									field : 'voteEndTime',
									width : '220',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '状态',
									field : 'state',
									width : '220',
									align : 'center',
									visibility : 'hidden',
									sortable:true
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
								queryVote();
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
function AddVote() {
	$("#addvote").css("visibility", "visible");
	$('#addvote').window('open');
	$('#addUser').form('clear');
	$("#addUser").css("visibility", "visible");
	$("#addUser").show();
	$("#addUser").appendTo('#eeXL');
}

//去除两头空格
function delTrim(str){	 
	return str.replace(/^\s+|\s+$/g,"");
}


//非空验证
function isEmpty(iempty,msg){
	 iempty=delTrim(iempty);
	if(iempty.length==0||iempty==null){
		$.messager.alert('warning',msg+"不能为空");
		return false;
	}else{
		//txtName_focus(id);
		return true;
	}
}

function add() {	
	$.ajax({
		type : "POST",
		url : "../site/manage-vote!saveVote",
		data : '' + $("#huodong").serialize(),
		dataType : "text",
		success : function(data) {
			if(data==1){
				$.messager.alert('消息', '添加成功!');
				close();
				$('#tt').datagrid('reload');
				close1();
			}else if(data==2){
				$.messager.alert('消息', '上次活动正在进行中，不能再次添加');
				close();
				$('#tt').datagrid('reload');
				close1();
			}
			
		},
		error : function() {
			$.messager.alert('消息', '添加失败!');
			close();
			$('#tt').datagrid('reload');
			close();

		}
	});
}
function close1() {
	$("#addvote").window("close");
	$("#updateuser").window("close");
}
function update() {
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
	//	alert(ids);
		$.ajax({
			type : "POST",
			url : "../site/manage-vote!update",
			data : {
				'userIds' : ids
			},
			secureuri : false,// 一般设置为false
			// dataType:"xml",
			success : function() {
				$.messager.alert('消息', "修改成功!");
				$('#tt').datagrid('reload');
				//window.location.reload();
			},
			error : function() {
				$.messager.alert('消息', '修改失败!');
				$('#tt').datagrid('reload');
				window.location.reload();
			}
		});
	
}

function DeleteUser() {

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
				url : "../site/manage-vote!delete",
				data : {
					'userIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功!");
					$('#tt').datagrid('reload');
					//window.location.reload();
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
function callback(message, success) {
	$.messager.alert('消息',message);
	close1();
	$('#tt').datagrid('reload');
	close1();
	/*close2();
*/	//window.location.reload();
}
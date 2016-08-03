var s_caocuo=false;
$(function() {
	$("#toolbar").css("visibility", "visible");	
	$('#tt')
			.datagrid(
					{

						title : '自定义菜单',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-menu!selectAll',
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
								return "<input type=\"checkbox\" name=\"xuanze\" id=\"xuanze\" value="
										+ rec.menuId + "  />";
							}
						} ,
								{
									title : '菜单Id',
									field : 'menuId',
									width : '791',
									align : 'center',
									hidden:true
								},
								{
									title : '菜单',
									field : 'menuNum',
									width : '350',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '菜单名',
									field : 'menuName',
									width : '350',
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
								//UpdateQuestion();
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

function createMenu(){
$.ajax({
		type:"post",
		url:"/wxpt/site/menu!createMenu",
		secureuri:false,
		success : function(date) {
			$.messager.alert('消息',"菜单创建成功!");
		},
		error : function() {
			$.messager.alert('消息',"菜单创建失败!");
		}
	});

}
function displayPageMsg() {

	$('#pageTt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function Addmenu(){
	$.ajax({
		type:"post",
		url:"../site/manage-menu!getCount",
		/*data : {
			'menuName' : $("#menuName").val(),
		},*/
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function(date) {
			var str = eval(date);
			if(str.state==0){
				$("#ff1").css("visibility", "visible");
				$("#add1").css("visibility", "visible");
				$('#ff1').show();
				$('#ff1').form('clear');
				$('#ff1').appendTo('#aaa');
				$('#add1').window('open');
				$('#menuNum').html(str.message);
			}else{
				$.messager.alert('消息',str.message);
			}
		},
		error : function() {
			$.messager.alert('消息',"添加失败!");
			$('#tt').datagrid('reload');
		}
	});
}
function close1(){
	$("#add1").window('close');
	$("#update").window('close');
	$("#add2").window('close');
	$("#update2").window('close');
}
function add(){
	$.ajax({
		type:"post",
		url:"../site/manage-menu!saveMenu",
		data : {
			'menuName' : $("#menuName").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"添加成功!");
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息',"添加失败!");
			$('#tt').datagrid('reload');
			close1();
		}
	});
}
function add2(){

	$.ajax({
		type:"post",
		url:"../site/manage-menu!saveMenu2",
		data : {
			'menuId' : $("#parentId").val(),
			'menuType' : $("#menuType").val(),
			'menuUrl' : $("#menuUrl").val(),
			'menuKey' : $("#menuKey").val(),
			'menuName' : $("#menuName").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"添加成功!");
			$('#pageTt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息',"添加失败!");
			$('#pageTt').datagrid('reload');
			close1();
		}
	});

}

/*function changeLuck() {
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
*/
function Editmenu(){
	var selected = $('#tt').datagrid('getSelected');
	if(selected){
		$("#ff1").css("visibility", "visible");
		$("#update").css("visibility", "visible");
		$('#ff1').show();
		$('#ff1').form('clear');
		$('#ff1').appendTo('#update1');
		$('#update').window('open');
		$('#menuNum').html(selected.menuNum);
		$('#menuName').val(selected.menuName);
	}else{
		$.messager.alert('消息',"请选择主菜单!");
	}
}

function updateMenu(){
	var selected = $('#tt').datagrid('getSelected');
	$.ajax({
		type:"post",
		url:"../site/manage-menu!updateMenu",
		data : {
			'menuName' : $("#menuName").val(),
			'menuId':selected.menuId
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"修改成功!");
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息',"修改失败!");
			$('#tt').datagrid('reload');
			close1();
		}
	});
}
function Seemenu(){
	var selected = $('#tt').datagrid('getSelected');
	if(selected){
		see(selected.menuId);
	}else{
		$.messager.alert('消息',"请选择主菜单!");
	}
	
}

function see(menuId){
	$("#parentId").val(menuId);
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		onRowContextMenu:onRowContextMenu,
		url : '../site/manage-menu!slectChild',
		queryParams : {
			menuId : menuId
			
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : 'menuID',
					field : 'menuId',
					width : '120',
					align : 'center',
					hidden:true
				},
				{
					title : '菜单',
					field : 'menuNum',
					width : '120',
					align : 'center',
					hidden : true
				},
				{
					title : '菜单名',
					field : 'menuName',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '类型',
					field : 'menuType',
					width : '170',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : 'url',
					field : 'menuUrl',
					width : '270',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : 'Key',
					field : 'menuKey',
					width : '170',
					align : 'center',
					visibility : 'hidden'
				}] ],
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

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}

function AddChilMenu(){
	$.ajax({
		type:"post",
		url:"../site/manage-menu!chileCount",
		data : {
			'menuId' : $("#parentId").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function(date) {
			var str = eval(date);
			if(str.state==0){
				$('#ff1').form('clear');
				$("#ff1").css("visibility", "visible");
				$("#add2").css("visibility", "visible");
				$("#url").css("display", "none");
				$("#key").css("display", "none");
				$('#ff1').show();
				$('#ff1').appendTo('#aaa2');
				$('#add2').window('open');
				$('#menuNum').html(str.message);
				$("#type").css("display", "");
			}else{
				$.messager.alert('消息',str.message);
			}
		},
		error : function() {
			$.messager.alert('消息',"添加失败!");
			$('#tt').datagrid('reload');
		}
	});

}

function change(){
	var type=$("#menuType").val();
	if(type=="view"){
		$("#url").css("display", "");
		$("#key").css("display", "none");
	}else{
		$("#key").css("display", "");
		$("#url").css("display", "none");
	}
}
/*function DeleteluckUser() {

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

}*/
function EditChilMenu(){
	var dd=$("#parentId").val();
	var selected = $('#pageTt').datagrid('getSelected');
	if(selected){
		$("#ff1").css("visibility", "visible");
		$("#update2").css("visibility", "visible");
		$('#ff1').show();
		$('#ff1').form('clear');
		$('#ff1').appendTo('#update12');
		$('#update2').window('open');
		$('#menuNum').html(selected.menuNum);
		$('#menuName').val(selected.menuName);
		$("#type").css("display", "");
		$('#menuType').val(selected.menuType);
		var type=selected.menuType;
		if(type=="view"){
			$("#key").css("display", "none");
			$("#url").css("display", "");
			$('#menuUrl').val(selected.menuUrl);
		}if(type=="click"){
			$("#key").css("display", "");
			$("#url").css("display", "none");
			$('#menuKey').val(selected.menuKey);
		}
		
	}else{
		$.messager.alert('消息',"请选择子菜单!");
	}


}

function updateChilMenu(){
	var selected = $('#pageTt').datagrid('getSelected');
	$.ajax({
		type:"post",
		url:"../site/manage-menu!updateChilMenu",
		data : {
			'menuName' : $("#menuName").val(),
			'menuId':selected.menuId,
			'menuParentId' : $("#parentId").val(),
			'menuType' : $("#menuType").val(),
			'menuUrl' : $("#menuUrl").val(),
			'menuKey' : $("#menuKey").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"修改成功!");
			$('#pageTt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息',"修改失败!");
			$('#pageTt').datagrid('reload');
			close1();
		}
	});

}

function Deletemenu(){
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
				url : "../site/manage-menu!deleMenu",
				data : {
					'menuIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"删除成功!");
					$('#tt').datagrid('reload');
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
function DeleChilMenu(){

	var selected = $('#pageTt').datagrid('getSelected');
	$.ajax({
		type:"post",
		url:"../site/manage-menu!deleteChilMenu",
		data : {
			'menuId':selected.menuId
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"删除成功!");
			$('#pageTt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息',"删除失败!");
			$('#pageTt').datagrid('reload');
			close1();
		}
	});


}


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
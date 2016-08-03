$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '企业信息',
						//iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-pay!getYList',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [
						     
									{
									title : '用户名',
									field : 'userName',
									width : '130',
									align : 'center',
									visibility : 'hidden'
								},
								
								{
									title : '企业名称',
									field : 'enterName',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title :'商户号',
									field : 'merId',
									width : '100',
									align : 'center',
									visibility : 'hidden'
									
								},
								{
									title :'币种',
									field : 'curyId',
									width : '80',
									align : 'center',
									visibility : 'hidden'
									
								},
								{
									title :'交易类型',
									field : 'transtType',
									width : '150',
									align : 'center',
									visibility : 'hidden'
									
								},
								{
									title :'版本号',
									field : 'version',
									width : '150',
									align : 'center',
									visibility : 'hidden'
									
								},{
									title :'支付网关号',
									field : 'gateId',
									width : '150',
									align : 'center',
									visibility : 'hidden'
								
								}
								,{
									title :'商户私有域',
									field : 'privl',
									width : '150',
									align : 'center',
									visibility : 'hidden'
									
								}
								
								 ] ],
						/*onClickRow : function(value, rec) {
							var str = $("#option").html();
							if (s_caocuo == "true") {
								str = $("#option").html().replace(
										"id=\"caozuo\"",
										"id=\"caozuo\" checked=\"checked\"");
							} else {
								str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
							}
							$("#option").html(str);
						},*/
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
		$("#enterName").val(select.enterName);
		$("#merId").val(select.merId);
		$("#curyId").val(select.curyId);
		$("#transtType").val(select.transtType);
		$("#version").val(select.version);
		$("#gateId").val(select.gateId);
		$("#privl").val(select.privl);
		//alert(select.enterInforId);
		$("#enterId").val(select.enterInforId);
		$("#address").val(select.enterAddress);
		$("#appId").val(select.appId);
		$("#appSecret").val(select.appSecret);
		$("#addUser").appendTo('#update');
	} else {
		$.messager.alert('消息', "请选择一行");
	}
}
function update2(){
	var opt = {
				url:'../site/manage-pay!update',
//          	    beforeSubmit:showStart,
          	    success:function(text) {
				//	alert(text);
          	    	$('#tt').datagrid('reload');
				},
				error : function(text) {
				//	alert(text);
					$('#tt').datagrid('reload');
				}
			};
	
		$("#addUser").ajaxSubmit(opt);
}
function callback(messages, success) {
	//alert(messages+"dddd");
	$('#tt').datagrid('reload');
	alert(messages);
	close1();
	
}
function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-pay!update",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '修改成功!');
			close1();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息','修改失败!');
			close1();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}

function close1(){
	
	$("#updateuser").window("close");
}
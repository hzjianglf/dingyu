$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt')
			.datagrid(
					{
						title : '客户管理',
						iconCls : 'icon-save',
						fitColumns : true,
						width : '100%',
						height : '100%',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-customers',
						loadMsg : '数据装载中......',
						height : "auto",
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [
						{
							field : 'Opt',
						title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
						width : 40,
						align : 'center',
						formatter : function(value, rec) {
							return "<input type=\"checkbox\" name=\"xuanze\" value="
									+ rec.customersId + "  />";
							}
						} ,
								{
									title : '编号',
									field : 'customersNo',
									width : '70',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '客户名称',
									field : 'customersName',
									width : '150',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '行业',
									field : 'industryName',
									width : '90',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '区域',
									field : 'areaName',
									width : '80',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '行政区域',
									field : 'cantonName',
									width : '100',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '客户地址',
									field : 'customersAddress',
									width : '200',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '规格型号',
									field : 'model',
									width : '100',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '数量',
									field : 'count',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '说明',
									field : 'introduce',
									width : '200',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '经度',
									field : 'locationY',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '纬度',
									field : 'locationX',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
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
								getSelect();
							}
						},
						onHeaderContextMenu:function(){
							alert("111111111111111");
						},
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
	showIndustry();
	showArea();
	showCanton();
});

function judgeQuery() {
	$('#tt')
			.datagrid(
					{
						url : '../site/manage-customers?industry='
								+ $("#industry").combobox("getValue")
								+ "&area=" + $("#area").combobox("getValue")
								+ "&canton="
								+ $("#canton").combobox("getValue"),

						queryParams : {
						// inforTitle : 1,
						// inforState : $("#inforState").combobox("getValue"),
						},
						columns : [ [
						             {
							field : 'Opt',
							title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
							width : 40,
							align : 'center',
							formatter : function(value, rec) {
								return "<input type=\"checkbox\" name=\"xuanze\" value="
										+ rec.customersId + "  />";
							}
						} ,
								{
									title : '编号',
									field : 'customersNo',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '客户名称',
									field : 'customersName',
									width : '150',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '行业',
									field : 'industryName',
									width : '90',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '区域',
									field : 'areaName',
									width : '100',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '行政区域',
									field : 'cantonName',
									width : '100',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '客户地址',
									field : 'customersAddress',
									width : '200',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '规格型号',
									field : 'model',
									width : '100',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '数量',
									field : 'count',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '说明',
									field : 'introduce',
									width : '200',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '经度',
									field : 'locationY',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								},
								{
									title : '纬度',
									field : 'locationX',
									width : '50',
									align : 'center',
									visibility : 'hidden',
									sortable:true
								}
								] ]
					});
	displayMsg();
}
function showIndustry() {
	//var select = $('#tt').datagrid('getSelected');
	$('#industry').combobox({
		url : '../site/manage-customers!showIndustry',
		valueField : 'id',
		textField : 'text'
	});
	//var industryName = select.customersId;
	$('#industryId').combobox({
		type:'post',
		url : '../site/manage-customers!showIndustry',
		valueField : 'id',
		textField : 'text'
	});
}
function showArea() {
	//var select = $('#tt').datagrid('getSelected');
	$('#area').combobox({
		url : '../site/manage-customers!showArea',
		valueField : 'id',
		textField : 'text'
	});
	$('#areaId').combobox({
		url : '../site/manage-customers!showArea',
		valueField : 'id',
		textField : 'text'
	});

}
function showCanton() {
	//var select = $('#tt').datagrid('getSelected');
	$('#canton').combobox({
		url : '../site/manage-customers!showCanton',
		valueField : 'id',
		textField : 'text'
	});
	$('#cantonId').combobox({
		url : '../site/manage-customers!showCanton',
		valueField : 'id',
		textField : 'text'
	});

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

var id = "";

function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		
		id = select.customersId;
		$("#edit").css("visibility", "visible");
		$("#customer").css("visibility", "visible");
		$('#edit').window('open');
		$('#customer').appendTo('#ee');
		$("#customer_name").html(select.customersName);
		$("#customer_industry").html(select.industryName);
		$("#customer_area").html(select.areaName);
		$("#customer_canton").html(select.cantonName);
		$("#customer_model").html(select.model);
		$("#customer_count").html(select.count);
		$("#location_x").html(select.locationX);
		$("#location_y").html(select.locationY);
		$("#customer_no").html(select.customersNo);
		$("#customer_address").html(select.customersAddress);
		$("#customer_introduce").html(select.introduce);
		$("#customer_image").html(select.customersImage);
	}
}
function UpdateCustomers() {
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
	
	if (ids) {
	var select = $('#tt').datagrid('getSelected');
		id = select.customersId;
		$("#updatecustomers").css("visibility", "visible");
		$('#updatecustomers').window('open');
		$('#addCustomers').form('clear');
		$("#addCustomers").css("visibility", "visible");
		$('#addCustomers').show();
		$("#customersName").val(select.customersName);
		/*$("#customersName").val(select.customersName);
		cantonName=select.cantonName;
		alert(cantonName);
		  
		*/
		$("#customersCount").val(select.count);
		$("#locationx").val(select.locationX);
		$("#locationy").val(select.locationY);
		$("#customersId").val(id);
		$("#customersAddress").val(select.customersAddress);
		$("#customersIntroduce").val(select.introduce);
		$("#customersNo").val(select.customersNo);
		$("#customersModel").val(select.model);
		$('#addCustomers').appendTo('#update');
		showIndustry();
		showArea();
		showCanton();
		
		$('#area').combobox('select', select.areaId); 
		$('#areaId').combobox('select', select.areaId); 
		$('#canton').combobox('select', select.cantonId); 
		$('#cantonId').combobox('select', select.cantonId); 
		$('#industry').combobox('select', select.industryId); 
		$('#industryId').combobox('select', select.industryId); 
		
		
	} else {
		$.messager.alert('消息', "请选中一条信息~");
	}
}
function AddCustomers() {

	$("#addcustomers").css("visibility", "visible");
	$('#addcustomers').window('open');
	$('#addCustomers').form('clear');
	$("#addCustomers").css("visibility", "visible");
	$('#addCustomers').show();
	$('#addCustomers').appendTo('#eeXL');
	showIndustry();
	showArea();
	showCanton();
}

function add() {
	var r = /^\+?[0-9][0-9]*$/;
	if (!(r.test($("#customersCount").val()))) {
		$.messager.alert('消息', "请正确填写数量!");
		return;
	}
	
	if ($("#industryId").combobox("getValue") == "-1"
			|| $("#areaId").combobox("getValue") == "-1"
			|| $("#customersName").val() == ""
			|| $("#customersName").val() == ""||$("#customersNo").val()=="") {
		$.messager.alert('消息', "请完善资料!");
	} else {
		var opt = {
				url:"/wxpt/site/manage-customers!add",
				
				success : function(text) {
					$.messager.alert('消息', text);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('消息', " 系统繁忙，请稍后再试...");
				}
		};
		$("#addCustomers").ajaxSubmit(opt);
		
		/*$.ajax({
			url : "../../site/manage/manage-customers!add",
			type : 'post',
			data : '' + $("#addCustomers").serialize(),
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

		});*/
	}
}

function update() {
	
	var r = /^\+?[0-9][0-9]*$/;
	if (!(r.test($("#customersCount").val()))) {
		$.messager.alert('消息', "请正确填写数量!");
		return;
	}
	
	if ($("#industryId").combobox("getValue") == "-1"
			|| $("#areaId").combobox("getValue") == "-1"
			|| $("#customersName").val() == ""
			|| $("#customersName").val() == ""||$("#customersNo").val()=="") {
		$.messager.alert('消息', "请完善资料!");
	} else {
	var a = $("#industryId").combobox("getValue");
	var b =$("#cantonId").combobox("getValue");
	var c =$("#areaId").combobox("getValue");
		var opt = {
				url:"../site/manage-customers!update?industryId="+a+"&cantonId="+b+"&areaId="+c+"",
				success : function(text) {
					$.messager.alert('消息', text);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('消息', " 系统繁忙，请稍后再试...");
				}
		};
		$("#addCustomers").ajaxSubmit(opt);
		/*$.ajax({
			url : "../../site/manage/manage-customers!update",
			type : 'post',
			data : '' + $("#addCustomers").serialize(),
			dataType : "json",
			success : function() {
				$.messager.alert('消息', '修改成功!');
				close();
				$('#tt').datagrid('reload');
				close1();
			},
			error : function() {
				$.messager.alert('消息', '修改失败!');
				close();
				$('#tt').datagrid('reload');
				close1();

			}

		});*/
	}
}

function close1() {
	$('#addcustomers').window('close');
	$('#add').window('close');
	$('#updatecustomers').window('close');
}
function callback(message, success) {
	$.messager.alert('消息', message);
	close();
	$('#tt').datagrid('reload');
	close1();
	
}

function DeleteCustomers() {

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
				url : "../site/manage-customers!delete",
				data : {
					'customersIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功！");
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', "删除失败!");
					$('#tt').datagrid('reload');
					//window.location.reload();
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
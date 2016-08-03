$(function() {
	$("#toolbar").css("visibility", "visible");

	$('#tt')
			.datagrid(
					{
						onRowContextMenu : onRowContextMenu,
						title : '栏目设置',
						iconCls : 'icon-save',
						fitColumns : false,
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-greeting-card',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						onRowContextMenu : onRowContextMenu,
						remoteSort : false,
						columns : [ [
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 50,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.cardId + "  />";
									}
								}, {
									title : '模板名',
									field : 'cardTypeName',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '名称',
									field : 'cardName',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '祝福语',
									field : 'cardValue',
									width : '340',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '背景图片',
									field : 'cardBgImage',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '文字背景',
									field : 'cardTxtImage',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '分享图片',
									field : 'cardTitleImage',
									width : '120',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '是否默认',
									field : 'cardTypeStateStr',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								} ] ],
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
/**
 * 查看所有类型
 */
function cardTypeEdit() {
	// queryCardType();
	queryTemplate();
	$("#card_tt").css("visibility", "visible");
	$("#card_toolbar").css("visibility", "visible");
	$("#cardType").css("visibility", "visible");
	$('#cardType').window('open');
}
function queryTemplate() {
	$('#card_tt').datagrid({
		onRowContextMenu : onRowContextMenu,

		iconCls : 'icon-save',
		fitColumns : false,
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-greeting-card!cardTemplateEdit',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		onRowContextMenu : onRowContextMenu,
		remoteSort : false,
		columns : [ [ {
			title : '模板编号',
			field : 'cardTemplateName',
			width : '150',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '是否默认',
			field : 'stateStr',
			width : '150',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '模板',
			field : 'cardTemplatePath',
			width : '300',
			align : 'center',
			visibility : 'hidden'
		} ] ],
		rownumbers : true,
		onDblClickRow : function() {
			var selected = $('#card_tt').datagrid('getSelected');
			if (selected) {
				// window.open("DataView.action?Id="+selected.ID);
				judgeGetSelected(selected);
			}
		},
		toolbar : "#card_toolbar"
	});
	displayCardTypeMsg();
}
function judgeGetSelected(select) {
	$('#cardType').window('close');
	$("#cardTmplateName").val(select.cardTemplateName);
	$("#cardTemplateId").val(select.cardTemplateId);
}

function queryCardType() {
	$('#card_tt').datagrid({
		onRowContextMenu : onRowContextMenu,

		iconCls : 'icon-save',
		fitColumns : false,
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-greeting-card!cardTypeEdit',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		onRowContextMenu : onRowContextMenu,
		remoteSort : false,
		columns : [ [ {
			title : '类型名',
			field : 'cardTypeName',
			width : '300',
			align : 'center',
			visibility : 'hidden'
		}, {
			title : '是否默认',
			field : 'stateStr',
			width : '300',
			align : 'center',
			visibility : 'hidden'
		} ] ],
		rownumbers : true,

		toolbar : "#card_toolbar"
	});
	displayCardTypeMsg();
}

function displayCardTypeMsg() {

	$('#card_tt')
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

// 添加类型
function addCardType() {
	$("#cardTypeForm").css("visibility", "visible");
	$("#addCardType").css("visibility", "visible");
	$('#addCardType').window('open');
	$('#cardTypeForm').form('clear');
	$("#cardTypeForm").show();
	$("#cardTypeForm").appendTo('#eeXL');
}

function add() {
	if ($("#cardTypeName").val() == "") {
		$.messager.alert("消息", "请填写类型名称");
	} else {
		$.ajax({
			url : "../site/manage-greeting-card!addCardType",
			type : "post",
			datatype : "json",
			data : {
				"cardTypeName" : $("#cardTypeName").val()
			},
			success : function(date) {
				var str = eval(date);
				if (str.state != 0) {
					$.messager.alert("消息", "添加成功");
					closeWindow("addCardType");
					$("#card_tt").datagrid('reload');
				} else {
					$.messager.alert("消息", "添加失败 ");
				}
			},
			error : function(date) {
				$.messager.alert("消息", "网络异常");
			}
		});
	}
}
/**
 * 打开修改对话框
 */
function updateCardType() {
	var select = $('#card_tt').datagrid('getSelected');
	if (select) {
		$("#cardTypeForm").css("visibility", "visible");
		$("#updateCardType").css("visibility", "visible");
		$('#updateCardType').window('open');
		$('#cardTypeForm').form('clear');
		$("#cardTypeName").val(select.cardTypeName);
		$("#cardTypeId").val(select.cardTypeId);
		$("#cardTypeForm").show();
		$("#cardTypeForm").appendTo('#updateeeXL');
	} else {
		$.messager.alert("消息", "请选择修改的数据");
	}
}
/**
 * 
 */
function update() {
	if ($("#cardTypeName").val() == "") {
		$.messager.alert("消息", "类型不能为空");
	}
	$.ajax({
		url : "../site/manage-greeting-card!updateCardType",
		type : "post",
		datatype : "json",
		data : '' + $("#cardTypeForm").serialize(),
		success : function(date) {
			var str = eval(date);
			if (str.state == 0) {
				$.messager.alert("消息", "修改失败");
			} else {
				closeWindow("updateCardType");
				$("#card_tt").datagrid('reload');
				$("#tt").datagrid('reload');
				$.messager.alert("消息", "修改成功");
			}
		},
		error : function() {
			$.messager.alert("消息", "网络异常");
		}
	});
}
/**
 * 删除类型
 */
function deleteCardType() {
	var select = $('#card_tt').datagrid('getSelected');
	if (select) {
		$.messager.confirm('消息', '确认删除么?', function(id) {
			if (id == false) {
				return;
			}
			$.ajax({
				url : "../site/manage-greeting-card!deleteCardType",
				type : "post",
				datatype : "json",
				data : {
					"cardTypeId" : select.cardTypeId
				},
				success : function(date) {
					var str = eval(date);
					if (str.state == 0) {
						$.messager.alert("消息", "删除失败");
					} else {
						$("#card_tt").datagrid('reload');
						$.messager.alert("消息", "删除成功");
						$("#tt").datagrid('reload');
					}
				},
				error : function() {
					$.messager.alert("消息", "网络异常");
				}
			});
		});
	} else {
		$.messager.alert("消息", "请选择删除数据");
	}
}

/**
 * 添加贺卡显示
 */

function addCard() {
	// editCardType();
	$("#cardForm").css("visibility", "visible");
	$("#addCard").css("visibility", "visible");
	$('#addCard').window('open');
	$('#cardForm').form('clear');
	//document.getElementById("idmetaValue").innerHTML = "\<textarea id='cardValue' name='cardValue' cols='50' rows='10' class='ww'\>\</textarea\>";
	$("#cardForm").show();
	$("#cardForm").appendTo('#addCardEeXL');
	//cardValue();
}
/**
 * 获取贺卡类型
 */
function editCardType() {
	$("#cardTypeID").combobox({
		url : "../site/manage-greeting-card!editCardType",
		valueField : "cardTypeId",
		textField : "cardTypeName",
		editable : false,
		missingMessage : '请选择分类！！',
		listHeight : '15xp'
	});
}
/**
 * 添加贺卡
 */
function AddCard() {
	if ($("#cardValue").val() == "" || $("#cardName").val() == "") {
		$.messager.alert("消息", "默认内容、名称不能为空");
	} else {
		$("#cardId").val(0);
		var opt = {
			url : "../site/manage-greeting-card!addCard",
			success : function(text) {
				closeWindow("addCard");
				$.messager.alert('消息', text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeWindow("addCard");
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#cardForm").ajaxSubmit(opt);
	}
}
function cardAdd() {
	$.ajax({
		url : "../site/manage-greeting-card!checkCard",
		type : "post",
		datatype : "json",
		data : {
			"cardTypeId" : $('#cardTypeID').combobox('getValue')
		},
		success : function(date) {
			var str = eval(date);
			if (str.state != 0) {
				$.messager.alert("消息", "当前分类下只能修改");
			} else {
				cardAdd();
			}
		},
		error : function() {
			$.messager.alert("消息", "网络异常");
		}
	});
}
/**
 * 显示修改窗口
 */
function updateCard() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		//editCardType();
		$("#cardForm").css("visibility", "visible");
		$("#updateCard").css("visibility", "visible");
		$('#updateCard').window('open');
		$('#cardForm').form('clear');
		//document.getElementById("idmetaValue").innerHTML = "\<textarea id='cardValue' name='cardValue' cols='50' rows='10' class='ww'\>\</textarea\>";
		$("#cardValue").val(select.cardValue);
		$('#cardTypeID').combobox('setValue', select.cardTypeId);
		$("#cardId").val(select.cardId);
		$("#cardTmplateName").val(select.cardTypeName);
		$("#cardTemplateId").val(select.cardTypeId);
		$("#cardName").val(select.cardName);
		$("#cardForm").show();
		$("#cardForm").appendTo('#updateCardEeXL');
		//cardValue();
		$('#cardTypeID').combobox('disable');
	} else {
		$.messager.alert("消息", "请选择要修改的数据");
	}
}
/**
 * 修改
 */
function cardUpdate() {
	if ($("#cardValue").val() == "") {
		$.messager.alert("消息", "请填写内容");
		return;
	}
	var opt = {
		url : "../site/manage-greeting-card!updateCard",
		success : function(text) {
			closeWindow("updateCard");
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeWindow("updateCard");
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#cardForm").ajaxSubmit(opt);
}
/**
 * 删除贺卡
 */
function deleteCard() {
	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			if (i + 1 == checks.length) {
				ids += checks[i].value;
			} else {
				ids += checks[i].value + ",";
			}

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
				url : "../site/manage-greeting-card!deleteCard",
				data : {
					'cardIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					alert("删除成功!");
					$('#tt').datagrid('reload');
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
				}
			});
		}
		$('#tt').datagrid('reload');
	});
}

/**
 * 设置默认
 */
function seeMenu() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$.ajax({
			url : "../site/manage-greeting-card!sehZhi",
			type : "post",
			data : {
				"cardId" : select.cardId
			},
			datatype : "json",
			success : function(date) {
				var str = eval(date);
				if (str.state == 0) {
					$.messager.alert("消息", "设置失败");
				} else {
					$.messager.alert("消息", "设置成功");
				}
				$('#tt').datagrid('reload');
			},
			error : function() {
				$.messager.alert("消息", "网络异常");
			}
		});
	} else {
		$.messager.alert("消息", "请选择要修改的数据");
	}
}
/**
 * 查看模板
 */
function lookCard() {
	
	getUrl();
}
function getUrl() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$("#cardUrl").css("visibility", "visible");
		$('#cardUrl').window('open');
		$('#cardUrlForm').form('clear');
		$("#cardUrlForm").css("visibility", "visible");
		$("#cardUrlForm").show();
		$("#cardUrlForm").appendTo('#eeXL1');
		$.ajax({
			type : "post",
			url : "../site/manage-website!getCookie",
			datatype : "json",
			success : function(date) {
				var str = eval(date);
				var urlStr = window.location.href;
				var url = urlStr.split("\/");// <a target=\"_blank\"
												// href=\""+url[0]+"//"+url[2]+"/wxpt/site/web/template/index!index?enterID="+str.enterID+"
												// \">
				$("#urlStr").html(
						"微贺卡访问路径：</br><span id=\"enterID\">" + url[0] + "//"
								+ url[2]
								+ "/wxpt/site/web/template/card?enterID="
								+ str.enterID + "&cardId=" + select.cardId
								+ "</span>");
			},
			error : function() {
				$.messager.alert("警告", "网络异常!");
			}
		});
	} else {
		$.messager.alert("消息", "请选择要查看的数据");
	}
}

function seeErrorUrl(){
	$("#errorUrl").css("visibility", "visible");
	$('#errorUrl').window('open');
	$('#errorUrlForm').form('clear');
	$("#errorUrlForm").css("visibility", "visible");
	$("#errorUrlForm").show();
	$("#errorUrlForm").appendTo('#errorEEXL');
	$.ajax({
		type : "post",
		url : "../site/manage-greeting-card!getProterty",
		datatype : "json",
		success : function(date) {
			var str = eval(date);
			$("#errorUrlStr").val(str.url);
		},
		error : function() {
			$("#errorUrlStr").val("http://");
		}
	});
}

function errorUrl(){
	$.ajax({
		type : "post",
		url : "../site/manage-greeting-card!udpateProperty",
		datatype : "json",
		data : {
			"errorUrlStr" : $("#errorUrlStr").val()
		},
		success : function(date) {
			var str = eval(date);
			if(str.state==0){
				$.messager.alert("消息", "修改失败");
			}else{
				$.messager.alert("消息", "修改成功");
			}
		},
		error : function() {
			$.messager.alert("消息", "请选择要查看的数据");
		}
	});
}

/**
 * 关闭添加增加类型界面
 */
function closeWindow(name) {
	$("#" + name).window("close");
}
function cardValue() {
	new tqEditor('cardValue', {
		toolbar : 'full'
	});
}
function callback(message, success) {
	closeWindow("addCard");
	closeWindow("updateCard");
	$.messager.alert("消息", message);
	$("#tt").datagrid('reload');
}
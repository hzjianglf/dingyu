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
						url : '../site/manage-page',
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
												+ rec.pageId + "  />";
									}
								}, {
									title : '标题',
									field : 'pageTitle',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '作者',
									field : 'pageAuthor',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '平台名称',
									field : 'pageNoName',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '时间',
									field : 'pageTime',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '文章配图',
									field : 'pageImage',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '概述',
									field : 'pageMetaValue',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '文章内容',
									field : 'pageContent',
									width : '420',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '原文链接',
									field : 'pageUrl',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '跳转链接',
									field : 'pageJumpUrl',
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
 * 显示添加文章对话框
 */

function add() {
	// editCardType();
	$("#cardForm").css("visibility", "visible");
	$("#addCard").css("visibility", "visible");
	$('#addCard').window('open');
	$('#cardForm').form('clear');
	$("#pageUrl").val("http://");
	$("#pageJumpUrl").val("http://");
	document.getElementById("idmetaValue").innerHTML = "\<textarea id='pageContent' name='pageContent' cols='50' rows='10' class='ww'\>\</textarea\>";
	$("#cardForm").show();
	$("#cardForm").appendTo('#addCardEeXL');
	kindEditor();
}


/**
 * 添加文章
 */
function AddPage1(){
	if($("#pageTitle").val()==""||$("#pageNoName").val()==""||$("#pageMetaValue").val()==""||$("#pageContent").val()==""||$("#pageImageFile").val()==""){
		$.messager.alert("消息","标题、平台名称、概述、内容、配图请填写完整");
		return;
	}
	var opt = {
			url : "../site/manage-page!addPage",
			success : function(text) {
				closeWindow("addCard");
				$.messager.alert('消息',"修改成功");
				$("#tt").datagrid('reload');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeUpdatePage();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#cardForm").ajaxSubmit(opt);
	
}
function AddPage(){
	if($("#pageJumpUrl").val()==""){
		$.messager.alert("消息","请填写分享链接");
		return;
	}
	var opt = {
			url : "../site/manage-page!addPage",
			success : function(text) {
				closeWindow("addCard");
				$.messager.alert('消息',"修改成功");
				$("#tt").datagrid('reload');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeUpdatePage();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#cardForm").ajaxSubmit(opt);
	
}

/**
 * 显示修改窗口
 */
function update() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$("#cardForm").css("visibility", "visible");
		$("#updateCard").css("visibility", "visible");
		$('#updateCard').window('open');
		$('#cardForm').form('clear');
		document.getElementById("idmetaValue").innerHTML = "\<textarea id='pageContent' name='pageContent' cols='50' rows='10' class='ww'\>\</textarea\>";
		$("#pageContent").val(select.pageContent);
		$("#pageId").val(select.pageId);
		$("#pageTitle").val(select.pageTitle);
		$("#pageAutor").val(select.pageAutor);
		$("#pageNoName").val(select.pageNoName);
		$("#pageUrl").val(select.pageUrl);
		$("#pageJumpUrl").val(select.pageJumpUrl);
		$("#pageMetaValue").val(select.pageMetaValue);
		$("#cardForm").show();
		$("#cardForm").appendTo('#updateCardEeXL');
		kindEditor();
	} else {
		$.messager.alert("消息", "请选择要修改的数据");
	}
}
/**
 * 修改
 */
function cardUpdate() {
	if($("#pageJumpUrl").val()==""){
		$.messager.alert("消息","请填写分享链接");
		return;
	}
	var opt = {
		url : "../site/manage-page!update",
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
function cardUpdate1() {
	if($("#pageTitle").val()==""||$("#pageNoName").val()==""||$("#pageMetaValue").val()==""||$("#pageContent").val()==""){
		$.messager.alert("消息","标题、平台名称、概述、内容、配图请填写完整");
		return;
	}
	var opt = {
		url : "../site/manage-page!update",
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
 * 删除
 */
function deletePage() {
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
				url : "../site/manage-page!delete",
				data : {
					'pageIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"删除成功!");
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
						"访问路径：</br><span id=\"enterID\">" + url[0] + "//"
								+ url[2]
								+ "/wxpt/site/web/template/page?enterID="
								+ str.enterID + "&pageId=" + select.pageId
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



function callback(message, success) {
	$("#addCard").window('close');
	$("#updateCard").window('close');
	$.messager.alert('消息', message);
	$("#tt").datagrid('reload');
}
function closeWindow(name){
	$("#"+name).window('close');
}

function kindEditor() {
	var editor = KindEditor.create('textarea[name="pageContent"]',{
		cssPath : '/wxpt/manager/js/kindeditor/plugins/code/prettify.css',
		uploadJson : '/wxpt/manager/js/kindeditor/jsp/upload_json.jsp',
        fileManagerJson : '/wxpt/manager/js/kindeditor/jsp/file_manager_json.jsp',
        allowFileManager : true,
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
}


function kindEditor2() {
	var editor = KindEditor.create('textarea[name="menuName"]',{
	
		uploadJson : '/wxpt/manager/js/kindeditor/jsp/upload_json.jsp',
        fileManagerJson : '/wxpt/manager/js/kindeditor/jsp/file_manager_json.jsp',
        allowFileManager : true,
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
}


$(function() {
	$("#ff").css("visibility", "visible");
	$("#toolbar").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid({

		title : '六格转盘图片上传',
		iconCls : 'icon-save',
		fit : true,
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '/wxpt/site/manage-prize!queryImage',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu:onRowContextMenu,
		columns : [ [ {
			title : '图片',
			field : 'imageTemp',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '上传时间',
			field : 'uploadTime',
			width : 400,
			rowspan : 2,
			align : 'center'
		} ] ],
		rownumbers : true,
		toolbar : "#toolbar"
	});

});
$(function() {
	$("#ff1").css("visibility", "visible");
	$('#ff1').hide();
	$('#tt1').datagrid({

		title : '四格转盘图片上传',
		iconCls : 'icon-save',
		fit : true,
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '/wxpt/site/manage-prize!queryImage1',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu:onRowContextMenu,
		columns : [ [ {
			title : '图片',
			field : 'imageTemp',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '上传时间',
			field : 'uploadTime',
			width : 400,
			rowspan : 2,
			align : 'center'
		} ] ],
		rownumbers : true,
		toolbar : "#toolbar1"
	});

});
$(function() {
	$("#ff2").css("visibility", "visible");
	$('#ff2').hide();
	$("#toolbar2").css("visibility", "visible");
	$('#tt2').datagrid({

		title : '三格转盘图片上传',
		iconCls : 'icon-save',
		fit : true,
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '/wxpt/site/manage-prize!queryImage2',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu:onRowContextMenu,
		columns : [ [ {
			title : '图片',
			field : 'imageTemp',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '上传时间',
			field : 'uploadTime',
			width : 400,
			rowspan : 2,
			align : 'center'
		} ] ],
		rownumbers : true,
		toolbar : "#toolbar2"
	});

});

function close1() {
	$('#edit').window('close');
	$('#edit1').window('close');
	$('#edit2').window('close');
}
function close2() {

	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	alert("修改成功");
	// $.messager.alert('warning','修改成功','warning');
	$('#p').progressbar('setValue', 100);
	// alert("close");
	// $('#edit').window('close');
	$('#tt').datagrid('reload');
	window.location.reload();
}

function judgeGetSelected() {

	// 判断是否有此权限
	var userName = $.cookie('uniqyw');
	var privilegeId = 24;// 需传入
	$.ajax({
		type : "POST",
		url : '../../site/manage/parent!judgePrivilege',
		data : {
			'userName' : userName,
			'privilegeId' : privilegeId
		},
		dataType : "text",
		success : function(data) {
			var MyCookie = $.cookie('canClick');
			// alert(MyCookie);
			// alert(MyCookie);
			if (MyCookie == "1") {

				getSelect();
			} else {
				alert("你没有此权限");
			}

		}
	});

}
function getSelect() {
	$("#tt").datagrid("selectRow", 0);
	var select = $('#tt').datagrid('getSelected');
	$('#edit').window('open');
	$('#ff').show();
	$("#edit").css("visibility", "visible");
	$('#ff').appendTo('#ee');
	/*$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);*/
}
function getSelect1() {
	$("#tt1").datagrid("selectRow", 0);
	var select = $('#tt1').datagrid('getSelected');
	$('#edit1').window('open');
	$('#ff1').show();
	$("#edit1").css("visibility", "visible");
	$('#ff1').appendTo('#ee1');
	/*$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);*/
}

function getSelect2() {
	$("#tt2").datagrid("selectRow", 0);
	var select = $('#tt2').datagrid('getSelected');
	$('#edit2').window('open');
	$('#ff2').show();
	$("#edit2").css("visibility", "visible");
	$('#ff2').appendTo('#ee2');
	/*$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);*/
}
function add() {

	var opt = {
		url : "../site/manage-prize!getuploadImage",
		success : function(text) {
			$('#tt').datagrid('reload');
			window.location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff").ajaxSubmit(opt);
	$('#tt').datagrid('reload');
	close1();
	
	
}
function add1() {

	var opt = {
		url : "/wxpt/site/manage-prize!getuploadImage1",
		success : function(text) {
			$('#tt1').datagrid('reload');
			window.location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff1").ajaxSubmit(opt);
	$('#tt1').datagrid('reload');
	close1();
	
}

function add2() {

	var opt = {
		url : "../site/manage-prize!getuploadImage2",
		success : function(text) {
			$('#tt2').datagrid('reload');
			window.location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff2").ajaxSubmit(opt);
	$('#tt2').datagrid('reload');
	close1();
	
}




function seeImg() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#showImg').window('open');
		$('#img').show();
		$('#img').appendTo('#showImg');
		var url = select.advertisementImage;
		re = new RegExp("' width='80' height='35'/\>", "g");
		re1 = new RegExp("\<img src='", "g");
		var newurl = url.replace(re, " ");
		var newur2 = newurl.replace(re1, " ");
		document.getElementById("showImg").innerHTML = "\<img id='img1' src='"
				+ newur2 + "'/\>";
		// var imgA = document.getElementById("imga");
		// document.getElementById("img1").src = newur2;
		// imgA.src = newur2;
		// $('#imga').attr("src",newur2);
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function callback(message, success) {
	$.messager.alert('消息',message);
	close1();
	$('#tt').datagrid('reload');
	$('#tt2').datagrid('reload');
	$('#tt1').datagrid('reload');
	close1();
	/*close2();
*/	//window.location.reload();
}
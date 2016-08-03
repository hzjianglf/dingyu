//$(function() {
//	$("#ff").css("visibility", "visible");
//	$('#ff').hide();
//	$('#tt').datagrid({
//
//		title : '刮刮乐图片管理',
//		iconCls : 'icon-save',
//		fit : true,
//		nowrap : false,
//		pagination : true,
//		striped : true,
//		collapsible : true,
//		singleSelect : true,
//		url : '../site/manage-card!queryImage',
//		loadMsg : '数据装载中......',
//		sortName : 'code',
//		sortOrder : 'desc',
//		remoteSort : false,
//		columns : [ [ {
//			title : '图片',
//			field : 'imageTemp',
//			width : 400,
//			rowspan : 2,
//			align : 'center'
//		}, {
//			title : '图片类型',
//			field : 'cardTypeName',
//			width : 400,
//			rowspan : 2,
//			align : 'center'
//		} ,
//		
//		{
//			title : '图片数目',
//			field : 'cardCount',
//			width : 300,
//			rowspan : 2,
//			align : 'center'
//		} ,
//		
//		{
//			field : 'Opt',
//			title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
//			width : 40,
//			align : 'center',
//			formatter : function(value, rec) {
//				return "<input type=\"checkbox\" name=\"xuanze\" value="
//						+ rec.cardId + "  />";
//			}
//		}
//		
//		] ],
//		rownumbers : true,
//		onClickRow : function(value, rec) {
//			var str = $("#option").html();
//			if (s_caocuo == "true") {
//				str = $("#option").html().replace(
//						"id=\"caozuo\"",
//						"id=\"caozuo\" checked=\"checked\"");
//			} else {
//				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
//			}
//			$("#option").html(str);
//		},
//		onDblClickRow : function() {
//			var selected = $('#tt').datagrid('getSelected');
//			if (selected) {
//				// window.open("DataView.action?Id="+selected.ID);
//				UpdateQuestion();
//			}
//		},
//		toolbar : "#toolbar"
//	});
//	displayMsg();
//});
$(function() {

	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '关键字及对应回复',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-card2!queryImage2',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
								{
									title : '图片',
									field : 'imageTemp',
									width : '400',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '图片类型',
									field : 'cardTypeDesc',
									width : '400',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '图片数目',
									field : 'cardCount',
									width : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.cardId + "  />";
									}
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
function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 5,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}



function close1() {
	$('#edit').window('close');
	$('#edit1').window('close');
	$('#edit2').window('close');
	
}
var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	
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

/*中奖概率自定义设置开始*/
function closePrizeChance(){
	$('#editPrize').window('close');
}

function SetPrizeChance(){
	$.ajax({
		type : "POST",
		url : '../site/manage-card2!checkMoveTime',
		dataType : "text",
		success : function(data) {
			if(data==0){
				alert("活动已开启不可以再更改");
			}else{
				$('#editPrize').window('open');
				$('#ffPrize').show();
				$("#editPrize").css("visibility", "visible");
				$("#ffPrize").css("visibility", "visible");
				$('#ffPrize').appendTo('#eePrize');
				$("#prizeCountNum").val("0");
			}
		}
	});
	
	
	
}

function setChance(){
	var opt = {
		url : "../site/manage-card2!setPrizeChance",
		success : function(text) {
			alert("设置成功");
			$('#tt').datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ffPrize").ajaxSubmit(opt);
	closePrizeChance();
	
	

}

function countNum(){
	var a1=$("#onePrizeCount").val();
	
	var a2=$("#twoPrizeCount").val();
	var a3=$("#threePrizeCount").val();
	var a4=$("#fourPrizeCount").val();
	var a5=$("#fivePrizeCount").val();
	var a6=$("#sixPrizeCount").val();
	var a7=$("#sevenPrizeCount").val();
	var a8=$("#eightPrizeCount").val();
	var a9=$("#ninePrizeCount").val();
	var a10=$("#tenPrizeCount").val();
	var a0=Number(a1)+Number(a2)+Number(a3)+Number(a4)
	 +Number(a5)+Number(a6)+Number(a7)+Number(a8)+Number(a9)+Number(a10);
	$("#prizeCountNum").val(a0);
}
/*中奖概率自定义设置结束*/
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
	$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);
}
function getSelect1() {
	$("#tt1").datagrid("selectRow", 0);
	var select = $('#tt1').datagrid('getSelected');
	$('#edit1').window('open');
	$('#ff1').show();
	$("#edit1").css("visibility", "visible");
	$('#ff1').appendTo('#ee1');
	$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);
}

function getSelect2() {
	$("#tt2").datagrid("selectRow", 0);
	var select = $('#tt2').datagrid('getSelected');
	$('#edit2').window('open');
	$('#ff2').show();
	$("#edit2").css("visibility", "visible");
	$('#ff2').appendTo('#ee2');
	$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);
}

function add() {

	var opt = {
		url : "../site/manage-card2!getuploadImage",
		success : function(text) {
			
			$('#tt').datagrid('reload');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff").ajaxSubmit(opt);
	close1();
	$('#tt').datagrid('reload');
	
}

function add1() {

	var opt = {
		url : "../site/manage-card2!getuploadImage1",
		success : function(text) {
			$('#tt1').datagrid('reload');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff1").ajaxSubmit(opt);
	close1();
	$('#tt1').datagrid('reload');
	
}

function add2() {

	var opt = {
		url : "../site/manage-card2!getuploadImage2",
		success : function(text) {
			$('#tt2').datagrid('reload');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff2").ajaxSubmit(opt);
	close1();
	$('#tt2').datagrid('reload');
	
}



function callback(message, success) {
	alert("ffddfdf");
	alert(message);
	$('#edit').window('close');
	$('#add').window('close');
	$('#tt').datagrid('reload');
	window.location.reload();
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

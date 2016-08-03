$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt').datagrid({
		onDblClickRow : updateactivity,
		title : 'ZoneManagement',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		pageNumber : 1,
		nowrap : true,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/international-college!getInterColl',
		loadMsg : 'In the data load......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [[{
			title : 'ActivityTitle',
			field : 'collegeActivityTitle',
			width : 130,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ActivityTime',
			field : 'collegeActivityTime',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ActivityLocation',
			field : 'collegeActivityAdress',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ActivityContent',
			field : 'collegeActivityContent',
			width : 500,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'AddTime',
			field : 'collegeActivityAddTime',
			width : 200,
			rowspan : 2,
			align : 'center'
		}]],
		rownumbers : true,
		pagination : true,
		toolbar : "#toolbar"
	});
//	displayMsg();
});

function fixWidthTable(percent) {
	return $(window).width() * percent;
}
//function displayMsg() {
//	$('#tt').datagrid('getPager').pagination({
//		beforePageText : '第',// 页数文本框前显示的汉字
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
//}
function addActivity(){
	$.ajax({
		type : "POST",
		url : "../../site/manage/international-college!saveInterColl",
		data : {
			'collegeActivityTitle' : $("#title").val(),
			'collegeActivityTime' : $("#time").val(),
			'collegeActivityAdress' : $("#adress").val(),
			'collegeActivityContent' : $("#content").val()
		},
		dataType : "json",
		success : function(json) {
			if (json) {
				$.messager.alert("message", "Save Success！", "info");
				$('#tt').datagrid('reload');
				close1();
			} else {
				$.messager.alert("message", "Save Fail！", "info");
				$('#tt').datagrid('reload');
				close1();
			}
		}
	});
}
function updateactivity() {
	$("#update").css("visibility", "visible");
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#update').window('open');
		$('#activityForm').form('clear');
		$('#activityForm').show();
		$('#activityForm').appendTo('#infor1');
		 document.getElementById("idContent").innerHTML = 
			   "\<textarea id='content' name='content' style='width:600px; height:240px;' class='ww'\>\</textarea\>";
		$("#time").val(select.collegeActivityTime);
		$("#title").val(select.collegeActivityTitle);
		$("#adress").val(select.collegeActivityAdress);
		$("#content").val(select.collegeActivityContent);
		content();
	}else{
		$.messager.alert("message", "Please select a line！", "info");
	}
}
function openactivity() {
	$('#add').window('open');
	$('#activityForm').form('clear');
	$('#activityForm').show();
	$('#activityForm').appendTo('#infor');
	 document.getElementById("idContent").innerHTML = 
		   "\<textarea id='content' name='content' style='width:600px; height:240px;' class='ww'\>\</textarea\>";
	content();
}
function close1() {
	$('#add').window('close');
}
function close2() {
	$('#update').window('close');
}
function addinfor(){
	
}
function updateAct() {
	var select = $('#tt').datagrid('getSelected');
	var activityId = select.collegeActivityId;
	$.ajax({
		type : "POST",
		url : "../../site/manage/international-college!updateInterColl",
		data : {
			'collegeActivityId' : activityId,
			'collegeActivityTitle' : $("#title").val(),
			'collegeActivityTime' : $("#time").val(),
			'collegeActivityAdress' : $("#adress").val(),
			'collegeActivityContent' : $("#content").val()
		},
		dataType : "json",
		success : function(json) {
			if (json) {
				$.messager.alert("message", "Update Success！", "info");
				$('#tt').datagrid('reload');
				close2();
			}else{
				$.messager.alert("message", "Update Fail！", "info");
				$('#tt').datagrid('reload');
				close2();
			}
		}
	});
}
function delActivity() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('message', 'Confirm Delete?', function(r) {
			var collegeActivityId = selected.collegeActivityId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/international-college!deleteInterColl",
					data : "collegeActivityId="+collegeActivityId,
					dataType : "json",
					success : function(json) {
						if (json) {
							$.messager.alert("message", "Delete Success！", "info");
							$('#tt').datagrid('reload');
						} else {
							$.messager.alert("message", "Delete Fail！", "info");
							$('#tt').datagrid('reload');
						}
					}
				});
				$('#tt').datagrid('reload');
			}
		});
	} else {
		$.messager.alert('message', 'Please select a line!', 'info');
	}
}
function query() {
	$('#tt').datagrid({
		url :'../../site/manage/international-college!getInterColl',
		queryParams : {
			collegeActivityTitle : $("#seltitle").val(),
			collegeActivityAdress : $("#seladress").val(),
			collegeActivityContent : $("#selcontent").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
		}
	});
//	displayMsg();
}
function seeReserve(){
	  $("#ad").window('open');
}
$(function() {
	$('#ff1').hide();
	$('#tt1').datagrid({
//		onDblClickRow : see1,
		title : '',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/international-college!getReserve',
		loadMsg : 'In the data load......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : 'Name',
			field : 'collegeReserveName',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'Wechat',
			field : 'collegeReserveWeixin',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'Phone',
			field : 'collegeReservePhone',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ActivityTitle',
			field : 'collegeActivityTitle',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : 'ActivityTime',
			field : 'collegeActivityTime',
			width : 150,
			rowspan : 2,
			align : 'center'
		}]],
		pagination : true,
		rownumbers : true,
		toolbar:"#toolbar1"

	});
//	displayMsg1();
});
function queryReserve() {
	$('#tt1').datagrid({
		url :'../../site/manage/international-college!getReserve',
		queryParams : {
			collegeReserveName : $("#ReserveName").val(),
			collegeReserveWeixin : $("#ReserveWeixin").val(),
		}
	});
//	displayMsg1();
}
//function displayMsg1() {
//	$('#tt1').datagrid('getPager').pagination({
//		beforePageText : '第',// 页数文本框前显示的汉字
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
//}
function content(){
	new tqEditor(
			'content',
			{
				toolbar : 'full'
			});
}
$(function() {
	$("#toolbar").css("visibility", "visible");
	$('#tt').datagrid({
		onDblClickRow : updatetype,
		title : '信息发布管理',
		iconCls : 'icon-save',
		fit : true,
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		pageNumber : 1,
		nowrap : true,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../../site/manage/teach!getTeach',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [[{
			title : '姓名',
			field : 'teachName',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '职称',
			field : 'teachProfessional',
			width : 120,
			rowspan : 2,
			align : 'center'
		}, {
			title : '专业',
			field : 'majorName',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '图片',
			field : 'teachImage',
			width : 150,
			rowspan : 2,
			align : 'center'
		}, {
			title : '详情',
			field : 'teachDetail',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '添加修改时间',
			field : 'teachAddTime',
			width : 200,
			rowspan : 2,
			align : 'center'
		}]],
		rownumbers : true,
		pagination : true,
		toolbar : "#toolbar"
	});
	displayMsg();
});

function fixWidthTable(percent) {
	return $(window).width() * percent;
}
function displayMsg() {
	$('#tt').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function updatetype() {
	$("#update").css("visibility", "visible");
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#update').window('open');
		$('#Inforpub').form('clear');
		$('#Inforpub').show();
		$('#Inforpub').appendTo('#infor1');
		 document.getElementById("iddetail").innerHTML = 
			   "\<textarea id='detail' name='detail' style='width:670px; height:240px;' class='ww'\>\</textarea\>";
		typeName2();
		 var selectvalue = select.majorId;
		  $('#major').combobox('setValue',selectvalue);
		  var Field = select.majorName;
		  $("#teachId").val(select.teachId);
		  $("#teachName").val(select.teachName);
		  $("#profess").val(select.teachProfessional);
		  $("#detail").val(select.teachDetail);
		  detail();
	}else{
		$.messager.alert("提示", "请先选中一行数据！", "info");
	}
}
function opentype() {
	$('#add').window('open');
	$('#Inforpub').form('clear');
	$('#Inforpub').show();
	$('#Inforpub').appendTo('#infor');
	 document.getElementById("iddetail").innerHTML = 
		   "\<textarea id='detail' name='detail' style='width:670px; height:240px;' class='ww'\>\</textarea\>";
	 detail();
	typeName1();
}
function close1() {
	$('#add').window('close');
}
function close2() {
	$('#update').window('close');
}
function addinfor(){
	var opt = {
		url : "../../site/manage/teach!saveTeach",
		success : function(text) {
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	};
	$("#Inforpub").ajaxSubmit(opt);
	close1();
}
function callback(message, success) {
	alert(message);
	$('#edit').window('close');
	$('#add').window('close');
	$('#tt').datagrid('reload');
	window.location.reload();
}
function updateinfor(){
	var opt = {
			url : "../../site/manage/teach!updateTeach",
			success : function(text) {
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		};
		$("#Inforpub").ajaxSubmit(opt);
		close2();
}
function delInfor() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('提示', '确认删除吗?', function(r) {
			var inforId = selected.teachId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/teach!deleteTeach",
					data : "teachId=" + inforId,
					dataType : "json",
					success : function(json) {
						if (json) {
							$.messager.alert("提示", "删除成功！", "info");
							$('#tt').datagrid('reload');
						} else {
							$.messager.alert("提示", "删除失败，请稍后重试！", "info");
							$('#tt').datagrid('reload');
						}
					}
				});
				$('#tt').datagrid('reload');
			}
		});
	} else {
		$.messager.alert('提示', '请选择一行数据', 'warning');
	}
}
function typeName1(){
	  $("#major").combobox({
		     url:"../../site/manage/teach!getAllMajor",
					valueField:"majorId",
					textField:"majorName",
					editable:false,
		           missingMessage:'请选择组别！',
		            listHeight:'15xp',
		            	 onLoadSuccess: function () { //数据加载完毕事件
				            	var data = $('#major').combobox('getData');
		                        if (data.length > 0) {
		                   		 $("#major").combobox('select',data[0].majorId);
		                        }
		                    }
		}); 
}
function typeName2(){
	  $("#major").combobox({
		     url:"../../site/manage/teach!getAllMajor",
					valueField:"majorId",
					textField:"majorName",
					editable:false,
		           missingMessage:'请选择组别！',
		            listHeight:'15xp'
		}); 
}
function typeName3(){
	  $("#selmajor").combobox({
		     url:"../../site/manage/teach!getselMajor",
					valueField:"majorId",
					textField:"majorName",
					editable:false,
		           missingMessage:'请选择组别！',
		            listHeight:'15xp',
		            	 onLoadSuccess: function () { //数据加载完毕事件
		            		  $('#selmajor').combobox('setValue','全部');
		                    }
		}); 
}
function query() {
	var major = $("#selmajor").combobox("getValue");
	$('#tt').datagrid({
		url :'../../site/manage/teach!getTeach',
		queryParams : {
			teachName : $("#selName").val(),
			majorId:major,
		}
	});
	displayMsg();
}
function detail(){
	new tqEditor(
			'detail',
			{
				toolbar : 'full'
			});
}


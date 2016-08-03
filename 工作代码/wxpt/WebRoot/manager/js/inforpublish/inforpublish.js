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
		url : '../../site/manage/infor-pub!getInfor',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [[{
			title : '标题',
			field : 'title',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '概述',
			field : 'summarize',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '内容',
			field : 'content',
			width : 400,
			rowspan : 2,
			align : 'center'
		}, {
			title : '图片',
			field : 'image',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '类别名称',
			field : 'typeName',
			width : 100,
			rowspan : 2,
			align : 'center'
		}, {
			title : '添加修改时间',
			field : 'addTime',
			width : 150,
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
		 document.getElementById("idcontent").innerHTML = 
			   "\<textarea id='content' name='content' style='width:670px; height:240px;' class='ww'\>\</textarea\>";
		typeName2();
		 var selectvalue = select.typeId;
		  $('#type').combobox('setValue',selectvalue);
		  var Field = select.typeName;
		  $("#inforId").val(select.informationId);
		  $("#title").val(select.title);
		  $("#summarize").val(select.summarize);
		  $("#content").val(select.content);
		  content();
	}else{
		$.messager.alert("提示", "请先选中一行数据！", "info");
	}
}
function opentype() {
	$('#add').window('open');
	$('#Inforpub').form('clear');
	$('#Inforpub').show();
	$('#Inforpub').appendTo('#infor');
	 document.getElementById("idcontent").innerHTML = 
		   "\<textarea id='content' name='content' style='width:670px; height:240px;' class='ww'\>\</textarea\>";
	content();
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
		url : "../../site/manage/infor-pub!saveInforPub",
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
			url : "../../site/manage/infor-pub!updateInforPub",
			success : function(text) {
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		};
		$("#Inforpub").ajaxSubmit(opt);
		close2();
}
function typeName1(){
	  $("#type").combobox({
		     url:"../../site/manage/infor-pub!getAllType",
					valueField:"typeId",
					textField:"typeName",
					editable:false,
		           missingMessage:'请选择组别！',
		            listHeight:'15xp',
		            	 onLoadSuccess: function () { //数据加载完毕事件
				            	var data = $('#type').combobox('getData');
		                        if (data.length > 0) {
		                   		 $("#type").combobox('select',data[0].typeId);
		                        }
		                    }
		}); 
}
function typeName2(){
	  $("#type").combobox({
		     url:"../../site/manage/infor-pub!getAllType",
					valueField:"typeId",
					textField:"typeName",
					editable:false,
		           missingMessage:'请选择组别！',
		            listHeight:'15xp'
		}); 
}
function delInfor() {
	var selected = $('#tt').datagrid('getSelected');
	if (selected) {
		$.messager.confirm('warning', '确认删除吗?', function(r) {
			var inforId = selected.informationId;
			if (r) {
				$.ajax({
					type : "POST",
					url : "../../site/manage/infor-pub!deleteInfor",
					data : "inforId=" + inforId,
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
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function query() {
	$('#tt').datagrid({
		url :'../../site/manage/infor-pub!getInfor',
		queryParams : {
			title : $("#selInforId").val(),
		}
	});
	displayMsg();
}
function content(){
	new tqEditor(
			'content',
			{
				toolbar : 'full'
			});
}


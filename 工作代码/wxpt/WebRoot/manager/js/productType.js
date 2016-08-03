var s_caocuo=false;
$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#product").css("visibility", "visible");	
	$('#product')
			.datagrid(
					{ onRowContextMenu:onRowContextMenu,
						title : '商品信息',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 5,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						/*onDblClickRow:selectAll,*/
						collapsible : true,
						singleSelect : true,
						url : '../wxpt/site/product-type!listProductType',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
						     	{ field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.productId + "  />";
									}
						    	},
				
								{title : '商品编号',field : 'productTypeId',width : '150',align : 'center'},
								{title : '商品类别',field : 'typeName',width : '510',align : 'center'},
		
								{title : '商品大类别',field : 'parentTypeName',width : '510',align : 'center'},
								]],
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



document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
}

function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}

function displayMsg() {

	$('#product').datagrid('getPager').pagination({
		pageSize : 5,
		pageList : [ 5, 10, 15, 20 ],
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
//商品检索
function query() {
	$('#product').datagrid(
			{
				url : "../wxpt/site/manage!showProduct",
				queryParams : {
					proName:$("#proName").val(),
					proNum:$("#proNum").val(),
					proTime:$("#proTime").val()
				}

			});
	displayMsg();
}




function AddUI() {

	$('#addUI').window('open');
	$("#addUI").css("visibility", "visible");
	
	
}
function  AddBigType() {
	var flag="add";
	var typeName=$("#addBigType").val();
if (typeName!="") {
	$.ajax({
		type : "POST",
		url : "../wxpt/site/product-type!productTypeObjectOperator",
		data : {
			'flag' : flag,
			"typeName":typeName
		},
		dataType:"json",
		success : function(json) {
				
			if (json.add=="1") {
				$.messager.alert('消息', '添加成功!');
			}if (json.add=="2") {
				$.messager.alert('消息', '添加失败!');
			}if (json.add=="3") {
				$.messager.alert('消息', '添加失败,种类有重复的!');
			}
			$("#addBigType").val("");
			addclose();
			window.location.reload();
			
		}
	
	});
}else {
	$.messager.alert('消息', '请填写类别名称!');
}
}

function  AddSmallType() {
	var flag="add";
	var typeName=$("#addSmallType").val();
	var parentId=$("#typeSelect").val();
if (typeName!="") {
	$.ajax({
		type : "POST",
		url : "../wxpt/site/product-type!productTypeObjectOperator",
		data : {
			'flag' : flag,
			'parentId':parentId,
			"typeName":typeName
		},
		dataType:"json",
		success : function(json) {
				
			if (json.add=="1") {
				$.messager.alert('消息', '添加成功!');
			}if (json.add=="2") {
				$.messager.alert('消息', '添加失败!');
			}if (json.add=="3") {
				$.messager.alert('消息', '添加失败,种类有重复的!');
			}
			$("#addSmallType").val("");
			addBigclose();
			$('#product').datagrid('reload');
			
		}
	})
}
else {
	$.messager.alert('消息', '请填写类别名称!');
}

}
function addclose()
{
	
	 $('#addUI').window('close');	
	 
}


function AddSmallUI() {
	
	
	$('#addSmallUI').window('open');
	$("#addSmallUI").css("visibility", "visible");
	
}
function addBigclose()
{
	
	 $('#addSmallUI').window('close');	
}
function updateUIClose()
{
	
	 $('#updateUI').window('close');	
}
function updateSmallUI()
{
	
	 $('#updateSmallUI').window('close');	
}

$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : "../wxpt/site/product-type!productTypeList",
		success : function(text) {

			$("#typeSelect").html(text);
				
			$("#updatetypeSelect").html(text);
		}
	
	});
});






function updateUI() {
	var select = $('#product').datagrid('getSelected');
	if (select) {
	$("#updateSmallType").empty();
	/*	$("#updatetypeSelect").empty();//清空下拉框
*/		var parentTypeName=select.parentTypeName;
		var text=select.parentTypeName;
		var typeName=select.typeName;
		
		var productTypeId=select.productTypeId;
		var value=select.productParentTypeId;	
	
		$("#updateid").val(productTypeId);
		if (parentTypeName=="无") {
			$("#updateUI").window('open');
			$("#updateUI").css("visibility", "visible");
			$('#updateBigType1').val(typeName);
		}else {
			$("#updateSmallUI").window('open');
			$("#updateSmallUI").css("visibility", "visible");
			var sel=$("#updatetypeSelect")[0];
			for ( var i = 0; i < sel.length; i++) {
				if (sel[i].text == text) {
					sel.selectedIndex = i;
				}
			}
			$("#updateSmallType").val(typeName);
			/*$("#updateSmallType").append("<option value="+value+">"+typeName+"</option>");*/

		}
		
	}else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}

function updateMethod(){
	var flag="update";
	var typeName=$("#updateBigType1").val();
	var id=$("#updateid").val();
	var productTypeId=0;
	$.ajax({
		type : "POST",
		url : "../wxpt/site/product-type!productTypeObjectOperator",
		data : {
			'flag' : flag,
			'parentId':productTypeId,
			"id":id,
			"typeName":typeName
		},
		dataType:"json",
		success : function(json) {
			if (json.add=="4") {
				$.messager.alert('消息', '修改成功!');
				updateUIClose();
			}if (json.add=="5") {
				$.messager.alert('消息', '修改失败!');
			}
		
			$('#product').datagrid('reload');
			
		}
	})
	
}

function updateMethodSmall() {
	var flag="update"; 
	var typeName=$("#updateSmallType").val();
	var productTypeId=$("#updatetypeSelect").val();
/*	alert(productTypeId);*/
	var id=$("#updateid").val();
	$.ajax({
		type : "POST",
		url : "../wxpt/site/product-type!productTypeObjectOperator",
		data : {
			'flag' : flag,
			'parentId':productTypeId,
			"id":id,
			"typeName":typeName
		},
		dataType:"json",
		success : function(json) {
			if (json.add=="4") {
				$.messager.alert('消息', '修改成功!');
				updateSmallUI();
			}if (json.add=="5") {
				$.messager.alert('消息', '修改失败!');
			}
			 updateSmallUI();
			$('#product').datagrid('reload');
			
		}
	})
	
}


function changeSelect() {

var parentId= $("#updatetypeSelect").val();
	
	$.ajax({
		type : "POST",
		data : {
			"parentId":parentId
		},
		url : "../wxpt/site/product-type!productTypeSmall",
		success : function(text) {

			$("#updateSmallType").html(text);
				
		}
	
	});
}





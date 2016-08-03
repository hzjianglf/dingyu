var temp = 0;
$(function() {
	$("#toolbar").css("visibility", "visible");
	$("#product").css("visibility", "visible");
	$('#product')
			.datagrid(
					{
						title : '商品信息',
						iconCls : 'icon-save',
						fit : true,
						onDblClickRow : doubleSee,
						pagination : true,// 设置分页条显示
						pageSize : 5,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						onRowContextMenu:onRowContextMenu,
						url : '../wxpt/site/manage!showProduct',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.productId + "  />";
									}
								}, {
									title : '商品ID',
									field : 'productId',
									width : '60',
									align : 'center',
									hidden : true
								}, {
									title : '商品编号',
									field : 'productNum',
									width : '100',
									align : 'center'
								}, {
									title : '商品名称',
									field : 'productName',
									width : '100',
									align : 'center'
								}, 
								 {
									title : '商品品牌',
									field : 'productBrand',
									width : '100',
									align : 'center'
								},
								{
									title : '商品价格',
									field : 'price',
									width : '100',
									align : 'center'
								}, {
									title : '优惠价格',
									field : 'cheapPrice',
									width : '100',
									align : 'center'
								},
								{
									title : '商品销量',
									field : 'sellNum',
									width : '100',
									align : 'center'
								},
								 {
									title : '商品库存',
									field : 'inventoryNum',
									width : '100',
									align : 'center'
								},
								{
									title : '商品颜色',
									field : 'productColor',
									width : '100',
									align : 'center'
								}, {
									title : '是否推荐',
									field : 'productRecomme',
									width : '80',
									align : 'center'
								}, {
									title : '上架时间',
									field : 'productUpdateTime',
									width : '150',
									align : 'center'
								},
								 {
									title : '商品图片',
									field : 'img',
									width : '100',
									align : 'center'
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
// 双击查看
function doubleSee() {
	var select = $('#product').datagrid('getSelected');
	$('#select').window('open');
	$("#select").css("visibility", "visible");
	$('#productNum2').val(select.productNum);
	$('#productName2').val(select.productName);
	$('#price2').val(select.price);
	$('#cheapPrice2').val(select.cheapPrice);
	$('#sellNum2').val(select.sellNum);
	$('#productColor2').val(select.productColor);
	$('#productAddtime2').val(select.productAddtime);
	$('#productUpdateTime2').val(select.productUpdateTime);
	$('#productBrand2').val(select.productBrand);
	$('#inventoryNum2').val(select.inventoryNum);
	$.ajax({
		type : 'post',
		url : '../wxpt/site/manage!selectType',
		data : {
			'productId' : select.productId
		},
		success : function(data) {
			$('#productTypes').val(data);
		},
		error : function(data) {
			$.messager.alert('消息', '查看失败!');

		}
	});
	var Ximage = select.productXimage;
	ts.innerHTML = "<img id='tp1'  src='/wxpt/web/images/" + Ximage
			+ "'  width='190' height='190'  />";
	var Dimage = select.productDimage;
	pts.innerHTML = "<img id='tp1'  src='/wxpt/web/images/" + Dimage
			+ "'  width='210' height='210'   />";

	$('#tuijians').val(select.productRecomme);
	$('#Details2').val(select.productDetails);
	$('#productOverview').val(select.productOverview);
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
function displayMsg() {

	$('#product')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 5,
						pageList : [ 5, 10, 15, 20 ],
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

function closeadd() {
	$('#addProduct').window('close');
}
function closeupdate() {
	$('#update').window('close');
}
function AddPro() {
	document.getElementById("pro").innerHTML = "";
	$('#addProduct').window('open');
	$("#addProduct").css("visibility", "visible");
	//document.getElementById("pro").innerHTML = "\<textarea name='metaDetail' id='metaDetail' cols=75 rows=15 style='background:#e0e2e4' \>\</textarea\>";
	//kindEditor();
    document.getElementById("pro").innerHTML = "\<textarea name='metaDetail' style='width:380px;height:180px;background:#e0e2e4' id='metaDetail'\>\</textarea\>";
    TQEditor();
}
// 商品添加
function saveProducts() {
	temp=0;
	if(isEmpty($('#productName').val()) == false)
	{
		$.messager.alert('消息', '商品名称不能为空!');
	}else if(isEmpty($('#price').val()) == false)
	{
		$.messager.alert('消息', '商品价格不能为空!');
	}
	else if(isNaturalNumber($('#inventoryNum').val()) == false)
	{
		$.messager.alert('消息', '商品库存不能为空且大于零!');
	}
	else if(isNaturalNumber($('#sellNum').val()) == false)
	{
		$.messager.alert('消息', '销量不能为空且大于零!');
	}
	else if(isEmpty($('#metaDetail').val()) == false)
	{
		$.messager.alert('消息', '商品描述不能为空!');
	}
	else
	{
		$.ajax({
			type : 'post',
			url : '../wxpt/site/manage!addProduct',
			data : {
				'product.productNum' : $('#productNum').val(),
				'product.productName' : $('#productName').val(),
				'product.price' : $('#price').val(),
				'product.cheapPrice' : $('#cheapPrice').val(),
				'product.sellNum' : $('#sellNum').val(),
				'product.productColor' : $('#productColor').val(),
				'product.productAddtime' : $('#productAddtime').val(),
				'product.productDetails' : $('#metaDetail').val(),
				'product.productOverview' : $('#metaDetails').val(),
				'product.productRecomme' : $('#tuijian').val(),
				'product.inventoryNum' : $('#inventoryNum').val(),
				'product.productBrand' : $('#productBrand').val(),
				'type' : $('#productType1').val()
			},
			success : function(data) {
				if(data == 1)
				{
					$('#product').datagrid('reload');
					$.messager.alert('消息', '添加成功!');
					$('#addProduct').window('close');
					$('#addProducts').form('clear');
					document.getElementById("productType").options[0].selected=true;//显示第1个
					document.getElementById("productType1").options[0].selected=true;//显示第1个
					document.getElementById("tuijian").options[0].selected=true;//显示第1个
				}
				else
				{
					$('#product').datagrid('reload');
					$.messager.alert('消息', '添加失败!');
					$('#addProduct').window('close');
					
				}
				
			}
			
		});
	}


}

// 修改页面
function UpdatePro() {
	var select = $('#product').datagrid('getSelected');
	var sel = document.getElementById('productType21');
	sel.length = 0;
	if (select) {
		$('#update').window('open');
		$('#updateProducts').form('clear');
		$("#update").css("visibility", "visible");
		$('#productId').val(select.productId);
		$('#productNum1').val(select.productNum);
		$('#productName1').val(select.productName);
		$('#inventoryNum1').val(select.inventoryNum);
		$('#productBrand1').val(select.productBrand);
		$('#price1').val(select.price);
		$.ajax({
			type : 'post',
			url : '../wxpt/site/manage!showType',
			success : function(data) {
				$('#productType2').html(data);
				var sel = $("#productType2")[0];// 大类

				$("#productType2").find("option:selected").text();

				for ( var i = 0; i < sel.length; i++) {
					if (sel[i].text == select.dtype) {
						sel.selectedIndex = i;
					}
				}
			}
		});
		var sel = document.getElementById('productType21');
		sel.options[sel.options.length] = new Option(select.xtype,select.xtypeId);
		var sel = $("#tuijian1")[0];// 大类

		$("#tuijian1").find("option:selected").text();

		for ( var i = 0; i < sel.length; i++) {
			if (sel[i].text == select.productRecomme) {
				sel.selectedIndex = i;
			}
		}
		$('#sellNum1').val(select.sellNum);
		$('#cheapPrice1').val(select.cheapPrice);
		$('#productColor1').val(select.productColor);
		$('#productAddtime1').val(select.productAddtime);
		$('#metaDetails1').val(select.productOverview);
/*		document.getElementById("pro2").innerHTML = "\<textarea name='metaDetail1' id='metaDetail1' cols=75 rows=15 style='background:#e0e2e4' \>\</textarea\>";
		document.getElementById("metaDetail1").value=select.productDetails;
		kindEditor1();*/
		  document.getElementById("pro2").innerHTML = "\<textarea name='metaDetail1' id='metaDetail1' style='width:380px;height:180px;background:#e0e2e4' \>\</textarea\>";
		  document.getElementById("metaDetail1").value=select.productDetails;
		  TQEditor1();
		
	} else {
		$.messager.alert('消息', '请选中要修改的数据');
	}

}
// 修改商品
function updateProducts() {
	temp=0;
	
	if(isEmpty($('#productName1').val()) == false)
	{
		$.messager.alert('消息', '商品名称不能为空!');
	}else if(isEmpty($('#price1').val()) == false)
	{
		$.messager.alert('消息', '商品价格不能为空!');
	}
	else if(isNaturalNumber($('#inventoryNum1').val()) == false)
	{
		$.messager.alert('消息', '商品库存不能为空且大于零!');
	}
	else if(isNaturalNumber($('#sellNum1').val()) == false)
	{
		$.messager.alert('消息', '销量不能为空且大于零!');
	}
	else if(isEmpty($('#metaDetail1').val()) == false)
	{
		$.messager.alert('消息', '商品描述不能为空!');
	}
	else
	{
	$.ajax({
		type : 'post',
		url : '../wxpt/site/manage!updateProduct',
		data : {
			'product.productId' : $('#productId').val(),
			'product.productNum' : $('#productNum1').val(),
			'product.productName' : $('#productName1').val(),
			'product.price' : $('#price1').val(),
			'product.sellNum' : $('#sellNum1').val(),
			'product.inventoryNum' : $('#inventoryNum1').val(),
			'product.productRecomme' : $('#tuijian1').val(),
			'product.productColor' : $('#productColor1').val(),
			'product.productAddtime' : $('#productAddtime1').val(),
			'product.productDetails' : $('#metaDetail1').val(),
			'product.productOverview' : $('#metaDetails1').val(),
			'product.productBrand' : $('#productBrand1').val(),
			'type' : $('#productType21').val()
		},
		success : function() {
			$.messager.alert('消息', '修改成功!');
			$('#update').window('close');
			$('#product').datagrid('reload');
		},
		error : function() {
			$.messager.alert('消息', '修改失败!');
			$('#update').window('close');
			$('#product').datagrid('reload');

		}
	});
	}
}
// 商品检索
function query() {

	$('#product').datagrid({
		url : "../wxpt/site/manage!showProduct",
		queryParams : {
			proName : $("#proName").val(),
			proNum : $("#proNum").val(),
			proTime : $("#proTime").val(),
			value : 1
		}

	});
	displayMsg();
}

// 商品删除
function DeletePro() {
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
				url : "../wxpt/site/manage!delProduct",
				data : {
					'productId' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功!");
					$('#product').datagrid('reload');

				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#product').datagrid('reload');

				}
			});
		}
		$('#product').datagrid('reload');
	});
}
// 商品小图上传
function uploadXshopPic() {
	$.ajaxFileUpload({
		url : "../wxpt/site/manage!uploadShopPic",
		secureuri : false,// 一般设置为false
		fileElementId : 'xpic',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{
			// alert(data+"ddd");
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
			} else if (data == -1) {
				$.messager.alert('提示信息', '上传失败');
			} else if (data == 0) {
				$.messager.alert('提示信息', '请选择上传图片');

			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});

}
//商品小图上传
function uploadXshopPic1()
{
	$.ajaxFileUpload({
		url : "../wxpt/site/manage!uploadShopPic1",
		secureuri : false,// 一般设置为false
		fileElementId : 'xpic1',// 文件上传空间的id属性 <input type="file" id="file"
									// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{
			//alert(data+"ddd");
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
			}else if(data == -1) {
				$.messager.alert('提示信息', '上传失败');
			}else if(data == 0){
				$.messager.alert('提示信息', '请选择上传图片');
				
			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});

}

function uploading() {
	temp = temp + 1;
	if(temp>=7){
		$.messager.alert('提示信息', '最多上传六张！！！');
	}else{
	
	$.ajaxFileUpload({
		url : "../wxpt/site/manage!uploadShopPics?temp=" + temp,
		secureuri : false,// 一般设置为false
		fileElementId : 'dpic1',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{
			// alert(data+"ddd");
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
			} else if (data == -1) {
				$.messager.alert('提示信息', '上传失败');
			} else if (data == 0) {
				$.messager.alert('提示信息', '请选择上传图片');

			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});
}
}
function uploading1() {
	temp = temp + 1;
	if(temp>=7){
		$.messager.alert('提示信息', '最多上传六张！！！');
	}else{
	
	$.ajaxFileUpload({
		url : "../wxpt/site/manage!uploadShopPics1?temp=" + temp,
		secureuri : false,// 一般设置为false
		fileElementId : 'dpic',// 文件上传空间的id属性 <input type="file" id="file"
		// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{
			// alert(data+"ddd");
			if (data == 1) {
				$.messager.alert('提示信息', '上传成功');
			} else if (data == -1) {
				$.messager.alert('提示信息', '上传失败');
			} else if (data == 0) {
				$.messager.alert('提示信息', '请选择上传图片');

			}

		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	});
}
}
/*//编辑器
function kindEditor() {
		var editor = KindEditor.create('textarea[name="metaDetail"]',{
			 afterBlur:function(){this.sync();}
		});
		editor.remove().create();
		
	}
//编辑器
function kindEditor1() {
		var editor = KindEditor.create('textarea[name="metaDetail1"]',{
			 afterBlur:function(){this.sync();}
		});
		editor.remove().create();
		
	}*/
function TQEditor(){
	new tqEditor(
			'metaDetail',
			{
				toolbar : 'full'
			});
}
function TQEditor1(){
	new tqEditor(
			'metaDetail1',
			{
				toolbar : 'full'
			});
}
//去除两头空格
function delTrim(str){	 
	return str.replace(/^\s+|\s+$/g,"");
}
//编号整数
function isNaturalNumber(id){
	id=delTrim(id);
	if(typeof(id)=="undefined"){
		return false;
	}
	if(id=="" || id==null){ 
		return false;
	} 
	
	var regExp = /^\d*$/;
	if(regExp.test(id)&&regExp.test(id)>0){
		return true;
	}else{
		return false;
	}
}
//非空验证
function isEmpty(iempty){
	 iempty=delTrim(iempty);
	if(iempty.length==0||iempty==null){
		return false;
	}else{
		return true;
	}
}

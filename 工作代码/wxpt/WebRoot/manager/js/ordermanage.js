var s_caocuo="false";
$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#product").css("visibility", "visible");	
	$('#product')
			.datagrid(
					{
						onRowContextMenu:onRowContextMenu,
						title : '商品订单管理',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 5,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						onDblClickRow:lookOder,
						collapsible : true,
						singleSelect : true,
						url :'../wxpt/site/order-manage!spiltOrderManage',
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
						    
								{title : '商品名称',field : 'productName',width : '150',align : 'center'},
								{title : '订单编号',field : 'product_num',width : '200',align : 'center'},
								{title : '下单日期',field : 'order_time',width : '200',align : 'center'},
								{title : '支付金额',field : 'pay_money',width : '200',align : 'center'},
								{title : '用户名',field : 'memberName',width : '200',align : 'center'},
							/*	{title : '支付状态',field : 'pay_type',width : '200',align : 'center'},
								{title : '收货人',field : 'receive_person',width : '200',align : 'center'},
								{title : '收货地址',field : 'receive_address',width : '200',align : 'center'},*/
								{title : '发货状态',field : 'send_type',width : '200',align : 'center'},
								{title : '收货人电话',field : 'receive_phone',width : '200',align : 'center',hidden:true},
								/*{title : '收货状态',field : 'send_type',width : '200',align : 'center'},
								{title : '物流方式',field : 'Logistics_name',width : '200',align : 'center'},
								{title : '会员名称',field : 'memberName',width : '200',align : 'center'},
								{title : '订单状态',field : 'order_type',width : '200',align : 'center'},
								{title : '订单备注',field : 'order_remark',width : '200',align : 'center'},*/
								
								
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


function displayMsg() {

	$('#product').datagrid('getPager').pagination({
		pageSize : 5,
		pageList : [ 5, 10, 15, 20 ],
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function selectAll() {
	
}
//商品检索
function query() {
	
	$('#product').datagrid(
			{
				url : "../wxpt/site/order-manage!spiltOrderManage",
				queryParams : {
					username:$("#proName").val(),
					product_num:$("#proNum").val()
				
				}

			});
	displayMsg();
}

function lookOder() {




	var select = $('#product').datagrid('getSelected');
	if (select) {
			$("#orderUI").window('open');
			$("#orderUI").css("visibility", "visible");
	
			var productName=select.productName;
			$("#productName").html(productName);
			
			var product_num=select.product_num;
			$("#product_num").html(product_num);
			
			var order_time=select.order_time;
			$("#order_time").html(order_time);
			
			var pay_money=select.pay_money;
			$("#pay_money").html(pay_money);
			
			var pay_time=select.pay_time;
		
			$("#pay_time").html(pay_time);
			
			var pay_type=select.pay_type;
			$("#pay_type").html(pay_type);
			
			var receive_person=select.receive_person;
			$("#receive_person").html(receive_person);
			
			var  order_type=select.order_type;
			$("#order_type").html(order_type);
			
			var receive_address=select.receive_address;
			$("#receive_address").html(receive_address);
		
			var  takeType=select.takeType;
			$("#takeType").html(takeType);	
			
			var  send_type=select.send_type;
			$("#send_type").html(send_type);
			
			
			
			var  receive_phone=select.receive_phone;
	
			$("#receive_phone").html(receive_phone);
			
	
			
			
			var  Logistics_name=select.Logistics_name;
			$("#Logistics_name").html(Logistics_name);

			var  memberName=select.memberName;

			$("#memberName").html(memberName);
			
		
			document.getElementById("pro1").innerHTML = "\<textarea name='order_leave' id='order_leave' cols=75 rows=15 style='background:#e0e2e4' \>\</textarea\>";
			$('#order_leave').val(select.order_leave);
			kindEditor2();
			
			document.getElementById("pro").innerHTML = "\<textarea name='metaDetail' id='metaDetail' cols=75 rows=15 style='background:#e0e2e4' \>\</textarea\>";
			$('#metaDetail').val(select.order_remark);
			kindEditor();
			
	
			
			var  sendTime=select.sendTime;

			$("#sendTime").html(sendTime);
	}else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
	
}









function kindEditor() {
	var editor = KindEditor.create('textarea[name="metaDetail"]',{
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
	
}

function kindEditor2() {
	var editor = KindEditor.create('textarea[name="order_leave"]',{
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
	
}


function sendproduct() {
	var flag="1"
		var select = $('#product').datagrid('getSelected');

	if (select) {
	 var order_id=select.order_id;
		$.ajax({
			type : "POST",
			url : "../wxpt/site/order-manage!sendORcancel_type",
			data : {
				'id':order_id
			
				
			},
			dataType:"json",
			success : function(json) {
				if (json.a=="1") {
					$.messager.alert('消息', '发货成功!');
					
				}if (json.a=="0") {
					$.messager.alert('消息', '发货失败!');
				}
				if (json.a=="2") {
					$.messager.alert('消息', '发货失败,已经发货');
				}
				if (json.a=="3") {
					$.messager.alert('消息', '发货失败,没有支付');
				}
				
				 
				$('#product').datagrid('reload');
				
			}
		});
	}else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
	
	
	
}
















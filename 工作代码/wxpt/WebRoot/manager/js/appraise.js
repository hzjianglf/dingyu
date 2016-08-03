$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{
						onRowContextMenu:onRowContextMenu,
						title : '商品评价',
						iconCls : 'icon-save',
						fit : true,
						onRowContextMenu:onRowContextMenu,
						pagination : true,// 设置分页条显示					
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../wxpt/site/appraise!getList',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
	      								
										{
											field : 'Opt',
											title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
											width : 50,
											align : 'center',
											formatter : function(value, rec) {
												return "<input type=\"checkbox\" name=\"xuanze\" value="
														+ rec.appraiseId + "  />";
											}
										},	
										{
											title : '会员',
											field : 'appraiseId',
											width : '130',
											align : 'center',
											hidden : true
										},
										{
											title : '会员',
											field : 'member',
											width : '130',
											align : 'center',
											visibility : 'hidden'
										},
																		
										{
											title : '产品',
											field : 'product',
											width : '160',
											align : 'center',
											visibility : 'hidden'
										},
										{
											title :'评价内容',
											field : 'appraiseContent',
											width : '230',
											align : 'center',
											visibility : 'hidden',
											sortable:true
											},
										{
											title :'评价时间',
											field : 'appraiseTime',
											width : '180',
											align : 'center',
											visibility : 'hidden',
											sortable:true
										},
										{
											title :'商品价格',
											field : 'money',
											width : '180',
											align : 'center',
											hidden : true,
											sortable:true
										},
										{
											title :'address',
											field : 'address',
											width : '180',
											align : 'center',
											hidden : true,
											sortable:true
										},
										{
											title :'phone',
											field : 'phone',
											width : '180',
											align : 'center',
											hidden : true,
											sortable:true
										}
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
						onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								queryVote();
							}
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

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

//去除两头空格
function delTrim(str){	 
	return str.replace(/^\s+|\s+$/g,"");
}

//编号整数
function isNaturalNumber(id,msg){
	var objValue = delTrim(id);
	if(typeof(objValue)=="undefined"){
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	}
	if(objValue=="" || objValue==null){ 
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	} 
	var regExp = /^\d*$/;
	if(regExp.test(objValue)){
		return true;
	}else{
		$.messager.alert('warning',"编号不合法，必须为整数");
		return false;
	}
	return true;
}

//非空验证
function isEmpty(iempty,msg){
	 iempty=delTrim(iempty);
	if(iempty.length==0||iempty==null){
		$.messager.alert('warning',msg+"不能为空");
		return false;
	}else{
		//txtName_focus(id);
		return true;
	}
}

function close1() {
	$("#adduser").window("close");
	$("#updateuser").window("close");
}

function Delete() {

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
				url : "../wxpt/site/appraise!delete",
				data : {
					'appraiseId' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功!");
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					window.location.reload();
				}
			});
		}
		$('#tt').datagrid('reload');
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
function displayMsg1() {

	$('#la').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function queryVote(){
	var selected = $('#tt').datagrid('getSelected');
	$('#app').window('open');
	$("#app").css("visibility", "visible");	
	$('#productName').val(selected.product);
	$('#price').val(selected.money);
	$('#member').val(selected.member);
	$('#phone').val(selected.phone);
	$('#address').val(selected.address);
	var apId=selected.appraiseId;
    $.ajax({
		 secureuri : false,
        type:"POST",
        url:"../wxpt/site/appraise!getAll",
        	dataType : "text",
        data:{
        'appId':apId
        },
        
      secureuri : false,//一般设置为false
     
       success:function (text){
     // alert("111");
      //alert("333");
        $("#liuyanban").html(text);
     // alert("222");
       },
       error:function (text){
    	   
       }
     });
}
function huifu(id){

	$('#'+id).css("display","");
	
}

function quxiao(id){
	
	$('#'+id).css("display","none");
	
	
}
function tijiao(id,id2){

	
	//alert($('#'+id).val()+"==yyyy=="+id2);

	  $.ajax({
			 type:"post",
	          url: '../wxpt/site/appraise!yanzheng',
	          data: {
	        	 'appId':id2
	          },
	          success: function (date) {
	        	 if(date==1){
	        		 
	        		  $.ajax({
	 	     			 type:"post",
	 	     	          url: '../wxpt/site/appraise!update',
	 	     	          data: {
	 	     	        	 
	 	     	        	'huifuneirong':$('#'+id).val(),
	 	     	        	 'appId':id2
	 	     	          },
	 	     	          success: function (text) {
	 	     	        	 // alert(text);
	 	     	        	  $("#liuyanban").html(text);
	 	     	        	  $("#liuyanban").html(text);
	 	     	        	  $.messager.alert('warning','回复成功'); 
	 	     	          },
	 	     	          error: function (text) {
	 	     	        	  $.messager.alert('warning','回复失败'); 
	 	     	        	
	 	     	          }
	 	     	      });  
	        		 
	        		 
	        	 }else{
	        		 
	        		  $.messager.alert('warning','只能回复一条');  
	        		 
	        	 }
	        	  
	        
	        	  
	        	  
	          },
	          error: function (date) {
	        	
	        	
	          }
	      });
	
	
	
	
/*	  $.ajax({
			 type:"post",
	          url: '../site/member!huifuliuyan',
	          data: {
	        	 
	        	 'huifuneirong':$('#'+id).val(),
	        
	        	 'messageid':id2
	          },
	          success: function (text) {
	        	  $("#liuyanban").html(text);
	        	  $.messager.alert('warning','回复成功'); 
	          },
	          error: function (text) {
	        	  $.messager.alert('warning','回复失败'); 
	        	
	          }
	      });*/
	
}
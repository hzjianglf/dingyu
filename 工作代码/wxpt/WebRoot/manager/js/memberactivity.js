$(function() {
	
	$("#toolbar").css("visibility", "visible");	
	$("#active").css("visibility", "visible");	
	$('#active')
			.datagrid(
					{
						title : '促销活动',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 5,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						onDblClickRow:getSelects2,
						collapsible : true,
						singleSelect : true,
						url : '/wxpt/site/member!selectActivity',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [
								{title : '活动编号',field : 'activityId',width : '60',align : 'center'},
								{title : '活动标题',field : 'activityTitle',width : '100',align : 'center'},
								{title : '活动内容',field : 'activityContent',width : '300',align : 'center'},
								{title : '开始时间',field : 'activityStarttime',width : '150',align : 'center'},
								{title : '结束时间',field : 'activityEndtime',width : '150',align : 'center'},
								{title : '活动海报',field : 'imageTemp',width : '200',align : 'center'},
								]],
						rownumbers : true,
						toolbar : "#toolbar"
					});
	displayMsg();
});


function displayMsg() {

	$('#active').datagrid('getPager').pagination({
		pageSize : 5,
		pageList : [ 5, 10, 15, 20 ],
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function closeadd()
{
 $('#addActivity').window('close');
 $('#addActivitys').form('clear');
}
function closeupdate()
{
 $('#updateActivity').window('close');
 $('#updateActivitys').form('clear');
}
//打开add页面
function addActivity()
{
	$('#addActivity').window("open");
}

// 删除活动
function delactivity(){
	
	// alert("dddddddd");
	var select =$('#active').datagrid('getSelected');

	if(select){
		var	activityid = select.activityId;
		$.messager.confirm('warning','是否要删除？',function(id){
			if(id){
				
			  $.ajax({
				   type:"post",
				   url:'/wxpt/site/member!delactivity',
				   data:{
				   
				   'activityId':activityid
				   
				   },
				   success:function(date){
					 
					   
					   if(date==1){
						   $('#active').datagrid('reload');
						   $.messager.alert('warning','删除成功','warning')
					   }
					   
					   
				   },
				   error:function(date){
					   if(date==-1){
						   $.messager.alert('warning','删除失败','warning')
					   }
					   
				   }
				   
			   });
			}

		});

	}else{
		
		$.messager.alert('warning','请选择数据','warning');
		
	}
}

//打开上传图片
function openupload(){
	var select =$('#active').datagrid('getSelected');

	if(select){
		//var	activityid = select.activityId;
		
  $('#activityid').val(select.activityId);
	$('#shangchuanhaibao').window('open');
	$('#shangchuanhaibao').css("visibility","visible");
	}else{
		
		$.messager.alert('warning','请选择数据','warning');
		
	}
	
	
}
//上传海报
function shangchuanhaibao(){
	var activityid= $('#activityid').val();
	$.ajaxFileUpload({
		url : "/wxpt/site/member!shangchuanhaibao?activityId="+activityid,
		secureuri : false,// 一般设置为false
		fileElementId : 'haibao',// 文件上传空间的id属性 <input type="file" id="file"
									// name="file" />
		dataType : 'text',

		success : function(data) // 服务器成功响应处理函数
		{
			//alert(data+"ddd");
			if (data == 1) {
				 $('#shangchuanhaibao').window('close');
				   $('#active').datagrid('reload');
				  	
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

//取消上传
function close2(){
	$('#shangchuanhaibao').window('close');	
}

//双击
function getSelects2(){
	  var select = $('#active').datagrid('getSelected');
	
	$('#chakanxiangqing').window('open');
	$('#chakanxiangqing').css("visibility","visible");
	
	$('#cuxiaobianhao').val(select.activityId);
	$('#huodongbiaoti').val(select.activityTitle);
	
	$('#kaishishijian').val(select.activityStarttime);
	
	$('#jieshushijian').val(select.activityEndtime);
	
	$('#huodongneirong').val(select.activityContent);
	
	$('#haibaoming').val(select.imageUrl);
	var haibaoming =$('#haibaoming').val();
//	alert(	$('#haibaoming').val());
	//onclick='show2(\"<img width=500 height=500   src=/wxpt/web/images/"+haibaoming+">\")'    ts.innerHTML="<img id='tp1'  src='/wxpt/web/images/"+haibaoming+"' title='点击查看大图' width='100' height='100'  onclick='show2(\"<img width=500 height=500 src=/hdcrm/shangchuan/images/"+id+"yingyezhizhaozhaopian.JPG >\")' onMouseOut='hide()'/>";
	ts.innerHTML="<img id='tp1'  src='/wxpt/web/images/"+haibaoming+"'  width='290' height='290' style='border:1px solid red;'  />";

}

//显示大图
/*function show2(ss){
	
	//alert(ss+"sssssssssss");
	 document.getElementById('datu').innerHTML=ss;
	 ('#datu').css("display","");
	
}*/


//隐藏大图
//function hide(){
//	
//	 $('#datu').css("display","none"); 
//}
//促销活动add
function addActivitys()
{
	//alert($("#addActivitys").serialize());
	$.ajax({
		type:'post',
		url:'/wxpt/site/member!addActivity',
		data: ''+$("#addActivitys").serialize(),
		success:function(data)
		{
			if(data)
			{
				$.messager.alert('消息', "添加成功");
				$('#addActivity').window('close');
				$('#addActivitys').form("clear");
				$('#active').datagrid('reload');
			}
		}
		
	});	
}
//打开update页面
function updateActivity()
{
	
	var select = $('#active').datagrid('getSelected');
	if(select)
	{
		$('#updateActivity').window('open');
		$('#activityId').val(select.activityId);
		$('#activityStartTimes').val(select.activityStarttime);
		$('#activityEndTimes').val(select.activityEndtime);
		//$('#imageUrls').val(select.imageUrl);
		$('#activityTitles').val(select.activityTitle);
		$('#activityContents').val(select.activityContent);
	}
	else
	{
		$.messager.alert('消息', "请选择一条数据");
	}
	
}
//促销活动update
function updateActivitys()
{
	$.ajax({
		type:'post',
		url:'/wxpt/site/member!updateActivity',
		data: ''+$("#updateActivitys").serialize(),
		success:function(data)
		{
			if(data)
			{
				$.messager.alert('消息', "修改成功");
				$('#updateActivity').window('close');
				//$('#addActivitys').form("clear");
				$('#active').datagrid('reload');
			}
		}
		
	});
}
/*//开始时间
$(document).ready(function(){
	$('#activityStartTime').datebox({
		 editable:false,
	//	 onSelect:yanzhengCommunicatAddtime
	});
})
//结束时间
$(document).ready(function(){
	$('#activityEndTime').datebox({
		 editable:false,
		 //onSelect:yanzhengCommunicatAddtime
	});
})*/



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<title>系统日志</title>
<script type="text/javascript">
var winWidth = 0;  
var winHeight = 0; 
//获取浏览器高、宽
function findDimensions() {  
	//获取窗口宽度  
	if (window.innerWidth)  
		winWidth = window.innerWidth;  
	else if ((document.body) && (document.body.clientWidth))  
		winWidth = document.body.clientWidth;  
	//获取窗口高度  
	if (window.innerHeight)  
		winHeight = window.innerHeight;  
	else if ((document.body) && (document.body.clientHeight))  
		winHeight = document.body.clientHeight;  
	//通过深入Document内部对body进行检测，获取窗口大小  
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){  
		winHeight = document.documentElement.clientHeight;  
		winWidth = document.documentElement.clientWidth;  
	}
	//alert("高度:" + winHeight + " 宽度：" + winWidth);
}  
findDimensions();  
var pageSize = parseInt((winHeight-220) / 30);
var _cur_page = 0;
getData(0,pageSize);
function getData(start,pageSize){
	$.ajax({
		  type: 'POST',
		  url: 'log_systemLog.action',
		  data: {			  
			  start:start, 
			  rows:pageSize
		  },
		  dataType: 'JSON',
		  success: function(data){
			  var dataRows = data.fileLogs;			 
			  var trs = "";
			  for(var i = 0;i < dataRows.length;i++){
				  var rowNM = _cur_page * pageSize + i + 1;		
				  trs += '<tr valign="middle" style=background-color:' + ((i%2==1)?'#F5F5F5':'') + '>';
				  trs += '<td style="text-align: center;">' + rowNM + '</td>';
				  trs += '<td>' + dataRows[i].title + '</td>';
				  trs += '<td>' + dataRows[i].logType + '</td>';
				  trs += '<td>' + dataRows[i].userName + '</td>';
				  trs += '<td>' + dataRows[i].ip + '</td>';
				  trs += '<td>' + dataRows[i].logDate + '</td>';
				  trs += '<td title="' + dataRows[i].description + '">' + (dataRows[i].description).substring(0,10) + '...</td>';
				  trs += '</tr>';
			  }
			  $('#tableBody').html(trs);
			  if(data.total == 0){				  
				  $('#tableBody').html('<tr><td colspan="7" align = "center"><font color=red>暂无数据！</font></td></tr>');					
			  }
			  //分页
			  $("#Pagination").pagination(data.total, {
				  num_edge_entries: 2,
				  num_display_entries: 5,
				  callback: pageselectCallback,
				  current_page:_cur_page,
				  items_per_page:pageSize
			  });
			  //文件统计
			  $('#listViewCount').html("共 " + data.total + " 条记录 ！");
		  },
		  error:function(){
			  alert('查找失败！');
		  }		
	});
}

//分页回调函数
function pageselectCallback(start, jq){	
	var cur_start = start * pageSize;
	_cur_page = start;
	getData(cur_start,pageSize);
}
</script>
</head>
<body>
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：
		
			系统管理 >>   系统日志
		
		<br/>
	</div>	
<div class="listDataTableStyle">
	<table cellpadding="0"  cellspacing="0">
		<thead>
			<tr >
				<th width="3%">&nbsp;</th>				
				<th width="25%" >标题</th>
				<th width="10%" >类型</th>
				<th width="10%" >访问用户</th>
				<th width="10%" >访问ip</th>
				<th width="10%" >访问时间</th>
				<th width="25%" >日志描述</th>
			</tr>			
		</thead>
		<tbody id="tableBody">
			<tr><td colspan="7"><div class="loading-indicator">加载中...</div></td></tr>
		</tbody>		
	</table> 
</div>
	<div id="listViewCount" style="margin-top:10px;margin-left:10px;float: left;">
		共 ${total } 条记录
	</div>
	<div id="Pagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
	</div>
</body>
</html>
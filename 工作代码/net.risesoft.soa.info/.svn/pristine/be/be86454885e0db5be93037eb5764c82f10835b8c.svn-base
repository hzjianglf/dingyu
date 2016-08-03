<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
List<String[]> list = (List<String[]>)request.getAttribute("list");
%>
<html>
<head>
<title></title>
<style type="text/css">

</style>
<script type="text/javascript">
function moveUp(t){
	var cur = $(t).parent().parent();
	var pre = cur.prev('tr');
	if (pre.length == 0){
		return;
	}
	cur.insertBefore(pre);
	var sr = $("#tbody1 tr").length;
	if (sr == 0){
		return;
	}
    var ids = new Array(sr);
  	for(var i = 0;i < sr;i++) 
  	{ 
   		ids[i] = $("#tbody1 tr:eq(" + i + ")").attr("id");
   	}
  	$.post('/info/infoFileList.action', { operation: 'index', indexs: ids.toString()});
}
function moveDown(t){
	var cur = $(t).parent().parent();
	var nex = cur.next('tr');
	if (nex.length == 0){
		return;
	}
	cur.insertAfter(nex);
	var sr = $("#tbody1 tr").length;
	if (sr == 0){
		return;
	}
	var ids = new Array(sr);
	for(var i = 0;i < sr;i++) 
	{ 
	    ids[i] = $("#tbody1 tr:eq(" + i + ")").attr("id");
	} 
	$.post('/info/infoFileList.action', { operation: 'index', indexs: ids.toString()});
}
function removeInfoFile(img){
	var parent = $(img).parent().parent();
	$.post('/info/infoFileList.action', 
			{ operation: 'remove', id: parent.attr("id")},
			function(data){
				if (data.success){
					parent.remove();
				}else{
					$('#message_cancel_div').html(data.message);
					$('#dialog_cancel_message').dialog('open');
				}
			},
			"json"
	);
}
</script>
</head>
<body>
<%
if (list.size() > 0){
%>
<table align="center" cellpadding="0" cellspacing="0" style="border: 1px dotted #D1D1D1;border-collapse: collapse;">
	<tr style="background: rgb(179, 179, 179);">
		<td height=30 style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;" width=300 align="center">附件名称</td>
		<td width=80 align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;">大小</td>
		<td width=80 align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;">上传人</td>
		<td width=120 align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;">上传时间</td>
		<td width=80 style="border-bottom:1px dotted #D1D1D1;"></td>
	</tr>
	<tbody id="tbody1">
	<%
	for(int i = 0; i < list.size(); i++){
		String[] string = list.get(i);
	%>
	<tr id="<%=string[0]%>">
		<td height=30 style="padding-left: 2px; border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;"><%=string[1]%></td>
		<td align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;"><%=string[2]%></td>
		<td align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;"><%=string[3]%></td>
		<td align="center" style="border-right:1px dotted #D1D1D1;border-bottom:1px dotted #D1D1D1;"><%=string[4]%></td>
		<td align="center" style="border-bottom:1px dotted #D1D1D1;">
			<img src='/info/css/images/up.png' style="cursor: pointer;" title="向上移动" alt="上移" onclick="javascript:moveUp(this);">&nbsp;
			<img src='/info/css/images/down.png' style="cursor: pointer;" title="向下移动" alt="下移" onclick="javascript:moveDown(this);">&nbsp;
			<img src='/info/css/images/delete.png' style="cursor: pointer;" title="移除附件" alt="移除" onclick="javascript:removeInfoFile(this);">
		</td>
	</tr>
	<%}%>
	</tbody>
</table>
<%}%>
</body>
</html>



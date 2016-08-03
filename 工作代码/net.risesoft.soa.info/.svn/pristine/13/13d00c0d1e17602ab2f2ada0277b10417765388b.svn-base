<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应急预案查询</title>
</head>
<body>
<div style="text-align:right;width:95%; font-size:10pt; color:#999; margin-right:10px; line-height:40px; height:30px;">总数: ${total}</div>
	<div class="listDataTableStyle">
      	<table align="center" id="pubInfo" style="background-color: #ffffff;border: 1px solid #E5E5E5; width:95%;" cellpadding="0" cellspacing="0" border="0">
			<tr style="background-color: #eeeeee">
				<td align=center height=35 width="40px">&nbsp;</td>
				<td align=center>标题</td>
				<td align=center>部门</td>
				<td align=center>发布时间</td>
			</tr> 
			<s:if test="list.size>0">
			<s:iterator value="list" var="temp" status="l">
			<tr>
				<td align="center" style="cursor:pointer;">
				${l.index+1}
				</td>
				<td align="center" style="cursor:pointer;">
				<a class="link1" href="javascript:showInfo('${temp[0]}');">${temp[1] }</a>
				</td>
				<td align="center" style="cursor:pointer;">
				<a class="link1" href="javascript:showInfo('${temp[0]}');">${temp[2] }</a>
				</td>
				<td align="center" style="cursor:pointer;">
				<a class="link1" href="javascript:showInfo('${temp[0]}');">${temp[3] }</a>
				</td>
			</tr>
			</s:iterator>
			</s:if>
			<s:else>
			<tr bgcolor="white">
				<td colspan="5" align="center">没有数据</td>
			</tr>
			</s:else>
		</table>
	</div>
</body>
<script type="text/javascript">
	var _cur_page = ${pageNo};
	var pageSize = ${pageSize};
	$("#Pagination").pagination('${total}', {
	  num_edge_entries: 2,
	  num_display_entries: 8,
	  callback: pageselectCallback,
	  current_page:_cur_page,
	  items_per_page:pageSize
	});
	//分页回调函数 start为起始位置
	function pageselectCallback(start, jq){
		//$("#inform_list").mask('装载中...');
		$("#search_list").load("/info/infoSearchList.action", {"infoType":$("#infoType").val(),"startTime": $("#startTime").val(),"endTime": $("#endTime").val(),"title": $("#title").val(),"pageSize": pageSize, "pageNo": start});
	} 
	function showInfo(id){
		window.open("infoShow.jsp?id="+id);
	}
</script>
</html>
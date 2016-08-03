<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	#optionSearch{
		display: none;
		  position: absolute;
		  z-index: 200;
		  text-align: left;
		  background-color: white;
		  filter:alpha(opacity=99);
	}
	
</style>
<script type="text/javascript">	
//分页的起始位置
_dFilePageStart = '${start}';
_dFilePageStart = parseInt(_dFilePageStart);
//记录的总数
var _dFilePageTotalCount = '${totalCount}';
_dFilePageTotalCount = parseInt(_dFilePageTotalCount);
$(function(){
	  //分页
	  $("#dimensionFileListPagination").pagination(_dFilePageTotalCount, {
		  num_edge_entries: 2,
		  num_display_entries: 5,
		  callback: dimensionFileListPaginationCallback,
		  current_page:_dFilePageStart / _dFilePagePageSize,
		  items_per_page:_dFilePagePageSize
	  });
});
//分页回调函数
function dimensionFileListPaginationCallback(start, jq){	
	_dFilePageStart = start * _dFilePagePageSize;
	dimensionFind('');
}
//按名称搜索   searchStr -- 输入的搜索字符
function dimensionFind(searchStr){		
	var dimensionSql = eval('${dimensionSql}');		
	var hasNameCondition = false;
	for(var i = 0; i < dimensionSql.length;i++){
		if(dimensionSql[i].columnName == 'name' && searchStr.trim() != ''){
			hasNameCondition = true;
			dimensionSql[i].value = searchStr.trim();
		}
	}
	if(!hasNameCondition){
		var length = dimensionSql.length;
		length++;
		dimensionSql[length] = {};
		dimensionSql[length].columnName = 'name';
		dimensionSql[length].value = searchStr.trim();
	}		
	Ext.getCmp('index-center-panel').load({url:"dimension_file.action?dimensionSql="
			+ encodeURIComponent(Ext.encode(dimensionSql)) + "&start=" + _dFilePageStart + "&rows=" + _dFilePagePageSize
	   	,scripts:true});
}
/**
 * 按名称搜索文件或文件夹
 */
function searchByName(){   
	Ext.MessageBox.prompt("筛选","请输入文件或文件夹的名称：",function(button,txt){   
		if(button == 'cancel') return;	
		dimensionFind(txt);
	});
}
	
</script>
<title>维度文件页面</title>
</head>
<body>	
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：多维度展示  >> 
		<br/>
	</div>
	<!-- 数据区域 -->
	<div class="listDataTableStyle">	
	<table cellpadding="0"  cellspacing="0">
		<thead>
			<tr>
				<th width="3%" ></th>
				<th width="35%" style="padding-left: 10px;">名称 
					<img src="images/icons/filter_name.png" 
						style="vertical-align: middle;margin-left: 10px;cursor: pointer;" 
						title="筛选" onclick="searchByName()"/>
						</th>
				<th width="8%" >大小</th>
				<th width="8%" >类型</th>
				<th width="10%" >创建时间</th>
				<th width="10%" >修改时间</th>
			</tr>			
		</thead>
		<tbody>
			<s:iterator value="fileInfos" id="fileInfo" status="index">				
				<tr 
					<s:if test="#index.count % 2 == 0">
						class = "changeColor"
					</s:if>
				>
					<td style="text-align: center;"><s:property value='#index.count + start'/></td>					
					<td style="padding-left: 10px;">
					<img src="<%=path %>/images/file/small/files/<s:property value='#fileInfo.extension'/>.gif" style="vertical-align: middle;">
					<a href=javascript:preViewdocument("<s:property value='#fileInfo.fileUid'/>") ><s:property value='#fileInfo.name'/></a>
					</td>
					<td><s:property value='#fileInfo.size'/></td>
					<td><s:property value='#fileInfo.type'/></td>
					<td><s:date name="#fileInfo.createdate" format="yyyy-MM-dd" /></td>
					<td><s:date name="#fileInfo.modifieddate" format="yyyy-MM-dd" /></td>
				</tr>
			</s:iterator>
		</tbody>		
	</table> 
	</div>
	<div id="dimensionFileListPagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
	</div>
</body>
</html>
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
<title>我的收藏</title>
<script type="text/javascript">
function deleteFavorite(uid){	
	Ext.Msg.confirm('提示', '确定取消此收藏记录吗？',function(button,text){
		if(button != 'yes') return;
		$.ajax({
			  type: 'POST',
			  url: 'favorite_delete.action',
			  data: {'favorites.favoritesUid':uid},
			  dataType: 'JSON',
			  success: function(date){
				  Ext.Msg.alert('操作成功','取消收藏记录成功！');     
				  Ext.getCmp('index-center-panel').
					load({url:'favorite_preList.action?start=0&rows=' 
							+ parseInt((_leftWinHeight - 220)/30),scripts:true});
			  },
			  error:function(){
				  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
			  }			  
		});
	});
}
//分页的起始位置
var _favPageStart = '${start}';
_favPageStart = parseInt(_favPageStart);
//分页的行数
var _favPagePageSize = '${rows}';
_favPagePageSize = parseInt(_favPagePageSize);
//记录的总数
var _favPageTotalCount = '${totalCount}';
_favPageTotalCount = parseInt(_favPageTotalCount);
var _current_page = _favPageStart / (_favPagePageSize > 1 ? _favPagePageSize : 1);
$(function(){
	  //分页
	  $("#favoriteListPagination").pagination(_favPageTotalCount, {
		  num_edge_entries: 2,
		  num_display_entries: 5,
		  callback: favoriteListPaginationCallback,
		  current_page:_current_page ,
		  items_per_page:_favPagePageSize
	  });
});
//分页回调函数
function favoriteListPaginationCallback(start, jq){	
	var cur_start = start * _favPagePageSize;
	Ext.getCmp('index-center-panel').
	load({url:'favorite_preList.action?start=' + cur_start + 
			'&rows=' + _favPagePageSize,scripts:true});
}
</script>
</head>
<body>	
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：
		
			个人中心 >> 我的收藏  
		
		<br/>
	</div>	
<div class="listDataTableStyle">	
	<table cellpadding="0"  cellspacing="0">
		<thead>
			<tr >
				<th width="3%">&nbsp;</th>				
				<th width="25%" >名称</th>
				<th width="15%" >收藏时间</th>
				<th width="10%" >操作</th>
			</tr>			
		</thead>
		<tbody>
			<s:if test="#favoritesAlls.size == 0">
				<tr><td colspan="4" align = "center"><font color=red>暂无数据！</font></td></tr>
			</s:if>			
			<s:iterator value="favoritesAlls" id="favorite" status="index">
				<tr valign="middle"
						<s:if test="#index.count % 2 == 0">
							class = "changeColor"
						</s:if>
					>
					<td style="text-align: center;">
						<s:property value='#index.count + start'/>
					</td>
					<td>
						<s:if test="%{#favorite.fileInfo == null}">
							<img src="<%=path %>/images/file/small/folders/folder.gif" style="vertical-align: middle;">
						</s:if>
						<s:if test="%{#favorite.fileFolder == null}">
							<img src="<%=path %>/images/file/small/files/<s:property value='#favorite.fileInfo.extension'/>.gif" style="vertical-align: middle;">
						</s:if>
						<!-- 收藏的文件 -->
						<font color="blue" style="cursor: pointer;" 
						onclick=preViewdocument('<s:property value='#favorite.fileInfo.fileUid'/>') >
						<s:property value='#favorite.fileInfo.name'/>
						</font>
						<!-- 收藏的文件夹 -->
						<font color="blue" style="cursor: pointer;" 
						onclick=preViewfolder('<s:property value='#favorite.fileFolder.folderUid'/>') >
						<s:property value='#favorite.fileFolder.name'/>		
						</font>			
					</td>
					<td><s:date name="#favorite.favoriteDate" format="yyyy-MM-dd" /></td>
					<td>
						<img alt="" src="<%=path %>/images/button_operation/delete.png" title='取消收藏'
							onclick=deleteFavorite("<s:property value='#favorite.favoritesUid'/>")
						 style="cursor: pointer;">
					</td>
				</tr>
			</s:iterator>
		</tbody>		
	</table> 
</div>
	<div id="favoriteListPagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
	</div>
</body>
</html>
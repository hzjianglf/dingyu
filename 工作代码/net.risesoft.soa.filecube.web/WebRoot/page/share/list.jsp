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
<title>我的共享</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<script type="text/javascript">

//分页的起始位置
var _sharePageStart = '${start}';
_sharePageStart = parseInt(_sharePageStart);
//分页的行数
var _sharePagePageSize = '${rows}';
_sharePagePageSize = parseInt(_sharePagePageSize);
//记录的总数
var _sharePageTotalCount = '${totalCount}';
_sharePageTotalCount = parseInt(_sharePageTotalCount);
var _current_page = _sharePageStart / (_sharePagePageSize > 1 ? _sharePagePageSize : 1);
$(function(){
	  //分页
	  $("#shareListPagination").pagination(_sharePageTotalCount, {
		  num_edge_entries: 2,
		  num_display_entries: 5,
		  callback: shareListPaginationCallback,
		  current_page:_current_page,
		  items_per_page:_sharePagePageSize
	  });
});
//分页回调函数
function shareListPaginationCallback(start, jq){	
	var cur_start = start * _sharePagePageSize;
	Ext.getCmp('index-center-panel').
	load({url:'share_preList.action?start=' + cur_start + 
			'&rows=' + _sharePagePageSize,scripts:true});
}
function deleteShare(uid){	
	Ext.Msg.confirm('提示', '确定取消共享吗？',function(button,text){
		if(button != 'yes') return;
		$.ajax({
			  type: 'POST',
			  url: 'share_delete.action',
			  data: {'fileShare.shareUid':uid},
			  dataType: 'JSON',
			  success: function(date){
				  Ext.Msg.alert("操作成功",'取消共享成功！');   
				  Ext.getCmp('index-center-panel').
					load({url:'share_preList.action?start=0&rows=' 
							+ parseInt((_leftWinHeight - 220)/30),scripts:true});
			  },
			  error:function(){
				  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
			  }			  
		});
	});
}

</script>
</head>
<body>	
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：
		
			个人中心 >> 共享   >> 我的共享
		
		<br/>
	</div>	
<div class="listDataTableStyle">
	<table cellpadding="0"  cellspacing="0">
		<thead >
			<tr >
				<th width="3%" >&nbsp;</th>				
				<th width="25%" >名称</th>
				<th width="15%" >共享时间</th>
				<th width="10%" >操作</th>
			</tr>			
		</thead>
		<tbody>
			<s:if test="#fileShares.size == 0">
				<tr><td colspan="4" align = "center"><font color=red>暂无数据！</font></td></tr>
			</s:if>		
			<s:iterator value="fileShares" id="share" status="index">
				<tr valign="middle"
					<s:if test="#index.count % 2 == 0">
						class = "changeColor"
					</s:if>
				>
					<td style="text-align: center;"><s:property value='#index.count + start'/></td>
					<td>
						<s:if test="%{#share.fileInfo == null}">
							<img src="<%=path %>/images/file/small/folders/folder.gif" style="vertical-align: middle;">
						</s:if>
						<s:if test="%{#share.fileFolder == null}">
							<img src="<%=path %>/images/file/small/files/<s:property value='#share.fileInfo.extension'/>.gif" style="vertical-align: middle;">
						</s:if>
						<!-- 收藏的文件 -->
						<font color="blue" style="cursor: pointer;" 
						onclick=preViewdocument('<s:property value='#share.fileInfo.fileUid'/>') >
						<s:property value='#share.fileInfo.name'/>
						</font>
						<!-- 收藏的文件夹 -->
						<font color="blue" style="cursor: pointer;" 
						onclick=preViewfolder('<s:property value='#share.fileFolder.folderUid'/>') >
						<s:property value='#share.fileFolder.name'/>		
						</font>			
					</td>
					<td><s:date name="#share.shareDate" format="yyyy-MM-dd" /></td>
					<td>
						<img alt="" src="<%=path %>/images/menu/view.gif" title='查看共享'
							onclick=share("<s:property value='#share.fileInfo.fileUid'/><s:property value='#share.fileFolder.folderUid'/>",'${(share.fileFolder == null) ? "":"folder"}')
						 style="cursor: pointer;">
						<img alt="" src="<%=path %>/images/button_operation/delete.png" title='删除'
							onclick=deleteShare("<s:property value='#share.shareUid'/>")
						 style="cursor: pointer;">
					</td>
				</tr>
			</s:iterator>
		</tbody>		
	</table> 
</div>
<div id="shareListPagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
</div>
<div id="shareDialogParent">
	<!-- 共享对话框 -->	
</div>
</body>
</html>
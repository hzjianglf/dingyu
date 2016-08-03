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
<title>来自他人的共享</title>
<script type="text/javascript">
//分页的起始位置
var _shareMePageStart = '${start}';
_shareMePageStart = parseInt(_shareMePageStart);
//分页的行数
var _shareMePagePageSize = '${rows}';
_shareMePagePageSize = parseInt(_shareMePagePageSize);
//记录的总数
var _shareMePageTotalCount = '${totalCount}';
_shareMePageTotalCount = parseInt(_shareMePageTotalCount);
var _current_page = _shareMePageStart / (_shareMePagePageSize > 1 ? _shareMePagePageSize : 1)
$(function(){
	  //分页
	  $("#shareMeoriteListPagination").pagination(_shareMePageTotalCount, {
		  num_edge_entries: 2,
		  num_display_entries: 5,
		  callback: shareMeoriteListPaginationCallback,
		  current_page:_current_page,
		  items_per_page:_shareMePagePageSize
	  });
});
//分页回调函数
function shareMeoriteListPaginationCallback(start, jq){	
	var cur_start = start * _shareMePagePageSize;
	Ext.getCmp('index-center-panel').
	load({url:'share_preOtherToMeList.action?start=' + cur_start + 
			'&rows=' + _shareMePagePageSize,scripts:true});
}
/**
 * 删除他人共享给我的此条记录
 * @param uid -- 共享记录的uid
 */
function deleteShareToMe(uid){	
	Ext.Msg.confirm('提示', '确定删除此共享吗？',function(button,text){
		if(button != 'yes') return;
		$.ajax({
			  type: 'POST',
			  url: 'share_deleteShareToMe.action',
			  data: {'fileShare.shareUid':uid},
			  dataType: 'JSON',
			  success: function(date){
				  Ext.Msg.alert("操作成功",'删除共享成功！');   
				  Ext.getCmp('index-center-panel').
					load({url:'share_preOtherToMeList.action?start=0&rows=' 
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
		
			个人中心 >> 共享  >> 来自他人的共享
		
		<br/>
	</div>	
	<div class="listDataTableStyle">
		<table cellpadding=0 cellspacing=0>
			<thead>
				<tr >
					<th width="3%" >&nbsp;</th>				
					<th width="25%" >名称</th>
					<th width="10%" >共享者</th>
					<th width="15%" >共享时间</th>	
					<th width="10%" >操作</th>				
				</tr>			
			</thead>
			<tbody>
				<s:if test="#otherToMeList.size == 0">
					<tr><td colspan="4" align = "center"><font color=red>暂无数据！</font></td></tr>
				</s:if>		
				<s:iterator value="otherToMeList" id="share" status="index">
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
						<td>
							<s:property value='#share.userName'/>
						</td>
						<td><s:date name="#share.shareDate" format="yyyy-MM-dd" /></td>		
						<td>
							<img alt="" src="<%=path %>/images/button_operation/delete.png" title='删除'
							onclick=deleteShareToMe("<s:property value='#share.shareUid'/>")
							 style="cursor: pointer;">
						</td>				
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div id="shareMeoriteListPagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
	</div>
</body>
</html>
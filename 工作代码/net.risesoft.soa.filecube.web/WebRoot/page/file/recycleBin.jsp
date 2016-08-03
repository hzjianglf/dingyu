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
<title>我的回收站</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<script type="text/javascript">
	//删除
	function realDelete(uid,type){
		var url = "";
		var data = {};
		if(type != global_RESOURCE_TYPE_FOLDER_EN){
			url = 'file_delete.action';
			data = {'acFileInfo.fileUid':uid,folderTreeType:global_FOLDER_TREE_TYPE_CUR};			
		}else{
			url = 'folder_delete.action';
			data = {'folder.folderUid':uid,folderTreeType:global_FOLDER_TREE_TYPE_CUR};
		}
		ajaxPost(url,'确定删除吗？删除后将无法恢复！',data);
	}
	//恢复
	function recover(uid,type){
		var url = "";
		var data = {};		
		if(type != global_RESOURCE_TYPE_FOLDER_EN){
			url = 'file_recoverFile.action';
			data = {'acFileInfo.fileUid':uid};			
		}else{
			url = 'folder_recoverFolder.action';
			data = {'folder.folderUid':uid};
		}
		ajaxPost(url,'确定恢复吗？',data);
	}
	//后台提交
	function ajaxPost(url,title,data){
		Ext.Msg.confirm('提示', title,function(button,text){
			if(button == 'yes'){			
				$.ajax({
				  type: 'POST',
				  url: url,
				  data: data,
				  dataType: 'JSON',
				  success: function(date){
					  Ext.Msg.alert('操作成功',date.success);   
					  Ext.getCmp('index-center-panel').
						load({url:'file_preRecycleBin.action',scripts:true});
				  },
				  error:function(){
					  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
				  }			  
				});
			}
		});
	}
	//清空回收站
	function batchDelete(){
		var batchUids = "";
		$('.batchUids').each(function(){
			batchUids += this.value + ",";
		});		
		ajaxPost('folder_batchDeleteFiles.action','确定删除吗？删除后将无法恢复！',{batchUids:batchUids + "1",folderTreeType:global_FOLDER_TREE_TYPE_CUR});
	} 
</script>
</head>
<body>
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：
		
			个人中心 >> 我的回收站  
		
		<br/>
	</div>	
<div class="listDataTableStyle">
	<table cellpadding="0"  cellspacing="0">
		<thead >
			<tr>
				<th width="25%" style="padding-left: 10px;">名称</th>
				<th width="10%" >大小</th>				
				<th width="10%" >操作 <button style="margin-left: 30px;height: 30px;" onclick="batchDelete()">清空回收站</button></th>
			</tr>			
		</thead>
		<tbody>
			<s:iterator value="deleteFiles" id="file" status="index">				
				<tr>	
					<td style="padding-left: 10px;">
					<!-- 清空回收站用 -->
					<input type="hidden" class="batchUids" value="<s:property value='#file.uid'/>" />
					
					<s:if test="%{#file.extension == 'folder'}">
						<img src="<%=path %>/images/file/small/folders/folder.gif" style="vertical-align: middle;">
					</s:if>
					<s:else>
						<img src="<%=path %>/images/file/small/files/<s:property value='#file.extension'/>.gif" style="vertical-align: middle;">
					</s:else>					
					<s:property value='#file.name'/>
					</td>
					<td><s:property value='#file.fileSize'/></td>					
					<td>
						<img src="<%=path %>/images/button_operation/delete.png" 
						style="vertical-align: middle;cursor: pointer;" title="彻底删除"
						onclick=realDelete("<s:property value='#file.uid'/>","<s:property value='#file.type'/>") >
						| 
						<img src="<%=path %>/images/button_operation/arrow_redo.png" 
						style="vertical-align: middle;cursor: pointer;" title="还原"
						onclick=recover("<s:property value='#file.uid'/>","<s:property value='#file.type'/>")>						
					</td>
				</tr>
			</s:iterator>
		</tbody>		
	</table> 
</div>
</body>
</html>
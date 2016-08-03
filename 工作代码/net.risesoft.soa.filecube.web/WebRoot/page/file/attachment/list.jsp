<%@page import="net.risesoft.soa.filecube.util.OperationType"%>
<%@page import="net.risesoft.soa.filecube.util.GlobalConst"%>
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
<title>附件列表</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<script type="text/javascript">
attachment_operationKey_modify = '<%=OperationType.FC_MODIFY.toString()%>';
</script>
</head>
<body>
	<!-- 附件信息 -->
	<div class="listDataTableStyle" style="margin-top: 10px;border-top: 1px solid #E5E5E5;">
	<table  cellpadding="0"  cellspacing="0">	
		<thead>	
			<tr >
				<th width="3%" > &nbsp;</th>
				<th>名称</th>
				<th width="10%">类型</th>
				<!-- 
				<th width="10%">大小</th>
				 -->
				<th width="10%">上传者</th>
				<th width="20%">上传时间</th>
				<th width="15%">
					<div style="white-space:nowrap;width: 100%">
					<span style="width:20%;float: left;">
						操作
					</span>
					<span style="width:70%">
					<img src="<%=path%>/commons/ajaxUpload/images/upload.png"
					style="cursor:pointer;height: 28px;vertical-align: middle;
					margin-top: 1px;margin-bottom: 1px;float: right; "
					onclick="uploadAttachment('${fileInfo.fileUid}')"/>
					</span>
					</div>
					
				</th>
			</tr>	
		</thead>
		<tbody>
			<s:iterator value="attachments" id="attachment"  status="index">
				<tr <s:if test="#index.count % 2 == 0">
							class = "changeColor"
						</s:if>
					>
					<td  style="text-align: center;"><s:property value='#index.count'/> &nbsp;</td>
					<td>
					<img src="<%=path %>/images/file/small/files/<s:property value='#attachment.extension'/>.gif" style="vertical-align: middle;">
					
					<a href=javascript:openAttrDoc("<s:property value='#attachment.attachmentUid'/>") >
						<s:property value="#attachment.name"/>
					</a>   &nbsp;
					
					</td>
					<td><s:property value="#attachment.type"/>  &nbsp;</td>
					<!-- 
					<td><s:property value="#attachment.size"/>  &nbsp;</td>
					-->
					<td> &nbsp;</td>
					<td><s:property value="#attachment.createDate"/> &nbsp;</td>
					<td>
						<img alt="" src="<%=path %>/images/button_operation/delete.png" title='删除'
							onclick=deleteAttachment("<s:property value='#attachment.attachmentUid'/>")
						 style="cursor: pointer;vertical-align: middle;"> &nbsp;||&nbsp; 
						<img alt="" src="<%=path %>/images/view/download.png" title='下载'
							onclick=downloadAttachment("<s:property value='#attachment.attachmentUid'/>")
						style="cursor: pointer;vertical-align: middle;">						
					</td>
				</tr>
			</s:iterator>	
		</tbody>		
	</table>
	</div>
	<!-- 上传文件控件 -->
	<div id="global_uploadDiv">	
	</div>
</body>
</html>
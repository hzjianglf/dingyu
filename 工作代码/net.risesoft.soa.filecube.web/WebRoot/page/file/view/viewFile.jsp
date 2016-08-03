<%@page import="net.risesoft.soa.filecube.util.OperationType"%>
<%@page import="net.risesoft.soa.filecube.util.GlobalConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看文件</title>
<style type="text/css">
	html{
		font-size: 12px;	
		overflow: auto;
	}
	a:link {color: blue; text-decoration:none;}
</style>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/jquery/ui/css/flick/jquery-ui-1.9.1.custom.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/file/view/viewFile.css" />

<script type="text/javascript" src="<%=path %>/commons/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/commons/jquery/ui/jquery-ui-1.9.1.custom.min.js"></script>

<script type="text/javascript" src="<%=path %>/commons/flexpaper/flexpaper.js"></script>
<script type="text/javascript" src="<%=path %>/commons/ajaxUpload/customUpload.js"></script>

<link rel="stylesheet" type="text/css" href="/extjs/3.4/resources/css/ext-all.css" />
<script type="text/javascript" src="/extjs/3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/extjs/3.4/ext-all.js"></script>
<script type="text/javascript" src="/extjs/3.4/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/extjs/init.js"></script>

<script type="text/javascript" src="<%=path %>/js/permission/permission.js"></script>
<script type="text/javascript" src="<%=path %>/js/file/attachment.js"></script>
<script type="text/javascript" src="/fulltext/js/document.js"></script>

<script type="text/javascript">	
	var global_FOLDER_TREE_TYPE_CUR = '${fileInfo.itemType}';
	var viewFile_FileUid = '${fileInfo.fileUid}';
	var viewFile_FileName = '${fileInfo.name}';
	var _file_persional_ItemType = '<%=GlobalConst.RESOURCE_ITEM_TYPE_PERSONNEL%>';
	var viewFile_operationKey_download = '<%=OperationType.FC_DOWNLOAD.toString()%>';
	var viewFile_operationKey_share = '<%=OperationType.FC_SHARE.toString()%>';	
	
</script>
<script type="text/javascript" src="<%=path %>/js/file/view/viewFile.js"></script>

</head>
<body style="margin: 0px 0px;">

<div id="info" style="display: none;">
	<ul>
		<li><a href="#viewFileTab">文件</a></li>
		<li><a href="<%=path%>/file_preMetadata.action?acFileInfo.fileUid=${fileInfo.fileUid}">基本信息</a></li>
		<!-- <li><a href="<%=path%>/relevanceFile_preList.action?fileInfo.fileUid=${fileInfo.fileUid}">关联文件</a></li> -->
		<!-- 关联文件 -->
		<li id="relevanceFileTabLi"><a href="#relevanceFileTab">关联文件</a></li>
		<li><a href="<%=path%>/attachment_preList.action?fileInfo.fileUid=${fileInfo.fileUid}">附件</a></li>
		<li><a href="#commentary">评论</a></li>
	</ul>
	<div id="relevanceFileTab" style="padding: 0px 0px;">
		<iframe height="660px" width="100%" frameborder="0" style="margin: 0px 0px;" id="relevanceFileIframe"
		src="<%=path%>/relevanceFile_preFileList.action?fileUid=${fileInfo.fileUid}"></iframe>
	</div>
	<div id="commentary" style="padding: 0px 0px;">
		<iframe height="650px" width="100%" frameborder="0" style="margin: 0px 0px;" id="commentaryIframe"
		src="<%=path%>/commentary_preList.action?fileInfo.fileUid=${fileInfo.fileUid}"></iframe>
	</div>
	
<div id="viewFileTab" style="padding: 0px 0px;min-height: 660px">

<div id="viewFile_FileDiv" style="width: 100%;">
	
	<table width="100%"  cellpadding="0" cellspacing="0" id="viewFileTable">			
		<tr>
			<td width="75%" style="vertical-align: top;padding-top: 6px;">
		<div id="viewerPlaceHolder">	        
			<div id="progressDialog" style="font-size: 16px;font-style: normal;vertical-align: middle;">
				<center>		
					<br/>
					数据加载中,请稍后……
					<br/>
					<br/>
					<img src="<%=path %>/images/view/progress.gif"/>
				</center>
			</div>
        </div>
        </td>
        <td width="25%" rowspan="2" style="padding-top: 4px;">
        
        <div style="font-family:Verdana;font-size:9pt;border: 1px solid #F7F5F5;" id="viewFileRight">        	   	
        	  	       	
        	<div style="border-bottom: 1px solid #F7F5F5;
        	background: none repeat scroll 0 0 #F5F5F5;
        	margin-left: 2px;margin-right: 2px;
        	padding-left: 5px;padding-right: 5px;height:280px;">        		
       			<h1 style="margin-left: 10px;margin-top: 5px;padding-top:5px;color: #333333;
    			font-family: '微软雅黑','黑体',arial;
    			font-size: 16px;">${fileInfo.name}</h1>
    			<!-- 评分 
    			<span style="color: #F7A600;
    				font-size: 28px;height: 22px;
    				font-style: italic;
    				font-weight: bold;" id="totalScore">0</span>
    			<span id="star"></span>    			
				<span id="star-target"></span>-->
    			<span style=" color: #999999;">已有：</span>
					<span style="color: #F7A600;
    				font-size: 24px;height: 13px;
    				font-style: italic;
    				font-weight: bold;" id="userCountSpan">
						
					</span>
					人评论
									
					<p style="margin-bottom: 2px;margin-top: 2px;"></p>
				
					<span style=" color: #999999;">浏览：</span>
					<span style=" color: #999999;">
						<span id="viewCount" style="color: #F7A600;
	    				font-size: 24px;
	    				font-style: italic;
	    				font-weight: bold;" >${fileInfo.openTimes == null ? 0 : fileInfo.openTimes}</span>
						次
						</span>
						<span style=" color: #999999;">下载：</span>						
						<span id="downCount" style="color: #F7A600;
	    				font-size: 24px;
	    				font-style: italic;
	    				font-weight: bold;">${fileInfo.downLoadTimes == null ? 0 : fileInfo.downLoadTimes}</span>
						次
				
					<p style="margin-bottom: 10px;margin-top: 10px;"></p>
					
					<span style=" color: #999999;">所属部门：</span>
					<a style="color: blue;">${fileInfo.departmentName}</a>
					
					<p style="margin-bottom: 10px;margin-top: 10px;"></p>
					
					<span style=" color: #999999;">创建者：</span>
					<a style="color: blue;">${fileInfo.creatorName}</a>
			
					<p style="margin-bottom: 10px;margin-top: 10px;"></p>
					
					<span style=" color: #999999;">创建时间：</span>					
					<s:date name="#fileInfo.createdate" format="yyyy-MM-dd" />
				    
				    <p style="margin-bottom: 10px;margin-top: 10px;"></p>
			
					<span style=" color: #999999;">格式：</span>
					<b style="margin-top: 5px;">
						<img src="<%=path %>/images/file/small/files/${fileInfo.extension}.gif" style="vertical-align: middle;">
					</b>
					<span style=" margin-left: 5px !important;color: #333333;">${fileInfo.extension}</span>
				
					<p style="margin-bottom: 10px;margin-top: 10px;"></p>			
						
		        	<span class="buttonSpan" id="favoritedButtonSpan"  style="display:none" title="取消收藏">
		        		<img src="<%=path %>/commons/grade/img/star-on.png" style="vertical-align: middle;margin-right: 6px;"/>取消收藏
		        	</span>
		        	<span class="buttonSpan" id="notFavoritedButtonSpan" title="收藏此文档">
		        		<img src="<%=path %>/commons/grade/img/star-off.png" style="vertical-align: middle;margin-right: 6px;"/>收藏
		        	</span>
		        	<span class="buttonSpan" id="ViewFileDownload" title="下载此文档">
		        		<img src="<%=path %>/images/button_operation/download.gif" style="vertical-align: middle;margin-right: 6px;"/>下载
		        	</span>
		        	<span class="buttonSpan" id="ViewFileShare" title="共享此文档">
		        		<img src="<%=path %>/images/icons/share_16.png" style="vertical-align: middle;margin-right: 6px;"/>共享
		        	</span>
					      		
        	</div>  
        	
        	<!-- 关联信息 -->
        	<div id="relation"> 
        		<h3>打开此文件的人也打开了...</h3>
        		<div style="background: none repeat scroll 0 0 #F5F5F5;"
        		 id="relationOpenHistoryDiv">	        		
	        		<div class="loading-indicator" style="height: 280px;">加载中...</div>	       				
        		</div>        		
        	
        		<h3>收藏此文件的人也收藏了...</h3>
        		<div style="background: none repeat scroll 0 0 #F5F5F5;"
        		 id="relationFavoriteDiv">	        		
	        		<div class="loading-indicator">加载中...</div>	       				
        		</div>   
	        </div>      	      	
        </div>
        </td>
        </tr>
       </table> 
 </div>  
 <div id="viewFile_AttachmentDiv" class="x-hide-display"></div>  
 <div id="viewFile_CommentaryDiv" class="x-hide-display"></div>  
</div>
</div>
	<div id="shareDialogParent" style="padding: 0px 0px;">
		<!-- 共享对话框 -->	
	</div>
</body>
</html>
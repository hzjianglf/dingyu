<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件图标视图</title>
<style type="text/css">
	.transparent { 
		opacity: 0.6; 
	}
	.transparent1 { 
		opacity: 1.0;
	}	
</style>	
<script type="text/javascript" src="<%=path %>/js/file/view/imgView.js"></script>
<script type="text/javascript" src="<%=path %>/js/file/view/openImages.js"></script>
<script type="text/javascript">

	global_CUR_FILE_VIEWMODE = '${showFile}';
	//分页的起始位置
	var _imgViewPageStart = '${start}';
	_imgViewPageStart = parseInt(_imgViewPageStart);
	//分页的行数
	var _imgViewPageSize = '${pageSize}';
	_imgViewPagePageSize = parseInt(_imgViewPageSize);
	//记录的总数
	var _imgViewPageTotalCount = '${totalCount}';
	_imgViewPageTotalCount = parseInt(_imgViewPageTotalCount);
	$(function(){
		  //显示区域的高度
		 // $('#imgContent').attr("style","height:" + (_leftWinHeight - 240) + "px;overflow: auto;");
		  //分页
		  $("#imgViewPagination").pagination(_imgViewPageTotalCount, {
			  num_edge_entries: 2,
			  num_display_entries: 5,
			  callback: imgViewPaginationCallback,
			  current_page:_imgViewPageStart / _imgViewPageSize ,
			  items_per_page:_imgViewPageSize
		  });
	});
	//分页回调函数
	function imgViewPaginationCallback(start, jq){	
		var cur_start = start * _imgViewPageSize;
		Ext.getCmp('showFile').
		load({url:'file_${showFile}.action?showFile=${showFile}&folderUid=' + file_jsp_folderUid +
				'&start=' + cur_start + 
				'&pageSize=' + _imgViewPageSize,scripts:true});
	}
</script>

</head>
<body>
<!-- 导航代码 -->
<p style="margin-left: 2px;margin-top: 10px">
       您的位置：
	<s:iterator value="navigationBar" id="folder">
		<s:if test="#folder.name == 'overThree'">
			..... >> 
		</s:if>
		<s:else>
			<a href=javascript:nav('<s:property value="#folder.folderUid"/>') > 
				<s:property value="#folder.name"/>  
			</a> >> 
		</s:else>			
	</s:iterator>
</p>
<p style="border-bottom: 1px solid #E5E5E5;margin-left: 2px;margin-right: 2px;margin-top: 10px;margin-bottom: 2px;"></p>
<div style="" id="imgContent">
<ul style="width:100%;">	
<s:iterator value="files" id="file">
	<li class="image_container" fileUid="<s:property value='#file.id'/>" title="<s:property value='#file.name'/>"
			style="float:left;width:120px;height:135px;padding:1px; 
			background-color : #f7f7f7;		
			border:1px solid #ccc;			
			-moz-box-shadow: 0 1px 3px #777; 
			-webkit-box-shadow: 0 1px 3px #777; 
			margin-left:10px;margin-top:10px;list-style:none; margin-right: 20px;" fileSelect="no" 
			fileType="<s:property value='#file.type'/>" id="<s:property value='#file.id'/>"
			extendFolderUids = '<s:property value='#file.extendFolderUids'/>'>
			
	  <ul class="topBar transparent" id="tlbar<s:property value='#file.id'/>" 
	  		style="position:absolute; 
			width:120px;height:20px;margin:0px; 
			padding:0px; background-color:#fff; 
			border-bottom:1px solid #ccc; list-style-type:none; ">
			<!-- img选中的文件或文件夹的checkbox  -->
			<li style="float:left; display:inline;">
				<input type="checkbox" class="imgCheckClick" 
					style="vertical-align: middle;margin-left: 2px;margin-top: 2px;"
					fileUid="<s:property value='#file.id'/>"/>
			</li>
			<li style="float:right; display:inline;">
				<!-- 下载按钮的显示与隐藏 ，死值，常量值都存在GlobalConst类中-->
				<s:if test="#file.type != 'folder'">
					<img style='cursor: pointer;vertical-align: middle;margin-right:2px;' class='operationImg' src='images/view/download.png'
					 title='下载' onclick=downLoadFile("<s:property value='#file.id'/>") />&nbsp;
		 		</s:if>
				<img style='cursor: pointer;vertical-align: middle;margin-right:2px;' class='operationImg' src='images/icons/share_16.png'
		 title='共享' onclick=share("<s:property value='#file.id'/>","<s:property value='#file.type'/>") />
				&nbsp;<img style='cursor: pointer;vertical-align: middle;margin-right:2px;'
				 class='operationImg' 
				 src="images/button_operation/star-<s:property value='#file.favorite'/>.png" 
				 imgSrc = '<s:property value='#file.favorite'/>'
				 favoriteUid = "<s:property value='#file.favoriteUid'/>"  title='收藏' 
				 onclick=favorite("<s:property value='#file.id'/>","<s:property value='#file.type'/>",this);
				 />
			</li>
	  </ul>
	 
  	 <!-- 打开文件  图片需特殊处理-->
  	 <a  fileUid="<s:property value='#file.id'/>"
  	 	<s:if test="#file.type == 'image'">
  		 	class="imagesView"
  		 	href="file_viewImgFile.action?acFileInfo.fileUid=<s:property value='#file.id'/>" 
  	 		title="<s:property value='#file.name'/>"
  	 	</s:if>
  	 >
  	 	<s:if test="#file.type == 'image'">
  	 		<img src="<%=path %>/file_smallImg.action?fileUid=<s:property value='#file.id'/>" 		  	 		
		  	onerror="this.src='<%=path %>/images/file/large/files/unknown.gif'"
		  	width="65" class="img" height="65" 
		  	style="margin-top:28px;margin-left:30px;margin-bottom: 5px;cursor: pointer;"
		  	title="<s:property value='#file.name'/>" 
		  	<s:if test="#file.type != 'image'">
		  	onclick="preView<s:property value='#file.type'/>('<s:property value='#file.id'/>')"
		  	</s:if>
		  	/>	
  	 	</s:if>
  	 	<s:else>
  	 		<img src="<%=path %>/images/file/large/files/<s:property value='#file.extension'/>.gif" 		  	 		
		  	onerror="this.src='<%=path %>/images/file/large/files/unknown.gif'"
		  	width="65" class="img" height="65" 
		  	style="margin-top:28px;margin-left:30px;margin-bottom: 5px;cursor: pointer;"
		  	title="<s:property value='#file.name'/>" 
		  	<s:if test="#file.type != 'image'">
		  	onclick="preView<s:property value='#file.type'/>('<s:property value='#file.id'/>')"
		  	</s:if>
		  	/>		 
  	 	</s:else>  	 	 			 
	</a>
		 
			  <div style="margin-top:10px;margin-left:1px;border-top:1px;
				  margin-bottom: 4px; " >
			  	<s:property value='#file.shortName'/>
			  </div>	
	  	 
	 <!-- 
	  <div class="bottomBar transparent" id="nav<s:property value='#file.id'/>" 
	  		style="display:none;width:120px; height:20px; 
			position:absolute;background-color:#fff; "> 
			<a class="left" 
				style="float:left; cursor:pointer; width:20px; height:20px; opacity: 0.6;
				background:#fff url(/filecube/images/view/tag.png) no-repeat center center; ">
			</a> 					
			<span class="text" onclick=share("<s:property value='#file.id'/>","<s:property value='#file.type'/>");
				style="position:absolute; cursor: pointer;color:#888; font-weight:bold;
				font-size:12px;margin-top:3px;margin-left:20px;font-family: Arial; ">
			共享
			</span> 
			<a class="right" 
				style="float:right; cursor:pointer; width:20px; height:20px; opacity: 0.6;
				background:#fff url(/filecube/images/view/tag.png) no-repeat center center;">
			</a> 
			
	  </div>
	  --> 
	</li>
	
</s:iterator>
</ul>
<!-- 
<div style="display:none" id="imageOperateDiv">	
	<div style="float:right;padding-top:12px;position:relative;">
		<a class='imageOperate' style='margin-right:10px;cursor:pointer;font-weight:bold;color:#7C7C7C;' onclick="imgDownload()">下载</a>
		<a class='imageOperate' style='margin-right:10px;cursor:pointer;font-weight:bold;color:#7C7C7C;' onclick="imgShare()">共享</a>
		<a class='imageOperate' style='margin-right:50px;cursor:pointer;font-weight:bold;color:#7C7C7C;' onclick="imgFavorite()">收藏</a>
	</div>	
</div>
-->	
</div>
<!-- 分页 -->		
<div style="width: 100%;float: left;">
<p style="border-bottom: 1px solid #E5E5E5;margin-left: 2px;margin-right: 2px;margin-top: 10px;margin-bottom: 2px;"></p>

	<div id="imgViewCount" style="margin-top:10px;margin-left:10px;float: left;">
		目录共   ${totalCount } 个项，当前共   ${totalSize }
	</div>	
    <div id="imgViewPagination" class="pagination" style="float: right;padding-right: 150px;padding-top: 10px;">
	</div>
</div>	

</body>
</html>
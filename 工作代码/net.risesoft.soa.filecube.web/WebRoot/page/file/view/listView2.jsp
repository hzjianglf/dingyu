<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   String sign = request.getParameter("numcountnum");   
   String sign2 = "123";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表视图</title>
<script type="text/javascript">
/**
 * 搜索字符
 */
var _searchStr = "${searchStr}";
/**
 * 搜索字符
 */
var _sortStr = "${sortStr}";
</script>
<script type="text/javascript" src="<%=path %>/js/file/view/listView2.js"></script>
<script type="text/javascript" src="<%=path %>/js/file/view/openImages.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<script type="text/javascript">
	global_CUR_FILE_VIEWMODE = '${showFile}';
	alert(">>>>>"+global_CUR_FILE_VIEWMODE);
	function refreshurl(){
		// alert(window.location.href);
         window.location.href=window.location.href+'?numcountnum="stop"';
    }
</script>
<style type="text/css">
	.operationImg{
		cursor: pointer;
	}
	#optionSearch{
		  display: none;
		  position: absolute;
		  z-index: 200;
		  text-align: left;
		  background-color: white;
		  filter:alpha(opacity=99);
	}	
</style>
</head>
<body style="height: 100%;"  >	
	<!-- 导航区域 -->	
	<!-- <div style="width:100%;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
		border-bottom: 1px solid #E5E5E5;"> -->
		<!-- 导航代码 -->
	<!-- <p style="margin-left: 2px;">
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
	</div>	-->
	
	<!-- 数据区域 -->
	<div class="listDataTableStyle">
	<table cellpadding=0 cellspacing=0>
		<thead >
			<tr >
				<th width="3%" >&nbsp;</th>
				<!--<th width="5%" ><input id="selectAllData" type="checkbox"/></th>-->
				<th width="25%" > 名称 
					<img src="images/icons/filter_name.png" 
						style="vertical-align: middle;margin-left: 10px;margin-top:-3px; ;cursor: pointer;" 
						title="筛选" onclick="searchByName()"/>
				</th>
				<th width="10%" >大小</th>
				<!--<th width="10%" >类型</th>-->
				<th width="15%" >创建时间
					<img src="images/icons/sort_desc.png" sortType = "desc"
						style="vertical-align: middle;margin-left: 10px;cursor: pointer;" 
						title="点击排序" id="sortImg_createDate"/>
				</th>
				<!--<th width="15%" >修改时间</th>-->
				<!--<th width="10%" >操作</th>-->
			</tr>			
		</thead>
		<tbody id="listViewTbody">
			
		</tbody>		
	</table> 
	</div>
	<!-- 分页 -->		
	<div style="width: 100%">
		<div id="listViewCount" style="margin-top:10px;margin-left:10px;float: left;">
		
		</div>
		<div id="Pagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;">
	    </div>
	</div>	
</body>
</html>
<script type="text/javascript">
	//setTimeout("refreshurl()",1000); 
	//window.location.reload();
	alert('<%=sign%>');
	alert('<%=sign2%>');
	<%
	   if(("stop").equals(sign)){

	   } else {
	%>
         refreshurl();  
	<% }%>
</script>

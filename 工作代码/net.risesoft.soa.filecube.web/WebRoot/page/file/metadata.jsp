<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件的基本信息</title>
</head>
<body style="">
	<div style="overflow:hidden;margin: 0px 0px">		 
	    <table border="0" align="center" width="100%" 
	    style="border-collapse: collapse;margin-top: 10px;">
	      <tr>
	      	<td colspan="6" align="center">
	      		<h1 style="color: #333333;
		    			font-family: '微软雅黑','黑体',arial;
		    			font-size: 14px;">
		    				基本信息
		    	</h1>
		    </td>
	      </tr>   
		  <tr height="30">
	        <td style="border-style: dotted; border-width: 1px;" align="center" width="120">名称</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.name }
	         </td>
	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">唯一标识</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.fileUid }</td>
	        
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">类型</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
				<img src="<%=path %>/images/file/small/files/${fileInfo.extension }.gif" 
				style="vertical-align: middle;">
			</td>
	      </tr>
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">扩展名</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.extension }
	         </td>	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">存储路径</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.directory }</td>
	        
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">大小</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
				${fileInfo.size }
			</td>
	      </tr>
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">创建时间</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.createdate }
	         </td>	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">修改时间</td>
	        <td colspan="3" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.modifieddate}</td>
			
	      </tr>
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">关键字 </td>
	        <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.keyWords }</td>			
	      </tr>
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">描述 </td>
	        <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.description }</td>
	      </tr>      
	    </table>  
	    <br/>
	    <table border="0" align="center" width="100%" 
	    style="border-collapse: collapse;margin-top: 10px;">
	      <tr>
	      	<td colspan="7" align="center">
	      		<h1 style="color: #333333;
		    			font-family: '微软雅黑','黑体',arial;
		    			font-size: 14px;">
		    				责任者信息
		    	</h1>
		    </td>
	      </tr>   
		  <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">创建人编号</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.creatorUid }
	         </td>
	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">创建人姓名</td>
	        <td colspan="3" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.creatorName }</td>	       
	      </tr>
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">所属部门标识</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.departmentUid }
	         </td>
	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">所属部门名称</td>
	        <td colspan="3" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.departmentName }</td>	       
	      </tr>   
	    </table> 
	    
	    <br/>
	    
	     <table border="0" align="center" width="100%" 
	    style="border-collapse: collapse;margin-top: 10px;">
	      <tr>
	      	<td colspan="7" align="center">
	      		<h1 style="color: #333333;
		    			font-family: '微软雅黑','黑体',arial;
		    			font-size: 14px;">
		    				业务信息
		    	</h1>
		    </td>
	      </tr>   
		  <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">应用系统</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.app }
	         </td>
	         
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">子应系统</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.subApp }</td>
	        
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">文件来源适配器</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
				${fileInfo.adapter.adapterUid }
			</td>
	      </tr>	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">文件种类</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.kind }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">发文字号</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.fileNumber }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">公文签发时间</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.issueDate }
	        </td>
	      </tr> 
	      
	       <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">公文紧急程度</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.enmergency }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">文件密级</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.secretLevel }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">公文的保密期限</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.secretLife }
	        </td>
	      </tr> 
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">公文页数</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.pagination }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">签发人</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.underSigned }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">发文机关（责任者）</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.unitSender }
	        </td>
	      </tr>
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">主送机关</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.mainReciever }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">抄送机关</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.copyReciever }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">印发机关</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.issuedAuthority }
	        </td>
	      </tr>
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">印发份数</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.copies }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">印发时间</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.issuedDate }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">送出时间</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.submittedDate }
	        </td>
	      </tr>
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">办理单位</td>
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.handlingUnits }
	        </td>
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">办理时限</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.processedLife }
	        </td>	       
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">办理人</td>	        
	        <td style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.transactor }
	        </td>
	      </tr>
	      
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">公文的主题词</td>	        
	        <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px">
	          ${fileInfo.fod.keyWord }
	        </td>
	      </tr>    
	      <tr height="30">
	        <td style="border-style: dotted; border-width: 1px" align="center" width="120">备注</td>
	        <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px">${fileInfo.remarks}</td>
	      </tr>    
	    </table> 
    </div>		    
</body>
</html>
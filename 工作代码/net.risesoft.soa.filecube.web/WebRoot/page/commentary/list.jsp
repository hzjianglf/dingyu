<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
	application.setAttribute("userUid",sessionUser.getUserUID());
%>
<html>
<head>
<title>文件评论</title>
<script type="text/javascript" src="<%=path %>/commons/jquery/jquery-1.7.2.min.js"></script>
 <!--[if lt IE 9]>
<script type="text/javascript" src="<%=path %>/commons/css/shadow/pie.js"> </script>
 <![endif]--> 
<script type="text/javascript">
	//修改评论tab title	
	$(function(){
		if (window.PIE) {
			$('.shadow').each(function() { 
				PIE.attach(this); 
			});
		}
		$('#addCommentary').click(function(){
			var content = $.trim($('#commentaryContent').val());
			if(content != ""){
				$.ajax({
					  type: 'POST',
					  url: 'commentary_add.action',
					  data: {
						  "fileInfo.fileUid":'${fileUid}',
						  "commentary.content":content
					  },
					  dataType: 'JSON',
					  success: function(data){
						  
						  var html ='<div style="border-bottom: 1px dashed #E5E5E5;display:none;padding-left: 10px;" class="commentaries" id=' + data.commentaryUid + ' >';
						  html += '<img src="<%=path %>/images/view/commentary.png" style="vertical-align: middle;"/> ';
						  html += '<span style="padding-left: 5px;padding-top: 10px;">' + data.userName + '	&nbsp;';
						  html += '发表时间：' + data.createDate + '&nbsp;&nbsp;'
						  html += '	<a href=javascript:deleteCommentary("' + data.commentaryUid + '"); >删除</a></span>'
							
						  html += '<div style="margin-left: 40px;margin-top: 20px;margin-right: 40px;margin-bottom: 20px;">';
						  html += 	data.content;
						  html += '</div>';
						  html += '</div>';
						  $('#commentariesDiv').prepend(html);   
						  $('#' +　data.commentaryUid).show('slow');
						  $('#commentaryContent').val('');						 
					  },
					  error:function(){
						  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
					  }			  
				});
			}
		});	
		$('#addCommentary').hover(
			function(){
				this.src = '<%=path %>/images/icons/pinglun_hover.png';
			},function(){
				this.src = '<%=path %>/images/icons/pinglun.png';
			}
		);
		//已发评论的鼠标移上 变色	
		$('.commentaries').hover(
			function(){				
				$(this).css('background-color','#C0C0C0');
			},
			function(){
				var myTag = parseInt($(this).attr('mytag'));
				if(myTag % 2 == 1){
					$(this).css('background-color','#FAFDFC');
				}else{
					$(this).css('background-color','');
				}	
			}
		);
	});
	//删除评论
	function deleteCommentary(commentaryId){
		$.ajax({
			  type: 'POST',
			  url: 'commentary_delete.action',
			  data: {				  
				  "commentary.commentaryUid":commentaryId
			  },
			  dataType: 'JSON',
			  success: function(date){
				  $('#' + commentaryId).hide('slow');    
			  },
			  error:function(){
				  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
			  }			  
		});
	}
</script>
<style type="text/css">
html{
	font-size: 12px;	
}
a:link {color: blue; text-decoration:none;}
.shadow { 
	min-height:650px;
	width:900px;	
	-moz-border-radius:10px;
	-webkit-border-radius:10px; 
	border-radius:15px; 
	background:none repeat scroll 0 0 #F5F5F5;;
	-moz-box-shadow:0px 5px 15px #9C9C9C; 
	-webkit-box-shadow:0px 5px 15px #9C9C9C; 
	box-shadow:0px 5px 15px #9C9C9C;
}
textarea {
	font-size:14px;
	background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CCCCCC;
    border-radius: 5px 5px 5px 5px;
    box-shadow: 1px 2px 3px #EEEEEE inset, 0 1px 0 #FFFFFF;    
    font-size: 12px;
    height: 100px;
    outline: 0 none;
    padding: 5px 5px;
    resize: none;	
}
</style>
</head>
<body>
<center>
<div style="width: 1000px;margin-top:10px;
	margin-bottom: 10px;margin-right:auto;margin-left:auto;position: relative; " >
	
<div class="shadow">
	<br/>	
	<table style="width:100%;text-align:left;">
		<tr>
			<td colspan="2" width="100%">			
				
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
					<span style="color: #F7A600;font-size: 20px;line-height: 22px;
			  				font-style: italic;margin-left:10px;" >
	  					请输入评论内容：
	  				</span>	
				<div style="margin-top: 5px;width: 100%;text-align: center;">
					
					<textarea id="commentaryContent" rows="8" style="width:97%;"></textarea>		
				</div>
			</td>
		</tr>
		<tr align="right">
			<td></td>
			<td>
				<div style="float: right;margin-top: 5px;margin-right:10px;">					
					<img id="addCommentary" src="<%=path %>/images/icons/pinglun.png"
						style="cursor: pointer;"
					>					
				</div>
			</td>
		</tr>
	</table>
	<br/>
	<div style="margin-right:auto;margin-left:auto;text-align:left;
		position: relative;width: 100%;padding-bottom:20px;" id="commentariesDiv">
		
			<s:if test="commentaries.size == 0">
				<br/>
			</s:if>
			<s:iterator value="commentaries" id="commentary" status="index">
				<div mytag='<s:property value="#index.count"/>' style="width: 100%;border-bottom: 1px dashed #E5E5E5;
					<s:if test="#index.count % 2 == 1">
						background-color: #FAFDFC;
					</s:if>"
				class="commentaries" id='<s:property value="#commentary.commentaryUid"/>'>
					<br/>
					<div style="padding-left: 10px;width: 100%;">
						<img src="<%=path %>/images/view/commentary.png" style="vertical-align: middle;"/> 
						<s:property value="#commentary.userName"/>&nbsp;
						发表时间：<s:date name="#commentary.createDate" format="yyyy-MM-dd" />&nbsp;&nbsp;
					<s:if test="#application.userUid == #commentary.userUid">
						<a href=javascript:deleteCommentary('<s:property value="#commentary.commentaryUid"/>'); >删除</a>
					</s:if>
					</div>
					<div style="margin-left: 40px;margin-top: 20px;width: 100%;margin-right: 40px;margin-bottom: 20px;">
						<s:property value="#commentary.content"/>
					</div>
				</div>
			</s:iterator>
		
	</div>
</div>	
</div>
</center>
</body>
</html>
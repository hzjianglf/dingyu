<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
List<String[]> list = (List<String[]>)request.getAttribute("list");
%>
<html>
<head>
<LINK type="text/css" href="/info/css/colorbox.css" rel="stylesheet" >
<script type="text/javascript" src="/info/js/jquery.colorbox.js"></script>
<script language="javascript">
	$(document).ready(function(){
		$(".fancybox").colorbox({
			rel:'fancybox',
			width: '60%',
			hight: '60%',
			onOpen:function(){ 
				$('#infoHtmlDiv').hide();
			},
			onClosed:function(){ 
				$('#infoHtmlDiv').show();
			}
		});
	});
</script>
</head> 
<body>
<%
if (list.size() > 0){
%>
<table align="left" cellpadding="0" cellspacing="0" border="0" width=100% style="border-radius: 5px; -moz-border-radius:5px; -webkit-border-radius:5px; border: 5px solid #f5f5f5;">
	<tr>
		<td height=30 colspan=3 style="padding-left: 10px;background: #e7e7e7;"><font class="font1">附件列表   个数：<%=list.size()%></font></td>
	</tr>
	<tr>
		<td align="left">
            <table border=0 cellpadding="5" cellspacing="5" width=100%>
            	<tr>
				<%
				for(int i = 0; i < list.size(); i++){
					String[] string = list.get(i);
					if (i > 0 && i % 5 == 0){
				%>
					</tr>
					<tr>
				<%
					}
				%>
                	<td align="left" width=25% valign="top">
                		<table border="0">
                			<%
                			if (string[2].equals("image")){
                			%>
                			<tr><td align="center"><a class="fancybox" href="/info/infoImgShow.action?id=<%=string[0]%>&type=ImageShow" title="<%=string[1]%>"><img border="0" src='<%=string[3]%>' width="80" height="80"/><br><%=string[1]%></a></td></tr>
                			<%}%>
                			<%
                			if (string[2].equals("office")){
                			%>
                			<tr><td align="center"><a href="/info/infoFileShow.action?id=<%=string[0]%>&type=docDown"><img border="0" src='<%=string[3]%>' width="80" height="80"/><br><%=string[1]%></a></td></tr>
                			<%}%>
                			<%
                			if (string[2].equals("other")){
                			%>
                			<tr><td align="center"><a href="/info/infoFileShow.action?id=<%=string[0]%>&type=docDown"><img border="0" src='<%=string[3]%>' width="80" height="80"/><br><%=string[1]%></a></td></tr>
                			<%}%>
                		</table>
                	</td>
               	<%}%>
               	</tr>
            </table>
		</td>
	</tr>
</table>
<%}%>
</body>
</html>



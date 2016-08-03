<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String id = request.getParameter("id");
String name = request.getParameter("name");
%>
<LINK rel="stylesheet" type="text/css" href="css/pagination.css">
<script type="text/javascript" src="js/pagination.js"></script>
<table border=0 width=100% height=100%>
	<tr>
		<td align="center">
		<table width="96%" border="0" align="center" cellspacing="0" style="vertical-align:top;" height=35 >
			<tr>
				<td align="left" colspan=2 height=35 style="background:url(images/bullet_green.png) no-repeat left center; padding-left:20px; border-bottom:1px solid #d6d6d6;"><%=name%></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign=top style="color:#3a9fe1; font-size:12pt;">
			<div id="shujiaList_div"></div>
			<DIV id="Pagination" class="pagination" style="float: right; padding-right: 100px; padding-top: 10px; height: 30px;"></DIV>
		</td>
	</tr>
</table>
<script>
	function showInfoList2(obj){
		if ($(obj).attr("type") == "information"){
			$("#infoShow_div").empty();
			$("#infoShow_div").load("/info/index1.jsp",{"infoID":$(obj).attr("id"), "width": "100%"})
		}else{
			$("#infoShow_div").empty();
			$("#infoShow_div").load("list1.jsp",{"id":$(obj).attr("id"), "name":$(obj).attr("name")})
		}
	}
	
	$("#shujiaList_div").load("list2.jsp",{"id":"<%=id%>", "pageNo": 0})
	
	function pageselectCallback(start, jq){	
		$("#shujiaList_div").empty();
		$("#shujiaList_div").load("list2.jsp",{"id":"<%=id%>", "pageNo": start});
	}

</script>

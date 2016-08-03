<%@page import="net.risesoft.soa.info.model.InformationDesc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%@page import="net.risesoft.soa.info.model.Information"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.ac.manager.AccessControlService"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.ac.model.Operation"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
	String infoID = request.getParameter("infoID");
	String search = request.getParameter("search");
	
		String shortacnum = "";
try{
shortacnum=request.getParameter("shortacnum");
}catch(Exception e){}


String acid = "";
try{
acid=request.getParameter("acid");
}catch(Exception e){}
	
	Information information = InformationList.get(infoID);
	String actionName = "";
	if (sessionUser != null){
		AccessControlService accessControlService = SpringUtil.getBean("accessControlService");
		List<Operation> operations = accessControlService.getOperations(sessionUser.getUserUID(), infoID, null);
		for(Operation operation : operations){
			actionName = actionName + operation.getId();
		}
	}
	String tableName =information.getTableName();
	String url = information.getUrl();
	List<InformationDesc> list = new ArrayList<InformationDesc>();
	List<InformationDesc> informationDescs = information.getElements();
	for (InformationDesc informationDesc : informationDescs) {
		String listName = informationDesc.getListName();
		if (informationDesc.isSearch()) {
			list.add(informationDesc);
		}
	}
	int pageSize = 10;
%>

<LINK rel="stylesheet" type="text/css" href="/info/css/pagination.css">
<script type="text/javascript" src="/info/js/pagination.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/languages/jquery-ui-datepicker-zh-CN.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/languages/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/js/jquery.form.js"></script>
<style type="text/css">
	.input1{
		border: 1px solid #ccd0d0;
		height: 20px;
		padding-left: 5px;
	}
</style>

<table border=0 width=90% align=center>
    <%if (actionName.contains("create")){%>
    <tr>
	
	    <td align=right height=40>
	    	<a href="javascript:ACShow();" class="link2">返回</a>
	    </td>
    </tr>
    <%}else{%>
    <tr>
    	<td height=10>&nbsp;</td>
    </tr>
    <%}%>
    <tr>
    	<td align=center>
    		<form id=form1>
    		<table border=0 cellspacing="10" style="background:#eeeeee; border-radius: 5px; -moz-border-radius:5px; -webkit-border-radius:5px;">
    			<tr>
    				<td>
    					<table border=0>
    						<tr>
    						<td nowrap colspan=2>
									<table border=0 cellspacing="0">
										<tr>
											<td nowrap>发布时间段:</td>
											<td style="padding-right:5px;"><input class="input1" id="createTime_start" name="createTime_start" value="" size=8></td>
											<td align="center">--</td>
											<td style="padding-right:5px;"><input class="input1" id="createTime_end" name="createTime_end" value="" size=8></td>
										</tr>
									</table>
								</td>
    						
    						
    							<%if (list.size() > 0) {
    								for(int i = 0; i < list.size(); i++){
    									InformationDesc informationDesc = list.get(i);
    									String id  = informationDesc.getFormName().toUpperCase();
    							%>
    								<td align="left" nowrap><%=informationDesc.getListName()%>:</td>
									<td style="padding-right:5px;"><input class="input1" name="<%=id%>" id="<%=id%>" value=""></td>
								<%
										if ("日期".equals(informationDesc.getSearchType())){
								%>
									<script type="text/javascript">
										$('#<%=id%>').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: 'yy-mm-dd'});
									</script>
								<%			
										}
	    								if(i==1){
	    									out.println("</tr><tr>");
	    								}
	    								if(i>3&&(i-1)%3==0){
	        								out.println("</tr><tr>");
	    								}
    								}
    							}
    							if (list.size() == 0){
    							%>
    							<td align="left" nowrap>标题:</td>
								<td style="padding-right:5px;"><input class="input1" id="title" name="title" value=""></td>
    							<%
    							}
    							%>
								
							</tr>
    					</table>
    				</td>
    				<td width=30 align="center">
    					<img src="/info/images/search.png" alt="查询" title="查询" height=28 width=28 border=0 style="cursor: pointer;" onclick="javascript:searchInformation();">
    				</td>
    			</tr>
     		</table>
     		</form>
    	</td>
	</tr>
	<tr>
		<td height=5>&nbsp;</td>
	</tr>
    
	<tr>
		<td>
			<div id="talkList"></div>
		</td>
	</tr>
</table>
<DIV id="Pagination" class="pagination" style="float: right;padding-right: 100px;padding-top: 10px;padding-bottom: 10px;"></DIV>

<script type="text/javascript">
	$(document).ready(function() {  
		$('#createTime_start').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: 'yy-mm-dd'});

		$('#createTime_end').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: 'yy-mm-dd'});

		$("#talkList").load("/info/infoList2.action",{"infoID": "<%=infoID%>", "shortacnum":"<%=shortacnum%>","action":"<%=actionName%>", "search": "<%=search%>","acid":"<%=acid%>"});
	}); 

	

	//分页回调函数 start为起始位置
	function pageselectCallback(start, jq){	
		//$("#talkList").empty();
		$("#talkList").load("/info/infoList2.action?" + $('#form1').formSerialize(),{"infoID": "<%=infoID%>", "shortacnum":"<%=shortacnum%>","action":"<%=actionName%>", "pageNo": start, "search": "<%=search%>","acid":"<%=acid%>"});
	}

	function searchInformation(){
		$("#talkList").load("/info/infoList2.action?" + $('#form1').formSerialize(),{"infoID": "<%=infoID%>","shortacnum":"<%=shortacnum%>", "action":"<%=actionName%>", "search": "<%=search%>","acid":"<%=acid%>"});
	}
</script>
 
<script type="text/javascript">
function ACShow(){
	$("#infoMainDiv").empty();
  $("#infoMainDiv").load("/info/display/panel.jsp", {"infoID":"<%=infoID%>", "shortacnum":"<%=shortacnum%>","search":"<%=search%>","acid":"<%=acid%>"});
}
function infoAdd(id){
	$("#infoMainDiv").empty();
    $("#infoMainDiv").load("/info/display/infoAdd.jsp", {"infoID": id,"shortacnum":"<%=shortacnum%>","operation":"create","acid":"<%=acid%>"});
}
function infoAdd2(infoid,id){
	$("#infoMainDiv").empty();
    $("#infoMainDiv").load("/info/display/infoAdd.jsp", {"infoID": infoid,"operation":"create","shortacnum":"<%=shortacnum%>","myid":id,"acvarsion":"acvarsion","acid":"<%=acid%>"});
}
function infoEnable(id){
	var message = '是否要启用此信息?';
	var bool = window.confirm(message);
    if (bool){
		$.post('/info/infoList2.action', 
				{ operation: 'enable', id: id},
				function(data){
					if (data.success){
						$('#message_success_div').html(data.message);
						$('#dialog_success_message').dialog('open');
						$("#" + id).remove();						
						$.post('/csbwork/mnicontro/mfstatusoper.do?infoid='+id+'&oper=enable');// add by sunming 20150921
					}else{
						$('#message_cancel_div').html(data.message);
						$('#dialog_cancel_message').dialog('open');
					}
				},
				"json"
		);
    }
}

function infoEdit(id){
	$("#infoMainDiv").empty();
	$("#infoMainDiv").load("/info/display/infoAdd.jsp", {"id":id,"shortacnum":"<%=shortacnum%>", "operation":"edit","acid":"<%=acid%>"});
}

</script>  
  
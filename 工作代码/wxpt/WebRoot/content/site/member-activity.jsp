<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="testCookie.jsp" flush="true" />
    <base href="<%=basePath%>">
    
    <title>促销活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="/wxpt/manager/css/default.css">
<link rel="stylesheet" type="text/css" href="/wxpt/manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/wxpt/manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"href="/wxpt/manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="/wxpt/manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/hd.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/jquery.form.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/WebCalendar.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/memberactivity.js"></script>
<script type="text/javascript" src="/wxpt/manager/js/ajaxfileupload.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#addCheckForm,#updatequestion,#addcheckdiv,#toolbar,#active {
	/* visibility: hidden; */
}

.serchTest {
	_margin-top: -20px;
	_margin-bottom: -18px;
}

*+html #serchTest {
	*margin-top: -20px;
	*margin-bottom: -18px;
}

 </style>
 
 
 <style type="text/css">

.test a {
	vertical-align: center;
	display: block;
	float: left;
	height: 20px;
	padding-bottom: 2px;
	border: 1px solid #EFEFEF;
}

.test a:link {
	text-decoration: none;
	color: #000;
}

.test a:active {
	text-decoration: none;
	color: #000
}

.test a:visited {
	text-decoration: none;
	color: #000
}

.test a:hover {
	text-decoration: none;
	color: #000;
	background: #eaf2ff;
	border: 1px solid #dddddd;
}

.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
	
}

.kehus{
/* background:#FFFFFF;  */
background:#E2EDFF;
border:none;

}


.chakantable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
   margin:10px auto;

  }
  
  .chakantable td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}

.liuyanban{
    width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
   margin:10px auto;

  }
  
  .liuyanban td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}
    
    .beijing{
  background:#E5F0F4;
    
    }
    
    
       .tianjialianxi{
    width:950px;
    border-style: solid; 
    border-color: #CCCCCC; 
    border-width: 1px; 


  }
  
  .tianjialianxi td{ 
    border-style: solid; 
    border-color: #CCCCCC; 
    border-width: 1px;}
    
     .xx21 {
	margin: 2px 2px 2px 2px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
	
}
</style>




  </head>
  
  <body style="background-color:white;padding:0px;">

     <table  id="active">

	</table>
<script>
	
</script>
	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
			<td style="position: relative;left: -220px;">
			
				<td>
				<table align="right">
						<tr>
							 <td><a href="javascript:openupload()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传海报</span>&nbsp;&nbsp;&nbsp;</a>
							</td>
						 <td><a href="javascript:addActivity()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;&nbsp;</a>
							</td> 							 <td><a href="javascript:delactivity()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;&nbsp;</a>
							</td> 
							 <td><a href="javascript:updateActivity()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;&nbsp;</a>
							</td> 
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div> 


	
		<!-- 上传的div -->
	 <div id="shangchuanhaibao" class="easyui-window" scroll="no"
		title="上传海报"
		style="background:#E2EDFF;width:300px;height:150px;height:130px\9\0;left:500px; top:100px;overflow: hidden;visibility:hidden;"
		iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">

    
<div style="border: 1px solid #3B96CD;border-radius: 8px;margin:10px auto;width:260px;*margin-left:11px;">
  <table width="200"  cellspacing="0" cellpadding="0" style="margin:10px auto;">
    <tr>
      <td> <input type="file" id="haibao" name="haibao"style="*margin-left:15px;" /></td>
    </tr>
    <tr>
      <td  align="center"> 
      <input type="hidden"  id="activityid" name="activityid" />
       <div style="margin-top: 20px;*margin-left:35px;" >
     <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:void(0)" onClick="shangchuanhaibao()">提交</a>
     <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="close2()">取消</a>
    </div></td>
   
    </tr>
  </table>

</div>
		</div>
	
	<!-- 双击的div -->
	 <div id="chakanxiangqing" class="easyui-window" scroll="no"
		title="查看详情"
		style="background:#E2EDFF;width:500px;height:510px;left:300px; top:80px;[;height:490px;];*height:490px;*top:40;height:470px\9\0;visibility:hidden;"
		iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">
		
		<div style="border: 1px solid #3B96CD;border-radius: 8px;margin:10px auto;*margin-left:12px; width:460px;">
		<table border="1" class="chakantable" cellpadding="0" cellspacing="0" style="margin:10px auto;width:440px;height:400px;">
    <tr>
    <input type="hidden" id="haibaoming" name="haibaoming"/>
      <td align="center">促销编号 </td>
      <td align="center"><input type="text" id="cuxiaobianhao" name="cuxiaobianhao" style="border:none;" class="kehus"  readonly="true"/></td>
      <td align="center">活动标题 </td>
      <td align="center"><input type="text" id="huodongbiaoti" name="huodongbiaoti" style="border:none;" class="kehus"  readonly="true"/></td>
    </tr>
    <tr>
      <td align="center">开始时间 </td>
      <td align="center"><input type="text" id="kaishishijian" name="kaishishijian" style="border:none;" class="kehus"  readonly="true"/></td>
      <td align="center">结束时间</td>
      <td align="center"><input type="text" id="jieshushijian" name="jieshushijian" style="border:none;" class="kehus"  readonly="true"/></td>
    </tr>
    <tr>
     <td align="center" >活动内容</td>
      <td align="center" colspan="3"><!-- <input type="text" id="huodongneirong" name="huodongneirong" style="border:none;" readonly="true"/> -->
      <textarea rows="4" cols="58" id="huodongneirong" name="huodongneirong" style="resize:none;" readonly="true" class="kehus"  ></textarea>
      </td>
    </tr>
    <tr>
       <td align="center">活动海报</td>
      <td colspan="3" align="center"><span id="ts"></span>
      </td>
    </tr>
  </table>
	</div>	

		

		
		
		</div>
				<!--     大图div 
 <div id="datu"  style="position:absolute;left:200px;top:-0px;z-index:10;display:none; background-color: #F1F19B;width:500px;height:500px;border:1px solid red;"> 
 </div> 
               大图div end -->

<!--双击显示div  -->
	<div id="addActivity" scroll="no" class="easyui-window" title="促销活动添加" style="background:#E2EDFF;hidden;width:550px;height:320px;left:380px;top:20px;[;height:290px;];*height:300px;height:290px\9\0;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
     
     
 <form id="addActivitys" enctype="multipart/form-data"
		method="post">
   <div style="width:500px;margin:0 auto; margin-top: 20px;*margin-left:15px; ">
			<div class="xx21" >
			  <table  width="500" height="100"  border="0"  cellspacing="0" cellpadding="0">
		     
		      <tr>
		        <td  height="30px"  align="right">开始时间：</td>
		        <td> <input type="text" id="activityStartTime" name="activityStartTime" onclick="SelectDate(this,'yyyy/MM/dd');" /></td>
		      </tr>
		       <tr>
		        <td  align="right" height="30px">结束时间：</td>
		        <td> <input type="text" id="activityEndTime" name="activityEndTime" onclick="SelectDate(this,'yyyy/MM/dd');" /></td>
		      </tr><%--
		      <tr>
		        <td height="30px" align="right">活动图片：</td>
		        <td>
		           <input type="file" id="imageUrl" name="imageUrl" value="上传"/>
		        </td>
		        </tr>
					--%><tr>
					<td align="right">
					 活动标题：
					</td>
					<td>
					<input  type="text" id="activityTitle" name="activityTitle"  style="width:374px" >
					</td>
				</tr>
				<tr>
					<td align="right">
					具体内容：
					</td>
					<td>
					<textarea cols=70 rows=7 id="activityContent" name="activityContent" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
		    </table>
			<center>
				 <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:addActivitys()">提交</a>
		   		 <a class="easyui-linkbutton" style=" margin-left:30px;"  iconCls="icon-cancel" href="javascript:void(0)" onClick="closeadd()">取消</a>
						
			</center>
	</div>

</div>
</form>
     
     </div>
     <div id="updateActivity" scroll="no" class="easyui-window" title="促销活动修改" style="background:#E2EDFF;hidden;width:550px;height:320px;left:380px;top:20px;[;height:290px;];*height:300px;height:290px\9\0;"
    iconCls="icon-edit"  closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
     <form id="updateActivitys" enctype="multipart/form-data"
		method="post">
   <div style="width:500px;margin:0 auto; margin-top: 20px;*margin-left:15px;">
			<div class="xx21" >
			 <input type="hidden" name="activityId" id="activityId"/>
			  <table  width="500" height="100"  border="0"  cellspacing="0" cellpadding="0">
		    
		      <tr>
		        <td  height="30px"  align="right">开始时间：</td>
		        <td> <input type="text" id="activityStartTimes" name="activityStartTime" onclick="SelectDate(this,'yyyy/MM/dd');" /></td>
		      </tr>
		       <tr>
		        <td  align="right" height="30px">结束时间：</td>
		        <td> <input type="text" id="activityEndTimes" name="activityEndTime" onclick="SelectDate(this,'yyyy/MM/dd');" /></td>
		      </tr><%--
		      <tr>
		        <td height="30px" align="right">活动图片：</td>
		        <td>
		           <input type="file" id="imageUrls" name="imageUrl" value="上传"/>
		        </td>
		        </tr>
					--%><tr>
					<td align="right">
					 活动标题：
					</td>
					<td>
					<input  type="text" id="activityTitles" name="activityTitle"  style="width:374px" >
					</td>
				</tr>
				<tr>
					<td align="right">
					具体内容：
					</td>
					<td>
					<textarea cols=70 rows=7 id="activityContents" name="activityContent" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
		    </table>
			<center>
				 <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:updateActivitys()">提交</a>
		   		 <a class="easyui-linkbutton" style=" margin-left:30px;"  iconCls="icon-cancel" href="javascript:void(0)" onClick="closeupdate()">取消</a>
						
			</center>
	</div>

</div>
</form>
     </div>

  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<!-- <jsp:include page="testCookie.jsp" flush="true" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet" href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="../manager/js/hd.js"></script>
<script type="text/javascript" src="../manager/js/jquery.form.js"></script>
<script type="text/javascript" src="../manager/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../manager/js/huiyuan.js"></script>
<script type="text/javascript" src="../manager/js/WebCalendar.js"></script>
<script type="text/javascript" src="../manager/js/ajaxfileupload.js"></script>
<style type="text/css">
#serchTest,#edit,#add,#ff,#addCheckForm,#updatequestion,#addcheckdiv,#toolbar {
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
background:#FFFFFF;
border:none;

}

.kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
   margin:10px auto;

  }
  
  .kehustable td{ 
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
</style>

  </head>
  
  <body style="background-color:white;padding:0px;" onload="shaungji()">
<!--    sssssssssssss --> 
<script type="text/javascript">
function shaungji(){
	
	
	$('#huiyuan').datagrid({
		onDblClickRow:getSelects2	
	 });
}

//双击触发的事件
function getSelects2(){
//alert("jspjsp");

$('#huiyuanxingqing').window('open');

 var select = $('#huiyuan').datagrid('getSelected');
	
	  if(select){
	  var memberId = select.memberId;
	   $('#huiyuanid').val(memberId);
	   $('#huiyuankahao').val(select.cardId);
	   $('#weixinhao').val(select.weixinId);
	   $('#mima').val(select.password);
	   $('#yonghuming').val(select.username);
	   $('#xingbie').val(select.sex);
	   $('#nianling').val(select.age);
	   
	    $('#tianjiashijian').val(select.addTime);
	    $('#jieshushijian').val(select.endTime);
	    $('#dizhi').val(select.address);  
	    $('#dianhua').val(select.phone); 
	    $('#cunkuan').val(select.saveMoney);  
	    $('#huiyuanjibie').val(select.memberGrade);  
	    $('#zhuangtai').val(select.memberFreeze);   
	     
	     
	    $.ajax({
 		 secureuri : false,
         type:"POST",
         url:"../site/member!quliuyan",
         data:{
         'memberId':memberId
         },
       secureuri : false,//一般设置为false
      
        success:function (text){
        $("#liuyanban").html(text);
        },error:function callback(text){
     	   
        }
      }); 
	     
	     
	     
	     
	  }


}
</script>
   <table toolbar="#toolbar" id="huiyuan">
	</table>

	<form style="display: none;" id="ejob_form" name="imageForm" method="post" target="hidden_frame" enctype="multipart/form-data">

		<div align="center">				
		  	 上传企业图片:<input id="upload" type="file" name="upload" style="width:200px; height:20px; margin-left:20px;" /></br>
  	 <span style="position:relative;left:30000px;">*</span><span style="font-size: 10px;">上传图片的长度必须是138px,高度104px;</span>
		</div>									

  	</form>


  		<div id="enterprisepicture" class="easyui-window" title="企业图片"
		style="background:#E0ECFF;padding: 8px;width:400px;height:auto;top: 50px;display: none; overflow:hidden"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div style="border:1px solid #95B8E7;border-radius:8px;margin:0 auto;height:auto;line-height:30px;">
		<div id="ejobpicture" >
		</div>
	
		</div>
		<div>&nbsp;</div>
		<div style="position:relative;left:100px;display: none;" id="uploadok">	
	     <a class="easyui-linkbutton" style="left: 40px;" iconCls="icon-ok"
			href="javascript:uploadFile();" >上传</a> <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:closeFileUpload();" onClick="close2()">取消</a></div>

		
	</div>
	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
			<td>
			
	用户名：<input type="text" id="userName"/>
		
												
			</td>
			
<<<<<<< .mine
			<td style="position: relative;left: -40px;">
			微信号：<input id="weixin_id" type="text"/>
=======
			<td style="position: relative;left: -66px;">
			会员卡号：<input id="weixin_id" type="text"/>
>>>>>>> .r6382
			</td>
<<<<<<< .mine
			<td style="position: relative;left: -90px;">
=======
			<td style="position: relative;left: -136px;">
>>>>>>> .r6382
			添加时间：<input type="text" id="add_time" onclick="SelectDate(this,'yyyy-MM-dd');"/>
			</td>
		
			<td style="position: relative;left: -140px;">
			用户状态： <select id="member_freeze">
			<option value="-1" selected="selected">全部</option>
			<option value="0">冻结</option>
			<option value="1">解冻</option>
			</select>
			</td>
			<td style="position: relative;left: -140px;">
				<a  href="javascript:searchJob();"><span class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>查询&nbsp;&nbsp;  </a></td>
				<td>
				<table align="right">
						<tr>
							 <td><a href="javascript:uploadUi()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>上传&nbsp;&nbsp;&nbsp;</a>
							</td> 
												
							 <td><a href="javascript:dongjie()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>冻结&nbsp;&nbsp;&nbsp;</a>
							</td> 
							 <td><a href="javascript:jiedong()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>解冻&nbsp;&nbsp;&nbsp;</a>
							</td> 
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>


<!--双击显示div  -->
	<div id="huiyuanxingqing" scroll="no" class="easyui-window" title="会员详情及留言回复" style="background:#FFFFFF;hidden;width:800px;height:500px;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
	   
	   <div style="border:1px solid #7CB7DD;border-radius:8px;width:760px;margin:10px auto; ">
	     <table class="kehustable"  cellpadding="0" cellspacing="0">
	     <input type="hidden" id="huiyuanid" name="huyuanid" />
	     <tr>
	     	<td class="beijing" >
             会员卡号：
	        </td>
	        <td>
	        <input type="text" id="huiyuankahao" name="huiyuankahao" readonly="true" class="kehus" />
	        </td>
	        
	        	<td class="beijing">
             微信号：
	        </td>
	        <td>
	        <input type="text" id="weixinhao" name="weixinhao" readonly="true" class="kehus"  />
	        </td>
	     </tr>
	     
	      <tr>
	     	<td class="beijing">
             密码：
	        </td>
	        <td>
	        <input type="text" id="mima" name="mima" readonly="true" class="kehus"  />
	        </td>
	        
	        	<td class="beijing">
              用户名：
	        </td>
	        <td>
	        <input type="text" id="yonghuming" name="yonghuming" readonly="true" class="kehus"  />
	        </td>
	     </tr>
	     
	     <tr>
	     	<td class="beijing">
            性别：
	        </td>
	        <td>
	        <input type="text" id="xingbie" name="xingbie" readonly="true" class="kehus"  />
	        </td>
	        
	        	<td class="beijing">
              年龄：
	        </td>
	        <td>
	        <input type="text" id="nianling" name="nianling" readonly="true" class="kehus"  />
	        </td>
	     </tr>
	    
	    	     <tr>
	     	<td class="beijing">
            添加时间：
	        </td>
	        <td>
	        <input type="text" id="tianjiashijian" name="tianjiashijian" readonly="true" class="kehus"  />
	        </td>
	        
	        <td class="beijing">
              结束时间：
	        </td>
	        <td>
	        <input type="text" id="jieshushijian" name="jieshushijian" readonly="true" class="kehus"  />
	        </td>
	     </tr> 
	     
	     
	      <tr>
	     	<td class="beijing">
           地址：
	        </td>
	        <td>
	        <input type="text" id="dizhi" name="dizhi" readonly="true" class="kehus"  />
	        </td>
	        
	        <td class="beijing">
              电话：
	        </td>
	        <td>
	        <input type="text" id="dianhua" name="dianhua" readonly="true" class="kehus"  />
	        </td>
	     </tr> 
	     
	     
	     
	       <tr>
	     	<td class="beijing">
           存款：
	        </td>
	        <td>
	        <input type="text" id="cunkuan" name="cunkuan" readonly="true" class="kehus"  />
	        </td>
	        
	        <td class="beijing">
              会员级别：
	        </td>
	        <td >
	        <input type="text" id="huiyuanjibie"  name="huiyuanjibie" readonly="true" class="kehus"  />
	        </td>
	     </tr> 
	     <tr>
	       <td class="beijing">
                 状态：
	        </td>
	        <td colspan="3">
	        <input type="text" id="zhuangtai"  name="zhuangtai" readonly="true" class="kehus" />
	        </td>
	     </tr>
	     </table>
	     </div>
	     
	     
	     <!--留言表  -->
	     <div style="border:1px solid #7CB7DD;border-radius:8px;width:760px;height:240px;margin:10px auto;overflow-y:scroll; ">
	            <table id="liuyanban" class="liuyanban"  cellpadding="0" cellspacing="0">
	          <tr>
	            <td align="center" style="width:20%;background:#E5F0F4;">商家</td>
	            <td align="center"  style="width:30%;background:#E5F0F4;">留言标题</td>
	            <td align="center"  style="width:40%;background:#E5F0F4;">留言内容</td>
	            <td align="center"  style="width:10%;background:#E5F0F4;">操作</td>
	          </tr>
	          
	          <s:iterator value="listmessage2" var="lms">
	         <tr >
	            <td ><s:property value="#lms.member.cardId"/></td>
	            <td ><s:property value="#lms.messageTitle"/></td>
	            <td ><s:property value="#lms.messageContent"/></td>
	           <td align="center" ><input type="button" id="huifu" name="huifu" value="回复" onclick="huifu('<s:property value="#lms.messageId"/>')"/></td>
	        </tr>
	        
	         <s:iterator value="#lms.listhui" var="lmshui">
	         <tr>
	            <td ><s:property value="#lmshui.member.cardId"/></td>
	            <td ><s:property value="#lmshui.messageTitle"/></td>
	            <td ><s:property value="#lmshui.messageContent"/></td>
	            <td align="center" >商家已回复</td>
	        </tr>
	        </s:iterator>
	        
	           <tr id="<s:property value='#lms.messageId'/>"   style="display:none;" >
		           <td colspan="3"><input type="text" id="<s:property value="#lms.messageId"/>tt" name="<s:property value="#lms.messageId"/>" style="width:600px;"/></td>
		           <td align="center"><input type="button" class="spb" id="tijiao" name="tijiao" value="提交" onclick="tijiao('<s:property value="#lms.messageId"/>tt','<s:property value="#lms.messageId"/>')" /></td>
	           </tr>

	        </s:iterator>
	       </table>
	       <!--留言表  -->
	     </div>
	     
     </div>
<!--双击显示div  end  -->






  </body>
</html>

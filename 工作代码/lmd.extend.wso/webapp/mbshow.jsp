<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<LINK type="text/css" href="mbcss/style.css" rel="stylesheet" >
<LINK type="text/css" href="mbcss/colorbox.css" rel="stylesheet" >
<script src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<STYLE type="text/css">
.headWrap{-moz-box-shadow:0 1px 2px rgba(23,87,117,0.5);-webkit-box-shadow:0 1px 2px rgba(23,87,117,0.5);box-shadow:0 1px 2px rgba(23,87,117,0.5)}
.headWrap .headShadow{ opacity:.9; border-bottom:#2596c2 1px solid; background-color:#36a4d2;background:-webkit-gradient(linear,left top,left bottom,from(#45addd),to(#279cc8));background:-moz-linear-gradient(top,#45addd,#279cc8);background:transparent\9;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#45addd',endColorstr='#279cc8');background:-ms-linear-gradient(top,#45addd,#279cc8);}
.body {
    background: url("../images/wrapbg_v0.0.1.jpg") no-repeat scroll center top #73CFF1;
    color: #333333;
    font: 12px/1.75 Tahoma,Arial,sans-serif,宋体;
    height: 100%;
}
.png{
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="css/images/info.png");
	background:none;
	width:118px;
}
.navradiu { 
	-moz-border-radius:5px;
	-webkit-border-radius:5px; 
	border-radius:5px; 
	-moz-box-shadow:2px 2px 15px #b9b9b9; 
	-webkit-box-shadow:2px 2px 15px #b9b9b9; 
	box-shadow:2px 2px 15px #b9b9b9;
	behavior: url(PIE.htc);
}
.tab2{border-top:1px solid #9eadc3; border-left:1px solid #9eadc3; margin:1px auto; font-size:12px; background:#f6f9fb}
.tab2 td{border-bottom:1px solid #9eadc3; border-right:1px solid #9eadc3;  height:40px;}
</STYLE>
</head>
<body class="body">
<DIV id="mainWrapper" class="navradiu clear">
<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 960px; height: 600px; border-radius: 5px 5px 5px; background-color: rgb(255, 255, 255);">
	<tr>

		<td align="left" height="20" style="border-bottom: 1px solid #D1D1D1; padding-left: 20px;"><font class="font1">服务面板</font></td>
		<td align="right" style="border-bottom: 1px solid #D1D1D1; padding-right: 20px;"><span id="docDownload_span" style="font-size:10pt;">&nbsp;</span></td>
	</tr>
	<tr>
		<td colspan=2 align="center" valign="top" height="100%">
		<table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
		<tr>	<td style="background:#e8eff4;line-height:30px; text-align:center; color:#3d516f; font-size:20px; font-weight:bold; border-bottom:1px solid #9eadc3; background:#f6f9fb">
服务信息</td></tr>
		<tr>
			<td>
			
			
 
  <form> 
   <table id="tabl" align="center" border="0" cellpadding="0" cellspacing="0" width="900"> 
     <tr> 
      <td class="titl" style="background:#e8eff4"></td> 
     </tr> 
     <tr> 
      <td height="3"> 
       <table class="tab2" align="left"  border="0" cellpadding="0" cellspacing="0" height="500" width="80%"> 
        <tr > 
          <td  style="width: 150px; text-align: center;">资源编号:</td> 
          <td  style="width: 200px; "><input id="zybhId" readonly="readonly"  style="width: 200px;" value="${mb.zybh }"  type="text"></td> 
          <td  style="width: 150px; text-align: center;">版本号:</td> 
          <td  style="width: 200px; "><input  readonly="readonly"  style="width: 200px;" value="${mb.bbh }"  type="text"></td> 
         </tr> 
         <tr> 
          <td  style="width: 150px; text-align: center;">应用名称:</td> 
          <td  style="width: 200px;"><input readonly="readonly" value="${mb.fwmc }"  style="width: 200px; "  type="text"></td> 
          <td  style="width: 150px; text-align: center;">节点名称:</td> 
          <td  style="width: 200px;"><input  readonly="readonly" value="${mb.jdmc }"  style="width: 200px; "  type="text"></td> 
         </tr> 
         <tr> 
          <td  style="width: 150px; text-align: center;"> 资源名称: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.zymc }"  style="width: 200px; " value="管理员"  type="text"> </td> 
          <td  style="width: 150px; text-align: center;"> 运行状态: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.yxzt }"  style="width: 200px; " value="2015-11-17"  type="text"> </td> 
         </tr> 
         <tr> 
          <td  style="width: 150px; text-align: center;"> 消息流线程实例数目: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.additionalInstances }"  style="width: 200px; "  type="text"> </td> 
          <td  style="width: 150px; text-align: center;"> 消息流文件扩展名: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.fileExtension }"  style="width: 200px; "  type="text"> </td> 
         </tr> 
         <tr> 
           <td  style="width: 150px; text-align: center;"> 消息流部署时间: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.deployTime }"  style="width: 200px; "  type="text"> </td> 
           <td  style="width: 150px; text-align: center;"> 集成服务名称 : </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.jcfwmc }"  style="width: 200px; "  type="text"> </td> 
         </tr> 
         <tr> 
          <td  style="width: 150px; text-align: center;"> 已运行时间: </td> 
          <td  style="width: 200px;"><input  readonly="readonly" value="${mb.runtime }"  style="width: 200px; "  type="text">  </td> 
       	  <td  style="width: 150px; text-align: center;"> 服务链接: </td> 
          <td  style=""><a href='${mb.mburl }' target="_blank">WSDL</a></td> 
        </tr>
           <tr> 
          <td  style="width: 150px; text-align: center;"> 请求数: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.count }"  style="width: 200px; "  type="text"> </td> 
          <td  style="width: 150px; text-align: center;"> 响应计数: </td> 
          <td  style="width: 200px;"><input  readonly="readonly" value="${mb.sucesscount }"  style="width: 200px; "  type="text">  </td> 
         </tr>  
         <tr> 
          <td  style="width: 150px; text-align: center;"> 失败数: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.errorcount }"  style="width: 200px; "  type="text"> </td> 
          <td  style="width: 150px; text-align: center;"> 最大响应时间: </td> 
          <td  style="width: 200px;"><input  readonly="readonly" value="${mb.maxtime }"  style="width: 200px; "  type="text">  </td> 
         </tr>  
         <tr> 
          <td  style="width: 150px; text-align: center;"> 最小响应时间: </td> 
          <td  style="width: 200px;"> <input  readonly="readonly" value="${mb.mintime }"  style="width: 200px; "  type="text"> </td> 
          <td  style="width: 150px; text-align: center;"> 平均响应时间: </td> 
          <td  style="width: 200px;"><input id="pjxysj" readonly="readonly" value="${mb.avgtime }"  style="width: 200px; "  type="text">  </td> 
         </tr>  
         <tr> 
          <td  style="width: 150px; text-align: center;"> 服务资源地址: </td> 
          <td  colspan="3" style="width:200px;"> <input  readonly="readonly" value="${mb.mburl }"  style="width: 550px; "  type="text"> </td> 
         </tr>  
         <!-- 服务响应图表展示 -->
         <tr>
           <td colspan="4"  style="width: 550px; text-align: center;"> 
           <div id="container" style="width:700px;height:400px;margin:0 auto;"></div>
            </td> 
         </tr>
       </table> </td> 
     </tr> 
   </table> 
  </form> 
</td>
		</tr>
		</table>
		
		
		</td>
	</tr>
	
</table>
</DIV>
</body>
<script src="js/showchart.js"></script>
</html>

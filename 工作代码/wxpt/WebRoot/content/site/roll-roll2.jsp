<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
#lottery { background: url("/wxpt/manager/images/disc-bg1.gif?v=79804") no-repeat scroll 0 0 transparent; height: 800px; width:800px;top:600px;!important; }
#lottery .arrow { background: url("/wxpt/manager/images/zhizhen.png?v=1bde2") no-repeat scroll 0 0 transparent; height: 243px; left: 274px; position: absolute; top: 200px; width: 61px; }
#lottery .lot-btn { height: 92px; left: 258px; overflow: hidden; position: absolute; top: 350px; width: 91px; }
#lottery .lot-btn span { cursor: pointer; display: block; height: 92px; position: relative; width: 91px; }
#lottery .first span { background: url("/wxpt/manager/images/buttons_01.png?v=8bc8e") no-repeat scroll 0 0 transparent; }
.image { left: 47px; top: 47px; }
/*btn css3*/
.button {display: inline-block;zoom: 1; *display: inline;vertical-align: baseline;margin: 0 2px;outline: none;cursor: pointer;text-align: center;text-decoration: none;font: 14px/100% Arial, Helvetica, sans-serif;padding:0.25em 0.6em 0.3em;text-shadow: 0 1px 1px rgba(0,0,0,.3);-webkit-border-radius: .5em; -moz-border-radius: .5em;border-radius: .5em;-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);-moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);box-shadow: 0 1px 2px rgba(0,0,0,.2);
}
.red {color: #faddde;border: solid 1px #980c10;background: #d81b21;background: -webkit-gradient(linear, left top, left bottom, from(#ed1c24), to(#A51715));background: -moz-linear-gradient(top,  #ed1c24,  #A51715);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#ed1c24', endColorstr='#aa1317');
}
.red:hover {background: #b61318;background: -webkit-gradient(linear, left top, left bottom, from(#c9151b), to(#a11115));background: -moz-linear-gradient(top,  #c9151b,  #a11115);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#c9151b', endColorstr='#a11115');
}
.red:active {color: #de898c;background: -webkit-gradient(linear, left top, left bottom, from(#aa1317), to(#ed1c24));background: -moz-linear-gradient(top,  #aa1317,  #ed1c24);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#aa1317', endColorstr='#ed1c24');
}
#layer { background:#ffffcc; border:red solid 2px;border-radius:15px; padding:20px;display:none;width:350px; height: 220px;}
#layer p{ font-size:30px;color: red; font-size: 34px; text-align: center;}
#layer input{ font-size:50px; color: #ffffff;padding: 10px; margin-left: 110px; margin-top:-5px;  background:#f43b0d; font-family: 微软雅黑; border:red solid 2px;border-radius:10px;}
/*End css3*/
</style>
<script class="jquery library" src="<%=basePath %>manager/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>manager/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="<%=basePath %>manager/js/check.js"></script>

<script language="javascript">
function init(){

 var sum=$("#count").val();
   for(var  i=1;i<=sum;i++){
   document.getElementById("qian"+i).innerHTML = "<img src=\"/wxpt/siteManage/images/qian.jpg\"/>"; 
    document.getElementById("qian"+i).className="t3";
  }

};

</script>
<style type="">
.t2{

        font-family: 'Arial Black', serif;        font-size: 60px;   
       
}
.t{

         font-size: 60px ;  color: #999;   font-family: 'Arial Black';
       
}
.t3{

   background-color:#082F36;   
}
/* body{background-color:#CF9} */
td{height: 80px; width:80px; background-color:#1A6674; border:#173E45  solid 3px; }
</style>
</head>

<body onload="init();">
<form  style=" height:100%; width:100%; background-color:#173E45" >

<input type="hidden" value=${checkin.count } id="count"/>
<br/>
<br/>
<table style=" height:200px; width:80%;border-bottom-style:none; border-top-style:none;border-left-style:none; border-right-style:none;" border="0" align="center"  cellpadding="0" cellspacing="0">

  <tr >
    <td id="qian1" align="center" valign="middle" ><span class="t">1</span></td>
    <td id="qian2" align="center"><span class="t">2</span></td>
    <td id="qian3" align="center"><span class="t">3</span></td>
    <td id="qian4" align="center"><span class="t">4</span></td>
    <td id="qian5" align="center"><span class="t">5</span></td>
    <td id="qian6" align="center"><span class="t">6</span></td>
   
  </tr>
  <tr >
    <td id="qian7" align="center"><span class="t">7</span></td>
    <td id="qian8" align="center"><span class="t">8</span></td>
    <td id="qian9" align="center"><span class="t">9</span></td>
    <td id="qian10" align="center"><span class="t">10</span></td>
    <td id="qian11" align="center"><span class="t">11</span></td>
    <td id="qian12" align="center"><span class="t">12</span></td>
   
  </tr>
  <tr >
    <td id="qian13" align="center" ><span class="t">13</span></td>
    <td id="qian14" align="center"><span class="t">14</span></td>
    <td id="qian15"  align="center"><span class="t">15</span></td>
    <td id="qian16" align="center"><span class="t">16</span></td>
    <td id="qian17" align="center"><span class="t">17</span></td>
    <td id="qian18" align="center"><span class="t">18</span></td>
  
  </tr>
  <tr >
    <td id="qian19" align="center"><span class="t">19</span></td>
    <td id="qian20" align="center"><span class="t">20</span></td>
    <td id="qian21" align="center"><span class="t">21</span></td>
    <td id="qian22" align="center"><span class="t">22</span></td>
    <td id="qian23" align="center"><span class="t">23</span></td>
    <td id="qian24" align="center"><span class="t">24</span></td>
    
   
  </tr>
  <tr >
    <td id="qian25"  align="center"><span class="t">25</span></td>
    <td id="qian26"  align="center"><span class="t">26</span></td>
    <td id="qian27" align="center"><span class="t">27</span></td>
    <td id="qian28" align="center"><span class="t">28</span></td>
    <td id="qian29" align="center"><span class="t">29</span></td>
    <td id="qian30" align="center"><span class="t">30</span></td>   
  </tr>
   
</table>
<br/>
<br/>
</form>


<div id="layer" style="position:absolute; top:870px; left:31%; z-index: 100;">
  <p></p>
    <input type="button" value="确定" onclick="hide();" />
</div>
<div style="background:#fff; padding:20px 0; height:450px;">
<!--效果开始-->
<div id="jihui" style="border:1px #999999 solid; width:175px; font-family:微软雅黑; font-size:18px; margin-top:20px; padding-left:20px; display:none;">
	<span></span>
</div>
<s:iterator value="ImagerollList2" var="imageroll">
<div id="lottery" style="position: absolute;"> 

<img id="imgs1" src="/wxpt/web/images/${enterId}/<s:property value="#imageroll.uploadImage"/>" style="position: absolute; left: -45px; top: 46px; width: 700px; height: 700px;" class="image" />

  <div  class="arrow"> </div>
  <div  class="lot-btn first"> <span onclick="a()"></span></div>

  <form action="" id="form">
  
	<input  id="data" value="${data }" type="hidden"/>
	<input id="flag" value="${flag }" type="hidden" />
	<input id="state" value="${state }" type="hidden" />
	<input id="luck" value="${luck }" type="hidden" />
	  <input  value="${fromusername}" id="fromusername1" type="hidden"/>
	  	<input  value="${enterId}" id="enterId" type="hidden"/>
</form>

</div>
</s:iterator>
<script language="javascript">
$(function() {


$("#lottery").css("left",($(document).width()-600)/2);
$(".lot-btn").click(function() {
//$("#lottery").css("left",($(document).width()-600)/2);
 	 var fromusername=$("#fromusername1").val();
    // alert(fromusername);	
     //z
	var enterId=$("#enterId").val();
	$.ajax({
		type : "POST",
		url : '<%=basePath%>site/roll!toLuck3',
		data : {
			'fromusername' : fromusername,
			'enterId':enterId
		},
		dataType : "text",
		success : function(data) {
			 
		 var data2=data.split(",");
		// alert(data2);
		 var state=data2[0];
		// alert(state);
		 var f=data2[1];
		 var n =data2[2];
		 var luck=data2[3];
		 var m=parseFloat(n)+parseFloat(3600);
		 if(luck=='1'){
		  luck='一';
		 }
		 if(luck=='2'){
		  luck='二';
		 }
		 if(luck=='3'){
		  luck='三';
		 }
		 if(luck=='4'){
		  luck='四';
		 }
		 if(luck=='5'){
		  luck='五';
		 }
		 if(luck=='6'){
		  luck='六';
		 }
	    if(f=="true"){
		if(state=='1'){
		//$("#lottery").css("left",($(document).width()-600)/2);
		$("#layer p").html("您只有一次抽奖机会，恭喜下次中奖！");
			$("#layer").show();
		}else{
			for (var i = 0; i <= 10000; i++) {
			 $("#imgs1").rotate({
			animateTo: i,
			duration: 10000,
			callback: function(){
					if(luck==-1){
			   $("#layer p").html("不好意思,亲,您未中奖,再接再厉哦");
					$("#layer").show();
			    }else{
			  $("#layer p").html("恭喜您获得的" +luck+ "等奖");
					$("#layer").show();
			}
				}
			});
			if (i >=m) {
				break;
			}  
		}
		}	
	//$("#lottery").css("left",($(document).width()-600)/2);
	}	 
		}
	});
 
});
	//$("#lottery").css("left",($(document).width()-600)/2);
});
function hide(){
$("#layer").css("display","none");
};
</script>
</script>

<!--End-->
</div>
`
</body>
</html>

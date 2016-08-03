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
#lottery { background: url("/cityin/manager/images/disc-bg1.gif?v=79804") no-repeat scroll 0 0 transparent; height: 800px; width:800px;top:600px;!important; }
#lottery .arrow { background: url("/cityin/manager/images/arrow.png?v=1bde2") no-repeat scroll 0 0 transparent; height: 191px; left: 285px; position: absolute; top: 200px; width: 32px; }
#lottery .lot-btn { height: 92px; left: 255px; overflow: hidden; position: absolute; top: 340px; width: 91px; }
#lottery .lot-btn span { cursor: pointer; display: block; height: 92px; position: relative; width: 91px; }
#lottery .first span { background: url("/cityin/manager/images/buttons_01.png?v=8bc8e") no-repeat scroll 0 0 transparent; }
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
#layer { background:#f5f5f5; border:#000 solid 1px; padding:20px;display:none;width:350px; height: 220px;}
#layer p{ font-size:30px;color: red; font-size: 34px;}
#layer input{ font-size:50px;color: red; padding: 10px; margin-left: 100px;}

/*End css3*/
</style>
<script class="jquery library" src="<%=basePath %>manager/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>manager/js/jQueryRotate.2.2.js"></script> 
<script language="javascript">
function init(){

 var sum=$("#count").val();
   for(var i=1;i<=sum;i++){
 document.getElementById("qian"+i).innerHTML = "<img  src=\"/cityin/siteManage/images/qian.jpg\"/>"; 
  // $("#qian"+i).css("t3");
 
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
td{height: 114px; width:80px; background-color:#1A6674; border:#173E45  solid 3px; }
</style>
</head>

</head>

<body onload="init();" > 
<form  style=" height:100%; width:100%; background-color:#173E45" >

<input type="hidden" value=${checkin.count } id="count"/>
<br/>
<br/>
<table style=" height:200px; width:80%; border-bottom-style:none; border-top-style:none;border-left-style:none; border-right-style:none;" border="0" align="center"  cellpadding="0" cellspacing="0">

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

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<% String imagePath = request.getContextPath()+"/images/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
</head>
<style type="text/css"> 
a:link { text-decoration: none; color:#5d5d5d; font-size:12px;} 
a:active { text-decoration:blink} 
a:hover { text-decoration:underline;color: red} 
a:visited { text-decoration: none;color: green}
@charset "utf-8";
body,ul{margin:0; padding:0;}
body{font-size:12px; font-family:"微软雅黑", "宋体";}
.divone{width:1000px; margin:0 auto; background:#FFF;}
a{text-decoration:none;}
.clear{clear:both;}
.posa{position:absolute}
.posr{position:relative}
.floay{float:right;}
.floaz{float:left;}
.alinZ{text-align:left;}
.alinY{text-align:right;}
.color{color:#ff0000}
.colobg{
filter: progid:DXImageTransform.Microsoft.Gradient(startColorStr='#ffffff',endColorStr='#ececec',gradientType='0');

 /*IE 6 7 8*/ 

background: -ms-linear-gradient(top, #fff,  #ececec);        /* IE 10 */

background:-moz-linear-gradient(top,#fff,#ececec);/*火狐*/ 

background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#fff), to(#ececec));/*谷歌*/ 

background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#ececec));      /* Safari 4-5, Chrome 1-9*/

background: -webkit-linear-gradient(top, #fff, #ececec);   /*Safari5.1 Chrome 10+*/

background: -o-linear-gradient(top, #fff, #ececec);  /*Opera 11.10+*/}
.ptic{text-align:center; line-height:24px; color:#5d5d5d; font-size:12px;}
.ptic td{padding-top:20px;}
.ptic a{color:#5d5d5d;}

 
</style>
<script type="text/javascript" src="../../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
var fulls = "left=0,screenX=0,top=0,screenY=0,scrollbars=1";    //定义弹出窗口的参数
if (window.screen) {
   var ah = screen.availHeight - 30;
   var aw = screen.availWidth - 10;
   fulls += ",height=" + ah;
   fulls += ",innerHeight=" + ah;
   fulls += ",width=" + aw;
   fulls += ",innerWidth=" + aw;
   fulls += ",resizable"
} else {
   fulls += ",resizable"; // 对于不支持screen属性的浏览器，可以手工进行最大化。 manually
}
function linkurl(url){
	  window.open(url,"",fulls);
}

</script>
<body>
<a></a>
<div class="magtop5 pigs">
  <div class="picall">
      <div class="pictn colobg pdbotm1" style="height:100%;"">
      <br />
      <br />
       <table width="100%" border="0"  cellspacing="0" cellpadding="0" class="ptic">
  		${trStr }
		</table>
      </div>
      </div>
    </div>
</body>
</html>

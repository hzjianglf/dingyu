<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${param.colName}</title>
<!--加载ExtJs资源-->
<script type="text/javascript" src="/extjs/bootstrap3.4.js"></script>
<link rel="stylesheet" type="text/css" href="/extjs/3.4/ux/css/LockingGridView.css" />
<script type="text/javascript" src="/extjs/3.4/ux/LockingGridView.js"></script>
<script type="text/javascript" src="/jquery/jquery-1.8.2.min.js"></script>
<style type="text/css">
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
.org-grid-row td{
    width:100%;
    height:25px;
  	vertical-align:middle;
}
.org-grid-panel{
	border-top:1px solid;
	border-right:1px solid;
	border-color:#EDEDED;
}
.input{
	border: 1px solid #ccd0d0;
	height: 20px;
	line-height: 20px;
	padding-left: 5px;
	vertical-align:middle;
	font-size:10pt;
}

.button {
    height: 22px;
    line-height: 24px;
    border: 1px solid #ccd0d0;
	cursor: pointer;
	border-radius: 5px; 
	-moz-border-radius:5px; 
	-webkit-border-radius:5px;
	font-size: 10pt;
	text-align: center;
	background-color: #F5F5F5;
}
.button:hover {
    background-color: #C0C0C0;
}
</style>

<script type="text/javascript">
Ext.onReady(function(){
  Ext.QuickTips.init();
  new Ext.Viewport({
    layout: 'border',
    items: [
             /* {
      region: 'north',
      split: true,
      height: 87,
      enabled: false,
      maxHeight: 87,
      collapseMode: 'mini',
      border: true,
      autoLoad: {url: '/framework/layout/head.jsp', nocache: true, scripts:true}
    },  */
    {
	    id: 'treeTabPanel',
        region: 'west',
        title: '${param.colName}',
        width: 250,
        minWidth: 150,
        maxWidth: 250,
        split: true,
        border: true,
        layout: 'fit',
        collapseMode: 'mini',
        autoLoad: {url: 'operauthoritylist.jsp?id=${param.id}', scripts:true}
    },{
      id: 'centerLayout',
      region: 'center',
      border: true,
      collapsible: false,
      layout: 'fit'
    }
    ]
  });
});

</script>
</head>
<body>
</body>
</html>
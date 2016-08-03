<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>资源发布 后台管理</title>
<link rel="stylesheet" type="text/css" href="css/info.css"/>
<!--加载ExtJs资源-->
<script type="text/javascript" src="/extjs/bootstrap3.4.js"></script>

<script type="text/javascript" src="js/info.js"></script>

<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>

<!--加载公共样式文件-->
<link rel="stylesheet" type="text/css" href="/framework/css/global.css"/>

<style type="text/css">
.org-grid-row td{
    width:100%;
    height:25px;
  	vertical-align:middle;
}
.org-grid-panel{
	border-top:0px solid;
	border-right:0px solid;
	border-color:#EDEDED;
}
</style>

<script type="text/javascript">
Ext.onReady(function(){
  Ext.QuickTips.init();
  new Ext.Viewport({
    layout: 'border',
    items: [{
	    id: 'infoTreePanel',
        region: 'west',
        title: '资源发布',
        width: 250,
        minWidth: 150,
        maxWidth: 250,
        split: true,
        border: true,
        layout: 'fit',
        collapseMode: 'mini',
        autoLoad: {url: 'tree.jsp', nocache: true, scripts:true}
    },{
      id: 'centerLayout',
      region: 'center',
      border: true,
      collapsible: false,
      layout: 'fit',
      autoLoad: {url: 'display/default.jsp', scripts:true}
    }
    ]
  });
});

</script>
</head>
<body>

</body>
</html>
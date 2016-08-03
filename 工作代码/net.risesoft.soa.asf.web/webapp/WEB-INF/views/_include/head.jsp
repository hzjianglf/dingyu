<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store">
<meta http-equiv="Expires" content="-1">
<title>ASF 管理控制台</title>
<link rel="stylesheet" type="text/css" href="/extjs/3.4/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="/extjs/3.4/ux/css/ux-all.css"/>
<link rel="stylesheet" type="text/css" href="${AppRoot}/css/icons.css"/>
<link rel="stylesheet" type="text/css" href="${AppRoot}/css/ext-ff-patch.css"/>
<link rel="stylesheet" type="text/css" href="${AppRoot}/css/asf-icons.css"/>
<!-- 让 Ext.grid.GridPanel的单元格内容可选中 -->
<style type= "text/css" >      
    .x-selectable, .x-selectable * {      
        -moz-user-select: text! important ;      
        -khtml-user-select: text! important ;      
    }      
</style>
<script type="text/javascript" src="${AppRoot}/js/lib/jquery_1.7.2.min.js"></script>
<script type="text/javascript" src="/extjs/3.4/adapter/jquery/ext-jquery-adapter.js"></script>
<script type="text/javascript" src="/extjs/3.4/ext-all.js"></script>
<script type="text/javascript" src="/extjs/3.4/ux/ux-all.js"></script>
<script type="text/javascript" src="/extjs/3.4/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/extjs/init.js"></script>
<script type="text/javascript" src="${AppRoot }/js/lib/Ext.ux.js"></script>
<script type="text/javascript">
   Ext.namespace('App');
   App.Context = [];
   App.Context['RootPath'] = '${AppRoot}';
   
   //让 Ext.grid.GridPanel的单元格内容可选中
   if  (!Ext.grid.GridView.prototype.templates) {      
       Ext.grid.GridView.prototype.templates = {};      
   }      
   Ext.grid.GridView.prototype.templates.cell =  new  Ext.Template(      
        '<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}" style="{style}" tabIndex="0" {cellAttr}>' ,      
        '<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>' ,      
        '</td>'      
   );
   
   Ext.QuickTips.init();
</script>
</head>
<body>
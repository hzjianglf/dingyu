<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>此功能要求管理员账号</title>
    <link rel="stylesheet" type="text/css" href="/extjs/3.4/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="/asf/css/ext-ff-patch.css"/>
    <script type="text/javascript" src="/asf/js/lib/jquery_1.7.2.min.js"></script>
    <script type="text/javascript" src="/extjs/3.4/adapter/jquery/ext-jquery-adapter.js"></script>
    <script type="text/javascript" src="/extjs/3.4/ext-all.js"></script>
    <script type="text/javascript" src="/extjs/3.4/src/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript">
        Ext.onReady(function() {
    
            var getLocation = function() {
               var protocol = window.location.protocol;
               var host = window.location.host;
               return protocol + '//' + host + '/asf/';
            };
    
            Ext.MessageBox.show({
                     title : '要求管理员',
                     msg : '当前用户不是管理员, 请点击 "确定" 注销并使用管理员帐号重新登录.',
                     width : 300,
                     buttons : Ext.MessageBox.OK,
                     icon : Ext.MessageBox.WARNING,
                     fn : function(btn) {
                        var url = '/sso/logout?service=' + getLocation();
                        var win = window.parent ? window.parent : window;
                        win.location = url;
                     }
                  });
    
         });
    </script>
  </head>
  <body>
  </body>
</html>
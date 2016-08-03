<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/page/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/page";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:property value="page.PageTitle"/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css"
	href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/client-page1a986a.css" />
<!--[if lt IE 9]>    <link rel="stylesheet" type="text/css" href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/pc-page1a9334.css"/>    <![endif]-->
<link media="screen and (min-width:1000px)" rel="stylesheet"
	type="text/css"
	href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/pc-page1a9334.css" />
<style>
body {
	-webkit-touch-callout: none;
	-webkit-text-size-adjust: none;
}
</style>
<script type="text/javascript">
//is_weixin();
function is_weixin(){
	var linkStr="";
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i)=="micromessenger") {
		return true;
 	} else {
 		var urlStr = window.location.href;
		if(urlStr.indexOf("!") != -1){
			var badyUrl = urlStr.split("!");
			linkStr=badyUrl[0]+"!getTest?"+badyUrl[1].split("?")[1].split("&")[0];
		}else{
			var badyUrl = urlStr.split("?");
			linkStr=badyUrl[0]+"!getTest?"+badyUrl[1];
		}
		window.location.href=linkStr;
	}
}
        document.domain = "qq.com";
        var _wxao = window._wxao || {};

        _wxao.appid = "wx1bc509d4d039b25a";
        _wxao.version = "1.0.0";
        _wxao.begin = (+new Date());

        (function() {
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', function(){
                    _wxao.jsbReady=true;
                }, false);
            }
            var wxa = document.createElement('script'); wxa.type = 'text/javascript'; wxa.async = true, version = _wxao.version||"1.0";
            wxa.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 
                            'res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/wxa/wxa-' + version + '.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(wxa, s);
        })();
    </script>
<style>
#nickname {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	max-width: 90%;
}

ol,ul {
	list-style-position: inside;
}

#activity-detail .page-content .text {
	font-size: 16px;
}
</style>
</head>
<body id="activity-detail">
	<s:property value="page.pageContent" escapeHtml="false"/>
	<script type="text/javascript">

        var cookie = {
            get: function(name){
                if( name=='' ){
                    return '';
                }
                var reg = new RegExp(name+'=([^;]*)');
                var res = document.cookie.match(reg);
                return (res && res[1]) || '';
            },
            set: function(name, value){
                var now = new Date();
                    now.setDate(now.getDate() + 1);
                var exp = now.toGMTString();
                document.cookie = name + '=' + value + ';expires=' + exp;
                return true;
            }
        };

        function trim(str){
            return str.replace(/^\s*|\s*$/g,'');
        }

        function ajax(obj){
            var type  = (obj.type || 'GET').toUpperCase();
            var url   = obj.url;
            var async = typeof obj.async == 'undefined' ? true : obj.async;
            var data  = typeof obj.data  == 'string' ? obj.data : null;
            var xhr   = new XMLHttpRequest();
            var timer = null;
            xhr.open(type, url, async);
            xhr.onreadystatechange = function(){
                if( xhr.readyState == 3 ){
                    obj.received && obj.received(xhr);
                }
                if( xhr.readyState == 4 ){
                    if( xhr.status >= 200 && xhr.status < 400 ){
                        clearTimeout(timer);
                        obj.success && obj.success(xhr.responseText);
                    }
                    obj.complete && obj.complete();
                }
            };
            if( type == 'POST' ){
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            }
            xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
            xhr.send(data);
            if( typeof obj.timeout != 'undefined' ){
                timer = setTimeout(function(){
                    xhr.abort("timeout");
                }, obj.timeout);
            }
        }

var title     = trim("<s:property value="page.PageTitle"/>"      .replace(/&lt;/g, '<').replace(/&gt;/g, '>'));
var sourceurl = trim('<s:property value="page.pageUrl"/>'.replace(/&lt;/g, '<').replace(/&gt;/g, '>'));
      
        // 举报 
    function report_article(){
            var url   = sourceurl == '' ? location.href : sourceurl;
    var query = [
                '<s:property value="page.pageNoName"/>', 
                location.href, 
                title,
                url
            ].join("|WXM|");
              
            location.href = '/mp/readtemplate?t=wxm-appmsg-inform&__biz=MTYzNTEyOTUwMQ==&info=' + encodeURIComponent(query) + "#wechat_redirect";
    }


        // 阅读原文
function viewSource(){
	
            var redirectUrl = sourceurl.indexOf('://') < 0 ? 'http://'+sourceurl : sourceurl;
                redirectUrl = 'http://mp.weixin.qq.com/mp/redirect?url=' + encodeURIComponent(sourceurl);
            // TODO 统计不需要服务器响应数据
            location.href = redirectUrl;
    ajax({
                url: 'http://mp.weixin.qq.com/mp/appmsg/show-ajax' + location.search + '&r=' + Math.random(), //location.href
                async:false,
                type:'POST',
                timeout :2000,
                data: 'url='+sourceurl,
                complete:function(){
                    location.href = redirectUrl;
                }
    }); 
    
return false;
        };


        function parseParams(str) {
            if( !str ) return {};

            var arr = str.split('&'), obj = {}, item = '';
            for( var i=0,l=arr.length; i<l; i++ ){
                item = arr[i].split('=');
                obj[item[0]] = item[1];
            }
            return obj;
        }

        function htmlDecode(str){
            return str
                  .replace(/&#39;/g, '\'')
                  .replace(/<br\s*(\/)?\s*>/g, '\n')
                  .replace(/&nbsp;/g, ' ')
                  .replace(/&lt;/g, '<')
                  .replace(/&gt;/g, '>')
                  .replace(/&quot;/g, '"')
                  .replace(/&amp;/g, '&');
        }
                  
        function report(link, fakeid, action_type){
            var queryStr = link.split('?').pop();
                queryStr = queryStr.split('#').shift();
            if( queryStr == '' ){
                return;
            }

            var param = [
                queryStr,
                'action_type=' + action_type,
                'uin=' + fakeid
            ].join('&');
            
            ajax({
                url : 'http://mp.weixin.qq.com/mp/appmsg/show',
                type: 'POST',
                timeout: 2000,
                data: param
            });
            ajax({
                url : ((window.location.href).replace("?", "!wTongji?")).replace("#http://mp.weixin.qq.com/", "&type="+action_type),
                type: 'POST',
                timeout: 2000,
                data: param
           });
            if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)==true) {
                //alert(navigator.userAgent);  
            	//ajax({
               	  // url : ((window.location.href).replace("?", "!tongji?")).replace("#http://mp.weixin.qq.com/", "&type="+action_type),
               	  // type: 'POST',
                    //  timeout: 2000
                 // });
            }
        }
        function tongji(action_type){
        	ajax({
          	   url : ((window.location.href).replace("?", "!tongji?")).replace("#http://mp.weixin.qq.com/", "&type="+action_type),
          	   type: 'POST',
                 timeout: 2000
             });
        }
        function reportTimeOnPage(){
            var link     = location.href;
            var fakeid   = "";
            var queryStr = link.split('?').pop();
                queryStr = queryStr.split('#').shift();
            if( queryStr == '' ){
                return;
            }

            var param = [
                queryStr,
                'start_time='+_wxao.begin,
                'end_time='+new Date().getTime(),
                'uin='+fakeid,
                'title='+encodeURIComponent(title),
                'action=pagetime'
            ].join('&');

            ajax({
                url: 'http://mp.weixin.qq.com/mp/appmsg/show?'+param,
                //url: '/mp/comm_report?'+param,
                async : false,
                timeout: 2000
            });
            //var img = new Image(1,1);
            //img.src = '/mp/appmsg/show?'+param;
        }

        function share_scene(link, scene_type){
            var queryStr = link.split('?')[1] || '';
                queryStr = queryStr.split('#')[0];
            if( queryStr == '' ){
                return;
            }
            
            queryStr = [queryStr, 'scene='+scene_type].join('&');

            return link.split('?')[0] + '?' + queryStr + '#' + (link.split('#')[1]||'');
        }
          
      
      
(function(){

        function onBridgeReady() {

            var appId  = '',
    imgUrl = "<s:property value="page.pageImage"/>",
    link   = htmlDecode("<s:property value="page.pageJumpUrl"/>"),
title  = htmlDecode("<s:property value="page.PageTitle"/>"),
                desc   = htmlDecode("<s:property value="page.pageMetaValue"/>"),
                fakeid = "";
                desc   = desc || link;

        if( "1" == "0" ){
        WeixinJSBridge.call("hideOptionMenu");  
        }

            document.getElementById('post-user').addEventListener('click', function(){
                WeixinJSBridge.invoke('profile',{
                    'username':'<s:property value="page.weixinNo"/>',
                    'scene':'57'
                });
            });

// 发送给好友; 
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
            
WeixinJSBridge.invoke('sendAppMessage',{
  "appid"      : appId,
  "img_url"    : imgUrl,
  "img_width"  : "640",
  "img_height" : "640",
  "link"       : share_scene(link, 1),
  "desc"       : desc,
  "title"      : title
                        }, function(res) {report(link, fakeid, 1);
	                        if(res.err_msg=="send_app_msg:ok"){
	                    		tongji(1);
	                    	};
                        });
});

// 分享到朋友圈;
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                        report(link, fakeid, 2);
                        tongji(2);
WeixinJSBridge.invoke('shareTimeline',{
  "img_url"    : imgUrl,
  "img_width"  : "640",
  "img_height" : "640",
  "link"       : share_scene(link, 2),
  "desc"       : desc,
  "title"      : title
}, function(res) {
                        });
            
});

// 分享到微博;
var weiboContent = '';
            WeixinJSBridge.on('menu:share:weibo', function(argv){
            
WeixinJSBridge.invoke('shareWeibo',{
  "content" : title + share_scene(link, 3),
  "url"     : share_scene(link, 3) 
  }, function(res) {report(link, fakeid, 3);
  if(res.err_msg=="share_weibo:ok"){
		tongji(3);
	};
  });
});

// 分享到Facebook
    WeixinJSBridge.on('menu:share:facebook', function(argv){
    report(link, fakeid, 4);
    WeixinJSBridge.invoke('shareFB',{
                      "img_url"    : imgUrl,
                      "img_width"  : "640",
                      "img_height" : "640",
                      "link"       : share_scene(link, 4),
                      "desc"       : desc,
                      "title"      : title
    }, function(res) {} );
    });

                    // 新的接口
            WeixinJSBridge.on('menu:general:share', function(argv){
                var scene = 0;
                switch(argv.shareTo){
                    case 'friend'  : scene = 1; break;
                    case 'timeline': scene = 2; break;
                    case 'weibo'   : scene = 3; break;
                }
                    argv.generalShare({
                                        "appid"      : appId,
                                        "img_url"    : imgUrl,
                                        "img_width"  : "640",
                                        "img_height" : "640",
                                        "link"       : link,
                                        "desc"       : desc,
                                        "title"      : title
                    }, function(res){report(link, fakeid, scene);
                    	if(res.err_msg=="general_share:ok"){
                            switch(argv.shareTo){
                                case 'friend'  : tongji(1); break;
                                case 'timeline': tongji(2); break;
                                case 'weibo'   : tongji(3); break;
                            }
                    	}
                    });
            });


}

        if( document.addEventListener )
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);

        })();

        // 记住阅读位置
(function(){
var timeout = null;
var val = 0;
var url = location.search.substr(1);
var params = parseParams( url );
var biz = params['__biz'].replace(/=/g, '#');
var key = biz + params['appmsgid'] + params['itemidx'];

            if( window.addEventListener ){
                window.addEventListener('load', function(){
                    val = cookie.get(key);
                    window.scrollTo(0, val);
                }, false);

                window.addEventListener('unload', function(){
                    cookie.set(key,val);
                    // 上报页面停留时间
                    reportTimeOnPage();
                }, false);

                window.addEventListener('scroll', function(){
                    clearTimeout(timeout);
                    timeout = setTimeout(function(){
                        val = window.pageYOffset;
                    },500);
                }, false);

                document.addEventListener('touchmove', function(){
                    clearTimeout(timeout);
                    timeout = setTimeout(function(){
                        val = window.pageYOffset;
                    },500);
                }, false);
            }

})();


        //弹出框中图片的切换
        if( window.addEventListener ){
            window.addEventListener('load', function() {
                var imgs = document.getElementsByTagName('img'),
                    imgsSrc = [],
                    minWidth = 0;
                for( var i=0,l=imgs.length; i<l; i++ ){
                    var img = imgs.item(i);
                    var src = img.getAttribute('data-src') || img.getAttribute('src');
                    if( src ){
                        imgsSrc.push(src);
                        (function(src){
                            img.addEventListener('click', function(){
                                reviewImage(src);
                            });
                        })(src);
                    }
                }

                function reviewImage(src) {
                    if (typeof window.WeixinJSBridge != 'undefined') {
                        WeixinJSBridge.invoke('imagePreview', {
                            'current' : src,
                            'urls' : imgsSrc
                        });
                    }
                }
            }, false);
        }

        // 图片延迟加载
        (function(){
            var timer  = null;
            var height = (window.innerHeight||document.documentElement.clientHeight) + 40;
            var images = [];
            function detect(){
                var scrollTop = (window.pageYOffset||document.documentElement.scrollTop) - 20;
                for( var i=0,l=images.length; i<l; i++ ){
                    var img = images[i];
                    var offsetTop = img.el.offsetTop;
                    if( !img.show && scrollTop < offsetTop+img.height && scrollTop+height > offsetTop ){
                        img.el.setAttribute('src', img.src);
                        img.show = true;
                    }
                }
            }
            function onScroll(){
                clearTimeout(timer);
                timer = setTimeout(detect, 100);
            }
            function onLoad(){
                var imageEls = document.getElementsByTagName('img');
                for( var i=0,l=imageEls.length; i<l; i++ ){
                    var img = imageEls.item(i);
                    if(!img.getAttribute('data-src') ) continue;
                    images.push({
                        el     : img,
                        src    : img.getAttribute('data-src'),
                        height : img.offsetHeight,
                        show   : false
                    });
                }
                detect();
            }

            if( window.addEventListener ){
                window.addEventListener('scroll', onScroll, false);
                window.addEventListener('load', onLoad, false);
                document.addEventListener('touchmove', onScroll, false);
            }
            else {
                window.attachEvent('onscroll', onScroll);
                window.attachEvent('onload', onLoad);
            }
        })();
</script>
</body>
</html>
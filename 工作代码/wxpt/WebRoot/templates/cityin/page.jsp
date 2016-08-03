<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:property value="pageAbout.pageTitle" /></title>
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
	<div class="page-bizinfo">
		<div class="header">
			<h1 id="activity-name"><s:property value="pageAbout.pageTitle" /></h1>
			<p class="activity-info">
				<span id="post-date" class="activity-meta no-extra">2013-12-26</span>
				<a href="javascript:;" id="post-user" class="activity-meta"> <span
					class="text-ellipsis">百度贴吧</span><i class="icon_link_arrow"></i> </a>
			</p>
		</div>
	</div>
	<div class="page-content">
		<div class="media" id="media">
			<img
				src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />"
				onerror="this.parentNode.removeChild(this)"
				data-src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />" />
		</div>
		<div class="text">
			<s:property value="pageAbout.metaValue" escapeHtml="false"/>
		</div>
		<p class="page-toolbar">
			<a href="javascript:viewSource();" class="page-url">阅读原文</a><a
				href="javascript:report_article();" class="page-imform">举报</a>
		</p>
	</div>
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

var title     = trim('有奖活动：万张手机话费密码公开放送！'      .replace(/&lt;/g, '<').replace(/&gt;/g, '>'));
var sourceurl = trim('http://wapp.baidu.com/f?kz=2778760477#wechat_redirect'.replace(/&lt;/g, '<').replace(/&gt;/g, '>'));
      
        // 举报 
    function report_article(){
            var url   = sourceurl == '' ? location.href : sourceurl;
    var query = [
                '百度贴吧', 
                location.href, 
                title,
                url
            ].join("|WXM|");
              
            location.href = '/mp/readtemplate?t=wxm-appmsg-inform&__biz=MTYzNTEyOTUwMQ==&info=' + encodeURIComponent(query) + "#wechat_redirect";
    }


        // 阅读原文
function viewSource(){
            var redirectUrl = sourceurl.indexOf('://') < 0 ? 'http://'+sourceurl : sourceurl;
                redirectUrl = 'http://' + location.host + '/mp/redirect?url=' + encodeURIComponent(sourceurl);
            // TODO 统计不需要服务器响应数据
    ajax({
                url: '/mp/appmsg/show-ajax' + location.search + '&r=' + Math.random(), //location.href
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
                url : '/mp/appmsg/show',
                type: 'POST',
                timeout: 2000,
                data: param
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
                url: '/mp/appmsg/show?'+param,
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
    imgUrl = "http://mmbiz.qpic.cn/mmbiz/DX0FicSab6TKAdiaVTuI8BOEObib2wXrCibBvkoqicmzv66oX3CHfXxNtLObOQXezYLN2JfC6AIObziahWDgFhooEZyA/0",
    link   = "http://mp.weixin.qq.com/mp/appmsg/show?__biz=MTYzNTEyOTUwMQ==&appmsgid=117334478&itemidx=1&sign=98b838c1c160c67267d4180cd6f6e5b0#wechat_redirect",
title  = htmlDecode("有奖活动：万张手机话费密码公开放送！"),
                desc   = htmlDecode("不用再和土豪做朋友了，因为你就是土豪！12月26日-31日“贴吧手机客户端”年终大奖丰厚放送，手机话费充值帮你月底流量大回血！"),
                fakeid = "";
                desc   = desc || link;

        if( "1" == "0" ){
        WeixinJSBridge.call("hideOptionMenu");  
        }

            document.getElementById('post-user').addEventListener('click', function(){
                WeixinJSBridge.invoke('profile',{
                    'username':'wxid_5840698407111',
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
                        });
});

// 分享到朋友圈;
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                        report(link, fakeid, 2);
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
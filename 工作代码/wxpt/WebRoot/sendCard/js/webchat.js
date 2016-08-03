$(function(){
	is_weixin();
	var clk = ('touchend' in window ? 'tap' : 'click');	
	var _Code = false;					//记录当前是有ajax请求:false===没有
	var _Link = '';
	$(".cardWrap").css( "background-image" ,"url("+$("#bg_msg").val()+"  ) ");
	$(".cardWrap").css( "backgroundRepeat ", "no-repeat");
	$(".user").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".user").css( "backgroundRepeat ", "no-repeat");
	$(".sd2").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".sd2").css( "backgroundRepeat ", "no-repeat");
	$(".sd4").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".sd4").css( "backgroundRepeat ", "no-repeat");
	$(".sd8").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".sd8").css( "backgroundRepeat ", "no-repeat");
	$(".sd10").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".sd10").css( "backgroundRepeat ", "no-repeat");
	$(".sd11").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".sd11").css( "backgroundRepeat ", "no-repeat");
	function check_Name(name,str){     //arguments:1:元素,2:字符串
		var _name = name.val();
		if( 0 < _name.length && _name.length <= 16){
			if(_name !== name.attr('placeholder')){
				return 1;
			}
			prompt($('#prompt'), '请输入'+str);
			return 0;
		}else{
			prompt($('#prompt'), '请输入'+ str );
			return 0;
		}
	}
	
	var _opacity = function(){
		var iMax = Math.max(document.body.scrollHeight,document.body.offsetHeight,document.documentElement.clientHeight);
		$('#share-shadow').css('height',iMax).show();
		$('.box-guide').css({
			'position': 'fixed',
			'z-index': '150',
			'top': '10px',
			'right': '30px'
		}).show();
		$(window).ontouchmove = function(ev){
			ev = ev || window.event;
			ev.preventDefault();					
		}
		$('#share-shadow').on(clk,function(){
			$(this).hide();
			$('.box-guide').hide();
			$(window).ontouchmove = null;
		})
	}
	
	$('#f_name').on('input',function(){
		var _name = $(this).val();
		if (_name.length > 16){
			$(this).val(_name.slice(0, 16));
		}
	});
	
	$('#s_content').on('input',function(){
		var _name = $(this).val();
		if (_name.length > 60){
			$(this).val(_name.slice(0, 60));
		}
	});
	
	$('#f_name,#s_content').on('focus',function () {
		$('#shareMsg').hide();
	});
	$('#btn_guanzhu_card').on(clk, function(){
		var strRegex = "^((https|http|ftp|rtsp|mms)?://)"  
	         + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
	         + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
	         + "|" // 允许IP和DOMAIN（域名） 
	         + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
	         + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
	        + "[a-z]{2,6})" // first level domain- .com or .museum  
	        + "(:[0-9]{1,4})?" // 端口- :80  
	        + "((/?)|" // a slash isn't required if there is no file name  
	        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
	        var patt1=new RegExp(strRegex);
		
		var result = patt1.test($("#errorUrl").val());
		if(result){
			window.location.href = $("#errorUrl").val();
		}else{
			var urlStr = window.location.href;
			var badyUrl = urlStr.split("!");
			window.location.href=badyUrl[0]+"!getGuanZhu?"+badyUrl[1].split("?")[1].split("&")[0];
		}
	});
	 
	
	
	$('#send_image').on(clk, function(){
		var urlStr = window.location.href;
		var badyUrlStr = urlStr.split("?");
		var url = badyUrlStr[0]+"!addFansCard";
		var enterId = badyUrlStr[1].split("&");
		var state = check_Name($('#f_name'),'寄卡人');
		if(!(state) || _Code){return;} //寄卡人名称不符合则退出  或者 有ajax请求则退出
		_Code = true;
		$.ajax({
            url: url,
            type: 'POST',
            dataType:'json',
            data: {
				'fromUser': $('#f_name').val(),
				'cardsContent': $('#s_content').val(),
				'cardId': $('#i_type').val(),
				'enterID' : enterId[0].split("=")[1]
            },
            datatype:"json",
            success: function (date) {
            	var str = eval(date);
                if (str.state == 1) {
                	_Link = badyUrlStr[0]+"!getFandCard?"+badyUrlStr[1]+"&fandCardId="+str.fandCardId;
					_opacity();
					_Code = false;
					
                } else {
                    prompt($('#prompt'), '请先关注此账号');
                    var urlStr = window.location.href;
        			var badyUrl = urlStr.split("!");
        			window.location.href=badyUrl[0]+"!getError?"+badyUrl[1].split("?")[1].split("&")[0];
					_Code = false;
					_Link = '';
                }
            },
            error: function () {
            	prompt($('#prompt'), '请先关注此账号');
            	var urlStr = window.location.href;
    			var badyUrl = urlStr.split("!");
    			window.location.href=badyUrl[0]+"!getError?"+badyUrl[1].split("?")[1].split("&")[0];
				_Code = false;
				_Link = '';
            }
        });
	
	});

	$('#send_show_image').on(clk, function(){
		_Link=window.location.href;
		_opacity();			
	});
	
	
	(function(){
		var onBridgeReady = function () {
			var appId = '';
			var link, imgUrl, title, desc;
			var change_share_msg = function(){
				//link = _Link;
				link =_Link;
				imgUrl = $("#title_image").val();
				title = $('#f_name').val() + '发来一张贺卡';
				desc = $('#s_content').val();
			};
			var default_share_msg = function(){
				var urlStr = window.location.href;
				if(urlStr.indexOf("!") != -1){
					var badyUrl = urlStr.split("!");
	    			link=badyUrl[0]+"!getError?"+badyUrl[1].split("?")[1].split("&")[0];
				}else{
					var badyUrl = urlStr.split("?");
					link=badyUrl[0]+"!getError?"+badyUrl[1];
				}
				//link = 'http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5MDU2OTMyNA==&appmsgid=10001369&itemidx=1&sign=9638f470d5077ca0f65cb8dfc5ad729b#wechat_redirect';
				imgUrl = 'http://wxlk-files.chetuobang.com/wxlk_files/web/images/send_cards/cards_common.png';
				title = '制作一张专属贺卡送给那个专属的TA！';
				desc = $("#enterName").val() +'“发送贺卡”功能现已新鲜上线！快来制作一张专属贺卡给TA吧！';
			};
			default_share_msg();
			// 发送给好友;
			WeixinJSBridge.on('menu:share:appmessage', function(argv){
				if(!!_Link.length){
					change_share_msg();
				}else{
					default_share_msg();
				}
				WeixinJSBridge.invoke('sendAppMessage',{
					"appid" : appId,
					"img_url" : imgUrl,
					"img_width" : "640",
					"img_height" : "640",
					"link" : link,
					"desc" : desc,
					"title" : title
					}, function(res) {});
			});
			// 分享到朋友圈;
			WeixinJSBridge.on('menu:share:timeline', function(argv){
				if(!!_Link.length){
					change_share_msg();
				}else{
					default_share_msg();
				}
				WeixinJSBridge.invoke('shareTimeline',{
					"img_url" : imgUrl,
					"img_width" : "640",
					"img_height" : "640",
					"link" : link,
					"desc" : desc,
					"title" : title
				}, function(res) {
				});
			});


			// 分享到微博;
			var weiboContent = '';
			WeixinJSBridge.on('menu:share:weibo', function(argv){
				var link = 'http://wxlk.chetuobang.com/web_weixinlukuang/index.php?c=web_push&m=index&content_id=63';
				var weibo_content = $("#enterName").val()+ '“发送贺卡”功能现已新鲜上线！快来制作一张专属贺卡给TA吧！' + link;
				if(!!_Link.length){
					link = _Link;
					weibo_content = $('#f_name').val() + '发来一张贺卡 ' + link;
				}
				WeixinJSBridge.invoke('shareWeibo',{
				"content" : weibo_content,
				"url" : link
				}, function(res) {
				});
			});
			// 隐藏右上角的选项菜单入口;
			//WeixinJSBridge.call('hideOptionMenu');
		};
		if(document.addEventListener){
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		} else if(document.attachEvent){
		document.attachEvent('WeixinJSBridgeReady' , onBridgeReady);
		document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
		}
		})();
	
});
function is_weixin(){
	var link="";
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i)=="micromessenger") {
		return true;
 	} else {
 		var urlStr = window.location.href;
		if(urlStr.indexOf("!") != -1){
			var badyUrl = urlStr.split("!");
			link=badyUrl[0]+"!getTest?"+badyUrl[1].split("?")[1].split("&")[0];
		}else{
			var badyUrl = urlStr.split("?");
			link=badyUrl[0]+"!getTest?"+badyUrl[1];
		}
		window.location.href=link;
	}
}


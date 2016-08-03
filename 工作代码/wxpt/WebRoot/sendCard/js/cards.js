$(function(){
	var clk = ('touchend' in window ? 'tap' : 'click');	
	var _Uid = $('#i_uid').val();
	var _Type = $('#i_type').val();		//1为教师节 2为中秋节 3为国庆节 4为生日
	var _Code = false;					//记录当前是有ajax请求:false===没有
	var _Link = '';
	
	$(".cardWrap").css( "background-image" ,"url("+$("#bg_msg").val()+"  ) ");
	$(".cardWrap").css( "backgroundRepeat ", "no-repeat");
	$(".user").css( "background-image" ,"url("+$("#txt_msg").val()+"  ) ");
	$(".user").css( "backgroundRepeat ", "no-repeat");
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
		window.location.href=$("#errorUrl").val();
	});
	$('#btn_send_card').on(clk, function(){
		var urlStr = window.location.href;
		var badyUrl = urlStr.split("?");
		var url = badyUrl[0]+"!addFansCard";
		var enterId = badyUrl[1].split("&");
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
                	_Link = badyUrl[0]+"!getFandCard?"+badyUrl[1]+"&fandCardId="+str.fandCardId;
					_opacity();
					_Code = false;
					
                } else {
                    prompt($('#prompt'), '请先关注此账号');
                    window.location.href=$("#errorUrl").val();
					_Code = false;
					_Link = '';
                }
            },
            error: function () {
            	prompt($('#prompt'), '请先关注此账号');
                window.location.href=$("#errorUrl").val();
				_Code = false;
				_Link = '';
            }
        });
	
	});

	(function(){
		var onBridgeReady = function () {
			var imgUrlArr = ['','teacher','moon','national','birthday','halloween','single']; //第一个空字符串占位!!勿动!!
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
				link = 'http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5MDU2OTMyNA==&appmsgid=10001369&itemidx=1&sign=9638f470d5077ca0f65cb8dfc5ad729b#wechat_redirect';
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
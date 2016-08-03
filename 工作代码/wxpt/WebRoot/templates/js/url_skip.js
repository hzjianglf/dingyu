$(function() {
	$("body a").click(function() {
		var link =new Array ("taobao.com");
		var urlStr = $(this).attr("href");
		if(IsValidUrl(urlStr, link)==false&&is_weixin()){
			$(this).attr("href","javascript:showImage()");
			_opacity();
			return;
		}
	});
	var _opacity = function(){
		var iMax = Math.max(document.body.scrollHeight,document.body.offsetHeight,document.documentElement.clientHeight);
		var widthMax = Math.max(document.body.scrollWidth,document.body.offsetWidth ,document.documentElement.clientWidth);
		$('.box-img').css('width',widthMax);
		$('.box-img img').css('height',iMax);
		$('#share-shadow').css('height',iMax).show();
		$('.box-guide').css({
			'position': 'fixed',
			'z-index': '150',
			'top': '0px',
			'right':'0px'
		}).show();
		$(window).ontouchmove = function(ev){
			ev = ev || window.event;
			ev.preventDefault();					
		};
		$('.box-img img').click(function(){
			$(".shade").hide();
			$('.box-guide').hide();
			$(window).ontouchmove = null;
		});
	};
});
function showImage(){
	var iMax = Math.max(document.body.scrollHeight,document.body.offsetHeight,document.documentElement.clientHeight);
	var widthMax = Math.max(document.body.scrollWidth,document.body.offsetWidth ,document.documentElement.clientWidth);
	$('.box-img').css('width',widthMax);
	$('.box-img img').css('height',iMax);
	$('#share-shadow').css('height',iMax).show();
	$('.box-guide').css({
		'position': 'fixed',
		'z-index': '150',
		'top': '0px',
		'right':'0px'
	}).show();
}
function IsValidUrl(str,link) {
	var a = decodeURI(str);
	for ( var i = 0; i < link.length; i++) {
		if(a.lastIndexOf(link[i])!=-1){
			return false;
		}
	}
    return true;
}
function is_weixin(){
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i)=="micromessenger") {
		return true;
 	} else {
		return false;
	}
}


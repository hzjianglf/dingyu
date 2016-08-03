function tabMenu(className,className2,action,srartCallback,currentCallback){$(className).each(function(i){$(this).bind(action,function(){$(className).each(function(i){$(this).removeClass('hover');});$(this).addClass('hover');$(className2).hide().eq(i).show();$('.menu .icon').hide().eq(i).show();if(typeof currentCallback=='function'){currentCallback(i);}});if(typeof srartCallback=='function'){srartCallback(i);}});};function BtnActive(className,className2){$(className).bind('touchstart',function(){$(this).addClass(className2);});$(className).bind('touchend',function(){$(this).removeClass(className2);});$(className).bind('touchmove',function(){$(this).removeClass(className2);});}
function shadeBlock(className,className2){$(className).bind('click',function(e){var width=$(document).width();var height=$(document.body).height();var shadow=$('#shadow-clear-history');height=height<$(window).height()?window.screen.height:height;shadow.css('width',width);$(className2).show();$(className2).css("position","absolute");$(className2).css("top",($(window).height()-$(className2).height())/2+$(window).scrollTop()+"px");$(className2).css("left",($(window).width()-$(className2).width())/2+"px");shadow.css('height',height);shadow.show().css('background','#000');});}
function openPopup(popNode,shadowNode,callback){var width=$(document).width();var height=$(document.body).height();height=height<$(window).height()?window.screen.height:height;popNode.show();setTimeout(function(){popNode.css({'position':'absolute','top':($(window).height()-$(popNode).height())/2+$(window).scrollTop()+"px",'left':($(window).width()-$(popNode).width())/2+"px"});},0);shadowNode.css({'width':width,'height':height}).show().css('background','#000');if(typeof callback==='function'){callback(popNode);}}
function closeShade(className,callback){$(className).bind('click',function(){$('.shade').hide();$('.winHot').hide();if(callback&&typeof callback=='function'){callback();}});}
function closePopup(popNode,shadowNode,callback){popNode.hide();shadowNode.hide();if(typeof callback==='function'){callback(popNode);}}
function prompt(className2,value,callback){
	var width=$(document).width();
	var height=$(document.body).height();
	$('.prompt dd p').text(value);
	height=height<$(window).height()?window.screen.height:height;
	$('#share-shadow').css({'width':width,'height':height});
	$(className2).show();
	setTimeout(function(){
		$(className2).css({'position':'absolute','z-index':150,'top':($(window).height()-$(className2).height())/2+$(window).scrollTop()+"px",'left':($(window).width()-$(className2).width())/2+"px"});
		},0);
	$('#share-shadow').css('height',height).show().css('background','#000');;
	if(callback&&typeof callback=='function'){
		callback(className2);
	}
$('#prompt-close').on(clk,function(){className2.hide();$('#share-shadow').hide();});window.onresize=function(){if(className2.css('display')==='block'){$(className2).css({'position':'absolute','z-index':150,'top':($(window).height()-$(className2).height())/2+$(window).scrollTop()+"px",'left':($(window).width()-$(className2).width())/2+"px"});}}}
function repeatLoad(obj,callback){if(obj.type==='img'){obj.load.attr('src',obj.url);}
else if(obj.type==='elem'){obj.load.html('').append(obj.node);}
if(callback&&typeof callback=='function'){callback(obj.load);}}
function verifyForm(val,rule,errtip){var reg=new RegExp(rule);if(reg.test(val)){return true;}
else{prompt($('#prompt'),errtip);return false;}}
(function($){var o=$(document);$.subscribe=function(){o.on.apply(o,arguments);};$.unsubscribe=function(){o.off.apply(o,arguments);};$.publish=function(){o.trigger.apply(o,arguments);};}(Zepto));function menuHover(className,active){$(className).bind(active,function(){$(className).each(function(i){$(className).removeClass('hover');});$(this).addClass('hover');});}
function tabBox(className,className2,action){$(className).each(function(i){$(this).bind(action,function(){$(className).each(function(e){$(this).removeClass('hover');});$(this).addClass('hover');$(className2).hide().eq(i).show();});});$('.cancel').bind('click',function(){$('.box-conShow').hide();});}
function getParam(param){var reg=new RegExp(param+'=([^&^#]+)');var str=window.location.href;var val=reg.exec(str);if($('#'+param)[0]&&val){$('#'+param).val(val[1]);}}
getParam('uid');getParam('wid');
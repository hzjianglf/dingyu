/*! Lazy Load 1.9.3 - MIT license - Copyright 2010-2013 Mika Tuupola */
!function(a,b,c,d){var e=a(b);a.fn.lazyload=function(f){function g(){var b=0;i.each(function(){var c=a(this);if(!j.skip_invisible||c.is(":visible"))if(a.abovethetop(this,j)||a.leftofbegin(this,j));else if(a.belowthefold(this,j)||a.rightoffold(this,j)){if(++b>j.failure_limit)return!1}else c.trigger("appear"),b=0})}var h,i=this,j={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:b,data_attribute:"original",skip_invisible:!0,appear:null,load:null,placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return f&&(d!==f.failurelimit&&(f.failure_limit=f.failurelimit,delete f.failurelimit),d!==f.effectspeed&&(f.effect_speed=f.effectspeed,delete f.effectspeed),a.extend(j,f)),h=j.container===d||j.container===b?e:a(j.container),0===j.event.indexOf("scroll")&&h.bind(j.event,function(){return g()}),this.each(function(){var b=this,c=a(b);b.loaded=!1,(c.attr("src")===d||c.attr("src")===!1)&&c.is("img")&&c.attr("src",j.placeholder),c.one("appear",function(){if(!this.loaded){if(j.appear){var d=i.length;j.appear.call(b,d,j)}a("<img />").bind("load",function(){var d=c.attr("data-"+j.data_attribute);c.hide(),c.is("img")?c.attr("src",d):c.css("background-image","url('"+d+"')"),c[j.effect](j.effect_speed),b.loaded=!0;var e=a.grep(i,function(a){return!a.loaded});if(i=a(e),j.load){var f=i.length;j.load.call(b,f,j)}}).attr("src",c.attr("data-"+j.data_attribute))}}),0!==j.event.indexOf("scroll")&&c.bind(j.event,function(){b.loaded||c.trigger("appear")})}),e.bind("resize",function(){g()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&e.bind("pageshow",function(b){b.originalEvent&&b.originalEvent.persisted&&i.each(function(){a(this).trigger("appear")})}),a(c).ready(function(){g()}),this},a.belowthefold=function(c,f){var g;return g=f.container===d||f.container===b?(b.innerHeight?b.innerHeight:e.height())+e.scrollTop():a(f.container).offset().top+a(f.container).height(),g<=a(c).offset().top-f.threshold},a.rightoffold=function(c,f){var g;return g=f.container===d||f.container===b?e.width()+e.scrollLeft():a(f.container).offset().left+a(f.container).width(),g<=a(c).offset().left-f.threshold},a.abovethetop=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollTop():a(f.container).offset().top,g>=a(c).offset().top+f.threshold+a(c).height()},a.leftofbegin=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollLeft():a(f.container).offset().left,g>=a(c).offset().left+f.threshold+a(c).width()},a.inviewport=function(b,c){return!(a.rightoffold(b,c)||a.leftofbegin(b,c)||a.belowthefold(b,c)||a.abovethetop(b,c))},a.extend(a.expr[":"],{"below-the-fold":function(b){return a.belowthefold(b,{threshold:0})},"above-the-top":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-screen":function(b){return a.rightoffold(b,{threshold:0})},"left-of-screen":function(b){return!a.rightoffold(b,{threshold:0})},"in-viewport":function(b){return a.inviewport(b,{threshold:0})},"above-the-fold":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-fold":function(b){return a.rightoffold(b,{threshold:0})},"left-of-fold":function(b){return!a.rightoffold(b,{threshold:0})}})}(jQuery,window,document);

$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	function isPlaceholderSupport() {
	    return 'placeholder' in document.createElement('input');
	}

	function mScroll(id){
		$("html,body").stop(true);$("html,body").animate({scrollTop: $("#"+id).offset().top}, 1000);
		}
	//封装弹出loading
	 function appLoading(msg,time){
	    if(msg&&msg.length>0){
	    	$("#appLoadingMsg").text(msg);
	    }
	    	$("#appicon").show();
	      $("#appLoading").show();
	      if(time&&time>0){
			 setTimeout(function(){
				 $("#appLoading").hide();
			 },time);
	      }
	  }
	 
	 //封装弹出loading
	 function appMsg(msg,time){
	    if(msg&&msg.length>0){
	    	$("#appLoadingMsg").text(msg);
	    }
	      $("#appicon").hide();
	      $("#appLoading").show();
	      if(time&&time>0){
			 setTimeout(function(){
				 $("#appLoading").hide();
			 },time);
	      }
	  }
	 
	 //封装弹出loading
	 function appErrorMsg(msg,time){
		 if(msg&&msg.length>0){
			 $("#appLoadingMsg").html("<span class='t-red font-20'><i class='glyphicon glyphicon-warning-sign pr10'></i>"+msg+"</span>");
		 }
		 $("#appicon").hide();
		 $("#appLoading").show();
		 if(time&&time>0){
			 setTimeout(function(){
				 $("#appLoading").hide();
			 },time);
		 }
	 }
	 //封装弹出loading
	 function appSuccessMsg(msg,time){
	    if(msg&&msg.length>0){
	    	$("#appLoadingMsg").html("<span class='t-green font-20'><i class='glyphicon glyphicon-ok pr10'></i>"+msg+"</span>");
	    }
	      $("#appicon").hide();
	      $("#appLoading").show();
	      if(time&&time>0){
			 setTimeout(function(){
				 $("#appLoading").hide();
			 },time);
	      }
	  }

	 function closeAppLoading(){
		 setTimeout(function(){
			 $("#appLoading").hide();
		 },200);
	 }
	  function closeAppLoadingNow(){
	      $("#appLoading").hide();
	  }
	  
	  /********************************************************************
	   ************************表单验证 开始******************************
	   ********************************************************************/  
	    String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g, "");}


	    var g = function (id) {
	        return "string" == typeof id ? document.getElementById(id) : id;
	    };
	  //摇摆摇摆摇摆起来
	    jQuery.fn.shake = function(intShakes /*Amount of shakes*/, intDistance /*Shake distance*/, intDuration /*Time duration*/) {
	        this.each(function() {
	            var jqNode = $(this);
	            jqNode.css({position: 'relative'});
	            for (var x=1; x<=intShakes; x++) {
	                jqNode.animate({ left: (intDistance * -1) },(((intDuration / intShakes) / 4)))
	                .animate({ left: intDistance },((intDuration/intShakes)/2))
	                .animate({ left: 0 },(((intDuration/intShakes)/4)));
	            }
	        });
	        return this;
	    }

	    //检查form表单中的input textarea select
	    var checkInput=function(optform){
	        var error=true;
	        $("input,textarea,select",optform).each(function(){
	            if(!error){
	                return error;
	            }
	            var input=$(this);
	            if(typeof(input.attr("check"))!="undefined"){
	                var flag = checktype(input);
	                var ok='<span class="glyphicon glyphicon-ok form-control-feedback"></span>';
	                var remove='<span  class="glyphicon glyphicon-remove form-control-feedback"></span>';
	                if (!flag) {
	                    //input.css("border","dashed 1px red").css("background","#D3E6B6");
	                    input.parents(".form-group").removeClass("has-success").addClass("has-error").addClass("has-feedback").append(remove).find(".glyphicon-ok").remove();
	                    input.shake(2,10,400);
	                    input.focus();
	                    error=false;
	                    return;
	                }
	                error=true;
	                input.parents(".form-group").removeClass("has-error").addClass("has-success").addClass("has-feedback").append(ok).find(".glyphicon-remove").remove();
	                //input.css("border","solid 1px #7F9DB9").css("background","#FFFFFF");
	            }
	        });
	        return error;

	    }

	    //检查给定一个input textarea select的值
	    function checktype(input) {
	        var type=input.attr("check"),
	         value=input.val(),
	         mytips=input.attr("tips"),
	          show=input.attr("show"),
	          notNull=input.attr("notNull");
	        if (show != null) {
	                g(show).innerHTML = "";
	        }
	            //检测是否为空
	                if (value == null || value == ""||value.trim()=="") {
	                //检查配置是否需要进行非空检测
	                                if(notNull==null||notNull==true||notNull=="true"){
	                                        if(mytips!=null){
	                                            if (show == null) {
	                                                alert(mytips);
	                                            } else {
	                                                g(show).innerHTML = mytips;
	                                            }
	                                        }
	                                return false;
	                                }else{
	                                return true;
	                                }
	                }else{
	                    value=value.trim();
	                }
	        return checkTypes(type,value,mytips,show,notNull);

	    }
	    //检测数据类型
	    function checkTypes(type,value,mytips,show,notNull){
	        //验证多个的时候
	        var types=type.split("&");
	        var checkFlag=true;
	        var error=1;
	        var success=2;
	        var canNotCheck=3;
	        for(var i=0;i<types.length;i++){
	            var type=types[i];
	            var checkResult=checkSelf(type,value,mytips,show,notNull);
	            if(checkResult!=canNotCheck){//判断能否处理 如果处理了 成功了继续下一个type 失败了则直接整个结束
	                if(checkResult==success){
	                    continue;
	                }else{
	                    checkFlag=false;
	                    break;
	                }
	            }
	            
	            //如果上面不能处理 则进入比较处理
	            checkResult=checkCompare(type,value,mytips,show,notNull);
	            if(checkResult!=canNotCheck){//判断能否处理 如果处理了 成功了继续下一个type 失败了则直接整个结束
	                if(checkResult==success){
	                    continue;
	                }else{
	                    checkFlag=false;
	                    break;
	                }
	            }
	            
	            
	        }
	        return checkFlag;
	    }

	    //检测比较
	    function checkCompare(type,value,mytips,show,notNull){
	        var error=1;
	        var success=2;
	        var canNotCheck=3;
	        var selfValue=getRealTypeValue(value);
	        var compareValue=getCompareValue(type);
	        if(type.indexOf(">=")!=-1){
	            if(selfValue>=compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }else if(type.indexOf("<=")!=-1){
	            if(selfValue<=compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }else if(type.indexOf(">")!=-1){
	            if(selfValue>compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }else if(type.indexOf("<")!=-1){
	            if(selfValue<compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }else if(type.indexOf("!=")!=-1){
	            if(selfValue!=compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }else if(type.indexOf("==")!=-1){
	            if(selfValue==compareValue){
	                return success;
	            }
	            showMyTipsIfNeed(mytips,show);
	            return error;
	        }

	        return canNotCheck;
	    }
	    //得到正确类型的值
	    function getRealTypeValue(value){
	        if(!isNaN(value)){
	            return Number(value);
	        }
	        return value;
	    }
	    //得到需要比较的值
	    function getCompareValue(type){
	        if(type.indexOf("#")!=-1){
	            var cid=type.substring(type.indexOf("#"));
	            return getRealTypeValue($(cid).val());
	        }else{
	            if(type.indexOf("=")!=-1){
	                return getRealTypeValue(type.substring(2));
	            }else{
	                return getRealTypeValue(type.substring(1));
	            }
	        }
	    }
	    //显示提示信息
	    function showMyTipsIfNeed(mytips,show){
	        if(mytips!=null){
	                    if (show == null) {
	                        alert(mytips);
	                    } else {
	                        g(show).innerHTML = mytips;
	                    }
	                    }
	    }
	    //验证规则map
	    var ruleMap=[
	        {type:"double",method:function(value){return (!isNaN(value));}},//数字校验
	        {type:"number",method:function(value){return (!isNaN(value));}},//数字校验
	        {type:"pnumber",method:function(value){return (!isNaN(value)&&value*1>0);}},//验证正数
	        {type:"int",method:function(value){return TestRgexp(/^-?[0-9]\d*$/, value);}},//整数校验
	        {type:"pint",method:function(value){return TestRgexp(/^[0-9]*[1-9][0-9]*$/, value);}},//正整数校验
	        {type:"email",method:function(value){return TestRgexp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/, value);}},//Email校验
	        {type:"filepath",method:function(value){return TestRgexp(/^([a-zA-Z]){1}:(\\[^\/\\:\*\?\"<>]+)*(\\)?$/, value);}},//URL校验
	        {type:"url",method:function(value){return TestRgexp(/^([a-zA-Z]){1}:(\\[^\/\\:\*\?\"<>]+)*(\\)?$/, value);}},//URL校验
	        {type:"date",method:function(value){return TestRgexp(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/,value);}}//日期验证
	    ];

	    //检测自身
	    function checkSelf(type,value,mytips,show,notNull){
	        var error=1;
	        var success=2;
	        var canNotCheck=3;
	        if(type=="string"){
	            return success;
	        }
	        var process=false;
	        for(var i=0;i<ruleMap.length;i++){
	            if(type==ruleMap[i].type){
	                process=true;
	                var result=ruleMap[i].method.call(this,value);
	                if (!result) {
	                    showMyTipsIfNeed(mytips,show);
	                    return error;
	                }
	            }
	        }
	        return process?success:canNotCheck;
	     
	        
	    }
	    //调用正则表达式验证
	    function TestRgexp(re, s) {
	        return re.test(s);
	    }

    /********************************************************************
     ************************表单验证 结束******************************
     ********************************************************************/  
	//front search
    function search(){
		$("#p").val("1");
		$("#q").val($("#keywords").val());
		$("#searchForm").submit();
	}
	//init event bind and process 
	$(function() {
		//url forward control
		var url=window.location.href;
		var rel="home";
		if(url.indexOf("company")!=-1){
			rel="company";
		}else if(url.indexOf("person")!=-1){
			rel="person";
		}else if(url.indexOf("home")!=-1){
			rel="home";
		}else if(url.indexOf("article?category=1")!=-1||url.indexOf("article/detail/1")!=-1){
			rel="article1";
		}else if(url.indexOf("article?category=2")!=-1||url.indexOf("article/detail/2")!=-1){
			rel="article2";
		}else if(url.indexOf("article?category=3")!=-1||url.indexOf("article/detail/3")!=-1){
			rel="article3";
		}else if(url.indexOf("article?category=4")!=-1||url.indexOf("article/detail/4")!=-1){
			rel="article4";
		}
		$("a.navitem[rel='"+rel+"']").addClass("active");
		//input[placeholder]效果
		if(!isPlaceholderSupport()){
			$('input[placeholder]').focus(function() {
				var input = $(this);
				var pl=input.attr('placeholder');
				if (input.val() == pl) {
					input.val('');
				}
			}).blur(function() {
						var input = $(this);
						var pl=input.attr('placeholder');
						if (input.val() == ''
								|| input.val() ==pl ) {
							input.val(pl);
						}
					}).blur();
		}
		//header search btn event bind
		$("#keywords").keyup(function(event){
				if(event.which==13){
					search();
				}else{
					if($("#keywords").val().length>100){
						$("#keywords").val($("#keywords").val().sunstring(0,100));
					}
				}
		});
		 //img lazyload 
		 $(window).on("load",function(){
			 $("img.lazy").show().lazyload({ 
			      effect:"fadeIn", //加载图片使用的效果(淡入)  
			      effect_speed: 1000
			});
		 })
		
	});

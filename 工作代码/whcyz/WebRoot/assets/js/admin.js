
/*
 * jQuery hashchange event - v1.3 - 7/21/2010
 * http://benalman.com/projects/jquery-hashchange-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
(function($,e,b){var c="hashchange",h=document,f,g=$.event.special,i=h.documentMode,d="on"+c in e&&(i===b||i>7);function a(j){j=j||location.href;return"#"+j.replace(/^[^#]*#?(.*)$/,"$1")}$.fn[c]=function(j){return j?this.bind(c,j):this.trigger(c)};$.fn[c].delay=50;g[c]=$.extend(g[c],{setup:function(){if(d){return false}$(f.start)},teardown:function(){if(d){return false}$(f.stop)}});f=(function(){var j={},p,m=a(),k=function(q){return q},l=k,o=k;j.start=function(){p||n()};j.stop=function(){p&&clearTimeout(p);p=b};function n(){var r=a(),q=o(m);if(r!==m){l(m=r,q);$(e).trigger(c)}else{if(q!==m){location.href=location.href.replace(/#.*/,"")+q}}p=setTimeout(n,$.fn[c].delay)}$.browser.msie&&!d&&(function(){var q,r;j.start=function(){if(!q){r=$.fn[c].src;r=r&&r+a();q=$('<iframe tabindex="-1" title="empty"/>').hide().one("load",function(){r||l(a());n()}).attr("src",r||"javascript:0").insertAfter("body")[0].contentWindow;h.onpropertychange=function(){try{if(event.propertyName==="title"){q.document.title=h.title}}catch(s){}}}};j.stop=k;o=function(){return a(q.location.href)};l=function(v,s){var u=q.document,t=$.fn[c].domain;if(v!==s){u.title=h.title;u.open();t&&u.write('<script>document.domain="'+t+'"<\/script>');u.close();q.location.hash=v}}})();return j})()})(jQuery,this);

/**
 * T模板
 */
(function(){function c(a){this.t=a}function l(a,b){for(var e=b.split(".");e.length;){if(!(e[0]in a))return!1;a=a[e.shift()]}return a}function d(a,b){return a.replace(h,function(e,a,i,f,c,h,k,m){var f=l(b,f),j="",g;if(!f)return"!"==i?d(c,b):k?d(m,b):"";if(!i)return d(h,b);if("@"==i){e=b._key;a=b._val;for(g in f)f.hasOwnProperty(g)&&(b._key=g,b._val=f[g],j+=d(c,b));b._key=e;b._val=a;return j}}).replace(k,function(a,c,d){return(a=l(b,d))||0===a?"%"==c?(new Option(a)).innerHTML.replace(/"/g,"&quot;"):
a:""})}var h=/\{\{(([@!]?)(.+?))\}\}(([\s\S]+?)(\{\{:\1\}\}([\s\S]+?))?)\{\{\/\1\}\}/g,k=/\{\{([=%])(.+?)\}\}/g;c.prototype.render=function(a){return d(this.t,a)};window.t=c})();
/**
 * end
 */

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
  /**
   * 设置select选中值
   * @param id
   * @param value
   */
  function setSelectValue(id,value){
	  $("#"+id).val(value?value:0);
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
  function waittingform(form){
		$("#"+form).hide();
		$("#submitModalBtn").hide();
		$("#"+form).parent().find("#formwaitting").show();
		
	}
  function closeWaittingForm(form){
		setTimeout(function(){
			$("#"+form).show();
			$("#formwaitting").hide();
			$("#submitModalBtn").show();
		}, 200);
	}

$(function() {
	$(window).hashchange(function(){
		var url=location.hash;
		if(url&&url.indexOf("#")!=-1){
			url=url.replace("#","");
				  appLoading();
				    $("#mainContainer").empty();
				    $.get(url,function(data,status){
				    	closeAppLoading();
				    	if(status=="success"){
				    		$("#mainContainer").html(data);
				    	}else{
				    		alert("读取失败！");
				    	}
				    });
				    $(".nav li.active").removeClass("active");
				    if(url.indexOf("company")!=-1){
				    	$(".nav a.navitem[href='/companymgr']").parent("li").addClass("active"); 
				    }
				    if(url.indexOf("person")!=-1){
				    	$(".nav  a.navitem[href='/personmgr']").parent("li").addClass("active"); 
				    }
				    if(url.indexOf("article")!=-1){
				    	$(".nav  a.navitem[href='/articlemgr']").parent("li").addClass("active"); 
				    }
				    if(url.indexOf("link")!=-1){
				    	$(".nav a.navitem[href='/linkmgr']").parent("li").addClass("active"); 
				    }
				    if(url.indexOf("home")!=-1){
				    	$(".nav a.navitem[href='/admin/home']").parent("li").addClass("active"); 
				    }
				    if(url.indexOf("account")!=-1){
				    	$(".nav a.navitem[href='/accountmgr']").parent("li").addClass("active"); 
				    }
		}else{
			appLoading();
		    $("#mainContainer").empty();
		    $.get("/admin/home",function(data,status){
		    	closeAppLoading();
		    	if(status=="success"){
		    		$("#mainContainer").html(data);
		    	}else{
		    		alert("读取失败！");
		    	}
		    });
		}
		
	});
	//绑定上端nav事件
	$("body").on("click","a.navitem",function(event){
		event.preventDefault();
		event.stopPropagation();
	    var url=$(this).attr("href");
	    window.location.hash=url;
	    return false;
	});
	
	$(window).hashchange();

});